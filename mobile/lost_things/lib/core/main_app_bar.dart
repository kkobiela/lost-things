import 'package:flutter/material.dart';
import 'package:lost_things/search/search_page.dart';
import 'package:lost_things/states/main_app_bar_state.dart';

class MainAppBar extends StatelessWidget implements PreferredSizeWidget {
  final MainAppBarState state;

  MainAppBar({Key key, @required this.state}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: Color(0xfafafaf8),
      centerTitle: true,
      elevation: 1,
      title: Text('Lost Things'),
      actions: <Widget>[
        Padding(
            padding: EdgeInsets.only(right: 12),
            child: IconButton(
              icon: Icon(Icons.search),
              onPressed: () {
                if (this.state.canNavigateToSearch) {
                  Navigator.push(context,
                      MaterialPageRoute(builder: (context) => SearchPage()));
                }
              },
            ))
      ],
    );
  }

  @override
  Size get preferredSize => AppBar().preferredSize;
}
