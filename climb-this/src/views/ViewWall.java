package views;

import models.User;
import models.Wall;
import controllers.ControllerAlbums;
import controllers.ControllerNewLane;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

import java.io.*;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class ViewWall extends JPanel {
  BufferedImage myPicture;

  public ViewWall(ViewHomePage homePage, User user, Wall wall) {
    Dimension frameSize = homePage.getSize();
    double widthFrame = frameSize.getWidth();
    double heightFrame = frameSize.getHeight();

    this.setBackground(new Color(200, 200, 200));

    this.setLayout(null);

    // yo

    try {
      myPicture = ImageIO.read(new File("Images/murs/mur1.jpg"));
    } catch (IOException e2) {
      e2.printStackTrace();
    }
    Image image = myPicture.getScaledInstance((int) (widthFrame * 0.7), (int) (heightFrame), Image.SCALE_DEFAULT);
    JLabel picLabel = new JLabel(new ImageIcon(image));
    picLabel.setBounds(0, 0, (int) (widthFrame * 0.7), (int) (heightFrame));
    this.add(picLabel);

    int i = 0;

    float j = 0;

    ArrayList<JButton> listButtons = new ArrayList<JButton>();
    ArrayList<JLabel> listJlabel = new ArrayList<JLabel>();

    while (i < wall.getList_lane().size()) {

      listButtons.add(new JButton("Sélectionner"));
      listJlabel.add(new JLabel(wall.getList_lane().get(i).getColor()));

      listButtons.get(i).setBounds((int) (widthFrame * 0.85), (int) (heightFrame * (0.10 + j)),
          (int) (widthFrame * 0.08), 30);
      listJlabel.get(i).setBounds((int) (widthFrame * 0.75), (int) (heightFrame * (0.10 + j)),
          (int) (widthFrame * 0.08), 30);

      this.add(listButtons.get(i));
      this.add(listJlabel.get(i));

      int laneIndex = i;

      listButtons.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          if (wall.getList_lane().get(laneIndex).getList_albums().size() != 0) {
            new ControllerAlbums(homePage, user, wall, laneIndex);
          } else {
            JOptionPane.showMessageDialog(null, "Aucun album pour ce parcours", "ATTENTION", 2);
          }
        }
      });

      i++;
      j += 0.10;
    }

    JButton gestionLane = new JButton("Gérer les voies");
    gestionLane.setVisible(false);
    gestionLane.setBounds((int) (widthFrame * 0.8), (int) (heightFrame * 0.8), (int) (widthFrame * 0.1), 30);

    if (user.getUser_role().equals("admin")) {
      gestionLane.setVisible(true);
      homePage.repaint();
      homePage.revalidate();
    }

    this.add(gestionLane);

    gestionLane.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new ControllerNewLane(homePage, user, wall);
      }
    });
  }
}