package controllers;

import java.sql.SQLException;

import models.User;
import models.Wall;
import views.ViewHomePage;
import views.ViewWall;

public class ControllerWall {
    public ControllerWall(ViewHomePage homePage, User user, Wall wall) throws ClassNotFoundException, SQLException {
        ViewWall wallPanel = new ViewWall(homePage, user, wall);
        homePage.remove(1);
        homePage.add(wallPanel);
        homePage.repaint();
        homePage.revalidate();
    }
}
