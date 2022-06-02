// package controllers;
// import java.sql.*;
// import java.util.ArrayList;
// import javax.swing.JOptionPane;

// import models.User;
// import views.*;
// import utils.*;

// public class ControllerBuildWall {
//  private final String strQueryListWall = "SELECT id_wall FROM wall order by id_wall ASC;";
//  private final String strQueryAddWall = "INSERT INTO wall (id_member) VALUES  (?);";
//  private final String strQueryDeleteWall = "DELETE FROM wall WHERE id_wall = ?;";
//  private ArrayList<Integer> listWall = new ArrayList<Integer>();
 

//     public ControllerBuildWall(ViewHomePage homePage, User user) throws ClassNotFoundException, SQLException {  
//         ResultSet rsUsers = null;
//         BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
//         "jdbc:mysql://localhost:3306/");

//         try {
//             conn1.setPreparedStatement(strQueryListWall);
//             rsUsers = conn1.getPreparedStatement().executeQuery();
//         } catch (SQLException e1) {
//             e1.printStackTrace();
//           }

//           while(rsUsers.next()){
//             listWall.add(rsUsers.getInt("id_wall"));        
//           }

//           ViewBuildWall wallBuild = new ViewBuildWall(homePage, listWall, id_member);
//           homePage.remove(1);
//           homePage.add(wallBuild);
//           homePage.repaint();
//           homePage.revalidate();
//     }

//     public ControllerBuildWall(int id_member,ViewHomePage homePage) throws ClassNotFoundException, SQLException {
//         BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
//         "jdbc:mysql://localhost:3306/");
       
//         try {
//             conn1.setPreparedStatement(strQueryAddWall);
//             conn1.getPreparedStatement().setInt(1, id_member);
//             conn1.getPreparedStatement().executeUpdate();
//             JOptionPane.showMessageDialog(null,"Le mur a bien été ajouté", "Ajout d'un mur ",  2);
//         } catch (SQLException e1) {
//             e1.printStackTrace();
//           }

//         new ControllerBuildWall(homePage, id_member);
//     }

//    public ControllerBuildWall(int id_wall, int id_member,ViewHomePage homePage ) throws ClassNotFoundException, SQLException {
//        BDDacces conn1 = new BDDacces("com.mysql.cj.jdbc.Driver", "climb_this", "climb", "climb",
//      "jdbc:mysql://localhost:3306/");

//         try {
//          conn1.setPreparedStatement(strQueryDeleteWall);
//          conn1.getPreparedStatement().setInt(1, id_wall);
//          conn1.getPreparedStatement().executeUpdate();
//          JOptionPane.showMessageDialog(null, "Le mur a bien été supprimé", "Suppression mur ", 2);
//         } catch (SQLException e1) {
//             e1.printStackTrace();
//         }
        
//         new ControllerBuildWall(homePage, id_member);
//     }

// }  