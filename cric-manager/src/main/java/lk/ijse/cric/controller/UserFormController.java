package lk.ijse.cric.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.cric.bo.BOFactory;
import lk.ijse.cric.bo.custom.UserBo;
import lk.ijse.cric.dto.UserDto;
//import lk.ijse.cric.model.UserModel;

import java.io.IOException;
import java.net.URL;
import java.rmi.server.UID;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

//import static lk.ijse.cric.controller.AdminDashFormController.rootNode;

public class UserFormController implements Initializable {
    public Label lblUserId;
    public Label lblUserName;
    public Label lblStartDate;
    public Label lblNextUserId;
    public TextField txtUpdateName;
    public TextField txtUpdateDate;
    public AnchorPane paneBlur;
    public Label lblTodayDate;
    public AnchorPane idPaneUserLoader;
    public AnchorPane idPaneCurrentUserDetails;

    UserBo userBo = (UserBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USER);
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        lblTodayDate.setText(String.valueOf(LocalDate.now()));
        initializeWithStart();

        //idPaneCurrentUserDetails.setStyle("-fx-effect: dropshadow(3px 3px 5px #95afc0);");
    }

    public void initializeWithStart(){
        try {
            generateNextUserId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            //UserDto userDto = UserModel.searchUserId();
            UserDto userDto = userBo.getCurretUserDetails();


            if (userDto.getUName().equals(null)|userDto.getUId().equals(null)|userDto.getStartDate().equals(null)){
                System.out.println(userDto.getUName());
                System.out.println(userDto.getUId());
                System.out.println(userDto.getStartDate());

            }else{


                }

            lblUserId.setText(userDto.getUId());
            lblUserName.setText(userDto.getUName());
            lblStartDate.setText(userDto.getStartDate());

        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING,e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextUserId() throws SQLException, ClassNotFoundException {
        //String st = UserModel.generateNextUserId();
        String st = userBo.generateNextUserId();
        lblNextUserId.setText(st);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        txtUpdateName.setStyle("-fx-border-color: white; -fx-border-radius: 10px;-fx-background-radius: 10px");
        txtUpdateDate.setStyle("-fx-border-color: white; -fx-border-radius: 10px;-fx-background-radius: 10px");

        String id = lblNextUserId.getText();
        String name = txtUpdateName.getText();
        String date = txtUpdateDate.getText();
        String pw = null;
        String dt = String.valueOf(date);

        if (!name.isEmpty() & !date.isEmpty()){

            //UserModel.saveNewUser(id, name, nu, dt);
            boolean b1 = userBo.saveNewUser(new UserDto(id, name, pw, dt));

            //String st = UserModel.generateNextUserId();
            String st = userBo.generateNextUserId();
            lblNextUserId.setText(st);

            txtUpdateName.setText("");
            txtUpdateDate.setText("");

            txtUpdateName.setStyle("-fx-border-color: white; -fx-border-radius: 10px;-fx-background-radius: 10px");
            txtUpdateDate.setStyle("-fx-border-color: white; -fx-border-radius: 10px;-fx-background-radius: 10px");
            initializeWithStart();
        }else{
            if (name.isEmpty()){
                txtUpdateName.setStyle("-fx-border-color: red; -fx-border-radius: 10px;-fx-background-radius: 10px");
            }
            if (date.isEmpty()) {
                txtUpdateDate.setStyle("-fx-border-color: red; -fx-border-radius: 10px;-fx-background-radius: 10px");
            }

        }
    }

    public void btnShowPreUsersOnAction(ActionEvent actionEvent) throws IOException {
        Parent pane = FXMLLoader.load(this.getClass().getResource("/view/ShowPreUser_form.fxml"));

        this.idPaneUserLoader.getChildren().clear();
        this.idPaneUserLoader.getChildren().add(pane);


//        BoxBlur blur = new BoxBlur();
//        blur.setWidth(1222);
//        blur.setHeight(761);
//        paneBlur.setEffect(blur);

        //AdminDashFormController.blurred();



    }
}

/*
 stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
public void handle(WindowEvent e) {
        obList.clear();
        getAll();
        setTotalCustomers();
        setTotalCustomersToday();
        barGrapch.getData().clear();
        setBarGraphValues();
        }
        });
        stage.show();
        }*/
