package deedeveloper.examples.va_classifier.User_Management;

/**
 * Created by Dee Dee on 3/3/2016.
 */
public class Config {
    //Address of our scripts of the CRUD

    public static final String URL_LOGIN_USER = "http://deedeveloper.net23.net/UserManagement/login.php";
    public static final String URL_REGISTER_USER = "http://deedeveloper.net23.net/UserManagement/register.php";
    public static final String URL_GET_USER = "http://deedeveloper.net23.net/UserManagement/getUser.php?username=";
    public static final String URL_UPDATE_USER = "http://deedeveloper.net23.net/UserManagement/updateUser.php";


    //Keys that will be used to send the request to php scripts
    public static final String KEY_USER_ID = "id";
    public static final String KEY_USER_NAME = "name";
    public static final String KEY_USER_EMAIL = "email";
    public static final String KEY_USER_CON_NUM = "con_num";
    public static final String KEY_USER_USERNAME = "username";
    public static final String KEY_USER_PASSWORD = "password";


    //JSON Tags
    public static final String TAG_JSON_ARRAY="result";
    public static final String TAG_ID = "id";
    public static final String TAG_NAME = "name";
    public static final String TAG_EMAIL = "email";
    public static final String TAG_CON_NUM = "con_num";
    public static final String TAG_USERNAME = "username";
    public static final String TAG_PASSWORD = "password";

    //employee id to pass with intent
    public static final String USER_ID = "user_id";
    public static final String USER_USERNAME = "user_username";
    public static final String USER_EMAIL = "user_email";
    public static final String USER_NAME = "user_name";
    public static final String USER_CON_NUM = "user_con_num";
    public static final String USER_PASSWORD = "user_password";
}
