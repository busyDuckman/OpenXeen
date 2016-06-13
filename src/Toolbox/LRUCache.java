package Toolbox;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * Created by duckman on 11/06/2016.
 * This is that standard LRU trick.
 *
 * Not sure, but I think this code originates from the sun javadocs.
 */
public class LRUCache  < K, V > extends LinkedHashMap < K, V > {
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity+1, 1.0f, true);
        this.capacity = capacity;
    }

    protected boolean removeEldestEntry(Entry entry) {
        return (size() > this.capacity);
    }
}