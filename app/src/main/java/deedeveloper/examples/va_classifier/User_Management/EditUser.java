package deedeveloper.examples.va_classifier.User_Management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import deedeveloper.examples.va_classifier.R;

public class EditUser extends AppCompatActivity implements View.OnClickListener {

    private TextView tvId,tvUsername,tvEmail;
    private EditText etName,etConNum,etPassword,etConPassword;
    private Button btnUpdate,btnCancel;
    UserLocalStore userLocalStore;
  //  private String id,username, email, name, con_num,password,confirm_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);


        //find textView and editText by id and set variables
        tvId = (TextView) findViewById(R.id.tvId);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        etName = (EditText) findViewById(R.id.etName);
        etConNum = (EditText) findViewById(R.id.etConNum);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etConPassword = (EditText) findViewById(R.id.etConfirmPassword);

        userLocalStore = new UserLocalStore(this);

//        //intent data from user profile
//        Intent intent = getIntent();
//        id = intent.getStringExtra(Config.USER_ID);
//        username = intent.getStringExtra(Config.USER_USERNAME);
//        email = intent.getStringExtra(Config.USER_EMAIL);
//        name = intent.getStringExtra(Config.USER_NAME);
//        con_num = intent.getStringExtra(Config.USER_CON_NUM);
//        password = intent.getStringExtra(Config.USER_PASSWORD);

        User storedUser= userLocalStore.getLoggedInUser();
        //set Text to field
        tvId.setText(storedUser.id);
        tvUsername.setText(storedUser.username);
        tvEmail.setText(storedUser.email);
        etName.setText(storedUser.name);
        etConNum.setText(storedUser.con_num);
        etPassword.setText(storedUser.password);
        etConPassword.setText("");


        //find button id
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

        //set buttonOnClickListener
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(this);

    }

    private void updateUser(){

        final String updated_username = tvUsername.getText().toString().trim();
        final String updated_name = etName.getText().toString().trim();
        final String updated_con_num = etConNum.getText().toString().trim();
        final String updated_password = etPassword.getText().toString().trim();


        class UpdateUser extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(EditUser.this,"Updating...","Wait...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(EditUser.this, s, Toast.LENGTH_LONG).show();


            }

            @Override
            protected String doInBackground(Void... params) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put(Config.KEY_USER_USERNAME,updated_username);
                hashMap.put(Config.KEY_USER_NAME,updated_name);
                hashMap.put(Config.KEY_USER_CON_NUM,updated_con_num);
                hashMap.put(Config.KEY_USER_PASSWORD,updated_password);

                RequestHandler rh = new RequestHandler();

                String s = rh.sendPostRequest(Config.URL_UPDATE_USER,hashMap);

                return s;
            }
        }

        UpdateUser uu = new UpdateUser();
        uu.execute();
    }

    @Override
    public void onClick(View v) {
        String curr_id,curr_username,curr_email,curr_name,curr_con_num,curr_password,curr_confirm_password;

        curr_id = tvId.getText().toString();
        curr_username = tvUsername.getText().toString();
        curr_email = tvEmail.getText().toString();
        curr_name = etName.getText().toString();
        curr_con_num = etConNum.getText().toString();
        curr_password = etPassword.getText().toString();
        curr_confirm_password = etConPassword.getText().toString();
        if(v == btnUpdate){
            if(curr_password.equals(curr_confirm_password)) {
                updateUser();
                User editUser =  new User(curr_id,curr_name,curr_email,curr_con_num, curr_username,curr_password);
                userLocalStore.storeUserData(editUser);

                Intent intent = new Intent(EditUser.this,UserProfile.class);
                startActivity(intent);
              //  intent.putExtra("Uniqid","From_EditUser");
//                intent.putExtra(Config.USER_ID,curr_id);
//                intent.putExtra(Config.USER_USERNAME,curr_username);
//                intent.putExtra(Config.USER_EMAIL,curr_email);
//                intent.putExtra(Config.USER_NAME,curr_name);
//                intent.putExtra(Config.USER_CON_NUM,curr_con_num);
//                intent.putExtra(Config.USER_PASSWORD,curr_password);

            } else {
                Toast.makeText(EditUser.this, "Password are not matched", Toast.LENGTH_SHORT).show();
            }
        }

        if(v == btnCancel){
            Intent intent = new Intent(EditUser.this,UserProfile.class);
//            intent.putExtra("Uniqid","From_EditUser");
//            intent.putExtra(Config.USER_ID,id);
//            intent.putExtra(Config.USER_USERNAME,username);
//            intent.putExtra(Config.USER_EMAIL,email);
//            intent.putExtra(Config.USER_NAME,name);
//            intent.putExtra(Config.USER_CON_NUM,con_num);
//            intent.putExtra(Config.USER_PASSWORD,password);
            startActivity(intent);
         }

    }


}
