package lk.ijse.cric.dao.custom.Impl;

import lk.ijse.cric.dao.SQLUtil;
import lk.ijse.cric.dao.custom.PlayerDao;
import lk.ijse.cric.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayerDaoImpl implements PlayerDao {
    @Override
    public ArrayList<Player> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT PId,name,age,type FROM player");

        ArrayList<Player> playerArrayList = new ArrayList<>();

        while (resultSet.next()){

            playerArrayList.add(new Player(
                    resultSet.getString("PId"),
                    resultSet.getString("name"),
                    resultSet.getInt("age"),
                    resultSet.getString("type")
            ));
        }
        return playerArrayList;
    }

    @Override
    public boolean save(Player player) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO player VALUES(?,?,?,?,?)",player.getPId(),player.getPUId(),player.getName(),player.getAge(),player.getType());
    }

    @Override
    public boolean update(Player player) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE player SET name=?,age=?,type=? WHERE PId=?",player.getName(),player.getAge(),player.getType(),player.getPId());
    }

    @Override
    public ArrayList<Player> search(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT PId FROM player ORDER BY PId DESC LIMIT 1");

        if (resultSet.next()){

            return splitUmpireId(resultSet.getString(1));

        }
        return "P001";
    }


    private static String splitUmpireId(String pid) {
        if (pid != null){
            String[] splint = pid.split("P0");

            int id = Integer.parseInt(splint[1]);
            id++;
            if (id > 9){
                return "P0"+id;
            }
            return "P00"+id;
        } return null;
    }
    @Override
    public String getTotal(String type) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = null;
        if (type.equals("Bat")){
            resultSet = SQLUtil.execute("SELECT COUNT(PId) AS row_count FROM player WHERE type LIKE \'Bat%\'");
        } else if (type.equals("Bow")) {
            resultSet = SQLUtil.execute("SELECT COUNT(PId) AS row_count FROM player WHERE type LIKE \'Bow%\'");
        }else {
            resultSet = SQLUtil.execute("SELECT COUNT(PId) AS row_count FROM player WHERE type LIKE \'All%\'");

        }


        if (resultSet.next()){
            return resultSet.getString("row_count");

        }else return "0";


    }

    @Override
    public boolean delete(String pId) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("DELETE FROM player WHERE PId=?",pId);
    }
}
