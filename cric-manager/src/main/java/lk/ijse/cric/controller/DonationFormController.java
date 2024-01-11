package lk.ijse.cric.controller;

import com.jfoenix.controls.JFXButton;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import lk.ijse.cric.bo.BOFactory;
import lk.ijse.cric.bo.custom.DonationBo;
import lk.ijse.cric.db.DbConnection;
import lk.ijse.cric.dto.DonationDto;
//import lk.ijse.cric.dto.ShowPreUserDto;
import lk.ijse.cric.dto.tm.DonationTm;
import lk.ijse.cric.dto.tm.ShowPreUserTm;
//import lk.ijse.cric.model.CoachModel;
//import lk.ijse.cric.model.DonationModel;
//import lk.ijse.cric.model.UserModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class DonationFormController {

    public TableView tblDonation;
    public TableColumn colDonId;
    public TableColumn colDonType;
    public TableColumn colDonValue;
    public TableColumn colDonDesc;
    public TableColumn colDonDate;
    public Label lblTotalDonation;
    public Label lblTotalEquDonation;
    public Label lblTotalCashDonation;
    public Label lblLastDonDate;
    public PieChart pieChart;
    public JFXButton idBtnPrint;

    DonationBo donationBo = (DonationBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.DONATION);
    public void initialize() throws SQLException, ClassNotFoundException {
        setCellValueFactory();
        loadAllDonations();

        totalize();

        Tooltip tip = new Tooltip("Get Donation Table Document");
        Tooltip.install(idBtnPrint,tip);
    }


        private void setCellValueFactory(){
        colDonId.setCellValueFactory(new PropertyValueFactory<>("DId"));
        colDonType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDonValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colDonDesc.setCellValueFactory(new PropertyValueFactory<>("desc"));
        colDonDate.setCellValueFactory(new PropertyValueFactory<>("Date"));

    }

    private void loadAllDonations(){
        //var model = new DonationModel();

        ObservableList<DonationTm> obList = FXCollections.observableArrayList();

        try {
            //List<DonationDto> dtoList =model.getAllDonations();
            List<DonationDto> dtoList = donationBo.getAllDonation();

            for (DonationDto dto : dtoList) {
                obList.add(new DonationTm(
                        dto.getDId(),
                        dto.getType(),
                        dto.getValue(),
                        dto.getDesc(),
                        dto.getDate()
                        )
                );
            }
            tblDonation.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void totalize() throws SQLException, ClassNotFoundException {
        //String value =  DonationModel.getTotalDonations();

        String value =  donationBo.getAmountDonations();
        lblTotalDonation.setText(value);

        //String equ = DonationModel.getTotalEquDonation();
        String equ = donationBo.getTotalDonation("Equipment");
        lblTotalEquDonation.setText(equ);

        //String cash = DonationModel.getTotalCashDonation();
        String cash = donationBo.getTotalDonation("Cash donation");
        lblTotalCashDonation.setText(cash);

        //String lastDay = DonationModel.getLastDonationDate();
        String lastDay = donationBo.getLastDonationDate();
        lblLastDonDate.setText(lastDay);

        double equValue = Double.parseDouble(equ);
        double cashValue = Double.parseDouble(cash);

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("Equipment",equValue),
                        new PieChart.Data("Cash", cashValue));


//        pieChartData.forEach(data ->
//                data.nameProperty().bind(
//                        Bindings.concat(
//                                data.getName(), " : ", data.pieValueProperty()
//                        )
//                )
//        );

        pieChart.getData().addAll(pieChartData);
    }

    public void btnPrintOnAction(ActionEvent actionEvent) {
        InputStream resourceAsStream = getClass().getResourceAsStream("/jasper/DonationTbl.jrxml");
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
