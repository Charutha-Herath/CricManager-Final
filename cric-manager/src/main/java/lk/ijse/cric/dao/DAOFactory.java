package lk.ijse.cric.dao;

import lk.ijse.cric.dao.custom.CoachDao;
import lk.ijse.cric.dao.custom.Impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory(){

       return  (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType{
        COACH, SPONSORS, DONATION, USER, PLAYER, MATCH, COST, UMPIRE
    }

    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){

            case COACH:
                return new CoachDaoImpl();

            case SPONSORS:
                return  new SponsorDaoImpl();

            case DONATION:
                return new DonationDaoImpl();

            case USER:
                return new UserDaoImpl();

            case PLAYER:
                return new PlayerDaoImpl();

            case MATCH:
                return new MatchesDaoImpl();

            case COST:
                return new CostDaoImpl();

            case UMPIRE:
                return new UmpireDaoImpl();

            default:
                return null;
        }
        //return null;
    }
}
