package lk.ijse.cric.dao.custom;

import lk.ijse.cric.dao.CrudDAO;
import lk.ijse.cric.dao.SuperDAO;
import lk.ijse.cric.entity.Donation;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DonationDao extends CrudDAO<Donation> {
    //boolean save(Donation entity) throws SQLException, ClassNotFoundException;///////////////////////

    //String generateNewId() throws SQLException, ClassNotFoundException;///////////////////

    //ArrayList<Donation> getAll() throws SQLException, ClassNotFoundException;////////////////////

    String getAmount() throws SQLException, ClassNotFoundException;

    String getTotal(String type) throws SQLException, ClassNotFoundException;

    String getLastDate() throws SQLException, ClassNotFoundException;
}
