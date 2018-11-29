import 'package:flutter/material.dart';

class AdListTile extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Card(
      elevation: 8.0,
      margin: new EdgeInsets.symmetric(horizontal: 10.0, vertical: 6.0),
      child: Container(
        decoration: BoxDecoration(color: Color.fromRGBO(240, 240, 240, .9)),
        child: ListTile(
          contentPadding: EdgeInsets.all(12),
          leading: Container(
            padding: EdgeInsets.only(right: 12),
            decoration: new BoxDecoration(
                border: new Border(
                    right: new BorderSide(width: 1, color: Colors.black))),
            child: Icon(Icons.photo, color: Colors.black),
          ),
          title: Text(
            "Zagubione klucze",
            style: TextStyle(color: Colors.black, fontWeight: FontWeight.bold),
          ),
          subtitle: Row(
            children: <Widget>[
              Icon(Icons.place, color: Colors.black),
              Text(" Gdańsk", style: TextStyle(color: Colors.black))
            ],
          ),
        ),
      ),
    );
  }
}
