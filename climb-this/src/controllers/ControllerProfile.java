package controllers;

import java.sql.*;

import models.User;
import utils.BDDacces;
import views.*;

public class ControllerProfile {
    private final String strQueryProfile = "UPDATE utilisateur SET user_name = ?, first_name = ?, last_name = ?, email = ?, nickname = ? WHERE id_member = ? ;";
    private final String strQueryDelete = "DELETE FROM utilisateur WHERE id_member = ?;";
    private String id_member;

    public ControllerProfile(ViewHomePage homePage, MainFrame mainFrame, User user)
            throws ClassNotFoundException, SQLException {

        ViewProfile profile = new ViewProfile(homePage, mainFrame, user);

        homePage.remove(1);
        homePage.add(profile);
        homePage.repaint();
        homePage.revalidate();
    }

    public ControllerProfile(ViewHomePage homePage, MainFrame mainFrame, User user, String last_name, String first_name,
            String nickname, String user_name, String email)
            throws ClassNotFoundException, SQLException {

        id_member = Integer.toString(user.getId_member());

        try {
            BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
                    "jdbc:mysql://localhost:3306/");

            conn1.setPreparedStatement(strQueryProfile);
            conn1.getPreparedStatement().setString(1, user_name);
            conn1.getPreparedStatement().setString(2, first_name);
            conn1.getPreparedStatement().setString(3, last_name);
            conn1.getPreparedStatement().setString(4, email);
            conn1.getPreparedStatement().setString(5, nickname);
            conn1.getPreparedStatement().setString(6, id_member);
            conn1.getPreparedStatement().executeUpdate();

            conn1.disconnectDB();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

        user.setAccount_name(user_name);
        user.setFirst_name(first_name);
        user.setName(last_name);
        user.setEmail(email);
        user.setNickname(nickname);

        new ControllerProfile(homePage, mainFrame, user);
    }

    public ControllerProfile(int user_delete) throws ClassNotFoundException, SQLException {

        try {
            BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
                    "jdbc:mysql://localhost:3306/");

            conn1.setPreparedStatement(strQueryDelete);
            conn1.getPreparedStatement().setInt(1, user_delete);
            conn1.getPreparedStatement().executeUpdate();

            conn1.disconnectDB();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

}