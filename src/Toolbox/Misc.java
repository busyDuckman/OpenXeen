package Toolbox;

/**
 * Created by duckman on 19/06/2016.
 */
public class Misc
{
    /**
     * I like
     *   name = ifNull(someFunctionCall(), "bob")
     * not
     *    name = someFunctionCall();
     *    name = (name != null) ? name : "bob";
     */
    public static <T> T ifNull(T what, T ifNull)
    {
        return (what == null) ? ifNull : what;
    }
}
