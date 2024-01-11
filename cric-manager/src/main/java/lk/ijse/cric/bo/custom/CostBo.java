package lk.ijse.cric.bo.custom;

import lk.ijse.cric.bo.SuperBo;
import lk.ijse.cric.dto.CostDto;

import java.sql.SQLException;
import java.util.List;

public interface CostBo extends SuperBo {

    boolean saveNewMatchAndCost(CostDto costDto) throws SQLException;

    List<CostDto> getAllCost() throws SQLException, ClassNotFoundException;

    boolean checkMatchId(String newId) throws SQLException, ClassNotFoundException;

    String getTotals(String groundFee) throws SQLException, ClassNotFoundException;

    String generateNextCostId() throws SQLException, ClassNotFoundException;
}
