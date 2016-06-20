package Toolbox;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * Created by duckman on 19/06/2016.
 *
 * I repeatedly have a problem of finding all objects that are enumerated by a number.
 * Eg incremental file in a directory;
 *
 */
public class BlindIndexMaper
{
    public static<T> Map<Integer, T>findMapping(Function<Integer, T> getItem, int minRange, int maxRange, int maxFailsInARow)
    {
        Map<Integer, T> map = new HashMap<>();
        int missCount=0;

        //sanitise inputs
        maxFailsInARow = Math.max(0, maxFailsInARow);
        if(maxRange < minRange)
        {
            int temp = maxRange;
            maxRange = minRange;
            minRange = temp;
        }

        //explore for items
        for (int i = minRange; (i <= maxRange)&&(missCount<=maxFailsInARow); i++)
        {
            T obj = null;
            try
            {
                obj = getItem.apply(i);
            }
            catch (Exception ex)
            {
                //don't care, obj is still null
            }

            if(obj != null)
            {
                map.put(i, obj);

                //reset miss count
                missCount = 0;
            }
            else if(maxFailsInARow > 0) //maxFailsInARow == 0 means stop on first error.
            {
                missCount++;
            }
        }
        return map;
    }
}
