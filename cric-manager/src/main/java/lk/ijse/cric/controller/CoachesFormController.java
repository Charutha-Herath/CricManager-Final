package lk.ijse.cric.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.cric.bo.BOFactory;
import lk.ijse.cric.bo.custom.CoachBo;
import lk.ijse.cric.bo.custom.impl.CoachBoImpl;
import lk.ijse.cric.db.DbConnection;
import lk.ijse.cric.dto.CoachDto;
//import lk.ijse.cric.dto.ShowPreUserDto;
import lk.ijse.cric.dto.tm.CoachTm;
import lk.ijse.cric.dto.tm.ShowPreUserTm;
import lk.ijse.cric.entity.Coach;
//import lk.ijse.cric.model.CoachModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.security.auth.Refreshable;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class CoachesFormController {

    public TextField txtCoachName;
    public TextField txtCoachCategory;
    public Label lblNewCoachId;
    public TextField txtUpdateCoachId;

    public String AdminName ;


    public TextField txtUpdateCategory;
    public TextField txtUpdateName;
    public TableView tblCoach;
    public TableColumn colId;
    public TableColumn colAdmin;
    public TableColumn colName;
    public TableColumn colCategory;
    public Label lblNoyFill;
    public Label lblTotCoach;
    public PieChart pieChart;
    public ComboBox comboSelected;
    public ComboBox comboSelectedUpdate;
    public Label lblCoachIdError;
    public Label lblCoachFillAllError;
    public JFXButton idBtnCoachPrint;
    public Label lblTotCoach1;
    public JFXButton idBtnSearch;
    public Label idLblNoSearchResult;
    public Label idResultBellowTable;
    public TextField txtNameSearch;
    public JFXButton idBtnFilter;
    public Label idLblFilter;
    public ComboBox comboFilterCategory;

    private String currentAdmin;

    public String comboValues;
    private String comboValuesUpdt;

    //CoachBo coachBo = new CoachBoImpl();

    CoachBo coachBo = (CoachBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.COACH);


    public CoachesFormController() {

    }

    public CoachesFormController(String username) {
        this.AdminName = username;
    }

    public void initialize() throws SQLException, ClassNotFoundException {

        ObservableList<String> list = FXCollections.observableArrayList("Batting","Bowling","Fielding","Medic");
        comboSelected.setItems(list);

        ObservableList<String> listUp = FXCollections.observableArrayList("Batting","Bowling","Fielding","Medic");
        comboSelectedUpdate.setItems(listUp);

        ObservableList<String> listFilter = FXCollections.observableArrayList("Batting","Bowling","Fielding","Medic");
        comboFilterCategory.setItems(listFilter);

        Tooltip tip = new Tooltip("Get Coach Table Document");
        Tooltip.install(idBtnCoachPrint,tip);

        Tooltip tip1 = new Tooltip("Search");
        Tooltip.install(idBtnSearch,tip1);

        getNewCoachId();
        setCellValueFactory();
        loadAllUsers();
        getTotalCoach();

        pieChartDetails();
        idBtnFilter.setVisible(false);

    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory<>("CId"));
        colAdmin.setCellValueFactory(new PropertyValueFactory<>("CUId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("type"));

    }

    private void loadAllUsers(){
       // var model = new CoachModel();

        ObservableList<CoachTm> obList = FXCollections.observableArrayList();

        try {
            List<CoachDto> dtoList = coachBo.getAllCoaches();
            //List<CoachDto> dtoList =model.getAllUsers();
            for (CoachDto dto :dtoList) {
                obList.add(
                        new CoachTm(
                                dto.getCId(),
                                dto.getCUId(),
                                dto.getName(),
                                dto.getType()
                        )

                );
            }
            tblCoach.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String newId = lblNewCoachId.getText();
        String name = txtCoachName.getText();
        String admin = currentAdmin;
        String category = String.valueOf(comboSelected.getValue());
        //System.out.println("cateee "+category);


        if(name.isEmpty() | category.isEmpty()){
            lblCoachFillAllError.setText("Fill all fields");
        }else {
            boolean matches =Pattern.matches("[A-Za-z]{3,}", name);

            if (!matches) {
                new Alert(Alert.AlertType.ERROR, "Invalid Coach name. Please you can't include Symbols and Numbers !").show();
                txtCoachName.setText("");

            }else{
                boolean isSave = coachBo.saveCoach(new CoachDto(newId,admin,name,category));
                //boolean isSave = CoachModel.saveNewCoach(newId,admin,name,category);

                if(isSave){
                    txtCoachName.setText("");
                    comboSelected.getItems().clear();

                    String[] splint = newId.split("C0");

                    int id = Integer.parseInt(splint[1]);
                    id++;
                    if (id > 9){
                        lblNewCoachId.setText("C0"+id);
                    }
                    lblNewCoachId.setText("C00"+id);

                    getNewCoachId();
                    getTotalCoach();
                    //initialize();
                }

            }


        }

    }

    public void getNewCoachId() throws SQLException, ClassNotFoundException {

        String newId = coachBo.generateNewCoachId();
        //String newId = CoachModel.generateNewCoachId();

        lblNewCoachId.setText(newId);


    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String updateId =  txtUpdateCoachId.getText();
        String updateName = txtUpdateName.getText();
        String updateCategory = String.valueOf(comboSelectedUpdate.getValue());

        //lblCoachIdError.setText("");


        if (updateId.isEmpty() | updateName.isEmpty()){            //  | updateCategory.isEmpty()  Does not work properly
            lblNoyFill.setText("Fill all fields");
        }else {
            boolean matchesId = Pattern.matches("[C][0-9]{3,}",updateId);
            boolean matchesName =Pattern.matches("[A-Za-z]{3,}", updateName);

            if (!matchesName | !matchesId) {
                new Alert(Alert.AlertType.ERROR, "Invalid Coach name or ID.").show();
                txtCoachName.setText("");

            }else {

                lblNoyFill.setText("");
                if(coachBo.findCoach(updateId)){

                    boolean b = coachBo.updateCoach(new CoachDto(updateId,updateName,updateCategory));
                    //CoachModel.updateCoach(updateId,updateName,updateCategory);
                    if (b) {
                        new Alert(Alert.AlertType.CONFIRMATION,"Update Successfully..!").show();

                    }else {
                        new Alert(Alert.AlertType.ERROR,"Update Unsuccessfully..!").show();

                    }
                    txtUpdateCoachId.setText("");
                    txtUpdateName.setText("");
                    comboSelectedUpdate.getItems().clear();

                }else {
                    lblCoachIdError.setText("Invalid Coach ID");

                }

                txtUpdateCoachId.setText("");
                txtUpdateName.setText("");
                comboSelectedUpdate.getItems().clear();

                //initialize();
                //loadAllUsers();

                getNewCoachId();
                getTotalCoach();

            }





        }


    }

    public void setCoach(String adminId) {
        currentAdmin = adminId;

       // System.out.println("9999"+currentAdmin);
    }

    public  void  getTotalCoach() throws SQLException, ClassNotFoundException {

        //String tot = CoachModel.totalCoaches();
        String tot = coachBo.totalCoaches();

        lblTotCoach.setText(tot);
    }
    public void pieChartDetails() throws SQLException, ClassNotFoundException {

        int bowlingCount = coachBo.getCoachCount("Bowling");
        int battingCount = coachBo.getCoachCount("Batting");
        int fieldingCount = coachBo.getCoachCount("Fielding");
        int medicCount = coachBo.getCoachCount("Medic");

        /*int bowlingCount = CoachModel.getCoachCount("Bowling");
        int battingCount = CoachModel.getCoachCount("Batting");
        int fieldingCount = CoachModel.getCoachCount("Fielding");
        int medicCount = CoachModel.getCoachCount("Medic");*/


        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Bowling",bowlingCount),
                        new PieChart.Data("Batting", battingCount),
                        new PieChart.Data("Fielding", fieldingCount),//fieldingCount
                        new PieChart.Data("Medic",medicCount )
                );//medicCount


        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " : ", data.pieValueProperty()
                        )
                )
        );

        pieChart.getData().addAll(pieChartData);

    }

    public void selecterOnAction(ActionEvent actionEvent) {
        comboValues = (String) comboSelected.getValue();
    }

    public void selecterUpdateOnAction(ActionEvent actionEvent) {
        comboValuesUpdt = (String) comboSelectedUpdate.getValue();
    }

    public void btnPrintCoachOnAction(ActionEvent actionEvent) {
        InputStream resourceAsStream = getClass().getResourceAsStream("/jasper/CoachTbl.jrxml");
        JasperDesign load = null;
        try {
            load = JRXmlLoader.load(resourceAsStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(load);
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    null,
                    DbConnection.getInstance().getConnection()
            );
            JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnSearchOnAction(ActionEvent actionEvent) {
        String name = txtNameSearch.getText();
        //System.out.println("1111 : "+ name.isEmpty());
        if(name.isEmpty()){
            idLblNoSearchResult.setText("Please fill required field");
            idResultBellowTable.setText("");
            loadAllUsers();


        }else{

            boolean matches = Pattern.matches("[A-Za-z]{3,}",name);
            if (!matches) {
                new Alert(Alert.AlertType.ERROR, "Invalid Name. Please you can't include Symbols and Numbers !").show();
                txtNameSearch.setText("");

            }else{
                //System.out.println("33333");
                //var model = new CoachModel();

                ObservableList<CoachTm> obList = FXCollections.observableArrayList();

                try {
                    //List<CoachDto> dtoList =model.getSearchCoaches(name);
                    List<CoachDto> dtoList =coachBo.getSearchCoaches(name);
                    for (CoachDto dto :dtoList) {
                        obList.add(
                                new CoachTm(
                                        dto.getCId(),
                                        dto.getCUId(),
                                        dto.getName(),
                                        dto.getType()
                                )

                        );
                    }

                    if(obList.isEmpty()){
                        idLblNoSearchResult.setText("No Search Result..!");
                        idResultBellowTable.setText("");
                        txtNameSearch.setText("");
                        loadAllUsers();
                    }else{
                        idResultBellowTable.setText("Results are shown bellow..!");
                        idLblNoSearchResult.setText("");
                        txtNameSearch.setText("");
                        tblCoach.setItems(obList);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }

            }
        }
    }

    public void btnFilterOnAction(ActionEvent actionEvent) {

    }

    public void comboFilterOnAction(ActionEvent actionEvent) {
        String value = (String) comboFilterCategory.getValue();

        if(value.isEmpty()){
            idLblFilter.setText("Please select category");

            loadAllUsers();


        }else{
            //var model = new CoachModel();

            ObservableList<CoachTm> obList = FXCollections.observableArrayList();

            try {

                //List<CoachDto> dtoList =model.getFilterCategory(value);
                List<CoachDto> dtoList = coachBo.getFilterCategory(value);
                for (CoachDto dto :dtoList) {
                    obList.add(
                            new CoachTm(
                                    dto.getCId(),
                                    dto.getCUId(),
                                    dto.getName(),
                                    dto.getType()
                            )

                    );
                }

                if(obList.isEmpty()){
                    new Alert(Alert.AlertType.ERROR,"No "+value+" coaches yet");
                    idLblFilter.setText("");
                    loadAllUsers();
                }else{
                    idLblFilter.setText("");
                    tblCoach.setItems(obList);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }


        }
    }
}
