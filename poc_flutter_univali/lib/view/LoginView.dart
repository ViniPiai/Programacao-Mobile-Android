import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:pocflutterunivali/db/sign_in.dart';
import 'package:pocflutterunivali/view/ContentView.dart';
import 'package:random_color/random_color.dart';

class LoginView extends StatefulWidget {

    static const String routeName = "/loginView";

    @override
    _LoginViewState createState() => _LoginViewState();
}

class _LoginViewState extends State<LoginView> {

    /*PhoneNumberDB _phoneNumberDB = new PhoneNumberDB();
    PhoneValidator _phoneValidator = new PhoneValidator();*/
    RandomColor _randomColor = RandomColor();
    /*String _phoneNumber;
    String _smsCode;
    String _verificationId;
    FocusNode _phoneInput = new FocusNode();

    Future<void> verifyPhone() async {
        final PhoneCodeAutoRetrievalTimeout autoRetrieve = (String verId) {
            this._verificationId = verId;
        };

        final PhoneCodeSent _smsCodeSent = (String verId, [int forceCodeResend]) {
            this._verificationId = verId;
            _smsCodeDialog(context).then((value) {
                print('Signed in');
            });
        };

        final PhoneVerificationCompleted verifiedSuccess = (AuthCredential user) {
            print('verified');
        };

        final PhoneVerificationFailed veriFailed = (AuthException exception) {
            Scaffold.of(context).showSnackBar(SnackBar(
               content: Text("Número de celular invalido"),
            ));
        };

        await FirebaseAuth.instance.verifyPhoneNumber(
            phoneNumber: this._phoneNumber,
            codeAutoRetrievalTimeout: autoRetrieve,
            codeSent: _smsCodeSent,
            timeout: const Duration(seconds: 5),
            verificationCompleted: verifiedSuccess,
            verificationFailed: veriFailed);
    }

    Future<bool> _smsCodeDialog(BuildContext context) {
        return showDialog(
            context: context,
            barrierDismissible: false,
            builder: (BuildContext context) {
                return new AlertDialog(
                    title: Text('Digite o código enviado por SMS'),
                    content: TextField(
                        onChanged: (value) {this._smsCode = value;},
                        keyboardType: TextInputType.phone,
                    ),
                    contentPadding: EdgeInsets.all(10.0),
                    shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(25),
                    ),
                    actions: <Widget>[
                        new FlatButton(
                            child: Text('Done'),
                            onPressed: () {
                                FirebaseAuth.instance.currentUser().then((user) async{
                                    if (user != null) {
                                        Navigator.of(context).pop();

                                        DocumentSnapshot doc = await _phoneNumberDB.getOne(_phoneNumber);
                                        if(!doc.exists){
                                            Color _color = _randomColor.randomColor(
                                                colorBrightness: ColorBrightness.dark
                                            );
                                            _phoneNumberDB.insertOne(_phoneNumber,
                                                _color.toString().replaceAll("Color(0xff", "").replaceAll(")", ""));
                                        }
                                        Navigator.of(context).pushNamed(ContentView.routeName, arguments: {'phone': _phoneNumber});
                                    } else {
                                        Navigator.of(context).pop();
                                        signIn();
                                    }
                                });
                            },
                        )
                    ],
                );
            });
    }

    signIn() async{
        final AuthCredential credential = PhoneAuthProvider.getCredential(
            verificationId: _verificationId,
            smsCode: _smsCode
        );
        await FirebaseAuth.instance.signInWithCredential(credential).then((res) async{
            DocumentSnapshot doc = await _phoneNumberDB.getOne(_phoneNumber);
            if(!doc.exists){
                Color _color = _randomColor.randomColor(
                    colorBrightness: ColorBrightness.dark
                );
                _phoneNumberDB.insertOne(_phoneNumber, _color.toString().replaceAll("Color(0xff", "")
                    .replaceAll(")", ""));
            }
            Navigator.of(context).pushNamed(ContentView.routeName, arguments: {'phone': _phoneNumber});
        }).catchError((error)=>
            Scaffold.of(context).showSnackBar(SnackBar(
                content: Text("Número de celular invalido"),
            ))
        );
    }*/

    @override
    Widget build(BuildContext context) {
        var height = MediaQuery.of(context).size.height;
        var width = MediaQuery.of(context).size.width;
        var bottomInsets = MediaQuery.of(context).viewInsets.bottom;
        var percentHeight = 0.5;
        return Scaffold(
            body: Container(
                color: Colors.white,
                child: Center(
                    child: Column(
                        mainAxisSize: MainAxisSize.max,
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: <Widget>[
                            FlutterLogo(size: 150),
                            SizedBox(height: 50),
                            _signInButton(),
                        ],
                    ),
                ),
            ),
        );
            /*Scaffold(
            resizeToAvoidBottomInset: false,
            resizeToAvoidBottomPadding: false,
            body:SingleChildScrollView(
                child: Container(
                    height: height,
                    width: width,
                    color: Theme.of(context).primaryColor,
                    child: Column(
                        mainAxisAlignment: bottomInsets == 0 ?MainAxisAlignment.center : MainAxisAlignment.start,
                        crossAxisAlignment: CrossAxisAlignment.center,
                        children: <Widget>[
                            SizedBox(height: bottomInsets == 0 ? height * 0.05 : height*0.1),
                            Card(
                                shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(25)
                                ),
                                child: Container(
                                    height: height * percentHeight,
                                    width: width * 0.8,
                                    child: Column(
                                        mainAxisAlignment: MainAxisAlignment.center,
                                        crossAxisAlignment: CrossAxisAlignment.center,
                                        children: <Widget>[
                                            Container(
                                                height: height*percentHeight*0.2,
                                                child: Padding(
                                                    padding: EdgeInsets.fromLTRB(0, 16, 0, 4),
                                                    child: Text(
                                                        "Prove of Concept\nFlutter",
                                                        style: TextStyle(
                                                            fontSize: 25,
                                                            color: Theme.of(context).primaryColor,
                                                            fontWeight: FontWeight.bold,
                                                        ),
                                                        textAlign: TextAlign.center,
                                                    ),
                                                )
                                            ),
                                            Container(
                                                width: width*0.8*0.8,
                                                height: height*percentHeight*0.4,
                                                child: Center(
                                                    child: TextField(
                                                        onChanged: (value) {
                                                            _phoneNumber = value;
                                                        },
                                                        focusNode: _phoneInput,
                                                        keyboardType: TextInputType.number,
                                                        decoration: InputDecoration(
                                                            border: OutlineInputBorder(
                                                                borderSide: BorderSide(
                                                                    color: Theme.of(context).primaryColor,
                                                                    width: 5
                                                                ),
                                                                borderRadius: BorderRadius.circular(15),
                                                            ),
                                                            prefixIcon: Icon(
                                                                Icons.phone_android,
                                                                color: Theme.of(context).primaryColor,
                                                                size: 40,
                                                            ),

                                                        ),
                                                        style: TextStyle(
                                                            fontSize: 20
                                                        ),
                                                        inputFormatters: <TextInputFormatter>[
                                                            LengthLimitingTextInputFormatter(15),
                                                            WhitelistingTextInputFormatter.digitsOnly,
                                                            _phoneValidator
                                                        ],
                                                    ),
                                                )
                                            ),
                                            Padding(
                                                padding: EdgeInsets.fromLTRB(0, 20, 0, 20),
                                                child: ButtonTheme(
                                                    height: 50,
                                                    minWidth: width*0.8*0.8,
                                                    child: FlatButton(
                                                        child: Text(
                                                            "ENTRAR",
                                                            style: TextStyle(
                                                                color: Theme.of(context).primaryColor,
                                                                fontSize: 25,
                                                                fontWeight: FontWeight.bold
                                                            ),
                                                        ),
                                                        shape: RoundedRectangleBorder(
                                                            borderRadius: BorderRadius.circular(25),
                                                            side: BorderSide(
                                                                color: Theme.of(context).primaryColor,
                                                                width: 2
                                                            )
                                                        ),
                                                        onPressed: (){
                                                            this._phoneNumber = "+55"+this._phoneNumber
                                                                .replaceAll("(", "")
                                                                .replaceAll(")", "")
                                                                .replaceAll("-", "")
                                                                .replaceAll(" ", "");
                                                            print(this._phoneNumber);
                                                            verifyPhone();
                                                        },
                                                    ),
                                                ),
                                            )
                                        ],
                                    ),
                                ),
                            ),
                        ],
                    )),
            )
        );*/
    }

    Widget _signInButton() {
        return OutlineButton(
            splashColor: Colors.grey,
            onPressed: () {
                signInWithGoogle().whenComplete(() {
                    Navigator.of(context).popAndPushNamed(ContentView.routeName, arguments: {});
                });
            },
            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(40)),
            highlightElevation: 0,
            borderSide: BorderSide(color: Colors.grey),
            child: Padding(
                padding: const EdgeInsets.fromLTRB(0, 10, 0, 10),
                child: Row(
                    mainAxisSize: MainAxisSize.min,
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: <Widget>[
                        Image(image: AssetImage("images/google.png"), height: 35.0),
                        Padding(
                            padding: const EdgeInsets.only(left: 10),
                            child: Text(
                                'Sign in with Google',
                                style: TextStyle(
                                    fontSize: 20,
                                    color: Colors.grey,
                                ),
                            ),
                        )
                    ],
                ),
            ),
        );
    }
}
