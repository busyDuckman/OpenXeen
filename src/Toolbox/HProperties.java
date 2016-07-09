package Toolbox;

import java.awt.*;
import java.util.Properties;
import java.util.function.Function;

/**
 * Created by duckman on 13/06/2016.
 *
 * Hierarchical properties.
 *
 * hp.put("name", "Bob");
 * HProperties wifeProp = hp.push("spouse");
 * wifeProp.put("name", "Jane");
 *
 * Will create
 * name = Bbob
 * spouse\name = Jane
 *
 * This is useful to create properties of hierarchical object trees.
 */
public class HProperties extends Properties
{
    final String name;
    final HProperties parent;
    final static String seperator = "\\";

    //------------------------------------------------------------------------------------------------------------------
    // Constructors
    //------------------------------------------------------------------------------------------------------------------
    public HProperties() {
        super();
        name = null;
        parent = null;
    }

    protected HProperties(String name, HProperties parent) {
        super();
        this.name = name;
        this.parent = parent;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Getting and setting of properties and sections
    //------------------------------------------------------------------------------------------------------------------
    public HProperties push(String subName)
    {
        if((subName != null) && (!subName.trim().isEmpty()))
        {
            return new HProperties(subName, this);
        }
        return this;
    }

    public HProperties pop()
    {
        return parent;
    }

    @Override
    public String getProperty(String key) {
        return super.getProperty(fullName(key));
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        return super.getProperty(fullName(key), defaultValue);
    }

    @Override
    public synchronized Object setProperty(String key, String value) {
        return super.setProperty(fullName(key), value);
    }

    //------------------------------------------------------------------------------------------------------------------
    // Data collection helpers
    //------------------------------------------------------------------------------------------------------------------
    public  <T> void setArray(String key, T[] items, Function<T, String> toString)
    {
        setProperty(getCountKey(key), ""+items.length);
        for (int i = 0; i < items.length; i++) {
            T item = items[i];
            String indexKey = getNthKey(key, i);
            String value = toString.apply(item);
            setProperty(indexKey, value);
        }
    }

    public  <T> T[] getArray(String key, Function<String, T> parse, Function<Integer, T[]> javaStupidArrayHack)
    {
        int len = Integer.parseInt(getProperty(getCountKey(key)));

        T[] items = javaStupidArrayHack.apply(len);
        for (int i = 0; i < items.length; i++) {
            String indexKey = getNthKey(key, i);
            String value = getProperty(indexKey, null);
            items[i] = parse.apply(value);
        }

        return items;
    }


    private String getCountKey(String key) {
        return key.trim() + "_COUNT";
    }

    private String getNthKey(String key, int i) {
        // pad with 0's to keep ordering in text file
        // start with _N so that _COUNT is first
        String num = ""+i;
        if(num.length() < 4)
        {
            num = "0000" + num;
            num = num.substring(num.length() - 4);
        }
        return key.trim() + "_N" + num;
    }


    //------------------------------------------------------------------------------------------------------------------
    // Misc
    //------------------------------------------------------------------------------------------------------------------
    protected boolean isRoot() {return parent == null;}

    protected String getFullName()
    {
        return (parent == null) ? name : (parent.getFullName() + seperator + name);
    }

    protected String fullName(String property)
    {
        String path = getFullName();
        return path.isEmpty() ? property : (path + seperator + property);
    }
}
