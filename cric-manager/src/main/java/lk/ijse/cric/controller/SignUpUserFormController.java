package lk.ijse.cric.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cric.bo.BOFactory;
import lk.ijse.cric.bo.custom.UserBo;
import lk.ijse.cric.dto.UserDto;
//import lk.ijse.cric.model.SignUpModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class SignUpUserFormController {

    public AnchorPane rootNode;
    public TextField txtUserId;
    public TextField txtUsername;
    public TextField txtNewPwd;
    public TextField txtConfirmPwd;
    public Label lblErrorInvalid;
    public Label lblConfmPwd;
    public Label lblNewPwd;
    public Label lblFillAll;
    public JFXButton btnIdBack;

    UserBo userBo = (UserBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USER);

    public void initialize(){

        btnIdBack.setVisible(false);
    }


    public void btnCreateOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {

        String userId = txtUserId.getText();
        String userName = txtUsername.getText();
        String createPwd = txtNewPwd.getText();
        String confirmPwd = txtConfirmPwd.getText();

        lblConfmPwd.setText("");

        /*System.out.println(txtUserId.getText());//////////////////////////////////

        System.out.println(SignUpModel.findUserName(userId));/////////////////////

        System.out.println(userName.equals(SignUpModel.findUserName(userId)));/////////////////////*/

        if (userId.isEmpty() | userName.isEmpty() |createPwd.isEmpty() |confirmPwd.isEmpty()) {
            lblFillAll.setText("Fill all fields");
            lblConfmPwd.setText("");
        }else {
            lblFillAll.setText("");
            if(!validation()){
                //if(SignUpModel.isValidUser(userId,userName)){
                if(userBo.isValidUser(userId)){

                    //if (userName.equals(SignUpModel.findUserName(userId))) {
                    if (userName.equals(userBo.findUserName(userId))) {
                        lblErrorInvalid.setText("");
                        if (createPwd.equals(confirmPwd)) {

                            new Alert(Alert.AlertType.CONFIRMATION,"Create Successfully").show();

                            //SignUpModel.saveUserDetails(userName, createPwd);
                            userBo.saveUserDetails(new UserDto(userName, createPwd));

                            Stage stage = (Stage) rootNode.getScene().getWindow();
                            stage.close();

//
                        } else {
                            txtNewPwd.setText("");
                            txtConfirmPwd.setText("");
                            lblConfmPwd.setText("Use Same password above fields");
                        }

                    } else {
                        lblErrorInvalid.setText("Invalid Username");
                        //lblErrorInvalid.setStyle("-fx-background-color: red");
                        //txtUserId.setText(" ");
                        txtUsername.setText("");
                        //txtNewPwd.setText(" ");
                        //txtConfirmPwd.setText(" ");
                    }

                }else{
                    new Alert(Alert.AlertType.ERROR,"Sorry !\n User account all ready exist OR Your UserID is expired!").show();
                }

            }


        }
        //System.out.println(SignUpModel.findUserName(userId));


       /* AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(scene);

        stage.centerOnScreen();
        stage.setTitle("Login Form");
        stage.setResizable(false);
        stage.show();*/
    }

    private boolean validation() {
        String uId = txtUserId.getText();
        String uName = txtUsername.getText();
        String pwd = txtConfirmPwd.getText();

        boolean matches = Pattern.matches("[U][0-9]{3,}",uId);

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid UserID !").show();
            txtUserId.setText("");
            return true;
        }

        boolean matches1 =Pattern.matches("[A-Za-z]{3,}", uName);
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Invalid Username ! ").show();
            txtUsername.setText("");
            return true;
        }

        boolean matches2 =Pattern.matches("[\\w\\d\\.\\!\\@\\#\\$\\%\\^\\&\\*(\\)\\_\\+\\=\\?\\>\\<\\:\\\"\\|\\\\\\~\\,\\'\\;]{6,}",pwd);
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Password should be 6 or more characters!").show();
            txtNewPwd.setText("");
            txtConfirmPwd.setText("");
            return true;
        }
       /* boolean matches2 =Pattern.matches("/\\S/g", txtNewPwd.getText());
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Please you can't include spaces for password!").show();
            txtNewPwd.setText("");
            txtConfirmPwd.setText("");
            return true;
        }*/else return false;
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.setScene(scene);

        stage.centerOnScreen();
        stage.setTitle("Login Form");
        stage.setResizable(false);
        stage.show();
    }

   /* public void usernameOnMousereleased(MouseEvent mouseEvent) throws SQLException {
        String userId = txtUserId.getText();

        System.out.println(SignUpModel.findUserName(userId));
        String uname = SignUpModel.findUserName(userId);
        txtUsername.setText(uname);
    }*/
}


