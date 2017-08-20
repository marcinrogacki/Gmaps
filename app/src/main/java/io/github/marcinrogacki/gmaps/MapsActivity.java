package io.github.marcinrogacki.gmaps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import java.util.Random;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.location.LocationManager;
import android.location.LocationListener;
import android.Manifest;
import android.content.pm.PackageManager;
import android.content.Context;
import android.widget.ViewAnimator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.util.Log;
import android.view.View.OnTouchListener;
import android.view.MotionEvent;
import android.support.constraint.ConstraintLayout;

import com.github.pwittchen.swipe.library.Swipe;
import com.github.pwittchen.swipe.library.SwipeListener;

import io.github.marcinrogacki.gmaps.MyLocationListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private LocationListener locationListener;
    private LocationManager locationManager;
    ViewAnimator viewAnimator;
    Animation slide_in_left;
    Animation slide_out_right;
    private Swipe swipe;

    private static final String[] INITIAL_PERMS={
        Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        viewAnimator = (ViewAnimator)findViewById(R.id.viewanimator);

        SwipeListener otls = new SwipeListener() {
            @Override public void onSwipingLeft(final MotionEvent event) {
            }

            @Override public void onSwipedLeft(final MotionEvent event) {
              viewAnimator.showPrevious();
            }

            @Override public void onSwipingRight(final MotionEvent event) {
            }

            @Override public void onSwipedRight(final MotionEvent event) {
              viewAnimator.showNext();
            }

            @Override public void onSwipingUp(final MotionEvent event) {
            }

            @Override public void onSwipedUp(final MotionEvent event) {
            }

            @Override public void onSwipingDown(final MotionEvent event) {
            }

            @Override public void onSwipedDown(final MotionEvent event) {
            }
        };


        swipe = new Swipe(70, 300);
        swipe.setListener(otls);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        swipe.dispatchTouchEvent(event);
        return super.dispatchTouchEvent(event);
    }

    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (!canAccessLocation()) {
            requestPermissions(INITIAL_PERMS, 1337);
        } else {
            locationListener = new MyLocationListener(googleMap);
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 2000, 0, locationListener
            );
        }
    }
}
