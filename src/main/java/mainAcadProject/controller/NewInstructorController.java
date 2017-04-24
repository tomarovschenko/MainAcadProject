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
            instructor.setId(0);
            instructor.setSurname(tf_surname.getText());
            instructor.setName(tf_name.getText());
            instructor.setPatronymic(tf_patronymic.getText());
            instructor.setPhone(tf_phone.getText());
            instructor.setPassport(tf_passport.getText());
            instructor.setEmail(tf_mail.getText());
            instructor.setWorking(tf_working.getText());

            InstructorDao.update(instructor);

            System.out.println(instructor.getContracts().get(0).toString());
          // System.out.println(instructor.getContracts().get(1).toString());
           // LaborContractDao.update(instructor.getContracts().get(0));
           // System.out.println(instructor.toString());

            dialogStage.close();
        }
    }

    @FXML
    public void cancel(){
        dialogStage.close();
    }

//TODO дописать проверку на корректность вводимых данных
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
        runNewLaborContractController(contractEntity);
        instructor.addContracts(contractEntity);

    }

    public void runNewLaborContractController(LaborContractEntity contract) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(NewLaborContractController.class.getClassLoader().getResource("fxmlNewLaborContract.fxml"));

       // Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlNewLaborContract.fxml"));
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
    /*
    public boolean showPersonEditDialog(Person person) {
    try {
        // Загружаем fxml-файл и создаём новую сцену
        // для всплывающего диалогового окна.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("view/PersonEditDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load();

        // Создаём диалоговое окно Stage.
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Edit Person");
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Передаём адресата в контроллер.
        PersonEditDialogController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        controller.setPerson(person);

        // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
        dialogStage.showAndWait();

        return controller.isOkClicked();
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}
     */



}
