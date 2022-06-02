package controllers;

import models.User;
import models.Wall;
import views.MainFrame;
import views.ViewHomePage;

public class ControllerHomePage {

    public ControllerHomePage(MainFrame mainFrame, User user, Wall wall){ 
    ViewHomePage viewHomepage = new ViewHomePage(mainFrame, user, wall);
        mainFrame.getContentPane().removeAll();
        mainFrame.add(viewHomepage);
        mainFrame.repaint();
        mainFrame.revalidate();}
}
