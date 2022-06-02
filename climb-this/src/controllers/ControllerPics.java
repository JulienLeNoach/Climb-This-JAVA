package controllers;

import models.User;
import models.Wall;
import views.ViewHomePage;
import views.ViewPics;

public class ControllerPics {
  public ControllerPics(ViewHomePage homePage, User user, Wall wall, int laneIndex, int albumIndex) {
    ViewPics picsPage = new ViewPics(homePage, user, wall, laneIndex, albumIndex);
    homePage.remove(1);
    homePage.add(picsPage);
    homePage.repaint();
    homePage.revalidate();
  }
}
