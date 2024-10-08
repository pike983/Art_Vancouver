package ca.bcit.comp3717.artvancouver;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    ArrayList<Record> records;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        RecordsWrapper rw = (RecordsWrapper) getIntent().getSerializableExtra("records");
        records = rw.getRecords();
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
        mMap = googleMap;
        mMap.clear();

        boolean moved = false;
        Marker currentMarker;

        if (records.isEmpty()) {
            Toast toast = Toast.makeText(this,
                    "Sorry, no results found.", Toast.LENGTH_LONG);
            toast.show();
        }

        for (Record record: records) {
            if (!(record.getFields().getGeom() == null)) {
                LatLng location = new LatLng(record.getFields().getGeom().getCoordinates().get(1),
                        record.getFields().getGeom().getCoordinates().get(0));
                currentMarker = mMap.addMarker(
                        new MarkerOptions().position(location)
                                .title(record.getFields().getSitename())
                                .snippet("Click for more information."));
                currentMarker.setTag(record);
                mMap.setOnInfoWindowClickListener(this);
                if (!moved) {
                    mMap.moveCamera(CameraUpdateFactory.zoomTo(15));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
                    moved = true;
                }
            }
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(this, "Info window clicked",
                Toast.LENGTH_SHORT).show();
        Record markerRecord = (Record) marker.getTag();
        Log.d("Pass Record", markerRecord.toString());
        Intent intent = new Intent(this, ArtInfoActivity.class);
        intent.putExtra("record", markerRecord);
        startActivity(intent);
    }

    public void onZoom(View v) {
        if (v.getId() == R.id.btnZoomIn)
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        else
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
    }

    public void onClickBack(View v) {
        finish();
    }
}
