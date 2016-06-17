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

    //if shit hits the fan when looking for a MaM palate, this may well work.
    private final static Color[] defaultPal = {
            new Color(0, 0, 0), new Color(252, 252, 252), new Color(244, 244, 244), new Color(236, 236, 236), new Color(228, 228, 228), new Color(220, 220, 220),
            new Color(212, 212, 212), new Color(204, 204, 204), new Color(196, 196, 196), new Color(188, 188, 188), new Color(180, 180, 180), new Color(172, 172, 172),
            new Color(164, 164, 164), new Color(156, 156, 156), new Color(148, 148, 148), new Color(140, 140, 140), new Color(132, 132, 132), new Color(124, 124, 124),
            new Color(116, 116, 116), new Color(108, 108, 108), new Color(100, 100, 100), new Color(92, 92, 92), new Color(84, 84, 84), new Color(76, 76, 76),
            new Color(68, 68, 68), new Color(60, 60, 60), new Color(52, 52, 52), new Color(44, 44, 44), new Color(36, 36, 36), new Color(28, 28, 28),
            new Color(20, 20, 20), new Color(12, 12, 12), new Color(224, 236, 252), new Color(204, 228, 252), new Color(180, 212, 252), new Color(156, 192, 252),
            new Color(132, 172, 252), new Color(112, 148, 252), new Color(88, 124, 252), new Color(64, 96, 252), new Color(40, 64, 252), new Color(28, 48, 228),
            new Color(20, 36, 208), new Color(16, 28, 184), new Color(8, 16, 164), new Color(4, 8, 140), new Color(0, 4, 120), new Color(0, 0, 96),
            new Color(248, 252, 192), new Color(244, 252, 164), new Color(244, 252, 136), new Color(244, 252, 108), new Color(236, 248, 76), new Color(232, 240, 60),
            new Color(224, 228, 44), new Color(216, 220, 32), new Color(200, 204, 16), new Color(188, 192, 4), new Color(176, 172, 0), new Color(156, 148, 0),
            new Color(136, 128, 0), new Color(112, 104, 0), new Color(92, 84, 0), new Color(76, 64, 0), new Color(196, 232, 252), new Color(172, 220, 248),
            new Color(148, 208, 244), new Color(124, 196, 244), new Color(100, 188, 240), new Color(80, 176, 236), new Color(56, 164, 236), new Color(36, 152, 232),
            new Color(16, 144, 228), new Color(0, 132, 228), new Color(0, 120, 208), new Color(0, 108, 188), new Color(0, 92, 164), new Color(0, 80, 144),
            new Color(0, 68, 120), new Color(0, 56, 100), new Color(212, 248, 204), new Color(192, 248, 180), new Color(172, 248, 156), new Color(152, 248, 132),
            new Color(128, 244, 104), new Color(104, 236, 80), new Color(84, 224, 56), new Color(56, 216, 32), new Color(52, 208, 24), new Color(44, 192, 16),
            new Color(36, 176, 12), new Color(28, 160, 8), new Color(20, 144, 4), new Color(16, 128, 0), new Color(12, 112, 0), new Color(8, 96, 0),
            new Color(188, 248, 252), new Color(168, 236, 240), new Color(152, 224, 228), new Color(136, 212, 216), new Color(120, 200, 204), new Color(104, 192, 192),
            new Color(92, 180, 180), new Color(80, 168, 172), new Color(68, 160, 160), new Color(60, 144, 148), new Color(56, 132, 136), new Color(48, 116, 124),
            new Color(44, 104, 112), new Color(36, 92, 100), new Color(32, 80, 88), new Color(28, 68, 76), new Color(240, 216, 252), new Color(228, 184, 248),
            new Color(216, 156, 244), new Color(204, 128, 240), new Color(192, 100, 240), new Color(180, 72, 236), new Color(172, 44, 232), new Color(160, 20, 228),
            new Color(144, 0, 220), new Color(136, 0, 208), new Color(120, 0, 188), new Color(104, 0, 168), new Color(92, 0, 148), new Color(76, 0, 128),
            new Color(64, 0, 108), new Color(52, 0, 88), new Color(252, 216, 252), new Color(248, 184, 248), new Color(244, 156, 244), new Color(240, 128, 240),
            new Color(236, 100, 240), new Color(232, 72, 236), new Color(232, 44, 232), new Color(220, 20, 224), new Color(212, 0, 216), new Color(204, 0, 208),
            new Color(184, 0, 188), new Color(164, 0, 168), new Color(144, 0, 148), new Color(124, 0, 128), new Color(108, 0, 108), new Color(88, 0, 88),
            new Color(252, 232, 220), new Color(244, 220, 208), new Color(240, 212, 196), new Color(236, 204, 184), new Color(232, 196, 172), new Color(228, 184, 160),
            new Color(220, 176, 148), new Color(216, 168, 140), new Color(212, 160, 128), new Color(208, 152, 120), new Color(204, 144, 108), new Color(196, 140, 100),
            new Color(192, 132, 92), new Color(188, 124, 84), new Color(184, 116, 76), new Color(180, 112, 68), new Color(172, 108, 64), new Color(164, 104, 60),
            new Color(160, 100, 56), new Color(152, 96, 56), new Color(144, 92, 52), new Color(140, 88, 48), new Color(132, 84, 48), new Color(128, 80, 44),
            new Color(120, 76, 40), new Color(112, 72, 40), new Color(108, 68, 36), new Color(100, 64, 32), new Color(92, 60, 32), new Color(88, 56, 28),
            new Color(80, 52, 24), new Color(76, 48, 24), new Color(252, 212, 212), new Color(244, 184, 184), new Color(236, 160, 160), new Color(232, 136, 136),
            new Color(224, 112, 112), new Color(216, 92, 92), new Color(212, 68, 68), new Color(204, 48, 48), new Color(200, 32, 32), new Color(192, 20, 20),
            new Color(184, 8, 8), new Color(168, 0, 0), new Color(148, 0, 0), new Color(128, 0, 0), new Color(108, 0, 0), new Color(88, 0, 0), new Color(252, 220, 188),
            new Color(252, 208, 164), new Color(252, 196, 140), new Color(252, 184, 116), new Color(252, 176, 92), new Color(252, 164, 68), new Color(252, 152, 44),
            new Color(248, 140, 20), new Color(236, 128, 8), new Color(220, 120, 4), new Color(204, 108, 0), new Color(180, 96, 0), new Color(156, 80, 0),
            new Color(132, 68, 0), new Color(108, 56, 0), new Color(88, 44, 0), new Color(196, 248, 52), new Color(180, 232, 40), new Color(168, 220, 32),
            new Color(152, 208, 20), new Color(140, 192, 12), new Color(128, 180, 8), new Color(116, 168, 0), new Color(104, 156, 0), new Color(68, 144, 0),
            new Color(32, 136, 0), new Color(4, 124, 0), new Color(0, 116, 8), new Color(0, 104, 28), new Color(0, 96, 44), new Color(0, 84, 56),
            new Color(0, 76, 68), new Color(64, 44, 28), new Color(48, 36, 28), new Color(88, 128, 12), new Color(36, 64, 8), new Color(24, 60, 68),
            new Color(20, 52, 60), new Color(236, 232, 0), new Color(220, 208, 0), new Color(208, 188, 0), new Color(196, 168, 0), new Color(184, 152, 0),
            new Color(172, 136, 0), new Color(156, 120, 0), new Color(144, 104, 0), new Color(132, 88, 0), new Color(120, 76, 0), new Color(252, 156, 0),
            new Color(252, 176, 0), new Color(252, 196, 0), new Color(252, 216, 0), new Color(252, 240, 0), new Color(244, 252, 0), new Color(252, 228, 0),
            new Color(252, 200, 0), new Color(252, 172, 0), new Color(252, 140, 0), new Color(252, 112, 0), new Color(252, 84, 0), new Color(252, 52, 0),
            new Color(252, 24, 0), new Color(252, 0, 0), new Color(252, 252, 252)
    };
    //public MaMPallet() { colors = new Color[256]; }

    public MaMPallet(String name, String key, byte[] data, int numColors, int leftShift)
    {
        super(name, key);
        colors = new Color[numColors];
        for (int i = 0; i < Math.min(numColors,data.length/3); i ++)
        {
            byte r = (byte) ((data[i*3] << leftShift) & 0xff);
            byte g = (byte) ((data[i*3+1] << leftShift) & 0xff);
            byte b = (byte) ((data[i*3+2] << leftShift) & 0xff);

            colors[i] = new Color(INT(r),INT(g),INT(b));
        }
    }

    public MaMPallet(String name, String key, Color[] data)
    {
        super(name, key);
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
        return new MaMPallet(name+"_trans@"+index, MAMFile.generateUniqueKey(key), newColors);
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
                    return new MaMPallet(FileHelpers.getFileNameTillFirstDot(path),
                                        MAMFile.generateKeyFromPath(path),
                                        ArrayHelpers.toColorArray(cols));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static MaMPallet getDefaultMaMPallate() {
        return new MaMPallet("default pallate", MAMFile.generateUniqueKey("defaultPal"), defaultPal);
    }
}
