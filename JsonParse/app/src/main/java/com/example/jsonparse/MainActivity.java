package com.example.jsonparse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
public class MainActivity extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);
        mQueue = Volley.newRequestQueue(this);
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    jsonParse();
            }
        });
    }
    private void jsonParse(){
        String url = "https://graph.facebook.com/19292868552";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        /*HashMap<String, String> dogData = new HashMap<String, String>();
                        try {
                            dogData.put("name" ,response.getString("name"));
                            dogData.put("view" ,response.getString("view"));
                            dogData.put("dateOfBirth" ,response.getString("dateOfBirth"));
                            dogData.put("breed", response.getString("breed"));
                            dogData.put("color", response.getString("color"));
                            dogData.put("sex", response.getString("sex"));
                            dogData.put("registrationDate", response.getString("registrationDate"));
                            dogData.put("owner", response.getString("owner"));
                            dogData.put("mobile_number", response.getString("number"));
                            dogData.put("sterilization", response.getString("sterilization"));
                            dogData.put("animalId", response.getString("animalId"));
                            dogData.put("microchip", response.getString("microchip"));
                            dogData.put("QRpassport", response.getString("QRpassport"));
                            dogData.put("otherIdentifiers", response.getString("otherIdentifiers"));
                            dogData.put("imgUrl", response.getString("img"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        for(String key: dogData.keySet()){
                            mTextViewResult.append(key + ": " + dogData.get(key) + '\n');
                        }*/
                        String message = "";
                        try {
                            JSONObject error = response.getJSONObject("error");
                            message = error.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mTextViewResult.append(message);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                mTextViewResult.append("Error");
            }
        });
        mQueue.add(request);
    }
}
