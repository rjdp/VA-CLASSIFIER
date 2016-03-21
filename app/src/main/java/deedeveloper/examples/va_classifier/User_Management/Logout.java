package deedeveloper.examples.va_classifier.User_Management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public abstract class Logout extends RootActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_logout);
//        setSelectedBackground(tabLogout);
//        tabLogout.setEnabled(false);

//        setSelectedBackground(tabLogout);
//        tabLogout.setEnabled(false);
        Intent intent = new Intent(Logout.this,Login.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        super.onClick(v);
//        if(v== tabClassify) {
//            Intent intent = new Intent(this, Classification.class);
//            startActivity(intent);
//        }
//        if(v == tabProfile){
//            Intent intent = new Intent(this, UserProfile.class);
//            startActivity(intent);
//        }
//        if(v == tabHistory){
//            Intent intent = new Intent(this, HistoryPage.class);
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
//            gotoActivity(UserProfile.class);
//        }
//    }
}
