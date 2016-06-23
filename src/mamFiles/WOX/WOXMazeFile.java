package mamFiles.WOX;

import Game.Map.MaMTile;
import Game.Map.MaMWorld;
import Game.Map.WoXWorld;
import GameMechanics.Magic.SpellManager;
import Toolbox.BinaryHelpers;
import Toolbox.Direction;
import Toolbox.Grid;
import mamFiles.CCFileFormatException;
import mamFiles.MaMCCFileReader;
import mamFiles.MaMMazeFile;

import java.awt.*;
import java.io.ByteArrayInputStream;

/**
 * Created by duckman on 2/06/2016.
 */
public class WOXMazeFile extends MaMMazeFile
{


    public WOXMazeFile(int mazeID, String key, MaMWorld world) throws CCFileFormatException {
        super("MAZE_"+mazeID, key);

        int mapWidth = 16;
        int mapHeight = 16;

        //there are several different files to get.

        //DAT file, eg maze0001.DAT holds the terrain
        String mapDataFile = world.getMazeName(mazeID);
        MaMCCFileReader reader = ((WoXWorld)world).getCCFileCur();

        if(reader.fileExists(mapDataFile))
        {
            byte[] mapData = reader.getFileRaw(mapDataFile);
            CCFileFormatException.assertFalse(mapData == null, "WOXMazeFile(), mapData == null");

            loadMaze(mapData, mapWidth, mapHeight, world);
        }
        else
        {
            throw CCFileFormatException.fromMissingFile(mapDataFile, reader);
        }
    }

    protected void loadMaze(byte[] data, int mapWidth, int mapHeight, MaMWorld world) throws CCFileFormatException {
        //create a binary reader to save sanity.
        ByteArrayInputStream bisMapData = new ByteArrayInputStream(data);
        int mapSize = mapWidth * mapHeight;
        this.map = new Grid<>(mapWidth, mapHeight, P -> null);

            //interpret mapData
        int[] mapData = new int[mapSize]; //file data is in DWORD[]
        BinaryHelpers.readWORDs(bisMapData, mapData, mapSize, 0);

        int[] mapFlags = new int[mapSize];
        BinaryHelpers.readBYTEs(bisMapData, mapFlags, mapSize, 0);

       this.mazeID = (int)BinaryHelpers.BinaryDataTypes.WORD.read(bisMapData);

        /*
        * "SurrMazes":
        2 bytes: uint16 value indicating the map ID to the north
        2 bytes: uint16 value indicating the map ID to the east
        2 bytes: uint16 value indicating the map ID to the south
        2 bytes: uint16 value indicating the map ID to the west
        */

        //0 indicates no map
        this.joiningMaps = new int[4];
        joiningMaps[Direction.UP.ordinal()] = (int)BinaryHelpers.BinaryDataTypes.WORD.read(bisMapData);
        joiningMaps[Direction.RIGHT.ordinal()] = (int)BinaryHelpers.BinaryDataTypes.WORD.read(bisMapData);
        joiningMaps[Direction.DOWN.ordinal()] = (int)BinaryHelpers.BinaryDataTypes.WORD.read(bisMapData);
        joiningMaps[Direction.LEFT.ordinal()] = (int)BinaryHelpers.BinaryDataTypes.WORD.read(bisMapData);


        /*
        2 bytes: mazeFlags
        2 bytes: mazeFlags2
        */

        //First round of flags
        int flags = (int)BinaryHelpers.BinaryDataTypes.WORD.read(bisMapData);

        if((flags & (1<<6)) != 0) cantCastList.add(SpellManager.normaliseSpellName("Etheralize"));
        if((flags & (1<<8)) != 0) cantCastList.add(SpellManager.normaliseSpellName("Town Portal"));
        if((flags & (1<<9)) != 0) cantCastList.add(SpellManager.normaliseSpellName("Super Shelter"));
        if((flags & (1<<10)) != 0) cantCastList.add(SpellManager.normaliseSpellName("Time Distortion"));
        if((flags & (1<<11)) != 0) cantCastList.add(SpellManager.normaliseSpellName("Lloyds Beacon"));
        if((flags & (1<<12)) != 0) cantCastList.add(SpellManager.normaliseSpellName("Teleport"));

        canRest = (flags & (1 << 14)) != 0;
        canSave = (flags & (1 << 15)) != 0;

        //miscInfo
        int[] unusedBits = {0,1,2,3,4,5,7,13};
        miscFlags = new boolean[32];
        for(int i=0;i<unusedBits.length;i++)
        {
            miscFlags[i] = (flags & (1 << unusedBits[i])) != 0;
        }

        //Second round of flags
        flags = (int)BinaryHelpers.BinaryDataTypes.WORD.read(bisMapData);
        isDark = (flags & (1 << 14)) != 0;
        isOutdoors = (flags & (1 << 15)) != 0;

        unusedBits = new int[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13};
        for(int i=0;i<unusedBits.length;i++)
        {
            miscFlags[i+16] = (flags & (1 << unusedBits[i])) != 0;
        }


        /*
        16 bytes: wallTypes, 16 byte array of wall types, used for indirect lookup
        16 bytes: surfaceTypes, 16 byte array of surface types (ie, floors) used for indirect lookup
        */
        int[] wallLut = BinaryHelpers.readBYTEs(bisMapData, 16);
        int[] floorLut = BinaryHelpers.readBYTEs(bisMapData, 16);

        /*

        // OLD NOTES I WAS WORKING FROM
        // NO runX...
        // NEW NOTES HAS runX IN AN ODD SPOT
        // NB: maps are only 16x16, so in theory only one byte may hold the point.
        1 byte: floor type
        1 byte: wallNoPass
        1 byte: surfNoPass
        1 byte: unlockDoor
        1 byte: unlockBox
        1 byte: bashDoor
        1 byte: bashGrate
        1 byte: bashWall
        1 byte: chanceToRun
        1 byte: runY
        1 byte: trapDamage
        1 byte: wallKind
        1 byte: tavernTips
        */
        //14 bytes of stuff [thought this was 13?]

        // #1 - floor type, the default floor type (lookup table, used by indoor maps)
        floorType = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #2 - runX, the X coordinate the party will land at if they run from a fight
        int xRun = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #3 - wallNoPass, wall values greater than or equal to this value cannot be walked through at all.
        wallNoPass = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #4 - surfNoPass, surface values greater than or equal to this value cannot be stepped on
        // (typically only ever 0x0F, space).
        surfNoPass = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #5 - unlockDoor, the difficulty of unlocking a door on this map
        unlockDoor = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #6 - unlockBox, the difficulty of unlocking a chest on this map
        unlockBox = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #7 - bashDoor, the difficulty of bashing through a door
        bashDoor = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #8 - bashGrate, the difficulty of bashing through a grate
        bashGrate = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #9 - bashWall, the difficulty of bashing through a wall (note that there are other
        // requirements to bash through a wall, even if the party is strong enough)
        bashWall = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #10 - chanceToRun, the difficulty of running from a fight
        chanceToRun = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #11 - runY, the Y coordinate the party will land at if they run from a fight
        int yRun = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #12 - trapDamage, the level of damage the party will receive from traps on this map
        trapDamage = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #13 - wallKind, the type of walls, used in a lookup table
        wallKind = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        // #14 - tavernTips, lookup table for the text file used by the tavern, if any
        tavernTips = (int)BinaryHelpers.BinaryDataTypes.BYTE.read(bisMapData);

        //set run pos
        runPos = new Point(xRun, yRun);

        /*
        32 bytes: 16x16 bit array indicating which tiles have been "seen"
        32 bytes: 16x16 bit array indicating which tiles have been "stepped on"
        */
        int[] seenTiles = BinaryHelpers.readBYTEs(bisMapData, mapSize/8);
        int[] visitedTiles = BinaryHelpers.readBYTEs(bisMapData, mapSize/8);


        //loop over every tile and create the map
        for (int y = 0; y < mapHeight; y++)
        {
            for (int x = 0; x < mapWidth; x++)
            {
                int index = (y * mapWidth) + x;
                int bitIndex = index / 8;
                int bitMod = index % 8;

                boolean seen = BinaryHelpers.byte2Bits(seenTiles[bitIndex])[bitMod];
                boolean visited = BinaryHelpers.byte2Bits(visitedTiles[bitIndex])[bitMod];

                MaMTile tile = createTile(mapData[index], mapFlags[index]);
                tile.setSeen(seen);
                tile.setVisited(visited);
                //translate the iBase
                //map[x, y].iBase += (floorLib[map[x, y].iBase] & 0xf);
                ////map[x, y].iMiddle += (wallLib[map[x, y].iMiddle] & 0xf);
                //map[x, y].iMiddle += 8;

                map.set(x, mapHeight-y-1, tile);
            }
        }

        //graphics
        if(this.isOutdoors)
        {
            //TODO: use index
            environmentSet  = world.getOutdoorEnvironmentSet(0);
        }
        else
        {
            environmentSet  = world.getIndoorEnvironmentSet(0);
        }
    }

    private MaMTile createTile(int data, int flags) {
        int base = data & 0x0f;
        int middle = (data>>4) & 0x0f;
        int top = (data>>8) & 0x0f;
        int overlay = (data>>12) & 0x0f;

        //5 flags and a 3 bit int.
        boolean hasGrate = (flags & 0x80) != 0;
        boolean noRest = (flags & 0x40) != 0;
        boolean hasDrain = (flags & 0x20) != 0;
        boolean hasEvent = (flags & 0x10) != 0;
        boolean hasObject = (flags & 0x08) != 0;
        int numMonstors = (flags & 0x07);

        MaMTile tile = new MaMTile(base, middle, top, overlay);


        return tile;
    }

}
