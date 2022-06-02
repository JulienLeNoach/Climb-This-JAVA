package controllers;

import java.io.IOException;

import java.sql.*;

import views.*;
import javax.swing.JOptionPane;

import models.User;
import models.Wall;
import utils.*;

public class ControllerAskModerator {
  private final String strQueryContact = "INSERT INTO mod_request (subject,object,id_member) VALUES(?,?,?);";
  private String subject;
  private String object;

  public ControllerAskModerator(ViewHomePage homePage, User user, Wall wall, MainFrame mainframe)
      throws ClassNotFoundException, SQLException, IOException {

    /* création de la connection de la BDD */
    ViewAskModerator askModerator = new ViewAskModerator(homePage, user, wall, mainframe);
    homePage.remove(1);
    homePage.add(askModerator);
    homePage.repaint();
    homePage.revalidate();

  }

  public ControllerAskModerator(String subject, String object, ViewHomePage homePage, User user, Wall wall,
      MainFrame mainframe)
      throws ClassNotFoundException, SQLException, IOException {
    this.subject = subject;
    this.object = object;
    if (!(this.subject.isEmpty() || this.subject.isBlank()
        || this.object.isEmpty() || this.object.isBlank())
        && (this.subject.matches("^[a-zA-Z0-9 ]*$")
            && this.object.matches("^[a-zA-Z0-9 ]*$"))) {
      try {
        /* création de la connection de la BDD */
        BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this",
            "climb", "climb",
            "jdbc:mysql://localhost:3306/");

        conn1.setPreparedStatement(strQueryContact);
        conn1.getPreparedStatement().setString(1, subject);
        conn1.getPreparedStatement().setString(2, object);
        conn1.getPreparedStatement().setInt(3, user.getId_member());
        conn1.getPreparedStatement().executeUpdate();
        JOptionPane.showMessageDialog(null, "Votre message à bien été envoyé", "ATTENTION", 2);
        new ControllerHomePage(mainframe, user, wall);
        conn1.disconnectDB();

      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      JOptionPane.showMessageDialog(null, "Veuillez remplir le formulaire correctement", "ATTENTION", 2);
    }
  }
}
