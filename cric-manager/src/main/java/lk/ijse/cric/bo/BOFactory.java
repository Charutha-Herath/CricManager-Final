package lk.ijse.cric.bo;

import lk.ijse.cric.bo.custom.CoachBo;
import lk.ijse.cric.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory(){

        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes implements SuperBo{
        COACH, SPONSOR , DONATION, USER, PLAYER, MATCH, COST, UMPIRE
    }

    public SuperBo getBo(BOTypes boTypes){
        switch(boTypes){

            case COACH:
                return new CoachBoImpl();
            case SPONSOR:
                return new SponsorBoImpl();
            case DONATION:
                return new DonationBoImpl();
            case USER:
                return new UserBoImpl();
            case PLAYER:
                return new PlayerBoImpl();
            case MATCH:
                return new MatchesBoImpl();
            case COST:
                return new CostBoImpl();
            case UMPIRE:
                return new UmpireBoImpl();
            default:
                return null;
        }
    }
}
