package Toolbox;

/**
 * Created by duckman on 1/07/2016.
 *
 * This is perhaps an unorthodox class.
 * I created it to work around some awkwardness in java collection.
 * After all, one good awkwardness deserves another.
 *
 * Concept by example
 *    A Tag<Integer, String> acts just like an Integer.
 *
 *    Tag<Integer, String> myNum = new Tag<>(2, "two");
 *    if(myNum == 2)
 *    {
 *       System.out.printline("It worked 2 = " +  myNum.getTag());
 *    }
 *
 */
public final class Tag<K, T>
{
    private final K key;
    private final T tag;

    public Tag(K key, T tag) {
        this.key = key;
        this.tag = tag;
    }

    public final K getKey() {
        return key;
    }

    public final T getTag() {
        return tag;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;

        if(getClass() == obj.getClass())
        {
            return key.equals(((Tag<?, ?>) obj).key);
        }
        else
        {
            return (key.equals(obj));
        }
    }

    @Override
    public final int hashCode() {
        return key.hashCode();
    }

    @Override
    public final String toString() {
        return key.toString();
    }


}
