package Toolbox;

import java.util.Properties;

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

    boolean isRoot() {return parent == null;}

    String getFullName()
    {
        return (parent == null) ? name : (parent.getFullName() + seperator + name);
    }

    protected String fullName(String property)
    {
        String path = getFullName();
        return path.isEmpty() ? property : (path + seperator + property);
    }
}
