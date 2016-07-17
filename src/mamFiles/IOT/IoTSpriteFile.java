package mamFiles.IOT;

import Toolbox.HProperties;
import mamFiles.CCFileFormatException;
import mamFiles.MaMPallet;
import mamFiles.MaMSprite;
import mamFiles.WOX.WOXSpriteFile;

import java.util.Arrays;
import java.util.stream.IntStream;

import static Toolbox.BinaryHelpers.BYTES2INT_lsb;
import static Toolbox.BinaryHelpers.INT;

/**
 * Created by duckman on 16/07/2016.
 *
 * IoT sprite is related to WoXSprite
 */
public class IoTSpriteFile extends WOXSpriteFile
{
    int[] opcodeHistogram;
    public IoTSpriteFile(String name, String key, byte[] data, MaMPallet pal) throws CCFileFormatException {
        super(name, key, data, pal);
    }



    protected void renderToFrame(Cell cell, FrameInfo frame, byte[] data, int offset, MaMSprite sprite)
    {
        // This method is called by the super constructor, so the
        // initialisation of opcodeHistogram is a bit hacky.
        if(opcodeHistogram == null)
        {
            opcodeHistogram = new int[8];
            for (int i = 0; i < opcodeHistogram.length; i++) {
                opcodeHistogram[i] = 0;
            }
        }

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
                    cmd = ( opcode & 0xE0 ) >>> 5;
                    opcodeHistogram[cmd]++;

                    switch( cmd )
                    {
                        case 0:   //(OK) The following len + 1 bytes are stored as indexes into the color table.
                        case 1:   //(??)  The following len + 33 bytes are stored as indexes into the color table.
                            for( i = 0 ; i < opcode + 1 ; i++, xPos++ )
                            {
                                opr1 = INT(data[dp++]); byteCount++;
                                cell.putPixel( xPos, yPos, opr1, frame, sprite);
                            }
                            break;

                        case 2:   // The following byte is an index into the color table, draw it len + 3 times.
                            opr1 = INT(data[dp++]); byteCount++;
                            for( i = 0 ; i < len + 3 ; i++, xPos++ )
                            {
                                cell.putPixel( xPos, yPos, opr1, frame, sprite);
                            }
                            break;

                        case 3:   // Stream copy command.
                            opr1 = BYTES2INT_lsb(data[dp], data[dp+1]);//  *((WORD *)&cellData[dp]);
                            dp += 2; byteCount += 2;
                            for( i = 0 ; i < len + 4 ; i++, xPos++ )
                            {
                                opr2 = INT(data[dp-opr1+i]);
                                cell.putPixel( xPos, yPos, opr2, frame, sprite);
                            }
                            break;

                        case 4:   // The following two bytes are indexes into the color table, draw the pair len + 2 times.
                            opr1 = INT(data[dp++]); byteCount++;
                            opr2 = INT(data[dp++]); byteCount++;
                            for( i = 0 ; i < len + 2 ; i++, xPos += 2 )
                            {
                                cell.putPixel( xPos+0, yPos, opr1, frame, sprite);
                                cell.putPixel( xPos+1, yPos, opr2, frame, sprite);
                            }
                            break;

                        case 5:   // (OK?) Skip len + 1 pixels filling them with the transparent color.
                            xPos += len + 1;
                            break;

                        case 6:  //(OK) Pattern command.
                        case 7:  //(??)
                            // The pattern command has a different opcode format
                            len = opcode & 0x07;
                            cmd = ( opcode >>> 2 ) & 0x0E;

                            opr1 = INT(data[dp++]); byteCount++;
                            for( i = 0 ; i < len + 3 ; i++, xPos++ )
                            {
                                cell.putPixel( xPos, yPos, opr1, frame, sprite);
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

    @Override
    public boolean setProperties(HProperties p) {
        Object[] hist = IntStream.of(opcodeHistogram).boxed().toArray();
        p.setArray("CMD_HIST", hist, Object::toString);

        return super.setProperties(p);
    }
}
