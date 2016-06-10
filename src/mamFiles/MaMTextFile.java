package mamFiles;

import Toolbox.FileHelpers;
import Toolbox.ImageHelpers;
import mamFiles.MAMFile;

/**
 * Created by duckman on 21/05/2016.
 */
public class MaMTextFile extends MAMFile
{
    String text;

    public MaMTextFile(String name, byte[] data)
    {
        super(name);
        text = new String(data);
    }

    public MaMTextFile(String name, String data)
    {
        super(name);
        text = new String(data.toCharArray());
    }

    public String getText() {
        return text;
    }

    @Override
    public String suggestProxyFileName() {
        return name+".txt";
    }

    @Override
    public boolean saveProxy(String path) {
        return FileHelpers.saveText(text, path);
    }

    public static MaMTextFile fromTextFile(String path)
    {
        return new MaMTextFile(FileHelpers.getFileNameTillFirstDot(path), FileHelpers.readAllText(path));
    }
}
