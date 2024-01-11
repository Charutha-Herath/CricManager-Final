package lk.ijse.cric.bo.custom;

import lk.ijse.cric.bo.SuperBo;
import lk.ijse.cric.dto.UmpireDto;
import lk.ijse.cric.entity.Umpire;

import java.sql.SQLException;
import java.util.List;

public interface UmpireBo extends SuperBo {
    List<UmpireDto> getAllItems() throws SQLException, ClassNotFoundException;

    String totalUmpires() throws SQLException, ClassNotFoundException;

    boolean saveNewCoach(UmpireDto umpire) throws SQLException, ClassNotFoundException;

    boolean updateUmpire(UmpireDto umpireDto) throws SQLException, ClassNotFoundException;

    String generateNewCoachId() throws SQLException, ClassNotFoundException;

    boolean deleteUmpire(String umpId) throws SQLException, ClassNotFoundException;
}
