import 'package:flutter/material.dart';

class NavigationBar extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
        color: Colors.white,
        height: 50,
        child: BottomAppBar(
            child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: <Widget>[
              IconButton(icon: Icon(Icons.home), onPressed: null),
              IconButton(icon: Icon(Icons.add), onPressed: null)
            ])));
  }
}
