package Toolbox;

import Rendering.RenderablePos;
import mamFiles.CCFileFormatException;

import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import Rendering.RenderablePos.ScalePosition;

/**
 * Created by duckman on 6/07/2016.
 *
 * Changing this class probably breaks file loads... just saying.
 */
public final class ParseHelpers
{
    public enum ParseErrorBehavior
    {
        RETURN_NULL {
            @Override
            <T> T apply(T obj) {
                return null;
            }
        },
        THROW_EXCEPTION {
            @Override
            <T> T apply(T obj) throws CCFileFormatException {
                throw  new CCFileFormatException("Unable to parse string");
            }
        },
        RETURN_DEFAULT {
            @Override
            <T> T apply(T obj) throws CCFileFormatException {
                return obj;
            }
        };

        abstract <T> T apply(T obj) throws CCFileFormatException;
    }

    private static final String nullToken = "<<NULL>>";
    private static String prefix = "(";
    private static String postfix = ")";

    //------------------------------------------------------------------------------------------------------------------
    // parsers
    //------------------------------------------------------------------------------------------------------------------
    public static String pointToString(Point p)
    {
        return (p != null) ? assemble(new String[]{"x", "y"}, new Object[] {p.x, p.y}) : nullToken;
    }

    public static Point parsePointOrNull(String s)
    {
        if((s == null) || s.trim().equals(nullToken))
        {
            return null;
        }

        Map<String, String> kvpMap = getKeyValueMap(s);
        try
        {
            int x = Integer.parseInt(kvpMap.get("x"));
            int y = Integer.parseInt(kvpMap.get("y"));
            return new Point(x, y);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static String rectangleToString(Rectangle p)
    {
        return (p != null) ? assemble(new String[]{"x", "y", "w", "h"}, new Object[] {p.x, p.y, p.width, p.height}) : nullToken;
    }

    public static Rectangle parseRectangleOrNull(String s)
    {
        if((s == null) || s.trim().equals(nullToken))
        {
            return null;
        }

        Map<String, String> kvpMap = getKeyValueMap(s);
        try
        {
            int x = Integer.parseInt(kvpMap.get("x"));
            int y = Integer.parseInt(kvpMap.get("y"));
            int w = Integer.parseInt(kvpMap.get("w"));
            int h = Integer.parseInt(kvpMap.get("h"));
            return new Rectangle(x, y, w, h);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static String renderablePosToString(RenderablePos p)
    {
        return (p != null) ?
                    assemble(new String[]{"x", "y", "depth", "scale", "scale_pos"},
                        new Object[] {
                                p.getxPos(),
                                p.getyPos(),
                                p.getDepth(),
                                p.getScale(),
                                enumToString(p.getScalePos())
                        })
                : nullToken;
    }

    public static RenderablePos parseRenderablePosOrNull(String s)
    {
        if((s == null) || s.trim().equals(nullToken))
        {
            return null;
        }

        Map<String, String> kvpMap = getKeyValueMap(s);
        try
        {
            int x = Integer.parseInt(kvpMap.get("x"));
            int y = Integer.parseInt(kvpMap.get("y"));
            int depth = Integer.parseInt(kvpMap.get("depth"));
            double scale = Double.parseDouble(kvpMap.get("scale"));
            ScalePosition sPos = parseEnumOrNull(RenderablePos.ScalePosition.class, kvpMap.get("scale_pos"));
            return new RenderablePos(x, y, scale, sPos, depth);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    public static <T extends Enum<T>> String enumToString(T item) {
        return item.name();
    }

    public static <T extends Enum<T>> T parseEnumOrNull(Class<T> clazz, String text) {
        if( clazz != null && text != null ) {
            try {
                return Enum.valueOf(clazz, text.trim());
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Creation and parsing of lists of key/value pairs.
    //------------------------------------------------------------------------------------------------------------------
    private static String assemble(String[] names, Object[] values)
    {
        StringBuffer sb = new StringBuffer();
        appendIfNotNull(sb, prefix);

        for (int i=0; i<values.length; i++)
        {
            if(names != null)
            {
                appendIfNotNull(sb, names[i]);
                sb.append("=");
            }
            appendOrLabelNull(sb, values[i]);
        }

        appendIfNotNull(sb, postfix);
        return sb.toString();
    }

    private static Map<String, String> getKeyValueMap(String text)
    {
        Map<String, String> map = new HashMap<>();

        //prepare string
        text = text.trim();
        text = text.startsWith(prefix) ? text.substring(prefix.length()) : text;
        text = text.endsWith(postfix) ? text.substring(0, text.length()-postfix.length()) : text;

        //get key/values
        String[] tokens = text.split(",");
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            int splitPos= token.indexOf("=");
            if((splitPos > 0) && (splitPos<token.length()))
            {
                String key = token.substring(0, splitPos-1).trim();
                String value = token.substring(splitPos+1).trim();
                map.put(key, value);
            }
        }

        return map;
    }

    private static void appendOrLabelNull(StringBuffer sb, Object obj)
    {
        if(obj != null)
        {
            String s = obj.toString();
            if(s != null)
            {
                sb.append(s);
                return;
            }
        }

        //here because a null, or problem was encountered
        sb.append(nullToken);
    }

    private static void appendIfNotNull(StringBuffer sb, Object obj)
    {
        if(obj != null)
        {
            String s = obj.toString();
            if(s != null)
            {
                sb.append(s);
            }
        }
    }
}
