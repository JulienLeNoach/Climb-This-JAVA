// package views;
// import javax.swing.*;
// import controllers.ControllerBuildWall;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.Vector;


// public class ViewBuildWall extends JPanel{

//         public ViewBuildWall (ViewHomePage homePage, ArrayList listWall, int id_member ) {      
//             Dimension frameSize = homePage.getSize();
//             double widthFrame = frameSize.getWidth();
//             double heightFrame = frameSize.getHeight();
//             int width = (int) (widthFrame * 0.5);
//             int height = (int) (heightFrame * 0.5); 

//             this.setBounds((int) (widthFrame * 0.25), (int) (heightFrame * 0.25), width, height);
//             this.setLayout(null);
//             this.setBackground(new Color(200, 200, 200));

//             JButton ajoutMur= new JButton("Ajouter un mur");
//             ajoutMur.setBounds((int) (width * 0.33), (int) (height * 0.70), (int) (width * 0.33), 25);
//             this.add(ajoutMur);

//             JComboBox<Integer> combobox = new JComboBox<Integer>(new Vector<Integer>(listWall));
//             combobox.setBounds(80, 50, 140, 20);
    
//             JButton deleteButton = new JButton("Suppression");
//             deleteButton.setBounds(100, 100, 90, 20);
    
//             this.add(deleteButton);
//             this.add(combobox);
//             this.setLayout(null);
//             this.setSize(350, 250);
//             this.setVisible(true);
  
//             deleteButton.addActionListener(new ActionListener() {
//             @Override
//               public void actionPerformed(ActionEvent e) {
//                   int selectedWall = combobox.getItemAt(combobox.getSelectedIndex());
    
//                   try {
//                     new ControllerBuildWall(selectedWall, id_member, homePage);
//                   } catch (ClassNotFoundException | SQLException e1) {
//                     e1.printStackTrace();
//                   } 
//               }
//           });


//           ajoutMur.addActionListener(new ActionListener() {
//             @Override
//               public void actionPerformed(ActionEvent e) {
//                   try {
//                     new ControllerBuildWall( id_member, homePage);
//                   } catch (ClassNotFoundException | SQLException e1) {
//                     e1.printStackTrace();
//                   }     
//               }
//           });
//         }
// }
