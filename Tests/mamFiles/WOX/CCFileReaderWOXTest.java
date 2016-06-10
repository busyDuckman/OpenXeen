package mamFiles.WOX;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by duckman on 8/05/2016.
 */
public class CCFileReaderWOXTest
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
        assertEquals(CCFileReaderWOX._hashFileName("AAZE0002.TXT"), 0x7CA1);
        assertEquals(CCFileReaderWOX._hashFileName("GROUP.VGA"), 0xFF8E);
        assertEquals(CCFileReaderWOX._hashFileName("POW10.ICN"), 0x0062);
    }

}