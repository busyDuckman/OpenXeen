package Toolbox;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.ByteArrayInputStream;

/**
 * Created by duckman on 2/07/2016.
 */
public class BinaryHelpersTest {

    @Test
    public void LSL() throws Exception {
        Assert.assertEquals(200, BinaryHelpers.LSL((byte)100, 1));
        Assert.assertEquals(0, BinaryHelpers.LSL((byte)0, 3));
    }

    @Test
    public void INT() throws Exception {
        Assert.assertEquals(255, BinaryHelpers.INT((byte)-1));
    }

    @Test
    public void BYTES2INT_LSB() throws Exception {
        Assert.assertEquals(0x907e564f, BinaryHelpers.BYTES2INT_lsb((byte)0x4f, (byte)0x56, (byte)0x7e, (byte)0x90));
        Assert.assertEquals(0x7e564f, BinaryHelpers.BYTES2INT_lsb((byte)0x4f, (byte)0x56, (byte)0x7e));
        Assert.assertEquals(0x7e4f, BinaryHelpers.BYTES2INT_lsb((byte)0x4f, (byte)0x7e));
    }

    @Test
    public void BYTES2INT_MSB() throws Exception {
        Assert.assertEquals(0x4f567e90, BinaryHelpers.BYTES2INT_msb((byte)0x4f, (byte)0x56, (byte)0x7e, (byte)0x90));
        Assert.assertEquals(0x4f567e, BinaryHelpers.BYTES2INT_msb((byte)0x4f, (byte)0x56, (byte)0x7e));
        Assert.assertEquals(0x4f7e, BinaryHelpers.BYTES2INT_msb((byte)0x4f, (byte)0x7e));

        // Just putting in a sanity test, to check that This byte
        // order tracks with java ways of doing things (ARGB)
        Color c = new Color(32,64,96, 210);
        Color c2 = new Color(BinaryHelpers.BYTES2INT_msb((byte)c.getAlpha(),
                (byte)c.getRed(),
                (byte)c.getGreen(),
                (byte)c.getBlue()), true);
        Assert.assertEquals(c, c2);

    }

    @Test
    public void byte2Bits() throws Exception {
        boolean[] mask = BinaryHelpers.byte2Bits((byte)0b10011100);
        Assert.assertEquals(false, mask[0]);
        Assert.assertEquals(false, mask[1]);
        Assert.assertEquals(true,  mask[2]);
        Assert.assertEquals(true,  mask[3]);
        Assert.assertEquals(true,  mask[4]);
        Assert.assertEquals(false, mask[5]);
        Assert.assertEquals(false, mask[6]);
        Assert.assertEquals(true,  mask[7]);
    }


    @Test
    public void readBYTEs() throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(new byte[]{
                0x2f, 0x13, 0x7e, 0x56, 0x72, 0x12, 0x27, 0x0
        });
        int[] data = BinaryHelpers.readBYTEs(bis, 8);

        Assert.assertEquals(0x2f, data[0]);
        Assert.assertEquals(0x13, data[1]);
        Assert.assertEquals(0x7e, data[2]);
        Assert.assertEquals(0x56, data[3]);
        Assert.assertEquals(0x72, data[4]);
        Assert.assertEquals(0x12, data[5]);
        Assert.assertEquals(0x27, data[6]);
        Assert.assertEquals(0x0,  data[7]);
    }

    @Test
    public void readWORDs() throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(new byte[]{
                0x2f,0x13, 0x7e,0x56, 0x72,0x12, 0x27,0x0
        });
        int[] data = BinaryHelpers.readWORDs(bis, 4);

        Assert.assertEquals(0x2f13, data[0]);
        Assert.assertEquals(0x7e56, data[1]);
        Assert.assertEquals(0x7212, data[2]);
        Assert.assertEquals(0x2700, data[3]);
    }

    @Test
    public void readWORDsLSB() throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(new byte[]{
                0x2f,0x13, 0x7e,0x56, 0x72,0x12, 0x27,0x0
        });
        int[] data = BinaryHelpers.readWORDsLSB(bis, 4);

        Assert.assertEquals(0x132f, data[0]);
        Assert.assertEquals(0x567e, data[1]);
        Assert.assertEquals(0x1272, data[2]);
        Assert.assertEquals(0x0027, data[3]);
    }


    @Test
    public void readDWORDs() throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(new byte[]{
                0x2f,0x13,0x7e,0x56, 0x72,0x12,0x27,0x0
        });
        long[] data = BinaryHelpers.readDWORDs(bis, 2);

        Assert.assertEquals(0x2f137e56, data[0]);
        Assert.assertEquals(0x72122700, data[1]);
    }

    @Test
    public void readDWORDsLSB() throws Exception {
        ByteArrayInputStream bis = new ByteArrayInputStream(new byte[]{
                0x2f,0x13,0x7e,0x56, 0x72,0x12,0x27,0x0
        });
        long[] data = BinaryHelpers.readDWORDsLSB(bis, 2);

        Assert.assertEquals(0x567e132f, data[0]);
        Assert.assertEquals(0x00271272, data[1]);
    }

    @Test
    public void readBinary() throws Exception {

    }

}