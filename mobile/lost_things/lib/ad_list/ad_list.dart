import 'package:flutter/material.dart';
import 'package:lost_things/ad_list/ad_list_tile.dart';
import 'package:lost_things/models/ad_list_item.dart';

class AdList extends StatelessWidget {
  final List<AdListItem> ads;

  AdList({this.ads});

  @override
  Widget build(BuildContext context) {
    return Container(
        padding: EdgeInsets.all(12),
        child: ListView.builder(
          scrollDirection: Axis.vertical,
          shrinkWrap: true,
          itemCount: this.ads.length,
          itemBuilder: (BuildContext context, int index) {
            return AdListTile(ad: ads[index]);
          },
        ));
  }
}
