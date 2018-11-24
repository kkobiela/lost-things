import 'package:flutter/material.dart';
import 'package:lost_things/core/main_app_bar.dart';
import 'package:lost_things/states/main_app_bar_state.dart';

class AddAdPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: MainAppBar(state: MainAppBarState(true)),
      body: Center(child: Text('AddAdPage')),
    );
  }
}
