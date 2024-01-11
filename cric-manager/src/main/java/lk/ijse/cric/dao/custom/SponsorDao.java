package lk.ijse.cric.dao.custom;

import lk.ijse.cric.dao.CrudDAO;
import lk.ijse.cric.dao.SuperDAO;
import lk.ijse.cric.entity.Sponsor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SponsorDao extends CrudDAO<Sponsor> {
    //ArrayList<Sponsor> getAll() throws SQLException, ClassNotFoundException;///////////////////////

    //boolean save(Sponsor entity) throws SQLException, ClassNotFoundException;///////////////////

    //ArrayList<Sponsor> search(String value) throws SQLException, ClassNotFoundException;/////////////////////////

    //String generateNewId() throws SQLException, ClassNotFoundException;////////////////
    int getTotal() throws SQLException, ClassNotFoundException;

    Sponsor getBest() throws SQLException, ClassNotFoundException;

}
