package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import controllers.*;

public class ViewConnection extends JPanel {
  public ViewConnection(MainFrame mainFrame) {
    Dimension frameSize = mainFrame.getSize();
    double widthFrame = frameSize.getWidth();
    double heightFrame = frameSize.getHeight();
    int width = (int) (widthFrame * 0.5);
    int height = (int) (heightFrame * 0.5);
    this.setBounds((int) (widthFrame * 0.25), (int) (heightFrame * 0.25), width, height);

    this.setLayout(null);

    this.setBackground(new Color(200, 200, 200));

    JLabel id = new JLabel("Identifiant :", JLabel.CENTER);
    id.setBounds((int) (width * 0.33), (int) (height * 0.15), (int) (width * 0.33), 25);
    JTextField idText = new JTextField();
    idText.setBounds((int) (width * 0.33), (int) (height * 0.25), (int) (width * 0.33), 25);
    JLabel pwd = new JLabel("Mot de passe :", JLabel.CENTER);
    pwd.setBounds((int) (width * 0.33), (int) (height * 0.35), (int) (width * 0.33), 25);
    JPasswordField pwdText = new JPasswordField();
    pwdText.setBounds((int) (width * 0.33), (int) (height * 0.45), (int) (width * 0.33), 25);
    JButton btnConn = new JButton("Connexion");
    btnConn.setBounds((int) (width * 0.33), (int) (height * 0.60), (int) (width * 0.33), 25);
    JButton btnresetpwd = new JButton("mot de passe oublié");
    btnresetpwd.setBounds((int) (width * 0.33), (int) (height * 0.70), (int) (width * 0.33), 25);
    JButton btnregister = new JButton("S'inscrire");
    btnregister.setBounds((int) (width * 0.33), (int) (height * 0.80), (int) (width * 0.33), 25);

    btnresetpwd.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new ControllerResetPwd(mainFrame);
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    }); // si on clique sur mot de passe oublié
    // on appelle le controller pour nous renvoyer sur la page d'oubli de mdp

    btnregister.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new ControllerRegister(mainFrame);
        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    }); // si on clique sur mot de passe oublié
    // on appelle le controller pour nous renvoyer sur la page d'oubli de mdp

    btnConn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new ControllerConnection(idText.getText(), pwdText.getPassword(), mainFrame);

        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        } catch (SQLException e1) {
          e1.printStackTrace();
        }

      }

    });

    this.add(btnregister);
    this.add(btnresetpwd);
    this.add(id);
    this.add(idText);
    this.add(pwd);
    this.add(pwdText);
    this.add(btnConn);
  }
}