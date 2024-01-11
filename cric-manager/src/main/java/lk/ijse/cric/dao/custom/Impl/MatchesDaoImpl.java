package lk.ijse.cric.dao.custom.Impl;

import lk.ijse.cric.dao.SQLUtil;
import lk.ijse.cric.dao.custom.MatchesDao;
import lk.ijse.cric.dto.MatchesDto;
import lk.ijse.cric.entity.Matche;
import net.sf.jasperreports.web.commands.ResetInCacheCommand;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchesDaoImpl implements MatchesDao {
    @Override
    public ArrayList<Matche> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT MId,ground,opposing_team,est_cost,balance,Date,Status FROM matches");

        ArrayList<Matche> matchList = new ArrayList<>();
        while (resultSet.next()) {
            matchList.add(new Matche(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }

        return matchList;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT MId FROM matches ORDER BY MId DESC LIMIT 1");

        if (resultSet.next()){

            return splitMatchId(resultSet.getString(1));

        }else return "M001";
    }

    @Override
    public boolean checkId(String newMId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT MId FROM matches");

        boolean flag = false;
        while (resultSet.next()){
            flag = newMId.equals(resultSet.getString(1));
        }
        return flag;
    }

    @Override
    public boolean save(Matche matche) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO matches (MId,MUId,ground,opposing_team,est_cost,Date,Status) VALUES(?,?,?,?,?,?,?)",
                matche.getMId(),
                matche.getMUId(),
                matche.getGround(),
                matche.getOpposing_team(),
                matche.getEst_cost(),
                matche.getDate(),
                matche.getStatus()
                );
    }

    @Override
    public boolean update(Matche matche) throws SQLException, ClassNotFoundException {

        return  SQLUtil.execute("UPDATE matches SET ground=?,opposing_team=?,est_cost=?,Date=?,Status=? WHERE MId=?",
                matche.getGround(),
                matche.getOpposing_team(),
                matche.getEst_cost(),
                matche.getDate(),
                matche.getStatus(),
                matche.getMId()
        );
    }

    @Override
    public ArrayList<Matche> search(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String newId) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM matches WHERE MId = ?",newId);
    }

    @Override
    public String getTotal(String win) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(MId) AS row_count FROM matches WHERE Status =?",win);

        if (resultSet.next()){
            return resultSet.getString("row_count");

        }else return "0";
    }

    @Override
    public String getNextDate() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT Date FROM matches ORDER BY Date DESC LIMIT 1");

        if (resultSet.next()){
            return resultSet.getString(1);

        }else return "0";
    }

    @Override
    public List<String> getMatchIds() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT MId from matches");

        List<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(
                    resultSet.getString(1)
            );
        }
        return list;
    }

    @Override
    public boolean updateBalance(String matchId, double tot) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT est_cost  FROM matches WHERE MId= ?",matchId);

        if (resultSet.next()){
            double est_cost =  resultSet.getDouble("est_cost");
            double v = est_cost - tot;

            return SQLUtil.execute("UPDATE matches SET balance = ? WHERE MId = ?",v,matchId);

        }else{
            return false;
        }
    }

    private static String splitMatchId(String Mid) {
        if (Mid != null){
            String[] splint = Mid.split("M0");

            int id = Integer.parseInt(splint[1]);
            id++;
            if (id > 9){
                return "M0"+id;
            }else return "M00"+id;
        } return null;
    }
}
