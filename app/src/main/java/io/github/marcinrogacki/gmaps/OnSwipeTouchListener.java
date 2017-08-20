package io.github.marcinrogacki.gmaps;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ViewAnimator;
import android.util.Log;

public class OnSwipeTouchListener implements OnTouchListener {
    private final GestureDetector gestureDetector = new GestureDetector(new GestureListener());
	protected ViewAnimator viewAnimator;

    public OnSwipeTouchListener(ViewAnimator va) {
        Log.v("mrogacki", "OnSwipeTouchListener");
        this.viewAnimator = va;
    }

    public boolean onTouch(final View v, final MotionEvent event) {
        Log.v("mrogacki", "onTouch");
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;


        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            Log.v("mrogacki", "onFling");
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            result = onSwipeRight();
                        } else {
                            result = onSwipeLeft();
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            result = onSwipeBottom();
                        } else {
                            result = onSwipeTop();
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    public boolean onSwipeRight() {
        Log.v("mrogacki", "onSwipeRight");
        return false;
    }

    public boolean onSwipeLeft() {
        Log.v("mrogacki", "onSwipeLeft");
        return false;
    }

    public boolean onSwipeTop() {
        Log.v("mrogacki", "onSwipeTop");
        return false;
    }

    public boolean onSwipeBottom() {
        Log.v("mrogacki", "onSwipeBottom");
        return false;
    }
}