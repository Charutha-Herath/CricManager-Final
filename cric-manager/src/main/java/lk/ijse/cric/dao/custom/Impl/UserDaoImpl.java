package lk.ijse.cric.dao.custom.Impl;

import lk.ijse.cric.dao.SQLUtil;
import lk.ijse.cric.dao.custom.UserDao;
import lk.ijse.cric.db.DbConnection;
import lk.ijse.cric.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {

    @Override
    public boolean save(User user) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO user VALUES(?,?,?,?)", user.getUId(),user.getUName(),user.getPwd(),user.getStartDate());
    }

    @Override
    public boolean update(User entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<User> search(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet =  SQLUtil.execute("SELECT* FROM user");

        ArrayList<User> dtoList = new ArrayList<>();

        while (resultSet.next()){

            dtoList.add(new User(
                    resultSet.getString("UId"),
                    resultSet.getString("UName"),
                    resultSet.getString("Pwd"),
                    resultSet.getString("StartDate")
                    ));
        }
        return dtoList;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT UId FROM user WHERE UId LIKE \"U%\" ORDER BY UId desc LIMIT 1");

        if (resultSet.next()){
            return splitUserId(resultSet.getString("UId"));
        }
        return null;
    }

    @Override
    public boolean delete(String pId) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public User getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE UId LIKE \"U%\" ORDER BY UId desc LIMIT 1");

        User user = null;
        if (resultSet.next()){
            user = new User(
                    resultSet.getString("UId"),
                    resultSet.getString("UName"),
                    resultSet.getString("Pwd"),
                    resultSet.getString("StartDate")
            );
        }
        return user;

    }

    @Override
    public boolean authenticateUser(User entity) throws SQLException, ClassNotFoundException {

        String usedUserName = entity.getUName();
        String usedPassword = entity.getPwd();

        boolean flag = false;

        ResultSet resultSet = SQLUtil.execute("SELECT UName,Pwd FROM user WHERE UID LIKE 'U%' ORDER BY UID DESC LIMIT 1");

        if(resultSet.next()){
            String user = resultSet.getString("UName");
            String pw = resultSet.getString("Pwd");
            if (usedUserName.equals(user) & usedPassword.equals(pw)) {
                flag = true;
            }
            else flag = false;
        }
        return flag;

    }

    @Override
    public boolean authenticateAdmin(User entity) throws SQLException, ClassNotFoundException {

        String username = entity.getUName();
        String password = entity.getPwd();

        boolean flag = false;

        ResultSet resultSet = SQLUtil.execute("SELECT UName,Pwd FROM user WHERE UID LIKE 'A%' ORDER BY UID DESC LIMIT 1");

        if(resultSet.next()){
            String user = resultSet.getString("UName");
            String pw = resultSet.getString("Pwd");
            if (username.equals(user) & password.equals(pw)) {
                flag = true;
            }
            else flag = false;
        }
        return flag;
    }

    @Override
    public String getUser(User entity) throws SQLException, ClassNotFoundException {

        String usedUserName = entity.getUName();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE UName = ?",usedUserName);

        String r = null;

        if (resultSet.next()){
            if (usedUserName.equals(resultSet.getString("UName"))){
                r = resultSet.getString("UId");
                return r;
            }
        }return null;
    }

    @Override
    public String getAdmin(String username) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT UId FROM user WHERE UName = ?",username);


        if (resultSet.next()){
            return resultSet.getString(1);
        }else return null;
    }

    @Override
    public boolean isValidUser(String userId) throws SQLException, ClassNotFoundException {

       ResultSet resultSet = SQLUtil.execute("SELECT Pwd FROM user WHERE UId=?",userId);



        boolean flag = false;

        if (resultSet.next()){
            String pwd = resultSet.getString(1);
            if (pwd==null){
                flag = true;
            }

        }return flag;
    }

    @Override
    public String findName(String userId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT UName  FROM user WHERE UId =?",userId);

        String uname = null;
        if (resultSet.next()){

            uname = resultSet.getString("UName");
        }
        return uname;
    }

    @Override
    public void saveUser(User user) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("UPDATE user SET Pwd =? WHERE UName=?",user.getPwd(),user.getUName());

    }


    private static String splitUserId(String userId) {
        if (userId != null){
            String[] splint = userId.split("U0");

            int id = Integer.parseInt(splint[1]);
            id++;
            if (id > 9){
                return "U0"+id;
            }
            return "U00"+id;
        }
        return null;
    }
}
