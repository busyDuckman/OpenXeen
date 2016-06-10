package Game.Map;

/**
 * Created by duckman on 15/05/2016.
 */
public class MaMTile
{
    private final int idxBase;
    private final int idxMiddle;
    private final int idxTop;
    private final int idxOverlay;
    //int surface;
    private boolean seen;
    private boolean visited;
    private boolean[] flags;

    public MaMTile(int idxBase, int idxMiddle, int idxTop, int idxOverlay)
    {
        this.idxBase = idxBase;
        this.idxMiddle = idxMiddle;
        this.idxTop = idxTop;
        this.idxOverlay = idxOverlay;
        this.seen = false;
        this.visited = false;
        flags = new boolean[0];
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getIndexBase() {
        return idxBase;
    }

    public int getIndexMiddle() {
        return idxMiddle;
    }

    public int getIndexTop() {
        return idxTop;
    }

    public int getIndexOverlay() {
        return idxOverlay;
    }

    public boolean isSeen() {
        return seen;
    }

    public boolean isVisited() {
        return visited;
    }
}
