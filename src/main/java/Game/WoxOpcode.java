package Game;

import mamFiles.MaMScript;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.lib.VarArgFunction;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by duckman on 29/05/17.
 */

public enum WoxOpcode
{
    Display(0x01) {
        @Override public void run(Object[] args) {

        }
    },

    DoorTextSml(0x02) {
        @Override public void run(Object[] args) {

        }
    },

    DoorTextLrg(0x03) {
        @Override public void run(Object[] args) {

        }
    },

    SignTxt(0x04) {
        @Override public void run(Object[] args) {

        }
    },

    NPC(0x05) {
        @Override public void run(Object[] args) {

        }
    },

    PlayFX(0x06) {
        @Override public void run(Object[] args) {

        }
    },

    Teleport_7(0x07) {
        @Override public void run(Object[] args) {

        }
    },

    If_8(0x08) {
        @Override public String translate(MaMScript.MaMScriptLine line) {
            return "  -- TODO: IF";
        }

        @Override public void run(Object[] args) {

        }
    },

    If_9(0x09) {
        @Override public String translate(MaMScript.MaMScriptLine line) {
            return "  -- TODO: IF";
        }

        @Override public void run(Object[] args) {

        }
    },

    If_A(0x0A) {
        @Override public String translate(MaMScript.MaMScriptLine line) {
            return "  -- TODO: IF";
        }

        @Override public void run(Object[] args) {

        }
    },

    MoveObj(0x0B) {
        @Override public void run(Object[] args) {

        }
    },

    TakeOrGive_C(0x0C) {
        @Override public void run(Object[] args) {

        }
    },

    NoAction_D(0x0D) {
        @Override public void run(Object[] args) {

        }
    },

    Remove(0x0E) {
        @Override public void run(Object[] args) {

        }
    },

    SetChar(0x0F) {
        @Override public void run(Object[] args) {

        }
    },

    Spawn(0x10) {
        @Override public void run(Object[] args) {

        }
    },
    DoTownEvent(0x11) {
        @Override public void run(Object[] args) {

        }
    },

    Exit(0x12){
        @Override public String translate(MaMScript.MaMScriptLine line) {
            return "-- return";
        }

        @Override public void run(Object[] args) {

        }
    },

    AlterMap(0x13) {
        @Override public void run(Object[] args) {

        }
    },

    GiveExtended(0x14) {
        @Override public void run(Object[] args) {

        }
    },

    ConfirmWord(0x15) {
        @Override public void run(Object[] args) {

        }
    },

    Damage(0x16) {
        @Override public void run(Object[] args) {

        }
    },

    JumpRnd(0x17) {
        @Override public void run(Object[] args) {

        }
    },

    AlterEvent(0x18) {
        @Override public void run(Object[] args) {

        }
    },

    CallEvent(0x19) {
        @Override public void run(Object[] args) {

        }
    },

    Return(0x1A) {
        @Override public void run(Object[] args) {

        }
    },

    SetVar(0x1B) {
        @Override public void run(Object[] args) {

        }
    },

    TakeOrGive_1C(0x1C) {
        @Override public void run(Object[] args) {

        }
    },

    TakeOrGive_1D(0x1D) {
        @Override public void run(Object[] args) {

        }
    },

    CutsceneEndClouds(0x1E) {
        @Override public void run(Object[] args) {

        }
    },

    Teleport(0x1F) {
        @Override public void run(Object[] args) {

        }
    },

    WhoWill(0x20) {
        @Override public void run(Object[] args) {

        }
    },
    RndDamage(0x21) {
        @Override public void run(Object[] args) {

        }
    },

    MoveWallObj(0x22) {
        @Override public void run(Object[] args) {

        }
    },

    AlterCellFlag(0x23) {
        @Override public void run(Object[] args) {

        }
    },

    AlterHed(0x24) {
        @Override public void run(Object[] args) {

        }
    },

    DisplayStat(0x25) {
        @Override public void run(Object[] args) {

        }
    },

    TakeOrGive(0x26) {
        @Override public void run(Object[] args) {

        }
    },

    SeatTextSml(0x27) {
        @Override public void run(Object[] args) {

        }
    },

    PlayEventVoc(0x28) {
        @Override public void run(Object[] args) {

        }
    },

    DisplayBottom(0x29) {
        @Override public void run(Object[] args) {

        }
    },

    IfMapFlag(0x2A) {
        @Override public void run(Object[] args) {

        }
    },

    SelRndChar(0x2B) {
        @Override public void run(Object[] args) {

        }
    },

    GiveEnchanted(0x2C) {
        @Override public void run(Object[] args) {

        }
    },

    ItemType(0x2D) {
        @Override public void run(Object[] args) {

        }
    },

    MakeNothingHere(0x2E) {
        @Override public void run(Object[] args) {

        }
    },

    NoAction(0x2F)  {
        @Override public void run(Object[] args) {

        }
    },

    ChooseNumeric(0x30)  {
        @Override public void run(Object[] args) {

        }
    },

    DisplayBottomTwoLines(0x31)  {
        @Override public void run(Object[] args) {

        }
    },

    DisplayLarge(0x32)  {
        @Override public void run(Object[] args) {

        }
    },

    ExchObj(0x33)  {
        @Override public void run(Object[] args) {

        }
    },

    FallToMap(0x34)  {
        @Override public void run(Object[] args) {

        }
    },

    DisplayMain(0x35)  {
        @Override public void run(Object[] args) {

        }
    },

    Goto(0x36) {
        @Override public void run(Object[] args) {

        }

        @Override public String translate(MaMScript.MaMScriptLine line) {
            return "goto " + line.getArgs()[0];
        }
    },

    ConfirmWord2(0x37)  {
        @Override public void run(Object[] args) {

        }
    },

    GotoRandom(0x38)  {
        @Override public void run(Object[] args) {

        }
    },

    CutsceneEndDarkside(0x39)  {
        @Override public void run(Object[] args) {

        }
    },

    CutsceneEndWorld(0x3A)  {
        @Override public void run(Object[] args) {

        }
    },

    FlipWorld(0x3B)  {
        @Override public void run(Object[] args) {

        }
    },

    PlayCD(0x3C)  {
        @Override public void run(Object[] args) {

        }
    };

    //------------------------------------------------------------------------------------------------------------------
    int code;
    VarArgFunction func;

    WoxOpcode(int code) {
        this.code = code;

        func = new VarArgFunction() {
            public Varargs invoke(Varargs args) {
                int numArgs = (args != null) ? (args.narg() - 1) : 0;
                try {
                    Object[] parsedArgs = new Object[numArgs];
                    for (int i = 0; i < numArgs; i++) {
                        // first two ars are not parameters passed to the function.
                        parsedArgs[i] = toObj(args.checkvalue(i+2));
                    }
                    run(parsedArgs);
                }
                catch (Exception ex) {
                    //debuging is hard because luaj errors look like a script problem
                    System.out.println("ERROR EXECUTING OPERATOR SERVER SIDE: "
                            + name()
                            + "(...) with " +  args.narg() + numArgs + " arguments. -> "
                            + ex.getMessage());
                    throw ex;//new Exception("ERROR EXECUTING OPERATOR SERVER SIDE: " + ex.getMessage());
                }
                return args;
            }
        };
    }

    protected static Object toObj(LuaValue val)
    {
        return   val.isint() ? val.checkint() :
                (val.isboolean() ? val.checkboolean() :
                (val.isstring() ? val.checkstring() :
                (val.islong() ? val.checklong() :
                (val.isnil() ? null :
                (val.isnumber() ? val.checkdouble() : // no isdouble. isnumber means will cast to number, so checkdouble should work.
                val.checkuserdata() // might as well try
                )))));

    }

    public String translate(MaMScript.MaMScriptLine line) {
        if(line.getArgs().length > 0) {
            return "opLib:" +
                    this.name()
                    + " (" +
                    IntStream.range(0, line.getArgs().length)
                            .map(i -> line.getArgs()[i])
                            .mapToObj(I -> "" + I)
                            .collect(Collectors.joining(", "))
                    + ")";
        }
        else {
            return "opLib." + this.name() + " ()";
        }
    }

    public VarArgFunction getFunc() {
        return func;
    }

    public int getCode() {
        return code;
    }

    public abstract void run(Object[] args);


}