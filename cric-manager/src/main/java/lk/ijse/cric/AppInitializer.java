package lk.ijse.cric;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import static javafx.application.Application.launch;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws IOException {

        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"))));
        stage.initStyle(StageStyle.DECORATED);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
        stage.setFullScreen(false);
        stage.setResizable(false);
        stage.show();
    }
}
