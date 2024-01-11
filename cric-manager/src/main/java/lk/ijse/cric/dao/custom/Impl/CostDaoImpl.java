package lk.ijse.cric.dao.custom.Impl;

import lk.ijse.cric.dao.SQLUtil;
import lk.ijse.cric.dao.custom.CostDao;
import lk.ijse.cric.entity.Cost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CostDaoImpl implements CostDao {


    @Override
    public boolean save(Cost entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO cost VALUES(?,?,?,?,?,?,?)",
                entity.getCostId(),
                entity.getCostMId(),
                entity.getGround_fee(),
                entity.getUmpire_fee(),
                entity.getEquipment_fee(),
                entity.getMeal_and_other(),
                entity.getTotal()
        );

        /*System.out.println("\n\nIn save method : \n"
                +entity.getCostId()+"\n"+
                entity.getCostMId()+"\n"+
                entity.getGround_fee()+"\n"+
                entity.getUmpire_fee()+"\n"+
                entity.getEquipment_fee()+"\n"+
                entity.getMeal_and_other()+"\n"+
                entity.getTotal()+"\nEnd...........\n\n");*/

    }

    @Override
    public boolean update(Cost entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<Cost> search(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<Cost> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT* FROM cost");

        ArrayList<Cost> costs = new ArrayList<>();

        while (resultSet.next()){
            costs.add(new Cost(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getDouble(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7)
            ));
        }
        return costs;
    }

    @Override
    public boolean checkId(String newId) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT CostMId FROM cost");

        boolean flag = false;         /////////////////////////////////////////////////////////
        while (resultSet.next()){
//            System.out.println("c1 : "+resultSet.getString(1));
//            System.out.println("boolean : "+newId.equals(resultSet.getString(1)));

            if (newId.equals(resultSet.getString(1))){
                flag = true;
            }

        }return flag;
    }

    @Override
    public String getTotal(String fee) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = null;
        if (fee.equals("ground_fee")){
            resultSet = SQLUtil.execute("SELECT SUM(ground_fee) FROM cost");

        } else if (fee.equals("umpire_fee")) {
            resultSet = SQLUtil.execute("SELECT SUM(umpire_fee) FROM cost");

        } else if (fee.equals("meal_and_other")) {
            resultSet = SQLUtil.execute("SELECT SUM(meal_and_other) FROM cost");

        } else if (fee.equals("equipment_fee")) {
            resultSet = SQLUtil.execute("SELECT SUM(equipment_fee) FROM cost");
        }else {
            resultSet = SQLUtil.execute("SELECT SUM(total) FROM cost");
        }

        if (resultSet.next()){
            //System.out.println("fee : "+resultSet.getDouble(1));
            String vl = String.valueOf(resultSet.getDouble(1));

            return vl;

        }else return null;

    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT CostId FROM cost ORDER BY CostId DESC LIMIT 1");

        if (resultSet.next()){

            return splitMatchId(resultSet.getString(1));

        }else return "CT001";


    }

    @Override
    public boolean delete(String pId) throws SQLException, ClassNotFoundException {
        return false;
    }

    private static String splitMatchId(String Mid) {
        if (Mid != null){
            String[] splint = Mid.split("CT0");

            int id = Integer.parseInt(splint[1]);
            id++;
            if (id > 9){
                return "CT0"+id;
            }else return "CT00"+id;
        } return null;
    }
}
