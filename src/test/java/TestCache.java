import com.github.benmanes.caffeine.cache.Cache;
import com.weather.DAO.Forcast;
import com.weather.util.CachedWeatherStorage;
import junit.framework.Assert;
import org.junit.Test;

public class TestCache {

    @Test
    public void putToCacheTest(){
        CachedWeatherStorage cache = new CachedWeatherStorage();
        Forcast forcast = new Forcast();

        cache.putToCache("key", forcast);
        Assert.assertEquals(cache.getFromCache("key").getTemp(), 1.1);
    }

    @Test
    public void putToCacheWithDeleyTest() throws InterruptedException {
        CachedWeatherStorage cache = new CachedWeatherStorage(1);
        Forcast forcast = new Forcast();
        forcast.setTemp(1.1);
        cache.putToCache("key", forcast);
        Thread.sleep(3000);
        Assert.assertNull(cache.getFromCache("key"));
    }

}
