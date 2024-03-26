package com.ororura.backpack.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Api {
    private static final Logger logger = Logger.getLogger(Api.class.getName());

    public static String fetchDataFromApi(String urlApi) {
        StringBuilder response = new StringBuilder();
        try {
            URL url = new URL(urlApi);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error fetching data from API: " + e.getMessage(), e);
        }
        return response.toString();
    }
}
