package mainAcadProject.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by ) on 24.04.2017.
 */
public class EditInstructorController implements Initializable {

    private static Stage dialogStage = new Stage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void saveChanges(){

    }

    @FXML
    public void cancel(){
        dialogStage.close();
    }

    @FXML
    public void create_contract(){

    }
}
