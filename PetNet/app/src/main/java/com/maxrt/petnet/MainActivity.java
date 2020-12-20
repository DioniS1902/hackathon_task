package com.maxrt.petnet;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.util.IOUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    public static class fileApi {
        public static boolean fileExist(Context context, String fileName){
            File file = context.getFileStreamPath(fileName);
            return file.exists();
        }

        public static String readFile(Context context, @NotNull String fileName) {
            FileInputStream inputStream;
            String text = "";
            try {
                inputStream = context.openFileInput(fileName);
                Scanner sc = new Scanner(inputStream);
                StringBuffer sb = new StringBuffer();
                while (sc.hasNext()) {
                    sb.append(sc.nextLine());
                }
                text = sb.toString();
                Log.e("TEXT", text);
                inputStream.close();
            } catch (Exception e) {
                Log.e("readFile", e.getMessage());
                e.printStackTrace();
            }
            return text;
        }

        public static void writeFile(Context context, @NonNull String fileName, @NotNull String content, boolean update) {
            FileOutputStream outputStream;

            try {
                if (update) {
                    outputStream = context.openFileOutput(fileName, Context.MODE_APPEND);
                } else {
                    outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
                }
                outputStream.write(content.getBytes());
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                Log.e("writeFile", e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static final String settingsFileName = "user_data.json";
    public static String qrCodeId = "0";
    public static JSONObject settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_maps, R.id.navigation_qr_code_reader, R.id.navigation_profile)
                .build();
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        NavController navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        loadSettings();
    }

    private void loadSettings() {
        if (fileApi.fileExist(this, settingsFileName)) {
            String text = fileApi.readFile(this, settingsFileName);
            try {
                JSONObject settings = new JSONObject(text);
            } catch (org.json.JSONException e) {
                Log.e("loadSettings", e.getMessage());
            }
        } else {
            try {
                settings = new JSONObject("{\"id\"=\"0\"}");
            } catch (org.json.JSONException e) {
                Log.e("loadSettings", e.getMessage());
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        fileApi.writeFile(this, settingsFileName, settings.toString(), false);
    }
}