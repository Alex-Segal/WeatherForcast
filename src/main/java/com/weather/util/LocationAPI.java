package com.weather.util;
import com.weather.DAO.Location;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public  class LocationAPI {

    public void getCoordinatesByLocation(Location loc) {
        // get properties
        Properties props = Configurations.getProperties();
        String apiKey = props.getProperty("location_api_key");
        String apiKeyTag = props.getProperty("location_api_key_tag");
        String baseUrl = props.getProperty("location_base_url");
        Map<String, String> params = new HashMap<>();

        params.put("text", loc.getLocation()); // text is the variable name the API requests

        try {
            String response = API.callApi(baseUrl, apiKey, apiKeyTag, params);

            // drill down to the data based on the structure of the API response
            JSONObject jsonObject = new JSONObject(response);
            JSONArray arr = (JSONArray)jsonObject.get("features");
            JSONObject geometry = (JSONObject)arr.getJSONObject(0).get("geometry");
            JSONArray coordinates = geometry.getJSONArray("coordinates");

            // get zip code for cache storage
            JSONObject properties = (JSONObject)arr.getJSONObject(0).get("properties");
            String zip = properties.get("postcode").toString();

            if (!jsonObject.isEmpty()) {
                Location location = new Location();
                if (coordinates.get(0) != null){
                    loc.setLongitude(coordinates.get(0).toString());
                }
                if (coordinates.get(1) != null){
                    loc.setLatitude(coordinates.get(1).toString());
                }
                if (!zip.isBlank()) {
                    location.setZipcode(zip);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
