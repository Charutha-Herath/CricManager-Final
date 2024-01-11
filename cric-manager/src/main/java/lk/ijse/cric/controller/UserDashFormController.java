package lk.ijse.cric.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class UserDashFormController {

    public AnchorPane userPaneLoader;
    public AnchorPane idPaneLinkUmpire;
    public AnchorPane idPaneLinkPlayer;
    public AnchorPane idPaneLinkMatch;
    public AnchorPane idPaneLinkTour;
    public AnchorPane idPaneLinkReports;
    public AnchorPane rootNode;
    public Label lblLoginUserName;

    private String LoginUser;

    public String getLoginUser() {
        return LoginUser;
    }

    public void setLoginUser(String loginUser) {
        LoginUser = loginUser;
        lblLoginUserName.setText(LoginUser);
    }

    public void initialize() throws IOException {
        idPaneLinkPlayer.setStyle("-fx-background-color:  #dff9fb;");
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/Player_form.fxml"));

        this.userPaneLoader.getChildren().clear();
        this.userPaneLoader.getChildren().add(root);
    }
    public void btnUmpireOnAction(ActionEvent actionEvent) throws IOException {
        idPaneLinkUmpire.setStyle("-fx-background-color:  #dff9fb;");
        idPaneLinkPlayer.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkMatch.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkTour.setStyle("-fx-background-color:   #74b9ff;");

        //Parent root = FXMLLoader.load(this.getClass().getResource("/view/Umpire_form.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("/view/Umpire_form.fxml")));
        AnchorPane anchorPane = fxmlLoader.load();

//        Scene scene = new Scene(anchorPane);

        UmpireFormController controller = fxmlLoader.getController();
        controller.setLoginUsername(getLoginUser());


        this.userPaneLoader.getChildren().clear();
        this.userPaneLoader.getChildren().add(anchorPane);
    }

    public void btnPlayerOnAction(ActionEvent actionEvent) throws IOException {
        idPaneLinkUmpire.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkPlayer.setStyle("-fx-background-color:  #dff9fb;");
        idPaneLinkMatch.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkTour.setStyle("-fx-background-color:   #74b9ff;");

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/Player_form.fxml"));

        this.userPaneLoader.getChildren().clear();
        this.userPaneLoader.getChildren().add(root);
    }

    public void btnMatchOnAction(ActionEvent actionEvent) throws IOException {
        idPaneLinkUmpire.setStyle("-fx-background- #74b9ff;");
        idPaneLinkPlayer.setStyle("-fx-background- #74b9ff;");
        idPaneLinkMatch.setStyle("-fx-background-color:   #dff9fb;");
        idPaneLinkTour.setStyle("-fx-background- #74b9ff;");

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/Matches_form.fxml"));

        this.userPaneLoader.getChildren().clear();
        this.userPaneLoader.getChildren().add(root);
    }

    public void btnTourmntOnAction(ActionEvent actionEvent) throws IOException {

    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(scene);


        stage.centerOnScreen();
        stage.setTitle("Login Form");
        stage.setResizable(false);
        stage.show();
    }



    public void btnCostOnAction(ActionEvent actionEvent) throws IOException {
        idPaneLinkUmpire.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkPlayer.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkMatch.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkTour.setStyle("-fx-background-color: #dff9fb;");

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/Cost_form.fxml"));

        this.userPaneLoader.getChildren().clear();
        this.userPaneLoader.getChildren().add(root);
    }
}
