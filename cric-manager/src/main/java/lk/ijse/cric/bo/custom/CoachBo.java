package lk.ijse.cric.bo.custom;

import lk.ijse.cric.bo.SuperBo;
import lk.ijse.cric.dto.CoachDto;
import lk.ijse.cric.entity.Coach;

import java.sql.SQLException;
import java.util.List;

public interface CoachBo extends SuperBo {


    List<CoachDto> getAllCoaches() throws SQLException, ClassNotFoundException;

    boolean saveCoach(CoachDto coachDto) throws SQLException, ClassNotFoundException;

    String generateNewCoachId() throws SQLException, ClassNotFoundException;

    boolean updateCoach(CoachDto coach) throws SQLException, ClassNotFoundException;

    String totalCoaches() throws SQLException, ClassNotFoundException;

    int getCoachCount(String bowling) throws SQLException, ClassNotFoundException;

    List<CoachDto> getSearchCoaches(String name) throws SQLException, ClassNotFoundException;

    List<CoachDto> getFilterCategory(String value) throws SQLException, ClassNotFoundException;

    boolean findCoach(String updateId) throws SQLException, ClassNotFoundException;
}
