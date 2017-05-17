package Toolbox;

//import com.sun.istack.internal.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by duckman on 17/07/2016.
 *
 * Because reversing file formats needs some on the fly code tweaks.
 *
 * Intended use:
 *
 * for(...) {
 *   foo[i] += constA * (3)
 * }
 *
 * HackMe HM = HackMe("Tweak slope")
 * for(...) {
 *   foo[i] += (constA + (0.01*HM.getHack("ConstA +/- 0.01"))) * (3 + HM.getHack("offset +/- 1"))
 * }
 *
 * HM is then hooded up to hotkeys, and the console displays a menu to navigate all hacks.
 * NB: getHack("foo") registers the hack "foo" as 0 on its first call.
 *
 */
public class HackMe
{
    public static final HackMe GlobalInstance = new HackMe("Global Instance");

    Map<String, Integer> namedAdjustments;
    int currentHack;
    boolean silent;
    private String nameOfSet;


    public HackMe(String name) {
        this.nameOfSet = name;
        namedAdjustments = new HashMap<>();
        currentHack = 0;
        silent = false;
    }

    public int getHack(String str)
    {
        if(namedAdjustments.containsKey(str))
        {
            return namedAdjustments.get(str);
        }

        //add adjustment
        namedAdjustments.put(str, 0);
        return 0;
    }

    public void reset()
    {
        namedAdjustments.clear();
        currentHack = 0;
    }

    public void moveToNextHack()
    {
        if(namedAdjustments.size() > 0)
        {
            currentHack = (currentHack + 1) % namedAdjustments.size();
            report();
        }
    }

    public void moveToPreviousHack()
    {
        if(namedAdjustments.size() > 0)
        {
            if(currentHack <= 0)
            {
                currentHack = namedAdjustments.size() - 1;
            }
            else
            {
              currentHack =  (currentHack - 1) % namedAdjustments.size();
            }
            report();
        }
    }

    public void incCurrentHack()
    {
        if(currentHackPosValid())
        {
            String name = getCurrentHackName();
            namedAdjustments.put(name, namedAdjustments.get(name) + 1);
            report();
        }
    }

    public void decCurrentHack()
    {
        if(currentHackPosValid())
        {
            String name = getCurrentHackName();
            namedAdjustments.put(name, namedAdjustments.get(name) - 1);
            report();
        }
    }

    public void report()
    {
        if(!silent)
        {
            //System.out.println(" ---### " + nameOfSet + " ###---");
            if(currentHackPosValid())
            {
                String name = getCurrentHackName();
                System.out.println(name + " is " + namedAdjustments.get(name));
            }
            else
            {
                System.out.println("Hack in invalid state.");
            }
        }
    }

    public void report(String name)
    {
        if(!silent)
        {
            //System.out.println(" ---### " + nameOfSet + " ###---");
            if(namedAdjustments.containsKey(name))
            {
                System.out.println(name + " is " + namedAdjustments.get(name));
            }
            else
            {
                System.out.println("Hack in invalid state.");
            }
        }
    }

    public void reportAll()
    {
        if(!silent)
        {
            System.out.println(" ---### " + nameOfSet + " ###---");
            for (Map.Entry<String, Integer> adj : namedAdjustments.entrySet()) {
                System.out.println(adj.getKey() + " is " + adj.getValue());
            }
        }
    }

    public String getCurrentHackName()
    {
        String[] ffsJava = new String[namedAdjustments.keySet().size()];
        return namedAdjustments.keySet().toArray(ffsJava)[currentHack];
    }

    private boolean currentHackPosValid() {
        return (currentHack >= 0) && (currentHack < namedAdjustments.size());
    }

    public boolean isSilent() {
        return silent;
    }

    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    public void toggleHack(String hackName)
    {
        if(namedAdjustments.containsKey(hackName))
        {
            namedAdjustments.put(hackName, (namedAdjustments.get(hackName)==0)?1:0);
            report(hackName);
        }
        else
        {
            namedAdjustments.put(hackName, 0);
            report(hackName);
        }
    }
}
