import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/cupertino.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:pocflutterunivali/db/EmailDB.dart';
import 'package:random_color/random_color.dart';

final FirebaseAuth _auth = FirebaseAuth.instance;
final GoogleSignIn googleSignIn = GoogleSignIn();

Future<String> signInWithGoogle() async {
	final GoogleSignInAccount googleSignInAccount = await googleSignIn.signIn();
	final GoogleSignInAuthentication googleSignInAuthentication =
	await googleSignInAccount.authentication;

	final AuthCredential credential = GoogleAuthProvider.getCredential(
		accessToken: googleSignInAuthentication.accessToken,
		idToken: googleSignInAuthentication.idToken,
	);

	final AuthResult authResult = await _auth.signInWithCredential(credential);
	final FirebaseUser user = authResult.user;

	assert(!user.isAnonymous);
	assert(await user.getIdToken() != null);

	final FirebaseUser currentUser = await _auth.currentUser();
	assert(user.uid == currentUser.uid);
	EmailDB _email = new EmailDB();
	RandomColor _randomColor = new RandomColor();
	DocumentSnapshot docEmail = await _email.getOne(currentUser.email);
	if(!docEmail.exists){
		Color c = _randomColor.randomColor(colorHue: ColorHue.random);
		String color = c.toString().replaceAll("Color(0xff", "")
			.replaceAll(")", "");
		print(color);
		_email.insertOne(currentUser.email, color);
	}

	return 'signInWithGoogle succeeded: $user';
}

void signOutGoogle() async{
	await googleSignIn.signOut();

	print("User Sign Out");
}