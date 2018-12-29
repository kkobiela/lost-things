import 'package:flutter/material.dart';
import 'package:lost_things/ad_list/ad_list.dart';
import 'package:lost_things/models/ad_list_item.dart';

class HomePageBody extends StatelessWidget {
  final List<AdListItem> ads = [
    AdListItem(
        title: 'Advertisement 1',
        location: 'Katowice',
        thumbnail:
            'https://images.pexels.com/photos/573407/pexels-photo-573407.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=500&w=500'),
    AdListItem(
        title: 'Advertisement 2',
        location: 'Gliwice',
        thumbnail:
            'https://images.pexels.com/photos/573407/pexels-photo-573407.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=500&w=500'),
    AdListItem(
        title: 'Advertisement 3',
        location: 'Gliwice',
        thumbnail:
            'https://images.pexels.com/photos/573407/pexels-photo-573407.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=500&w=500'),
    AdListItem(
        title: 'Advertisement 4',
        location: 'Gliwice',
        thumbnail:
            'https://images.pexels.com/photos/573407/pexels-photo-573407.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=500&w=500'),
    AdListItem(
        title: 'Advertisement 5',
        location: 'Gliwice',
        thumbnail:
            'https://images.pexels.com/photos/573407/pexels-photo-573407.jpeg?auto=compress&cs=tinysrgb&dpr=1&fit=crop&h=500&w=500'),
  ];

  @override
  Widget build(BuildContext context) {
    return Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[Flexible(child: AdList(ads: ads))]);
  }
}
