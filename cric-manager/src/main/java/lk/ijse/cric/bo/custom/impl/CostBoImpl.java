package lk.ijse.cric.bo.custom.impl;

import lk.ijse.cric.bo.custom.CostBo;
import lk.ijse.cric.dao.DAOFactory;
import lk.ijse.cric.dao.custom.CostDao;
import lk.ijse.cric.dao.custom.MatchesDao;
import lk.ijse.cric.db.DbConnection;
import lk.ijse.cric.dto.CostDto;
import lk.ijse.cric.entity.Cost;
//import lk.ijse.cric.model.MatchesModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CostBoImpl implements CostBo {

    CostDao costDao = (CostDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.COST);
    MatchesDao matchesDao = (MatchesDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.MATCH);
    @Override
    public boolean saveNewMatchAndCost(CostDto costDto) throws SQLException {

        double tot = costDto.getGround_fee() + costDto.getUmpire_fee() + costDto.getEquipment_fee() + costDto.getEquipment_fee();

        Connection connection = null;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            System.out.println("costBo : "+ costDto.getCostMId());

            boolean isOrderSaved = costDao.save(new Cost(
                    costDto.getCostId(),
                    costDto.getCostMId(),
                    costDto.getGround_fee(),
                    costDto.getUmpire_fee(),
                    costDto.getEquipment_fee(),
                    costDto.getMeal_and_other(),
                    tot
            ));

            System.out.println("isOrderSaved : "+ isOrderSaved);

            if (isOrderSaved) {

                //boolean isUpdated = MatchesModel.updateBalance(costDto.getMatchId(),tot);
                boolean isUpdated = matchesDao.updateBalance(costDto.getCostMId(),tot);
                System.out.println("isUpdated : "+isUpdated);
                //boolean isUpdated = MatchesModel.updateBalance(matchId,tot);

                if (isUpdated) {

                    connection.commit();
                    return true;

                }
            }
            connection.rollback();

            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }


    }

    @Override
    public List<CostDto> getAllCost() throws SQLException, ClassNotFoundException {
        ArrayList<Cost> costs = costDao.getAll();

        ArrayList<CostDto> costDtos = new ArrayList<>();

        for (Cost cost :costs) {
            costDtos.add(new CostDto(
                    cost.getCostId(),
                    cost.getCostMId(),
                    cost.getGround_fee(),
                    cost.getUmpire_fee(),
                    cost.getEquipment_fee(),
                    cost.getMeal_and_other(),
                    cost.getTotal()
            ));

        }
            return costDtos;
    }

    @Override
    public boolean checkMatchId(String newId) throws SQLException, ClassNotFoundException {

        return costDao.checkId(newId);
    }

    @Override
    public String getTotals(String Fee) throws SQLException, ClassNotFoundException {

        return costDao.getTotal(Fee);
    }

    @Override
    public String generateNextCostId() throws SQLException, ClassNotFoundException {

        return costDao.generateNewId();
    }
}
