package GameMechanics;

/**
 * Created by duckman on 10/06/2016.
 */
public enum Stats
{
    MIGHT (0, 255, "Weak, Tough, Awesome"),
    AGE (0,100, "Baby, Child, Youth, Adult, Veteran, Old, Ancient");

    int[] descriptionBreaks;
    String[] descriptions;

    Stats(int descMin, int descMax, String descriptionList)
    {
        String[] tokens = descriptionList.split(",");
        for (int i = 0; i < tokens.length; i++) {
            descriptions[i] = tokens[i].trim();
            descriptionBreaks[i] = (int)(((descMax-descMin) / (double)tokens.length) * i);
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

        return descriptions[i];
    }
}
