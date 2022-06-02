package views;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;

import controllers.ControllerPics;
import controllers.ControllerWall;
import models.User;
import models.Wall;

public class ViewAlbums extends JPanel {
  BufferedImage myPicture;

  public ViewAlbums(ViewHomePage homePage, User user, Wall wall, int laneIndex) {
    Dimension frameSize = homePage.getSize();
    double widthFrame = frameSize.getWidth();
    double heightFrame = frameSize.getHeight();

    this.setLayout(null);

    int i = 0;
    int sizeAlbum = wall.getList_lane().get(laneIndex).getList_albums().size();

    ArrayList<JButton> listButtons = new ArrayList<JButton>();
    ArrayList<JLabel> listJlabel = new ArrayList<JLabel>();
    ArrayList<JPanel> listJPanel = new ArrayList<JPanel>();

    JPanel panelAlbum = new JPanel();
    this.add(panelAlbum);
    panelAlbum.setBounds(0, ((int) (heightFrame * 0.02) + 30), (int) widthFrame, (int) (heightFrame * 0.8));
    panelAlbum.setLayout(new FlowLayout(FlowLayout.LEFT, (int) (widthFrame * 0.02), (int) (heightFrame * 0.03)));

    while (i < sizeAlbum) {
      int albumIndex = i;
      String idAlbum = Integer
          .toString(wall.getList_lane().get(laneIndex).getList_albums().get(albumIndex).getId_album());
      String photo = wall.getList_lane().get(laneIndex).getList_albums().get(albumIndex).getList_photo().get(0);

      try {
        myPicture = ImageIO.read(new File("Images/DepotImage/" + idAlbum + "/" + photo));
      } catch (IOException e2) {
        e2.printStackTrace();
      }

      Image image = myPicture.getScaledInstance((int) (widthFrame * 0.2), (int) (heightFrame * 0.2),
          Image.SCALE_DEFAULT);

      listJPanel.add(new JPanel());
      panelAlbum.add(listJPanel.get(i));
      listJPanel.get(i).setLayout(new BoxLayout(listJPanel.get(i), BoxLayout.Y_AXIS));

      listButtons.add(new JButton(new ImageIcon(image)));
      listButtons.get(i).setBounds(0, 0, (int) (widthFrame * (0.2)), (int) (heightFrame * 0.2));
      listJPanel.get(i).add(listButtons.get(i));

      listJlabel.add(new JLabel());
      listJlabel.get(i).setBounds(0, (int) (heightFrame * 0.3), (int) (widthFrame * 0.2), 30);
      listJPanel.get(i).add(listJlabel.get(i));

      listJlabel.get(i).setText("Album " + (i + 1));

      listButtons.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          new ControllerPics(homePage, user, wall, laneIndex, albumIndex);
        }
      });

      i++;
    }

    JButton backBtn = new JButton("Retour");
    backBtn.setBounds((int) (widthFrame * 0.02), (int) (heightFrame * 0.02), 100, 30);
    this.add(backBtn);

    backBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new ControllerWall(homePage, user, wall);
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    });

  }
}
