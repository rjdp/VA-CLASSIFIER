package deedeveloper.examples.va_classifier.User_Management;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import deedeveloper.examples.va_classifier.R;

public class UserProfile extends RootActivity {

    private TextView textViewId,textViewUsername,textViewEmail,textViewName,textViewConNum,textViewPassword;

    private Button buttonEdit;
    private final static String TAG = "User Profile Activity";
    private String id,username, email, name, con_num,password;
    int counter = 0;
    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        textViewId = (TextView) findViewById(R.id.tvId);
        textViewUsername = (TextView) findViewById(R.id.tvUsername);
        textViewEmail = (TextView) findViewById(R.id.tvEmail);
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewConNum = (TextView) findViewById(R.id.textViewConNum);
        textViewPassword = (TextView) findViewById(R.id.textViewPassword);

        locateView();


        buttonEdit = (Button) findViewById(R.id.buttonEdit);
        buttonEdit.setOnClickListener(this);
        tabClassify.setOnClickListener(this);
        tabHistory.setOnClickListener(this);
        tabProfile.setOnClickListener(this);
        tabLogout.setOnClickListener(this);
        Classify_btn.setOnClickListener(this);
        History_btn.setOnClickListener(this);
        Profile_btn.setOnClickListener(this);
        Logout_btn.setOnClickListener(this);
        userLocalStore = new UserLocalStore(this);

//        setSelectedBackground(tabProfile);
//        tabProfile.setEnabled(false);
//
        setSelectedBackground(tabProfile);
        tabProfile.setEnabled(false);

             Intent intent = getIntent();
       // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            String strdata = intent.getStringExtra("Uniqid");
            if(strdata != null) {
                if (strdata.equals("From_Login")) {
                    username = intent.getStringExtra(Config.USER_USERNAME);
                    getUser();
                    strdata.equals(null);
                }
//                else if (strdata.equals("From_EditUser")) {
//                    id = intent.getStringExtra(Config.USER_ID);
//                    username = intent.getStringExtra(Config.USER_USERNAME);
//                    email = intent.getStringExtra(Config.USER_EMAIL);
//                    name = intent.getStringExtra(Config.USER_NAME);
//                    con_num = intent.getStringExtra(Config.USER_CON_NUM);
//                    password = intent.getStringExtra(Config.USER_PASSWORD);
//                    User editUser = new User(id, username, email, con_num,name,password);
//                    setUserTextData(editUser);
//                }
            }else{
                User storedUser= userLocalStore.getLoggedInUser();
                setUserTextData(storedUser);

            }

    }

    private void setUserTextData(User userData){
        textViewId.setText(userData.id);
        textViewUsername.setText(userData.username);
        textViewEmail.setText(userData.email);
        textViewName.setText(userData.name);
        textViewConNum.setText(userData.con_num);
        textViewPassword.setText(userData.password);
    }

    private void getUser(){
        class GetUser extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(UserProfile.this,"Fetching...","Wait...",false,false);
            }

            @Override
            protected String doInBackground(Void... params) {
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequestParam(Config.URL_GET_USER,username);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                showUser(s);
            }
        }
        GetUser gu= new GetUser();
        gu.execute();
    }

    private void showUser(String json){
        User returnedUser = null;
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);
            JSONObject c = result.getJSONObject(0);
                id = c.getString(Config.TAG_ID);
                name = c.getString(Config.TAG_NAME);
                email = c.getString(Config.TAG_EMAIL);
                con_num = c.getString(Config.TAG_CON_NUM);
                username = c.getString(Config.TAG_USERNAME);
                password = c.getString(Config.TAG_PASSWORD);

                returnedUser =  new User(id,name,email,con_num,username,password);
                userLocalStore.storeUserData(returnedUser);
              //  userLocalStore.setUserLoggedIn(true);
                setUserTextData(returnedUser);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        if(v == buttonEdit){
            Intent intent = new Intent(UserProfile.this,EditUser.class);
//            intent.putExtra(Config.USER_ID,id);
//            intent.putExtra(Config.USER_USERNAME,username);
//            intent.putExtra(Config.USER_EMAIL,email);
//            intent.putExtra(Config.USER_NAME,name);
//            intent.putExtra(Config.USER_CON_NUM,con_num);
//            intent.putExtra(Config.USER_PASSWORD,password);
            startActivity(intent);
        }

        super.onClick(v);

//        if(v== tabClassify) {
//            Intent intent = new Intent(this, Classification.class);
//           // intent.putExtra("Uniqid","From_Classify");
//            startActivity(intent);
//        }
////        if(v == tabProfile){
////            Intent intent = new Intent(this, UserProfile.class);
////            startActivity(intent);
////        }
//        if(v == tabHistory){
//            Intent intent = new Intent(this, HistoryPage.class);
//            //intent.putExtra("Uniqid","From_History");
//            startActivity(intent);
//        }
//        if(v == tabLogout){
//            Intent intent = new Intent(this, Logout.class);
//            startActivity(intent);
//        }
    }

//    @Override
//    public void switchActivity(int swipeDirection) {
//        if (swipeDirection == 2) {
//            gotoActivity(HistoryPage.class);
//        } else {
//            gotoActivity(Logout.class);
//        }
//    }

//    @Override
//    protected void onResume(){
//        super.onResume();
//        textViewId.setText(id);
//        textViewUsername.setText(username);
//        textViewEmail.setText(email);
//        textViewName.setText(name);
//        textViewConNum.setText(con_num);
//        textViewPassword.setText(password);
//    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        savedInstanceState.putBoolean("MyName", true);
        savedInstanceState.putDouble("MyDouble", 1.9);


        savedInstanceState.putString("MyTitle", "Welcome back to Android");
        savedInstanceState.putString("MyId", textViewId.getText().toString());
        savedInstanceState.putString("MyUsername", textViewName.getText().toString());
        savedInstanceState.putString("MyEmail", textViewEmail.getText().toString());
        savedInstanceState.putString("MyName", textViewName.getText().toString());
        savedInstanceState.putString("MyCon_Num", textViewConNum.getText().toString());
        savedInstanceState.putString("MyPassword", textViewPassword.getText().toString());
        savedInstanceState.putInt("Counter", counter);
        Log.v(TAG, "Inside of savedInstanceState");
        Log.v(TAG, counter +" is saved");


        // etc.
    }


//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        // Restore UI state from the savedInstanceState.
//        // This bundle has also been passed to onCreate.
//        // title = savedInstanceState.getString("MyTitle");
//        id = savedInstanceState.getString("MyId");
//        username = savedInstanceState.getString("MyUsername");
//        email = savedInstanceState.getString("MyEmail");
//        name = savedInstanceState.getString("MyName");
//        con_num = savedInstanceState.getString("MyCon_Num");
//        password = savedInstanceState.getString("MyPassword");
//        counter = savedInstanceState.getInt("Counter");
//        Log.v(TAG, "Inside of onRestoreInstanceState");
//        Log.v(TAG, counter +" is restored");
//
//    }

}
