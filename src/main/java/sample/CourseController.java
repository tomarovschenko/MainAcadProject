package sample;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class CourseController implements Initializable {

    private Label label;

    ObservableList<Course> courses = FXCollections.observableArrayList();
    @FXML
    private TableView<Course> table_courses = new TableView<Course>();
    @FXML
    private TableColumn<Course, String> tbl_col_name = new TableColumn<>();
    @FXML
    private TableColumn<Course, Double> tbl_col_hours = new TableColumn<>();
    ;

    public ObservableList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ObservableList<Course> courses) {
        this.courses = courses;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Course course1 = new Course(1, "OP", 128);
        Course course2 = new Course(1, "OP", 128);
        Course course3 = new Course(1, "OP", 128);
        Course course4 = new Course(1, "OP", 128);
        Course course5 = new Course(1, "OP", 128);
        Course course6 = new Course(1, "OP", 128);
        Course course7 = new Course(1, "OP", 128);
        Course course8 = new Course(1, "OP", 128);
        Course course9 = new Course(1, "OP", 128);
        Course course10 = new Course(1, "OP", 128);
        Course course11 = new Course(1, "OP", 128);
        Course course12 = new Course(1, "OP", 128);
        Course course13 = new Course(1, "OP", 128);
        Course course14 = new Course(1, "OP", 128);
        Course course15 = new Course(1, "OP", 128);
        Course course16 = new Course(1, "OP", 128);
        Course course17 = new Course(1, "OP", 128);
        Course course18 = new Course(1, "OP", 128);
        Course course19 = new Course(1, "OP", 128);

        courses.add(course1);
        courses.add(course2);
        courses.add(course3);
        courses.add(course4);
        courses.add(course5);
        courses.add(course6);
        courses.add(course7);
        courses.add(course8);
        courses.add(course9);
        courses.add(course10);
        courses.add(course11);
        courses.add(course12);
        courses.add(course13);
        courses.add(course14);
        courses.add(course15);
        courses.add(course16);
        courses.add(course17);
        courses.add(course18);
        courses.add(course19);


        // table_courses.setDisable(true);
        tbl_col_name.setCellFactory(TextFieldTableCell.forTableColumn());
        tbl_col_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
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
                    }
                }));
        tbl_col_hours.setCellValueFactory(cellData -> cellData.getValue().total_hoursProperty().asObject());

        table_courses.setItems(courses);
        //редактирование ячеек и сохранение информации после нажатия клавиши Enter
        table_courses.setEditable(true);
        Callback<TableColumn<Course, String>,
                TableCell<Course, String>> cellFactory
                = (TableColumn<Course, String> p) -> new EditingCell();

    }

    private Double DoubleStringConverter(String str) {
        return Double.parseDouble(str);
    }

    @FXML
    public void add(){

    }
    @FXML
    public ObservableList saveСhanges(){
        System.out.println("SAVE");
        return table_courses.getItems();

    }
    @FXML
    public void delete(){


    }
}

    class EditingCell extends TableCell<Course, String> {

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






