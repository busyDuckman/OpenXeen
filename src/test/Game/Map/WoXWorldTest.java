package Game.Map;

import AAAAAAA.CommonTest;
import mamFiles.CCFileFormatException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by duckman on 13/06/2016.
 */
public class WoXWorldTest extends CommonTest {
    public WoXWorldTest() {
        super(WoXWorldTest.class);
    }

    @Test
    public void makeMazeFileName() throws Exception {
        assertThrown(() ->WoXWorld.makeMazeFileName("foo", "bar", -1), CCFileFormatException.class);
        assertThrown(() ->WoXWorld.makeMazeFileName("foo", "bar", 1000), CCFileFormatException.class);
        Assert.assertEquals("foo0000.bar", WoXWorld.makeMazeFileName("foo", "bar", 0));
        Assert.assertEquals("foo0003.bar", WoXWorld.makeMazeFileName("foo", "bar", 3));
        Assert.assertEquals("foo0099.bar", WoXWorld.makeMazeFileName("foo", "bar", 99));
        Assert.assertEquals("fooX100.bar", WoXWorld.makeMazeFileName("foo", "bar", 100));
        Assert.assertEquals("fooX999.bar", WoXWorld.makeMazeFileName("foo", "bar", 999));
        Assert.assertEquals("0003.bar", WoXWorld.makeMazeFileName(null, "bar", 3));
        Assert.assertEquals("foo0003", WoXWorld.makeMazeFileName("foo", null, 3));
    }

}