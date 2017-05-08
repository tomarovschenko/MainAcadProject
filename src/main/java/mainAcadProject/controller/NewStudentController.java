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
import mainAcadProject.entity.*;
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ) on 24.04.2017.
 */
public class NewStudentController implements Initializable {

// TODO
    @FXML
    private TextField tf_surname;
    @FXML
    private TextField tf_name;
    @FXML
    private TextField tf_patronymic;
    @FXML
    private TextField tf_phone;
    @FXML
    private TextField tf_mail;
    @FXML
    private TextField tf_passport;
    @FXML
    private TextField tf_address;
    @FXML
    private TextField tf_group;
    @FXML
    private ComboBox <ManagerEntity> cb_manager;

    private StudentEntity student = new StudentEntity();
    private static Stage dialogStage = new Stage();

    public NewStudentController() {
        try {
            this.student.setId(createId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Stage getDialogStage() {
        return dialogStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObservableList <ManagerEntity> all_managers = FXCollections.observableArrayList(ManagerDao.unload());
            ObservableList <ManagerEntity> managers = FXCollections.observableArrayList();
            for (ManagerEntity managerEntity : all_managers){
                if (!managerEntity.isRemote()){
                    managers.add(managerEntity);
                }
            }
            cb_manager.setPromptText("Не выбран");
            cb_manager.setItems(managers);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    @FXML
    public void createStudent(){
        if (isInputValid()) {
            student.setSurname(tf_surname.getText());
            student.setName(tf_name.getText());
            student.setPatronymic(tf_patronymic.getText());
            student.setPhone(tf_phone.getText());
            student.setEmail(tf_mail.getText());
            student.setPassport(tf_passport.getText());
            student.setAddress(tf_address.getText());
            student.setManager_id(cb_manager.getSelectionModel().getSelectedItem().getId());
            StudentDao.insert(student);
            List<ContractEntity> contractList = student.getContracts();
            for (ContractEntity contr : contractList){
                 ContractDao.insert(contr);
            }
            dialogStage.close();
        }
    }


    @FXML
    public void cancel(){
        dialogStage.close();
    }

    private String createId() throws SQLException {
        String id_num = RandomStringUtils.randomAlphanumeric(45);
        ObservableList<StudentEntity> base = FXCollections.observableArrayList(StudentDao.unload());
        for (StudentEntity student : base){
            if (id_num.equals(student.getId())){
                id_num=createId();
                return id_num;
            }
        }
        return id_num;
    }

    @FXML
    public void create_contract() throws IOException{
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
