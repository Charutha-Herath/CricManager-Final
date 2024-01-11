package lk.ijse.cric.bo.custom;

import lk.ijse.cric.bo.SuperBo;
import lk.ijse.cric.dao.SuperDAO;
import lk.ijse.cric.dto.DonationDto;

import java.sql.SQLException;
import java.util.List;

public interface DonationBo extends SuperBo {
    String generateNextDonationId() throws SQLException, ClassNotFoundException;

    List<DonationDto> getAllDonation() throws SQLException, ClassNotFoundException;


    String getAmountDonations() throws SQLException, ClassNotFoundException;

    String getTotalDonation(String type) throws SQLException, ClassNotFoundException;

    String getLastDonationDate() throws SQLException, ClassNotFoundException;
}
