package deedeveloper.examples.va_classifier.User_Management;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Dee Dee on 8/3/2016.
 */
public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("id",user.id);
        spEditor.putString("name", user.name);
        spEditor.putString("email", user.email);
        spEditor.putString("con_num",user.con_num);
        spEditor.putString("username", user.username);
        spEditor.putString("password", user.password);
        spEditor.commit();
    }

    public User getLoggedInUser(){
        String id = userLocalDatabase.getString("id","");
        String name = userLocalDatabase.getString("name", "");
        String email = userLocalDatabase.getString("email","");
        String con_num = userLocalDatabase.getString("con_num","");
        String username = userLocalDatabase.getString("username","");
        String password = userLocalDatabase.getString("password","");

        User storedUser = new User(id, name, email, con_num, username, password);
        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn",loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if(userLocalDatabase.getBoolean("loggedIn", false)== true){
            return true;
        }else{
            return false;
        }
    }
    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }




}
