package mainAcadProject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by ) on 22.04.2017.
 */
public class NewInstructorController implements Initializable {

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
    private TextField tf_working;

    private static Stage dialogStage = new Stage();

    private InstructorEntity instructor = new InstructorEntity();


    public NewInstructorController() {
        try {
            this.instructor.setId(createId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Stage getDialogStage() {
        return dialogStage;
    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
// TODO сделать так, чтоб кнопка срабатывала от Enter
    @FXML
    public void createInstructor() throws IOException{
        if (isInputValid()) {
            instructor.setSurname(tf_surname.getText());
            instructor.setName(tf_name.getText());
            instructor.setPatronymic(tf_patronymic.getText());
            instructor.setPhone(tf_phone.getText());
            instructor.setPassport(tf_passport.getText());
            instructor.setEmail(tf_mail.getText());
            instructor.setWorking(tf_working.getText());
            InstructorDao.insert(instructor);
            List <LaborContractEntity> contractsList = instructor.getContracts();
            for (LaborContractEntity contr :contractsList){
                LaborContractDao.insert(contr);
            }
            dialogStage.close();
        }
    }

    @FXML
    public void cancel(){
        dialogStage.close();
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

    @FXML
    public void create_contract() throws IOException{
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

    private String createId() throws SQLException{
        String id_num = RandomStringUtils.randomAlphanumeric(45);
        ObservableList <InstructorEntity> base = FXCollections.observableArrayList(InstructorDao.unload());
        for (InstructorEntity instructor : base){
            if (id_num.equals(instructor.getId())){
                id_num=createId();
                return id_num;
            }
        }
        return id_num;
    }





}
