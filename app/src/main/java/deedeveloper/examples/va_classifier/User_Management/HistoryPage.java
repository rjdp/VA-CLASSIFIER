package deedeveloper.examples.va_classifier.User_Management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import deedeveloper.examples.va_classifier.LoadData;
import deedeveloper.examples.va_classifier.R;

public class HistoryPage extends RootActivity {

    private ImageButton classify_img_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        classify_img_btn = (ImageButton) findViewById(R.id.history_img_btn);
        locateView();
//        tabClassify = (ImageButton) findViewById(R.id.classify_btn);
//        tabHistory  = (ImageButton)findViewById(R.id.history_btn);
//        tabProfile  = (ImageButton)findViewById(R.id.profile_btn);
//        tabLogout  = (ImageButton)findViewById(R.id.logout_btn);

//        tabClassify = (FrameLayout) findViewById(R.id.classify);
//        tabHistory = (FrameLayout)findViewById(R.id.history);
//        tabProfile = (FrameLayout)findViewById(R.id.profile);
//        tabLogout = (FrameLayout)findViewById(R.id.logout);

        classify_img_btn .setOnClickListener(this);
        tabClassify.setOnClickListener(this);
        tabHistory.setOnClickListener(this);
        tabProfile.setOnClickListener(this);
        tabLogout.setOnClickListener(this);
        Classify_btn.setOnClickListener(this);
        History_btn.setOnClickListener(this);
        Profile_btn.setOnClickListener(this);
        Logout_btn.setOnClickListener(this);


        setSelectedBackground(tabHistory);
        tabClassify.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        //gotoActivity(activity);
        //Log.i(TAG, activity.getSimpleName());

        if (v == classify_img_btn) {
            Intent intent = new Intent(HistoryPage.this, LoadData.class);
            startActivity(intent);
        }
        super.onClick(v);
    }
}
