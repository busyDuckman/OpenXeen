/**
 * Created by duckman on 19/05/17.
 */

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;

import java.util.Arrays;

/**
 * A helper for working with commons CLI.
 *
 * Beyond the basics, it allows me to direct the order of printing in the help output.
 */
public enum CMDOptions {
    GAME_MM3 ("mm3", false, "Runs M&M III, Isles of Terra"),
    GAME_MM4 ("mm4", false, "Runs M&M IV, Clouds of Xeen"),
    GAME_MM5 ("mm5", false, "Runs M&M V, Darkside of Xeen"),
    GAME_WOX ("wox", false, "Runs M&M IV + V, World of Xeen"),

    DIR      ("dir", true, "Source directory for M&M game"),
    NO_CACHE ("no_cache", false, "Disable caching of files (for debuging/editing).");

    String name;
    String shortName;
    String desc;
    boolean hasArg;

    CMDOptions(String name, String shortName, boolean hasArg, String desc) {
        this.name = name;
        this.shortName = shortName;
        this.hasArg = hasArg;
        this.desc = desc;
    }

    CMDOptions(String name, boolean hasArg, String desc) {
        this(name, null, hasArg, desc);
    }

    public boolean isSet(CommandLine cmd) {
        return ((name != null) && cmd.hasOption(name)) || ((shortName != null) && cmd.hasOption(shortName));
    }

    public String getArg(CommandLine cmd) {
        if ((name != null) && cmd.hasOption(name)) {
            return cmd.getOptionValue(name);
        }

        if ((shortName != null) && cmd.hasOption(shortName)) {
            return cmd.getOptionValue(shortName);
        }

        return null;
    }

    public static Options getOptions() {
        Options options = new Options();
        Arrays.stream(CMDOptions.values())
                .sorted((a,b) -> Integer.compare(a.ordinal(), b.ordinal()))
                .forEach(C -> C.addToOptions(options));
        return options;
    }

    private void addToOptions(Options options) {
        if(shortName != null) {
            options.addOption(name, hasArg, desc);
        }
        else {
            options.addOption(shortName, name, hasArg, desc);
        }
    }
}