import com.weather.DAO.Forcast;
import com.weather.CachedWeatherStorage;
import junit.framework.Assert;
import org.junit.Test;
import static org.junit.Assert.assertNull;

public class TestCache {

    @Test
    public void putToCacheTest(){
        CachedWeatherStorage cache = CachedWeatherStorage.getInstance();
        Forcast forcast = new Forcast();
        forcast.setTemp(1.1);

        cache.putToCache("12345", forcast);
        Assert.assertEquals(cache.getFromCache("12345").getTemp(), 1.1);
    }

    @Test
    public void putToCacheWithDeleyTest() throws InterruptedException {
        // change properties file to make the cache remember values for less time than you test for
        CachedWeatherStorage cache = CachedWeatherStorage.getInstance();
        Forcast forcast = new Forcast();
        forcast.setTemp(1.1);
        cache.putToCache("12345", forcast);
        Thread.sleep(3000);
        assertNull(cache.getFromCache("12345"));
    }

}
