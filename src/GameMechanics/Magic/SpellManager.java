package GameMechanics.Magic;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by duckman on 31/05/2016.
 */
public class SpellManager
{
    protected ISpell[] AllSpells;
    //AcidSpray("Acid Spray", castAcidSpray);

    //Spells(String name, Spell ) {
    //}

    public static String normaliseSpellName(String name)
    {
        if(name == null)
        {
            return "";
        }

        return name.chars()
                .mapToObj(i -> (char)i)
                .filter(Character::isLetterOrDigit)
                .map(Object::toString)
                .collect(Collectors.joining())
                .toUpperCase();

    }
}



