package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import controllers.ControllerConnection;
import controllers.ControllerRegister;

public class ViewRegister extends JPanel {
  public ViewRegister(MainFrame mainFrame) {
    Dimension frameSize = mainFrame.getSize();
    double widthFrame = frameSize.getWidth();
    double heightFrame = frameSize.getHeight();
    int width = (int) (widthFrame * 0.6);
    int height = (int) (heightFrame * 0.8);

    this.setBounds((int) (widthFrame * 0.2), (int) (heightFrame * 0.1), width, height);

    this.setLayout(null);

    this.setBackground(new Color(200, 200, 200));

    JLabel lastNameUser = new JLabel("Nom :");
    lastNameUser.setBounds((int) (width * 0.25), (int) (height * 0.10), (int) (width * 0.25), 25);
    JTextField lastNameUserText = new JTextField();
    lastNameUserText.setBounds((int) (width * 0.5), (int) (height * 0.10), (int) (width * 0.25), 25);

    JLabel firstNameUser = new JLabel("Prénom :");
    firstNameUser.setBounds((int) (width * 0.25), (int) (height * 0.20), (int) (width * 0.25), 25);
    JTextField firstNameUserText = new JTextField();
    firstNameUserText.setBounds((int) (width * 0.5), (int) (height * 0.20), (int) (width * 0.25), 25);

    JLabel nicknameUser = new JLabel("Pseudo :");
    nicknameUser.setBounds((int) (width * 0.25), (int) (height * 0.30), (int) (width * 0.25), 25);
    JTextField nicknameUserText = new JTextField();
    nicknameUserText.setBounds((int) (width * 0.5), (int) (height * 0.30), (int) (width * 0.25), 25);

    JLabel emailUser = new JLabel("Email :");
    emailUser.setBounds((int) (width * 0.25), (int) (height * 0.40), (int) (width * 0.25), 25);
    JTextField emailUserText = new JTextField();
    emailUserText.setBounds((int) (width * 0.5), (int) (height * 0.40), (int) (width * 0.25), 25);

    JLabel userName = new JLabel("Nom de compte :");
    userName.setBounds((int) (width * 0.25), (int) (height * 0.50), (int) (width * 0.25), 25);
    JTextField userNameText = new JTextField();
    userNameText.setBounds((int) (width * 0.5), (int) (height * 0.50), (int) (width * 0.25), 25);

    JLabel userPassword = new JLabel("Mot de passe :");
    userPassword.setBounds((int) (width * 0.25), (int) (height * 0.60), (int) (width * 0.25), 25);
    JPasswordField userPasswordText = new JPasswordField();
    userPasswordText.setBounds((int) (width * 0.5), (int) (height * 0.60), (int) (width * 0.25), 25);

    JLabel userPasswordVerif = new JLabel("Confirmation mdp :");
    userPasswordVerif.setBounds((int) (width * 0.25), (int) (height * 0.70), (int) (width * 0.25), 25);
    JPasswordField userPasswordVerifText = new JPasswordField();
    userPasswordVerifText.setBounds((int) (width * 0.5), (int) (height * 0.70), (int) (width * 0.25), 25);

    JButton btnRegister = new JButton("S'incrire");
    btnRegister.setBounds((int) (width * 0.40), (int) (height * 0.80), (int) (width * 0.20), 25);

    JButton btnConnection = new JButton("Connexion");
    btnConnection.setBounds((int) (width * 0.40), (int) (height * 0.90), (int) (width * 0.20), 25);

    btnRegister.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new ControllerRegister(userNameText.getText(), userPasswordText.getPassword(), lastNameUserText.getText(),
              firstNameUserText.getText(),
              emailUserText.getText(), nicknameUserText.getText(),
              userPasswordVerifText.getPassword(), mainFrame);
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    });

    btnConnection.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new ControllerConnection(mainFrame);
      }
    });

    this.add(lastNameUser);
    this.add(lastNameUserText);

    this.add(firstNameUser);
    this.add(firstNameUserText);

    this.add(nicknameUser);
    this.add(nicknameUserText);

    this.add(emailUser);
    this.add(emailUserText);

    this.add(userName);
    this.add(userNameText);

    this.add(userPassword);
    this.add(userPasswordText);

    this.add(userPasswordVerif);
    this.add(userPasswordVerifText);

    this.add(btnRegister);
    this.add(btnConnection);
  }
}