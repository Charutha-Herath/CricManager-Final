package lk.ijse.cric.dao;

import lk.ijse.cric.entity.Coach;
import lk.ijse.cric.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO {

    ArrayList<T> getAll() throws SQLException, ClassNotFoundException;

    boolean save(T entity) throws SQLException, ClassNotFoundException;

    boolean update(T entity) throws SQLException, ClassNotFoundException;

    ArrayList<T> search(String name) throws SQLException, ClassNotFoundException;

    String generateNewId() throws SQLException, ClassNotFoundException;

    boolean delete(String pId) throws SQLException, ClassNotFoundException;

    //T getTotal() throws SQLException, ClassNotFoundException;
}
