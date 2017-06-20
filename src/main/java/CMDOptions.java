/**
 * Created by duckman on 19/05/17.
 */

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

import java.util.Arrays;
import java.util.Comparator;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import static Toolbox.Misc.ifNull;

/**
 * A helper for working with commons CLI.
 *
 * Items in this enum will be printed in the same order they are defined.
 */
public enum CMDOptions {
    GAME_MM3 ("mm3", false, "Runs M&M III, Isles of Terra"),
    GAME_MM4 ("mm4", false, "Runs M&M IV, Clouds of Xeen"),
    GAME_MM5 ("mm5", false, "Runs M&M V, Darkside of Xeen"),
    GAME_WOX ("wox", false, "Runs M&M IV + V, World of Xeen"),

    DIR      ("dir", true, "Source directory for M&M game"),
    REBUILD_PROXY ("rebuild_proxy", "p", false, "Extracts all data in the original ccFiles to a proxy file."),

    SCALE    ("scale", "s", true, "A zoom factor for graphics rendering (default 2)."),

    DEBUG   ("debug", "d", false, "Runs application in debug mode (recommended for developers)."),
    NO_CACHE ("no_cache", false, "Disable caching of files (for debugging/editing)."),
    NO_HUD  ("no_hud", false, "Disables rendering of the user interface, showing just the world."),
    SPRITE_ALPHA  ("sprite_alpha", true, "Forces an alpha for many sprites (0-255).");

    String name;
    String shortName;
    String desc;
    boolean hasArg;
    Option option;

    CMDOptions(String name, String shortName, boolean hasArg, String desc) {
        this.name = name;
        this.shortName = shortName;
        this.hasArg = hasArg;
        this.desc = desc;

        if(this.shortName == null) {
            this.option = new Option(name, hasArg, desc);
        }
        else {
            this.option = new Option(shortName, name, hasArg, desc);
        }
    }

    CMDOptions(String name, boolean hasArg, String desc) {
        this(name, null, hasArg, desc);
    }

    public boolean isSet(CommandLine cmd) {
        return ((name != null) && cmd.hasOption(name)) || ((shortName != null) && cmd.hasOption(shortName));
    }

    public String getArgOrDefault(CommandLine cmd, String onMissing) {
        String arg = getArg(cmd);

        return (arg == null) ? onMissing : arg;
    }

    public double getArgOrDefault(CommandLine cmd, double onMissing) {
        String arg = getArg(cmd);

        return (arg == null) ? onMissing : Double.parseDouble(arg);
    }

    public int getArgOrDefault(CommandLine cmd, int onMissing) {
        String arg = getArg(cmd);

        return (arg == null) ? onMissing : Integer.parseInt(arg);
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
                .sorted(Comparator.comparingInt(Enum::ordinal))
                .forEach(C -> options.addOption(C.option));
        return options;
    }

    public static int compare(Option a, Option b) {
        // This is not the logical and direct way of approaching the problem, but java.
        Option[] options = Arrays.stream(CMDOptions.values()).map(C -> C.option).toArray(Option[]::new);
        OptionalInt aPos = IntStream.range(0, options.length).filter(I -> options[I].equals(a)).findFirst();
        OptionalInt bPos = IntStream.range(0, options.length).filter(I -> options[I].equals(b)).findFirst();

        if(aPos.isPresent() && bPos.isPresent()) {
            return Integer.compare(aPos.getAsInt(), bPos.getAsInt());
        }

        else return ifNull(a.getLongOpt(),"").compareTo(ifNull(b.getLongOpt(), ""));
    }
}
