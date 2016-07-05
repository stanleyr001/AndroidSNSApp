package com.topicplaces.android.androidsnsapp.AndroidSNS.Users;

import android.support.v4.util.Pair;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RESTLogin {

    private String ENDPOINT;

    public RESTLogin(String end) {
        ENDPOINT = end;
    }

    public String login(String user, String password) {

        String authKey = null;

        try {

            URL url = new URL(ENDPOINT + "sessions");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setChunkedStreamingMode(0);

            urlConnection.addRequestProperty("username", user);
            urlConnection.addRequestProperty("password", password);
            urlConnection.addRequestProperty("on_success", ENDPOINT.replaceAll("api/2/",
                    "") + "util-iframes/blank-page.html?response=success");
            urlConnection.addRequestProperty("on_fail", ENDPOINT.replaceAll("api/2/", "")
                    + "util-iframes/blank-page.html?response=fail");
            /*
            Log.d("not encoded", ENDPOINT.replaceAll("api/2/",
                    "") + "util-iframes/blank-page.html?response=success");
            Log.d("encoded", URLEncoder.encode(ENDPOINT.replaceAll("api/2/",
                    "") + "util-iframes/blank-page.html?response=success", "UTF-8"));
            */

            String outputString = user + password +
                    URLEncoder.encode(ENDPOINT.replaceAll("api/2/", "") + "util-iframes/blank-page.html?response=success", "UTF-8") +
                    URLEncoder.encode(ENDPOINT.replaceAll("api/2/", "") + "util-iframes/blank-page.html?response=fail", "UTF-8");

            OutputStream output = urlConnection.getOutputStream();
            output.write(outputString.getBytes());

            Log.d("ResponseMessage", urlConnection.getResponseMessage());

            /*
            for (Map.Entry<String, List<String>> header : urlConnection.getHeaderFields().entrySet()) {
                Log.d("key=value", header.getKey() + "=" + header.getValue());
            }
            */

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return authKey;
    }
}