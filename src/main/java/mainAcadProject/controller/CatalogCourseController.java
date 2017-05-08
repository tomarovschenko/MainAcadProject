package mainAcadProject.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import mainAcadProject.entity.CourseEntity;
import mainAcadProject.dao.CourseDao;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by ) on 12.04.2017.
 */
public class CatalogCourseController implements Initializable{

    // TODO

    private ObservableList <CourseEntity> courses;
    @FXML
    private TableView<CourseEntity> courses_catalog = new TableView<CourseEntity>();
    @FXML
    private TableColumn<CourseEntity, String> course_name = new TableColumn<>();
    @FXML
    private TableColumn<CourseEntity, Double> course_hours = new TableColumn<>();

    private static Stage dialogStage = new Stage();

    public static Stage getDialogStage() {
        return dialogStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            courses=FXCollections.observableArrayList(CourseDao.unload());
            course_name.setCellFactory(TextFieldTableCell.forTableColumn());
            course_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            course_hours.setCellFactory(TextFieldTableCell.forTableColumn(
// TODO дописать свой конвертер DoubleUtil
                    new StringConverter<Double>() {
                        @Override public String toString(Double object) {
                            return String.format("%.2f", object);
                        }
                        @Override public Double fromString(String string) {
                            try {
                                return Double.valueOf(string);
                            } catch (NumberFormatException ex) {
                                return 0.0;
                            }
                        }}));
            course_hours.setCellValueFactory(cellData -> cellData.getValue().total_hoursProperty().asObject());
            courses_catalog.setItems(courses);
            editLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void editLine (){
        courses_catalog.setEditable(true);
        course_name.setOnEditCommit((TableColumn.CellEditEvent<CourseEntity, String> event) -> {
            TablePosition<CourseEntity, String> pos = event.getTablePosition();
            CourseEntity cou = event.getTableView().getItems().get(pos.getRow());
            cou.setName(event.getNewValue());
        });
        course_hours.setOnEditCommit((TableColumn.CellEditEvent<CourseEntity,Double> event) -> {
            TablePosition<CourseEntity, Double> pos = event.getTablePosition();
            CourseEntity cou = event.getTableView().getItems().get(pos.getRow());
            cou.setTotal_hours(event.getNewValue());
        });
    }

    @FXML
    public void add(){
        CourseEntity next=new CourseEntity();
        next.setId(-1);
        next.setName("");
        next.setTotal_hours(0.0);
        courses.add(next);
    }

    @FXML
    public void saveChanges() throws SQLException {
        courses=courses_catalog.getItems();
        for (CourseEntity cou: courses) {
            if (cou.getId()>0) {
                CourseDao.update(cou);
            }
            else {
                CourseDao.insert(cou);
            }
        }
        courses=FXCollections.observableArrayList(CourseDao.unload());
        courses_catalog.setItems(courses);
    }

    @FXML
    public void delete() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Подтверждение удаления");
        alert.setHeaderText("Вы действительно хотите удалить выбранный курс?");
        alert.setContentText("Нажмите OK для удаления");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int selectedIndex = courses_catalog.getSelectionModel().getSelectedIndex();
            courses_catalog.getItems().get(selectedIndex).setRemote(true);
            CourseDao.update(courses_catalog.getItems().get(selectedIndex));
            courses=FXCollections.observableArrayList(CourseDao.unload());
            courses_catalog.setItems(courses);
        } else {
           alert.close();
        }
    }

    @FXML
    public void close(){
        dialogStage.close();
    }
}








