package controllers;

import views.*;
import utils.*;
import java.sql.*;
import javax.swing.JOptionPane;

import models.Lane;
import models.User;
import models.Wall;

public class ControllerNewLane {

    private final String strQueryDeleteLane = "DELETE FROM lane WHERE id_lane = ?;";
    private final String strQueryAddLane = "INSERT INTO lane (color, id_wall, id_member) VALUES (?, 1, ?) ;";
    private final String strgetidlane = "SELECT * FROM lane;";

    public ControllerNewLane(ViewHomePage homePage, User user, Wall wall) {

        ViewNewLane viewlane = new ViewNewLane(homePage, user, wall);
        homePage.remove(1);
        homePage.add(viewlane);
        homePage.repaint();
        homePage.revalidate();

    }

    public ControllerNewLane(ViewHomePage homePage, User user, Wall wall, int idlane)
            throws ClassNotFoundException, SQLException {
        try {
            BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
                    "jdbc:mysql://localhost:3306/");

            conn1.setPreparedStatement(strQueryDeleteLane);
            conn1.getPreparedStatement().setInt(1, idlane);
            conn1.getPreparedStatement().executeUpdate();

            int i = 0;

            while (i < wall.getList_lane().size()) {
                if (wall.getList_lane().get(i).getid_lane() == idlane) {
                    wall.getList_lane().remove(i);
                }
                i++;
            }
            new ControllerNewLane(homePage, user, wall);
            conn1.disconnectDB();

            JOptionPane.showMessageDialog(null, "Le parcours a bien été supprimé ",
                    "parcours supprimé", 2);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public ControllerNewLane(MainFrame formnewlane, ViewHomePage homePage, User user, Wall wall, String color) {
        try {
            BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
                    "jdbc:mysql://localhost:3306/");

            conn1.setPreparedStatement(strQueryAddLane);
            conn1.getPreparedStatement().setString(1, color);
            conn1.getPreparedStatement().setInt(2, user.getId_member());
            conn1.getPreparedStatement().executeUpdate();

            ResultSet test = null;

            conn1.setPreparedStatement(strgetidlane);
            test = conn1.getPreparedStatement().executeQuery();

            while (test.next()) {
                if (test.isLast()) {
                    wall.getList_lane().add(new Lane(test.getString("color"), test.getInt("id_wall"),
                            test.getInt("id_lane")));
                }
            }
            new ControllerNewLane(homePage, user, wall);
            conn1.disconnectDB();

        } catch (Exception e) {
            e.printStackTrace();
        }
        formnewlane.dispose();
        JOptionPane.showMessageDialog(null, "Le parcours a été ajouté ",
                "parcours ajouté!", 2);

    }
}
