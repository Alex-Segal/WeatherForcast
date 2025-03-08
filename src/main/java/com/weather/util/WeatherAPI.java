package com.weather.util;
import com.weather.DAO.Forcast;
import com.weather.DAO.Location;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class WeatherAPI {

    // This API returns temperature in Kelvin. Need conversion.
    public Forcast getWeather(Location loc) throws IOException, JSONException {
        //get configs
        Properties props = Configurations.getProperties();
        String apiKey = props.getProperty("weather_api_key");
        String apiKeyTag = props.getProperty("weather_api_key_tag");
        String baseUrl = props.getProperty("weather_base_url");

        // params for API call
        Map<String, String> params = new HashMap<>();
        params.put("lat", loc.getLatitude().toString());
        params.put("lon", loc.getLongitude().toString());

        // Make API Call
        String response = API.callApi(baseUrl, apiKey, apiKeyTag, params);
        JSONObject jsonObject = new JSONObject(response).getJSONObject("main");

        // return forecast output
        Forcast forcast = new Forcast();
        forcast.setTemp(jsonObject.getDouble("temp"));
        forcast.setHighTmp(jsonObject.getDouble("temp_max"));
        forcast.setLowTmp(jsonObject.getDouble("temp_min"));

        // store in cache
        CachedWeatherStorage storage = new CachedWeatherStorage();
        storage.putToCache(loc.getZipcode(), forcast);

        return forcast;



    }
}
