package com.example.getapicalldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getData("https://api.github.com/users/param105/repos");
    }

    @SuppressLint("StaticFieldLeak")
    void getData(String url){

        String appUrl=url;

        new AsyncTask<String,Void,String>() {
            @Override
            protected String doInBackground(String... strings) {

                String result="";

                String url=strings[0];

                Log.e("ur",""+url);
                String inputLine;

                    try {
                        URL url1=new URL(url);

                        HttpsURLConnection connection= (HttpsURLConnection) url1.openConnection();

                        connection.setRequestMethod("GET");
                        connection.setReadTimeout(15000);
                        connection.setConnectTimeout(15000);

                        //Connect to our url
                        connection.connect()  ;       //Create a new InputStreamReader
                        InputStreamReader streamReader = new
                                InputStreamReader(connection.getInputStream());         //Create a new buffered reader and String Builder
                        BufferedReader reader = new BufferedReader(streamReader);
                        StringBuilder stringBuilder = new StringBuilder();         //Check if the line we are reading is not null
                        while((inputLine = reader.readLine()) != null){
                            stringBuilder.append(inputLine);
                        }         //Close our InputStream and Buffered reader
                        reader.close();
                        streamReader.close();         //Set our result equal to our stringBuilder
                        result = stringBuilder.toString();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                return result;
            }

        }.execute(url);
    }
}
