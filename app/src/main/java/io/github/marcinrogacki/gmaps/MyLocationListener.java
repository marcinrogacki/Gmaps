package io.github.marcinrogacki.gmaps;

import android.location.LocationListener;
import android.os.Bundle;
import android.location.Location;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdateFactory;


/*---------- Listener class to get coordinates ------------- */
class MyLocationListener implements LocationListener {

    private GoogleMap mMap;
    private MarkerOptions player;

    public MyLocationListener(GoogleMap m)
    {
        mMap = m;
        player = new MarkerOptions();
        player.title("You");
    }

    @Override
    public void onLocationChanged(Location loc) {
            player.position(new LatLng(loc.getLatitude(), loc.getLongitude()));
            mMap.clear();
            mMap.addMarker(player);
            mMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    player.getPosition(),
                    mMap.getCameraPosition().zoom
                )
            );
    }

    @Override
    public void onProviderDisabled(String provider) {}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {}
}
