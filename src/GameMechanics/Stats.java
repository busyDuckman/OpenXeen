package GameMechanics;

/**
 * Created by duckman on 10/06/2016.
 */
public abstract class Stats
{
    private abstract class StatBasic extends Stats
    {
        @Override public int getMin() {return 0;}
        @Override public int getMax() {return 1024;}
        @Override protected int getDescriptionMax() {return Math.min(255, getMax());}
    }

    public class Might extends StatBasic
    {
        @Override protected final String[] getDescriptions() { return new String[]{"Weak", "Tough", "Awesome"};}
    }

    public class Age extends StatBasic
    {
        @Override public int getMax() {return 120;}
        @Override protected final String[] getDescriptions() {
            return new String[]{"Baby, Child, Youth, Adult, Veteran, Old, Ancient"};
        }
    }

    public abstract int getMin();
    public abstract int getMax();
    protected abstract int getDescriptionMax();
    protected abstract String[] getDescriptions();
    private int[] descriptionBreaks;

    Stats()
    {
        String[] desc = getDescriptions();
        descriptionBreaks = new int[desc.length];
        double step = ((getDescriptionMax()-getMin()) / (double)desc.length);
        for (int i = 0; i < desc.length; i++) {
            descriptionBreaks[i] = (int)(step * i);
        }
    }

    public String describe(int level)
    {
        int i=0;
        for (;i < descriptionBreaks.length; i++) {
            if(descriptionBreaks[i] > level) {
                break;
            }
        }

        // Get previous description break.
        // Should also bring i, into a valid array range for
        // the last description.
        i = Math.max(0, i-1);

        return getDescriptions()[i];
    }
}

//public enum Stats
//{
//    MIGHT (0, 255, "Weak, Tough, Awesome"),
//    AGE (0,100, "Baby, Child, Youth, Adult, Veteran, Old, Ancient");
//
//    int[] descriptionBreaks;
//    String[] descriptions;
//
//    Stats(int descMin, int descMax, String descriptionList)
//    {
//        String[] tokens = descriptionList.split(",");
//        for (int i = 0; i < tokens.length; i++) {
//            descriptions[i] = tokens[i].trim();
//            descriptionBreaks[i] = (int)(((descMax-descMin) / (double)tokens.length) * i);
//        }
//    }
//
//    public String describe(int level)
//    {
//        int i=0;
//        for (;i < descriptionBreaks.length; i++) {
//            if(descriptionBreaks[i] > level) {
//                break;
//            }
//        }
//
//        // Get previous description break.
//        // Should also bring i, into a valid array range for
//        // the last description.
//        i = Math.max(0, i-1);
//
//        return descriptions[i];
//    }
//}
