package com.weather.util;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.weather.DAO.Forcast;

import java.util.concurrent.TimeUnit;

public class CachedWeatherStorage {
    public CachedWeatherStorage(){
        expiryInMills = Integer.parseInt(Configurations.getProperties().get("cache_expire_time_sec").toString());
    }
    // overloaded constructor to have the ability to change cache expiration time
    public CachedWeatherStorage(int expiryInMills){
        this.expiryInMills = expiryInMills;
    }





    // build a self expiring cache
    int expiryInMills;
    Cache<String, Forcast> cache = Caffeine.newBuilder()
            .expireAfterWrite(expiryInMills, TimeUnit.SECONDS)
            .build();



    public void putToCache(String key, Forcast value) {
        cache.put(key, value);
    }

    public Forcast getFromCache(String key) {
        Forcast result;
        if (cache.getIfPresent(key) != null) {
            result = cache.getIfPresent(key);
        }
        return null;
    }
}
