package views;

import controllers.ControllerGestionUser;
import controllers.ControllerNewLane;
import models.Wall;
import models.User;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

/*
 * la mainframe sera la fenêtre principale. Elle appellera les constructeurs des
 * jPanels selon l'appel des controlleurs.
 * elle doit donc posséder toutes les méthodes appelant les constructeurs des
 * Jpanel.
 * 
 * Le Jpanel sera donc remplaçable (1 seul Jpanel sur la Jframe par ex) et il
 * changera au fur et a mesure des appels controlleurs
 * ex: on lance l'app, la jframe se construit et construit 1 jpanel connexion.
 * La personne se connecte, le controlleur demande donc au Jframe
 * de construire un autre jpanel (page d'accueil) qui va se superposer sur le
 * jpanl actuel (connexion)
 */

public class MainFrame extends JFrame {
  BufferedImage myPicture;

  public MainFrame() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double widthScreen = screenSize.getWidth();
    double heightScreen = screenSize.getHeight();
    int width = (int) (widthScreen);
    int height = (int) (heightScreen * 0.90);

    this.setSize(width, height);
    this.setDefaultCloseOperation(3);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    this.setVisible(true);
    this.setTitle("Climb This !");
  }

  public MainFrame(ViewHomePage homepage, User user, Wall wall) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double widthScreen = screenSize.getWidth();
    double heightScreen = screenSize.getHeight();
    int width = (int) (widthScreen * 0.50);
    int height = (int) (heightScreen * 0.50);

    this.setSize(width, height);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    this.setVisible(true);
    this.setTitle("Climb This !");

    String[] listColor = { "Jaune", "Vert", "Bleu", "Rouge", "Marron", "Noir" };
    JComboBox<String> listColors = new JComboBox<String>(listColor);
    listColors.setBounds((int) (width * 0.33), (int) (height * 0.1), (int) (width * 0.33), 30);

    JLabel userName = new JLabel("Nom de compte : " + user.getNickname());
    userName.setBounds((int) (width * 0.33), (int) (height * 0.3), (int) (width * 0.33), 30);

    JButton btnAddLane = new JButton("Ajouter le parcours");
    btnAddLane.setBounds((int) (width * 0.33), (int) (height * 0.5), (int) (width * 0.33), 30);

    btnAddLane.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        String selectedcolor = (String) listColors.getItemAt(listColors.getSelectedIndex());
        new ControllerNewLane(MainFrame.this, homepage, user, wall, selectedcolor);
      }
    });

    this.add(listColors);
    this.add(userName);
    this.add(btnAddLane);

  }

  public MainFrame(Wall wall, String idAlbum, String photo) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double widthScreen = screenSize.getWidth();
    double heightScreen = screenSize.getHeight();
    int width = (int) (widthScreen * 0.75);
    int height = (int) (heightScreen * 0.75);

    this.setSize(width, height);
    this.setLocationRelativeTo(null);
    this.setVisible(true);

    try {
      myPicture = ImageIO.read(new File("Images/DepotImage/" + idAlbum + "/" + photo));
    } catch (IOException e2) {
      e2.printStackTrace();
    }

    Image image = myPicture.getScaledInstance(width, height, Image.SCALE_DEFAULT);

    JLabel picLabel = new JLabel(new ImageIcon(image));
    picLabel.setBounds(0, 0, width, height);
    this.add(picLabel);
  }

  // --------------------------------------------------------------------------------------------Constructeur
  // pour Gestion d'utilisateur

  public MainFrame(ViewHomePage homePage, int id_member, String nickname, String user_role) {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    double widthScreen = screenSize.getWidth();
    double heightScreen = screenSize.getHeight();
    int width = (int) (widthScreen * 0.3);
    int height = (int) (heightScreen * 0.3);

    this.setSize(width, height);
    this.setLocationRelativeTo(null);
    this.setLayout(null);
    this.setVisible(true);

    // -----------------------------------------------------------------------------------------------
    // Boutons
    JButton btnRole = new JButton("confirmer");
    btnRole.setBounds((int) (width * 0.7), (int) (height * 0.45), (int) (width * 0.25), 25);
    this.add(btnRole);

    // -----------------------------------------------------------------------------------------------
    // JLabel + JTextField

    JLabel userNameText = new JLabel("Nom de compte :");
    userNameText.setBounds((int) (width * 0.25), (int) (height * 0.20), (int) (width * 0.25), 25);
    this.add(userNameText);
    JLabel userName = new JLabel(nickname);
    userName.setBounds((int) (width * 0.5), (int) (height * 0.20), (int) (width * 0.25), 25);
    this.add(userName);

    JLabel userRoleText = new JLabel("Rôle du compte:");
    userRoleText.setBounds((int) (width * 0.25), (int) (height * 0.30), (int) (width * 0.25), 25);
    this.add(userRoleText);
    JLabel userRole = new JLabel(user_role);
    userRole.setBounds((int) (width * 0.5), (int) (height * 0.30), (int) (width * 0.25), 25);
    this.add(userRole);

    String role[] = { "", "user", "moderator", "admin" };
    JComboBox<String> userRole2 = new JComboBox<String>(role);
    userRole2.setBounds((int) (width * 0.7), (int) (height * 0.30), (int) (width * 0.25), 25);
    this.add(userRole2);

    // -----------------------------------------------------------------------------------------------
    // ActionListener

    btnRole.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String user_role_check;

        if (userRole2.getSelectedItem().toString().equals("")) {
          user_role_check = userRole.getText();
        } else {
          user_role_check = userRole2.getSelectedItem().toString();
          ;
        }

        try {
          new ControllerGestionUser(homePage, id_member, user_role_check);
        } catch (ClassNotFoundException | SQLException e1) {

          e1.printStackTrace();
        }
        MainFrame.this.dispose();
      }
    });

  }
}
