import com.weather.DAO.Forcast;
import com.weather.DAO.Location;
import com.weather.util.WeatherAPI;
import org.json.JSONException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TestWeatherAPI {


    @Test
    public void testWeatherAPI() throws IOException, JSONException {

        Location loc = new Location();
        loc.setLatitude("37.331643");
        loc.setLongitude("-122.0117562");
        WeatherAPI weatherApi = new WeatherAPI();
        Forcast forcast = weatherApi.getWeather(loc);

        assertNotNull(forcast.getTemp());
    }
}
