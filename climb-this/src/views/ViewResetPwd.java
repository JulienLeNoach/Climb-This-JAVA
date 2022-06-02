package views;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import controllers.*;
import java.awt.*;

public class ViewResetPwd extends JPanel {

  public ViewResetPwd(MainFrame mainFrame) {
    Dimension frameSize = mainFrame.getSize();
    double widthFrame = frameSize.getWidth();
    double heightFrame = frameSize.getHeight();
    int width = (int) (widthFrame * 0.5);
    int height = (int) (heightFrame * 0.5);

    this.setBounds((int) (widthFrame * 0.25), (int) (heightFrame * 0.25), width, height);

    this.setLayout(null);

    this.setBackground(new Color(200, 200, 200));

    JLabel id = new JLabel("Identifiant :");
    id.setBounds((int) (width * 0.33), (int) (height * 0.10), (int) (width * 0.33), 25);
    JTextField idText = new JTextField();
    idText.setBounds((int) (width * 0.33), (int) (height * 0.20), (int) (width * 0.33), 25);

    JLabel email = new JLabel("Email :");
    email.setBounds((int) (width * 0.33), (int) (height * 0.30), (int) (width * 0.33), 25);
    JTextField emailText = new JTextField();
    emailText.setBounds((int) (width * 0.33), (int) (height * 0.40), (int) (width * 0.33), 25);

    JLabel pwd = new JLabel("Nouveau mot de passe :");
    pwd.setBounds((int) (width * 0.33), (int) (height * 0.50), (int) (width * 0.33), 25);
    JPasswordField pwdText = new JPasswordField();
    pwdText.setBounds((int) (width * 0.33), (int) (height * 0.60), (int) (width * 0.33), 25);

    JButton btnReset = new JButton("Modifier mot de passe");
    btnReset.setBounds((int) (width * 0.33), (int) (height * 0.70), (int) (width * 0.33), 25);

    JButton btnConnection = new JButton("Connexion");
    btnConnection.setBounds((int) (width * 0.33), (int) (height * 0.90), (int) (width * 0.33), 25);

    btnReset.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new ControllerResetPwd(idText.getText(), pwdText.getPassword(), emailText.getText(), mainFrame);

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

    this.add(email);
    this.add(emailText);
    this.add(id);
    this.add(idText);
    this.add(pwd);
    this.add(pwdText);
    this.add(btnReset);
    this.add(btnConnection);
  }

}
