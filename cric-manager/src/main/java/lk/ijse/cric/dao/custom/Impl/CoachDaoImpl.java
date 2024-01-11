package lk.ijse.cric.dao.custom.Impl;

import lk.ijse.cric.dao.SQLUtil;
import lk.ijse.cric.dao.custom.CoachDao;
import lk.ijse.cric.db.DbConnection;
import lk.ijse.cric.dto.CoachDto;
import lk.ijse.cric.entity.Coach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoachDaoImpl implements CoachDao {

    @Override
    public ArrayList<Coach> getAll() throws SQLException, ClassNotFoundException {

        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql ="SELECT* FROM coach";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/

        ResultSet rst = SQLUtil.execute("SELECT* FROM coach");

        ArrayList<Coach> getAllCustomer = new ArrayList<>();

        while (rst.next()){
            Coach entity = new Coach(
                    rst.getString("CId"),
                    rst.getString("CUId"),
                    rst.getString("name"),
                    rst.getString("type")
            );

            getAllCustomer.add(entity);
        }
        return getAllCustomer;
        //return rst;
    }

    @Override
    public boolean save(Coach entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO coach VALUES(?,?,?,?)",entity.getCId(),entity.getCUId(),entity.getName(),entity.getType());

        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO coach VALUES(?,?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,newId);
        pstm.setString(2,adminName);
        pstm.setString(3,name);
        pstm.setString(4,category);

        int resultSet = pstm.executeUpdate();

        return resultSet > -1;

        return false;*/
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT CId FROM coach ORDER BY CId DESC LIMIT 1");

        if (resultSet.next()){

            return splitUserId(resultSet.getString(1));

        }
        return "C001";
    }

    @Override
    public boolean delete(String pId) throws SQLException, ClassNotFoundException {
        return false;
    }

    private String splitUserId(String CId) {
        if (CId != null){
            String[] splint = CId.split("C0");

            int id = Integer.parseInt(splint[1]);
            id++;
            if (id > 9){
                return "C0"+id;
            }
            return "C00"+id;
        }
        return null;
    }

    @Override
    public boolean update(Coach entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE coach SET name=?,type=? WHERE CId=?",entity.getName(),entity.getType(),entity.getCId());

    }

    @Override
    public Coach getTotal() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT COUNT(CId) AS row_count FROM Coach;");

        if (rst.next()){
            Coach coach = new Coach(rst.getString("row_count"));
            return coach;
        }
        return null;
    }

    @Override
    public int getCount(String entity) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT COUNT(*) AS row_count FROM Coach WHERE type=?",entity);

        if (rst.next()){
            return rst.getInt("row_count");
        }
        return 0;
    }

    @Override
    public ArrayList<Coach> search(String name) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT* FROM coach WHERE name =?",name);
        ArrayList<Coach> getAllCoaches = new ArrayList<>();
        if (rst.next()){
            Coach coach = new Coach(
                    rst.getString("CId"),
                    rst.getString("CUId"),
                    rst.getString("name"),
                    rst.getString("type")
            );

            getAllCoaches.add(coach);
        }
        return getAllCoaches;
    }

    @Override
    public List<Coach> getFilter(String value) throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.execute("SELECT* FROM coach WHERE type=?",value);
        ArrayList<Coach> getAllCoaches = new ArrayList<>();
        while (rst.next()){
            Coach coach = new Coach(
                    rst.getString("CId"),
                    rst.getString("CUId"),
                    rst.getString("name"),
                    rst.getString("type")
            );

            getAllCoaches.add(coach);
        }
        return getAllCoaches;
    }

    @Override
    public boolean find(String updateId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT CId FROM coach WHERE CId =? ",updateId);

        if (resultSet.next()){
            return updateId.equals(resultSet.getString("CId"));
        }
        return false;
    }
}
