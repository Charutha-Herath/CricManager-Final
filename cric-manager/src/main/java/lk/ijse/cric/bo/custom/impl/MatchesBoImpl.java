package lk.ijse.cric.bo.custom.impl;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import lk.ijse.cric.bo.custom.MatchesBo;
import lk.ijse.cric.dao.DAOFactory;
import lk.ijse.cric.dao.SQLUtil;
import lk.ijse.cric.dao.custom.MatchesDao;
import lk.ijse.cric.dto.MatchesDto;
import lk.ijse.cric.entity.Matche;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class MatchesBoImpl implements MatchesBo {

    MatchesDao matchesDao = (MatchesDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.MATCH);
    @Override
    public List<MatchesDto> getAllMatches() throws SQLException, ClassNotFoundException {
        ArrayList<Matche> matches = matchesDao.getAll();

        ArrayList<MatchesDto> matchesDtos = new ArrayList<>();

        for (Matche matche:matches) {
            matchesDtos.add(new MatchesDto(
                    matche.getMId(),
                    matche.getGround(),
                    matche.getOpposing_team(),
                    matche.getEst_cost(),
                    matche.getBalance(),
                    matche.getDate(),
                    matche.getStatus()
            ));
        }
        return matchesDtos;
    }

    @Override
    public String generateNextMatchId() throws SQLException, ClassNotFoundException {

        return matchesDao.generateNewId();
    }

    @Override
    public boolean checkId(String newMId) throws SQLException, ClassNotFoundException {

        return matchesDao.checkId(newMId);
    }

    @Override
    public boolean saveNewMatch(MatchesDto dto) throws SQLException, ClassNotFoundException {

        return matchesDao.save(new Matche(
                dto.getMId(),
                dto.getMUId(),
                dto.getGround(),
                dto.getOpposing_team(),
                dto.getEst_cost(),
                dto.getStatus(),
                dto.getDate()
        ));
    }

    @Override
    public boolean UpdateMatche(MatchesDto dto) throws SQLException, ClassNotFoundException {

        return matchesDao.update(new Matche(
                dto.getMId(),
                dto.getGround(),
                dto.getOpposing_team(),
                dto.getEst_cost(),
                dto.getEst_cost(),
                dto.getDate(),
                dto.getStatus()
        ));
    }

    @Override
    public boolean deleteMactch(String newId) throws SQLException, ClassNotFoundException {

        return matchesDao.delete(newId);
    }

    @Override
    public String getTotal(String win) throws SQLException, ClassNotFoundException {

        return matchesDao.getTotal(win);
    }

    @Override
    public String nextMatchDate() throws SQLException, ClassNotFoundException {

        return matchesDao.getNextDate();
    }

    @Override
    public List<String> getmatchIds() throws SQLException, ClassNotFoundException {

        return matchesDao.getMatchIds();
    }
}
