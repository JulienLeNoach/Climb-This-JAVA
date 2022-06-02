package models;

public class Admin extends Moderator {
  public Admin(int id_member, String account_name, String user_role, String name, String first_name, String email,
      String pseudo, String club_member, String password) {
    super(id_member, account_name, user_role, name, first_name, email, pseudo, club_member, password);
  }
}
