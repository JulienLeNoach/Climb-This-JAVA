package views;

import javax.imageio.ImageIO;
import javax.swing.*;
import controllers.ControllerAskModerator;
import controllers.ControllerConnection;
import controllers.ControllerRequestAlbum;
import controllers.ControllerAddAlbum;
import controllers.ControllerGestionUser;
import controllers.ControllerHomePage;
import controllers.ControllerModRequest;
import controllers.ControllerProfile;
import controllers.ControllerWall;
import models.User;
import models.Wall;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.*;

import java.sql.SQLException;

public class ViewHomePage extends JPanel {
  BufferedImage myPicture;

  public ViewHomePage(MainFrame mainFrame, User user, Wall wall) {
    Dimension frameSize = mainFrame.getSize();
    double widthFrame = frameSize.getWidth();
    double heightFrame = frameSize.getHeight();
    int width = (int) (widthFrame);
    int height = (int) (heightFrame);
    this.setSize(width, height);

    this.setBackground(new Color(200, 200, 200));

    this.setLayout(new BorderLayout());

    JMenuBar myMenuBar = new JMenuBar();
    this.add(myMenuBar, BorderLayout.NORTH);

    JButton accueil = new JButton("Accueil");
    myMenuBar.add(accueil);
    myMenuBar.add(Box.createRigidArea(new Dimension(5, 0)));

    JButton mur = new JButton("Mur");
    myMenuBar.add(mur);
    myMenuBar.add(Box.createRigidArea(new Dimension(5, 0)));

    JButton albumRequest = new JButton("Faire une demande d'album");
    myMenuBar.add(albumRequest);
    myMenuBar.add(Box.createRigidArea(new Dimension(5, 0)));

    JButton gestionUsers = new JButton("Gestion des utilisateurs");
    myMenuBar.add(gestionUsers);

    gestionUsers.setVisible(false);
    if (user.getUser_role().equals("admin")) {
      myMenuBar.add(Box.createRigidArea(new Dimension(5, 0)));
      gestionUsers.setVisible(true);
      myMenuBar.repaint();
      myMenuBar.revalidate();
    }

    JButton gestionRequests = new JButton("Albums en attente");
    myMenuBar.add(gestionRequests);

    gestionRequests.setVisible(false);
    if (user.getUser_role().equals("moderator") || user.getUser_role().equals("admin")) {
      myMenuBar.add(Box.createRigidArea(new Dimension(5, 0)));
      gestionRequests.setVisible(true);
      myMenuBar.repaint();
      myMenuBar.revalidate();
    }

    JButton askModerator = new JButton("Contactez un modérateur");
    myMenuBar.add(askModerator);
    myMenuBar.add(Box.createRigidArea(new Dimension(5, 0)));
    gestionRequests.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        // faire passer le nom d'utilisateur en paramètr
        try {
          new ControllerAddAlbum(ViewHomePage.this, user, wall);

        } catch (ClassNotFoundException | SQLException e1) {

          e1.printStackTrace();
        }
      }
    });

    JMenu menuFiles = new JMenu("Mon compte");
    myMenuBar.add(menuFiles);
    JMenuItem menuProfile = new JMenuItem("profil");
    JMenuItem menuMailBox = new JMenuItem("boite de reception");
    menuFiles.add(menuMailBox);
    menuMailBox.setVisible(false);
    if (user.getUser_role().equals("moderator") || user.getUser_role().equals("admin")) {
      menuMailBox.setVisible(true);
      myMenuBar.repaint();
      myMenuBar.revalidate();
    }
    JMenuItem menuLogout = new JMenuItem("déconnexion");

    menuFiles.add(menuProfile);
    menuFiles.add(menuMailBox);
    menuFiles.add(menuLogout);
    myMenuBar.add(Box.createHorizontalGlue());
    myMenuBar.add(menuFiles);

    // ceci est le JPanel de contenu de la page d'accueil
    JPanel contentPage = new JPanel();
    contentPage.setLayout(null);
    this.add(contentPage);

    JLabel welcome = new JLabel("Bienvenue à " + user.getClub_member() + ", " + user.getNickname() + "!");
    welcome.setFont(new Font("Verdana", 1, 20));

    welcome.setBounds((int) (width * 0.33), (int) (height * 0.02), (int) (width * 0.33), 30);
    welcome.setHorizontalAlignment(SwingConstants.CENTER);

    contentPage.add(welcome);

    try {
      myPicture = ImageIO.read(new File("Images/homePage/guide2.jpg"));
    } catch (IOException e2) {
      e2.printStackTrace();
    }
    Image image = myPicture.getScaledInstance((int) (width * 0.7), (int) (height
        * 0.8), Image.SCALE_DEFAULT);
    JLabel picLabel = new JLabel(new ImageIcon(image));
    picLabel.setBounds((int) (width * 0.15), (int) (height * 0.1), (int) (widthFrame * 0.7), (int) (heightFrame * 0.8));
    contentPage.add(picLabel);

    menuProfile.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // faire passer le nom d'utilisateur en paramètre

        try {
          new ControllerProfile(ViewHomePage.this, mainFrame, user);
        } catch (ClassNotFoundException | SQLException e1) {

          e1.printStackTrace();
        }
      }
    });

    albumRequest.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // faire passer le nom d'utilisateur en paramètre

        try {
          new ControllerRequestAlbum(ViewHomePage.this, user, wall, mainFrame);
        } catch (ClassNotFoundException | SQLException | IOException e1) {

          e1.printStackTrace();
        }
      }
    });

    mur.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new ControllerWall(ViewHomePage.this, user, wall);
        } catch (ClassNotFoundException | SQLException e1) {
          e1.printStackTrace();
        }
      }
    });

    accueil.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new ControllerHomePage(mainFrame, user, wall);
      }
    });

    gestionUsers.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // faire passer le nom d'utilisateur en paramètre
        try {
          new ControllerGestionUser(ViewHomePage.this);

        } catch (ClassNotFoundException | SQLException e1) {

          e1.printStackTrace();
        }
      }
    });
    askModerator.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // faire passer le nom d'utilisateur en paramètre
        try {
          new ControllerAskModerator(ViewHomePage.this, user, wall, mainFrame);

        } catch (ClassNotFoundException | SQLException | IOException e1) {

          e1.printStackTrace();
        }
      }
    });

    menuMailBox.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new ControllerModRequest(ViewHomePage.this, user);
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    });
    menuLogout.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new ControllerConnection(mainFrame);
      }
    });

  }
}
