package views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import java.sql.SQLException;

import controllers.ControllerAskModerator;
import models.User;
import models.Wall;

public class ViewAskModerator extends JPanel {
  public ViewAskModerator(ViewHomePage homePage, User user, Wall wall, MainFrame mainframe) throws IOException {
    Dimension frameSize = homePage.getSize();
    double widthFrame = frameSize.getWidth();
    double heightFrame = frameSize.getHeight();
    int width = (int) (widthFrame * 0.6);
    int height = (int) (heightFrame * 0.8);

    this.setBounds((int) (widthFrame * 0.2), (int) (heightFrame * 0.1), width, height);

    this.setLayout(null);

    this.setBackground(new Color(200, 200, 200));

    JLabel user_name = new JLabel("Nom de compte :");
    user_name.setBounds((int) (width * 0.25), (int) (height * 0.15), (int) (width * 0.25), 25);

    JLabel user_nameText = new JLabel(user.getNickname());
    user_nameText.setBounds((int) (width * 0.5), (int) (height * 0.15), (int) (width * 0.25), 25);

    JLabel subject = new JLabel("Sujet de la demande :");
    subject.setBounds((int) (width * 0.25), (int) (height * 0.20), (int) (width * 0.25), 25);

    JTextField subjectText = new JTextField();
    subjectText.setBounds((int) (width * 0.5), (int) (height * 0.20), (int) (width * 0.50), 25);

    JLabel object = new JLabel("Contenu du message :");
    object.setBounds((int) (width * 0.25), (int) (height * 0.30), (int) (width * 0.25), 25);

    JTextArea objectText = new JTextArea();
    objectText.setBounds((int) (width * 0.5), (int) (height * 0.30), (int) (width * 0.80), 90);
    objectText.setLineWrap(true);

    JButton btnContact = new JButton("Envoyez mon message");
    btnContact.setBounds((int) (width * 0.33), (int) (height * 0.90), (int) (width * 0.33), 25);

    btnContact.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          new ControllerAskModerator(
              subjectText.getText(),
              objectText.getText(),
              homePage, user, wall, mainframe);

        } catch (ClassNotFoundException e1) {
          e1.printStackTrace();
        } catch (SQLException e1) {
          e1.printStackTrace();
        } catch (IOException e1) {
          e1.printStackTrace();
        }
      }
    });

    this.add(subject);
    this.add(subjectText);
    this.add(user_name);
    this.add(user_nameText);
    this.add(object);
    this.add(objectText);
    this.add(btnContact);
  }
}