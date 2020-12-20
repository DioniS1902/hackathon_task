package com.maxrt.petnet.ui.profile;

import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private TextView textField;
    private RequestQueue queue;
    private JSONObject json;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);

        textField = root.findViewById(R.id.textViewInfo);
        queue = Volley.newRequestQueue(getContext());

//        String id = "WCT15336";
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
            getJSON(MainActivity.qrCodeId);
        }

        return root;
    }

    public void getJSON(String id) {
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
            Iterator<String> keys = json.keys();
            while (keys.hasNext()) {
                String k = keys.next();
                Log.i("Info", "Key: " + k + ", value: " + json.getString(k));
                textField.append(k + ": " + json.getString(k) + "\n");
            }

        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
}
