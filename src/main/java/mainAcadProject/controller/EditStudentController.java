package mainAcadProject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainAcadProject.dao.*;
import mainAcadProject.entity.ContractEntity;
import mainAcadProject.entity.LaborContractEntity;
import mainAcadProject.entity.ManagerEntity;
import mainAcadProject.entity.StudentEntity;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ) on 24.04.2017.
 */
public class EditStudentController implements Initializable {
// TODO

    @FXML
    private TextField tf_surname = new TextField();;
    @FXML
    private TextField tf_name = new TextField();;
    @FXML
    private TextField tf_patronymic = new TextField();;
    @FXML
    private TextField tf_phone = new TextField();;
    @FXML
    private TextField tf_mail = new TextField();;
    @FXML
    private TextField tf_passport = new TextField();;
    @FXML
    private TextField tf_address = new TextField();;
    @FXML
    private ComboBox<ManagerEntity> cb_manager = new ComboBox<>();

    private Stage dialogStage;
    private StudentEntity student;
    private boolean saveClicked = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObservableList<ManagerEntity> all_managers = FXCollections.observableArrayList(ManagerDao.unload());
            ObservableList <ManagerEntity> managers = FXCollections.observableArrayList();
            for (ManagerEntity managerEntity : all_managers){
                if (!managerEntity.isRemote()){
                    managers.add(managerEntity);
                }
            }
            cb_manager.setItems(managers);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setStudent(StudentEntity studentEntity) {
        this.student = studentEntity;
        tf_surname.setText(student.getSurname());
        tf_name.setText(student.getName());
        tf_patronymic.setText(student.getPatronymic());
        tf_phone.setText(student.getPhone());
        tf_mail.setText(student.getEmail());
        tf_passport.setText(student.getPassport());
        tf_address.setText(student.getAddress());
// TODO дописать группы
        cb_manager.setPromptText(student.getManagerName());
    }

    @FXML
    public void saveChanges(){
        if (isInputValid()) {
            student.setSurname(tf_surname.getText());
            student.setName(tf_name.getText());
            student.setPatronymic(tf_patronymic.getText());
            student.setPhone(tf_phone.getText());
            student.setEmail(tf_mail.getText());
            student.setPassport(tf_passport.getText());
            student.setAddress(tf_address.getText());
            student.setManager_id(cb_manager.getSelectionModel().getSelectedItem().getId());
            StudentDao.update(student);
            List<ContractEntity> contractsList = student.getContracts();
            for (ContractEntity contr :contractsList){
                ContractDao.insert(contr);
            }
            saveClicked=true;
            dialogStage.close();
        }
    }

    @FXML
    public void cancel(){
        dialogStage.close();
    }

    @FXML
    public void create_contract() throws IOException {
        ContractEntity contract = new ContractEntity();
        contract.setStudent_id(student.getId());
        runNewContractController(contract);
        if(contract==null) {
            student.addContracts(contract);
        }
    }

    public void runNewContractController(ContractEntity contract) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NewContractController.class.getClassLoader().getResource("fxmlNewContract.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Новый договор");
        NewContractController controller=loader.getController();
        controller.setDialogStage(stage);
        controller.setContract(contract);
        stage.showAndWait();
    }

    public boolean isSaveClicked() {
        return saveClicked;
    }

    //TODO удалить те поля, коорые могут быть не обязательными, дописать проверку на корректность вводимых данных
    // TODO дописать проверку менеджера
    private boolean isInputValid(){
        String errorMess ="";
        if ((tf_surname.getText()==null)||(tf_surname.getText().isEmpty())){
            errorMess += "Введите фамилию!\n";
        }
        if ((tf_name.getText()==null)||(tf_name.getText().isEmpty())){
            errorMess += "\nВведите имя!\n";
        }
        if ((tf_patronymic.getText()==null)||(tf_patronymic.getText().isEmpty())){
            errorMess += "\nВведите отчество!\n";
        }
        if ((tf_phone.getText()==null)||(tf_phone.getText().isEmpty())){
            errorMess += "\nВведите контактный телефон!";
        }
        if ((tf_mail.getText()==null)||(tf_mail.getText().isEmpty())){
            errorMess += "\nВведите e-mail!\n";
        }
        if ((tf_passport.getText()==null)||(tf_passport.getText().isEmpty())){
            errorMess += "\nВведите номер паспорта!\n";
        }
        if ((tf_address.getText()==null)||(tf_address.getText().isEmpty())){
            errorMess += "\nВведите адрес!\n";
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

    // TODO дописать добаление группы и появление имени группы в tf_group
    @FXML
    public void add_group(){}

}
