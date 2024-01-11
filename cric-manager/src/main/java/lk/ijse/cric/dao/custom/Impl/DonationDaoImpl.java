package lk.ijse.cric.dao.custom.Impl;

import lk.ijse.cric.dao.SQLUtil;
import lk.ijse.cric.dao.custom.DonationDao;
import lk.ijse.cric.entity.Donation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DonationDaoImpl implements DonationDao {
    @Override
    public boolean save(Donation entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO donation VALUES(?,?,?,?,?)",entity.getDId(),entity.getType(),entity.getValue(),entity.getDesc(),entity.getDate());
    }

    @Override
    public boolean update(Donation entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Donation> search(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT DId FROM donation ORDER BY DId desc LIMIT 1");

        if (rst.next()) {
            return splitDonationId(rst.getString(1));

        }
        return null;
    }

    @Override
    public boolean delete(String pId) throws SQLException, ClassNotFoundException {
        return false;
    }


    private static String splitDonationId(String dId) {

        if (dId != null) {
            String[] splint = dId.split("D0");

            int id = Integer.parseInt(splint[1]);
            id++;
            if (id > 9) {
                return "D0" + id;
            }
            return "D00" + id;

        }
        return null;
    }

    @Override
    public ArrayList<Donation> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT* FROM donation");

        ArrayList<Donation> donations = new ArrayList<>();

        while (resultSet.next()){
            Donation donation1 = new Donation(
                    resultSet.getString("DId"),
                    resultSet.getString("type"),
                    resultSet.getDouble("value"),
                    resultSet.getString("desc"),
                    resultSet.getString("date")
            );

            donations.add(donation1);
        }
        return donations;
    }

    @Override
    public String getAmount() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT SUM(value) AS sum_value FROM donation");

        if (resultSet.next()){

            return resultSet.getString("sum_value");
        }
        return null;
    }

    @Override
    public String getTotal(String type) throws SQLException, ClassNotFoundException {

        //ResultSet resultSet = SQLUtil.execute("SELECT SUM(value) AS sum_value FROM donation WHERE type = \"Equipment\"");
        ResultSet resultSet = SQLUtil.execute("SELECT SUM(value) AS sum_value FROM donation WHERE type =?",type);

        if (resultSet.next()){

            return resultSet.getString("sum_value");
        }
        return null;
    }


    @Override
    public String getLastDate() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT Date FROM donation ORDER BY Date desc LIMIT 1");

        if (resultSet.next()){
            return resultSet.getString("Date");
        }
        return null;
    }
}
