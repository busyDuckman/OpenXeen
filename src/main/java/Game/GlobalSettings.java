package Game;

import Toolbox.FileHelpers;

import java.awt.*;
import java.io.File;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by duckman on 18/06/2016.
 */
public enum GlobalSettings
{
    INSTANCE;

    boolean debugMode = true;
    boolean rebuildProxies = false;
    private boolean disableHUD = false;
    private boolean discoverFileNames = false;
    private volatile ResourceBundle localText = null;
    private double renderingScale = 2.0;
    private String gameDir = "";
    private transient Font messageFont = null;
    private transient Font headingFont = null;
    private int spriteDebugAlpha = -1;

    public boolean debugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    /**
     * True if ccFiles should save proxie files when loaded.
     */
    public boolean rebuildProxies() {
        return rebuildProxies;
    }
    public void setRebuildProxies(boolean rebuildProxies) {
        this.rebuildProxies = rebuildProxies;
    }

    public void setDisableHUD(boolean disableHUD) {
        this.disableHUD = disableHUD;
    }

    public boolean isHUDDisabled() {
        return disableHUD;
    }

    public boolean discoverFileNames() {
        return discoverFileNames;
    }

    public ResourceBundle getLocalText() {
        if(localText == null) {
            localText = ResourceBundle.getBundle("openXeen", Locale.ENGLISH);
        }
        return localText;
    }

    public double getRenderingScale() {
        return renderingScale;
    }

    public void setDiscoverFileNames(boolean discoverFileNames) {
        this.discoverFileNames = discoverFileNames;
    }

    public void setLocalText(ResourceBundle localText) {
        this.localText = localText;
    }

    public void setRenderingScale(double renderingScale) {
        this.renderingScale = renderingScale;
        this.headingFont = null;
        this.messageFont = null;
    }

    public void setGameDir(String gameDir) {
        this.gameDir = gameDir;
    }

    public String getGameDir() {
        return (gameDir == null) ? "" : gameDir;
    }

    public String relativeToGameDir(String relativePath) {
        return FileHelpers.join(getGameDir(), relativePath);
    }

    public String migDebugText() {
        return ""; //debugMode() ? ", debug 20" : "";
    }

    public Font getMessageFont() {
        if(messageFont == null) {
            messageFont = new Font("Dialog.plain", Font.PLAIN, (int)(8.0*getRenderingScale()));
        }
        return messageFont;
    }

    public Font getHeadingFont() {
        if(headingFont == null) {
            headingFont = new Font("Dialog.plain", Font.BOLD, (int)(10.0*getRenderingScale()));
        }
        return headingFont;
    }

    public int getSpriteDebugAlpha() {
        return spriteDebugAlpha;
    }

    public void setSpriteDebugAlpha(int spriteDebugAlpha) {
        // negative value indicates no alpha
        this.spriteDebugAlpha = Math.min(spriteDebugAlpha, 255);
    }
}
