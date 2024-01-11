package lk.ijse.cric.bo.custom;

import lk.ijse.cric.bo.SuperBo;
import lk.ijse.cric.dao.SuperDAO;
import lk.ijse.cric.dto.MatchesDto;

import java.sql.SQLException;
import java.util.List;

public interface MatchesBo extends SuperBo {
    List<MatchesDto> getAllMatches() throws SQLException, ClassNotFoundException;

    String generateNextMatchId() throws SQLException, ClassNotFoundException;

    boolean checkId(String newMId) throws SQLException, ClassNotFoundException;

    boolean saveNewMatch(MatchesDto u001) throws SQLException, ClassNotFoundException;

    boolean UpdateMatche(MatchesDto matchesDto) throws SQLException, ClassNotFoundException;

    boolean deleteMactch(String newId) throws SQLException, ClassNotFoundException;


    String getTotal(String win) throws SQLException, ClassNotFoundException;

    String nextMatchDate() throws SQLException, ClassNotFoundException;

    List<String> getmatchIds() throws SQLException, ClassNotFoundException;

}
