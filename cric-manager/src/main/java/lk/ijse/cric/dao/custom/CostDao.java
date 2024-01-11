package lk.ijse.cric.dao.custom;

import lk.ijse.cric.dao.CrudDAO;
import lk.ijse.cric.dao.SuperDAO;
import lk.ijse.cric.entity.Cost;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CostDao extends CrudDAO<Cost> {

   /* boolean save(Cost cost) throws SQLException, ClassNotFoundException;

    ArrayList<Cost> getAll() throws SQLException, ClassNotFoundException;

    String generateNewId() throws SQLException, ClassNotFoundException;*/

    boolean checkId(String newId) throws SQLException, ClassNotFoundException;

    String getTotal(String fee) throws SQLException, ClassNotFoundException;

}
