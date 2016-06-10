package mamFiles;

/**
 * Created by duckman on 21/05/2016.
 *
 * Indicates and object can be saved to a file type.
 * Implies it can be created from the file too, but FFS Java
 */
public interface IHasProxy
{
    String suggestProxyFileName();

    boolean saveProxy(String path) throws CCFileFormatException;

}
