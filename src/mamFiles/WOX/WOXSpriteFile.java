package mamFiles.WOX;

import Toolbox.BinaryHelpers;
import mamFiles.CCFileFormatException;
import mamFiles.MaMPallet;
import mamFiles.MaMSprite;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static Toolbox.BinaryHelpers.*;

/**
 * Created by duckman on 10/05/2016.
 *
 */
public class WOXSpriteFile extends MaMSprite
{

    protected class Cell extends Rectangle
    {
        byte[] rgbData;

        public Cell(byte[] data, int offset) throws CCFileFormatException {
            int dp = offset;

            // Read the position and size of the cell from the data stream
            x= BYTES2INT_lsb(data[dp], data[dp+1]);
            dp += 2;
            width = BYTES2INT_lsb(data[dp], data[dp+1]);
            dp += 2;
            y = BYTES2INT_lsb(data[dp], data[dp+1]);
            dp += 2;
            height = BYTES2INT_lsb(data[dp], data[dp+1]);

            CCFileFormatException.assertFalse(width  < 0, "Cell() width  < 0");
            CCFileFormatException.assertFalse(height < 0, "Cell() height < 0");
            CCFileFormatException.assertFalse(width >= 1024, "Cell() width >= 1024");
            CCFileFormatException.assertFalse(width >= 1024, "Cell() width >= 1024");
            rgbData = new byte[width * height * 3];
        }

        public byte[] getRgbData()
        {
            return rgbData;
        }

        @Override
        public String toString() { return "Cell{#bytes=" + rgbData.length + '}'; }

        void putPixel(int x, int y, int colorIndex, FrameInfo frame, MaMSprite sprite)
        {
            if(sprite == null)
            {
                System.out.println("WTF: sprite = null. Puppy Cheese Blanket");
                return;
            }

            try
            {
                int writePos = (y*width+x)*4;
                //argb
                frame.data[writePos] = (colorIndex == sprite.getTransparentIndex()) ? 0 : (byte)0xff;
                frame.data[writePos+1] = (byte)sprite.getPallet().getColor(colorIndex).getRed();
                frame.data[writePos+2] = (byte)sprite.getPallet().getColor(colorIndex).getGreen();
                frame.data[writePos+3] = (byte)sprite.getPallet().getColor(colorIndex).getBlue();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            //colorData[(y*width+x)*4] = (byte)( (pallet != null) ? pallet.getColor(colorIndex).getBlue()  : colorIndex );
            //colorData[(y*width+x)*4+1] = (byte)( (pallet != null) ? pallet.getColor(colorIndex).getGreen() : colorIndex );
            //colorData[(y*width+x)*4+2] = (byte)( (pallet != null) ? pallet.getColor(colorIndex).getRed()   : colorIndex );
        }

        //based on code at http://xeen.wikia.com/wiki/Sprite_File_Format#Pattern_Command
        public void renderToFrame(FrameInfo frame, byte[] data, int offset, MaMSprite sprite)
        {
            //computationally, treat all these are dwords
            int i, dp = offset;                          // Pointer within data stream
            int xPos, yPos;                         // The position within the color raster
            int lineLength, byteCount;              // total bytes/bytes read in this scan line
            int opcode, cmd, len, opr1, opr2;       // Used to decode the drawing commands
            int xOffset, yOffset, width, height;    // Cell position and size

            // The pattern steps used in the pattern command
            int patternSteps[] = { 0, 1, 1, 1, 2, 2, 3, 3, 0, -1, -1, -1, -2, -2, -3, -3 };

            // Read the position and size of the cell from the data stream
            xOffset = BYTES2INT_lsb(data[dp], data[dp+1]);
            dp += 2;
            width = BYTES2INT_lsb(data[dp], data[dp+1]);
            dp += 2;
            yOffset = BYTES2INT_lsb(data[dp], data[dp+1]);
            dp += 2;
            height = BYTES2INT_lsb(data[dp], data[dp+1]);
            dp += 2;


            // Fill the color raster with the transparent color
            //TODO: width-xOffset etc would seem the way... what is going on?
            for( i = 0 ; i < ( xOffset + width ) * ( yOffset + height ) ; i++ )
            {
                //frame.data[0] =0;
                //*((DWORD *)(&colorData[i * 4])) = transparent;
            }

            for( yPos = yOffset, byteCount = 0 ; yPos < height + yOffset ; yPos++, byteCount = 0 )
            {
                // The number of bytes in this scan line
                lineLength = INT(data[dp++]);

                if( lineLength > 0 )
                {
                    // Skip the transparent color at the beginning of the scan line
                    xPos = INT(data[dp++]) + xOffset; byteCount++;

                    while( byteCount < lineLength )
                    {
                        // The next byte is an opcode that determines what
                        // operators are to follow and how to interpret them.
                        opcode = INT(data[dp++]); byteCount++;

                        // Decode the opcode
                        len = opcode & 0x1F;
                        cmd = ( opcode & 0xE0 ) >> 5;

                        switch( cmd )
                        {
                            case 0:   // The following len + 1 bytes are stored as indexes into the color table.
                            case 1:   // The following len + 33 bytes are stored as indexes into the color table.
                                for( i = 0 ; i < opcode + 1 ; i++, xPos++ )
                                {
                                    opr1 = INT(data[dp++]); byteCount++;
                                    putPixel( xPos, yPos, opr1, frame, sprite);
                                }
                                break;

                            case 2:   // The following byte is an index into the color table, draw it len + 3 times.
                                opr1 = INT(data[dp++]); byteCount++;
                                for( i = 0 ; i < len + 3 ; i++, xPos++ )
                                {
                                    putPixel( xPos, yPos, opr1, frame, sprite);
                                }
                                break;

                            case 3:   // Stream copy command.
                                opr1 = BYTES2INT_lsb(data[dp], data[dp+1]);//  *((WORD *)&cellData[dp]);
                                dp += 2; byteCount += 2;
                                for( i = 0 ; i < len + 4 ; i++, xPos++ )
                                {
                                    opr2 = INT(data[dp-opr1+i]);
                                    putPixel( xPos, yPos, opr2, frame, sprite);
                                }
                                break;

                            case 4:   // The following two bytes are indexes into the color table, draw the pair len + 2 times.
                                opr1 = INT(data[dp++]); byteCount++;
                                opr2 = INT(data[dp++]); byteCount++;
                                for( i = 0 ; i < len + 2 ; i++, xPos += 2 )
                                {
                                    putPixel( xPos+0, yPos, opr1, frame, sprite);
                                    putPixel( xPos+1, yPos, opr2, frame, sprite);
                                }
                                break;

                            case 5:   // Skip len + 1 pixels filling them with the transparent color.
                                xPos += len + 1;
                                break;

                            case 6:   // Pattern command.
                            case 7:
                                // The pattern command has a different opcode format
                                len = opcode & 0x07;
                                cmd = ( opcode >> 2 ) & 0x0E;

                                opr1 = INT(data[dp++]); byteCount++;
                                for( i = 0 ; i < len + 3 ; i++, xPos++ )
                                {
                                    putPixel( xPos, yPos, opr1, frame, sprite);
                                    opr1 += patternSteps[cmd + ( i % 2 )];
                                }
                                break;
                        }
                    }
                }
                else
                {
                    // Skip the specified number of scan lines
                    yPos += INT(data[dp++]);
                }
            }
        }

    }



    public WOXSpriteFile(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        super(name, key, pal);
        CCFileFormatException.assertFalse_WhenLoadingFrom(data == null, name, key);
        BinaryHelpers.DebugDumpBinary(data, "sprite.last");

        //0002h	n * 4	The cells that are used to generate a frame where n is the number of frames in the sprite.
        // These are also the offsets in the sprite file where the cell data begins.
        // Each frame can be a combination of up to two cells - one drawn over top the other.
        // The first two bytes is the offset to the first cell and the second two bytes is the offset of the second cell.
        // The first offset is never zero (meaning there is always one cell that makes up a frame), but the second offset can be zero
        // if all the image data was stored in the first cell.

        //parse header

        //The number of frames in the sprite. This is a word with the LSB first.
        int p=0;
        int numFrames = BYTES2INT_lsb(data[p], data[p+1]);
        p += 2;

        //load cells
        Map<Integer, Cell> cells = new HashMap<>();

        // Setup the cell to animation frame lut.
        //   ie: frameSetup[frameNum][cellToRender]
        List<List<Integer>> frameSetup = new ArrayList<>();
        loadCellsAndFrameSetup(data, p, numFrames, cells, frameSetup);

        //calculate the sprites size, by accumulating all cell draw regions.
        if(cells.size() > 0)
        {
            width =  cells.values().stream().map(c -> c.x + c.width).max(Integer::compare).get();
            height = cells.values().stream().map(c -> c.y + c.height).max(Integer::compare).get();
        }
        else
        {
            width = 0;
            height = 0;
        }

        //it's a guess, but seems to work
        transparentIndex = 0;

        //render all frames
        frames = new FrameInfo[numFrames];
        for(int i=0; i<numFrames; i++)
        {
            List<Integer> cellOffsetsInThisFrame = frameSetup.get(i);

            //TODO: More investigation into what a blank frame is for and how to handle
            if(cellOffsetsInThisFrame.size() == 0)
            {
                frames[i] = FrameInfo.emptyFrame();
            }

            frames[i] = sizeFrame(cells, cellOffsetsInThisFrame);

            //uncomment If I think a sprite may be accumulative (difference between frames, like a flc)
//            if(i>0)
//            {
//                frames[i] = new FrameInfo(frames[i-1]);
//            }
//            else
//            {
//                frames[i] = new FrameInfo(width, height);
//            }

            for (int c = 0; c < cellOffsetsInThisFrame.size(); c++)
            {
                int offset = cellOffsetsInThisFrame.get(c);
                //System.out.println("Rendering celli="+i+" c="+c+" offset="+offset+"");
                cells.get(offset).renderToFrame(frames[i], data, offset, this);
            }
        }
    }

    protected FrameInfo sizeFrame(Map<Integer, Cell> cells, List<Integer> cellOffsetsInThisFrame) throws CCFileFormatException {
        int frameX=Integer.MAX_VALUE;
        int frameY=Integer.MAX_VALUE;
        int frameWidth=0;
        int frameHeight=0;

        List<Cell> cellsInFrame = cells.entrySet().stream()
                .filter((P) -> cellOffsetsInThisFrame.contains(P.getKey()))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        //CCFileFormatException.assertTrue(cellsInFrame.size() > 0, "WOXSpriteFile::sizeFrame() cellsInFrame.size() > 0");
        if(cellsInFrame.size() == 0)
        {
            System.out.println("WTF: empty frame.");
            return FrameInfo.emptyFrame();
        }

        for(Cell cell : cellsInFrame)
        {
            frameX = Math.min(frameX, cell.x);
            frameY = Math.min(frameY, cell.y);
            frameWidth = Math.max(frameWidth, cell.x + cell.width);
            frameHeight = Math.max(frameHeight, cell.y + cell.height);
        }

        return new FrameInfo(frameX, frameY, frameWidth, frameHeight);
    }

    private void loadCellsAndFrameSetup(byte[] data, int p, int numFrames,
                                        Map<Integer, Cell> cells, List<List<Integer>> frameSetup)
            throws CCFileFormatException
    {
        for(int i=0; i< numFrames; i++)
        {
            List<Integer> cellList = new ArrayList<>();

            int cellOffset1 = BYTES2INT_lsb(data[p], data[p+1]);
            p += 2;

            int cellOffset2 = BYTES2INT_lsb(data[p], data[p+1]);
            p += 2;

            if((cellOffset1 == 0) && (cellOffset2 == 0))
            {
                System.out.println("Frame with no cells?");
            }

            addCell(data, cellOffset1, cellList, cells);
            addCell(data, cellOffset2, cellList, cells);

            frameSetup.add(cellList);
        }
    }

    private void addCell(byte[] data, int offset, List<Integer> cellList, Map<Integer, Cell> cells)
            throws CCFileFormatException
    {
        //0 offset signifies no cell
        if(offset != 0)
        {
            if(!cells.containsKey(offset))
            {
                //new cell
                Cell c = new Cell(data, offset);
                cells.put(offset, c);
            }
            cellList.add(offset);
        }
    }

    //Straight port of: http://xeen.wikia.com/wiki/Sprite_File_Format
    // This macro draws a color in the pallet at colorIndex to the x,y coordinates in the raster
    /*void putPixel(int x, int y, int colorIndex)
    {
        colorData[(y*width+x)*4] = (byte)( (pallet != null) ? pallet.getColor(colorIndex).getBlue()  : colorIndex );
        colorData[(y*width+x)*4+1] = (byte)( (pallet != null) ? pallet.getColor(colorIndex).getGreen() : colorIndex );
        colorData[(y*width+x)*4+2] = (byte)( (pallet != null) ? pallet.getColor(colorIndex).getRed()   : colorIndex );
    }*/

    //Straight port of: http://xeen.wikia.com/wiki/Sprite_File_Format
    // Uncompress a cell color data into a 32-bit raster
//  * cellData     - pointer to the cell's color data
//  * palletData   - pointer to the pallet data as loaded from the .cc file or NULL
//  * colorData    - pointer to a byte array that will contain the RGB conversion of the cell
//  * transparent  - the color to use as transparent



}
