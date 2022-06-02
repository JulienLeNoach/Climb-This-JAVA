package views;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;
import controllers.ControllerRequestAlbum;
import models.User;
import models.Wall;

public class ViewRequestAlbum extends JPanel {
  boolean checkupload = false;

  public ViewRequestAlbum(ViewHomePage homePage, User user, Wall wall, MainFrame mainframe) throws IOException {
    Dimension frameSize = mainframe.getSize();
    double widthFrame = frameSize.getWidth();
    double heightFrame = frameSize.getHeight();
    int width = (int) (widthFrame * 0.6);
    int height = (int) (heightFrame * 0.8);
    this.setBounds((int) (widthFrame * 0.2), (int) (heightFrame * 0.1), width, height);

    this.setLayout(null);

    this.setBackground(new Color(200, 200, 200));

    JLabel subject = new JLabel("Sujet de la demande :");
    subject.setBounds((int) (width * 0.25), (int) (height * 0.15), (int) (width * 0.25), 25);

    JTextField subjectText = new JTextField();
    subjectText.setBounds((int) (width * 0.5), (int) (height * 0.15), (int) (width * 0.25), 25);

    JLabel user_name = new JLabel("Nom de compte :");
    user_name.setBounds((int) (width * 0.25), (int) (height * 0.20), (int) (width * 0.25), 25);

    JLabel user_nameText = new JLabel(user.getNickname());
    user_nameText.setBounds((int) (width * 0.5), (int) (height * 0.20), (int) (width * 0.25), 25);

    JLabel idWall = new JLabel("Choisissez un mur:");
    idWall.setBounds((int) (width * 0.25), (int) (height * 0.05), (int) (width * 0.25), 25);

    ArrayList<Integer> listmur = new ArrayList<Integer>();

    listmur.add(1);

    JComboBox<Integer> listemur = new JComboBox<Integer>(new Vector<Integer>(listmur));
    listemur.setBounds((int) (width * 0.5), (int) (height * 0.05), (int) (width * 0.25), 25);

    JLabel idLane = new JLabel("Choisissez une lane :");
    idLane.setBounds((int) (width * 0.25), (int) (height * 0.10), (int) (width * 0.25), 25);

    ArrayList<String> listlane = new ArrayList<String>();
    ArrayList<Integer> listeLaneIndex = new ArrayList<Integer>();
    int j = 0;

    while (j < wall.getList_lane().size()) {
      listlane.add(wall.getList_lane().get(j).getColor());
      listeLaneIndex.add(wall.getList_lane().get(j).getid_lane());
      j++;
    }

    JComboBox<String> listelane = new JComboBox<String>(new Vector<String>(listlane));
    listelane.setBounds((int) (width * 0.5), (int) (height * 0.10), (int) (width * 0.25), 25);

    JLabel object = new JLabel("Contenu du message :");
    object.setBounds((int) (width * 0.25), (int) (height * 0.30), (int) (width * 0.25), 25);

    JTextArea objectText = new JTextArea();
    objectText.setBounds((int) (width * 0.5), (int) (height * 0.30), (int) (width * 0.25), 90);
    objectText.setLineWrap(true);
    JButton btnContact = new JButton("Envoyez mon message");
    btnContact.setBounds((int) (width * 0.33), (int) (height * 0.90), (int) (width * 0.33), 25);

    JButton btnUploadFiles = new JButton("Envoyez mon image");
    btnUploadFiles.setBounds((int) (width * 0.33), (int) (height * 0.80), (int) (width * 0.33), 25);

    btnUploadFiles.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();// bouton explorateur windows
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png"));
        chooser.setMultiSelectionEnabled(true);// autorisation du choix de fichier multiple
        chooser.setAcceptAllFileFilterUsed(false);// affiche uniquement le filtre de fichier choisi
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          File[] openFiles = chooser.getSelectedFiles();// Range le chemin d'accès des fichiers séléctionnés dans un
                                                        // File

          for (int i = 0; i < openFiles.length; i++) {

            String uploadPath = System.getProperty("user.dir") + "/Images/DepotImage";// Chemin d'accès
            // destination
            Path newPath = Paths.get(uploadPath, openFiles[i].getName());// Ajout du noms des fichiers séléctionnés au
            // chemin d'accès destination
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd_MM_yyyy-HH-mm-SS");
            String formattedDate = myDateObj.format(myFormatObj);
            try {
              Files.copy(openFiles[i].toPath(),
                  newPath.resolveSibling(user.getNickname() + (i + 1) + "_" + formattedDate + "_" + ".jpg"));
              checkupload = true;
            } catch (IOException e1) {
              e1.printStackTrace();
            }

          }
          JOptionPane.showMessageDialog(null, "Vos photos on bien été ajoutés", "ATTENTION", 2);
        }
      }
    });

    btnContact.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new ControllerRequestAlbum(listemur.getSelectedItem().toString(),
              listelane.getSelectedItem().toString(),
              subjectText.getText(),
              objectText.getText(),
              homePage, user, wall, checkupload, mainframe);
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    });

    this.add(subject);
    this.add(subjectText);
    this.add(user_name);
    this.add(user_nameText);
    this.add(idWall);
    this.add(listemur);
    this.add(idLane);
    this.add(listelane);
    this.add(object);
    this.add(objectText);
    this.add(btnContact);
    this.add(btnUploadFiles);
  }
}