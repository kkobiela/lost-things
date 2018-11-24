import 'package:flutter/material.dart';
import 'package:lost_things/body.dart';

class Home extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Color(0xfafafaf8),
        centerTitle: true,
        elevation: 1,
        title: Text('Lost Things'),
        actions: <Widget>[
          Padding(
              padding: EdgeInsets.only(right: 12), child: Icon(Icons.search))
        ],
      ),
      body: Center(child: Body()),
      bottomNavigationBar: Container(
          color: Colors.white,
          height: 50,
          child: BottomAppBar(
            child: Row(
                mainAxisAlignment: MainAxisAlignment.spaceAround,
                children: <Widget>[
                  IconButton(icon: Icon(Icons.home), onPressed: null),
                  IconButton(icon: Icon(Icons.add), onPressed: null)
                ])
          )),
    );
  }
}
