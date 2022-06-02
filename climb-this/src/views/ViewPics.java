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

import controllers.ControllerAlbums;
import models.User;
import models.Wall;

public class ViewPics extends JPanel {
  BufferedImage myPicture;

  public ViewPics(ViewHomePage homePage, User user, Wall wall, int laneIndex, int albumIndex) {
    Dimension frameSize = homePage.getSize();
    double widthFrame = frameSize.getWidth();
    double heightFrame = frameSize.getHeight();

    this.setLayout(null);

    int i = 0;
    int sizeListPhoto = wall.getList_lane().get(laneIndex).getList_albums().get(albumIndex).getList_photo().size();

    ArrayList<JButton> listButtons = new ArrayList<JButton>();
    ArrayList<JLabel> listJlabel = new ArrayList<JLabel>();
    ArrayList<JPanel> listJPanel = new ArrayList<JPanel>();

    JPanel panelPhoto = new JPanel();
    this.add(panelPhoto);
    panelPhoto.setBounds(0, ((int) (heightFrame * 0.02) + 30), (int) widthFrame, (int) (heightFrame * 0.8));
    panelPhoto.setLayout(new FlowLayout(FlowLayout.LEFT, (int) (widthFrame * 0.02), (int) (heightFrame * 0.03)));

    while (i < sizeListPhoto) {
      String idAlbum = Integer
          .toString(wall.getList_lane().get(laneIndex).getList_albums().get(albumIndex).getId_album());
      String photo = wall.getList_lane().get(laneIndex).getList_albums().get(albumIndex).getList_photo().get(i);

      try {
        myPicture = ImageIO.read(new File("Images/DepotImage/" + idAlbum + "/" + photo));
      } catch (IOException e2) {
        e2.printStackTrace();
      }

      Image image = myPicture.getScaledInstance((int) (widthFrame * 0.2), (int) (heightFrame * 0.2),
          Image.SCALE_DEFAULT);

      listJPanel.add(new JPanel());
      panelPhoto.add(listJPanel.get(i));
      listJPanel.get(i).setLayout(new BoxLayout(listJPanel.get(i), BoxLayout.Y_AXIS));

      listButtons.add(new JButton(new ImageIcon(image)));
      listButtons.get(i).setBounds(0, 0, (int) (widthFrame * (0.2)), (int) (heightFrame * 0.2));
      listJPanel.get(i).add(listButtons.get(i));

      listJlabel.add(new JLabel());
      listJlabel.get(i).setBounds(0, 0, (int) (widthFrame * (0.2)), 30);
      listJlabel.get(i).setText("Photo " + (i + 1));
      listJPanel.get(i).add(listJlabel.get(i));

      listButtons.get(i).addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          new MainFrame(wall, idAlbum, photo);
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
        new ControllerAlbums(homePage, user, wall, laneIndex);
      }
    });

    JButton deleteAlbum = new JButton("Supprimer cet album");
    deleteAlbum.setBounds(((int) (widthFrame * 0.02) + 150), (int) (heightFrame * 0.02), 200, 30);
    deleteAlbum.setVisible(false);
    this.add(deleteAlbum);

    deleteAlbum.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new ControllerAlbums(homePage, user, wall, laneIndex, albumIndex);
        } catch (SQLException e1) {

          e1.printStackTrace();
        }
      }
    });

    if (user.getUser_role().equals("moderator") || user.getUser_role().equals("admin")) {
      deleteAlbum.setVisible(true);
      this.repaint();
      this.revalidate();
    }
  }
}
