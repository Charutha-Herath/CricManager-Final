package lk.ijse.cric.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.TextAlignment;
import lk.ijse.cric.bo.BOFactory;
import lk.ijse.cric.bo.custom.CostBo;
import lk.ijse.cric.bo.custom.MatchesBo;
import lk.ijse.cric.dao.custom.CostDao;
import lk.ijse.cric.db.DbConnection;
import lk.ijse.cric.dto.CostDto;
import lk.ijse.cric.dto.MatchesDto;
import lk.ijse.cric.dto.tm.CostTm;
import lk.ijse.cric.dto.tm.MatchesTm;
//import lk.ijse.cric.model.CostModel;
//import lk.ijse.cric.model.DonationModel;
//import lk.ijse.cric.model.MatchesModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class CostFormController {

    public TextField txtEquipmentFee;
    public Label lblNewCostId;
    public TextField txtGroundFee;
    public Label lblDonationId;
    public Label lblFillAllError;
    public TextField txtUmpireFee;
    public ComboBox comboMatchId;
    public TextField txtMealCost;
    public TableView tblCost;
    public TableColumn colCostId;
    public TableColumn colMatchId;
    public TableColumn colGroundFee;
    public TableColumn colUmpireFee;
    public TableColumn colEquipmentFee;
    public TableColumn colMealCost;
    public TableColumn colTotal;
    public Label lblTotalGroundFee;
    public Label lblTotalUmpireFee;
    public Label lblTotalEquipmentFee;
    public Label lblTotalMealCost;
    public Label lblTotalAmount;
    public PieChart pieChartCost;
    public JFXButton idBtnCostPrint;
    public Label lblOnlyOneTimeId;
    public JFXButton btnAddId;

    CostBo costBo = (CostBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.COST);

    MatchesBo matchesBo = (MatchesBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.MATCH);

    public void initialize() throws SQLException, ClassNotFoundException {
        setComboBoxes();
        getNewCostId();
        setCellValueFactory();
        loadAllCost();
        totalize();

        Tooltip tip = new Tooltip("Get Cost Table Document");
        Tooltip.install(idBtnCostPrint,tip);

        lblOnlyOneTimeId.setText("*You can enter details only one time for one match*");

        btnAddId.setDisable(true);
    }

    private void setCellValueFactory() {
        colCostId.setCellValueFactory(new PropertyValueFactory<>("CostId"));
        colMatchId.setCellValueFactory(new PropertyValueFactory<>("CostMId"));
        colGroundFee.setCellValueFactory(new PropertyValueFactory<>("ground_fee"));
        colUmpireFee.setCellValueFactory(new PropertyValueFactory<>("umpire_fee"));
        colEquipmentFee.setCellValueFactory(new PropertyValueFactory<>("equipment_fee"));
        colMealCost.setCellValueFactory(new PropertyValueFactory<>("meal_and_other"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

    }

    private void loadAllCost() {
//        var model = new ItemModel();
        ObservableList<CostTm> obList = FXCollections.observableArrayList();
        try {
            //List<CostDto> dtoList = CostModel.loadAllCost();
            List<CostDto> dtoList = costBo.getAllCost();

            for ( CostDto dto: dtoList) {
                obList.add(new CostTm(
                        dto.getCostId(),
                        dto.getCostMId(),
                        dto.getGround_fee(),
                        dto.getUmpire_fee() ,
                        dto.getEquipment_fee(),
                        dto.getMeal_and_other(),
                        dto.getTotal()
                ));
                tblCost.setItems(obList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String costId = lblNewCostId.getText();
        String matchId = (String) comboMatchId.getValue();
        double ground = Double.parseDouble(txtGroundFee.getText());
        double umpire = Double.parseDouble(txtUmpireFee.getText());
        double equipment = Double.parseDouble(txtEquipmentFee.getText());
        double meal = Double.parseDouble(txtMealCost.getText());

        if (matchId.isEmpty() | ground==0.0 | umpire==0.0 | equipment==0.0 | meal==0.0){

            new Alert(Alert.AlertType.ERROR,"Fill all fields").show();

        }else {
                System.out.println("matchId : "+ matchId);
                //boolean isSave =  CostModel.saveNewMatchCost(costId,matchId,ground,umpire,equipment,meal);
                boolean isSave =  costBo.saveNewMatchAndCost(new CostDto(costId,ground,umpire,equipment,meal,matchId));

            System.out.println("Contro : "+isSave);
                if (isSave) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
                    getNewCostId();
                    clearAdd();
                }else {
                    new Alert(Alert.AlertType.ERROR,"Save Unsuccessfully..!").show();
                }

        }

    }

    public void ComboCheckIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String newId = (String) comboMatchId.getValue();
        //boolean isNow = CostModel.checkMatchId(newId);
        boolean isNow = costBo.checkMatchId(newId);

        if (isNow) {
            new Alert(Alert.AlertType.ERROR,"All ready exist this match ID!\nYou can enter details only one time for one match").show();
            comboMatchId.setStyle("-fx-border-color: red; -fx-border-radius: 10px;-fx-background-radius: 10px");

        }else{
            comboMatchId.setStyle("-fx-border-color: white; -fx-border-radius: 10px;-fx-background-radius: 10px");
            btnAddId.setDisable(false);
        }
    }
    public void totalize() throws SQLException, ClassNotFoundException {
        //String groundFee =  CostModel.getTotal("ground_fee");
        String groundFee =  costBo.getTotals("ground_fee");
        lblTotalGroundFee.setText(groundFee);

        String umpireFee = costBo.getTotals("umpire_fee");
        lblTotalUmpireFee.setText(umpireFee);

        String equFee =costBo.getTotals("equipment_fee");
        lblTotalEquipmentFee.setText(equFee);

        String meal = costBo.getTotals("meal_and_other");
        lblTotalMealCost.setTextAlignment(TextAlignment.CENTER);
        lblTotalMealCost.setText(meal);

        String total = costBo.getTotals("total");
        lblTotalAmount.setText(total);

        double GroundValue = Double.parseDouble(groundFee);
        double UmpireValue = Double.parseDouble(umpireFee);
        double EquValue = Double.parseDouble(equFee);
        double MealValue = Double.parseDouble(meal);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Ground fee",GroundValue),
                        new PieChart.Data("Umpire fee", UmpireValue),
                        new PieChart.Data("Equipment fee", EquValue),
                        new PieChart.Data("Meal cost", MealValue)
                );


        pieChartData.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(), " : ", data.pieValueProperty()
                        )
                )
        );

        pieChartCost.getData().addAll(pieChartData);
    }

    private void getNewCostId() throws SQLException, ClassNotFoundException {
        //lblNewCostId.setText(CostModel.generateNextCostId());
        lblNewCostId.setText(costBo.generateNextCostId());

    }

    private void clearAdd() {
        comboMatchId.setValue("");
        txtGroundFee.setText("");
        txtUmpireFee.setText("");
        txtEquipmentFee.setText("");
        txtMealCost.setText("");
    }


    private void setComboBoxes() throws SQLException, ClassNotFoundException {
        //var model = new MatchesModel();
        //List<String> list = model.getmatchIds();
        List<String> list = matchesBo.getmatchIds();
        comboMatchId.getItems().addAll(FXCollections.observableArrayList(list));
    }

    public void btnPrintCostOnAction(ActionEvent actionEvent) {
        InputStream resourceAsStream = getClass().getResourceAsStream("/jasper/CostTbl.jrxml");
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


}
