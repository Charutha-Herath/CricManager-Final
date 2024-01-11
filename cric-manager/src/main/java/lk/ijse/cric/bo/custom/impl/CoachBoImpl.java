package lk.ijse.cric.bo.custom.impl;

import lk.ijse.cric.bo.custom.CoachBo;
import lk.ijse.cric.dao.DAOFactory;
import lk.ijse.cric.dao.custom.CoachDao;
import lk.ijse.cric.dao.custom.Impl.CoachDaoImpl;
import lk.ijse.cric.db.DbConnection;
import lk.ijse.cric.dto.CoachDto;
import lk.ijse.cric.entity.Coach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoachBoImpl implements CoachBo {

    //CoachDao coachDao = new CoachDaoImpl();

    CoachDao coachDao = (CoachDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.COACH);

    @Override
    public List<CoachDto> getAllCoaches() throws SQLException, ClassNotFoundException {

        //ResultSet resultSet = coachDao.getAll();

        ArrayList<Coach> coaches = coachDao.getAll();

        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql ="SELECT* FROM coach";

        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();*/

        ArrayList<CoachDto> dtoList = new ArrayList<>();

        for (Coach coach: coaches) {
            dtoList.add(new CoachDto(coach.getCId(), coach.getCUId(),coach.getName(),coach.getType()));
        }

        return dtoList;
    }

    @Override
    public boolean saveCoach(CoachDto coachDto) throws SQLException, ClassNotFoundException {

        return coachDao.save(new Coach(coachDto.getCId(),coachDto.getCUId(),coachDto.getName(),coachDto.getType()));

        /*Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO coach VALUES(?,?,?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,newId);
        pstm.setString(2,adminName);
        pstm.setString(3,name);
        pstm.setString(4,category);

        int resultSet = pstm.executeUpdate();

        return resultSet > -1;
        return false;*/
    }

    @Override
    public String generateNewCoachId() throws SQLException, ClassNotFoundException {

        return coachDao.generateNewId();
    }

    @Override
    public boolean updateCoach(CoachDto coachDto) throws SQLException, ClassNotFoundException {
        return coachDao.update(new Coach(coachDto.getCId(),coachDto.getName(),coachDto.getType()));
    }

    @Override
    public String totalCoaches() throws SQLException, ClassNotFoundException {

        Coach coach = coachDao.getTotal();
        //System.out.println(coach.getTotalCoaches());
        return coach.getTotalCoaches();

    }

    @Override
    public int getCoachCount(String type) throws SQLException, ClassNotFoundException {

        return coachDao.getCount(type);
    }

    @Override
    public List<CoachDto> getSearchCoaches(String name) throws SQLException, ClassNotFoundException {

        List<Coach> coach = coachDao.search(name);

        ArrayList<CoachDto> coachDtos = new ArrayList<>();

        for (Coach coaches : coach) {
            coachDtos.add(new CoachDto(coaches.getCId(), coaches.getCUId(),coaches.getName(),coaches.getType()));
        }

        return coachDtos;
    }

    @Override
    public List<CoachDto> getFilterCategory(String value) throws SQLException, ClassNotFoundException {

        List<Coach> coaches = coachDao.getFilter(value);

        ArrayList<CoachDto> coachDtos = new ArrayList<>();

        for (Coach coach : coaches) {
            coachDtos.add(new CoachDto(coach.getCId(), coach.getCUId(),coach.getName(),coach.getType()));
        }

        return coachDtos;
    }

    @Override
    public boolean findCoach(String updateId) throws SQLException, ClassNotFoundException {
        return coachDao.find(updateId);

    }
}
