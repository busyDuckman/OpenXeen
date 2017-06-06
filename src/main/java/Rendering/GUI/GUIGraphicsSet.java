package Rendering.GUI;

import mamFiles.CCFileFormatException;
import mamFiles.MaMCCFileReader;
import mamFiles.MaMSprite;

/**
 * Created by duckman on 31/05/17.
 *
 * Icons, fonts, and other things needed for GUI elements.
 */
public class GUIGraphicsSet
{

    protected MaMCCFileReader ccFile;

    protected MaMSprite btnBankDeposit;
    protected MaMSprite btnBankWithdraw;
    protected MaMSprite btnCloseDialog;
    protected MaMSprite btnYes;
    protected MaMSprite btnNo;

    protected MaMSprite btnShootAction;
    protected MaMSprite btnCastAction;
    protected MaMSprite btnRestAction;
    protected MaMSprite btnBashAction;
    protected MaMSprite btnDismssAction;
    protected MaMSprite btnQuestsAction;
    protected MaMSprite btnViewMapAction;
    protected MaMSprite btnViewTimeAction;
    protected MaMSprite btnViewPartyAction;
    protected MaMSprite btnMrWizardAction;
    protected MaMSprite btnTurnLeftAction;
    protected MaMSprite btnMoveForwardAction;
    protected MaMSprite btnTurnRightAction;
    protected MaMSprite btnMoveLeftAction;
    protected MaMSprite btnMoveBackAction;
    protected MaMSprite btnMoveRightAction;

    public GUIGraphicsSet(MaMCCFileReader ccFile) throws CCFileFormatException {
        this.ccFile = ccFile;

        btnBankDeposit = ccFile.getSprite("BANK.ICN").subSetOfFrames("bankDeposit", 0, 2);
        btnBankWithdraw = ccFile.getSprite("BANK.ICN").subSetOfFrames("bankDeposit", 2, 2);

        btnCloseDialog = ccFile.getSprite("ESC.ICN").subSetOfFrames("btnCloseDialog", 0, 2);
        btnYes = ccFile.getSprite("CONFIRM.ICN").subSetOfFrames("btnYes", 0, 2);
        btnNo = ccFile.getSprite("CONFIRM.ICN").subSetOfFrames("btnNo", 2, 2);

        btnShootAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnShootAction", 0, 2);
        btnCastAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnCastAction", 2, 2);
        btnRestAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnRestAction", 4, 2);
        btnBashAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnBashAction", 6, 2);
        btnDismssAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnDismssAction", 8, 2);
        btnQuestsAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnQuestsAction", 10, 2);
        btnViewMapAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnViewMapAction", 12, 2);
        btnViewTimeAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnViewTimeAction", 14, 2);
        btnViewPartyAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnViewPartyAction", 16, 2);
        btnMrWizardAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnMrWizardAction", 18, 2);
        btnTurnLeftAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnTurnLeftAction", 20, 2);
        btnMoveForwardAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnMoveForwardAction", 22, 2);
        btnTurnRightAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnTurnRightAction", 24, 2);
        btnMoveLeftAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnMoveLeftAction", 26, 2);
        btnMoveBackAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnMoveBackAction", 28, 2);
        btnMoveRightAction = ccFile.getSprite("MAIN.ICN").subSetOfFrames("btnMoveRightAction", 30, 2);
    }


    //------------------------------------------------------------------------------------------------------------------
    // A Bazillion Getters
    //------------------------------------------------------------------------------------------------------------------

    public MaMSprite getBtnBankDeposit() {
        return btnBankDeposit;
    }

    public MaMSprite getBtnBankWithdraw() {
        return btnBankWithdraw;
    }

    public MaMSprite getBtnCloseDialog() {
        return btnCloseDialog;
    }

    public MaMSprite getBtnYes() {
        return btnYes;
    }

    public MaMSprite getBtnNo() {
        return btnNo;
    }

    public MaMSprite getBtnShootAction() {
        return btnShootAction;
    }

    public MaMSprite getBtnCastAction() {
        return btnCastAction;
    }

    public MaMSprite getBtnRestAction() {
        return btnRestAction;
    }

    public MaMSprite getBtnBashAction() {
        return btnBashAction;
    }

    public MaMSprite getBtnDismssAction() {
        return btnDismssAction;
    }

    public MaMSprite getBtnQuestsAction() {
        return btnQuestsAction;
    }

    public MaMSprite getBtnViewMapAction() {
        return btnViewMapAction;
    }

    public MaMSprite getBtnViewTimeAction() {
        return btnViewTimeAction;
    }

    public MaMSprite getBtnViewPartyAction() {
        return btnViewPartyAction;
    }

    public MaMSprite getBtnMrWizardAction() {
        return btnMrWizardAction;
    }

    public MaMSprite getBtnTurnLeftAction() {
        return btnTurnLeftAction;
    }

    public MaMSprite getBtnMoveForwardAction() {
        return btnMoveForwardAction;
    }

    public MaMSprite getBtnTurnRightAction() {
        return btnTurnRightAction;
    }

    public MaMSprite getBtnMoveLeftAction() {
        return btnMoveLeftAction;
    }

    public MaMSprite getBtnMoveBackAction() {
        return btnMoveBackAction;
    }

    public MaMSprite getBtnMoveRightAction() {
        return btnMoveRightAction;
    }
}
