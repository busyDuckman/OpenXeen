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

    protected MaMSprite icnStatStr, icnStatInt, icnStatChr, icnStatEnd,
            icnStatSpeed, icnStatAcy, icnStatLuck, icnStatAge,
            icnStatLvL, icnStatAC, icnStatHP, icnStatSP, icnStatRes,
            icnStatSkills, icnStatAwards, icnStatXP,
            icnStatGold, icnStatGems, icnStatFood,
            icnStatCondition;

    protected MaMSprite btnPlayerDlgItem, btnPlayerDlgQuick, btnPlayerExchange, btnPlayerDlgExit;

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

        int i = 0;
        icnStatStr             = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatInt             = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatChr             = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatEnd             = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatSpeed           = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatAcy             = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatLuck            = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatAge             = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatLvL             = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatAC              = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatHP              = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatSP              = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatRes             = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatSkills          = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatAwards          = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatXP              = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatGold            = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatGems            = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatFood            = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        icnStatCondition       = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        btnPlayerDlgItem       = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        btnPlayerDlgQuick      = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        btnPlayerExchange      = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
        btnPlayerDlgExit       = ccFile.getSprite("VIEW.ICN").subSetOfFrames("PLR_DLG_"+i, i*2, 2); i++;
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

    public MaMSprite getIcnStatStr() {
        return icnStatStr;
    }

    public MaMSprite getIcnStatInt() {
        return icnStatInt;
    }

    public MaMSprite getIcnStatChr() {
        return icnStatChr;
    }

    public MaMSprite getIcnStatEnd() {
        return icnStatEnd;
    }

    public MaMSprite getIcnStatSpeed() {
        return icnStatSpeed;
    }

    public MaMSprite getIcnStatAcy() {
        return icnStatAcy;
    }

    public MaMSprite getIcnStatLuck() {
        return icnStatLuck;
    }

    public MaMSprite getIcnStatAge() {
        return icnStatAge;
    }

    public MaMSprite getIcnStatLvL() {
        return icnStatLvL;
    }

    public MaMSprite getIcnStatAC() {
        return icnStatAC;
    }

    public MaMSprite getIcnStatHP() {
        return icnStatHP;
    }

    public MaMSprite getIcnStatSP() {
        return icnStatSP;
    }

    public MaMSprite getIcnStatRes() {
        return icnStatRes;
    }

    public MaMSprite getIcnStatSkills() {
        return icnStatSkills;
    }

    public MaMSprite getIcnStatAwards() {
        return icnStatAwards;
    }

    public MaMSprite getIcnStatXP() {
        return icnStatXP;
    }

    public MaMSprite getIcnStatGold() {
        return icnStatGold;
    }

    public MaMSprite getIcnStatGems() {
        return icnStatGems;
    }

    public MaMSprite getIcnStatFood() {
        return icnStatFood;
    }

    public MaMSprite getIcnStatCondition() {
        return icnStatCondition;
    }

    public MaMSprite getBtnPlayerDlgItem() {
        return btnPlayerDlgItem;
    }

    public MaMSprite getBtnPlayerDlgQuick() {
        return btnPlayerDlgQuick;
    }

    public MaMSprite getBtnPlayerExchange() {
        return btnPlayerExchange;
    }

    public MaMSprite getBtnPlayerDlgExit() {
        return btnPlayerDlgExit;
    }
}
