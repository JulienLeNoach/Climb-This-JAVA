package controllers;

import java.sql.*;

import javax.swing.JOptionPane;

import models.User;
import views.*;
import utils.*;

public class ControllerModRequest {
  private final String strQueryContact = "SELECT utilisateur.nickname,mod_request.subject,mod_request.object,mod_request.id_request FROM mod_request JOIN utilisateur ON utilisateur.id_member = mod_request.id_member ";
  // Ajout de la contraite de suppression en cascade sur les 3 tables
  private final String strDeleteAlbum = "DELETE  FROM mod_request WHERE id_request = ?";

  public ControllerModRequest(ViewHomePage homePage, User user)
      throws ClassNotFoundException, SQLException {

    try {
      /* création de la connection de la BDD */
      BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
          "jdbc:mysql://localhost:3306/");
      ResultSet rsiD = null;
      conn1.setPreparedStatement(strQueryContact);
      rsiD = conn1.getPreparedStatement().executeQuery();

      String columns[] = { "<html><b>Pseudo</b></html>", "<html><b>Sujet</b></html>", "<html><b>Message</b></html>",
          "<html><b>Numero de la demande</b></html>" };// Creation du header
      String data[][] = new String[25][5];// Creation du tableau

      int i = 0;
      while (rsiD.next()) {// On recupère les données avec le while et on les intègre dans le tableau
        String nickname = rsiD.getString("nickname");
        String sub = rsiD.getString("subject");
        String obj = rsiD.getString("object");
        int id_request = rsiD.getInt("id_request");
        data[i][0] = nickname;
        data[i][1] = sub;
        data[i][2] = obj;
        data[i][3] = id_request + "";
        i++;
      }

      ViewModRequest modRequest = new ViewModRequest(homePage, data, columns, user);// On ajoute le header et le
      // tableau
      // rempli de données
      homePage.remove(1);
      homePage.add(modRequest);
      homePage.repaint();
      homePage.revalidate();
      conn1.disconnectDB();

    }

    catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public ControllerModRequest(ViewHomePage homePage, int id_request, User user)
      throws ClassNotFoundException, SQLException {

    try {
      /* création de la connection de la BDD */
      BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
          "jdbc:mysql://localhost:3306/");
      conn1.setPreparedStatement(strDeleteAlbum);
      conn1.getPreparedStatement().setInt(1, id_request);
      conn1.getPreparedStatement().executeUpdate();
      conn1.disconnectDB();
      JOptionPane.showMessageDialog(null, "Message supprimé", "ATTENTION", 2);
      new ControllerModRequest(homePage, user);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
