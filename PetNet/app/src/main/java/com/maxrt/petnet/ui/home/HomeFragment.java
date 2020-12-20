package com.maxrt.petnet.ui.home;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RequestQueue queue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView textView = root.findViewById(R.id.text_home);

        queue = Volley.newRequestQueue(getContext());

        getJSON();

        return root;
    }

    public void getJSON() {
        String url = "https://jbokdxgfjkxfgnknl.000webhostapp.com/sales.php";
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        setDataFields(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    private void setDataFields(JSONObject json) {
        /*try {
            // Code
        } catch (JSONException e) {
            Log.e("HomeFragment", e.getMessage());
            e.printStackTrace();
        }*/
    }
}