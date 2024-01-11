package lk.ijse.cric.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.cric.bo.BOFactory;
import lk.ijse.cric.bo.custom.MatchesBo;
import lk.ijse.cric.dto.MatchesDto;
import lk.ijse.cric.dto.playerDto;
import lk.ijse.cric.dto.tm.MatchesTm;
import lk.ijse.cric.dto.tm.PlayerTm;
//import lk.ijse.cric.model.MatchesModel;
//import lk.ijse.cric.model.PlayerModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class MatchesFormController {

    public TextField txtGround;
    public Label lblNewMatchId;
    public TextField txtOppoTeam;
    public Label lblDonationId;
    public DatePicker idMatchDatePicker;
    public Label lblFillAllError;
    public TextField txtEstCost;
    public TextField txtBalance;

    public TableColumn colMatchId;

    public TableColumn colGround;
    public TableColumn colOppoTeam;
    public TableColumn colEstCost;
    public TableColumn colBalance;
    public TableColumn colDate;
    public TableColumn colStatus;
    public Label lblTotalWin;
    public Label lblTotalLost;
    public Label lblNextMatch;
    public TableView tblMatch;
    public ComboBox comboStatus;
    public JFXButton idBtnUpdate;
    public JFXButton idBtnDelete;

    MatchesBo matchesBo = (MatchesBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.MATCH);

    public void initialize() throws SQLException, ClassNotFoundException {
        nextMatchId();
        comboFixer();
        setCellValueFactory();
        loadAllMatches();
        tableListener();
        getTotalAndDate();

        idBtnUpdate.setDisable(true);
        idBtnDelete.setDisable(true);


    }
    public void comboFixer(){
        ObservableList<String> list = FXCollections.observableArrayList("WIN","LOST","DRAW","SUSPENDED","Pending..");
        comboStatus.setItems(list);

        //comboStatus.setItems(list);
    }

    private void setCellValueFactory() {
        colMatchId.setCellValueFactory(new PropertyValueFactory<>("MId"));
        colGround.setCellValueFactory(new PropertyValueFactory<>("ground"));
        colOppoTeam.setCellValueFactory(new PropertyValueFactory<>("opposing_team"));
        colEstCost.setCellValueFactory(new PropertyValueFactory<>("est_cost"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("Status"));

    }

    private void loadAllMatches() {
//        var model = new ItemModel();
        ObservableList<MatchesTm> obList = FXCollections.observableArrayList();
        try {
            //List<MatchesDto> dtoList = MatchesModel.loadAllmatches();
            List<MatchesDto> dtoList = matchesBo.getAllMatches();

            for ( MatchesDto dto: dtoList) {
                obList.add(new MatchesTm(
                        dto.getMId(),
                        dto.getGround(),
                        dto.getOpposing_team(),
                        dto.getEst_cost(),
                        dto.getBalance(),
                        dto.getDate(),
                        dto.getStatus()
                ));
                tblMatch.setItems(obList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    private void nextMatchId() throws SQLException, ClassNotFoundException {
        //lblNewMatchId.setText(MatchesModel.generateNextMatchId());
        lblNewMatchId.setText(matchesBo.generateNextMatchId());
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String newMId = lblNewMatchId.getText();
        String ground = txtGround.getText();
        String oppoTeam = txtOppoTeam.getText();
        double estCost = Double.parseDouble(txtEstCost.getText());
        //double balance  = Double.parseDouble(txtBalance.getText());
        String status = (String) comboStatus.getValue();
        String date = String.valueOf(idMatchDatePicker.getValue());

        if(matchesBo.checkId(newMId)){
            new Alert(Alert.AlertType.ERROR,"All ready exist MatchID").show();

        }else {
            if( ground.isEmpty() | oppoTeam.isEmpty() | estCost==0.0 | date.isEmpty()){
                new Alert(Alert.AlertType.ERROR,"Please Fill All Fields").show();

            }else{

                boolean isValid = validations();

                if (isValid) {
                    //boolean isSave =  MatchesModel.saveNewMatch(newMId,"U001",ground,oppoTeam,estCost,status,date);
                    boolean isSave =  matchesBo.saveNewMatch(new MatchesDto(newMId,"U001",ground,oppoTeam,estCost,status,date));
                    if (isSave) {
                        new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
                        nextMatchId();

                        comboFixer();
                        nextMatchId();
                        clearAddbtn();
                        loadAllMatches();

                    }
                }
            }
        }
    }

    public boolean validations(){
        String ground = txtGround.getText();
        String oppoTeam = txtOppoTeam.getText();
        String cost = txtEstCost.getText();

        boolean matchesGround= Pattern.matches("[A-Za-z0-9]{3,}", ground);
        boolean matchesOppoteam = Pattern.matches("[A-Za-z0-9]{3,}", oppoTeam);
        boolean matchesCost = Pattern.matches("[0-9.]{3,}",cost);

        if (!matchesGround) {
            new Alert(Alert.AlertType.ERROR, "Invalid ground name!").show();
            txtGround.setText("");

            return false;
        }
        if (!matchesOppoteam) {
            new Alert(Alert.AlertType.ERROR, "Invalid team name!").show();
            txtOppoTeam.setText("");


            return false;
        }

        if (!matchesCost) {
            new Alert(Alert.AlertType.ERROR, "Invalid cost value!").show();
            txtEstCost.setText("");


            return false;
        }
        return true;

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String newMId = lblNewMatchId.getText();

        String ground = txtGround.getText();
        String oppoTeam = txtOppoTeam.getText();
        double estCost = Double.parseDouble(txtEstCost.getText());
        String status = (String) comboStatus.getValue();

        String date = String.valueOf(idMatchDatePicker.getValue());

        if(newMId.isEmpty() | ground.isEmpty() | oppoTeam.isEmpty() | estCost==0.0 | date.isEmpty()){
            new Alert(Alert.AlertType.ERROR,"Please Fill All Fields").show();

        }else{

            //boolean isUpdate = MatchesModel.UpdateMatches(newMId, ground, oppoTeam, estCost, status, date);
            boolean isUpdate = matchesBo.UpdateMatche(new MatchesDto(newMId, ground, oppoTeam, estCost, date, status));

            if (isUpdate) {
                new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
                clearAddbtn();
                nextMatchId();
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String newId = lblNewMatchId.getText();

        //boolean isDelete = MatchesModel.deleteMactch(newId);
        boolean isDelete = matchesBo.deleteMactch(newId);

        if (isDelete) {
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted\n Please press Match tab to refresh the table").show();
            clearAddbtn();
            nextMatchId();
            //loadAllMatches();
        }else{
            new Alert(Alert.AlertType.ERROR,"Delete Unsuccessfully").show();
        }


    }


    public void clearAddbtn(){
        txtGround.setText("");
        txtOppoTeam.setText("");
        txtEstCost.setText("");
        comboStatus.getItems().clear();
        idMatchDatePicker.getEditor();


    }

    public  void tableListener() {
        tblMatch.getSelectionModel().selectedItemProperty().addListener((observable, oldValued, newValue) -> {
//            System.out.println(newValue);
            idBtnUpdate.setDisable(false);
            idBtnDelete.setDisable(false);
            setData((MatchesTm) newValue);
        });
    }
    private void setData(MatchesTm row) {
        lblNewMatchId.setText(row.getMId());
        txtGround.setText(row.getGround());
        txtOppoTeam.setText(row.getOpposing_team());
        comboStatus.getItems().add(row.getStatus());
        txtEstCost.setText(String.valueOf(row.getEst_cost()));
        idMatchDatePicker.setValue(LocalDate.parse(row.getDate()));
    }

    public void getTotalAndDate() throws SQLException, ClassNotFoundException {
        /*lblTotalWin.setText(MatchesModel.getTotal("WIN"));
        lblTotalLost.setText(MatchesModel.getTotal("LOST"));
        lblNextMatch.setText(MatchesModel.nextMatchDate());*/

        lblTotalWin.setText(matchesBo.getTotal("WIN"));
        lblTotalLost.setText(matchesBo.getTotal("LOST"));
        lblNextMatch.setText(matchesBo.nextMatchDate());
    }
}
