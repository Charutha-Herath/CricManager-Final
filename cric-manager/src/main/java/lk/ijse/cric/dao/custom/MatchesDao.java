package lk.ijse.cric.dao.custom;

import lk.ijse.cric.dao.CrudDAO;
import lk.ijse.cric.dao.SuperDAO;
import lk.ijse.cric.entity.Matche;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MatchesDao extends CrudDAO<Matche> {
    /*ArrayList<Matche> getAll() throws SQLException, ClassNotFoundException;

    String generateNewId() throws SQLException, ClassNotFoundException;

    boolean save(Matche matche) throws SQLException, ClassNotFoundException;

    boolean update(Matche matche) throws SQLException, ClassNotFoundException;

    boolean delete(String newId) throws SQLException, ClassNotFoundException;*/

    boolean checkId(String newMId) throws SQLException, ClassNotFoundException;

    String getTotal(String win) throws SQLException, ClassNotFoundException;

    String getNextDate() throws SQLException, ClassNotFoundException;

    List<String> getMatchIds() throws SQLException, ClassNotFoundException;

    boolean updateBalance(String matchId, double tot) throws SQLException, ClassNotFoundException;
}
