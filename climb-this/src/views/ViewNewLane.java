package views;

import javax.swing.*;

import controllers.ControllerNewLane;
import models.User;
import models.Wall;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class ViewNewLane extends JPanel {

  private int lane;
  private String color;

  public ViewNewLane(ViewHomePage homePage, User user, Wall wall) {
    Dimension frameSize = homePage.getSize();
    double widthFrame = frameSize.getWidth();
    double heightFrame = frameSize.getHeight();
    int width = (int) (widthFrame * 0.6);
    int height = (int) (heightFrame * 0.8);

    this.setBounds((int) (widthFrame * 0.2), (int) (heightFrame * 0.1), width, height);

    this.setLayout(null);

    this.setBackground(new Color(200, 200, 200));

    String columns[] = { "<html><b>Parcours</b></html>", "<html><b>Couleur</b></html>",
        "<html><b>mur</b></html>"
    };// Creation du header

    String data[][] = new String[wall.getList_lane().size()][3];// Creation du tableau
    int i = 0;
    while (i < wall.getList_lane().size()) {
      color = wall.getList_lane().get(i).getColor();
      lane = wall.getList_lane().get(i).getid_lane();
      int idwall = wall.getId_wall();
      data[i][0] = lane + "";
      data[i][1] = color + "";
      data[i][2] = idwall + "";
      i++;
    }

    JTable table = new JTable(data, columns);
    table.setBounds((int) (width * 0.55), (int) (height * 0.70), (int) (width * 0.85), 85);
    table.setDefaultEditor(Object.class, null);
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds((int) (width * 0.5), (int) (height * 0.30), (int) (width * 0.85), 300);

    JButton deleteLane = new JButton("Supprimer le parcours");
    deleteLane.setBounds((int) (width * 0.2), (int) (height), (int) (width * 0.25), 25);

    deleteLane.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        int ligne = table.getSelectedRow();
        int id_lane = Integer.parseInt(table.getValueAt(ligne, 0).toString());
        try {
          new ControllerNewLane(homePage, user, wall, id_lane);
        } catch (ClassNotFoundException | SQLException e1) {

          e1.printStackTrace();
        }
      }
    });

    JButton addLane = new JButton("Ajouter un parcours");
    addLane.setBounds((int) (width * 0.5), (int) (height), (int) (width * 0.25), 25);
    addLane.addActionListener(new ActionListener() {
      @Override

      public void actionPerformed(ActionEvent e) {
        new MainFrame(homePage, user, wall);
      }
    });
    this.add(scrollPane);
    this.add(deleteLane);
    this.add(addLane);
  }
}
