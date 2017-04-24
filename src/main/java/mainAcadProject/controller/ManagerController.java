package mainAcadProject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import mainAcadProject.dao.CourseDao;
import mainAcadProject.dao.ManagerDao;
import mainAcadProject.entity.CourseEntity;
import mainAcadProject.entity.ManagerEntity;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by ) on 21.04.2017.
 */
public class ManagerController implements Initializable{

    private ObservableList <ManagerEntity> managers;
    @FXML
    TableView <ManagerEntity> managers_catalog = new TableView <ManagerEntity>();
    @FXML
    TableColumn <ManagerEntity, String> managers_name = new TableColumn<>();
    @FXML
    TableColumn <ManagerEntity, String> managers_access = new TableColumn<>();

    public ObservableList<ManagerEntity> getManagers() {
        return managers;
    }

    public void setManagers(ObservableList<ManagerEntity> managers) {
        this.managers = managers;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try{
            managers= FXCollections.observableArrayList(ManagerDao.unload());
            managers_name.setCellFactory(TextFieldTableCell.forTableColumn());
            managers_name.setCellValueFactory((cellData -> cellData.getValue().nameProperty()));
            managers_access.setCellFactory((TextFieldTableCell.forTableColumn()));
            managers_access.setCellValueFactory(cellData -> cellData.getValue().accessProperty());
            managers_catalog.setItems(managers);
            editLine();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void editLine(){
        managers_catalog.setEditable(true);
        managers_name.setOnEditCommit((TableColumn.CellEditEvent<ManagerEntity, String> event) -> {
            TablePosition<ManagerEntity, String> pos = event.getTablePosition();
            ManagerEntity manager = event.getTableView().getItems().get(pos.getRow());
            manager.setName(event.getNewValue());
        });
        managers_access.setOnEditCommit((TableColumn.CellEditEvent<ManagerEntity, String> event) -> {
            TablePosition<ManagerEntity, String> pos = event.getTablePosition();
            ManagerEntity manager = event.getTableView().getItems().get(pos.getRow());
            manager.setAccess(event.getNewValue());
        });
    }

    @FXML
    public void add(){
        ManagerEntity next = new ManagerEntity();
        next.setId(-1);
        next.setName(" ");
        next.setAccess(" ");
        managers.add(next);
    }

    @FXML
    public void save() throws SQLException{
        managers=managers_catalog.getItems();
        for (ManagerEntity manag: managers){
            ManagerDao.update(manag);
        }
        managers= FXCollections.observableArrayList(ManagerDao.unload());
        managers_catalog.setItems(managers);
    }

    @FXML
    public void delete() throws SQLException{
        int selectedIndex = managers_catalog.getSelectionModel().getSelectedIndex();
        managers_catalog.getItems().get(selectedIndex).setRemote(true);
        ManagerDao.update(managers_catalog.getItems().get(selectedIndex));
        managers=FXCollections.observableArrayList(ManagerDao.unload());
        managers_catalog.setItems(managers);
    }
    // TODO сделать что-то с кнопкой Закрыть
    @FXML
    public void close(){

    }
}
