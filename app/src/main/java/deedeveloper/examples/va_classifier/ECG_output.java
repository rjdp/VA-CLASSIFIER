package deedeveloper.examples.va_classifier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import deedeveloper.examples.va_classifier.ECGAnalysis.ECG_detect_classification;

public class ECG_output extends AppCompatActivity {
    TextView classResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecg_output);

        classResult = (TextView)findViewById(R.id.textView_classResult);

        ECG_detect_classification ecg_dc = new ECG_detect_classification();
        // ecg_dc = (ECG_detect_classification) getIntent().getSerializableExtra("ecgClassifyObj");
        Intent intent = getIntent();
        String pathStr = intent.getStringExtra("ecgClassifyPath");
        ecg_dc.ecgDC_setting(pathStr);



        //ecg_dc.ecgDC_setting(path);
        classResult.setText("");
    }
}
