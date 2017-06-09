package Rendering.GUI;

import Game.GlobalSettings;
import Game.Map.MaMWorld;
import GameMechanics.Adventurers.Adventurer;
import GameMechanics.Stat;
import Rendering.SimpleCanvas.MaMButton;
import mamFiles.MaMCCFileReader;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by duckman on 8/06/17.
 */
public class PlayerDialog extends PapyrusDialogBase
{
    JScrollPane spStats;
    JPanel pnlStats;

    public PlayerDialog(MaMWorld world, Adventurer adventurer) {
        super(world);

        pnlStats = new JPanel(new GridLayout(0, 3));


        for(Stat stat : adventurer.getStats())
        {
            pnlStats.add(makeStatViewer(adventurer, stat));
        }

        spStats = new JScrollPane(pnlStats);
        this.add(spStats);
    }

    private Component makeStatViewer(Adventurer adventurer, Stat stat) {
        JPanel pnl = new JPanel(new MigLayout("fill" + GlobalSettings.INSTANCE.migDebugText(),
                "[]",
                "[][grow]"));
        MaMButton btn  = new MaMButton("", world.getGraphicsSet().getIcnStatAcy());
        pnl.add(btn, "cell 0 0");

        return pnl;
    }
}
