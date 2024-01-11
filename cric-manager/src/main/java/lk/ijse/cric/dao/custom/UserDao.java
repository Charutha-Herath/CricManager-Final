package lk.ijse.cric.dao.custom;

import lk.ijse.cric.dao.CrudDAO;
import lk.ijse.cric.dao.SuperDAO;
import lk.ijse.cric.dto.UserDto;
import lk.ijse.cric.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDao extends CrudDAO<User> {
    //String generateNewId() throws SQLException, ClassNotFoundException;////////////////

    User getCurrentId() throws SQLException, ClassNotFoundException;

    boolean authenticateUser(User user) throws SQLException, ClassNotFoundException;

    boolean authenticateAdmin(User user) throws SQLException, ClassNotFoundException;

    String getUser(User user) throws SQLException, ClassNotFoundException;

    String getAdmin(String username) throws SQLException, ClassNotFoundException;

    boolean isValidUser(String userId) throws SQLException, ClassNotFoundException;

    String findName(String userId) throws SQLException, ClassNotFoundException;

    void saveUser(User user) throws SQLException, ClassNotFoundException;



    // boolean save(User entity) throws SQLException, ClassNotFoundException;////////////////////////

    //ArrayList<User> getAll() throws SQLException, ClassNotFoundException;////////////////
}
