package Rendering;

import Toolbox.HProperties;
import Toolbox.IHasProperties;

public class AnimationSettings implements IHasProperties
{
    int numberOfFrames;
    int milliSecondsPerFrame;
    boolean pingPongAnimation;
    boolean repeatAnimation;

    public AnimationSettings(
            int numberOfFrames,
            int msPerFrame,
            boolean pingPongAnimation,
            boolean repeatAnimation)
    {
        this.numberOfFrames = numberOfFrames;
        this.milliSecondsPerFrame = msPerFrame;
        this.pingPongAnimation = pingPongAnimation;
        this.repeatAnimation = repeatAnimation;
    }

    public int getNumberOfFrames() {
        return numberOfFrames;
    }

    public double getMilliSecondsPerFrame() {
        return milliSecondsPerFrame;
    }

    public boolean isPingPongAnimation() {
        return pingPongAnimation;
    }

    public boolean isRepeatAnimation() {
        return repeatAnimation;
    }

    public final int resolveFrameNumber(long timeMS)
    {
        int totalDuration = milliSecondsPerFrame * numberOfFrames;
        int msIntoAnimation = (int)(timeMS % totalDuration);
        int frameNumber = msIntoAnimation / milliSecondsPerFrame;
        return frameNumber;
    }

    //------------------------------------------------------------------------------------------------------------------
    // IHasProperties
    //------------------------------------------------------------------------------------------------------------------
    @Override
    public boolean getProperties(HProperties p)
    {
        this.numberOfFrames = Integer.parseInt(p.getProperty("AnimationSettings.numberOfFrames"));
        this.milliSecondsPerFrame = Integer.parseInt(p.getProperty("AnimationSettings.milliSecondsPerFrame"));
        this.pingPongAnimation = Boolean.parseBoolean(p.getProperty("AnimationSettings.pingPongAnimation"));
        this.repeatAnimation = Boolean.parseBoolean(p.getProperty("AnimationSettings.repeatAnimation"));
        return true;
    }

    @Override
    public boolean setProperties(HProperties p) {
        p.setProperty("AnimationSettings.numberOfFrames", String.valueOf(this.numberOfFrames));
        p.setProperty("AnimationSettings.milliSecondsPerFrame", String.valueOf(this.milliSecondsPerFrame));
        p.setProperty("AnimationSettings.pingPongAnimation", String.valueOf(this.pingPongAnimation));
        p.setProperty("AnimationSettings.repeatAnimation", String.valueOf(this.repeatAnimation));
        return true;
    }
}
