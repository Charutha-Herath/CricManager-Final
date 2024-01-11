package lk.ijse.cric.bo.custom.impl;

import lk.ijse.cric.bo.custom.DonationBo;
import lk.ijse.cric.dao.DAOFactory;
import lk.ijse.cric.dao.custom.DonationDao;
import lk.ijse.cric.dto.DonationDto;
import lk.ijse.cric.entity.Donation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DonationBoImpl implements DonationBo {

    DonationDao donationDao = (DonationDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.DONATION);
    @Override
    public String generateNextDonationId() throws SQLException, ClassNotFoundException {

        return donationDao.generateNewId();
    }

    @Override
    public List<DonationDto> getAllDonation() throws SQLException, ClassNotFoundException {

        ArrayList<Donation> donations = donationDao.getAll();

        ArrayList<DonationDto> donationDtos = new ArrayList<>();

        for (Donation donation : donations) {
            DonationDto dto = new DonationDto(
                    donation.getDId(),
                    donation.getType(),
                    donation.getValue(),
                    donation.getDesc(),
                    donation.getDate()
            );
            donationDtos.add(dto);
        }
        return donationDtos;
    }

    @Override
    public String getAmountDonations() throws SQLException, ClassNotFoundException {

        return donationDao.getAmount();
    }

    @Override
    public String getTotalDonation(String type) throws SQLException, ClassNotFoundException {

        return donationDao.getTotal(type);
    }

    @Override
    public String getLastDonationDate() throws SQLException, ClassNotFoundException {

        return donationDao.getLastDate();
    }
}
