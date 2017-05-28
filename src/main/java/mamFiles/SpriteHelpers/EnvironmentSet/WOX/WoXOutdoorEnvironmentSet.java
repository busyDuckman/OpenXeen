package mamFiles.SpriteHelpers.EnvironmentSet.WOX;

import Game.Map.WoXWorld;
import Rendering.IRenderableGameObject;
import mamFiles.CCFileFormatException;
import mamFiles.MaMCCFileReader;
import mamFiles.MaMSprite;
import mamFiles.MaMSurface;
import mamFiles.SpriteHelpers.EnvironmentSet.IMaMOutdoorEnvironmentSet;

import java.awt.*;

/**
 * Created by duckman on 16/06/2016.
 */
public class WoXOutdoorEnvironmentSet extends WoXEnvironmentSet implements IMaMOutdoorEnvironmentSet
{

    //as per xeen wikia
//    private static final String[] surfaceNameLut = new String[] {
//            null, "DIRT.SRF", "GRASS.SRF", "SNOW.SRF",
//            "SWAMP.SRF", "LAVA.SRF", "DESERT.SRF", "ROAD.SRF",
//            "WATER.SRF", "TFLR.SRF", "SKY.SRF", "CROAD.SRF",
//            "SEWER.SRF", "CLOUD.SRF", "SCORTCH.SRF",
//            "SPACE.SRF"};

    //Altered tto match the outdoor.til on my machine
    private static final String[] surfaceNameLutMM5 = new String[] {
        "WATER.SRF", "DIRT.SRF", "GRASS.SRF",
        "SNOW.SRF",  "SWAMP.SRF", "LAVA.SRF",
        "DESERT.SRF", "ROAD.SRF", "DWATER.SRF",
        "TFLR.SRF", "SKY.SRF", "CLOUD.SRF",
        "SEWER.SRF", "CROAD.SRF", "SCORTCH.SRF",
        "SPACE.SRF"};

    private static final String[] surfaceNameLutMM4 = new String[] {

            "WATER.SRF",//ok
            "DIRT.SRF",//ok
            "GRASS.SRF",//ok
            "SNOW.SRF",//ok
            "SWAMP.SRF",//ok
            "LAVA.SRF",//ok
            "DESERT.SRF",//ok
            "ROAD.SRF",//ok
            "DWATER.SRF",//ok
            "TFLR.SRF",//ok
            "SKY.SRF",//ok
            "CLOUD.SRF", //ok
            "SPACE.SRF",  //"SEWER.SRF" not in the file //TODO, there appears to be .SRF files I have not identified
            "SPACE.SRF",  //"CROAD.SRF" not in the file //TODO, there appears to be .SRF files I have not identified
            "SCORTCH.SRF",//ok
            "SPACE.SRF"};//ok



    private final MaMSurface[] surfaces;

    private static final String[] environNameLut = new String[] {
            null,
            "MOUNT.WAL",
            "LTREE.WAL",
            "DTREE.WAL",
            "GRASS.WAL",
            "SNOTREE.WAL",
            "DSNOTREE.WAL",
            "SNOMNT.WAL",
            "DEDLTREE.WAL",
            null,//"DMOUNT.WAL",
            "LAVAMNT.WAL",
            "PALM.WAL",
            "DMOUNT.WAL"

       };

//    private final IRenderableGameObject[] environObjects;

    /**
     * Loads a set of environment sprites.
     * <p>
     * Environment keys are 4 char, EG:
     * <p>
     * Cave (CAVE)
     * Castle (CSTL)
     * Dungeon (DUNG)
     * Sci-Fi, used for example in the crashed escape pods (SCFI)
     * Town (TOWN)
     * Tower (TOWR)
     *
     * @param variant
     * @param environmentKey
     * @param ccFile
     */
    public WoXOutdoorEnvironmentSet(WoXWorld.WoxVariant variant, MaMCCFileReader ccFile) throws CCFileFormatException {
        super(variant, null, ccFile);

        String[] surfaceNameLut = (variant == WoXWorld.WoxVariant.CLOUDS) ? surfaceNameLutMM4 : surfaceNameLutMM5;
        surfaces = new MaMSurface[surfaceNameLut.length];
        for (int i = 0; i < surfaceNameLut.length; i++) {
            String surfaceName = surfaceNameLut[i];
            if(surfaceName != null)
            {
                surfaces[i] = ccFile.getSurface(surfaceName);
            }
        }

//        environObjects = new MaMSurface[environNameLut.length];
//        for (int i = 0; i < environNameLut.length; i++) {
//            String environName = environNameLut[i];
//            if(environName != null)
//            {
//                environObjects[i] = ccFile.getSprite(environName);
//            }
//        }
    }

    @Override
    public MaMSprite getEnviron(int environIndex, int mipMapLevel) {
//        String name =environIndex +".OBJ";
//        name = (environIndex < 10) ? ("0" + name) : name;
//        name = (environIndex < 100) ? ("0" + name) : name;
//        MaMSprite envObj = this.ccFile.getSpriteOrNull(name);
        String name = environNameLut[environIndex%environNameLut.length];
        if(name == null)
        {
            if(environIndex != 0)
            {
                System.out.println("UNKNOWN ENV OBJECT:" + environIndex);
            }
            return null;
        }
        MaMSprite envObj = this.ccFile.getSpriteOrNull(name);

        if(envObj != null)
        {
            try {
                envObj = envObj.appendSprite(envObj.getName() + " with mirror", envObj.mirror().asSprite());
                CCFileFormatException.stub();
            } catch (CCFileFormatException e) {
                e.printStackTrace();
            }

            return envObj;
        }
        if(environIndex != 0)
        {
            return IRenderableGameObject.fromText("Missing obj: " + environIndex, Color.red, 200, 25).asSprite();
        }
        return null;
    }

    @Override
    public IRenderableGameObject getMapEnviron(int environIndex) {
        return this.basicMapEnvrion[environIndex%this.basicMapEnvrion.length];
    }

    @Override
    public MaMSurface getSurface(int surfaceIndex) throws CCFileFormatException {
        return surfaces[(surfaceIndex)%surfaces.length];
    }

    @Override
    public IRenderableGameObject getMapTile(int groundIndex) {
        return this.basicMapGround[groundIndex%this.basicMapGround.length];
    }
}
