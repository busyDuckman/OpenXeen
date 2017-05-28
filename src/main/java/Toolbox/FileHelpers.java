package Toolbox;

import org.apache.commons.io.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by duckman on 15/05/2016.
 *
 * Somehow java does not like 'one-liner' file operations.
 * Also the set of standard file operations is spread across 5 different packages.
 * So I made this class to keep my sanity.
 */
public class FileHelpers {
    public static boolean fileExists(String path) {
        if(path == null) {
            return false;
        }

        File f = new File(path);
        return (f.exists() && !f.isDirectory());
    }

    public static boolean fileExists(Path path) {
        File f = path.toFile();
        return (f.exists() && !f.isDirectory());
    }

    public static String readAllText(String path) {
        try {
            byte[] data = Files.readAllBytes(Paths.get(path));
            return new String(data);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String[] readAllLines(String path) {
        return readAllLines(Paths.get(path));
    }

    public static String[] readAllLines(Path path) {
        if (fileExists(path)) {
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(path.toFile()));

                String str;

                List<String> list = new ArrayList<String>();
                while ((str = in.readLine()) != null) {
                    list.add(str);
                }

                return list.toArray(new String[0]);
            } catch (FileNotFoundException e) {
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        return null;
    }

    /**
     * Often the file is not quite where you looked for it.
     * @param idealURL
     * @param ignoreCase Find any file with the same name in the path (path case remains intact).
     * @param unpackFromResources
     * @return
     */
    public static String resolveFile(String idealURL, boolean ignoreCase, boolean unpackFromResources) {
        if(idealURL == null) {
            return null;
        }

        if(fileExists(idealURL)) {
            return idealURL;
        }

        final String nameOnly = getFileName(idealURL);

        if(ignoreCase) {
            try {
                Optional<Path> filePath = Files.walk(Paths.get(getParentDirectory(idealURL)))
                        .filter(P -> getFileName(P.toString()).equalsIgnoreCase(nameOnly))
                        .findFirst();
                if(filePath.isPresent()) {
                    return filePath.get().toString();
                }

            } catch (IOException e) {
                return null;
            }
        }

        if(unpackFromResources) {
            InputStream res = tryGetResourceFileStream(nameOnly);
            if(res != null) {
                try {
                    byte[] data = IOUtils.toByteArray(res);
                    if(data.length > 0) {
                        if(FileHelpers.saveBytes(idealURL, data)) {
                            return fileExists(idealURL) ? idealURL : null;
                        }
                    }
                } catch (IOException e) {
                    return null;
                }

            }
        }

        return null;
    }

    /**
     * Gets a file out of resources.
     * @param fileName
     * @return An input stream, or null on error.
     */
    public static InputStream tryGetResourceFileStream(String fileName) {
        return FileHelpers.class.getResourceAsStream("/"+fileName);
    }

    /**
     * Gets a file out of resources.
     * @param fileName
     * @return An array of bytes, or null on error.
     */
    public static byte[] tryGetResourceFile(String fileName) {
        InputStream res = tryGetResourceFileStream(fileName);
        if(res != null) {
            try {
                return IOUtils.toByteArray(res);
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    public static String getParentDirectory(String path)
    {
        if(isNullOrWhitespace(path))
        {
            return "";
        }

        int endParent = Math.max(path.lastIndexOf('\\'), path.lastIndexOf('/'));
        while (endParent == (path.length()-1))
        {
            if(path.length() <= 1)
            {
                return "";
            }
            path = path.substring(0, path.length()-2);
            endParent = Math.max(path.lastIndexOf('\\'), path.lastIndexOf('/'));
        }

        return (endParent > 0) ? path.substring(0, endParent) : "";
    }



    public static String getFileName(String path)
    {
        if(path == null)
        {
            return "";
        }
        else if(path.endsWith("/") || path.endsWith("\\"))
        {
            return ""; //eg c:\foo\  indicates no file...
        }
        return new File(path).getName();
    }

    public static String getFileExtension(String path)
    {
        if(path == null)
        {
            return "";
        }

        //respect our own rules for what constitutes a file name...
        String name = getFileName(path);
        if(name.isEmpty())
        {
            return "";
        }

        return com.google.common.io.Files.getFileExtension(name);
    }

    public static boolean hasExtension(String path)
    {
        String str = getFileName(path);
        if(str != null)
        {
            return str.contains(".");
        }
        return  false;
    }

    public static String getFileNameNoExtension(String path)
    {
        if(path == null)
        {
            return "";
        }

        //enforce our rules regarding what is a filename.
        String filename = getFileName(path);
        return com.google.common.io.Files.getNameWithoutExtension(filename);
    }

    public static String join(String pathA, String pathB)
    {
        if(isNullOrWhitespace(pathA))
        {
            return nullSafeTrim(pathB);
        }
        if(isNullOrWhitespace(pathB))
        {
            return nullSafeTrim(pathA);
        }

        boolean pathAEndsWithSeperator = isPathSeperator(pathA.charAt(pathA.length()-1));
        boolean pathBStartsWithSeperator = isPathSeperator(pathB.charAt(0));

        if(pathAEndsWithSeperator && pathBStartsWithSeperator)
        {
            //easy, pathA wins
            return pathA + pathTrimStart(pathB);
        }
        else if(!(pathAEndsWithSeperator || pathBStartsWithSeperator))
        {
            //no separator, divine from context
            String firstB = firstSeperatorUsed(pathB);
            String lastA = lastSeperatorUsed(pathA);
            if(!lastA.isEmpty())
            {
                return pathA + lastA + pathB; //use the later paths notation
            }
            else if(!firstB.isEmpty())
            {
                return pathA + firstB + pathB; //use the later paths notation
            }
            else
            {
                return pathA + systemPathSeperator() + pathB; //assume native file system
            }
        }
        else
        {
            //easy, byo separator
            return pathA + pathB;
        }


//        File file1 = new File(pathA);
//        File file2 = new File(file1, pathB);
//        return file2.getPath();
    }

    private static String lastSeperatorUsed(String s) {
        if(s == null)
        {
            return "";
        }
        int fsPos = s.lastIndexOf('/');
        int bsPos = s.lastIndexOf('\\');

        if((fsPos < 0) && (bsPos < 0))
        {
            return "";
        }

        return (fsPos > bsPos) ? "/" : "\\";
    }

    private static String firstSeperatorUsed(String s)
    {
        if(s == null)
        {
            return "";
        }
        int fsPos = s.indexOf('/');
        int bsPos = s.indexOf('\\');

        if((fsPos < 0) && (bsPos < 0))
        {
            return "";
        }

        return (fsPos < bsPos) ? "/" : "\\";
    }

    public static String systemPathSeperator()
    {
        return File.separator;
    }

    public static boolean ensurePathExists(String path)
    {
        File ffs = new File(path);
        //total bug here if file and folder of same name...
        // but just can't be bothered babysitting java tonight
        if(!ffs.exists())
        {
            return ffs.mkdirs(); //will return false if folder exists
        }
        return true; //folder already exists
    }

    public static boolean saveText(String text, String path)
    {
        try(PrintWriter out = new PrintWriter(path))
        {
            out.println( text );
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static String getFileNameTillFirstDot(String path)
    {
        return getFileNameTillFirst(path, '.');
    }

    public static String getFileNameTillFirst(String path, char c)
    {
        if(path == null)
        {
            return "";
        }
        String fileName = getFileName(path);
        int pos = fileName.indexOf(c);
        return (pos >= 0) ? fileName.substring(0, pos) : fileName;
    }

    public static boolean saveBytes(String path, byte[] data)
    {
        try {
            Files.write(Paths.get(path), data);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static byte[] readAllBytes(String path)
    {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getAbsolutePath(String filePath) {
        File f = new File(filePath);
        try {
            return f.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String changeExtesion(String file, String extension) {
        String path = getParentDirectory(file);
        String name = getFileNameNoExtension(file);

        if((extension == null) || extension.trim().isEmpty())
        {
            //no extension
            return join(path, name);
        }
        else
        {
            //new extension
            extension = extension.replaceAll("\\.", "").trim();
            return join(path, name + '.' + extension);
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    // helpers
    //------------------------------------------------------------------------------------------------------------------

    private static boolean isPathSeperator(char c) {
        return "/\\".indexOf(c) >= 0;
    }

    private static String pathTrimStart(String s)
    {
        if(isNullOrWhitespace(s))
        {
            return "";
        }

        while (isPathSeperator(s.charAt(0)))
        {
            //The paramater is endIndex... but the code below is buggy?
            //s = s.substring(1, s.length()-1);

            //this works, is the index exclusive? ... must evey java function involve a trip to doco land?
            s = s.substring(1, s.length());
            if(isNullOrWhitespace(s))
            {
                return "";
            }
        }

        return s;
    }

    private static String pathTrimEnd(String s)
    {
        if(isNullOrWhitespace(s))
        {
            return "";
        }

        while (isPathSeperator(s.charAt(s.length()-1)))
        {
            s = s.substring(0, s.length()-2);
            if(isNullOrWhitespace(s))
            {
                return "";
            }
        }

        return s;
    }

    private static boolean isNullOrWhitespace(String s) {
        return (s == null) || (s.trim().isEmpty());
    }

    private static String nullSafeTrim(String s)
    {
        return (s == null) ? "" : s.trim();
    }
}
