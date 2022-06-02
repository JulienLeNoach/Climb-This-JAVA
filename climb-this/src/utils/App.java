package utils;

import views.MainFrame;

import views.ViewConnection;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        MainFrame mainFrame = new MainFrame();
        ViewConnection connectionPage = new ViewConnection(mainFrame);
        mainFrame.getContentPane().add(connectionPage);
        mainFrame.getContentPane().repaint();
        mainFrame.getContentPane().revalidate();
    }
}