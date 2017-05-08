package mainAcadProject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainAcadProject.dao.InstructorDao;
import mainAcadProject.dao.LaborContractDao;
import mainAcadProject.entity.InstructorEntity;
import mainAcadProject.entity.LaborContractEntity;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ) on 24.04.2017.
 */


public class EditInstructorController implements Initializable {

    @FXML
    private TextField tf_surname;
    @FXML
    private TextField tf_name = new TextField();
    @FXML
    private TextField tf_patronymic = new TextField();
    @FXML
    private TextField tf_phone = new TextField();
    @FXML
    private TextField tf_mail = new TextField();
    @FXML
    private TextField tf_passport = new TextField();
    @FXML
    private TextField tf_working = new TextField();

    private Stage dialogStage;
    private InstructorEntity instructor;
    private boolean saveClicked = false;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setInstructor(InstructorEntity instructorEntity) {
        this.instructor = instructorEntity;
        tf_surname.setText(instructor.getSurname());
        tf_name.setText(instructor.getName());
        tf_patronymic.setText(instructor.getPatronymic());
        tf_phone.setText(instructor.getPhone());
        tf_passport.setText(instructor.getPassport());
        tf_mail.setText(instructor.getEmail());
        tf_working.setText(instructor.getWorking());
    }

    @FXML
    public void saveChanges(){
        if (isInputValid()) {
            instructor.setSurname(tf_surname.getText());
            instructor.setName(tf_name.getText());
            instructor.setPatronymic(tf_patronymic.getText());
            instructor.setPhone(tf_phone.getText());
            instructor.setPassport(tf_passport.getText());
            instructor.setEmail(tf_mail.getText());
            instructor.setWorking(tf_working.getText());
            InstructorDao.update(instructor);
            List<LaborContractEntity> contractsList = instructor.getContracts();
            for (LaborContractEntity contr :contractsList){
                LaborContractDao.insert(contr);
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
        LaborContractEntity contractEntity = new LaborContractEntity();
        contractEntity.setInstructor_id(instructor.getId());
        runNewLaborContractController(contractEntity);
        if (contractEntity==null) {
            instructor.addContracts(contractEntity);
        }
    }

    public void runNewLaborContractController(LaborContractEntity contract) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NewLaborContractController.class.getClassLoader().getResource("fxmlNewLaborContract.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Новый трудовой договор");
        NewLaborContractController controller=loader.getController();
        controller.setDialogStage(stage);
        controller.setContract(contract);
        stage.showAndWait();
    }
    //TODO удалить те поля, коорые могут быть не обязательными, дописать проверку на корректность вводимых данных
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

    public boolean isSaveClicked() {
        return saveClicked;
    }
}
