package mamFiles.IOT;

import Toolbox.ArrayHelpers;
import Toolbox.BinaryHelpers;
import mamFiles.CCFileFormatException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by duckman on 9/07/2016.
 *
 * NB: This is a straight port of https://github.com/rwfpl/rewolf-mm3-dumper/blob/master/rwf_lzhuf.cpp
 * The original code for lzhuf is from some amiga stuff http://stjarnhimlen.se/snippets/lzhuf.c
 *
 * ReWolf is the guy who cracked the MM3.CC file: http://blog.rewolf.pl/blog/?p=1202
 */
public class CompressionLZHuf
{
    private static final byte[] d_len = new byte[]
    {
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
        4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
        5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5,
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6,
        7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
        7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
        7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7,
        8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8
    };

    private static final byte[] d_code = new byte[]
    {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
        2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,
        3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
        4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5,
        6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7,
        8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9,
        10, 10, 10, 10, 10, 10, 10, 10, 11, 11, 11, 11, 11, 11, 11, 11,
        12, 12, 12, 12, 13, 13, 13, 13, 14, 14, 14, 14, 15, 15, 15, 15,
        16, 16, 16, 16, 17, 17, 17, 17, 18, 18, 18, 18, 19, 19, 19, 19,
        20, 20, 20, 20, 21, 21, 21, 21, 22, 22, 22, 22, 23, 23, 23, 23,
        24, 24, 25, 25, 26, 26, 27, 27, 28, 28, 29, 29, 30, 30, 31, 31,
        32, 32, 33, 33, 34, 34, 35, 35, 36, 36, 37, 37, 38, 38, 39, 39,
        40, 40, 41, 41, 42, 42, 43, 43, 44, 44, 45, 45, 46, 46, 47, 47,
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63
    };

    //the following are words in the original code
    private static final int LZHUF_MAX_FREQ	    = 0x8000;
    private static final int LZHUF_N			= 0x1000;
    private static final int LZHUF_F			= 0x3c;
    private static final int LZHUF_THRESHOLD	= 2;
    private static final int LZHUF_N_CHAR		= 0x100 - LZHUF_THRESHOLD + LZHUF_F;		// 0x13A
    private static final int LZHUF_T			= LZHUF_N_CHAR * 2 - 1;						// 0x273
    private static final int LZHUF_R			= LZHUF_T - 1;								// 0x272

    public static byte[] rwf_lzhuf_decompress(byte[] inBuf,
                             int outSize,
                             byte initValue) throws CCFileFormatException
    {
        int outBufIndex = 0;
        byte[] outBuf = new byte[outSize];
        try
        {
            //uint8_t* outBufBegin = outBuf;
            int[] freq = new int[LZHUF_T + 1];
            int[] prnt = new int[LZHUF_T + LZHUF_N_CHAR];
            int[] son  = new int[LZHUF_T];
            int cache = 0x8000;
            Arrays.setAll(freq, V -> 0);
            Arrays.setAll(prnt, V -> 0);
            Arrays.setAll(son, V -> 0);

            int inBufPos = 0;


            // huffmann init
            for (int i = 0; i < LZHUF_N_CHAR; i++)
            {
                freq[i] = 1;
                prnt[i + LZHUF_T] = i;
                son[i] = i + LZHUF_T;
            }
            for (int i = 0, j = LZHUF_N_CHAR; j < LZHUF_T; i+=2, j++)
            {
                freq[j] = freq[i] + freq[i + 1];
                son[j] = i;
                prnt[i] = j;
                prnt[i + 1] = j;
            }
            prnt[LZHUF_R] = 0;
            freq[LZHUF_T] = 0xFFFF;

            int textBufIndex = LZHUF_N - LZHUF_F;
            byte[] textBuf = new byte[LZHUF_N];
            for (int i = 0; i < LZHUF_N - LZHUF_F; i++) {
                textBuf[i] = initValue;
            }

            while (outBufIndex < outBuf.length)
            {
                // decode char
                int c = son[LZHUF_R];
                while (c < LZHUF_T)
                {
                    int bit = cache >>> 0xF;
                    cache = (cache << 1) & 0xffff;
                    if (0 == cache)
                    {
                        int tmpWord;
                        if((inBufPos < 0) || ((inBufPos+1) >= inBuf.length))
                        {
                            System.out.println("LZH broke again - check alpha");
                            tmpWord = BinaryHelpers.BYTES2INT_lsb(inBuf[inBufPos], (byte)0);
                        }
                        else
                        {
                            tmpWord = BinaryHelpers.BYTES2INT_lsb(inBuf[inBufPos], inBuf[inBufPos+1]);
                        }
                        int tmp = rotl16(tmpWord, 8);
                        inBufPos += 2;
                        bit = tmp >>> 0xF;
                        cache = ((tmp << 1)&0xffff) | 1;
                    }
                    c += bit;
                    if((c < 0) || (c >= son.length))
                    {
                        System.out.println("LZH broke again - check bravo");
                    }
                    c = son[c];
                }
                c -= LZHUF_T;

                if (freq[LZHUF_R] == LZHUF_MAX_FREQ)
                {
                    // ReWolf noted:
                    // tree reconstruction (this part is taken 1:1 from original LZHUF, as
                    // it is not triggered for files from MM3.CC, so I decided to not reverse
                    // engineer it. When it was clear that it is LZHUF, I just put it here to
                    // have complete decmpression just in case)

                    for (int i = 0, j = 0; i < LZHUF_T; i++)
                    {
                        if (son[i] >= LZHUF_T)
                        {
                            freq[j] = (freq[i] + 1) / 2;
                            son[j] = son[i];
                            j++;
                        }
                    }

                    for (int i = 0, j = LZHUF_N_CHAR; j < LZHUF_T; i += 2, j++)
                    {
                        int k = i + 1;
                        int f = freq[j] = freq[i] + freq[k];
                        for (k = j - 1; f < freq[k]; k--);
                        k++;
                        int l = j - k;
                        memmove(freq, k + 1, freq, k, l * 2);
                        freq[k] = f;
                        memmove(son, k + 1, son, k, l * 2);
                        son[k] = i;
                    }

                    for (int i = 0; i < LZHUF_T; i++)
                    {
                        int k = son[i];
                        if (k >= LZHUF_T)
                            prnt[k] = i;
                        else
                            prnt[k] = prnt[k + 1] = i;
                    }
                }

                //update tree
                int b = prnt[c + LZHUF_T];
                do
                {
                    int k = ++freq[b];
                    if (k > freq[b + 1])
                    {
                        int l = b + 1;
                        do { l++; } while (k > freq[l]);

                        l--;
                        freq[b] = freq[l];
                        freq[l] = k;

                        int i = son[b];
                        prnt[i] = l;
                        if (i < LZHUF_T)
                            prnt[i + 1] = l;

                        int j = son[l];
                        son[l] = i;
                        prnt[j] = b;
                        if (j < LZHUF_T)
                            prnt[j + 1] = b;

                        son[b] = j;
                        b = l;
                    }
                    b = prnt[b];
                }
                while (b != 0);

                if (c < 0x100)
                {
                    //*outBuf++ = (uint8_t)c;
                    outBuf[outBufIndex] = (byte)c;
                    outBufIndex++;

                    //textBuf[textBufIndex++] = (uint8_t)c;
                    textBuf[textBufIndex++] = (byte)c;

                    textBufIndex &= (LZHUF_N - 1);
                    //outBufIndex++; //dolphin
                    continue;
                }

                int bt = 0;
                for (int i = 0; i < 8; i++)
                {
                    int bit = cache >>> 0xF;
                    cache = (cache << 1) & 0xffff;
                    if (cache == 0)
                    {
                        int tmpWord;
                        if((inBufPos < 0) || ((inBufPos+1) >= inBuf.length))
                        {
                            System.out.println("LZH broke again - check delta");
                            tmpWord = BinaryHelpers.BYTES2INT_lsb(inBuf[inBufPos], (byte)0);
                        }
                        else
                        {
                            tmpWord = BinaryHelpers.BYTES2INT_lsb(inBuf[inBufPos], inBuf[inBufPos+1]);
                        }
                        cache = rotl16(tmpWord, 8);
                        inBufPos += 2;
                        bit = 1;
                        for (int j = 0; j < (8 - i); j++)
                        {
                            int tmp_bit = cache >>> 0xF;
                            cache = (cache << 1) & 0xffff;
                            cache |= bit;
                            bit = tmp_bit;
                            tmp_bit = bt >>> 0xF;
                            bt = (bt << 1) & 0xffff;
                            bt |= bit;
                            bit = tmp_bit;
                        }
                        break;
                    }
                    bt = (bt << 1) & 0xffff;
                    bt |= bit;
                }

                int dc = (d_code[bt] << 6) & 0xffff;
                int i = d_len[bt] - 1;
                while (--i != 0)
                {
                    int bit = cache >>> 0xF;
                    cache = (cache << 1) & 0xffff;
                    if (cache == 0)
                    {
                        int tmpWord;
                        if((inBufPos < 0) || ((inBufPos+1) >= inBuf.length))
                        {
                            System.out.println("LZH broke again - check echo");
                            tmpWord = BinaryHelpers.BYTES2INT_lsb(inBuf[inBufPos], (byte)0);
                        }
                        else
                        {
                            tmpWord = BinaryHelpers.BYTES2INT_lsb(inBuf[inBufPos], inBuf[inBufPos+1]);
                        }
                        cache = rotl16(tmpWord, 8);
                        inBufPos += 2;
                        bit = cache >>> 0xF;
                        cache = (cache << 1) & 0xffff;
                        cache |= 1;
                    }
                    bt = (bt << 1) & 0xffff;
                    bt |= bit;
                }
                dc |= (bt & 0x3F);
                int textBufIndex2 = textBufIndex - dc - 1;
                for (int r = (c - 0xFD); r > 0; r--)
                {
                    textBufIndex2 &= 0xFFF;
                    byte tmp = textBuf[textBufIndex2];
                    //*outBuf++ = tmp;
                    outBuf[outBufIndex] = tmp;
                    outBufIndex++; //dolphin

                    textBuf[textBufIndex++] = tmp;
                    textBufIndex &= (LZHUF_N - 1);
                    //outBufIndex++;
                    textBufIndex2++;
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new CCFileFormatException("Error decompressing lhz", ex);
        }

        //create final array
//        byte[] finalData = new byte[outBufIndex];
//        for(int i=0; i<outBufIndex; i++)
//        {
//            finalData[i] = outBuf[i];
//        }
//        return finalData;
        return outBuf;
    }

    /**
     * Must handle src and dest being the same array, with movement across overlapping areas
     */
    private static void memmove(int[] src, int scrOffset, int[] dest, int destOffset, int lenInBytes)
    {
        for(int i=0; i< lenInBytes; i++)
        {
            dest[i+destOffset] = src[i+scrOffset];
        }
    }

    private static int rotl16(int c, int val) throws CCFileFormatException {
        //rotl16 just appears to be used for byte swapping in this code
        if(val == 8)
        {
            return BinaryHelpers.BYTES2INT_msb((byte)(c&0xff), (byte)((c>>>8)&0xff));
        }
        CCFileFormatException.notSupposedToBeHere();
        //There is this... java.lang.Integer.rotateLeft(
        return 0;
    }
}
