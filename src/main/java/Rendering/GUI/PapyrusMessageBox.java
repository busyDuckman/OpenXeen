package Rendering.GUI;

import Game.Map.MaMWorld;
import Toolbox.Tag;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by duckman on 3/06/17.
 *
 * Creates basic dialog boxes:
 *   - Hooks up standard shortcuts enter/esc/y/n/r
 *   - can run model, normal or modeless.
 */
public class PapyrusMessageBox extends PapyrusDialogBase {
    public enum DialogeResult {
    CHAR_1, CHAR_2, CHAR_3, CHAR_4, CHAR_5, CHAR_6, CHAR_7, CHAR_8, CHAR_9, CHAR_10, CHAR_11, CHAR_12,
        OK, CANCEL, YES, NO, RETRY, EXIT, NO_RESULT };

    protected String heading;
    protected String message;
    protected List<Tag<Object, DialogeResult>> buttons;
    boolean canSelectPlayer = false;

    JLabel lblTitle;
    JLabel lblText;

    volatile DialogeResult result = DialogeResult.NO_RESULT;

    //------------------------------------------------------------------------------------------------------------------
    // Constructor
    //------------------------------------------------------------------------------------------------------------------
    public PapyrusMessageBox(MaMWorld world, String heading, String message, List<Tag<Object, DialogeResult>> buttons) {
        super(world);
        this.heading = heading;
        this.message = message;
        this.buttons = buttons;
        canSelectPlayer = false;

        this.setUnscaledBounds(new Rectangle(8, 8, 64, 128));
        int sWidth = getScaledThinBorderWidth();
        System.out.println("Updating layout for border:" + sWidth + " bounds:" + getScaledBounds().toString());
        String colConstraints = (sWidth*2)+"px![center, grow]"+(sWidth*2)+"px!";
        String rowConstraints = (sWidth*2)+"px![]"+(sWidth)+"[grow, center]"+(sWidth*2)+"[center]"+(sWidth*2)+"px!";

        //this.setPreferredSize(getScaledBounds().getSize());
        //this.setMinimumSize(getScaledBounds().getSize());
        //this.setMaximumSize(getScaledBounds().getSize());

        this.setLayout(new MigLayout("fillx, filly", colConstraints, rowConstraints));

        this.setFocusable(false);
        JButton exitButton = makeButton("Ok");
        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                setVisible(false);
            }
        });

        this.add(exitButton, "cell 0 2, center");
        exitButton.setFocusable(false);

        lblTitle = makeLabel(asHtml(heading), Alignment.CENTER, FontEffect.HEADING);
        this.add(lblTitle, "cell 0 0, center");

        lblText = makeLabel(asHtml(message), Alignment.CENTER);
        this.add(lblText, "cell 0 1, center");

        this.addComponentListener(this);

        exitButton.revalidate();
        this.revalidate();
    }

    private String asHtml(String text) {
        return "<html>" + text + "</html>";
    }

    //------------------------------------------------------------------------------------------------------------------
    // Factory methods
    //------------------------------------------------------------------------------------------------------------------
    public static PapyrusMessageBox fromModalOKMessage(MaMWorld world, String title, String message) {
        List<Tag<Object, DialogeResult>> btnList = new ArrayList<>();
        btnList.add(new Tag<Object, DialogeResult>("OK", DialogeResult.OK));
        PapyrusMessageBox msgBox = new PapyrusMessageBox(world, title, message, btnList);
        msgBox.setFocusable(true);
        msgBox.setUnscaledBounds(new Rectangle(50, 25, 128, 64));

        return msgBox;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Control
    //------------------------------------------------------------------------------------------------------------------
    public DialogeResult awaitResult() {
        while (result == DialogeResult.NO_RESULT) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //------------------------------------------------------------------------------------------------------------------
    // Getters and setters
    //------------------------------------------------------------------------------------------------------------------
    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Tag<Object, DialogeResult>> getButtons() {
        return buttons;
    }

    public boolean getCanSelectPlayer() {
        return canSelectPlayer;
    }

    public void setCanSelectPlayer(boolean canSelectPlayer) {
        this.canSelectPlayer = canSelectPlayer;
    }

    public DialogeResult getResult() {
        return result;
    }
}
