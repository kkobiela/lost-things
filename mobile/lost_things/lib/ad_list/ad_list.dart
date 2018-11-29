import 'package:flutter/material.dart';
import 'package:lost_things/ad_list/ad_list_tile.dart';

class AdList extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
        padding: EdgeInsets.all(12),
        child: ListView.builder(
          scrollDirection: Axis.vertical,
          shrinkWrap: true,
          itemCount: 10,
          itemBuilder: (BuildContext context, int index) {
            return AdListTile();
          },
        ));
  }
}
