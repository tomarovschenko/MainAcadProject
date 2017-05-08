package mainAcadProject.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainAcadProject.dao.InstructorDao;
import mainAcadProject.dao.LaborContractDao;
import mainAcadProject.entity.InstructorEntity;
import mainAcadProject.entity.LaborContractEntity;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ) on 28.04.2017.
 */
public class EditLaborContractController implements Initializable{
    @FXML
    private TextField tf_number;
    @FXML
    private DatePicker dp_startDate;
    @FXML
    private DatePicker dp_endDate;

    private Stage dialogStage;
    private LaborContractEntity contract;
    private boolean saveClicked = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setLaborContract(LaborContractEntity laborContract) {
        this.contract = laborContract;
        tf_number.setText(contract.getNumber());
        dp_startDate.setValue(contract.getDate_start());
        dp_endDate.setValue(contract.getDate_end());
    }

    @FXML
    public void saveChanges(){
        if (isInputValid()) {
            contract.setNumber(tf_number.getText());
            contract.setDate_start(dp_startDate.getValue());
            contract.setDate_end(dp_endDate.getValue());
            LaborContractDao.update(contract);
            saveClicked=true;
            dialogStage.close();
        }
    }

    @FXML
    public void cancel(){
        dialogStage.close();
    }

    //TODO написать
    private boolean isInputValid(){

        return true;
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }
}
