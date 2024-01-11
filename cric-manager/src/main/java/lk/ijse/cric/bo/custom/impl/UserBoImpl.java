package lk.ijse.cric.bo.custom.impl;

import lk.ijse.cric.bo.custom.UserBo;
import lk.ijse.cric.dao.DAOFactory;
import lk.ijse.cric.dao.custom.UserDao;
import lk.ijse.cric.dto.LoginDto;
import lk.ijse.cric.dto.UserDto;
import lk.ijse.cric.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserBoImpl implements UserBo {

    UserDao userDao = (UserDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.USER);
    @Override
    public String generateNextUserId() throws SQLException, ClassNotFoundException {

        return userDao.generateNewId();
    }

    @Override
    public UserDto getCurretUserDetails() throws SQLException, ClassNotFoundException {

        User user= userDao.getCurrentId();

        return new UserDto(user.getUId(),user.getUName(),user.getPwd(),user.getStartDate());


    }

    @Override
    public boolean saveNewUser(UserDto userDto) throws SQLException, ClassNotFoundException {

        return userDao.save(new User(userDto.getUId(),userDto.getUName(),userDto.getPwd(),userDto.getStartDate()));
    }

    @Override
    public List<UserDto> getAllUsers() throws SQLException, ClassNotFoundException {

        ArrayList<User> users = userDao.getAll();

        ArrayList<UserDto> dtos = new ArrayList<>();

        for (User user : users) {
            dtos.add(new UserDto(
                    user.getUId(),
                    user.getUName(),
                    user.getPwd(),
                    user.getStartDate()
                    ));
        }
        return dtos;
    }

    @Override
    public boolean authenticateUser(UserDto login) throws SQLException, ClassNotFoundException {

        return userDao.authenticateUser(new User(login.getUName(),login.getPwd()));
    }

    @Override
    public boolean authenticateAdmin(UserDto login) throws SQLException, ClassNotFoundException {

        return userDao.authenticateAdmin(new User(login.getUName(),login.getPwd()));
    }

    @Override
    public String getUser(UserDto login) throws SQLException, ClassNotFoundException {

        return userDao.getUser(new User(login.getUName(), login.getPwd()));
    }

    @Override
    public String getAdminId(String username) throws SQLException, ClassNotFoundException {

        return userDao.getAdmin(username);
    }

    @Override
    public boolean isValidUser(String userId) throws SQLException, ClassNotFoundException {

        return userDao.isValidUser(userId);
    }

    @Override
    public String findUserName(String userId) throws SQLException, ClassNotFoundException {

        return userDao.findName(userId);
    }

    @Override
    public void saveUserDetails(UserDto userDto) throws SQLException, ClassNotFoundException {

        userDao.saveUser(new User(userDto.getUName(),userDto.getPwd()));
    }


}
