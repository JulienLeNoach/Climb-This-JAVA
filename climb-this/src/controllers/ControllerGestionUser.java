package controllers;

import java.sql.*;
import views.*;
import utils.*;
import java.sql.SQLException;

public class ControllerGestionUser {
  private final String strQueryListUser = "SELECT id_member,nickname, user_role FROM utilisateur ORDER BY user_role;";
  private final String strQueryUpdate = "UPDATE utilisateur SET user_role = ? WHERE id_member = ? ;";
  private final String strQueryDeleteUser = "DELETE FROM utilisateur WHERE id_member = ?;";

  public ControllerGestionUser(ViewHomePage homePage) throws ClassNotFoundException, SQLException {
    ResultSet listUser = null;

    try {
      BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
          "jdbc:mysql://localhost:3306/");
      conn1.setPreparedStatement(strQueryListUser);
      listUser = conn1.getPreparedStatement().executeQuery();

      int cpt = 0;
      while (listUser.next()) {
        cpt++;
      }

      String columns[] = { "<html><b>id member</b></html>", "<html><b>Nickname</b></html>",
          "<html><b>User role</b></html>" };// Creation du header
      String data[][] = new String[cpt][3];// Creation du tableau

      conn1.setPreparedStatement(strQueryListUser);
      listUser = conn1.getPreparedStatement().executeQuery();
      int i = 0;
      while (listUser.next()) {// On recupère les données avec le while et on les intègre dans le tableau
        String id_member = listUser.getString("id_member");
        String nickname = listUser.getString("nickname");
        String user_role = listUser.getString("user_role");
        data[i][0] = id_member;
        data[i][1] = nickname;
        data[i][2] = user_role;
        i++;

      }

      ViewGestionUser gestionUser = new ViewGestionUser(homePage, data, columns);
      homePage.remove(1);
      homePage.add(gestionUser);
      homePage.repaint();
      homePage.revalidate();
      conn1.disconnectDB();

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public ControllerGestionUser(ViewHomePage homePage, int id_member) throws ClassNotFoundException, SQLException {
    try {
      BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
          "jdbc:mysql://localhost:3306/");
      conn1.setPreparedStatement(strQueryDeleteUser);
      conn1.getPreparedStatement().setInt(1, id_member);
      conn1.getPreparedStatement().executeUpdate();

      new ControllerGestionUser(homePage);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public ControllerGestionUser(ViewHomePage homePage, int id_member, String user_role)
      throws ClassNotFoundException, SQLException {
    System.out.println(id_member);
    try {
      BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
          "jdbc:mysql://localhost:3306/");
      conn1.setPreparedStatement(strQueryUpdate);
      conn1.getPreparedStatement().setString(1, user_role);
      conn1.getPreparedStatement().setInt(2, id_member);
      conn1.getPreparedStatement().executeUpdate();

      new ControllerGestionUser(homePage);
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

}
