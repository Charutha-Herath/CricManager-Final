package lk.ijse.cric.controller;


import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.cric.bo.BOFactory;
import lk.ijse.cric.bo.custom.UserBo;
import lk.ijse.cric.dto.LoginDto;
import lk.ijse.cric.dto.UserDto;
//import lk.ijse.cric.model.LoginModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class LoginFormController {


    public TextField txtUsername;
    public PasswordField txtPassword;
    public Label lblIncorrectUP;
    public JFXButton idLodinBtn;
    public AnchorPane paneLoginTP;
    public JFXButton btnIdClose;

    @FXML
    private AnchorPane root;

    UserBo userBo = (UserBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USER);

    SponsorFormController sponsorFormController = new SponsorFormController();


    public String getTxtUsername() {
        return txtUsername.getText();
    }

    public String getTxtPassword() {
        return txtPassword.getText();
    }

    public void initialize(){
//        Tooltip tip = new Tooltip("This is login button");
//        Tooltip.install(idLodinBtn,tip);
        btnIdClose.setVisible(false);

        paneLoginTP.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3); -fx-background-radius: 5 40 5 40;");


    }

    public void loginOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        lblIncorrectUP.setText("");


        //loadAdminDash("Kamal");                                                     // Test
        //loadUserDash("Mahesh");
        if (username.isEmpty() | password.isEmpty()){
            lblIncorrectUP.setText("Fill all fields");
        }else{
            if (!validateUser()) {
                lblIncorrectUP.setText("");
                UserDto login = new UserDto(username, password);

                //boolean authenticateResultUser = LoginModel.authenticateUser(login);
                boolean authenticateResultUser = userBo.authenticateUser(login);

                //boolean authenticateResultAdmin = LoginModel.authenticateAdmin(username,password);
                boolean authenticateResultAdmin = userBo.authenticateAdmin(login);

                if (authenticateResultUser || authenticateResultAdmin) {
                    //System.out.println("Login Success");
                    //String currentUser = LoginModel.getUser(login);
                    String currentUser = userBo.getUser(login);


                    if(currentUser==null){
                        txtUsername.setText("");
                        txtPassword.setText("");
                        new Alert(Alert.AlertType.ERROR,"Incorrect Username or password").show();

                    }else {
                        if (currentUser.startsWith("A")) {
                            loadAdminDash(username);

                            CoachesFormController AdminCoach = new CoachesFormController();
                            //AdminCoach.setCoach(LoginModel.getAdminId(username));
                            AdminCoach.setCoach(userBo.getAdminId(username));

                            //new SponsorFormController(username,password);
                            sponsorFormController.setAdminUName(username);
                            sponsorFormController.setAdminPwd(password);


                        } else if (currentUser.startsWith("U")) {
                            //new PlayerFormController(username);
                            loadUserDash(username);
                        }
                    }

                } else {
                    txtUsername.setText("");
                    txtPassword.setText("");
                    lblIncorrectUP.setText("Incorrect Username or password");
                    //System.out.println("Login Failed");
                }
            }




        }



    }
    private boolean validateUser() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        boolean matches =Pattern.matches("[A-Za-z]{3,}", username);

        if (!matches) {
            new Alert(Alert.AlertType.ERROR, "Invalid Username. Please you can't include Symbols and Numbers !").show();
            txtUsername.setText("");
            return true;
        }

        else return false;
    }
    private void loadAdminDash(String name) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("/view/AdminDash_form.fxml")));
        AnchorPane anchorPane = fxmlLoader.load();

        Scene scene = new Scene(anchorPane);

        AdminDashFormController controller = fxmlLoader.getController();
        controller.setLogAdmin(name);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("AdminDash");
        stage.centerOnScreen();
        stage.show();

        //AdminDashFormController.userOnAction();

    }

    private void loadUserDash(String name) throws IOException {
        //AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/UserDash_form.fxml"));

        FXMLLoader fxmlLoader = new FXMLLoader((getClass().getResource("/view/UserDash_form.fxml")));
        AnchorPane anchorPane = fxmlLoader.load();

        Scene scene = new Scene(anchorPane);

        UserDashFormController controller = fxmlLoader.getController();
        controller.setLoginUser(name);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("UserDash");
        stage.centerOnScreen();
        stage.show();
    }


    public void btnSignUpNewUserOnAction(ActionEvent actionEvent) throws IOException {

//        BoxBlur blur = new BoxBlur();
//        blur.setWidth(1920);
//        blur.setHeight(1080);
//        root.setEffect(blur);

        Parent pane = FXMLLoader.load(this.getClass().getResource("/view/SignUpUser_form.fxml"));

        Scene scene = new Scene(pane);

        Stage stage = new Stage();
        stage.setTitle("SignUp Users");
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }



    public void btnCloseOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();

        stage.close();
    }

    public void loginOnAction2(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        loginOnAction(actionEvent);
    }
}
