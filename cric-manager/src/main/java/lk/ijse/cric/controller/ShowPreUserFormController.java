package lk.ijse.cric.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.cric.bo.BOFactory;
import lk.ijse.cric.bo.custom.UserBo;
//import lk.ijse.cric.dto.ShowPreUserDto;
import lk.ijse.cric.dto.UserDto;
import lk.ijse.cric.dto.tm.ShowPreUserTm;
import lk.ijse.cric.dto.tm.UserDashTm;
//import lk.ijse.cric.model.UserModel;

import java.sql.SQLException;
import java.util.List;

public class ShowPreUserFormController {

    @FXML
    private TableView<ShowPreUserTm>  tblPreUser;
    @FXML
    private TableColumn<?, ?> colId;
    @FXML
    private TableColumn<?, ?> colUName;
    @FXML
    private TableColumn<?, ?> colPwd;
    @FXML
    private TableColumn<?, ?> colSDate;

UserBo userBo = (UserBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.USER);

public void initialize(){

    setCellValueFactory();
    loadAllUsers();
}
private void setCellValueFactory(){
    colId.setCellValueFactory(new PropertyValueFactory<>("UId"));
    colUName.setCellValueFactory(new PropertyValueFactory<>("UName"));
    colPwd.setCellValueFactory(new PropertyValueFactory<>("Pwd"));
    colSDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));

}

private void loadAllUsers(){
    //var model = new UserModel();

    ObservableList<ShowPreUserTm> obList = FXCollections.observableArrayList();

    try {
        //List<ShowPreUserDto> dtoList =model.getAllUsers();
        List<UserDto> dtoList = userBo.getAllUsers();

        for (UserDto dto : dtoList) {
            obList.add(new ShowPreUserTm(dto.getUId(),
                    dto.getUName(),
                    dto.getPwd(),
                    dto.getStartDate())
            );
        }
        tblPreUser.setItems(obList);

    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
}






    /*private void loadAllUsers(){
        var model = new UserModel();

        ObservableList<UserDashTm> obList = FXCollections.observableArrayList();

        try {
            List<UserDto> dtoList = model.getAllUsers();

            for (UserDto dto: dtoList) {
                obList.add(new UserDashTm(dto.getUId(),
                                            dto.getUName(),
                                            dto.getPwd(),
                                            dto.getStartDate())

                );
            }
            tblPreUser.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }*/
//        public void start(Stage primaryStage) {
//            // Create a button to trigger the blur
//            Button blurButton = new Button("Blur Form");
//            blurButton.setOnAction(event -> blurForm(primaryStage));
//
//            StackPane root = new StackPane(blurButton);
//            Scene scene = new Scene(root, 400, 300);
//
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        }
//
//        private void blurForm(Stage primaryStage) {
//            primaryStage.initStyle(StageStyle.TRANSPARENT);
//
//            Region blurRegion = new Region();
//            blurRegion.setStyle("-fx-background-color: rgba(0, 0, 0, 0.85);");
//
//            Scene scene = new Scene(blurRegion, 400, 300);
//            scene.setFill(Color.TRANSPARENT);
//
//            primaryStage.setScene(scene);
//
//            BoxBlur blur = new BoxBlur();
//            blur.setWidth(10);
//            blur.setHeight(10);
//            blurRegion.setEffect(blur);
//
//            primaryStage.show();
//        }


}
