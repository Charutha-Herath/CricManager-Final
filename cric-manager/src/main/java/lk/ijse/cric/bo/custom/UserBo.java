package lk.ijse.cric.bo.custom;

import lk.ijse.cric.bo.SuperBo;
import lk.ijse.cric.dao.SuperDAO;
import lk.ijse.cric.dto.LoginDto;
import lk.ijse.cric.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserBo extends SuperBo {
    String generateNextUserId() throws SQLException, ClassNotFoundException;

    UserDto getCurretUserDetails() throws SQLException, ClassNotFoundException;

    boolean saveNewUser(UserDto userDto) throws SQLException, ClassNotFoundException;

    List<UserDto> getAllUsers() throws SQLException, ClassNotFoundException;

    boolean authenticateUser(UserDto login) throws SQLException, ClassNotFoundException;

    boolean authenticateAdmin(UserDto login) throws SQLException, ClassNotFoundException;

    String getUser(UserDto login) throws SQLException, ClassNotFoundException;

    String getAdminId(String username) throws SQLException, ClassNotFoundException;

    boolean isValidUser(String userId) throws SQLException, ClassNotFoundException;

    String findUserName(String userId) throws SQLException, ClassNotFoundException;


    void saveUserDetails(UserDto userDto) throws SQLException, ClassNotFoundException;
}
