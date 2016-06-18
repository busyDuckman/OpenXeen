package mamFiles;

/**
 * Created by duckman on 7/05/2016.
 */
public class CCFileFormatException extends Exception
{
    public CCFileFormatException() {
    }

    public CCFileFormatException(String message) {
        super(message);
    }

    public CCFileFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public CCFileFormatException(Throwable cause) {
        super(cause);
    }

    public CCFileFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    @Override
    public String toString() {
        return "CCFile Format Error " + super.toString();
    }


    public static CCFileFormatException fromBadHeader(CCFileReader file, String s)
    {
        return new CCFileFormatException("Invalid File Header, " + s);
    }

    public static CCFileFormatException fromBadReadLength(CCFileReader file, int readLen, int expectedLen) {
        if (readLen  == -1)
        {
            return new CCFileFormatException("End of file reached.");
        }
        else if(readLen < -1)
        {
            return new CCFileFormatException("Unspecified error reading file (" + readLen + ")");
        }
        else if (readLen < expectedLen)
        {
            return new CCFileFormatException("Error reading file, no more data available.");
        }
        else if (readLen > expectedLen)
        {
            return new CCFileFormatException("Error reading file (hmm, Java's fault).");
        }
        return new CCFileFormatException("Error reading file (hmm, no fault).");
    }

    public static CCFileFormatException fromIOError(String message)
    {
        return new CCFileFormatException("IO ERROR: " + message);
    }

    public static void assertFalse(boolean test, String desc) throws CCFileFormatException
    {
        if(test)
        {
            throw new CCFileFormatException("assertFalse failed: " + desc);
        }
    }

    public static void assertTrue(boolean test, String desc) throws CCFileFormatException
    {
        if(!test)
        {
            throw new CCFileFormatException("assertTrue failed: " + desc);
        }
    }

    public static CCFileFormatException fromMissingFile(String file, CCFileReader reader) {
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

    public static void assertFalse_WhenLoadingFrom(boolean test, String loading, String from) throws CCFileFormatException
    {
        if(test)
        {
            throw new CCFileFormatException("assertFalse failed, loading " + makeSafe(loading) + " from " + makeSafe(from));
        }
    }

    public static void assertTrue_WhenLoadingFrom(boolean test, String loading, String from) throws CCFileFormatException
    {
        if(!test)
        {
            throw new CCFileFormatException("assertTrue failed, loading " + makeSafe(loading) + " from " + makeSafe(from));
        }
    }

    private static String makeSafe(Object obj)
    {
        return (obj==null) ? "NULL" : ((obj instanceof String) ? (String)obj : makeSafe(obj.toString()));
    }

    /**
     * Often CCFileFormatException is thrown at a file processing level where the filename is not available.
     * This helper allows methods higher up the chain to tag parsing errors with the originating file name.
     */
    public static CCFileFormatException wrapWithFilename(CCFileFormatException ex, String fileName)
    {
        return new CCFileFormatException("Error loading file " +
                                            makeSafe(fileName) +
                                            " because: " +
                                            makeSafe(ex.getMessage()), ex);
    }

    public static CCFileFormatException wrapWith(CCFileFormatException ex, MaMSprite sprite) {
        return new CCFileFormatException("Error working with sprite " +
                makeSafe(sprite) +
                " because: " +
                makeSafe(ex.getMessage()), ex);
    }

    public static void throwUnloadableProxy(String details) throws CCFileFormatException {
        throw new CCFileFormatException("Error loading proxy: " + details);
    }
}
