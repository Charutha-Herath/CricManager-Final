package lk.ijse.cric.dao.custom.Impl;

import lk.ijse.cric.dao.SQLUtil;
import lk.ijse.cric.dao.custom.UmpireDao;
import lk.ijse.cric.dto.UmpireDto;
import lk.ijse.cric.entity.Umpire;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UmpireDaoImpl implements UmpireDao {

    @Override
    public ArrayList<Umpire> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM umpire");

        ArrayList<Umpire> umpireList = new ArrayList<>();

        while (resultSet.next()) {
            umpireList.add(new Umpire(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }

        return umpireList;
    }

    @Override
    public String getTotal() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(UmpId) AS row_count FROM umpire");

        if (resultSet.next()){
            return resultSet.getString("row_count");

        }
        return null;
    }

    @Override
    public boolean save(Umpire entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO umpire VALUES(?,?,?)",entity.getUmpId(),entity.getUmpUId(),entity.getName());
    }

    @Override
    public boolean update(Umpire entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE umpire SET name=? WHERE UmpId=?",entity.getName(),entity.getUmpId());
    }

    @Override
    public ArrayList<Umpire> search(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT UmpId FROM umpire ORDER BY UmpId DESC LIMIT 1");

        if (resultSet.next()){

            return splitUmpireId(resultSet.getString(1));

        }
        return "U001";
    }

    @Override
    public boolean delete(String umpId) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM umpire WHERE UmpId=?",umpId);
    }

    private static String splitUmpireId(String uid) {
        if (uid != null){
            String[] splint = uid.split("U0");

            int id = Integer.parseInt(splint[1]);
            id++;
            if (id > 9){
                return "U0"+id;
            }
            return "U00"+id;
        } return null;
    }
}
