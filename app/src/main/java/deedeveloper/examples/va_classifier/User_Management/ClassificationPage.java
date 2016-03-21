package deedeveloper.examples.va_classifier.User_Management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import deedeveloper.examples.va_classifier.LoadData;
import deedeveloper.examples.va_classifier.R;

/**
 * Created by Dee Dee on 20/3/2016.
 */


public class ClassificationPage extends RootActivity {

    private ImageButton classify_img_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);

        classify_img_btn = (ImageButton) findViewById(R.id.classify_img_btn);
        locateView();

        classify_img_btn .setOnClickListener(this);
        tabClassify.setOnClickListener(this);
        tabHistory.setOnClickListener(this);
        tabProfile.setOnClickListener(this);
        tabLogout.setOnClickListener(this);
        Classify_btn.setOnClickListener(this);
        History_btn.setOnClickListener(this);
        Profile_btn.setOnClickListener(this);
        Logout_btn.setOnClickListener(this);

        setSelectedBackground(tabClassify);
        tabClassify.setEnabled(false);

    }

    @Override
    public void onClick(View v) {

        if(v == classify_img_btn){
            Intent intent = new Intent(ClassificationPage.this,LoadData.class);
            startActivity(intent);
        }
        super.onClick(v);
    }
}




