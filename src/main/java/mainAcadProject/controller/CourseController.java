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
import java.util.ResourceBundle;

/**
 * Created by ) on 12.04.2017.
 */
public class CourseController implements Initializable{

        private Label label;
        private ObservableList <CourseEntity> courses;
        @FXML
        private TableView<CourseEntity> table_courses = new TableView<CourseEntity>();
        @FXML
        private TableColumn<CourseEntity, String> tbl_col_name = new TableColumn<>();
        @FXML
        private TableColumn<CourseEntity, Double> tbl_col_hours = new TableColumn<>();
        ;

        public ObservableList<CourseEntity> getCourses() {
            return courses;
        }

        public void setCourses(ObservableList<CourseEntity> courses) {
            this.courses = courses;
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            try {
                courses=FXCollections.observableArrayList(CourseDao.unload());
                tbl_col_name.setCellFactory(TextFieldTableCell.forTableColumn());
                tbl_col_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
                tbl_col_hours.setCellFactory(TextFieldTableCell.forTableColumn(
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
                tbl_col_hours.setCellValueFactory(cellData -> cellData.getValue().total_hoursProperty().asObject());
                table_courses.setItems(courses);
                editLine();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public void editLine (){
            table_courses.setEditable(true);
            tbl_col_name.setOnEditCommit((TableColumn.CellEditEvent<CourseEntity, String> event) -> {
                TablePosition<CourseEntity, String> pos = event.getTablePosition();
                CourseEntity cou = event.getTableView().getItems().get(pos.getRow());
                cou.setName(event.getNewValue());
            });
            tbl_col_hours.setOnEditCommit((TableColumn.CellEditEvent<CourseEntity,Double> event) -> {
                TablePosition<CourseEntity, Double> pos = event.getTablePosition();
                CourseEntity cou = event.getTableView().getItems().get(pos.getRow());
                cou.setTotal_hours(event.getNewValue());
            });
        }

        @FXML
        public void add(){
            CourseEntity next=new CourseEntity();
            next.setId(-1);
            next.setName(" ");
            next.setTotal_hours(0.0);
            courses.add(next);
        }

        @FXML
        public void saveСhanges() throws SQLException {
            courses=table_courses.getItems();
            for (CourseEntity cou: courses) {
                CourseDao.update(cou);
            }
            courses=FXCollections.observableArrayList(CourseDao.unload());
            table_courses.setItems(courses);
        }

        @FXML
        public void delete() throws SQLException {
            int selectedIndex = table_courses.getSelectionModel().getSelectedIndex();
            table_courses.getItems().get(selectedIndex).setRemote(true);
            CourseDao.update(table_courses.getItems().get(selectedIndex));
            courses=FXCollections.observableArrayList(CourseDao.unload());
            table_courses.setItems(courses);
        }
// TODO сделать что-то с кнопкой отмена
}








