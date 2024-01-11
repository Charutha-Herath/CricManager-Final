package lk.ijse.cric.bo.custom.impl;

import lk.ijse.cric.bo.custom.UmpireBo;
import lk.ijse.cric.dao.DAOFactory;
import lk.ijse.cric.dao.custom.UmpireDao;
import lk.ijse.cric.dto.UmpireDto;
import lk.ijse.cric.entity.Umpire;
import lk.ijse.cric.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UmpireBoImpl implements UmpireBo {

    UmpireDao umpireDao = (UmpireDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.UMPIRE);
    @Override
    public List<UmpireDto> getAllItems() throws SQLException, ClassNotFoundException {

        ArrayList<Umpire> umpires = umpireDao.getAll();

        ArrayList<UmpireDto> umpireDtos = new ArrayList<>();

        for (Umpire umpire :umpires) {
            umpireDtos.add(new UmpireDto(
                    umpire.getUmpId(),
                    umpire.getUmpUId(),
                    umpire.getName()
            ));
        }
        return umpireDtos;
    }

    @Override
    public String totalUmpires() throws SQLException, ClassNotFoundException {

        return umpireDao.getTotal();
    }

    @Override
    public boolean saveNewCoach(UmpireDto dto) throws SQLException, ClassNotFoundException {

        return umpireDao.save(new Umpire(dto.getUmpId(),dto.getUmpUId(),dto.getName()));
    }

    @Override
    public boolean updateUmpire(UmpireDto dto) throws SQLException, ClassNotFoundException {

        return umpireDao.update(new Umpire(dto.getUmpId(),dto.getName()));
    }

    @Override
    public String generateNewCoachId() throws SQLException, ClassNotFoundException {

        return umpireDao.generateNewId();
    }

    @Override
    public boolean deleteUmpire(String umpId) throws SQLException, ClassNotFoundException {

        return umpireDao.delete(umpId);
    }
}
