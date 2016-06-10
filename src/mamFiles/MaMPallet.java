package mamFiles;

import Toolbox.ArrayHelpers;
import Toolbox.ColourHelpers;
import Toolbox.FileHelpers;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Toolbox.BinaryHelpers.*;

/**
 * Created by duckman on 10/05/2016.
 */
public class MaMPallet extends MAMFile
{
    public Color[] colors;

    //public MaMPallet() { colors = new Color[256]; }

    public MaMPallet(String name, byte[] data, int numColors, int leftShift)
    {
        super(name);
        colors = new Color[numColors];
        for (int i = 0; i < Math.min(numColors,data.length/3); i ++)
        {
            byte r = (byte) ((data[i*3] << leftShift) & 0xff);
            byte g = (byte) ((data[i*3+1] << leftShift) & 0xff);
            byte b = (byte) ((data[i*3+2] << leftShift) & 0xff);

            colors[i] = new Color(INT(r),INT(g),INT(b));
        }
    }

    public MaMPallet(String name, Color[] data)
    {
        super(name);
        colors = new Color[data.length];
        for (int i = 0; i < data.length; i++) {
            colors[i] = new Color(data[i].getRed(), data[i].getGreen(), data[i].getBlue(), data[i].getAlpha());
        }
    }

    public MaMPallet withTransperency(int index)
    {
        if((index <0)||(index >= this.colors.length))
        {
            return this; //operation will have no effect, so just return this.
        }
        Color[] newColors = new Color[colors.length];
        for (int i = 0; i < newColors.length; i++)
        {
            newColors[i] = (i == index) ? new Color(0,0,0,0) : colors[i];
        }

        //done.
        return new MaMPallet(name+"_trans@"+index, newColors);
    }

    public Color getColor(int index) { return colors[index% colors.length]; }
    public int getPalletSize() { return colors.length; }

    @Override
    public String suggestProxyFileName() {
        return name+".pal";
    }

    @Override
    public boolean saveProxy(String path)
    {
        StringBuilder sb = new StringBuilder();
        Arrays.stream(colors).forEach(c -> sb.append(ColourHelpers.RGB2String(c)).append(System.lineSeparator()));
        return FileHelpers.saveText(sb.toString(), path);
    }

    public static MaMPallet fromPalFile(String path)
    {
        try
        {
            if(FileHelpers.fileExists(path))
            {
                String[] lines = FileHelpers.readAllLines(path);
                if((lines != null) &&(lines.length >= 256))
                {
                    List<Color> cols = new ArrayList<>();
                    for (int i = 0; i < lines.length; i++)
                    {
                        String line = lines[i];
                        if(line.trim().length() > 0)
                        {
                            cols.add(ColourHelpers.String2RGB(line.trim()));
                        }

                    }

                    //done
                    return new MaMPallet(FileHelpers.getFileNameTillFirstDot(path), ArrayHelpers.toColorArray(cols));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
