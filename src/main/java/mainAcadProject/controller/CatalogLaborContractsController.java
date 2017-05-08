package mainAcadProject.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.util.converter.DateStringConverter;
import mainAcadProject.dao.CourseDao;
import mainAcadProject.dao.InstructorDao;
import mainAcadProject.dao.LaborContractDao;
import mainAcadProject.entity.InstructorEntity;
import mainAcadProject.entity.LaborContractEntity;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by ) on 24.04.2017.
 */
public class CatalogLaborContractsController implements Initializable{

    private ObservableList <LaborContractEntity> contracts;
    @FXML
    TableView <LaborContractEntity> contracts_catalog = new TableView<>();
    @FXML
    TableColumn <LaborContractEntity, String> contract_number = new TableColumn<>();
    @FXML
    TableColumn <LaborContractEntity, String> contract_dateStart = new TableColumn<>();
    @FXML
    TableColumn <LaborContractEntity, String> contract_dateEnd = new TableColumn<>();
    @FXML
    TableColumn <LaborContractEntity, String> contract_instructor = new TableColumn<>();

    //TODO


    private static Stage dialogStage = new Stage();

    public static Stage getDialogStage() {
        return dialogStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            contracts=FXCollections.observableArrayList(LaborContractDao.unload());
            contract_number.setCellFactory(TextFieldTableCell.forTableColumn());
            contract_number.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
            contract_dateStart.setCellFactory(TextFieldTableCell.forTableColumn());
            contract_dateStart.setCellValueFactory(cellData -> cellData.getValue().date_startProperty());
            contract_dateEnd.setCellFactory(TextFieldTableCell.forTableColumn());
            contract_dateEnd.setCellValueFactory(cellData -> cellData.getValue().date_endProperty());
            contract_instructor.setCellFactory(TextFieldTableCell.forTableColumn());
            contract_instructor.setCellValueFactory(cellData -> cellData.getValue().getFullName());
            contracts_catalog.setItems(contracts);
            openEditLaborContract();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // запуск окна Редактирование: Трудовой договор
    public void openEditLaborContract() throws SQLException {
// вызов сцены по двойному клику
        contracts_catalog.setRowFactory(catalog -> {
            TableRow<LaborContractEntity> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {
                        LaborContractEntity rowD = row.getItem();
                        boolean edit =runEditLaborContractController(rowD);
// обновление справочника Трудовых договоров после сохранения изменений
                        if (edit){
                            contracts= FXCollections.observableArrayList(LaborContractDao.unload());
                            contracts_catalog.setItems(contracts);
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

    @FXML
    public void delete() throws SQLException {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение удаления");
            alert.setHeaderText("Вы действительно хотите удалить выбранный договор?");
            alert.setContentText("Нажмите OK для удаления");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                int selectedIndex = contracts_catalog.getSelectionModel().getSelectedIndex();
                contracts_catalog.getItems().get(selectedIndex).setRemote(true);
                LaborContractDao.update(contracts_catalog.getItems().get(selectedIndex));
                contracts = FXCollections.observableArrayList(LaborContractDao.unload());
                contracts_catalog.setItems(contracts);
            }
            else {
                alert.close();
            }
    }

    @FXML
    public void close(){
        dialogStage.close();
    }

    private boolean runEditLaborContractController(LaborContractEntity laborContractEntity) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EditInstructorController.class.getClassLoader().getResource("fxmlEditLaborContract.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.setTitle("Редактирование: Трудовой договор");
        EditLaborContractController controller = loader.getController();
        controller.setDialogStage(stage);
        controller.setLaborContract(laborContractEntity);
        stage.showAndWait();
        return controller.isSaveClicked();
    }


}
