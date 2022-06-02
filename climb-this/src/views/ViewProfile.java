package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import controllers.ControllerConnection;
import controllers.ControllerProfile;
import models.User;



public class ViewProfile extends JPanel {

    public ViewProfile(ViewHomePage homePage, MainFrame mainFrame ,User user) {

        Dimension frameSize = homePage.getSize();
        double widthFrame = frameSize.getWidth();
        double heightFrame = frameSize.getHeight();
        int width = (int) (widthFrame * 0.6);
        int height = (int) (heightFrame * 0.8);

        this.setBounds((int) (widthFrame * 0.2), (int) (heightFrame * 0.1), width, height);
        this.setLayout(null);
        this.setBackground(new Color(200, 200, 200));

        /*----------------------------------------------------------------- 
                                                                            Création d'ArrayList et remplissage de tableaux afin de
                                                                            créer des JTextfield et Jlabel grâce à une boucle.
         -----------------------------------------------------------------*/
        ArrayList<JLabel> listJlabel1 = new ArrayList<JLabel>();
        ArrayList<JLabel> listJlabel2 = new ArrayList<JLabel>();
        ArrayList<JTextField> listJtextfield = new ArrayList<JTextField>();

        listJlabel1.add(new JLabel("Nom"));
        listJlabel1.add(new JLabel("Prénom"));
        listJlabel1.add(new JLabel("Pseudo"));
        listJlabel1.add(new JLabel("Email"));
        listJlabel1.add(new JLabel("Nom de compte"));
        listJlabel1.add(new JLabel("Nom du club"));

        listJlabel2.add(new JLabel(user.getName()));
        listJlabel2.add(new JLabel(user.getFirst_name()));
        listJlabel2.add(new JLabel(user.getNickname()));
        listJlabel2.add(new JLabel(user.getEmail()));
        listJlabel2.add(new JLabel(user.getAccount_name()));
        listJlabel2.add(new JLabel(user.getClub_member()));
        
           /*----------------------------------------------------------------- 
                                                                               Ici la boucle va créer des JLabel et JTextfield les ajouter
                                                                               à la page. Les JTextfield sont invisibles lors de leur création.
                                                                               La variable j permet de placer les items sur la hauteur. 
           -----------------------------------------------------------------*/

        int i = 0;
        float j = 0;
        
        while (i < 6) {
          listJtextfield.add(new JTextField());

          listJlabel1.get(i).setBounds((int) (width * 0.25), (int) (height * (0.10 + j)), (int) (width * 0.25), 25);
          listJlabel2.get(i).setBounds((int) (width * 0.5), (int) (height * (0.10 + j)), (int) (width * 0.25), 25);
          listJtextfield.get(i).setBounds((int) (width * 0.7), (int) (height * (0.10 + j)), (int) (width * 0.25), 25);

          this.add(listJlabel1.get(i));
          this.add(listJlabel2.get(i));
          this.add(listJtextfield.get(i));
          listJtextfield.get(i).setVisible(false);
           
          i++;
          j += 0.10;
        } 
           /*----------------------------------------------------------------- 
                                                                               Création de boutons pour afficher les Jtexfield avec "afficher_modification".
                                                                               
                                                                               Supression du compte avec le bouton "supression".
                                                                               
                                                                               Confirmer les modifications suite au remplissage des JTextfield 
                                                                               avec le bouton "modifier"
           -----------------------------------------------------------------*/



        JButton afficher_modification = new JButton("Modifier");
        afficher_modification.setBounds((int) (width * 0.3), (int) (height * 0.70), (int) (width * 0.25), 25);
        this.add(afficher_modification);

        JButton supression = new JButton("Supprimer le compte");
        supression.setBounds((int) (width * 0.3), (int) (height * 0.80), (int) (width * 0.25), 25);
        this.add(supression);  

        JButton modifier = new JButton("Confirmer");
        modifier.setBounds((int) (width * 0.7), (int) (height * 0.70), (int) (width * 0.25), 25);
        this.add(modifier);
        modifier.setVisible(false);  

          /*----------------------------------------------------------------- 
                                                                               Afficher_modification : Une boucle parcours l'arrayList "listJtextfield"
                                                                               afin de mettre chaque élément en visible (Invisible lors de la création des
                                                                               JTextfield).
           -----------------------------------------------------------------*/

        afficher_modification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              int i = 0;
                while(i < 6){
                  listJtextfield.get(i).setVisible(true);
                  i++;
                }

              listJtextfield.get(5).setVisible(false);  
              modifier.setVisible(true);  
              homePage.repaint();
              homePage.revalidate();
            }
          });

          /*----------------------------------------------------------------- 
                                                                               Supprime le compte et ramène l'utilisateur à la page de connection 
                                                                               en appelant le ControllerConnection.
          -----------------------------------------------------------------*/
          supression.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
          
             int user_delete = user.getId_member();
              try {
                new ControllerProfile(user_delete);
                new ControllerConnection(mainFrame);
              } catch (ClassNotFoundException | SQLException e1) {
      
                e1.printStackTrace();
              }
            }
          });

           /*----------------------------------------------------------------- 
                                                                               Lorsqu'on appuie sur le bouton modifier si les JTextfield ne sont pas remplis
                                                                               alors on récupère le texte de l'élément de listJlabel2 que l'on associe à 
                                                                               la variable qui lui correspond. Exemple : user.getNickname() est associé à 
                                                                               nickname_list. Dans le cas où le JTextfield est rempli on prend son 
                                                                               contenu que l'on associe à une variable qui lui correspond.

                                                                               A chaque fois que l'on clique sur modifier on met à jour les informations sur la base de données.
           -----------------------------------------------------------------*/

          modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<JTextField> updateList = new ArrayList<JTextField>();
    
                int i = 0;
               while(i < 5){
                    if( listJtextfield.get(i).getText().isEmpty()){
                     updateList.add(new JTextField(listJlabel2.get(i).getText()));
                    }
                    else{
                      updateList.add(new JTextField(listJtextfield.get(i).getText()));
                    }
                  i++;
                }
                String last_name_list = updateList.get(0).getText();
                String first_name_list = updateList.get(1).getText();
                String nickname_list = updateList.get(2).getText();
                String email_list = updateList.get(3).getText();
                String user_name_list = updateList.get(4).getText();
                

              try {
                if( !(last_name_list.isBlank() && first_name_list.isBlank() && nickname_list.isBlank() && email_list.isBlank() && user_name_list.isBlank())  && (email_list.matches("^(.+)@(.+)$") && last_name_list.matches("^[a-zA-Z0-9]*$") && first_name_list.matches("^[a-zA-Z0-9]*$") && nickname_list.matches("^[a-zA-Z0-9]*$") && user_name_list.matches("^[a-zA-Z0-9]*$") ) ){
            
                new ControllerProfile(homePage,mainFrame ,user, last_name_list, first_name_list, nickname_list, user_name_list, email_list);
                    }
                else{
                    JOptionPane.showMessageDialog(null, "Veuillez bien remplir les champs :\n (Ne pas mettre d'espaces, de chiffres et ou de caractères speciaux)", "ATTENTION", 2);
                }

              } catch (ClassNotFoundException | SQLException e1) {
                e1.printStackTrace();
              }
            }
          });        
        }        
}