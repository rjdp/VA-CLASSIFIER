package deedeveloper.examples.va_classifier.User_Management;

/**
 * Created by Dee Dee on 8/3/2016.
 */
public class User {
    String id, name, email, con_num, username, password;

    public User(String id, String name, String email, String con_num, String username, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.con_num = con_num;
        this.username = username;
        this.password = password;
    }

    public User(String name, String email, String con_num, String username, String password){
        this.name = name;
        this.email = email;
        this.con_num = con_num;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.con_num = "";
        this.username ="";
        this.password ="";
    }
}
