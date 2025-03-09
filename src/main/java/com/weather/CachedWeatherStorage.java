package com.weather;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.weather.DAO.Forcast;
import com.weather.util.Configurations;

import java.util.concurrent.TimeUnit;

public class CachedWeatherStorage {
    private CachedWeatherStorage(){
    }

    public static CachedWeatherStorage instance;

    public static synchronized CachedWeatherStorage getInstance() {
        if (instance == null) {
            instance = new CachedWeatherStorage();
        }
        return instance;
    }


    // build a self expiring cache
    int expiryTime = Integer.parseInt(Configurations.getProperties().get("cache_expire_time_sec").toString());
    Cache<String, Forcast> cache = Caffeine.newBuilder()
            .expireAfterWrite(expiryTime, TimeUnit.SECONDS)
            .build();



    public void putToCache(String key, Forcast value) {
        cache.put(key, value);
    }

    public Forcast getFromCache(String key) {
        Forcast result;
        if (cache.getIfPresent(key) != null) {
            return cache.getIfPresent(key);
        }
        return null;
    }
}
