import 'package:flutter/material.dart';
import 'package:lost_things/home_page_body.dart';
import 'package:lost_things/shared/main_app_bar.dart';
import 'package:lost_things/shared/navigation_bar.dart';

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: MainAppBar(),
        body: Center(child: HomePageBody()),
        bottomNavigationBar: NavigationBar());
  }
}
