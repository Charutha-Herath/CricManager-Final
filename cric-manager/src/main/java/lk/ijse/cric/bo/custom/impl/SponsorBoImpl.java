package lk.ijse.cric.bo.custom.impl;

import lk.ijse.cric.bo.custom.SponsorBo;
import lk.ijse.cric.dao.DAOFactory;
import lk.ijse.cric.dao.custom.DonationDao;
import lk.ijse.cric.dao.custom.Impl.SponsorDaoImpl;
import lk.ijse.cric.dao.custom.SponsorDao;
import lk.ijse.cric.db.DbConnection;
import lk.ijse.cric.dto.CoachDto;
import lk.ijse.cric.dto.SponsorDto;
import lk.ijse.cric.entity.Coach;
import lk.ijse.cric.entity.Donation;
import lk.ijse.cric.entity.Sponsor;
//import lk.ijse.cric.model.DonationModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SponsorBoImpl implements SponsorBo {

    SponsorDao sponsorDao = (SponsorDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SPONSORS);

    DonationDao donationDao = (DonationDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.DONATION);

    @Override
    public List<SponsorDto> getAllSponsors() throws SQLException, ClassNotFoundException {

        ArrayList<Sponsor> sponsors = sponsorDao.getAll();

        ArrayList<SponsorDto> dtoList = new ArrayList<>();

        for (Sponsor sponsor: sponsors) {

            dtoList.add(new SponsorDto(sponsor.getSId(),sponsor.getUId(),sponsor.getName(),sponsor.getCompany(),sponsor.getValue(),sponsor.getDate()));
        }

        return dtoList;
    }

    @Override
    public String generateNextSponsorId() throws SQLException, ClassNotFoundException {

        return sponsorDao.generateNewId();
    }

    @Override
    public boolean saveSponsorDetails(String spoId, String a001, String spoName, String spoCompany, double spoValue, String date, String donId, String type, String desc) throws SQLException {

        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

              boolean isSaveSponsor = sponsorDao.save(new Sponsor(spoId,a001,spoName,spoCompany,spoValue,date));


              if (isSaveSponsor) {

                //boolean isSaveDonation = DonationModel.saveDonation(donId,type,spoValue,desc,date);
                  boolean isSaveDonation = donationDao.save(new Donation(donId,type,spoValue,desc,date));

                  if (isSaveDonation) {

                    connection.commit();
                  }

              }connection.rollback();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }return true;
    }

    @Override
    public int getTotalSponsor() throws SQLException, ClassNotFoundException {

        return sponsorDao.getTotal();
    }

    @Override
    public SponsorDto getBestSpoDetails() throws SQLException, ClassNotFoundException {

        Sponsor sponsor = sponsorDao.getBest();

        return new SponsorDto(sponsor.getName(),sponsor.getCompany(),sponsor.getValue());

    }

    @Override
    public List<SponsorDto> getSearchSponsor(String value) throws SQLException, ClassNotFoundException {

        ArrayList<Sponsor> sponsors = sponsorDao.search(value);

        ArrayList<SponsorDto> dtos = new ArrayList<>();

        for (Sponsor sponsor :sponsors) {

            dtos.add(new SponsorDto(sponsor.getSId(),sponsor.getUId(),sponsor.getName(),sponsor.getCompany(),sponsor.getValue(),sponsor.getDate()));
        }
        return dtos;
    }

    @Override
    public String findAdmin(String adminUName, String adminPwd) throws SQLException {

        //System.out.println(adminUName+" 2222 "+adminPwd);
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT UId FROM user WHERE  UName=? AND Pwd =?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,adminUName);
        pstm.setString(2,adminPwd);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()){
            String check = resultSet.getString("UId");
            //System.out.println(check);

        }else {
            //System.out.println("c 1");
            return null;
        }
        //System.out.println("c 2");
        return null;
    }


}
