import 'dart:io';

import 'package:cached_network_image/cached_network_image.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:firebase_storage/firebase_storage.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:font_awesome_flutter/font_awesome_flutter.dart';
import 'package:image_picker/image_picker.dart';
import 'package:percent_indicator/linear_percent_indicator.dart';
import 'package:pocflutterunivali/db/MessagesDB.dart';
import 'package:pocflutterunivali/db/EmailDB.dart';
import 'package:pocflutterunivali/db/sign_in.dart';
import 'package:pocflutterunivali/view/LoginView.dart';

class ContentView extends StatefulWidget {
  static const String routeName = "/contentView";

  @override
  _ContentViewState createState() => _ContentViewState();
}

class _ContentViewState extends State<ContentView> {
  MessagesDB _messagesDB = new MessagesDB();
  EmailDB _emailDB = new EmailDB();
  TextEditingController _controller = new TextEditingController();
  FirebaseUser _user;
  bool _loadingFile = false;
  double _progress = 0.0;

  @override
  Widget build(BuildContext context) {
    load();
    return Scaffold(appBar: appBar(), body: body());
  }

  Widget appBar() {
    return AppBar(
      title: Text(
        "Prove of Concept",
        style: TextStyle(
          color: Colors.white,
        ),
      ),
      automaticallyImplyLeading: true,
      actions: <Widget>[
        Row(
          mainAxisAlignment: MainAxisAlignment.start,
          children: <Widget>[
            IconButton(
              icon: FaIcon(
                FontAwesomeIcons.signOutAlt,
                color: Colors.white,
              ),
              iconSize: 25,
              onPressed: () {
                showDialog(
                    context: context,
                    builder: (BuildContext context) {
                      return AlertDialog(
                        title: Text("Deseja realmente sair?"),
                        actions: <Widget>[
                          FlatButton(
                            child: Text("Não"),
                            onPressed: () {
                              Navigator.of(context).pop();
                            },
                          ),
                          FlatButton(
                            child: Text("Sim"),
                            onPressed: () {
                              Navigator.pop(context);
                              signOutGoogle();
                              Navigator.of(context).popAndPushNamed(LoginView.routeName);
                            },
                          )
                        ],
                      );
                    });
              },
            ),
          ],
        )
      ],
    );
  }

  Widget body() {
    double height = MediaQuery.of(context).size.height;
    double width = MediaQuery.of(context).size.width;

    return Column(
      children: <Widget>[
        _loadingFile ? uploadingFile() : Container(),
        Expanded(
          child: StreamBuilder(
            stream: _messagesDB.getAllForStream(),
            builder: (context, snapshot) {
              if (!snapshot.hasData) {
                return Center(
                  child: Text("Carregando..."),
                );
              }
              return ListView.builder(
                  reverse: true,
                  shrinkWrap: true,
                  itemCount: snapshot.data.documents.length,
                  itemBuilder: (context, index) {
                    DocumentSnapshot doc = snapshot.data.documents[index];

                    return Align(
                      alignment: _user.email == doc['email'] ? Alignment.centerRight : Alignment.centerLeft,
                      child: Padding(
                        padding: EdgeInsets.all(6),
                        child: Container(
                            width: MediaQuery.of(context).size.width * 0.8,
                            decoration: BoxDecoration(
                              color: Color(int.parse("FF" + doc['color'], radix: 16)),
                              borderRadius: BorderRadius.circular(8),
                            ),
                            child: doc['type'] == 'message' ? showMessage(doc) : showImage(doc)),
                      ),
                    );
                  });
            },
          ),
        ),
        inputBar()
      ],
    );
  }

  Widget showImage(DocumentSnapshot doc) {
    double height = MediaQuery.of(context).size.height;
    double width = MediaQuery.of(context).size.width;
    return Column(
      children: <Widget>[
        _user.email != doc['email']
            ? Align(
                alignment: Alignment.centerLeft,
                child: Padding(
                  padding: EdgeInsets.fromLTRB(8, 8, 8, 8),
                  child: Text(
                    doc['name'],
                    style: TextStyle(color: Colors.white, fontSize: 16),
                    textAlign: TextAlign.left,
                  ),
                ))
            : Container(),
        CachedNetworkImage(
          imageUrl: doc['uri'],
          progressIndicatorBuilder: (context, url, downloadProgress) => Container(
            height: height * 0.6 * 0.6,
            width: width * 0.6 * 0.6,
            child: Center(
              child: CircularProgressIndicator(
                value: downloadProgress.progress,
                backgroundColor: Colors.black,
                strokeWidth: 2,
              ),
            ),
          ),
          imageBuilder: (context, imageProvider) => Container(
            height: height * 0.8 * 0.8,
            width: width * 0.8 * 0.8,
            decoration: BoxDecoration(
                image: DecorationImage(
              image: imageProvider,
              fit: BoxFit.cover,
            )),
          ),
          errorWidget: (context, url, error) => Icon(Icons.error),
        )
      ],
    );
  }

  Widget showMessage(DocumentSnapshot doc) {
    Column(
      children: <Widget>[
        _user.email != doc['email']
            ? Align(
                alignment: Alignment.centerLeft,
                child: Padding(
                  padding: EdgeInsets.fromLTRB(8, 8, 8, 8),
                  child: Text(
                    doc['name'],
                    style: TextStyle(color: Colors.white, fontSize: 16),
                    textAlign: TextAlign.left,
                  ),
                ))
            : Container(),
        Align(
            alignment: Alignment.centerLeft,
            child: Padding(
              padding: EdgeInsets.fromLTRB(8, 8, 8, 8),
              child: Text(
                doc['message'],
                style: TextStyle(color: Colors.white, fontSize: 16),
                textAlign: TextAlign.left,
              ),
            )),
      ],
    );
  }

  Widget uploadingFile() {
    double width = MediaQuery.of(context).size.width;
    return Padding(
        padding: EdgeInsets.fromLTRB(width * 0.05, 8, width * 0.05, 8),
        child: Center(
          child: LinearPercentIndicator(
            width: width * 0.9,
            lineHeight: 25,
            percent: _progress / 100,
            center: Text(
              (_progress).toStringAsFixed(2) + "%",
              style: TextStyle(fontSize: 20),
            ),
            linearStrokeCap: LinearStrokeCap.roundAll,
            backgroundColor: Colors.grey,
            progressColor: Colors.green,
          ),
        ));
  }

  Widget inputBar() {
    return Padding(
      padding: EdgeInsets.all(8.0),
      child: Row(
        children: <Widget>[
          Expanded(
            child: roundedContainer(),
          ),
          SizedBox(
            width: 5.0,
          ),
          GestureDetector(
            onTap: () async {
              DocumentSnapshot doc = await _emailDB.getOne(_user.email);
              _messagesDB.insertMessage(_controller.text, _user.email, _user.displayName, doc['color']);
              _controller.clear();
            },
            child: CircleAvatar(
              child: Icon(
                Icons.send,
                color: Colors.white,
              ),
              backgroundColor: Theme.of(context).primaryColor,
            ),
          ),
        ],
      ),
    );
  }

  Widget roundedContainer() {
    return ClipRRect(
      borderRadius: BorderRadius.circular(20.0),
      child: Container(
        color: Colors.white,
        child: Row(
          children: <Widget>[
            SizedBox(width: 8.0),
            Expanded(
              child: TextField(
                controller: _controller,
                decoration: InputDecoration(
                  hintText: 'Digite uma mensagem ...',
                  border: InputBorder.none,
                ),
                onEditingComplete: () {
                  FocusScope.of(context).requestFocus(new FocusNode());
                },
              ),
            ),
            IconButton(
              icon: Icon(Icons.attach_file, size: 30.0, color: Theme.of(context).hintColor),
              onPressed: () {
                if (!_loadingFile) {
                  chooseFile("galery");
                } else {
                  Fluttertoast.showToast(
                      msg: "Uma imagem está sendo carregada no momento, \npor favor, espere para enviar outra",
                      toastLength: Toast.LENGTH_LONG,
                      gravity: ToastGravity.BOTTOM,
                      backgroundColor: Colors.grey,
                      textColor: Colors.white,
                      fontSize: 16);
                }
              },
            ),
            SizedBox(width: 8.0),
            IconButton(
              icon: Icon(Icons.camera_alt, size: 30.0, color: Theme.of(context).hintColor),
              onPressed: () {
                if (!_loadingFile) {
                  chooseFile("camera");
                } else {
                  Fluttertoast.showToast(
                      msg: "Uma imagem está sendo carregada no momento, \npor favor, espere para enviar outra",
                      toastLength: Toast.LENGTH_LONG,
                      gravity: ToastGravity.BOTTOM,
                      backgroundColor: Colors.grey,
                      textColor: Colors.white,
                      fontSize: 16);
                }
              },
            ),
            SizedBox(width: 8.0),
          ],
        ),
      ),
    );
  }

  void chooseFile(String value) async {
    File selectedFile;
    switch (value) {
      case "galery":
        // ignore: deprecated_member_use
        final file = await ImagePicker.pickImage(source: ImageSource.gallery);
        if (file != null) {
          selectedFile = File(file.path);
        }
        break;
      case "camera":
        // ignore: deprecated_member_use
        final file = await ImagePicker.pickImage(source: ImageSource.camera);
        if (file != null) {
          selectedFile = File(file.path);
        }
        break;
    }
    if (selectedFile != null) {
      DocumentSnapshot doc = await _emailDB.getOne(_user.email);
      FirebaseStorage storage = FirebaseStorage.instance;
      StorageReference reference = storage.ref().child(selectedFile.path);
      StorageUploadTask storageUploadTask = reference.putFile(selectedFile);
      storageUploadTask.events.listen((StorageTaskEvent storageTaskEvent) {
        if (storageTaskEvent.type == StorageTaskEventType.progress) {
          setState(() {
            _loadingFile = true;
            _progress = 100 *
                (storageTaskEvent.snapshot.bytesTransferred.ceilToDouble() /
                    storageTaskEvent.snapshot.totalByteCount.ceilToDouble());
          });
        }
        if (storageTaskEvent.type == StorageTaskEventType.success) {
          print("ENVIADO");
          setState(() {
            _loadingFile = false;
          });
        }
      });
      var downloadURL = await (await storageUploadTask.onComplete).ref.getDownloadURL();
      var url = downloadURL.toString();
      _messagesDB.insertPhoto(url, _user.email, _user.displayName, doc['color']);
    }
  }

  void load() async {
    _user = await FirebaseAuth.instance.currentUser();
    print(_user.email);
    print(_user.displayName);
  }
}
