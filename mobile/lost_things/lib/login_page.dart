import 'package:flutter/material.dart';
import 'package:google_sign_in/google_sign_in.dart';
import 'package:lost_things/home_page.dart';

class LoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Lost Things")),
        body: Center(
            child: RaisedButton(
                textColor: Colors.white,
                color: Colors.red,
                child: Text("Sign In with Google"),
                onPressed: () {
                  _signIn(context);
                })));
  }

  final GoogleSignIn _googleSignIn = new GoogleSignIn(
    scopes: [
      'email',
      'https://www.googleapis.com/auth/contacts.readonly',
    ],
  );

  Future<void> _signIn(context) async {
    try {
      await _googleSignIn.signIn();
      Navigator.push(
          context, MaterialPageRoute(builder: (context) => HomePage()));
    } catch (error) {
      print(error);
    }
  }
}
