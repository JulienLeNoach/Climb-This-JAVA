package views;

import javax.swing.*;

import controllers.ControllerModRequest;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import models.*;

public class ViewModRequest extends JPanel {

  public ViewModRequest(ViewHomePage homePage, String[][] data, String[] columns, User user) {
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
    table.setRowHeight(50);

    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds((int) (width * 0.1), (int) (height * 0.30), (int) (width * 1.5), 300);

    JButton btnDel = new JButton("Supprimer la demande");
    btnDel.setBounds((int) (width * 0.9), (int) (height * 1), (int) (width * 0.33), 25);

    btnDel.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        int ligne = table.getSelectedRow();
        int id_request = Integer.parseInt(table.getValueAt(ligne, 3).toString());
        try {
          new ControllerModRequest(homePage, id_request, user);
        } catch (ClassNotFoundException | SQLException e1) {

          e1.printStackTrace();
        }
      }
    });

    this.add(scrollPane);
    this.add(btnDel);
  }
}
