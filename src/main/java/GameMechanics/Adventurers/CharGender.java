package GameMechanics.Adventurers;

/**
 * Created by duckman on 31/05/2016.
 */
public enum CharGender
{
    //Gender has no effect other than changing sentence structure.

    Male(0,"Male", "his"), Female(1, "Female", "her");

    CharGender(int id, String desc, String hisHer)
    {
        this.id = id;
        this.desc = desc;
        this.hisHer = hisHer;
    }

    int id; String desc; String hisHer;
}
