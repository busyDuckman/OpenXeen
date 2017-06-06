package mamFiles.WOX;

import Game.WoxOpcode;
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

        sb.append("require 'opLib'"+nl);

        functions.stream().forEach(F -> {
            sb.append(getFuncCode(F));
            sb.append(nl);
        });

        sb.append("function doMap()" + nl);
        sb.append("  print ('mapX=', _pos:getX(), ', mapY=',_pos:getY());" + nl);
        sb.append(nl);
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
        sb.append("  print 'in event script: " +  getFuncName(func) + "(...)'" + nl);
        func.stream().forEach(L -> sb.append("  " + translateLine(L) + nl));

        sb.append("end" + nl);

        return sb.toString();
    }

    private String translateLine(MaMScriptLine line) {
        WoxOpcode op = getWoxOpcode(line);
        String s = "::line" + line.getLineNumber() + ":: ";
        if(line.getLineNumber() < 10) {
            s += " "; // pad the script to align functions
        }
        if (op == null) {
            s += "error(\"null command\")";
        }
        else {
            s += op.translate(line);
        }

        return s;
    }

    private WoxOpcode getWoxOpcode(MaMScriptLine line) {
        return Arrays.stream(WoxOpcode.values()).filter(C -> C.getCode() == line.getOpcode()).findFirst().orElse(null);
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

