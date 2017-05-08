package mainAcadProject.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainAcadProject.entity.ContractEntity;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ) on 24.04.2017.
 */
public class NewContractController implements Initializable {
// TODO
    @FXML
    private DatePicker dp_startDate;
    @FXML
    private DatePicker dp_endDate;
    @FXML
    private TextField tf_number;
    @FXML
    private TextField tf_amount;

    private static Stage dialogStage;
    private ContractEntity contract;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public static void setDialogStage(Stage dialogStage) {
        NewContractController.dialogStage = dialogStage;
    }

    public void setContract(ContractEntity contract) {
        this.contract = contract;
    }
    @FXML
    public void createContract(){
        if (isInputValid()){
            contract.setNumb(tf_number.getText());
//TODO прописать проверку на уникальность ключа
            contract.setStartDate(dp_startDate.getValue());
            contract.setEndDate(dp_endDate.getValue());
            contract.setAmount(Double.valueOf(tf_amount.getText()));
        }
        System.out.println(contract.toString());
        dialogStage.close();
    }
    @FXML
    public void cancel(){
        dialogStage.close();
    }
    private boolean isInputValid(){
        String errorMess ="";
        if ((tf_number.getText()==null)||(tf_number.getText().isEmpty())){
            errorMess += "Введите номер договора!\n";
        }
        if (dp_startDate.getValue()==null){
            errorMess += "\nВыберите дату договора!\n";
        }
        if (dp_endDate.getValue()==null){
            errorMess += "\nВыберите дату окончания договора!\n";
        }
        if ((tf_amount.getText()==null)||(tf_amount.getText().isEmpty())){
            errorMess += "Введите сумму договора!\n";
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
