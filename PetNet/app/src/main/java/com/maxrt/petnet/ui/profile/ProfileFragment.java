package com.maxrt.petnet.ui.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.maxrt.petnet.MainActivity;
import com.maxrt.petnet.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private View root;
    private RequestQueue queue;
    private JSONObject json;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        root = inflater.inflate(R.layout.fragment_profile, container, false);

        queue = Volley.newRequestQueue(getContext());

//        String id = "WCT15336";
        MainActivity.loadSettings(getContext());

        String fileName = MainActivity.qrCodeId + ".json";

        if (MainActivity.fileApi.fileExist(getContext(), fileName)) {
            try {
                String text = MainActivity.fileApi.readFile(getContext(), fileName);
                Log.d("ProfileFragment", text);
                json = new JSONObject(text);
                setText(json);
            } catch (Exception e) {
                Log.e("ProfileFragment", e.getMessage());
            }
        } else {
            try {
                Toast.makeText(getContext(), MainActivity.qrCodeId, Toast.LENGTH_SHORT).show();
                MainActivity.settings.put("id", MainActivity.qrCodeId);
            } catch (org.json.JSONException e) {
                Log.e("QRCodeReader", e.getMessage());
            }
            getJSON(MainActivity.qrCodeId);
        }

        return root;
    }

    public void getJSON(String id) {
        if (id.equals("0")) {
            return;
        }
        String url = "https://jbokdxgfjkxfgnknl.000webhostapp.com/petdata.php?id=" + id;
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        json = response;
                        Log.e("DataRequest", json.toString());
                        MainActivity.fileApi.writeFile(getContext(), id + ".json", json.toString(), false);
                        setText(json);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    private void setText(JSONObject json) {
        try {
            TextView text = root.findViewById(R.id.textView17);
            text.setText(json.getString("name"));

            text = root.findViewById(R.id.textView20);
            text.setText(json.getString("view"));

            text = root.findViewById(R.id.textView21);
            text.setText(json.getString("breed"));

            text = root.findViewById(R.id.textView22);
            text.setText(json.getString("sex"));

            text = root.findViewById(R.id.textView23);
            text.setText(json.getString("sterilization"));

            text = root.findViewById(R.id.textView24);
            text.setText(json.getString("dateOfBirth"));

            text = root.findViewById(R.id.textView25);
            text.setText(json.getString("owner"));

            text = root.findViewById(R.id.textView26);
            text.setText("");

            text = root.findViewById(R.id.textView27);
            text.setText(json.getString("registrationDate"));

            text = root.findViewById(R.id.textView28);
            text.setText("");

            text = root.findViewById(R.id.textView29);
            text.setText(json.getString("QRpassport"));

            text = root.findViewById(R.id.textView30);
            text.setText(json.getString("animalId"));

            text = root.findViewById(R.id.textView31);
            text.setText(json.getString("microchip"));

            text = root.findViewById(R.id.textView32);
            text.setText(json.getString("otherIdentifiers"));

            ImageView img = root.findViewById(R.id.imageView2);
            Picasso.with(getContext())
                    .load(json.getString("img"))
                    .into(img);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
