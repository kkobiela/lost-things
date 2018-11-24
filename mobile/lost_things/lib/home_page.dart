import 'package:flutter/material.dart';
import 'package:lost_things/core/main_app_bar.dart';
import 'package:lost_things/core/navigation_bar.dart';
import 'package:lost_things/home_page_body.dart';
import 'package:lost_things/states/main_app_bar_state.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: MainAppBar(
          state: MainAppBarState(true),
        ),
        body: Center(child: HomePageBody()),
        bottomNavigationBar: NavigationBar());
  }
}
