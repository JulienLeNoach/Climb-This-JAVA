package views;

import javax.swing.*;

import controllers.ControllerAddAlbum;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import models.*;

public class ViewAddAlbum extends JPanel {

  public ViewAddAlbum(ViewHomePage homePage, String[][] data, String[] columns, User user, Wall wall) {
    Dimension frameSize = homePage.getSize();
    double widthFrame = frameSize.getWidth();
    double heightFrame = frameSize.getHeight();
    int width = (int) (widthFrame * 0.6);
    int height = (int) (heightFrame * 0.8);

    this.setBounds((int) (widthFrame * 0.2), (int) (heightFrame * 0.1), width, height);

    this.setLayout(null);

    this.setBackground(new Color(200, 200, 200));

    JTable table = new JTable(data, columns);
    table.setBounds((int) (width * 0.3), (int) (height * 0.70), (int) (width * 0.85), 85);
    table.setRowHeight(30);

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds((int) (width * 0.1), (int) (height * 0.30), (int) (width * 1.5), 300);

    JButton btnAdd = new JButton("Accepter la demande");
    btnAdd.setBounds((int) (width * 0.5), (int) (height * 1), (int) (width * 0.33), 25);

    JButton btnDel = new JButton("Refuser la demande");
    btnDel.setBounds((int) (width * 0.9), (int) (height * 1), (int) (width * 0.33), 25);

    btnDel.addActionListener(new ActionListener() {
      Boolean idB = false;

      @Override
      public void actionPerformed(ActionEvent e) {
        int ligne = table.getSelectedRow();
        int id_mail = Integer.parseInt(table.getValueAt(ligne, 3).toString());
        String color = table.getValueAt(ligne, 3).toString();
        try {
          new ControllerAddAlbum(homePage, idB, id_mail, user, wall, color);
        } catch (ClassNotFoundException | SQLException e1) {

          e1.printStackTrace();
        }
      }
    });
    btnAdd.addActionListener(new ActionListener() {
      Boolean idB = true;

      @Override
      public void actionPerformed(ActionEvent e) {
        int ligne = table.getSelectedRow();
        int id_album = Integer.parseInt(table.getValueAt(ligne, 3).toString());
        String color = table.getValueAt(ligne, 3).toString();
        try {
          new ControllerAddAlbum(homePage, idB, id_album, user, wall, color);
        } catch (ClassNotFoundException | SQLException e1) {

          e1.printStackTrace();
        }
      }
    });
    this.add(scrollPane);
    this.add(btnAdd);
    this.add(btnDel);
  }
}
