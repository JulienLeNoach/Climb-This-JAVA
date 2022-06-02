package controllers;

import java.sql.*;
import views.*;
import javax.swing.JOptionPane;
import utils.*;
import java.math.BigInteger;
import java.security.MessageDigest;

public class ControllerRegister {
  private final String strQueryRegister = "INSERT INTO utilisateur (user_name,user_password,first_name,last_name,email,nickname,club_member,user_role) VALUES(?,?,?,?,?,?,'brest','user');";
  private final String strQueryCheckiD = "SELECT user_name,email,nickname FROM utilisateur WHERE user_name = ? OR email = ? OR  nickname = ? ;";
  private String user_name;
  private String password;
  private String hashPw = "";
  private String last_name;
  private String first_name;
  private String email;
  private String nickname;
  private String passwordCheck;

  public ControllerRegister(MainFrame mainFrame) throws ClassNotFoundException, SQLException {
    ViewRegister register = new ViewRegister(mainFrame);
    mainFrame.getContentPane().removeAll();
    mainFrame.getContentPane().add(register);
    mainFrame.getContentPane().repaint();
    mainFrame.getContentPane().revalidate();
  }

  public ControllerRegister(String user_name, char[] cs, String last_name, String first_name, String email,
      String nickname, char[] cs2, MainFrame mainFrame)
      throws ClassNotFoundException, SQLException {
    this.user_name = user_name;
    this.password = String.valueOf(cs);
    this.last_name = last_name;
    this.first_name = first_name;
    this.email = email;
    this.nickname = nickname;
    this.passwordCheck = String.valueOf(cs2);

    if (!(this.user_name.isEmpty() || this.user_name.isBlank() || this.password.isEmpty() || this.password.isBlank()
        || this.last_name.isEmpty() || this.last_name.isBlank() || this.first_name.isEmpty()
        || this.first_name.isBlank()
        || this.email.isEmpty() || this.email.isBlank() || this.nickname.isEmpty() || this.nickname.isBlank())
        && (this.user_name.matches("^[a-zA-Z0-9]*$") && this.password.matches("^[a-zA-Z0-9]*$"))
        && this.email.matches("^(.+)@(.+)$")
        && this.password.equals(this.passwordCheck)) {
      ResultSet rsCheckiD = null;
      try {
        /* création de la connection de la BDD */
        BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
            "jdbc:mysql://localhost:3306/");
        conn1.setPreparedStatement(strQueryCheckiD);
        conn1.getPreparedStatement().setString(1, user_name);
        conn1.getPreparedStatement().setString(2, email);
        conn1.getPreparedStatement().setString(3, nickname);
        rsCheckiD = conn1.getPreparedStatement().executeQuery();
        if (!(rsCheckiD.next())) {
          try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(String.valueOf(cs).getBytes("utf8"));
            hashPw = String.format("%040x", new BigInteger(1, digest.digest()));
          } catch (Exception e) {
            e.printStackTrace();
          }
          conn1.setPreparedStatement(strQueryRegister);
          conn1.getPreparedStatement().setString(1, user_name);
          conn1.getPreparedStatement().setString(2, hashPw);
          conn1.getPreparedStatement().setString(3, last_name);
          conn1.getPreparedStatement().setString(4, first_name);
          conn1.getPreparedStatement().setString(5, email);
          conn1.getPreparedStatement().setString(6, nickname);
          conn1.getPreparedStatement().executeUpdate();
          JOptionPane.showMessageDialog(null, "Le compte à bien été créé", "Inscription réussie", 2);
          new ControllerConnection(mainFrame);
        } else {
          JOptionPane.showMessageDialog(null, "Identifiant ou e-mail déjà utilisé", "ATTENTION", 2);
        }
        conn1.disconnectDB();

      } catch (SQLException e) {
        e.printStackTrace();
      }
    } else {
      JOptionPane.showMessageDialog(null, "Respecte les conditions d'inscriptionsSSsSssssssssS", "ATTENTION", 2);
    }
  }

  public String getUser_name() {
    return this.user_name;
  }

  public void setUser_name(String user_name) {
    this.user_name = user_name;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getLast_name() {
    return this.last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public String getFirst_name() {
    return this.first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNickname() {
    return this.nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getPasswordCheck() {
    return this.passwordCheck;
  }

  public void setPasswordCheck(String passwordCheck) {
    this.passwordCheck = passwordCheck;
  }
}
