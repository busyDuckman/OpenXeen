package Toolbox;

import java.util.Arrays;

/**
 * Created by duckman on 19/06/2016.
 */
public class Misc
{
    /**
     * I like
     *   name = ifNull(someFunctionCall(), "bob")
     * not
     *    name = someFunctionCall();
     *    name = (name != null) ? name : "bob";
     */
    public static <T> T ifNull(T what, T ifNull)
    {
        return (what == null) ? ifNull : what;
    }

    public enum PadType
    {
        PAD_BEFORE, CENTRE_TEXT, PAD_AFTER;
    }

    public static String padZeros(int number, int places)
    {
        return pad(""+number, places, '0', PadType.PAD_BEFORE);
    }

    public static String pad(String s, int places, char padChar, PadType padType)
    {
        if(s == null)
        {
            return repeat(padChar, places);
        }
        if(s.length() >= places)
        {
            return s;
        }

        int padSize = places - s.length();

        switch(padType)
        {
            case PAD_BEFORE:
                return repeat(padChar, padSize) + s;
            case CENTRE_TEXT:
                return repeat(padChar, padSize/2) + s + repeat(padChar, padSize-(padSize/2));
            case PAD_AFTER:
                return s + repeat(padChar, padSize);
        }
        return s;
    }

    public static String repeat(char c, int n)
    {
        if(n < 0) {
            return null;
        }
        if(n == 0) {
            return "";
        }

        char[] reps = new char[n];
        Arrays.fill(reps, c);
        return new String(reps);
    }
}
