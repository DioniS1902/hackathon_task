package com.maxrt.petnet.ui.maps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.maxrt.petnet.Data;
import com.maxrt.petnet.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;




public class MapsFragment extends Fragment {


    private OnMapReadyCallback callback = new OnMapReadyCallback() {


        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        private JSONObject json;
        @Override
        public void onMapReady(GoogleMap googleMap) {
            try {
                String text = Data.readFile(getContext());
                Log.d("ProfileFragment", text);
                json = new JSONObject(text);

            } catch (Exception e) {
                Log.e("ProfileFragment", e.getMessage());
            }
            JSONArray jsarr = null;
            try {
                jsarr= json.getJSONArray("Places");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for(int i=0;i < jsarr.length();i++) {
                try {
                    googleMap.addMarker(new MarkerOptions().position(new LatLng(jsarr.getJSONObject(i).getDouble("v"), jsarr.getJSONObject(i).getDouble("v1"))).title(jsarr.getJSONObject(i).getString("name"))).setIcon(BitmapDescriptorFactory.fromAsset(jsarr.getJSONObject(i).getString("img")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(12));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(new  LatLng(49.84289512798616, 24.026557047742685)));

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}