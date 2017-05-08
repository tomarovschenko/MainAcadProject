package mainAcadProject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import mainAcadProject.dao.InstructorDao;
import mainAcadProject.dao.StudentDao;
import mainAcadProject.entity.InstructorEntity;
import mainAcadProject.entity.StudentEntity;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by ) on 24.04.2017.
 */
public class CatalogStudentController implements Initializable {
// TODO
    private ObservableList <StudentEntity> students;
    @FXML
    TableView<StudentEntity> students_catalog = new TableView<>();
    @FXML
    TableColumn <StudentEntity, String> surname = new TableColumn <>();
    @FXML
    TableColumn <StudentEntity, String> name = new TableColumn <>();
    @FXML
    TableColumn <StudentEntity, String> patronymic = new TableColumn <>();
    @FXML
    TableColumn <StudentEntity, String> phone = new TableColumn <>();
    @FXML
    TableColumn <StudentEntity, String> passport = new TableColumn <>();
    @FXML
    TableColumn <StudentEntity, String> mail = new TableColumn <>();
    @FXML
    TableColumn <StudentEntity, String> address = new TableColumn <>();
    @FXML
    TableColumn <StudentEntity, String> manager = new TableColumn <>();

    private static Stage dialogStage = new Stage();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            students = FXCollections.observableArrayList(StudentDao.unload());
            surname.setCellFactory(TextFieldTableCell.forTableColumn());
            surname.setCellValueFactory((cellData -> cellData.getValue().surnameProperty()));
            name.setCellFactory(TextFieldTableCell.forTableColumn());
            name.setCellValueFactory((cellData -> cellData.getValue().nameProperty()));
            patronymic.setCellFactory(TextFieldTableCell.forTableColumn());
            patronymic.setCellValueFactory((cellData -> cellData.getValue().patronymicProperty()));
            phone.setCellFactory(TextFieldTableCell.forTableColumn());
            phone.setCellValueFactory((cellData -> cellData.getValue().phoneProperty()));
            passport.setCellFactory(TextFieldTableCell.forTableColumn());
            passport.setCellValueFactory((cellData -> cellData.getValue().passportProperty()));
            mail.setCellFactory(TextFieldTableCell.forTableColumn());
            mail.setCellValueFactory((cellData -> cellData.getValue().emailProperty()));
            address.setCellFactory(TextFieldTableCell.forTableColumn());
            address.setCellValueFactory((cellData -> cellData.getValue().addressProperty()));
            manager.setCellFactory(TextFieldTableCell.forTableColumn());
            manager.setCellValueFactory((cellData -> cellData.getValue().managerName()));
            students_catalog.setItems(students);
            openEditStudent();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public static Stage getDialogStage() {
        return dialogStage;
    }

    @FXML
    public void delete() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение удаления");
        alert.setHeaderText("Вы действительно хотите удалить выбранного студента?");
        alert.setContentText("Нажмите OK для удаления");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int selectedIndex = students_catalog.getSelectionModel().getSelectedIndex();
            students_catalog.getItems().get(selectedIndex).setRemote(true);
            StudentDao.update(students_catalog.getItems().get(selectedIndex));
            students=FXCollections.observableArrayList(StudentDao.unload());
            students_catalog.setItems(students);
        } else {
            alert.close();
        }
    }

    @FXML
    public void close (){
        dialogStage.close();
    }

    // запуск окна Редактирование: Студент
    public void openEditStudent() throws SQLException {
// вызов сцены по двойному клику
        students_catalog.setRowFactory(catalog -> {
            TableRow<StudentEntity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        StudentEntity rowD = row.getItem();
                        boolean edit =runEditStudentController(rowD);
// обновление справочника Студенты после сохранения изменений
                        if (edit){
                            students= FXCollections.observableArrayList(StudentDao.unload());
                            students_catalog.setItems(students);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
            return row;
        });
    }

    private boolean runEditStudentController(StudentEntity student) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditStudentController.class.getClassLoader().getResource("fxmlEditStudent.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.setTitle("Редактирование: Студент");
        EditStudentController controller = loader.getController();
        controller.setDialogStage(stage);
        controller.setStudent(student);
        stage.showAndWait();
        return controller.isSaveClicked();
    }
}
