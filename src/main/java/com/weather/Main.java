package com.weather;

import com.weather.DAO.Forcast;
import com.weather.DAO.Location;
import com.weather.util.CachedWeatherStorage;
import com.weather.util.DegreeConvertor;
import com.weather.util.LocationAPI;
import com.weather.util.WeatherAPI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Location loc = new Location();
        LocationAPI locApi = new LocationAPI();
        WeatherAPI weatherApi = new WeatherAPI();
        String address = "One Apple Park Way, Cupertino, CA 95014";
        loc.setLocation(address);

        locApi.getCoordinatesByLocation(loc);
        Forcast forcast = weatherApi.getWeather(loc);
        DegreeConvertor deConv = new DegreeConvertor();
        Double ferTemp = deConv.convertKelToFar(Double.valueOf(forcast.getTemp()));

        // cache the result
        CachedWeatherStorage storage = new CachedWeatherStorage();


        System.out.println("The temperature in " + address + " is " + ferTemp + " FARENHEIT"); ;






    }
}