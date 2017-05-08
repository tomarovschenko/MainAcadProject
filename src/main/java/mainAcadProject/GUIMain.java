package mainAcadProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by ) on 13.04.2017.
 */
public class GUIMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("rootLayout.fxml"));
        primaryStage.setTitle("Main Academy");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void runUI(String [] args) {
        launch(args);
    }


}
