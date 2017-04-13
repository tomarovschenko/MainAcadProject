package mainAcadProject.controller;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import mainAcadProject.entity.CourseEntity;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ) on 12.04.2017.
 */
public class CourseController implements Initializable{

        private Label label;

        ObservableList<CourseEntity> courses = FXCollections.observableArrayList();
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




            // table_courses.setDisable(true);
            tbl_col_name.setCellFactory(TextFieldTableCell.forTableColumn());
            tbl_col_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
            //    tbl_col_hours.setCellFactory(TextFieldTableCell.forTableColumn());
            tbl_col_hours.setCellFactory(TextFieldTableCell.forTableColumn(
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
            // tbl_col_hours.setCellValueFactory(cellData -> cellData.getValue().total_hoursProperty().asString());
 /*       tbl_col_hours.setCellFactory(col ->
                new TableCell<Course, Double>() {
                    @Override
                    public void updateItem(Double hours, boolean empty) {
                        super.updateItem(hours, empty);
                        if (empty) {
                            setText(null);
                        } else {
                            setText(String.format("%.2f", hours.doubleValue()));
                        }
                    }
                });
  */

            table_courses.setItems(courses);
            //редактирование ячеек и сохранение информации после нажатия клавиши Enter
            table_courses.setEditable(true);
            Callback<TableColumn<CourseEntity, String>,
                    TableCell<CourseEntity, String>> cellFactory
                    = (TableColumn<CourseEntity, String> p) -> new EditingCell();

        }

        @FXML
        public void add(){
           CourseEntity next=new CourseEntity();

            courses.add(next);

        }
        @FXML
        public ObservableList saveСhanges(){
            System.out.println("SAVE");
            courses=table_courses.getItems();
            for (CourseEntity cou: courses) {
                System.out.println(cou.toString());
            }
            return courses;


        }
        @FXML
        public void delete(){
            int selectedIndex = table_courses.getSelectionModel().getSelectedIndex();
            table_courses.getItems().get(selectedIndex).setRemote(true);
        }
    }

    class EditingCell extends TableCell<CourseEntity, String> {

        private TextField textField;

        public EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {
                    setText(getString());
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(
                    (ObservableValue<? extends Boolean> arg0,
                     Boolean arg1, Boolean arg2) -> {
                        if (!arg2) {
                            commitEdit(textField.getText());
                        }
                    });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }








