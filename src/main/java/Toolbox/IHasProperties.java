package Toolbox;

import java.io.*;
import java.util.*;

/**
 * Created by duckman on 28/05/2016.
 */
public interface IHasProperties
{
    boolean getProperties(HProperties p);
    boolean setProperties(HProperties p);

    default boolean saveProperties(String path, String comment)
    {
        try(OutputStream os = new FileOutputStream(path))
        {
            //yes its cool that I can do this in java, but its not cool that I have to.
            HProperties p = new HProperties(){
                @Override
                public synchronized Enumeration<Object> keys() {
                    TreeSet<Object> ts = new TreeSet<>(new NaturalOrderComparator());
                    ts.addAll(super.keySet());
                    return Collections.enumeration(ts);
                }
            };

            if(setProperties(p))
            {
                p.store(os, comment);
                return true;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    default boolean loadProperties(String path)
    {
        HProperties prop = new HProperties();
        try(InputStream is = new FileInputStream(path))
        {
            prop.load(is);
            return getProperties(prop);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
