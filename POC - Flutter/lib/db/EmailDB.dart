
import 'package:cloud_firestore/cloud_firestore.dart';

class EmailDB {

	Firestore _firestore = Firestore.instance;

	Future<DocumentSnapshot> getOne(String email) async{
		return await _firestore.collection("emails").document(email).get();
	}

	void insertOne(String email, String color){
		_firestore.collection("emails").document(email).setData({"color": color});
	}

}