package lk.ijse.cric.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashFormController {


    public AnchorPane AdminLoader;
    public AnchorPane rootNode;
    public AnchorPane AdminUserLoader;
    public static AnchorPane idPaneBlurred;
    public AnchorPane idPaneLinkUser;
    public AnchorPane idPaneLinkCoach;
    public AnchorPane idPaneLinkSponsor;
    public AnchorPane idPaneLinkDonation;
    public JFXButton idBtnCoach;
    public Label lblSetUsername;

    private String logAdmin;

//    public AdminDashFormController(String username) {
//        this.logAdmin = username;
//    }

    public String getLogAdmin() {
        return logAdmin;
    }

    public void setLogAdmin(String logAdmin) {
        this.logAdmin = logAdmin;
        lblSetUsername.setText(logAdmin+" !!!");

    }

    public void userOnAction(ActionEvent actionEvent) throws IOException {
//        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/supplier_form.fxml"));
//
//        this.Pane.getChildren().clear();
//        this.Pane.getChildren().add(rootNode);
        idPaneLinkUser.setStyle("-fx-background-color:  #ecf0f1;");
        idPaneLinkCoach.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkSponsor.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkDonation.setStyle("-fx-background-color:   #74b9ff;");

        /*Parent rootNode = FXMLLoader.load(getClass().getResource("/view/User_form.fxml"));*/
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/User_form.fxml"));

        this.AdminUserLoader.getChildren().clear();
        this.AdminUserLoader.getChildren().add(root);
    }
   /* public void userOnAction() throws IOException {

        Parent  rootNode = FXMLLoader.load(this.getClass().getResource("/view/User_form.fxml"));

        this.AdminUserLoader.getChildren().clear();
        this.AdminUserLoader.getChildren().add(rootNode);

    }*/

    public void sponsorOnAction(ActionEvent actionEvent) throws IOException {
        idPaneLinkUser.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkCoach.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkSponsor.setStyle("-fx-background-color:  #ecf0f1;");
        idPaneLinkDonation.setStyle("-fx-background-color:   #74b9ff;");

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/Sponsor_form.fxml"));

        this.AdminUserLoader.getChildren().clear();
        this.AdminUserLoader.getChildren().add(root);
    }

    public void donationOnAction(ActionEvent actionEvent) throws IOException {
        idPaneLinkUser.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkCoach.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkSponsor.setStyle("-fx-background-color:   #74b9ff");
        idPaneLinkDonation.setStyle("-fx-background-color:   #ecf0f1;");

        Parent root = FXMLLoader.load(this.getClass().getResource("/view/Donation_form.fxml"));

        this.AdminUserLoader.getChildren().clear();
        this.AdminUserLoader.getChildren().add(root);
    }

    public void coachOnAction(ActionEvent actionEvent) throws IOException {

        idPaneLinkUser.setStyle("-fx-background-color:   #74b9ff;");
        idPaneLinkCoach.setStyle("-fx-background-color:  #ecf0f1;");
        idPaneLinkSponsor.setStyle("-fx-background-color: #74b9ff;");
        idPaneLinkDonation.setStyle("-fx-background-colour: #74b9ff;");


       Parent root =  FXMLLoader.load(this.getClass().getResource("/view/Coaches_form.fxml"));

       this.AdminUserLoader.getChildren().clear();
       this.AdminUserLoader.getChildren().add(root);
    }

    public void initialize() throws IOException {

        idPaneLinkCoach.setStyle("-fx-background-color:  #ecf0f1;");
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/Coaches_form.fxml"));
        this.AdminUserLoader.getChildren().add(root);


    }

    public static void blurred(){
        BoxBlur blur = new BoxBlur();
        blur.setWidth(1455);
        blur.setHeight(791);
        idPaneBlurred.setEffect(blur);

    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(scene);


        stage.centerOnScreen();
        stage.setTitle("Login Form");
        stage.setResizable(false);
        stage.show();
    }



}
