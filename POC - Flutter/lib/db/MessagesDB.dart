

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';

class MessagesDB {

	Firestore _firestore = Firestore.instance;

	void insertMessage(String message, String email, String name, String color) async{
		DocumentReference documentReference = await _firestore.collection("messages").add({
			"message": message,
			"email": email,
			"name": name,
			"time": FieldValue.serverTimestamp(),
			"color": color,
			"type": "message"
		});
		_firestore.collection("messages").document(documentReference.documentID).setData({
			'hash': documentReference.documentID}, merge: true);
	}

	void insertPhoto(String uri, String email, String name, String color) async{
		DocumentReference documentReference = await _firestore.collection("messages").add({
			"uri": uri,
			"email": email,
			"name": name,
			"time": FieldValue.serverTimestamp(),
			"color": color,
			"type": "photo"
		});
		_firestore.collection("messages").document(documentReference.documentID).setData({
			'hash': documentReference.documentID}, merge: true);
	}

	void deleteData(hash){
		_firestore.collection("messages").document(hash).delete();
	}

	Stream<QuerySnapshot> getAllForStream(){
		return _firestore.collection("messages").orderBy("time", descending: true).snapshots();
	}


}