package controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import javax.swing.JOptionPane;

import models.Admin;
import models.Album;
import models.Lane;
import models.Moderator;
import models.User;
import models.Wall;

import java.sql.*;
import java.util.*;

import views.*;
import utils.*;

public class ControllerConnection {

    private final String strQueryCheckConnect = "SELECT * FROM utilisateur WHERE user_name = ?;";
    private final String strQueryListLanes = "SELECT * FROM lane WHERE id_wall = 1;";

    private String user_name;
    private String pwd;

    public ControllerConnection(MainFrame mainFrame) {
        ViewConnection connectionPage = new ViewConnection(mainFrame);
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(connectionPage);
        mainFrame.getContentPane().repaint();
        mainFrame.getContentPane().revalidate();
    }

    public ControllerConnection(String user_name, char[] cs, MainFrame mainFrame)
            throws ClassNotFoundException, SQLException {
        this.user_name = user_name;
        this.pwd = String.valueOf(cs);

        String sha1 = "";
        String user_role;
        String lastname;
        String firstname;
        String email;
        String nickname;
        String club_member;
        String pwdUser;
        int id_member;
        int idlane;

        if (!(this.user_name.isEmpty() || this.pwd.isEmpty() || this.user_name.isBlank() || this.pwd.isBlank())
                && (user_name.matches("^[a-zA-Z0-9]*$") && String.valueOf(cs).matches("^[a-zA-Z0-9]*$"))) {

            ResultSet rsUsers = null;
            ResultSet rsLane = null;
            ResultSet rsAlbum = null;
            ResultSet rsPics = null;
            Boolean idFound = false;

            try {
                /* création de la connection de la BDD */
                BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
                        "jdbc:mysql://localhost:3306/");
                // jdbc:mysql://lycee.yves-thepot.pro.dns-orange.fr:20374/

                conn1.setPreparedStatement(strQueryCheckConnect);
                conn1.getPreparedStatement().setString(1, user_name);
                rsUsers = conn1.getPreparedStatement().executeQuery();

                while (rsUsers.next()) {
                    idFound = true;

                    if (rsUsers.getString("user_name").equals(user_name)) {
                        /* création de la vue Connection */
                        /* si utilisateur est reconnu, vérification de la concordance du mot de passe */
                        try {
                            MessageDigest digest = MessageDigest.getInstance("SHA-1");
                            digest.reset();
                            digest.update((String.valueOf(cs)).getBytes("utf8"));
                            sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (rsUsers.getString("user_password").equals(sha1)) {

                            // ici on va instancier le mur puis les lanes et les placer dans un arraylist
                            pwdUser = rsUsers.getString("user_password");
                            id_member = rsUsers.getInt("id_member");
                            user_role = rsUsers.getString("user_role");
                            user_name = rsUsers.getString("user_name");
                            lastname = rsUsers.getString("last_name");
                            firstname = rsUsers.getString("first_name");
                            email = rsUsers.getString("email");
                            nickname = rsUsers.getString("nickname");
                            club_member = rsUsers.getString("club_member");

                            ArrayList<Lane> listLane = new ArrayList<Lane>();
                            conn1.setPreparedStatement(strQueryListLanes);
                            rsLane = conn1.getPreparedStatement().executeQuery();
                            int i = 0;

                            while (rsLane.next()) {
                                int j = 0;

                                idlane = rsLane.getInt("id_lane");

                                listLane.add(new Lane(rsLane.getString("color"), rsLane.getInt("id_wall"),
                                        rsLane.getInt("id_lane")));

                                String strQueryListAlbum = "SELECT * FROM album WHERE id_lane =" + idlane + ";";
                                conn1.setPreparedStatement(strQueryListAlbum);
                                rsAlbum = conn1.getPreparedStatement().executeQuery();

                                while (rsAlbum.next()) {
                                    int idAlbum = rsAlbum.getInt("id_album");
                                    listLane.get(i).addAlbum(new Album(idAlbum));
                                    String strQueryListPics = "SELECT * FROM photo WHERE id_album =" + idAlbum;

                                    conn1.setPreparedStatement(strQueryListPics);
                                    rsPics = conn1.getPreparedStatement().executeQuery();

                                    while (rsPics.next()) {
                                        listLane.get(i).getList_albums().get(j).addPics(rsPics.getString("photo"));
                                    }
                                    j++;

                                    rsPics = null;
                                }

                                rsAlbum = null;
                                i++;
                            }

                            Wall wall = new Wall(listLane, 1);

                            if (user_role.equals("user")) {
                                User user = new User(id_member, user_name, user_role, lastname, firstname, email,
                                        nickname, club_member, pwdUser);

                                ViewHomePage homePage = new ViewHomePage(mainFrame, user, wall); // on créé la
                                // nouvelle

                                mainFrame.getContentPane().removeAll();
                                mainFrame.getContentPane().add(homePage);
                                mainFrame.getContentPane().repaint();
                                mainFrame.getContentPane().revalidate();
                            }

                            if (user_role.equals("moderator")) {
                                Moderator moderator = new Moderator(id_member, user_name, user_role, lastname,
                                        firstname, email,
                                        nickname, club_member, pwdUser);
                                ViewHomePage homePage = new ViewHomePage(mainFrame, moderator, wall); // on créé la
                                // nouvelle

                                mainFrame.getContentPane().removeAll();
                                mainFrame.getContentPane().add(homePage);
                                mainFrame.getContentPane().repaint();
                                mainFrame.getContentPane().revalidate();
                            }

                            if (user_role.equals("admin")) {
                                Admin admin = new Admin(id_member, user_name, user_role, lastname, firstname, email,
                                        nickname, club_member, pwdUser);

                                ViewHomePage homePage = new ViewHomePage(mainFrame, admin, wall); // on créé la
                                // nouvelle
                                mainFrame.getContentPane().removeAll();
                                mainFrame.getContentPane().add(homePage);
                                mainFrame.getContentPane().repaint();
                                mainFrame.getContentPane().revalidate();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
                    }
                }

                if (idFound == false) {
                    JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
                }

                conn1.disconnectDB();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect Username Or Password", "Login Failed", 2);
        }
    }
}