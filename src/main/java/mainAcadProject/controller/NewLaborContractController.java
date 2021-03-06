package mainAcadProject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import mainAcadProject.entity.LaborContractEntity;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ) on 22.04.2017.
 */
public class NewLaborContractController implements Initializable {

    @FXML
    private TextField tf_number;
    @FXML
    private DatePicker dp_startDate;
    @FXML
    private DatePicker dp_endDate;

    private Stage dialogStage;

    private LaborContractEntity contract;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setContract(LaborContractEntity contract) {
        this.contract = contract;
    }

    @FXML
    public void createLaborContract(){
        if (isInputValid()){
            contract.setNumber(tf_number.getText());
//TODO прописать проверку на уникальность ключа
            contract.setDate_start(dp_startDate.getValue());
            contract.setDate_end(dp_endDate.getValue());
        }

        dialogStage.close();
    }

    @FXML
    public void cancel(){
        dialogStage.close();
    }

    private boolean isInputValid(){
        String errorMess ="";
        if ((tf_number.getText()==null)||(tf_number.getText().isEmpty())){
            errorMess += "Введите номер трудового договора!\n";
        }
        if (dp_startDate.getValue()==null){
            errorMess += "\nВыберите дату договора!\n";
        }
        if (dp_endDate.getValue()==null){
            errorMess += "\nВыберите дату окончания договора!\n";
        }
        if (errorMess.isEmpty()){
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Незаполнены обязательные поля:");
            alert.setContentText(errorMess);
            alert.showAndWait();

            return false;
        }
    }
}
