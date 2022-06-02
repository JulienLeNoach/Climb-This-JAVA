package controllers;

import java.sql.SQLException;
import javax.swing.JOptionPane;

import models.User;
import models.Wall;
import utils.BDDacces;
import views.ViewAlbums;
import views.ViewHomePage;

public class ControllerAlbums {
  public ControllerAlbums(ViewHomePage homePage, User user, Wall wall, int laneIndex) {
    ViewAlbums albumPage = new ViewAlbums(homePage, user, wall, laneIndex);
    homePage.remove(1);
    homePage.add(albumPage);
    homePage.repaint();
    homePage.revalidate();
  }

  public ControllerAlbums(ViewHomePage homePage, User user, Wall wall, int laneIndex, int albumIndex)
      throws SQLException {

    final String strQuerydeleteAlbum = "DELETE FROM album where id_album= ?;";
    int id_album = wall.getList_lane().get(laneIndex).getList_albums().get(albumIndex).getId_album();

    BDDacces conn1;
    try {
      conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
          "jdbc:mysql://localhost:3306/");

      conn1.setPreparedStatement(strQuerydeleteAlbum);
      conn1.getPreparedStatement().setInt(1, id_album);
      conn1.getPreparedStatement().executeUpdate();

    } catch (Exception e) {

    }
    wall.getList_lane().get(laneIndex).delte_Album(wall.getList_lane().get(laneIndex).getList_albums().get(albumIndex));

    new ControllerAlbums(homePage, user, wall, laneIndex);

    JOptionPane.showMessageDialog(null, "L'Album a été supprimé ", "album supprimé!", 2);

    // String albumPath = System.getProperty("user.dir") + "/Images/DepotImage/" + id_album;

    // File albumFile = new File(albumPath);

    // albumFile.delete();
  }
}