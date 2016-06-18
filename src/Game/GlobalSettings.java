package Game;

/**
 * Created by duckman on 18/06/2016.
 */
public enum GlobalSettings
{
    INSTANCE;

    boolean debugMode = true;

    public boolean debugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
