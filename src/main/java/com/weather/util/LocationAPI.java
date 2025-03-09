package com.weather.util;
import com.weather.DAO.Location;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
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
            String response = CallAPI.callApi(baseUrl, apiKey, apiKeyTag, params);

            // drill down to the data based on the structure of the API response
            // NOTE: in a real application I would spend the time, go over every scenario and put much more null checks for this drill down.
            JSONObject jsonObject = new JSONObject(response);
            JSONArray arr = (JSONArray)jsonObject.get("features");
            if (arr.isEmpty()) {
                return; // no location found
            }
            if (!arr.getJSONObject(0).has("geometry")){
                return; // no location found
            }
            JSONObject geometry = (JSONObject)arr.getJSONObject(0).get("geometry");
            JSONArray coordinates = geometry.getJSONArray("coordinates");

            // get zip code for cache storage
            JSONObject properties = (JSONObject)arr.getJSONObject(0).get("properties");
            String zip = null;
            if (properties.has("postcode")){
                zip = properties.get("postcode").toString();
            }

            if (!jsonObject.isEmpty()) {
                Location location = new Location();
                if (coordinates.get(0) != null){
                    loc.setLongitude(coordinates.get(0).toString());
                }
                if (coordinates.get(1) != null){
                    loc.setLatitude(coordinates.get(1).toString());
                }
                // zip code might exist already, but we will overwrite it with zip code given by the API
                // This will solve the problem if zip was never given by the user
                if (zip != null) {
                    location.setZipcode(zip);
                }
                loc.setLocation(properties.get("formatted").toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
