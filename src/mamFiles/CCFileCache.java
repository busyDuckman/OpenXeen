package mamFiles;

import Toolbox.LRUCache;
import com.google.common.collect.ClassToInstanceMap;
import com.sun.istack.internal.NotNull;

import java.util.IdentityHashMap;
import java.util.function.IntFunction;

/**
 * Created by duckman on 12/06/2016.
 *
 * A central cache for ccFile objects.
 *
 * I used a singleton, because I feel all ccFiles should be in competition for caching of their
 * contained objects.
 *
 * This class deliberately lacks any file exists type method.
 */
public enum CCFileCache {
    INSTANCE;

    //TODO: Base on MB, not number of files
    private LRUCache<String, MAMFile> fileCache = new LRUCache<>(512);



    @FunctionalInterface
    public interface FuncGetMaMFile<R extends MAMFile> {
        R apply(int key) throws CCFileFormatException;
    }

    @FunctionalInterface
    public interface FuncGetMaMFile2<R extends MAMFile, T extends MAMFile> {
        R apply(int key, T with) throws CCFileFormatException;
    }

    @FunctionalInterface
    public interface GetMaMFile<R extends MAMFile> {
        R apply() throws CCFileFormatException;
    }

    public final <T extends MAMFile> T cachedGetFile(CCFileReader reader,
                                                        int id,
                                                        FuncGetMaMFile<T> getFunc)
            throws CCFileFormatException
    {
        return cachedGetFile(MAMFile.generateKeyFromCCFile(id, reader), () -> getFunc.apply(id));
    }

    public final <T extends MAMFile, W extends MAMFile> T cachedGetFile(CCFileReader reader,
                                                     int id,
                                                     W with,
                                                     FuncGetMaMFile2<T, W> getFunc)
            throws CCFileFormatException
    {
        return cachedGetFile(MAMFile.generateKeyFromJoin(MAMFile.generateKeyFromCCFile(id, reader), with),
                            () -> getFunc.apply(id, with));
    }

    public final <T extends MAMFile> T cachedGetFile(String key,
                                                        GetMaMFile<T> getFunc)
                                            throws CCFileFormatException
    {
        MAMFile file = fileCache.getOrDefault(key, null);
        if(file == null)
        {
            file = getFunc.apply();
            fileCache.put(key, file);
            //System.out.println("Caching: " + key);
        }
        try
        {
            return (T)file;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new CCFileFormatException(ex);
        }
    }


}
