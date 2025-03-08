import com.weather.DAO.Location;
import com.weather.util.LocationAPI;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestLocationAPI {

    @Test
    public void testCoordinatesByLocation() {
        Location loc = new Location();
        LocationAPI locApi = new LocationAPI();
        loc.setLocation("One Apple Park Way, Cupertino, CA 95014");
        locApi.getCoordinatesByLocation(loc);

        assertEquals(loc.getLatitude().toString(), "37.331643");
        assertEquals(loc.getLongitude().toString(), "-122.0117562");
    }
}
