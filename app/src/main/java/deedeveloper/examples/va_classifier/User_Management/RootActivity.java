package deedeveloper.examples.va_classifier.User_Management;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import deedeveloper.examples.va_classifier.R;

public abstract class RootActivity extends AppCompatActivity implements OnClickListener{
        //implements MyGestureListener.SimpleGestureListener {

    protected ViewGroup container;
    protected ImageButton Classify_btn;
    protected ImageButton History_btn;
    protected ImageButton Profile_btn;
    protected ImageButton Logout_btn;
    protected View tabClassify;
    protected View tabHistory;
    protected View tabProfile;
    protected View tabLogout;
    UserLocalStore userLocalStore;

//    protected FrameLayout Classify_frame;
//    protected FrameLayout History_frame;
//    protected FrameLayout Profile_frame;
//    protected FrameLayout Logout_frame;
    protected TextView contentText;
    protected MyGestureListener gestureListener;
    private final static String TAG = "RootActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_footer);

      //  setClickListener();

      //  inflateContentFromLayout();
        locateView();
        userLocalStore = new UserLocalStore(this);


//        tabClassify.setOnClickListener(onClick(Classification.class));
//        tabHistory.setOnClickListener(onClick(HistoryPage.class));
//        tabProfile.setOnClickListener(onClick(UserProfile.class));
//        tabLogout.setOnClickListener(onClick(Logout.class));
//        tabClassify.setOnClickListener(this);
//        tabHistory.setOnClickListener(this);
//        tabProfile.setOnClickListener(this);
//        tabLogout.setOnClickListener(this);
        Log.i(TAG, "All Listener set ");
        //setTabListener();
        // set on click listener to go to other activities
       // tabClassify.setOnClickListener(this);

        // Detect touched area
      //  gestureListener = new MyGestureListener(this, this);


    }
//

    protected void locateView() {
//
        Classify_btn = (ImageButton) findViewById(R.id.classify_btn);
        History_btn = (ImageButton)findViewById(R.id.history_btn);
        Profile_btn = (ImageButton)findViewById(R.id.profile_btn);
        Logout_btn = (ImageButton)findViewById(R.id.logout_btn);
        tabClassify = (FrameLayout) findViewById(R.id.classify);
        tabHistory = (FrameLayout)findViewById(R.id.history);
        tabProfile = (FrameLayout)findViewById(R.id.profile);
        tabLogout = (FrameLayout)findViewById(R.id.logout);
//
       Log.i(TAG, "Hi i here at root activity");
    }

//    protected void setClickListener(){
////        tabClassify.setOnClickListener(onClick(Classification.class));
////        tabHistory.setOnClickListener(onClick(HistoryPage.class));
////        tabProfile.setOnClickListener(onClick(UserProfile.class));
////        tabLogout.setOnClickListener(onClick(Logout.class));
//    }
    /**
     * Start an activity through Intent
     // * @param activity
     */
//    protected void gotoActivity(Class<?> activity) {
//
//        Intent intent = new Intent(this, activity);
//        startActivity(intent);
//
//    }


//    protected OnClickListener onClick(final Class<?> activity) {
//        Log.i(TAG, "on Click Listener start at here");
//        return new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gotoActivity(activity);
//                Log.i(TAG, activity.getSimpleName());
//            }
//        };
//    }

    protected void setSelectedBackground(View v) {
        v.setBackgroundResource(R.drawable.blue_border_square);
    }

    public void onClick(View v) {

        if(v == tabProfile || v == Profile_btn ){
            Intent intent = new Intent(this, UserProfile.class);
            startActivity(intent);
        }
        if(v == tabClassify || v == Classify_btn) {
            Intent intent = new Intent(this, ClassificationPage.class);
            startActivity(intent);
        }
        if(v == tabHistory || v == History_btn ){
            Intent intent = new Intent(this, HistoryPage.class);
            startActivity(intent);
        }
        if(v == tabLogout || v == Logout_btn){
            userLocalStore.clearUserData();
            userLocalStore.setUserLoggedIn(false);
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }

       // gotoActivity(v);
        //Log.i(TAG, activity.getSimpleName());
    }



//    public void onClick_root(View v) {
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
//       // Log.i(TAG, activity.getSimpleName());
//    }


//    @Override
//            public void onClick(View v) {
//                if(v == tabClassify) {
//                    Intent intent = new Intent(this, Classification.class);
//                     startActivity(intent);
//                }
//            }


//    @SuppressLint("InflateParams")
//    private void inflateContentFromLayout() {
//        LayoutInflater inflater = (LayoutInflater) getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
//        View contentView = inflater.inflate(R.layout.activity_content, null,
//                false);
//        container.addView(contentView);
//       // contentText = (TextView) contentView.findViewById(R.id.image);
//    }


//    @Override
//    public boolean dispatchTouchEvent(MotionEvent me) {
//
//        // Call onTouchEvent of SimpleGestureFilter class
//        this.gestureListener.onTouchEvent(me);
//        return super.dispatchTouchEvent(me);
//    }

//    @Override
//    public void onSwipe(int direction) {
//        String str = "";
//
//        switch (direction) {
//
//            case MyGestureListener.SWIPE_RIGHT:
//                str = "Swipe Left To Right";
//                switchActivity(direction);
//                break;
//            case MyGestureListener.SWIPE_LEFT:
//                str = "Swipe Right To Left";
//                switchActivity(direction);
//                break;
//
//            default:
//                break;
//
//        }
//        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
//    }

    /**
     * switch activity on swipe
     * implemented by sub activities
   //  * @param swipeDirection
     */
//    public abstract void switchActivity(int swipeDirection);
}