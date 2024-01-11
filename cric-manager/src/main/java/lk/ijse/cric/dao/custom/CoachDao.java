package lk.ijse.cric.dao.custom;

import lk.ijse.cric.dao.CrudDAO;
import lk.ijse.cric.dao.SuperDAO;
import lk.ijse.cric.dto.CoachDto;
import lk.ijse.cric.entity.Coach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CoachDao extends CrudDAO<Coach> {

    //ArrayList<Coach> getAll() throws SQLException, ClassNotFoundException; ///////////////////////

    //boolean save(Coach entity) throws SQLException, ClassNotFoundException;//////////////////

    //String generateNewId() throws SQLException, ClassNotFoundException;//////////////////////

    //boolean update(Coach entity) throws SQLException, ClassNotFoundException;/////////////////////

    Coach getTotal() throws SQLException, ClassNotFoundException;

    int getCount(String entity) throws SQLException, ClassNotFoundException;

    //ArrayList<Coach> search(String name) throws SQLException, ClassNotFoundException;/////////////////

    List<Coach> getFilter(String value) throws SQLException, ClassNotFoundException;

    boolean find(String updateId) throws SQLException, ClassNotFoundException;
}
