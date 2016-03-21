package deedeveloper.examples.va_classifier.User_Management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import deedeveloper.examples.va_classifier.R;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView linkRegister;

    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        linkRegister = (TextView) findViewById(R.id.linkRegister);

        buttonLogin.setOnClickListener(this);
        linkRegister.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    private void login(){
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(username.isEmpty() || password.isEmpty()) {
            Toast.makeText(Login.this, "Please fill in both username and password", Toast.LENGTH_LONG).show();
        }else {
            userLogin(username, password);
        }

       // authenticate(user);




    }

    private void userLogin(final String username, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this,"Please Wait","Logging....",true,true);
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put(Config.KEY_USER_USERNAME,params[0]);
                data.put(Config.KEY_USER_PASSWORD,params[1]);

                RequestHandler rh = new RequestHandler();

                String result = rh.sendPostRequest(Config.URL_LOGIN_USER,data);

                return result;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("Login Sucessfully")){
                    User user = new User(username, password);
//                    userLocalStore.storeUserData(user);
                    userLocalStore.setUserLoggedIn(true);
                    Intent intent = new Intent(Login.this,UserProfile.class);
                    intent.putExtra("Uniqid","From_Login");
                    intent.putExtra(Config.USER_USERNAME,username);
                    startActivity(intent);
                }else{
                    if(!s.equals(null)) {
                        Toast.makeText(Login.this, s, Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(Login.this, "No Internet Connection!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username,password);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonLogin){
            login();
        }
        if(v == linkRegister){
            Intent intent = new Intent(Login.this,MainActivity.class);
            intent.putExtra("Uniqid","From_Login");
            startActivity(intent);
        }
    }
}

