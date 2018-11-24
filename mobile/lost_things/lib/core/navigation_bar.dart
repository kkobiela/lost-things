import 'package:flutter/material.dart';
import 'package:lost_things/add_ad/add_ad_page.dart';

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
              IconButton(
                  icon: Icon(Icons.home),
                  onPressed: () {
                    Navigator.canPop(context);
                  }),
              IconButton(
                  icon: Icon(Icons.add),
                  onPressed: () {
                    Navigator.push(context,
                        MaterialPageRoute(builder: (context) => AddAdPage()));
                  })
            ])));
  }
}
