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

    @override
    Widget build(BuildContext context) {
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
