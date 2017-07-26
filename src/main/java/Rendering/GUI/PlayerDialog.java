package Rendering.GUI;

import Game.GlobalSettings;
import Game.Map.MaMWorld;
import GameMechanics.Adventurers.Adventurer;
import GameMechanics.Stat;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Created by duckman on 8/06/17.
 */
public class PlayerDialog extends PapyrusDialogBase
{
    JScrollPane spStats;
    JPanel pnlStats;



    public PlayerDialog(MaMWorld world, Adventurer adventurer) {
        super(world);
        pnlStats = new JPanel(new GridLayout(0, 4));

        for(Stat stat : adventurer.getStats())
        {
            pnlStats.add(makeStatViewer(adventurer, stat));
        }

        spStats = new JScrollPane(pnlStats);
        this.add(spStats);
    }

    private Component makeStatViewer(Adventurer adventurer, Stat stat) {
//        JPanel pnl = makePanel(new MigLayout("fill" + GlobalSettings.INSTANCE.migDebugText(),
//                "[]0[left]", // col
//                "[]"));   // row

        JPanel pnl = makePanel(VGALayoutManager.mode13h());   // row
        MaMButton btn  = new MaMButton("", stat.getIcon(world));// world.getGraphicsSet().getIcnStatAcy());
        String scriptName = stat.getName().replace(" ", "").toLowerCase();
        //pnl.add(btn, "btn_" + scriptName);
        pnl.add(btn, new Rectangle(12, 12, 16, 16));
        pnl.add(makeLabel(stat.getName(), Alignment.LEFT), "lbl_"+scriptName);

        return pnl;
    }


}
