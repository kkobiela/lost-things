import 'package:flutter/material.dart';
import 'package:lost_things/ad_list.dart';

class Body extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Column(
        mainAxisAlignment: MainAxisAlignment.start,
        children: <Widget>[Flexible(child: AdList())]);
  }
}
