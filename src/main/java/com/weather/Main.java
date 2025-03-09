package com.weather;

import com.weather.DAO.Forcast;
import com.weather.DAO.Location;
import com.weather.util.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Location loc = new Location();
        LocationAPI locApi = new LocationAPI();
        WeatherAPI weatherApi = new WeatherAPI();
        String address = "";
        Scanner scanner = new Scanner(System.in);
        CachedWeatherStorage storage = CachedWeatherStorage.getInstance();
        boolean fromStorage = false;
        System.out.print("Type exit for program end program \n");
        while (true){

            System.out.print("Please enter address: ");
            address = scanner.nextLine();
            if (address.equals("exit")){
                System.exit(0);
            }

            // check the cache for forecast by zip
            String zip = ZipExtractor.extractZipCode(address);
            Forcast forcast;
            Forcast forcastFromCache = null;
            if (zip != null) {
                loc.setZipcode(zip);
                forcastFromCache = storage.getFromCache(zip);
            }
            if (forcastFromCache != null) {
                forcast = forcastFromCache;
                fromStorage = true;
            }else{
                loc.setLocation(address);
                locApi.getCoordinatesByLocation(loc);
                if (loc.getLatitude() == null || loc.getLongitude() == null) {
                    System.out.println("No location found for this address. Please try again");
                    continue;
                }
                forcast = weatherApi.getWeather(loc);
                fromStorage = false;
            }
            DegreeConvertor deConv = new DegreeConvertor();
            double ferTemp = deConv.convertKelToFar(forcast.getTemp());
            double highTemp = deConv.convertKelToFar(forcast.getHighTmp());
            double lowTemp = deConv.convertKelToFar(forcast.getLowTmp());
            // there are 20+ other measures in the result coming from the API and they can be added the same way.
            System.out.print("Forecast for address \033[1m" + loc.getLocation() + ":\n"
                    + "Temp: " + Math.round(ferTemp) + " F°\n"
                    + "High: " + Math.round(highTemp) + " F°\n"
                    + "Low: " + Math.round(lowTemp) + " F°\n");
            if (fromStorage){
                System.out.println(" (This forecast was taken from cache)");
            }
        }












    }
}