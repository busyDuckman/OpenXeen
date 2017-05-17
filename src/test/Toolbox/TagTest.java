package Toolbox;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by duckman on 1/07/2016.
 */
public class TagTest {
    Tag<Integer, String> intTag;
    Tag<String, Integer> stringTag;
    Tag<DateTime, String> dateTag;

    @Before
    public void setUp() throws Exception {
        intTag = new Tag<>(2, "two");
        stringTag = new Tag<>("VI", 6);
        dateTag = new Tag<>(new DateTime(2005, 3, 26, 12, 0, 0, 0), "some day");
    }

    @Test
    public void getKey() throws Exception {
        Assert.assertEquals((Object)2, intTag.getKey());
    }

    @Test
    public void hashCodeTest() throws Exception {
        Assert.assertEquals(stringTag.hashCode(), "VI".hashCode());
    }

    @Test
    public void equalsTest() throws Exception {
        Assert.assertEquals(dateTag.getKey(), new DateTime(2005, 3, 26, 12, 0, 0, 0));
        Assert.assertEquals(dateTag.getTag().length(), 8);
        Assert.assertTrue(dateTag.equals(new DateTime(2005, 3, 26, 12, 0, 0, 0)));
        Assert.assertTrue(intTag.equals(2));
    }

    @Test
    public void toStringTest() throws Exception {
        Assert.assertEquals(dateTag.toString(), new DateTime(2005, 3, 26, 12, 0, 0, 0).toString());
    }

}