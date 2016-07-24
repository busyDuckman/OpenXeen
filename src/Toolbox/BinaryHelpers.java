package Toolbox;

import javafx.beans.binding.StringBinding;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by duckman on 8/05/2016.
 */
public class BinaryHelpers
{
    static boolean _debugMode = false;
    public static int LSL(byte val, int shift)
    {
        return (((int)val) << shift) & 0xff;
    }

    public static int INT(byte val)
    {
        return val & 0xff;
    }


    /**
     * Assemble bytes into an integer, using LSB (dos/pc) byte order
     */
    public static int BYTES2INT_lsb(byte a, byte b)
    {
        return (INT(b) << 8) + INT(a);
    }

    /**
     * Assemble bytes into an integer, using LSB (dos/pc) byte order
     */
    public static int BYTES2INT_lsb(byte a, byte b, byte c)
    {
        return (INT(c) << 16) + (INT(b) << 8) + INT(a);
    }

    /**
     * Assemble bytes into an integer, using LSB (dos/pc) byte order
     */
    public static int BYTES2INT_lsb(byte a, byte b, byte c, byte d)
    {
        return (INT(d) << 24) + (INT(c) << 16) + (INT(b) << 8) + INT(a);
    }

    /**
     * Assemble bytes into an integer, using MSB (network/java) byte order
     */
    public static int BYTES2INT_msb(byte a, byte b)
    {
        return (INT(a) << 8) + INT(b);
    }

    /**
     * Assemble bytes into an integer, using MSB (network/java) byte order
     */
    public static int BYTES2INT_msb(byte a, byte b, byte c)
    {
        return (INT(a) << 16) + (INT(b) << 8) + INT(c);
    }

    /**
     * Assemble bytes into an integer, using MSB (network/java) byte order
     */
    public static int BYTES2INT_msb(byte a, byte b, byte c, byte d)
    {
        return (INT(a) << 24) + (INT(b) << 16) + (INT(c) << 8) + INT(d);
    }


    public static void DebugDumpBinary(byte[] data, String filename)
    {
        if(_debugMode)
        {
            //dump header to file
            try
            {
                System.out.println("Dumping binary: " + filename);
                Files.write(Paths.get(filename), data);
            }
            catch (FileSystemException ex)
            {
                System.out.println("ERROR dumping binary, file in use: " + filename);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * n-th value in the output array is the nth bit of val
     * ie: byte2Bits(5) == [1, 0, 1, 0, 0, 0, 0, 0];
     * or to say byte2Bits(n)[2] is the 3rd LSB of n.
     */
    public static boolean[] byte2Bits(byte value)
    {
        boolean[] bits = new boolean[8];
        shiftOutBits(bits, value, bits.length);
        return bits;
    }

    /**
     * n-th value in the output array is the nth bit of val
     * ie: byte2Bits(5) == [1, 0, 1, 0, 0, 0, 0, 0];
     * or to say byte2Bits(n)[2] is the 3rd LSB of n.
     */
    public static boolean[] byte2Bits(int value)
    {
        boolean[] bits = new boolean[8];
        shiftOutBits(bits, value, bits.length);
        return bits;
    }

    /**
     * n-th value in the output array is the nth bit of val
     * ie: byte2Bits(5) == [1, 0, 1, 0, 0, 0, 0, 0];
     * or to say byte2Bits(n)[2] is the 3rd LSB of n.
     */
    public static boolean[] word2Bits(int value)
    {
        boolean[] bits = new boolean[16];
        shiftOutBits(bits, value, bits.length);
        return bits;
    }

    /**
     * n-th value in the output array is the nth bit of val
     * ie: byte2Bits(5) == [1, 0, 1, 0, 0, 0, 0, 0];
     * or to say byte2Bits(n)[2] is the 3rd LSB of n.
     */
    public static boolean[] dword2Bits(long value)
    {
        boolean[] bits = new boolean[32];
        shiftOutBits(bits, value, bits.length);
        return bits;
    }

    /**
     * n-th value in the output array is the nth bit of val
     * ie: byte2Bits(5) == [1, 0, 1, 0, 0, 0, 0, 0];
     * or to say byte2Bits(n)[2] is the 3rd LSB of n.
     */
    public static boolean[] long2Bits(long value)
    {
        boolean[] bits = new boolean[64];
        shiftOutBits(bits, value, bits.length);
        return bits;
    }


    private static void shiftOutBits(boolean[] bits, long value, int n)
    {
        for(int i=0; i<n; i++)
        {
            bits[i] = (value & 1) != 0;
            value >>>= 1;
        }
    }

    public static String hexEditorView(byte[] data, int pos, int len)
    {
        if(data == null)
        {
            return "";
        }
        pos = Math.max(pos, 0);
        len = Math.max(len, 0);

        StringBuilder sb = new StringBuilder();
        for(int q=0, i=pos; q<len; q++, i++)
        {
            if(i < data.length)
            {
                sb.append(String.format("%02X", (0xFF & data[i])));
            }
            else
            {
                sb.append("  "); //add spaces(to ensure memory dumps look good.
            }

            if(q<(len-1))
            {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    //------------------------------------------------------------------------------------------------------------------
    // ByteInputStream lacked everything I wanted... so as per every other java library I am adding to a toolbox.
    //------------------------------------------------------------------------------------------------------------------

    public enum BinaryDataTypes
    {
        BYTE      (1, false, true),
        S_BYTE    (1, true,  true),
        WORD      (2, false, true),
        S_WORD    (2, true,  true),
        INT24     (3, false, true),
        S_INT24   (3, true,  true),
        DWORD     (4, false, true),
        S_DWORD   (4, true,  true),

        S_BYTE_LSB  (1, true,  false),
        WORD_LSB    (2, false, false),
        S_WORD_LSB  (2, true,  false),
        INT24_LSB   (3, false, false),
        S_INT24_LSB (3, true,  false),
        DWORD_LSB   (4, false, false),
        S_DWORD_LSB (4, true,  false);
        //Not done because it creates headaches.
        //INT64     (8, false),
        //S_INT64   (8, true);

        int bytes;
        boolean signed;
        boolean msb;

        BinaryDataTypes(int bytes, boolean signed, boolean msb) {
            this.bytes = bytes;
            this.signed = signed;
            this.msb = msb;
        }

        public long read(ByteArrayInputStream bis)
        {
            long val = 0;
            if(msb)
            {
                for(int i=0; i<bytes; i++)
                {
                    val <<= 8;
                    val = val | (bis.read() & 0xff);

                }
            }
            else
            {
                for(int i=0; i<bytes; i++)
                {
                    val = val | ((bis.read() & 0xff)<<(i*8));
                }
            }

            if(signed)
            {
                int powN = 1<<(bytes * 8);
                if(val > (powN >> 1))
                {
                    val -= powN;
                }
            }

            return val;
        }
    }

    public static void readWORDs(ByteArrayInputStream bis, int[] dest, int length, int offset)
    {
        for(int i=0; i<length; i++)
        {
            dest[i+offset] = (int) BinaryDataTypes.WORD.read(bis);
        }
    }

    public static void readWORDsLSB(ByteArrayInputStream bis, int[] dest, int length, int offset)
    {
        for(int i=0; i<length; i++)
        {
            dest[i+offset] = (int) BinaryDataTypes.WORD_LSB.read(bis);
        }
    }

    public static int[] readWORDs(ByteArrayInputStream bis, int length)
    {
        int[] data = new int[length];
        readWORDs(bis, data, length, 0);
        return data;
    }

    public static int[] readWORDsLSB(ByteArrayInputStream bis, int length)
    {
        int[] data = new int[length];
        readWORDsLSB(bis, data, length, 0);
        return data;
    }

    public static void readDWORDs(ByteArrayInputStream bis, long[] dest, int length, int offset)
    {
        for(int i=0; i<length; i++)
        {
            dest[i+offset] = BinaryDataTypes.DWORD.read(bis);
        }
    }

    public static void readDWORDsLSB(ByteArrayInputStream bis, long[] dest, int length, int offset)
    {
        for(int i=0; i<length; i++)
        {
            dest[i+offset] = BinaryDataTypes.DWORD_LSB.read(bis);
        }
    }

    public static long[] readDWORDs(ByteArrayInputStream bis, int length)
    {
        long[] data = new long[length];
        readDWORDs(bis, data, length, 0);
        return data;
    }

    public static long[] readDWORDsLSB(ByteArrayInputStream bis, int length)
    {
        long[] data = new long[length];
        readDWORDsLSB(bis, data, length, 0);
        return data;
    }

    public static void readBYTEs(ByteArrayInputStream bis, int[] dest, int length, int offset)
    {
        for(int i=0; i<length; i++)
        {
            dest[i+offset] = (int)BinaryDataTypes.BYTE.read(bis);
        }
    }

    public static int[] readBYTEs(ByteArrayInputStream bis, int length)
    {
        int[] data = new int[length];
        readBYTEs(bis, data, length, 0);
        return data;
    }

    public static void readBinary(ByteArrayInputStream bis, BinaryDataTypes type, long[] dest, int length, int offset)
    {
        for(int i=0; i<length; i++)
        {
            dest[i+offset] = type.read(bis);
        }
    }

    public static long[] readBinary(ByteArrayInputStream bis, BinaryDataTypes type, int length)
    {
        long[] data = new long[length];
        readBinary(bis, type, data, length, 0);
        return data;
    }
}
