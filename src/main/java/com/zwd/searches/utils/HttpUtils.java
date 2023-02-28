package com.zwd.searches.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class HttpUtils {
    public static String httpGet(String url) throws Exception {
        StringBuilder builder = new StringBuilder();
        URL httpUrl = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) httpUrl.openConnection();
        connection.setRequestMethod("GET");
        connection.setReadTimeout(5 * 1000);
        connection.connect();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            try (InputStream in = connection.getInputStream(); BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"))) {
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    builder.append(temp);
                    builder.append("\r\n");
                }
            } finally {
                connection.disconnect();
            }
        }
        return builder.toString();
    }
}
