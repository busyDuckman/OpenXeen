package mamFiles;

import Game.MaMGame;
import Rendering.AnimationSettings;
import Toolbox.FileHelpers;
import Toolbox.IHasProperties;
import Toolbox.ImageHelpers;
import Toolbox.xBR.ResizeXBR;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.sun.org.apache.xalan.internal.lib.ExsltStrings.split;

/**
 * Created by duckman on 10/05/2016.
 */
public class MaMSprite extends MAMFile implements Rendering.IMaMSprite, IHasProperties
{
    public static class FrameInfo
    {
        transient public byte[] data;

        protected int xPos;
        protected int yPos;
        protected int width;
        protected int height;

        //protected BufferedImage[] preRenderedFrames;
        protected FrameInfo() {}
        public FrameInfo(int xPos, int yPos, int width, int height)
        {
            this.xPos = xPos;
            this.yPos = yPos;
            this.width = width;
            this.height = height;
            data = new byte[width * height * 4];
        }

        public FrameInfo(FrameInfo other)
        {
            this.data = Arrays.copyOf(other.data, other.data.length);
            this.width = other.width;
            this.height = other.height;
            this.xPos = other.xPos;
            this.yPos = other.yPos;
            //this.preRenderedFrames = ImageHelpers.copyOf(other.preRenderedFrames);
        }

        public static FrameInfo fromParsableString(String s)
        {
            if(s != null)
            {
                int[] nums = Arrays.asList(s.split(",")).
                            stream().
                            map(String::trim).
                            mapToInt(Integer::parseInt).toArray();

                if(nums.length == 4)
                {
                    return new FrameInfo(nums[0], nums[1], nums[2], nums[3]);
                }
                System.out.println("Frame string (in proxy) not parsable: " + s);
            }
            return null;
        }


        @Override
        public String toString() {
            return "FrameInfo{" +
                    "#bytes=" + data.length +
                    ", xPos=" + xPos +
                    ", yPos=" + yPos +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }

        public String toParsableString() {
            return Arrays.stream(new int[] {xPos, yPos, width, height}).mapToObj(i -> ""+i).collect(Collectors.joining(","));
        }
    }

    protected FrameInfo[] frames;
    protected BufferedImage[] renderedFrames;

    protected MaMPallet pallet;
    protected int transparentIndex;

    protected int width;
    protected int height;

    protected AnimationSettings animationSettings = null;

    protected MaMSprite(String name, MaMPallet pal)
    {
        super(name);
        pallet = pal;
        transparentIndex = 0;
    }

    protected MaMSprite(String name, BufferedImage[] frames)
    {
        super(name);
        pallet = null;
        renderedFrames = frames;
        transparentIndex = 0;
    }


    @Override
    public MaMPallet getPallet() {
        return pallet;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public FrameInfo[] getFrames() {
        return frames;
    }

    @Override
    public BufferedImage[] getRenderedFrames()
    {
        if((renderedFrames == null) && (frames != null))
        {
            renderedFrames = new BufferedImage[frames.length];
            for (int i = 0; i < frames.length; i++)
            {
                //TODO: Now that I am making frames regions,
                // width, height are not correct for annoying sprites that change size.
                //renderedFrames[i] = ImageHelpers.RGBA2Image(frames[i].data, width, height);

                renderedFrames[i] = ImageHelpers.RGBA2Image(frames[i].data, frames[i].width, frames[i].height);
            }
        }
        return renderedFrames;
    }

    public int getTransparentIndex() {
        return transparentIndex;
    }

    @Override
    public String toString() {
        return "MaMSprite{" +
                "name=" + name +
                "frames=" + Arrays.toString(frames) +
                ", transparentIndex=" + transparentIndex +
                ", width=" + width +
                ", height=" + height +
                '}';
    }

    @Override
    public BufferedImage getImage(int frame) {
        return getRenderedFrames()[frame];
    }

    @Override
    public AnimationSettings getAnimationSettings() {
        if((animationSettings == null) && (getRenderedFrames() != null))
        {
            animationSettings = new AnimationSettings(getRenderedFrames().length, 100, false,  true);
        }
        return animationSettings;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Proxy
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public String suggestProxyFileName() {
        return name + ".png";
    }

    @Override
    public boolean saveProxy(String path) throws CCFileFormatException {
        //return ImageHelpers.saveAsGif(path, this.getRenderedFrames(), 300, true);
        CCFileFormatException.throwIf(width  <=0);
        CCFileFormatException.throwIf(height <=0);

        BufferedImage join = ImageHelpers.joinHorizontally(this.getRenderedFrames());
        //BufferedImage xBRJoin = ResizeXBR.xBR(join, 4);
        try {
            ImageIO.write(join, "png", new File(path));
            //ImageIO.write(xBRJoin, "png", new File(MaMGame.getModVersionOfPath(path, "xbr")));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return saveProperties(FileHelpers.changeExtesion(path, "cfg"), "openXeen sprite configuration file");
    }

    //not used anymore
    public MaMSprite fromGifFile(String path)
    {
        try {
            return new MaMSprite(FileHelpers.getFileNameTillFirstDot(path),
                    ImageHelpers.getGifFrames(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * loads a sprite sheet and metadata.
     * TODO: This is now broken, it does not consider image frames of different size in the sprite sheet.
     */
    public static MaMSprite fromPNGFile(String path) throws CCFileFormatException
    {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            MaMSprite sprite = new MaMSprite("", (MaMPallet)null);
            if(sprite.loadProperties(FileHelpers.changeExtesion(path, "cfg")))
            {
                //this works because getProperties fills out a dummy array of the correct size.
                sprite.renderedFrames = ImageHelpers.splitHorizontally(img, sprite.renderedFrames.length);

                CCFileFormatException.throwIf(!FileHelpers.getFileNameTillFirstDot(path).equals(sprite.getName()));
                return sprite;
            }
            else
            {
                //just give up and create a non-animated sprite, in the images resolution.
                return new MaMSprite(FileHelpers.getFileNameTillFirstDot(path), new BufferedImage[] {img});
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    //------------------------------------------------------------------------------------------------------------------
    // IHasProperties
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean setProperties(Properties p) {
        super.setProperties(p);
        p.setProperty("MaMSprite.originalWidth", String.valueOf(this.width));
        p.setProperty("MaMSprite.originalHeight", String.valueOf(this.height));
        p.setProperty("MaMSprite.frameCount", String.valueOf(this.getRenderedFrames().length));

        AnimationSettings s = this.getAnimationSettings();
        p.setProperty("MaMSprite.hasAnimation", String.valueOf(s != null));
        if(s != null)
        {
            s.setProperties(p);
        }
        for(int i=0; i<frames.length; i++)
        {
            p.setProperty("MaMSprite.Frame["+i+"]", frames[i].toParsableString());
        }

        return true;
    }

    @Override
    public boolean getProperties(Properties p) {
        super.getProperties(p);
        this.width = Integer.parseInt(p.getProperty("MaMSprite.originalWidth"));
        this.height = Integer.parseInt(p.getProperty("MaMSprite.originalHeight"));
        int frameCount = Integer.parseInt(p.getProperty("MaMSprite.frameCount"));


        //TODO: is this an error when getting settings for an existing sprite?
        //if((getRenderedFrames() != null) && (frameCount != getRenderedFrames().length))

        //In constructing a new sprite, we fill out a null array to guide how we split the sprite sheet
        if(getRenderedFrames() == null)
        {
            renderedFrames = new BufferedImage[frameCount];
        }

        if(Boolean.parseBoolean(p.getProperty("MaMSprite.hasAnimation")))
        {
            if(this.getAnimationSettings() == null)
            {
                animationSettings = new AnimationSettings(1, 100, true, true);
            }
            animationSettings.getProperties(p);
        }

        //I am not proud of what follows
        frames = new FrameInfo[animationSettings.getNumberOfFrames()];
        for(int i=0; i<frames.length; i++)
        {
            String propName = "MaMSprite.Frame["+i+"]";
            String frameString = p.getProperty(propName);
            if(frameString != null)
            {
                FrameInfo f = FrameInfo.fromParsableString(frameString);
                if(f != null)
                {
                    frames[i] = f;
                    continue;
                }
            }

            System.out.println("Error parsing property for frame: " + propName);
        }

        return true;
    }

}
