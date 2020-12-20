package com.example.jsonparse;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class GetAnimalData {
    private HashMap<String, String> animalData;
    private String id;
    private JSONObject jSonObj;
    public GetAnimalData(String id){
        this.id = id;
    }
    public HashMap<String, String> getData(){
        return animalData;
    }
    public JSONObject getjSonObj(){
         while (jSonObj == null){ 
                                  
         }                        
        return jSonObj;
    }
    void setJsonObject() {
        String url = "https://jbokdxgfjkxfgnknl.000webhostapp.com/petdata.php?id=" + id;
        JsonObjectRequest request = new JsonObjectRequest(url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        jSonObj = response;
                        Log.e("MainActivity", jSonObj.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        MainActivity.mQueue.add(request);
    }
    public void jsonParse() {
        while (jSonObj == null){

        }
        try {
            animalData.put("name", jSonObj.getString("name"));
            animalData.put("view", jSonObj.getString("view"));
            animalData.put("dateOfBirth", jSonObj.getString("dateOfBirth"));
            animalData.put("breed", jSonObj.getString("breed"));
            animalData.put("color", jSonObj.getString("color"));
            animalData.put("sex", jSonObj.getString("sex"));
            animalData.put("registrationDate", jSonObj.getString("registrationDate"));
            animalData.put("owner", jSonObj.getString("owner"));
            animalData.put("mobile_number", jSonObj.getString("number"));
            animalData.put("sterilization", jSonObj.getString("sterilization"));
            animalData.put("animalId", jSonObj.getString("animalId"));
            animalData.put("microchip", jSonObj.getString("microchip"));
            animalData.put("QRpassport", jSonObj.getString("QRpassport"));
            animalData.put("otherIdentifiers", jSonObj.getString("otherIdentifiers"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}