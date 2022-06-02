package controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import javax.swing.JOptionPane;
import java.sql.*;
import views.*;
import utils.*;

public class ControllerResetPwd {
    private final String strQueryCheckUser = "SELECT user_name, email FROM utilisateur WHERE user_name = ?;";
    private final String strQueryChangepassword = "UPDATE utilisateur SET user_password = ? WHERE user_name = ?;";
    private String user_name;
    private String pwd;
    private String sha1 = "";

    public ControllerResetPwd(MainFrame mainFrame) throws ClassNotFoundException, SQLException {
        ViewResetPwd resetlabel = new ViewResetPwd(mainFrame);
        mainFrame.getContentPane().removeAll();
        mainFrame.getContentPane().add(resetlabel);
        mainFrame.getContentPane().repaint();
        mainFrame.getContentPane().revalidate();

        // ici on créé la vue pour reset password
    }

    public ControllerResetPwd(String user_name, char[] cs, String email, MainFrame mainFrame)
            throws ClassNotFoundException, SQLException {

        this.user_name = user_name;

        this.pwd = String.valueOf(cs);
        ResultSet rsUsers = null;
        Boolean idFound = false;

        if (!(this.user_name.isEmpty() || this.pwd.isEmpty() || this.user_name.isBlank() || this.pwd.isBlank()) &&
                (user_name.matches("^[a-zA-Z0-9]*$") && String.valueOf(cs).matches("^[a-zA-Z0-9]*$"))) {

            try {
                /* création de la connection de la BDD */
                BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
                        "jdbc:mysql://localhost:3306/");

                conn1.setPreparedStatement(strQueryCheckUser);
                conn1.getPreparedStatement().setString(1, user_name);
                rsUsers = conn1.getPreparedStatement().executeQuery();

                try {
                    MessageDigest digest = MessageDigest.getInstance("SHA-1");
                    digest.reset();
                    digest.update(String.valueOf(cs).getBytes("utf8"));
                    sha1 = String.format("%040x", new BigInteger(1, digest.digest()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                while (rsUsers.next()) {

                    if (rsUsers.getString("user_name").equals(user_name) && rsUsers.getString("email").equals(email)) {
                        /* si utilisateur est reconnu, on change le mot de passe */
                        idFound = true;

                        conn1.setPreparedStatement(strQueryChangepassword);
                        conn1.getPreparedStatement().setString(1, sha1);
                        conn1.getPreparedStatement().setString(2, user_name);
                        conn1.getPreparedStatement().executeUpdate();

                        JOptionPane.showMessageDialog(null, "Votre mot de passe a bien été modifié ",
                                "mot de passe modifié", 2);
                    }

                }
                if (idFound == false) {
                    JOptionPane.showMessageDialog(null, "Cet identifiant ou cet e-mail n'existent pas", "Failed", 2);
                }

                idFound = false;
                conn1.disconnectDB();
            }

            catch (SQLException e) {
                e.printStackTrace();

            }

        }
    }

}
