package mamFiles;

import Toolbox.FileHelpers;

/**
 * Created by duckman on 21/05/2016.
 */
public class MaMBinaryFile extends MAMFile
{
    byte[] data;

    public MaMBinaryFile(String name, byte[] data)
    {
        super(name);
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String suggestProxyFileName() {
        return name + ".BIN";
    }

    @Override
    public boolean saveProxy(String path) {
        return FileHelpers.saveBytes(path, this.data);
    }

    public MaMBinaryFile fromBinFile(String path)
    {
        byte[] data = FileHelpers.readAllBytes(path);
        return new MaMBinaryFile(FileHelpers.getFileNameTillFirstDot(path), data);
    }
}
