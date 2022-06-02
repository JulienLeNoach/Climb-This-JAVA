package controllers;

import java.sql.*;

import javax.swing.JOptionPane;

import models.Album;
import models.User;
import models.Wall;
import views.*;
import utils.*;

public class ControllerAddAlbum {
  private final String strQueryContact = "SELECT utilisateur.nickname,mailbox.subject,mailbox.object,mailbox.id_album,album.color FROM mailbox JOIN utilisateur ON utilisateur.id_member = mailbox.id_member LEFT JOIN album ON album.id_album = mailbox.id_album WHERE album.is_checked='EnCours'";
  // Ajout de la contraite de suppression en cascade sur les 3 tables
  private final String strDeleteAlbum = "DELETE mailbox,photo,album FROM mailbox INNER JOIN photo ON photo.id_album = mailbox.id_album INNER JOIN album ON mailbox.id_album = album.id_album WHERE mailbox.id_album = ?";
  private final String strAddalbum = "UPDATE album SET is_checked = 'Valide' WHERE id_album = ? ";

  public ControllerAddAlbum(ViewHomePage homePage, User user, Wall wall)
      throws ClassNotFoundException, SQLException {

    try {
      /* création de la connection de la BDD */
      BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
          "jdbc:mysql://localhost:3306/");
      ResultSet rsiD = null;
      conn1.setPreparedStatement(strQueryContact);
      rsiD = conn1.getPreparedStatement().executeQuery();

      String columns[] = { "<html><b>Pseudo</b></html>", "<html><b>Sujet</b></html>", "<html><b>Message</b></html>",
          "<html><b>ID</b></html>", "<html><b>Lane<</b></html>" };// Creation du header
      String data[][] = new String[25][5];// Creation du tableau

      int i = 0;
      while (rsiD.next()) {// On recupère les données avec le while et on les intègre dans le tableau
        String nickname = rsiD.getString("nickname");
        String sub = rsiD.getString("subject");
        String obj = rsiD.getString("object");
        int id_album = rsiD.getInt("id_album");
        String lane = rsiD.getString("color");
        data[i][0] = nickname;
        data[i][1] = sub;
        data[i][2] = obj;
        data[i][3] = id_album + "";
        data[i][4] = lane;
        i++;
      }

      ViewAddAlbum gestion = new ViewAddAlbum(homePage, data, columns, user, wall);
      homePage.remove(1);
      homePage.add(gestion);
      homePage.repaint();
      homePage.revalidate();
      conn1.disconnectDB();

    }

    catch (SQLException e) {
      e.printStackTrace();
    }

  }

  public ControllerAddAlbum(ViewHomePage homePage, Boolean act, int id_album, User user, Wall wall, String color)
      throws ClassNotFoundException, SQLException {
    try {
      /* création de la connection de la BDD */
      BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
          "jdbc:mysql://localhost:3306/");
      if (act == false) {

        conn1.setPreparedStatement(strDeleteAlbum);
        conn1.getPreparedStatement().setInt(1, id_album);
        conn1.getPreparedStatement().executeUpdate();
        conn1.disconnectDB();
        JOptionPane.showMessageDialog(null, "La demande à été supprimée", "ATTENTION", 2);
        new ControllerAddAlbum(homePage, user, wall);
        conn1.disconnectDB();

      } else if (act == true) {

        conn1.setPreparedStatement(strAddalbum);
        conn1.getPreparedStatement().setInt(1, id_album);
        conn1.getPreparedStatement().executeUpdate();
        ResultSet rsPics = null;

        String strQueryListPics = "SELECT id_lane , photo FROM album JOIN photo ON album.id_album = photo.id_album WHERE album.id_album = ?;";
        conn1.setPreparedStatement(strQueryListPics);
        conn1.getPreparedStatement().setInt(1, id_album);
        rsPics = conn1.getPreparedStatement().executeQuery();
        Album album = new Album(id_album);
        int id_lane = 0;
        while (rsPics.next()) {
          album.addPics(rsPics.getString("photo"));
          id_lane = (rsPics.getInt("id_lane"));
        }
        for (int j = 0; j < wall.getList_lane().size(); j++) {
          if (wall.getList_lane().get(j).getid_lane() == id_lane) {
            wall.getList_lane().get(j).addAlbum(album);
          }
        }
        conn1.disconnectDB();
        JOptionPane.showMessageDialog(null, "L'album à bien été ajouté", "ATTENTION", 2);
        new ControllerAddAlbum(homePage, user, wall);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
