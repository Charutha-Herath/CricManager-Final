package lk.ijse.cric.bo.custom;

import lk.ijse.cric.bo.SuperBo;
import lk.ijse.cric.dto.SponsorDto;

import java.sql.SQLException;
import java.util.List;

public interface SponsorBo extends SuperBo {

    List<SponsorDto> getAllSponsors() throws SQLException, ClassNotFoundException;

    String generateNextSponsorId() throws SQLException, ClassNotFoundException;

    boolean saveSponsorDetails(String spoId, String a001, String spoName, String spoCompany, double spoValue, String date, String donId, String type, String desc) throws SQLException;

    int getTotalSponsor() throws SQLException, ClassNotFoundException;

    SponsorDto getBestSpoDetails() throws SQLException, ClassNotFoundException;

    List<SponsorDto> getSearchSponsor(String value) throws SQLException, ClassNotFoundException;

    String findAdmin(String adminUName, String adminPwd) throws SQLException;
}
