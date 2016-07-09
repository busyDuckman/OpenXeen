package mamFiles;

import Rendering.IRenderableGameObject;
import Toolbox.HProperties;
import Toolbox.IHasProperties;

/**
 * Created by duckman on 7/05/2016.
 *
 * There is an odd concept here: The MaMFile does not store its actual name or file ID.
 * Why:
 *      - I don't know if earlier MaM games have this concept.
 *      - File is a misnomer... Its a game object.
 *      - Some MAMFiles are transformed into something that is different (adding transparency to a pallate)
 *      - The hash system used in the xeen TOC won't scale up.
 */
public abstract class MAMFile implements IHasProxy, IHasProperties
{
    /**
     * Not the name as per the cc file. This is a description.
     * It does not need to be unique.
     */
    protected String name;

    /**
     * This is a string unique to every MaM File.
     */
    protected transient final String key;

    public MAMFile(String name, String key)
    {
        this.name = name;
        this.key = key.intern(); //allow for key to be used in instancemap etc
    }

    public String getName() {
        return name;
    }

    /**
     * This is a string unique to every MaM File.
     */
    public String getKey() {
        return key;
    }

    public static String generateKeyFromCCFile(int id, MaMCCFileReader reader)
    {
        // "C 1234@c:\games\xeen\dark.cc"
        return "C " + id + "@" + reader.filePath;
    }

    //TODO: 1 path != 1 resource, add name of object type or something
    public static String generateKeyFromPath(String path)
    {
        // "P c:\games\xeen\dark_cc_proxy\SKY_files\sky.sky"
        return "P " +  path;
    }

    /**
     * NB a unique key may well not be assosiated with an object bound for the cache.
     * Its more about objects that are modified versions of something else.
     */
    private static transient volatile long __id = 0;
    public synchronized static String generateUniqueKey(String itemName)
    {
        __id++;
        // U[133721@MMM4PAL "set transparency"]
        return  "U " + __id + "@" + itemName;
    }

    public static String generateKeyFromJoin(String key, MAMFile with) {
        return "J " + key + "@WITH " + with.key;
    }

    public static String generateKeyFromWrapper(String wrapperName, MAMFile file) {
        return "W " + file.key + "@IN " + wrapperName;
    }

    //------------------------------------------------------------------------------------------------------------------
    // IHasProperties
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean setProperties(HProperties p)
    {
        //NB: key is not saved
        p.setProperty("MAMFile.name", name);
        return true;
    }

    @Override
    public boolean getProperties(HProperties p)
    {
        this.name = p.getProperty("MAMFile.name");
        return false;
    }
}
