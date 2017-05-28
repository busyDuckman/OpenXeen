package mamFiles.WOX;

import Toolbox.Direction;
import mamFiles.CCFileFormatException;
import mamFiles.MaMCCFileReader;
import mamFiles.MaMScript;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static Toolbox.BinaryHelpers.INT;

/**
 * Created by duckman on 27/05/17.
 */
public class WoxScript extends MaMScript {

    protected static String[] dirConstants = new String[4];

    static {
        // globals that will be made available in lua
        dirConstants[Direction.LEFT.ordinal()] = "DIR_LEFT";
        dirConstants[Direction.UP.ordinal()] = "DIR_UP";
        dirConstants[Direction.RIGHT.ordinal()] = "DIR_RIGHT";
        dirConstants[Direction.DOWN.ordinal()] = "DIR_DOWN";
    }


    public WoxScript(String name, String key, byte[] data) throws CCFileFormatException {
        super(name, key, data);
    }

    public WoxScript(String name, String key, String data) {
        super(name, key, data);
    }

    @Override
    protected String parseScript(byte[] data) throws CCFileFormatException {
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        MaMScriptLine line = lineFromBytes(bis);
        List<MaMScriptLine> currentFunction = new ArrayList<>();
        List<List<MaMScriptLine>> functions = new ArrayList<>();

        //get functions
        while(line != null) {
            // has the current function come to an end.
            if((currentFunction.size() > 0) && !line.follows(currentFunction.get(currentFunction.size()-1))) {
                functions.add(currentFunction);
                currentFunction = new ArrayList<>();
            }

            currentFunction.add(line);
            line = lineFromBytes(bis);
        }

        //add last function
        if(currentFunction.size() > 0) {
            functions.add(currentFunction);
        }

        //get text
        StringBuilder sb = new StringBuilder();
        String nl = System.lineSeparator();



        functions.stream().forEach(F -> {
            sb.append(getFuncCode(F));
            sb.append(nl);
        });

        sb.append("function doMap()" + nl);
        functions.stream().forEach(F -> {
            sb.append("  if " + getFuncCondition(F) + " then" + nl);
            sb.append("    " + getFuncName(F) + "()" + nl);
            sb.append("  end" + nl + nl);
        });
        sb.append("end" + nl + nl);

        return sb.toString();
    }

    private String getFuncCode(List<MaMScriptLine> func) {
        StringBuilder sb = new StringBuilder();
        String nl = System.lineSeparator();
        sb.append("function " + getFuncName(func) + "()" + nl);
        sb.append("print ('in event script: " + getFuncName(func) + "()');");
        func.stream().forEach(L -> sb.append("  -- " + translateLine(L) + nl));

        sb.append("end" + nl);

        return sb.toString();
    }

    private String translateLine(MaMScriptLine line) {
        WoxOpcode op = getWoxOpcode(line);
        String s = line.getLineNumber() + ": ";
        if (op == null) {
            s += "error(\"null command\")";
        }
        else {
            s += op.translate(line);
        }

        return s;
    }

    private WoxOpcode getWoxOpcode(MaMScriptLine line) {
        return Arrays.stream(WoxOpcode.values()).filter(C -> C.code == line.getOpcode()).findFirst().orElse(null);
    }

    private String getFuncName(List<MaMScriptLine> func) {
        int x = func.get(0).getPos().x;
        int y = func.get(0).getPos().y;
        return "script_" + x + "_" + y + "_" + getDescWord(func);
    }

    private String getDescWord(List<MaMScriptLine> func) {
        //TODO: improve this whe I have a better handle on scripting.
        return "" + (Math.abs(func.stream().map(F -> F.toString().hashCode()).mapToInt(H -> (int)H).sum())%10000)
                + "_" + getWoxOpcode(func.get(func.size()-1)).name();
    }

    // Lua code to see if a function is applicable
    private String getFuncCondition(List<MaMScriptLine> func) {
        Set<Direction> dirs = func.get(0).getFromDirections();
        int x = func.get(0).getPos().x;
        int y = func.get(0).getPos().y;

        String s = "_pos:getX() == " + x + " and _pos:getY() == " + y;

        // if not triggerable from all directions
        if(dirs.size() != 4) {
            s += " and (";
            s += dirs.stream()
                     .map(D -> "_dir == " + dirConstants[D.ordinal()])
                     .collect(Collectors.joining(" or "));
            s += ")";
        }

        return s;
    }

    /**
     * From http://xeen.wikia.com/wiki/Event_Scripting
     */
    public MaMScriptLine lineFromBytes(ByteArrayInputStream bis) throws CCFileFormatException {
        int len = bis.read();
        if(len < 0) {
            return null; //no more data
        }

        if (len < 5) {
            throw new CCFileFormatException("Script line gave invalid length");
        }

        Point pos = new Point(bis.read(), bis.read());
        Set<Direction> fromDirections = new HashSet<>();
        int dirRaw = bis.read();
        switch (dirRaw) {
            case 0:
                fromDirections.add(Direction.UP);
                break;
            case 1:
                fromDirections.add(Direction.DOWN);
                break;
            case 2:
                fromDirections.add(Direction.LEFT);
                break;
            case 3:
                fromDirections.add(Direction.RIGHT);
                break;
            case 4:  //wiki suggests all is 4
                Arrays.stream(Direction.values()).forEach(D -> fromDirections.add(D));
                break;
            default:
                throw new CCFileFormatException("Unknown direction: " + dirRaw);
        }

        int lineNumber = bis.read();
        int opcode = bis.read();

        int argLen = len - 5;
        byte[] args = new byte[argLen];
        if (argLen > 0) {
            bis.read(args, 0, argLen);
        }

        return new MaMScriptLine(len, pos, fromDirections, lineNumber, opcode, args);
    }
}


enum WoxOpcode
{
    Display(0x01),
    DoorTextSml(0x02),
    DoorTextLrg(0x03),
    SignTxt(0x04),
    NPC(0x05),
    PlayFX(0x06),
    Teleport_7(0x07),

    If_8(0x08) {
        @Override
        public String translate(MaMScript.MaMScriptLine line) {
            return "  -- TODO: IF";
        }
    },

    If_9(0x09){
        @Override
        public String translate(MaMScript.MaMScriptLine line) {
            return "  -- TODO: IF";
        }
    },

    If_A(0x0A){
        @Override
        public String translate(MaMScript.MaMScriptLine line) {
            return "  -- TODO: IF";
        }
    },

    MoveObj(0x0B),
    TakeOrGive_C(0x0C),
    NoAction_D(0x0D),
    Remove(0x0E),
    SetChar(0x0F),
    Spawn(0x10),
    DoTownEvent(0x11),

    Exit(0x12){
        @Override
        public String translate(MaMScript.MaMScriptLine line) {
            return "return";
        }
    },

    AlterMap(0x13),
    GiveExtended(0x14),
    ConfirmWord(0x15),
    Damage(0x16),
    JumpRnd(0x17),
    AlterEvent(0x18),
    CallEvent(0x19),
    Return(0x1A),
    SetVar(0x1B),
    TakeOrGive_1C(0x1C),
    TakeOrGive_1D(0x1D),
    CutsceneEndClouds(0x1E),
    Teleport(0x1F),
    WhoWill(0x20),
    RndDamage(0x21),
    MoveWallObj(0x22),
    AlterCellFlag(0x23),
    AlterHed(0x24),
    DisplayStat(0x25),
    TakeOrGive(0x26),
    SeatTextSml(0x27),
    PlayEventVoc(0x28),
    DisplayBottom(0x29),
    IfMapFlag(0x2A),
    SelRndChar(0x2B),
    GiveEnchanted(0x2C),
    ItemType(0x2D),
    MakeNothingHere(0x2E),
    NoAction(0x2F),
    ChooseNumeric(0x30),
    DisplayBottomTwoLines(0x31),
    DisplayLarge(0x32),
    ExchObj(0x33),
    FallToMap(0x34),
    DisplayMain(0x35),
    Goto(0x36) {
        @Override
        public String translate(MaMScript.MaMScriptLine line) {
            return "goto " + line.getArgs()[0];
        }
    },

    ConfirmWord2(0x37),
    GotoRandom(0x38),
    CutsceneEndDarkside(0x39),
    CutsceneEndWorld(0x3A),
    FlipWorld(0x3B),
    PlayCD(0x3C);

    int code;

    WoxOpcode(int code) {
        this.code = code;
    }

    public String translate(MaMScript.MaMScriptLine line) {
        if(line.getArgs().length > 0) {
            return this.name()
                    + " (" +
                    IntStream.range(0, line.getArgs().length)
                            .map(i -> line.getArgs()[i])
                            .mapToObj(I -> "" + I)
                            .collect(Collectors.joining(", "))
                    + ")";
        }
        else {
            return this.name() + " ()";
        }
    }
}