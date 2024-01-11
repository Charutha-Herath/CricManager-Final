package lk.ijse.cric.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cric.bo.BOFactory;
import lk.ijse.cric.bo.custom.UmpireBo;
import lk.ijse.cric.dto.UmpireDto;
import lk.ijse.cric.dto.tm.UmpireTm;
import lk.ijse.cric.entity.Umpire;
//import lk.ijse.cric.model.CoachModel;
//import lk.ijse.cric.model.UmpireModel;
//import lk.ijse.cric.model.UserModel;

import java.sql.Array;
import java.sql.SQLException;
import java.util.List;

public class UmpireFormController {

    public AnchorPane lblNewUmpireId;
    public TextField txtUmpireName;
    public Label lblNewCoachId;

    public TextField txtUpdateName;
    public Label lblUpdateNotFillError;
    public Label lblCoachIdError;
    public Label lblUpdateUmpireId;
    public Label lblAddUmpireFillAllError;
    public TableView<UmpireTm> tblUmpire;
    public TableColumn colUmpireId;
    public TableColumn colUserId;
    public TableColumn colUmpireName;
    public Label lblTotUmpires;
    public TableColumn colDelete;

    private String loginUsername;

    private String loginID;
    //private UmpireModel umpireModel = new UmpireModel();

    UmpireBo umpireBo = (UmpireBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.UMPIRE);
    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
        //System.out.println("Login user : "+loginUsername);
    }
    public void initialize() throws SQLException, ClassNotFoundException {

        //System.out.println("Login user : "+loginUsername);

        getNewCouchId();
        setCellValueFactory();
        loadAllUmpires();
        getTotalUmpires();
        tableListener();
        getUserId();
    }

    private void setCellValueFactory() {
        colUmpireId.setCellValueFactory(new PropertyValueFactory<>("UmpId"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("UmpUId"));
        colUmpireName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    public void getUserId() throws SQLException {
        //loginID = UserModel.getUserId(loginUsername);

        //System.out.println("test : "+UserModel.getUserId(loginUsername));

    }

    private void loadAllUmpires() {
//        var model = new ItemModel();
        ObservableList<UmpireTm> obList = FXCollections.observableArrayList();
        try {
            //List<UmpireDto> dtoList = umpireModel.loadAllItems();
            List<UmpireDto> dtoList = umpireBo.getAllItems();



            for (UmpireDto dto : dtoList) {
                Button removeButton = new Button("Delete");
                removeButton.setStyle("-fx-background-color: #FFCDD2" );
                obList.add(new UmpireTm(
                        dto.getUmpId(),
                        dto.getUmpUId(),
                        dto.getName(),
                        removeButton//new Button("Delete")
                ));
                for(int i = 0; i < obList.size(); i++) {
                    Button btn = obList.get(i).getBtn();
                    btn.setCursor(Cursor.HAND);
                    int finalI = i;
                    obList.get(i).getBtn().setOnAction((ActionEvent actionEvent) ->{
                        boolean b = false;
                        try {
                            //b = umpireModel.deleteUmpire(obList.get(finalI).getUmpId());
                            b = umpireBo.deleteUmpire(obList.get(finalI).getUmpId());
                            if (b) {
                                new Alert(Alert.AlertType.CONFIRMATION,"Umpire Deleted").show();
                                tblUmpire.refresh();
                            }
                        } catch (SQLException e) {

                            new Alert(Alert.AlertType.ERROR,"Save Unsuccessfully");
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            }
            tblUmpire.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getTotalUmpires() throws SQLException, ClassNotFoundException {
        //lblTotUmpires.setText(UmpireModel.totalUmpires());
        lblTotUmpires.setText(umpireBo.totalUmpires());
    }

    public void btnUmpireAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String newId = lblNewCoachId.getText();
        String userId = "U007";
        String name = txtUmpireName.getText();


        if (newId.isEmpty() | name.isEmpty()){
             new Alert(Alert.AlertType.ERROR,"Please fill name").show();
             lblAddUmpireFillAllError.setText("Please fill name");
        }else {
            txtUmpireName.setText("");
            lblAddUmpireFillAllError.setText("");
            //boolean isSave = umpireModel.saveNewCoach(newId,name);
            boolean isSave = umpireBo.saveNewCoach(new UmpireDto(newId,userId,name));
            if (isSave){


                new Alert(Alert.AlertType.CONFIRMATION,"Add Successfully").show();
                getNewCouchId();
                //initialize();
                //loadAllUmpires();

            }else {
                new Alert(Alert.AlertType.ERROR,"Save Unsuccesful!").show();
            }

        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String  idd = lblUpdateUmpireId.getText();
        String name = txtUpdateName.getText();

        if (idd.isEmpty() | name.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please fill name").show();
            lblUpdateNotFillError.setText("Please fill name");
        }else{
            //boolean isUpdate = UmpireModel.updateUmpire(idd,name);
            boolean isUpdate = umpireBo.updateUmpire(new UmpireDto(idd,name));

            if (isUpdate){
                new Alert(Alert.AlertType.CONFIRMATION,"Update Successfully");

                lblUpdateUmpireId.setText("");
                txtUpdateName.setText("");
                //initialize();
            }else {
                new Alert(Alert.AlertType.ERROR,"Save Unsuccessfully");
            }
            
        }
    }

    public  void tableListener() {
        tblUmpire.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
//            System.out.println(newValue);
            setData(newValue);
        });
    }

    private void setData(UmpireTm row) {
        lblUpdateUmpireId.setText(row.getUmpId());
        txtUpdateName.setText(row.getName());
    }

    public void getNewCouchId() throws SQLException, ClassNotFoundException {
        //String newId = umpireModel.generateNewCoachId();
        String newId = umpireBo.generateNewCoachId();

        lblNewCoachId.setText(newId);
    }
}
