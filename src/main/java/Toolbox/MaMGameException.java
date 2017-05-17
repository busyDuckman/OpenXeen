package Toolbox;

import mamFiles.CCFileFormatException;
import mamFiles.MaMCCFileReader;

/**
 * Created by duckman on 26/06/2016.
 */
public class MaMGameException extends Exception {
    public MaMGameException(String message) {
        super(message);
    }

    public MaMGameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MaMGameException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaMGameException(Throwable cause) {
        super(cause);
    }

    public static void assertFalse(boolean test, String desc) throws MaMGameException
    {
        if(test)
        {
            throw new MaMGameException("assertFalse failed: " + desc);
        }
    }

    public static void assertTrue(boolean test, String desc) throws MaMGameException
    {
        if(!test)
        {
            throw new MaMGameException("assertTrue failed: " + desc);
        }
    }

    public static CCFileFormatException fromMissingFile(String file, MaMCCFileReader reader) {
        return new CCFileFormatException("Missing file \"" + file + "\" in \"" + reader.getFilePath() + "\"");
    }

    public static void throwFeatureNotReady() throws CCFileFormatException
    {
        throwFeatureNotReady(null);
    }

    public static void throwFeatureNotReady(String message) throws CCFileFormatException
    {
        throw new CCFileFormatException("MaM Feature not developed yet: " + ((message != null) ? message : "(N/A)"));
    }

    public static void notSupposedToBeHere() throws CCFileFormatException
    {
        throw new CCFileFormatException("Code execution reached somewhere it was not supposed to.");
    }

    protected static String makeSafe(Object obj)
    {
        return (obj==null) ? "NULL" : ((obj instanceof String) ? (String)obj : makeSafe(obj.toString()));
    }

    /**
     * This is a stub to allow try blocks to compile even though I had to
     * temporarily comment out the one line of code that needed the block.
     */
    public static void stub() throws CCFileFormatException
    {

    }
}
