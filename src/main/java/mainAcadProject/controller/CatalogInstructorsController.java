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
import mainAcadProject.entity.InstructorEntity;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by ) on 24.04.2017.
 */
public class CatalogInstructorsController implements Initializable {

    private ObservableList <InstructorEntity> instructors;

    @FXML
    TableView <InstructorEntity> instructors_catalog = new TableView<>();
    @FXML
    TableColumn <InstructorEntity, String> instructor_surname = new TableColumn <>();
    @FXML
    TableColumn <InstructorEntity, String> instructor_name = new TableColumn <>();
    @FXML
    TableColumn <InstructorEntity, String> instructor_patronymic = new TableColumn <>();
    @FXML
    TableColumn <InstructorEntity, String> instructor_phone = new TableColumn <>();
    @FXML
    TableColumn <InstructorEntity, String> instructor_passport = new TableColumn <>();
    @FXML
    TableColumn <InstructorEntity, String> instructor_mail = new TableColumn <>();
    @FXML
    TableColumn <InstructorEntity, String> instructor_working = new TableColumn <>();



    private static Stage dialogStage = new Stage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            instructors= FXCollections.observableArrayList(InstructorDao.unload());
            instructor_surname.setCellFactory(TextFieldTableCell.forTableColumn());
            instructor_surname.setCellValueFactory((cellData -> cellData.getValue().surnameProperty()));
            instructor_name.setCellFactory(TextFieldTableCell.forTableColumn());
            instructor_name.setCellValueFactory((cellData -> cellData.getValue().nameProperty()));
            instructor_patronymic.setCellFactory(TextFieldTableCell.forTableColumn());
            instructor_patronymic.setCellValueFactory((cellData -> cellData.getValue().patronymicProperty()));
            instructor_phone.setCellFactory(TextFieldTableCell.forTableColumn());
            instructor_phone.setCellValueFactory((cellData -> cellData.getValue().phoneProperty()));
            instructor_passport.setCellFactory(TextFieldTableCell.forTableColumn());
            instructor_passport.setCellValueFactory((cellData -> cellData.getValue().passportProperty()));
            instructor_mail.setCellFactory(TextFieldTableCell.forTableColumn());
            instructor_mail.setCellValueFactory((cellData -> cellData.getValue().emailProperty()));
            instructor_working.setCellFactory(TextFieldTableCell.forTableColumn());
            instructor_working.setCellValueFactory((cellData -> cellData.getValue().workingProperty()));
            instructors_catalog.setItems(instructors);
            openEditInstructor();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Stage getDialogStage() {
        return dialogStage;
    }

    @FXML
    public void delete() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение удаления");
        alert.setHeaderText("Вы действительно хотите удалить выбранного инструктора?");
        alert.setContentText("Нажмите OK для удаления");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int selectedIndex = instructors_catalog.getSelectionModel().getSelectedIndex();
            instructors_catalog.getItems().get(selectedIndex).setRemote(true);
            InstructorDao.update(instructors_catalog.getItems().get(selectedIndex));
            instructors=FXCollections.observableArrayList(InstructorDao.unload());
            instructors_catalog.setItems(instructors);
        } else {
            alert.close();
        }
    }

    @FXML
    public void close(){
        dialogStage.close();
    }

// запуск окна Редактирование: Инструктор
    public void openEditInstructor() throws SQLException {
// вызов сцены по двойному клику
        instructors_catalog.setRowFactory(catalog -> {
            TableRow<InstructorEntity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        InstructorEntity rowD = row.getItem();
                        boolean edit =runEditInstructorController(rowD);
// обновление справочника Инструкторы после сохранения изменений
                        if (edit){
                            instructors= FXCollections.observableArrayList(InstructorDao.unload());
                            instructors_catalog.setItems(instructors);
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

    private boolean runEditInstructorController(InstructorEntity instructor) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditInstructorController.class.getClassLoader().getResource("fxmlEditInstructors.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.setTitle("Редактирование: Инструктор");
        EditInstructorController controller = loader.getController();
        controller.setDialogStage(stage);
        controller.setInstructor(instructor);
        stage.showAndWait();
        return controller.isSaveClicked();
    }
}
