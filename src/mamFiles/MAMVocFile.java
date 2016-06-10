package mamFiles;

import Toolbox.FileHelpers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;

/**
 * Created by duckman on 22/05/2016.
 */
public class MAMVocFile extends MAMFile
{
    byte[] vocFileData;

    //TODO: put this in config file.
    static String pathtoFFMpeg = "x:\\utils\\";

    public MAMVocFile(String name, byte[] data)
    {
        super(name);
        vocFileData = data;
    }

    public boolean convertToWav(String vocFile)
    {
        String wavFile = FileHelpers.changeExtesion(vocFile, "wav");

        if(!FileHelpers.fileExists(wavFile))
        {
            try {
                String exeFile = FileHelpers.join(pathtoFFMpeg, "ffmpeg.exe");
                if(FileHelpers.fileExists(exeFile))
                {

                    ProcessBuilder builder = new ProcessBuilder(exeFile, "-i", vocFile, wavFile);
                    builder.redirectErrorStream(true);
                    final Process process = builder.start();
                    int r = process.waitFor();

                    return (r == 0) && (FileHelpers.fileExists(wavFile));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return false;
        }
        else
        {
            return true;
        }
    }

    public void play()
    {
       /* URL url = new URL(
                "http://pscode.org/media/leftright.wav");
        Clip clip = AudioSystem.getClip();
        // getAudioInputStream() also accepts a File or InputStream
        AudioInputStream ais = AudioSystem.
        getAudioInputStream( ,url );
        clip.open(ais);
        clip.start();

        */
    }


    @Override
    public String suggestProxyFileName() {
        return name+".wav";
    }

    @Override
    public boolean saveProxy(String path)
    {
        String vocFile = FileHelpers.changeExtesion(path, "voc");
        FileHelpers.saveBytes(vocFile, vocFileData);
        return convertToWav(vocFile);
    }

    public MAMVocFile fromVocFile(String path)
    {
        return new MAMVocFile(FileHelpers.getFileNameTillFirstDot(path), FileHelpers.readAllBytes(path));
    }
}
