package mamFiles.WOX;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by duckman on 8/05/2016.
 */
public class WOXccFileReaderTest
{
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void hashFileName() throws Exception
    {
        //actual files, with is known hash values
        assertEquals(WOXccFileReader._hashFileName("AAZE0002.TXT"), 0x7CA1);
        assertEquals(WOXccFileReader._hashFileName("GROUP.VGA"), 0xFF8E);
        assertEquals(WOXccFileReader._hashFileName("POW10.ICN"), 0x0062);
    }

}