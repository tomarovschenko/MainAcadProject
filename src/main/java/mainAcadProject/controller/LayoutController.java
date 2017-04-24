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
        Stage stage = new Stage();
        runCourseController(stage);
    }

    private void runCourseController(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlCourses.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Учебные курсы");
        stage.show();
    }

    @FXML
    public void openManagersCatalog() throws IOException {
        Stage stage = new Stage();
        runManagerController(stage);
    }

    private void runManagerController(Stage stage) throws  IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlManagers.fxml"));
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
        runInstructorsCatalogController(InstructorsCatalogController.getDialogStage());
    }

    private void runInstructorsCatalogController(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxmlInstructorsCatalog.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Инструкторы");
        stage.show();
    }

}
