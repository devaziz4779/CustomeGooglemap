package com.example.customegooglemap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
 GoogleMap map;
 Fragment fragment;
 SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment=(SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        searchView=findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
              String location=searchView.getQuery().toString();
              List<Address> addresslist=null;

              if(location!=null || !location.equals("")) {
                  Geocoder geocoder = new Geocoder(MainActivity.this);
                  try{
                      addresslist=geocoder.getFromLocationName(location,1);



                  }catch (Exception e){
                   e.printStackTrace();
                  }
                  Address address=addresslist.get(0);
                  LatLng latLng=new LatLng(address.getLatitude(),address.getLongitude());
                  map.addMarker(new MarkerOptions().position(latLng).title(location));
                  map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));

              }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

      //mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map=googleMap;

        LatLng Multan=new LatLng(30.3753,69.3451);
        map.addMarker(new MarkerOptions().position(Multan).title("Multan"));
        map.moveCamera(CameraUpdateFactory.newLatLng(Multan));
       // map.animateCamera(CameraUpdateFactory.zoomTo(map.getCameraPosition().zoom - 0.5f));

    }
}
