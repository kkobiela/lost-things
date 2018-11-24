import 'package:flutter/material.dart';

class MainAppBar extends StatelessWidget implements PreferredSizeWidget {
  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Color(0xfafafaf8),
      centerTitle: true,
      elevation: 1,
      title: Text('Lost Things'),
      actions: <Widget>[
        Padding(padding: EdgeInsets.only(right: 12), child: Icon(Icons.search))
      ],
    );
  }

  @override
  Size get preferredSize => AppBar().preferredSize;
}
