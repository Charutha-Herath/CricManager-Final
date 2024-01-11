package lk.ijse.cric.dao.custom;

import lk.ijse.cric.dao.CrudDAO;
import lk.ijse.cric.dao.SuperDAO;
import lk.ijse.cric.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlayerDao extends CrudDAO<Player> {
 /*   ArrayList<Player> getAll() throws SQLException, ClassNotFoundException;

    boolean save(Player player) throws SQLException, ClassNotFoundException;

    boolean update(Player player) throws SQLException, ClassNotFoundException;

    String generateNewId() throws SQLException, ClassNotFoundException;
    boolean delete(String pId) throws SQLException, ClassNotFoundException;*/

    String getTotal(String type) throws SQLException, ClassNotFoundException;

}
