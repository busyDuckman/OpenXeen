package mamFiles;

import Game.GlobalSettings;
import Game.IScript;
import Game.MaMGame;
import Game.Map.MaMWorld;
import Toolbox.BinaryHelpers;
import Toolbox.Direction;
import Toolbox.FileHelpers;
import Toolbox.IHasProperties;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.*;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

/**
 * Created by duckman on 27/05/17.
 */
public class MaMScript extends MAMFile implements IScript<MaMMazeFile>, IHasProperties {
    String script;

    /**
     * One raw line in a script file
     */
    public static class MaMScriptLine
    {
        int len;
        Point pos;
        Set<Direction> fromDirections;
        int lineNumber;
        int opcode;
        byte[] args;

        public MaMScriptLine(int len, Point pos, Set<Direction> fromDirections, int lineNumber, int opcode, byte[] args) throws CCFileFormatException {
            this.len = len;
            this.pos = pos;
            this.fromDirections = fromDirections;
            this.lineNumber = lineNumber;
            this.opcode = opcode;
            this.args = args;

            if((len < 0)||(pos.x<0)||(pos.y<0)||(lineNumber<0)||(opcode<0)) {
                throw new CCFileFormatException("Bad script line: " + this.toString());
            }
        }

        @Override
        public String toString() {
            return "MaMScriptLine{" +
                    "len=" + len +
                    ", pos=" + pos +
                    ", fromDirections=" + fromDirections +
                    ", lineNumber=" + lineNumber +
                    ", opcode=" + opcode +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }

        public boolean follows(MaMScriptLine other) {
            return (this.fromDirections.size() == other.fromDirections.size()) &&
                   this.fromDirections.containsAll(other.fromDirections) &&
                   this.pos.equals(other.pos) &&
                   (this.lineNumber == (other.lineNumber + 1));

        }

        public int getLen() { return len; }
        public Point getPos() { return pos; }
        public Set<Direction> getFromDirections() { return fromDirections; }
        public int getLineNumber() { return lineNumber; }
        public int getOpcode() { return opcode; }
        public byte[] getArgs() { return args; }
    }

    public MaMScript(String name, String key, byte[] data) throws CCFileFormatException {
        super(name, key);
        script = parseScript(data);
    }

    public MaMScript(String name, String key, String data)
    {
        super(name, key);
        script = new String(data.toCharArray());
    }

    protected String parseScript(byte[] data) throws CCFileFormatException {
        StringBuilder sb = new StringBuilder();
        //Bin
        return "print 'no script parsed'";
    }


    @Override
    public String getScript() {
        return script;
    }

    @Override
    public void setScript(String script) {
        this.script = script;
    }

    @Override
    public void run(MaMGame game, MaMMazeFile owner) {
        //TODO: This gives lua too much and needs to be sandboxed to prevent plugin scripts doing damage.
        // see: http://www.luaj.org/luaj/3.0/examples/jse/SampleSandboxed.java

        Globals globals = JsePlatform.standardGlobals();
        globals.set("_game", CoerceJavaToLua.coerce(game));
        globals.set("_world", CoerceJavaToLua.coerce(game.getWorld()));
        globals.set("_map", CoerceJavaToLua.coerce(game.getWorld().getCurrentMazeView().getMazeFileForPoint(game.getPartyPos())));
        globals.set("_pos", CoerceJavaToLua.coerce(game.getPartyPos()));
        globals.set("_dir", CoerceJavaToLua.coerce(game.getPartyDir().ordinal()));
        globals.set("_party", CoerceJavaToLua.coerce(game.getParty()));
        globals.set("DIR_UP", CoerceJavaToLua.coerce(Direction.UP.ordinal()));
        globals.set("DIR_DOWN", CoerceJavaToLua.coerce(Direction.DOWN.ordinal()));
        globals.set("DIR_LEFT", CoerceJavaToLua.coerce(Direction.LEFT.ordinal()));
        globals.set("DIR_RIGHT", CoerceJavaToLua.coerce(Direction.RIGHT.ordinal()));

        try {
//            LuaValue chunk = globals.load("function foo(pos)\r\n  print 'pos:getX()';\r\nend");
//            LuaValue chunk = globals.load(script);
//            chunk.call(CoerceJavaToLua.coerce(game.getPartyPos()));
//            LuaValue fooFunct = chunk.get("foo");
//            fooFunct.call(CoerceJavaToLua.coerce(game.getPartyPos()));

            //this works
//            LuaValue chunk = globals.load("print ('pos:getX()', _pos:getX());");

            //this works
            //globals.load("function doMap() \r\n print ('pos:getX()', _pos:getX()); \r\n end").call();
            globals.load(script).call();
            LuaValue fooFunct = globals.get("doMap");
            fooFunct.call();
            //chunk.call("foo");
        }
        catch (Exception ex) {
            if(GlobalSettings.INSTANCE.debugMode()) {
                System.out.println("------------------------------------------------");
                System.out.println(script);
                System.out.println("------------------------------------------------");
            }
            throw ex;
        }
    }

    @Override
    public String suggestProxyFileName() {
        return name + ".lua";
    }

    @Override
    public boolean saveProxy(String path) throws CCFileFormatException {
        return FileHelpers.saveText(script, path);
    }

    public static MaMScript fromTextFile(String path)
    {
        return new MaMScript(FileHelpers.getFileNameTillFirstDot(path),
                MAMFile.generateKeyFromPath(path),
                FileHelpers.readAllText(path));
    }
}
