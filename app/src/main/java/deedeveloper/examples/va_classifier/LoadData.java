package deedeveloper.examples.va_classifier;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URISyntaxException;

public class LoadData extends AppCompatActivity implements View.OnClickListener{

    private Button buttonOpenFile,buttonClassify;
    private static final int FILE_SELECT_CODE = 0;
    private final static String TAG = "LoadData";
    private TextView tv_filePath;
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);

        buttonOpenFile = (Button)findViewById(R.id.button_openFile);
        buttonOpenFile.setOnClickListener(this);
        buttonClassify = (Button)findViewById(R.id.button_classify);
        buttonClassify.setOnClickListener(this);
        tv_filePath = (TextView)findViewById(R.id.textView_filePath);

    }

    private void showFileChooser(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("file/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try{
            startActivityForResult(
                    Intent.createChooser(intent, "Select a file to classify"),
                    FILE_SELECT_CODE);

        }catch(android.content.ActivityNotFoundException ex){

            Toast.makeText(this, "Please install a File Manager.".toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case FILE_SELECT_CODE:
                if(resultCode == RESULT_OK){
                    //Get the Uri of the selected file
                    Uri uri = data.getData();
                    Log.d(TAG,"File Uri: " + uri.toString());
                    //Get the path
                        path = null;
                    try {
                        path = FileUtils.getPath(this, uri);
                    } catch (URISyntaxException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG,"File Path: " + path);
                    //Get the file instance
                    tv_filePath.setText(path);
                    //File file = new File(path);
                    //Initiate the classify
                }
                break;
        }
        super.onActivityResult(requestCode,resultCode,data);
    }


    @Override
    public void onClick(View v) {
        if(v == buttonOpenFile){
            showFileChooser();
        }
        if(v == buttonClassify){
//            Intent intent = new Intent(LoadData.this, ECG_detect_classification.class);
//            intent.putExtra("filePath",path);
//            startActivity(intent);
            //ECG_detect_classification ecg_dc = new ECG_detect_classification();
          //  ecg_dc.ecgDC_setting(path);

            Intent intent = new Intent(LoadData.this,ECG_output.class);
            intent.putExtra("ecgClassifyPath",path);
            startActivity(intent);
        }

    }
}
