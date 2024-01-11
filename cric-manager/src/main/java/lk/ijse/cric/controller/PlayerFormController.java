package lk.ijse.cric.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lk.ijse.cric.bo.BOFactory;
import lk.ijse.cric.bo.custom.PlayerBo;
import lk.ijse.cric.dto.UmpireDto;
import lk.ijse.cric.dto.playerDto;
import lk.ijse.cric.dto.tm.PlayerTm;
import lk.ijse.cric.dto.tm.UmpireTm;
//import lk.ijse.cric.model.PlayerModel;
//import lk.ijse.cric.model.UmpireModel;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class PlayerFormController {

    public TextField txtAddPlayerName;
    public Label lblNewPlayerId;
    public Label lblAddUmpireFillAllError;
    public TextField txtAddPlayerAge;
    public ComboBox comboAddNewPlayerType;
    public TextField txtUpdatePlayerName;
    public Label lblUpdatePlayerId;
    public Label lblAddUmpireFillAllError1;
    public TextField txtUpdatePlayerAge;
    public ComboBox comboUpdatePlayerType;

    public Label lblTotalBowlers;
    public Label lblTotalBatsmen;
    public Label lblTotalAllrounders;
    public TableColumn colPlayerId;
    public TableColumn colPlayerName;
    public TableColumn colPlayerAge;
    public TableColumn colPlayerType;
    public TableView tblPlayer;
    public TableColumn colDelete;

    PlayerBo playerBo = (PlayerBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.PLAYER);

    /* private String username;

     public String getUsername() {
         return username;
     }

     public void setUsername(String username) {
         this.username = username;
     }

     public PlayerFormController(String username) {
         this.username = username;
     }*/
    public void initialize() throws SQLException, ClassNotFoundException {

        comboFix();
        nextPlayerId();
        setCellValueFactory();
        loadAllPlayers();
        tableListener();
        getTotBatsmen();
        getTotBowler();
        getTotAllRounders();

    }
    public void comboFix(){
        ObservableList<String> list = FXCollections.observableArrayList("Batsmen (Right Hand)","Batsmen (Left Hand)","Bowler (Fast)","Bowler(Spinner)","All-Rounder","Wicket Keeper");
        comboAddNewPlayerType.setItems(list);

        comboUpdatePlayerType.setItems(list);
    }
    private void setCellValueFactory() {
        colPlayerId.setCellValueFactory(new PropertyValueFactory<>("PId"));
        colPlayerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colPlayerAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        colPlayerType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }


    private void loadAllPlayers() {
//        var model = new ItemModel();
        ObservableList<PlayerTm> obList = FXCollections.observableArrayList();
        try {
            //List<playerDto> dtoList = PlayerModel.loadAllPlayers();
            List<playerDto> dtoList = playerBo.getAllPlayers();

            for (playerDto dto : dtoList) {

                    //Image img = new Image("");
                    //ImageView imv = new ImageView(img);
                    Button removeButton = new Button("Remove");
                    //removeButton.setGraphic();
                    removeButton.setStyle("-fx-background-color: #FFCDD2" );

                    obList.add(new PlayerTm(
                            dto.getPId(),
                            dto.getName(),
                            dto.getAge(),
                            dto.getType(),
                            removeButton
                    ));



               /* obList.add(new PlayerTm(
                        dto.getPId(),
                        dto.getName(),
                        dto.getAge(),
                        dto.getType(),
                        new Button("Remove")
                ));*/
                for(int i = 0; i < obList.size(); i++) {
                    Button btn = obList.get(i).getBtn();
                    btn.setCursor(Cursor.HAND);
                    int finalI = i;
                    obList.get(i).getBtn().setOnAction((ActionEvent actionEvent) -> {
                        boolean b = false;
                        try {
                            //b = PlayerModel.deletePlayer(obList.get(finalI).getPId());
                            b = playerBo.deletePlayer(obList.get(finalI).getPId());
                            if (b) {
                                new Alert(Alert.AlertType.CONFIRMATION, "Player Deleted").show();
                                //loadAllPlayers();
                            }
                        } catch (SQLException e) {

                            new Alert(Alert.AlertType.ERROR, "Delete Unsuccessfully");
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }

                    });
                }
                //System.out.println(obList);
                tblPlayer.setItems(obList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnPlayerAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String newId = lblNewPlayerId.getText();
        String name = txtAddPlayerName.getText();
        String ag = txtAddPlayerAge.getText();
        String value = (String) comboAddNewPlayerType.getValue();

        int age = Integer.parseInt(ag);

        if (name.isEmpty() | age==0){    // | value.isEmpty() Does not work properly.
            new Alert(Alert.AlertType.ERROR,"Please Fill All Fields").show();

        }else{

            boolean isValid = validations();

            if (isValid) {

                //boolean isSave =  PlayerModel.saveNewPlayer(newId,"U001",name,age,value);
                boolean isSave = playerBo.savePlayer(new playerDto(newId,"U001",name,age,value));
                if (isSave) {
                    //new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
                    comboFix();
                    loadAllPlayers();
                    nextPlayerId();
                    clearMethodAddform();
                    getTotBatsmen();
                    getTotBowler();
                    getTotAllRounders();


                }else {
                    clearMethodAddform();
                    new Alert(Alert.AlertType.ERROR,"Save Unsuccessfully").show();
                }
            }

        }

    }

    public boolean updateValidation(){

        String name = txtUpdatePlayerName.getText();
        String age = txtUpdatePlayerAge.getText();

        boolean matchesName= Pattern.matches("[A-Za-z]{3,}", name);
        boolean matchesAge = Pattern.matches("([5-9]|[1-3][0-9]|40)$", age);

        if (!matchesName) {
            new Alert(Alert.AlertType.ERROR, "Invalid name. Please you can't include Symbols and Numbers !").show();
            txtUpdatePlayerName.setText("");

            return false;
        }
        if (!matchesAge) {
            new Alert(Alert.AlertType.ERROR, "Invalid Age.").show();
            txtUpdatePlayerAge.setText("");


            return false;
        }
        return true;
    }

    public boolean validations(){

        String name = txtAddPlayerName.getText();
        String age = txtAddPlayerAge.getText();

        boolean matchesName= Pattern.matches("[A-Za-z]{3,}", name);
        boolean matchesAge = Pattern.matches("([5-9]|[1-3][0-9]|40)$", age);

        if (!matchesName) {
            new Alert(Alert.AlertType.ERROR, "Invalid name. Please you can't include Symbols and Numbers !").show();
            txtAddPlayerName.setText("");

            return false;
        }
        if (!matchesAge) {
            new Alert(Alert.AlertType.ERROR, "Invalid Age.").show();
            txtAddPlayerAge.setText("");


            return false;
        }
        return true;
    }


    public void clearMethodAddform() {

        txtAddPlayerName.setText("");
        txtAddPlayerAge.setText("");
        //comboAddNewPlayerType.getItems().clear();
    }

    public void clearMethodUpdateform(){

        lblUpdatePlayerId.setText("");
        txtUpdatePlayerName.setText("");
        txtUpdatePlayerAge.setText("");
        //comboUpdatePlayerType.getItems().clear();
    }

    public void btnPlayerUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String Id = lblUpdatePlayerId.getText();
        String name = txtUpdatePlayerName.getText();
        String ag = txtUpdatePlayerAge.getText();
        String value = (String) comboUpdatePlayerType.getValue();

        //int age = Integer.parseInt(ag);

        if (name.isEmpty() | ag.isEmpty() | value.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Fill All Fields").show();

        }else{
            int age = Integer.parseInt(ag);
            boolean isValid = updateValidation();

            if (isValid) {
                //boolean isSave =  PlayerModel.updatePlayer(name, String.valueOf(age),value,Id);
                boolean isSave =  playerBo.updatePlayer(new playerDto(Id,name,age,value));

                if (isSave){
                    //loadAllPlayers();
                    clearMethodUpdateform();
                    comboFix();
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();

                }else{
                    clearMethodUpdateform();
                    new Alert(Alert.AlertType.ERROR,"Couldn't Update Properly").show();
                }
            }

        }
    }

    public  void tableListener() {
        tblPlayer.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
            System.out.println("t1 : "+newValue);
            setData((PlayerTm) newValue);
        });
    }

    private void setData(PlayerTm row) {
        System.out.println("t2 : "+row.getPId()+" "+ row.getName()+" "+row.getAge()+" "+row.getType());
        lblUpdatePlayerId.setText(row.getPId());
        txtUpdatePlayerName.setText(row.getName());
        txtUpdatePlayerAge.setText(String.valueOf(row.getAge()));
        comboUpdatePlayerType.getItems().add(row.getType());
    }


    public void nextPlayerId() throws SQLException, ClassNotFoundException {
        //lblNewPlayerId.setText(PlayerModel.generateNextPlayerId());
        lblNewPlayerId.setText(playerBo.generateNextPlayerId());

    }
    public void getTotBatsmen() throws SQLException, ClassNotFoundException {
        //lblTotalBatsmen.setText(PlayerModel.getTotalBatsmen());
        lblTotalBatsmen.setText(playerBo.getTotalPlayers("Bat"));

    }
    public void getTotBowler() throws SQLException, ClassNotFoundException {
        //lblTotalBowlers.setText(PlayerModel.getTotalBowlers());
        lblTotalBowlers.setText(playerBo.getTotalPlayers("Bow"));
    }

    public void getTotAllRounders() throws SQLException, ClassNotFoundException {
        //lblTotalAllrounders.setText(PlayerModel.getTotalAllRounders());
        lblTotalAllrounders.setText(playerBo.getTotalPlayers("All"));
    }



}