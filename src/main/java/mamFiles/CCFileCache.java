package mamFiles;

import Game.Map.MaMWorld;
import Toolbox.LRUCache;

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
    public interface FuncDecodeMaMFile<R extends MAMFile> {
        R apply(String name, String key, byte[] data) throws CCFileFormatException;
    }

    @FunctionalInterface
    public interface FuncDecodeMaMFileWith<R extends MAMFile, T> {
        R apply(String name, String key, byte[] data, T with) throws CCFileFormatException;
    }

    @FunctionalInterface
    public interface FuncDecodeMaMMaze<R extends MaMMazeFile> {
        R apply(String name, String key, byte[] data, MaMWorld world, int mazeID) throws CCFileFormatException;
    }

    @FunctionalInterface
    public interface GetMaMFile<R extends MAMFile> {
        R apply() throws CCFileFormatException;
    }

    private boolean enabled = true;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void flush()
    {
        fileCache.clear();
    }

    public final <T extends MAMFile> T cachedGetFile(MaMCCFileReader reader,
                                                     int id,
                                                     FuncDecodeMaMFile<T> getFunc)
            throws CCFileFormatException
    {
        String key = MAMFile.generateKeyFromCCFile(id, reader);
        return cachedGetFile(key, () -> getFunc.apply(reader.getNameForID(id), key, reader.getFileRaw(id)));
    }

    public final <T extends MAMFile, W> T cachedGetFile(MaMCCFileReader reader,
                                                                        int id,
                                                                        W with,
                                                                        FuncDecodeMaMFileWith<T, W> getFunc)
            throws CCFileFormatException
    {
        String key = MAMFile.generateKeyFromCCFile(id, reader);
        return cachedGetFile(key, () -> getFunc.apply(reader.getNameForID(id), key, reader.getFileRaw(id), with));
    }

    public final <T extends MaMMazeFile> T cachedGetFile(MaMCCFileReader reader,
                                                        int id,
                                                        MaMWorld world,
                                                        int mazeID,
                                                        FuncDecodeMaMMaze<T> getFunc)
            throws CCFileFormatException
    {
        String key = MAMFile.generateKeyFromCCFile(id, reader);
        return cachedGetFile(key, () -> getFunc.apply(reader.getNameForID(id), key, reader.getFileRaw(id), world, mazeID));
    }

    public final <T extends MAMFile> T cachedGetFile(String key,
                                                        GetMaMFile<T> getFunc)
                                            throws CCFileFormatException
    {
        //bypass cache mode
        if(!enabled)
        {
            return getFunc.apply();
        }

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
