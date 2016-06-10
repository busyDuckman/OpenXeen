package mamFiles;

import Rendering.AnimationSettings;
import Toolbox.IHasProperties;

import java.io.File;
import java.util.Properties;

/**
 * Created by duckman on 7/05/2016.
 */
public abstract class MAMFile implements IHasProxy, IHasProperties
{
    protected String name;
    //protected int fileSize;

    public MAMFile(String name)
    {
        this.name = name;
        //this.fileSize = size;
    }

    public String getName() {
        return name;
    }

    //public int getFileSize() { return fileSize; }

    //------------------------------------------------------------------------------------------------------------------
    // IHasProperties
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean setProperties(Properties p)
    {
        p.setProperty("MAMFile.name", name);
        return true;
    }

    @Override
    public boolean getProperties(Properties p)
    {
        this.name = p.getProperty("MAMFile.name");
        return false;
    }

}
