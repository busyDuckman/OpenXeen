package Rendering;

import mamFiles.CCFileFormatException;
import mamFiles.IHasProxy;
import mamFiles.MAMFile;
import mamFiles.MaMPallet;

import java.awt.image.BufferedImage;

/**
 * Created by duckman on 19/05/2016.
 */
public interface IMaMSprite extends IRenderableGameObject
{
    MaMPallet getPallet();

    int getWidth();

    int getHeight();

    //Now in IRenderableGameObject as a default
    //BufferedImage[] getRenderedFrames();
}
