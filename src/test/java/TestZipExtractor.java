import com.weather.util.ZipExtractor;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestZipExtractor {

    @Test
    public void testZipExtractor() {

        ZipExtractor zipExtractor = new ZipExtractor();
        assertEquals(ZipExtractor.extractZipCode("123 address at city and state country 55555"), "55555");
        assertEquals(ZipExtractor.extractZipCode("123 address at city and state country 55555-2222"), "55555-2222");

    }

}
