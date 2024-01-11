package lk.ijse.cric.dao.custom.Impl;

import lk.ijse.cric.dao.SQLUtil;
import lk.ijse.cric.dao.custom.SponsorDao;
import lk.ijse.cric.dto.SponsorDto;
import lk.ijse.cric.entity.Coach;
import lk.ijse.cric.entity.Sponsor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SponsorDaoImpl implements SponsorDao {

    @Override
    public ArrayList<Sponsor> getAll() throws SQLException, ClassNotFoundException {


        ResultSet rst = SQLUtil.execute("SELECT* FROM sponsor");

        ArrayList<Sponsor> getAllSponsors = new ArrayList<>();

        while (rst.next()){
            Sponsor entity = new Sponsor(
                    rst.getString("SId"),
                    rst.getString("UId"),
                    rst.getString("name"),
                    rst.getString("company"),
                    rst.getDouble("value"),
                    rst.getString("Date")
            );

            getAllSponsors.add(entity);
        }


        return getAllSponsors;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT SId FROM sponsor ORDER BY UId desc LIMIT 1");

        if (rst.next()){
            return splitSponsorId(rst.getString("SId"));

        }
        return null;
    }

    @Override
    public boolean delete(String pId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean save(Sponsor entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO sponsor VALUES(?,?,?,?,?,?)",entity.getSId(),entity.getUId(),entity.getName(),entity.getCompany(),entity.getValue(),entity.getDate());

    }

    @Override
    public boolean update(Sponsor entity) throws SQLException, ClassNotFoundException {
        return false;
    }





    private static String splitSponsorId(String idd) {
        if (idd != null){
            String[] splint = idd.split("S0");

            int id = Integer.parseInt(splint[1]);
            id++;
            if (id > 9){
                return "S0"+id;
            }
            return "S00"+id;

        }
        return null;
    }

    @Override
    public int getTotal() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) AS row_count FROM sponsor");

        if (rst.next()){
            return rst.getInt("row_count");

        }
        else return 0;
    }

    @Override
    public Sponsor getBest() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT name,company,value FROM sponsor ORDER BY value desc LIMIT 1");

        Sponsor sponsor = null;
        if (resultSet.next()){
            sponsor = new Sponsor(
                    resultSet.getString("name"),
                    resultSet.getString("company"),
                    resultSet.getDouble("value")
            );
        }
        return sponsor;
    }

    @Override
    public ArrayList<Sponsor> search(String entity) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT* FROM sponsor WHERE company=?",entity);

        ArrayList<Sponsor> sponsors = new ArrayList<>();

        while(resultSet.next()){
            sponsors.add(
                    new Sponsor(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDouble(5),
                            resultSet.getString(6)
                    )
            );

        }
        return sponsors;

    }


}
