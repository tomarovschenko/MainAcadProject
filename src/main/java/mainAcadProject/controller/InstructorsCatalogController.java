package mainAcadProject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import mainAcadProject.dao.InstructorDao;
import mainAcadProject.entity.InstructorEntity;
import mainAcadProject.entity.ManagerEntity;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by ) on 24.04.2017.
 */
public class InstructorsCatalogController implements Initializable {

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
          //  editLine();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static Stage getDialogStage() {
        return dialogStage;
    }
// TODO Дописать кнопку
    @FXML
    public void editInstructor(){}

    @FXML
    public void delete() throws SQLException {
        int selectedIndex = instructors_catalog.getSelectionModel().getSelectedIndex();
        instructors_catalog.getItems().get(selectedIndex).setRemote(true);
        InstructorDao.update(instructors_catalog.getItems().get(selectedIndex));
        instructors=FXCollections.observableArrayList(InstructorDao.unload());
        instructors_catalog.setItems(instructors);
    }

    @FXML
    public void close(){
        dialogStage.close();
    }
}
