import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:pocflutterunivali/view/ContentView.dart';
import 'package:pocflutterunivali/view/LoginView.dart';

void main() async{
	WidgetsFlutterBinding.ensureInitialized();
	final GoogleSignIn googleSignIn = GoogleSignIn();

	runApp(MaterialApp(
		title: 'Flutter Demo',
		debugShowCheckedModeBanner: false,
		theme: ThemeData(
			primaryColor: Color(0xff16b9fd),
		),
		home: await googleSignIn.isSignedIn() ? ContentView() : LoginView(),
		// ignore: missing_return
		onGenerateRoute: (RouteSettings settings) {
			switch (settings.name) {
				case ContentView.routeName:
					return MaterialPageRoute(builder: (context) {
						return ContentView();
					});
					break;
				case LoginView.routeName:
					return MaterialPageRoute(builder: (context){
						return LoginView();
					});
					break;
			}
		},
	));
}
