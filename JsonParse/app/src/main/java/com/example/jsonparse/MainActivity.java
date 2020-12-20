package com.example.jsonparse;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

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
    static private TextView mTextViewResult;
    static public RequestQueue mQueue;
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);
        mQueue = Volley.newRequestQueue(this);
        image = (ImageView)findViewById(R.id.imageView);
        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetAnimalData Data1 = new GetAnimalData("WCT15336");
                Data1.setJsonObject();
                //mTextViewResult.append(Data1.getjSonObj().toString());
                //Data1.jsonParse();
                //HashMap<String, String> dogData = Data1.getData();
            }
        });
    }

    private void jsonParse(){
        String url = "https://jbokdxgfjkxfgnknl.000webhostapp.com/petdata.php?id=WCT15336";
        JsonObjectRequest request = new JsonObjectRequest( url, null,
                new Response.Listener<JSONObject>() {
                    HashMap<String, String> dogData = new HashMap<String, String>();
                    @Override
                    public void onResponse(JSONObject response) {
                        String imgUrl = "";
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
                            imgUrl = response.getString("img");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Picasso.get().load(imgUrl).into(image);
                        for(String key: dogData.keySet()){
                            mTextViewResult.append(key + ": " + dogData.get(key) + '\n');
                        }
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
