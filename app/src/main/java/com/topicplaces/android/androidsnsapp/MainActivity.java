package com.topicplaces.android.androidsnsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.topicplaces.android.androidsnsapp.AndroidSNS.AndroidSNS;

public class MainActivity extends AppCompatActivity {

    Button testButton;
    String authKey;
    String username;
    String password;
    final String ENDPOINT = "http://tse.topicplaces.com/api/2/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testButton = (Button)findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("T", "Successful Test");
                Toast.makeText(getApplicationContext(), "Successful Test", Toast.LENGTH_SHORT).show();

                StrictMode.ThreadPolicy policy =
                        new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                username = "stanleyr001";
                password = "stanleyr001";

                AndroidSNS loginController = new AndroidSNS(ENDPOINT);

                ConnectivityManager cm =
                        (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

                NetworkInfo networkInfo = cm.getActiveNetworkInfo();
                Boolean networkConected = networkInfo.isConnected();

                Log.d("NetworkInfo", networkConected.toString());

                if (networkInfo != null && networkInfo.isConnected()) {
                    authKey = loginController.acquireKey(username, password);
                }else{
                    Toast.makeText(getApplicationContext(), "Network Failure", Toast.LENGTH_SHORT).show();
                }
                //Log.d("authKey ", authKey);
            }
        });

    }
}
