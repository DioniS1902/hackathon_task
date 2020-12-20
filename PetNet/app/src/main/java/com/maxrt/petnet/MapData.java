package com.maxrt.petnet;

import android.content.Context;

import java.io.InputStream;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Scanner;

public class MapData extends AppCompatActivity {

    public static String readFile(Context context) {
        InputStream inputStream;
        String text = "";
        try {
            inputStream = context.getAssets().open("places.json");
            Scanner sc = new Scanner(inputStream);
            StringBuffer sb = new StringBuffer();
            while (sc.hasNext()) {
                sb.append(sc.nextLine());
            }
            text = sb.toString();
            inputStream.close();
        } catch (Exception e) {
            Log.e("readFile", e.getMessage());
            e.printStackTrace();
        }
        return text;
    }
}
