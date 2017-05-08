package mainAcadProject.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ) on 21.04.2017.
 */
public class LayoutController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void openCoursesCatalog() throws IOException {
        runCourseController(CatalogCourseController.getDialogStage());
    }

    private void runCourseController(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlCatalogCourses.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Учебные курсы");
        stage.show();
    }

    @FXML
    public void openManagersCatalog() throws IOException {
       runManagerController(CatalogManagerController.getDialogStage());
    }

    private void runManagerController(Stage stage) throws  IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlCatalogManagers.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Менеджеры");
        stage.show();
    }

    @FXML
    public void openNewInstructorController() throws IOException{
       runNewInstructorController(NewInstructorController.getDialogStage());
    }

    private void runNewInstructorController(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlNewInstructor.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Новый инструктор");
        stage.show();
    }

    @FXML
    public void openInstructorsCatalog() throws IOException{
        runInstructorsCatalogController(CatalogInstructorsController.getDialogStage());
    }

    private void runInstructorsCatalogController(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlCatalogInstructors.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Инструкторы");
        stage.show();
    }

    public void openLaborContractsCatalog() throws IOException {
        runCatalogLaborContractsController(CatalogLaborContractsController.getDialogStage());

    }

    private void runCatalogLaborContractsController(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlCatalogLaborContracts.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Трудовые договора");
        stage.show();
    }

    @FXML
    public void openNewStudentController() throws IOException{
        runNewStudentController(NewStudentController.getDialogStage());
    }

    private void runNewStudentController(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlNewStudent.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Новый студент");
        stage.show();
    }

    @FXML
    public void openStudentsCatalog() throws IOException{
        runStudentsCatalogController(CatalogStudentController.getDialogStage());
    }

    private void runStudentsCatalogController(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlCatalogStudents.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Студенты");
        stage.show();
    }

    //TODO сделать корректный Файл -> закрыть

}
