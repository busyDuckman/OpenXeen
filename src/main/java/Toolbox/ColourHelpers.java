package Toolbox;

import java.awt.*;
import java.text.ParseException;

/**
 * Created by duckman on 21/05/2016.
 */
public class ColourHelpers
{
    public static String RGB2String(Color c)
    {
        return c.getRed() + ", " + c.getGreen() + ", " + c.getBlue();
    }

    public static Color String2RGB(String s) throws Exception {
        try
        {
            if(s != null)
            {
                String[] tokens = s.split("\\,");
                if(tokens.length == 3)
                {
                    int r = Integer.parseInt(tokens[0]);
                    int g = Integer.parseInt(tokens[1]);
                    int b = Integer.parseInt(tokens[2]);

                    return new Color(r,g,b);
                }
            }
        }
        catch(Exception ex)
        {
        }

        throw new Exception("Unable to parse colour");
    }


}
