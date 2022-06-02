package views;

import javax.swing.*;
import controllers.ControllerGestionUser;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ViewGestionUser extends JPanel {

  public ViewGestionUser(ViewHomePage homePage, String[][] data, String[] columns) {
    Dimension frameSize = homePage.getSize();
    double widthFrame = frameSize.getWidth();
    double heightFrame = frameSize.getHeight();
    int width = (int) (widthFrame * 0.55);
    int height = (int) (heightFrame * 0.7);

    this.setBounds((int) (widthFrame * 0.2), (int) (heightFrame * 0.1), width, height);

    this.setLayout(null);

    this.setBackground(new Color(200, 200, 200));

    JTable table = new JTable(data, columns);
    table.setBounds((int) (width * 0.55), (int) (height * 0.70), (int) (width * 0.85), 85);
    table.setRowHeight(30);
    table.setDefaultEditor(Object.class, null);
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setBounds((int) (width * 0.2), (int) (height * 0.30), (int) (width * 1.5), 300);
    JButton btnDel = new JButton("Supprimer le compte");
    btnDel.setBounds((int) (width * 0.5), (int) (height * 1), (int) (width * 0.33), 25);
    JButton btnRole = new JButton("modifier le profil");
    btnRole.setBounds((int) (width * 1), (int) (height * 1), (int) (width * 0.33), 25);

    btnDel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        int ligne = table.getSelectedRow();
        int id_member = Integer.parseInt(table.getValueAt(ligne, 0).toString());

        try {
          new ControllerGestionUser(homePage, id_member);
        } catch (ClassNotFoundException | SQLException e1) {

          e1.printStackTrace();
        }
      }
    });

    btnRole.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        int ligne = table.getSelectedRow();
        int id_member = Integer.parseInt(table.getValueAt(ligne, 0).toString());
        String nickname = table.getValueAt(ligne, 1).toString();
        String user_role = table.getValueAt(ligne, 2).toString();

        new MainFrame(homePage, id_member, nickname, user_role);
      }
    });

    this.add(btnRole);
    this.add(scrollPane);
    this.add(btnDel);

  }

}