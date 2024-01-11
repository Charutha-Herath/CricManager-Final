package lk.ijse.cric.dao.custom;

import lk.ijse.cric.dao.CrudDAO;
import lk.ijse.cric.dao.SuperDAO;
import lk.ijse.cric.entity.Umpire;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UmpireDao extends CrudDAO<Umpire> {

    /*ArrayList<Umpire> getAll() throws SQLException, ClassNotFoundException;


    boolean save(Umpire umpire) throws SQLException, ClassNotFoundException;

    boolean update(Umpire entity) throws SQLException, ClassNotFoundException;

    String generateNewId() throws SQLException, ClassNotFoundException;

    boolean delete(String umpId) throws SQLException, ClassNotFoundException;*/

    String getTotal() throws SQLException, ClassNotFoundException;


}
