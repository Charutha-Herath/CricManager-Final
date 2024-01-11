package lk.ijse.cric.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.ijse.cric.bo.BOFactory;
import lk.ijse.cric.bo.custom.DonationBo;
import lk.ijse.cric.bo.custom.SponsorBo;
import lk.ijse.cric.bo.custom.impl.SponsorBoImpl;
import lk.ijse.cric.db.DbConnection;
import lk.ijse.cric.dto.CoachDto;
//import lk.ijse.cric.dto.ShowPreUserDto;
import lk.ijse.cric.dto.SponsorDto;
import lk.ijse.cric.dto.tm.CoachTm;
import lk.ijse.cric.dto.tm.ShowPreUserTm;
import lk.ijse.cric.dto.tm.SponsorTm;
//import lk.ijse.cric.model.CoachModel;
//import lk.ijse.cric.model.DonationModel;
//import lk.ijse.cric.model.SponsorModel;
//import lk.ijse.cric.model.UserModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.eclipse.jdt.internal.compiler.env.IBinaryField;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class SponsorFormController {


    public TextField txtSponsorName;
    public TextField txtSponsorCompany;
    public Label lblSponsorId ;
    public TextField txtSponsorValue;
    public Label lblDonationId;
    public TextArea txrAreaDonationDesc;
    public ComboBox selectCombo;

    public String comboValue;

    public String AdminUName;

    public String AdminPwd;

    public String currentUserId;

    public String currentAdmin;
    public TableView tblSponsor;
    public TableColumn colSpoId;
    public TableColumn colAdminId;
    public TableColumn colSpoName;
    public TableColumn colSpoCompany;
    public TableColumn colSpoValue;
    public Label lblTotalSponsors;
    public Label lblBestSpoName;
    public Label lblBestSpoCompany;
    public Label lblBestSpoAmount;
    public DatePicker idDatePicker;
    public TableColumn colSpoDate;
    public Label lblFillAllError;
    public JFXButton idBtnSponsorPrint;
    public TextField txtCompanyNameSearch;
    public JFXButton idBtnCompanySearch;
    public Label idLblNoSearchResult;
    public Label idResultBellowTable;

    //SponsorBo sponsorBo = new SponsorBoImpl();

    SponsorBo sponsorBo = (SponsorBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.SPONSOR);

    DonationBo donationBo = (DonationBo) BOFactory.getBoFactory().getBo(BOFactory.BOTypes.DONATION);

    public SponsorFormController() {

    }

    /*public SponsorFormController(String username, String password) {
        this.AdminUName = username;
        this.AdminPwd = password;

    }*/

    public String getAdminUName() {
        return AdminUName;
    }

    public void setAdminUName(String adminUName) {
        this.AdminUName = adminUName;
        //System.out.println("set"+adminUName);
    }

    public String getAdminPwd() {
        return AdminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.AdminPwd = adminPwd;
        //System.out.println("set"+adminPwd);
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        /*System.out.println(this.AdminUName);
        System.out.println(this.AdminPwd);
*/
        ObservableList<String> list = FXCollections.observableArrayList("Cash donation","Equipment");
        selectCombo.setItems(list);

        Tooltip tip = new Tooltip("Get Sponsor Table Document");
        Tooltip.install(idBtnSponsorPrint,tip);

       generateNextSponsorId();
       generateNextDonationId();
       findCurrentAdmin();
       setCellValueFactory();
       loadAllSponsor();
       totalSponsors();
       findBestSpoDetails();

    }
    private void setCellValueFactory(){
        colSpoId.setCellValueFactory(new PropertyValueFactory<>("SId"));
        colAdminId.setCellValueFactory(new PropertyValueFactory<>("UId"));
        colSpoName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colSpoCompany.setCellValueFactory(new PropertyValueFactory<>("company"));
        colSpoValue.setCellValueFactory(new PropertyValueFactory<>("value"));
        colSpoDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
    }

    private void loadAllSponsor(){
        //var model = new SponsorModel();

        ObservableList<SponsorTm> obList = FXCollections.observableArrayList();

        try {
            //List<SponsorDto> dtoList =model.getAllSponsors();
            //System.out.println("Befor List");
            List<SponsorDto> dtoList1 = sponsorBo.getAllSponsors();
            //System.out.println("After List");

            for (SponsorDto dto : dtoList1) {
                obList.add(new SponsorTm(
                        dto.getSId(),
                        dto.getUId(),
                        dto.getName(),
                        dto.getCompany(),
                        dto.getValue(),
                        dto.getDate()
                        )
                );
            }
            tblSponsor.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextDonationId() throws SQLException, ClassNotFoundException {
        //String id = DonationModel.nextDonationId();
        String id = donationBo.generateNextDonationId();

        lblDonationId.setText(id);
    }

    public void initializeWise(){
       // String nm = LoginFormController.getTxtUsername();
       // String pwd = LoginFormController.
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String spoId = lblSponsorId.getText();
        String spoName = txtSponsorName.getText();
        String spoCompany = txtSponsorCompany.getText();
        String sv = txtSponsorValue.getText();


        if (sv.isEmpty()){
            sv = "0.0";
        }

        String date = String.valueOf(idDatePicker.getValue());


        String donId = lblDonationId.getText();
        String type = String.valueOf(selectCombo.getValue());
        String desc = txrAreaDonationDesc.getText();

        if (spoName.isEmpty() | spoCompany.isEmpty() | sv.equals("0.0") | date.isEmpty() | type.isEmpty() ){
                lblFillAllError.setText("Fill all fields");
        }else {
            boolean isValid = validations();
            double spoValue = Double.parseDouble(sv);

            if (isValid){

                //boolean isSaveSponsor = SponsorModel.saveSponsorDetails(spoId,"A001",spoName,spoCompany,spoValue,date,donId,type,desc);

                boolean isSaveSponsor = sponsorBo.saveSponsorDetails(spoId,"A001",spoName,spoCompany,spoValue,date,donId,type,desc);
                if(isSaveSponsor) {
                    lblSponsorId.setText("");
                    txtSponsorName.setText("");
                    txtSponsorCompany.setText("");
                    txtSponsorValue.setText("");


                    lblDonationId.setText("");
                    selectCombo.getItems().clear();
                    txrAreaDonationDesc.setText("");

                    new Alert(Alert.AlertType.CONFIRMATION,"Saved sponsor donation!").show();

                    initialize();
                }

            }




        }
    } public boolean validations(){
        String spoName = txtSponsorName.getText();
        String spoCompany = txtSponsorCompany.getText();
        String sv = txtSponsorValue.getText();


        boolean matchesName =Pattern.matches("[A-Za-z]{3,}", spoName);
        boolean matchesCompany = Pattern.matches("[A-Za-z0-9]{2,}",spoCompany);
        boolean matchesSV = Pattern.matches("[0-9]{3,}",sv);

        if (!matchesName){
            new Alert(Alert.AlertType.ERROR,"Invalid Name. Please you can't include Symbols and Numbers !").show();
            txtSponsorName.setText("");
            return false;
        }

        if (!matchesCompany){
            new Alert(Alert.AlertType.ERROR,"Invalid Company Name.").show();
            txtSponsorCompany.setText("");
            return false;
        }

        if (!matchesSV){
            new Alert(Alert.AlertType.ERROR,"Please use numeric values only for value field!").show();
            txtSponsorValue.setText("");
            return false;
        }

        return true;
    }
    public void findCurrentAdmin() throws SQLException {
        //System.out.println(AdminUName+" 1111 "+AdminPwd);

        //String id = SponsorModel.findAdminId(AdminUName,AdminPwd);
        String id = sponsorBo.findAdmin(AdminUName,AdminPwd);
        this.currentUserId = id;

        //System.out.println(id);

    }


    public void txtArea(MouseEvent mouseEvent) {

    }

    public void selectOnAction(ActionEvent actionEvent) {
        comboValue = (String) selectCombo.getValue();


    }

    private void generateNextSponsorId() throws SQLException, ClassNotFoundException {

        //String idd = SponsorModel.nextSponsorId();

        String id = sponsorBo.generateNextSponsorId();

        lblSponsorId.setText(id);
    }

    public void totalSponsors() throws SQLException, ClassNotFoundException {

       //int tot =  SponsorModel.getTotalSponsors();
       int total = sponsorBo.getTotalSponsor();
       lblTotalSponsors.setText(String.valueOf(total));

    }
    public void findBestSpoDetails() throws SQLException, ClassNotFoundException {
        //SponsorDto bestSpoDetails = SponsorModel.getBestSpoDetails();

        SponsorDto sponsorDto = sponsorBo.getBestSpoDetails();

        String name = sponsorDto.getName();
        String company = sponsorDto.getCompany();
        double amount = sponsorDto.getValue();

        lblBestSpoName.setText(name);
        lblBestSpoCompany.setText(company);
        lblBestSpoAmount.setText(String.valueOf(amount));
    }

    public void btnPrintSponsorOnAction(ActionEvent actionEvent) {
        InputStream resourceAsStream = getClass().getResourceAsStream("/jasper/Sponsor.jrxml");
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

    public void btnSearchCompanyOnAction(ActionEvent actionEvent) {

        String value = txtCompanyNameSearch.getText();

        if(value.isEmpty()){
            idLblNoSearchResult.setText("Please fill required field");
            idResultBellowTable.setText("");
            loadAllSponsor();


        }else{
            boolean matches = Pattern.matches("[A-Za-z]{2,}",value);
            if (!matches) {
                new Alert(Alert.AlertType.ERROR, "Invalid Name. Please you can't include Symbols and Numbers !").show();
                txtCompanyNameSearch.setText("");

            }else{
                //var model = new SponsorModel();

                ObservableList<SponsorTm> obList = FXCollections.observableArrayList();

                try {
                    //List<SponsorDto> dtoList =model.getSponsorDetails(value);
                    List<SponsorDto> dtoList =sponsorBo.getSearchSponsor(value);


                    for (SponsorDto dto :dtoList) {
                        obList.add(
                                new SponsorTm(
                                        dto.getSId(),
                                        dto.getUId(),
                                        dto.getName(),
                                        dto.getCompany(),
                                        dto.getValue(),
                                        dto.getDate()
                                )

                        );
                    }

                    if(obList.isEmpty()){
                        new Alert(Alert.AlertType.ERROR,"No company found with this name..!").show();
                        idLblNoSearchResult.setText("");
                        idResultBellowTable.setText("");
                        txtCompanyNameSearch.setText("");
                        loadAllSponsor();
                    }else{
                        idResultBellowTable.setText("Results are shown bellow..!");
                        idLblNoSearchResult.setText("");
                        txtCompanyNameSearch.setText("");
                        tblSponsor.setItems(obList);
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }



        }
    }
}
