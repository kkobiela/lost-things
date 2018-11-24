import 'package:flutter/material.dart';
import 'package:lost_things/core/main_app_bar.dart';
import 'package:lost_things/states/main_app_bar_state.dart';

class SearchPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MainAppBar(state: MainAppBarState(false)),
      body: Center(child: Text('SearchPage')),
    );
  }
}
