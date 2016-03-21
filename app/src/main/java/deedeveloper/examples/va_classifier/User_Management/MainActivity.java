package deedeveloper.examples.va_classifier.User_Management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import deedeveloper.examples.va_classifier.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextName;
    private EditText editTextEmail;
    private EditText editTextConNum;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextConfirmPassword;
    private Button buttonRegister, buttonLogin;

    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextConNum = (EditText) findViewById(R.id.editTextConNum);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonRegister.setOnClickListener(this);
        buttonLogin.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);

    }

    @Override
    public void onStart(){
        super.onStart();
        Intent intent = getIntent();
        String strdata = intent.getStringExtra("Uniqid");
        if(authenticate()== true){
          //  displayUserDetails();
            User user = userLocalStore.getLoggedInUser();
            intent = new Intent(MainActivity.this,UserProfile.class);
            startActivity(intent);
        }else if(strdata != null) {
            if (strdata.equals("From_Login")) {
                //setContentView(R.layout.activity_main);
                strdata.equals(null);
            }
        }else{
            startActivity(new Intent(MainActivity.this,Login.class));
        }
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggedIn();
    }

    private void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();

    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim().toLowerCase();
        String email = editTextEmail.getText().toString().trim().toLowerCase();
        String con_num = editTextConNum.getText().toString().trim().toLowerCase();
        String username = editTextUsername.getText().toString().trim().toLowerCase();
        String password = editTextPassword.getText().toString().trim().toLowerCase();
        String confirm_password = editTextConfirmPassword.getText().toString().trim().toLowerCase();



        if(password.equals(confirm_password)) {
            User registerData = new User(name, email,name, username, password);
            register(registerData);
        }else{
            Toast.makeText(MainActivity.this, "Password are not matched", Toast.LENGTH_SHORT).show();
        }
    }

    private void register(User registerUser) {
        class RegisterUser extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Please wait","loading...", true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String, String> data = new HashMap<String,String>();
                data.put(Config.KEY_USER_NAME,params[0]);
                data.put(Config.KEY_USER_EMAIL,params[1]);
                data.put(Config.KEY_USER_CON_NUM,params[2]);
                data.put(Config.KEY_USER_USERNAME,params[3]);
                data.put(Config.KEY_USER_PASSWORD,params[4]);


                String result = rh.sendPostRequest(Config.URL_REGISTER_USER,data);
                return  result;
            }
        }

        RegisterUser ru = new RegisterUser();
        ru.execute(registerUser.name, registerUser.email, registerUser.con_num, registerUser.username, registerUser.password);
    }

    @Override
    public void onClick(View v) {
        if(v == buttonRegister){
            registerUser();
        }
        if(v == buttonLogin){
            Intent intent = new Intent(MainActivity.this,Login.class);
            startActivity(intent);
        }
    }
}
