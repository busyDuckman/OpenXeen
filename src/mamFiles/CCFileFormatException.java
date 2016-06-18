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

    public static void assertFalse(boolean test) throws CCFileFormatException
    {
        if(test)
        {
            throw new CCFileFormatException("Test failed");
        }
    }

    public static void assertTrue(boolean test) throws CCFileFormatException
    {
        if(!test)
        {
            throw new CCFileFormatException("Test failed");
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

    public static void assertFalse(boolean test, String name, String key) throws CCFileFormatException
    {
        if(test)
        {
            throw new CCFileFormatException("Test failed, loading " + makeSafe(name) + " from " + makeSafe(key));
        }
    }

    public static void assertTrue(boolean test, String name, String key) throws CCFileFormatException
    {
        if(!test)
        {
            throw new CCFileFormatException("Test failed, loading " + makeSafe(name) + " from " + makeSafe(key));
        }
    }

    private static String makeSafe(String s)
    {
        return (s==null) ? "NULL" : s;
    }

    /**
     * Often CCFileFormatException is thrown at a file processing level where the filename is not available.
     * This helper allows methods higher up the chain to tag parsing errors with the originating file name.
     */
    public static CCFileFormatException WrapWithFilename(CCFileFormatException ex, String fileName)
    {
        return new CCFileFormatException("Error loading file " +
                                            makeSafe(fileName) +
                                            " because: " +
                                            makeSafe(ex.getMessage()), ex);
    }
}
