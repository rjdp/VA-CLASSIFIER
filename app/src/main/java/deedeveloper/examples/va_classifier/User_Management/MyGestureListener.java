package deedeveloper.examples.va_classifier.User_Management;

import android.app.Activity;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

/**
 * Created by Dee Dee on 5/3/2016.
 */
public class MyGestureListener extends SimpleOnGestureListener {

    public final static int SWIPE_LEFT = 1;
    public final static int SWIPE_RIGHT = 2;

    public final static int MODE_TRANSPARENT = 0;
    public final static int MODE_SOLID = 1;
    public final static int MODE_DYNAMIC = 2;

    private final static int ACTION_FAKE = -1; // just an unlikely number
    private int swipe_Min_Distance = 100;
    private int swipe_Max_Distance = 350;
    private int swipe_Min_Velocity = 100;

    private int mode = MODE_DYNAMIC;
    private boolean running = true;
    private boolean tapIndicator = false;

    @SuppressWarnings("unused")
    private Activity activity;
    private GestureDetector detector;
    private SimpleGestureListener listener;

    public MyGestureListener(Activity activity, SimpleGestureListener sgl) {

        this.activity = activity;
        this.detector = new GestureDetector(activity, this);
        this.listener = sgl;
    }

    public void onTouchEvent(MotionEvent event) {

        if (!this.running)
            return;

        boolean result = this.detector.onTouchEvent(event);

        if (this.mode == MODE_SOLID)
            event.setAction(MotionEvent.ACTION_CANCEL);
        else if (this.mode == MODE_DYNAMIC) {

            if (event.getAction() == ACTION_FAKE)
                event.setAction(MotionEvent.ACTION_UP);
            else if (result)
                event.setAction(MotionEvent.ACTION_CANCEL);
            else if (this.tapIndicator) {
                event.setAction(MotionEvent.ACTION_DOWN);
                this.tapIndicator = false;
            }

        }
        // else just do nothing!
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {

        final float xDistance = Math.abs(e1.getX() - e2.getX());
        final float yDistance = Math.abs(e1.getY() - e2.getY());

        if (xDistance > this.swipe_Max_Distance
                || yDistance > this.swipe_Max_Distance)
            return false;

        velocityX = Math.abs(velocityX);
        velocityY = Math.abs(velocityY);
        boolean result = false;

        if (velocityX > this.swipe_Min_Velocity
                && xDistance > this.swipe_Min_Distance) {

            //detecting swipe direction
            if (e1.getX() > e2.getX()) {

                // swipe right to left
                this.listener.onSwipe(SWIPE_LEFT);
            } else {

                // left to right
                this.listener.onSwipe(SWIPE_RIGHT);
            }
            result = true;
        }
        return result;
    }

    /**
     * Declaring an interface to handling swipe actions
     *
     */
    public static interface SimpleGestureListener {
        public void onSwipe(int direction);

    }

}
