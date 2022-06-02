package controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;

import java.util.ArrayList;
import java.util.List;
import views.*;
import javax.swing.JOptionPane;

import models.User;
import models.Wall;
import utils.*;

public class ControllerRequestAlbum {
  private final String strQueryContact = "INSERT INTO mailbox (subject,object,id_member,id_album) VALUES(?,?,?,?);";
  private final String strUpdateAlbum = "INSERT INTO `album` (`id_lane`,`color`, `id_member`,`id_wall`,`is_checked`) VALUES (?,?,?,?,'EnCours');";
  private final String strQueryidAlbum = "SELECT id_album FROM album ORDER BY id_album DESC LIMIT 1";
  private final String strQueryColor = "SELECT id_lane FROM lane WHERE color= ? ";
  private final String strUpdatePhoto = "INSERT INTO `photo` (`photo`,`id_album`,`id_member`) VALUES (?,?,?);";
  private String subject;
  private String object;
  private int id_album;
  private int id_lane;

  public ControllerRequestAlbum(ViewHomePage homePage, User user, Wall wall, MainFrame mainframe)
      throws ClassNotFoundException, SQLException, IOException {

    ViewRequestAlbum contact = new ViewRequestAlbum(homePage, user, wall, mainframe);
    homePage.remove(1);
    homePage.add(contact);
    homePage.repaint();
    homePage.revalidate();

  }

  public ControllerRequestAlbum(String wallSelect, String laneSelect, String subject, String object,
      ViewHomePage homePage,
      User user,
      Wall wall, boolean checkupload, MainFrame mainframe)
      throws ClassNotFoundException, SQLException {
    this.subject = subject;
    this.object = object;

    if (checkupload == true) {
      if (!(this.subject.isEmpty() || this.subject.isBlank()
          || this.object.isEmpty() || this.object.isBlank())
          && (this.subject.matches("^[a-zA-Z0-9 ]*$")
              && this.object.matches("^[a-zA-Z0-9 ]*$"))) {
        try {
          /* création de la connection de la BDD */
          BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this",
              "climb", "climb",
              "jdbc:mysql://localhost:3306/");
          ResultSet rsIdColor = null;
          conn1.setPreparedStatement(strQueryColor);
          conn1.getPreparedStatement().setString(1, laneSelect);
          rsIdColor = conn1.getPreparedStatement().executeQuery();
          while (rsIdColor.next()) {
            id_lane = rsIdColor.getInt("id_lane");

          }
          conn1.setPreparedStatement(strUpdateAlbum);
          conn1.getPreparedStatement().setInt(1, id_lane);
          conn1.getPreparedStatement().setString(2, laneSelect);
          conn1.getPreparedStatement().setInt(3, user.getId_member());
          conn1.getPreparedStatement().setString(4, wallSelect);
          conn1.getPreparedStatement().executeUpdate();

          int i = 0;
          List<String> results = new ArrayList<String>();
          String uploadPath = System.getProperty("user.dir") + "/Images/DepotImage";
          File[] files = new File(uploadPath).listFiles();

          for (File file : files) {
            if (file.isFile()) {
              results.add(file.getName());// Ajoute les noms de fichiers dans la liste,
              String photo = results.get(i);// Permet de lire chaque donnés de la liste dans
              // une variable String
              ResultSet rsiDAlbum = null;
              conn1.setPreparedStatement(strQueryidAlbum);
              rsiDAlbum = conn1.getPreparedStatement().executeQuery();

              while (rsiDAlbum.next()) {
                id_album = rsiDAlbum.getInt("id_album");
              }

              conn1.setPreparedStatement(strUpdatePhoto);
              conn1.getPreparedStatement().setString(1, photo);
              conn1.getPreparedStatement().setInt(2, id_album);
              conn1.getPreparedStatement().setInt(3, user.getId_member());
              conn1.getPreparedStatement().executeUpdate();// Puis insère chaque nom de
              // fichier associé au fomulaire
              // de contact renseigné dans la vue
              Path path = Paths.get(uploadPath + "/" + id_album);
              Files.createDirectories(path);
              String uploadPath2 = System.getProperty("user.dir") + "/Images/DepotImage/" + id_album;
              Path newPath = Paths.get(uploadPath2, file.getName());
              Files.move(file.toPath(), newPath);
              i++;
            }
          }

          conn1.setPreparedStatement(strQueryContact);
          conn1.getPreparedStatement().setString(1, subject);
          conn1.getPreparedStatement().setString(2, object);
          conn1.getPreparedStatement().setInt(3, user.getId_member());
          conn1.getPreparedStatement().setInt(4, id_album);
          conn1.getPreparedStatement().executeUpdate();
          JOptionPane.showMessageDialog(null, "Votre message à bien été envoyé", "ATTENTION", 2);
          new ControllerRequestAlbum(homePage, user, wall, mainframe);
          conn1.disconnectDB();

        } catch (SQLException | IOException e) {
          e.printStackTrace();
        }
      } else {
        JOptionPane.showMessageDialog(null, "Veuillez remplir le formulaire correctement", "ATTENTION", 2);
      }
    } else {
      JOptionPane.showMessageDialog(null, "Veuillez ajoutez vos photos", "ATTENTION", 2);
    }
  }
}
