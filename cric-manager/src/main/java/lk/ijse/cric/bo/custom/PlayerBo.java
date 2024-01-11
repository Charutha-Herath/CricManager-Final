package lk.ijse.cric.bo.custom;

import lk.ijse.cric.bo.SuperBo;
import lk.ijse.cric.dto.playerDto;

import java.sql.SQLException;
import java.util.List;

public interface PlayerBo extends SuperBo {

    List<playerDto> getAllPlayers() throws SQLException, ClassNotFoundException;

    boolean savePlayer(playerDto u001) throws SQLException, ClassNotFoundException;

    boolean updatePlayer(playerDto playerDto) throws SQLException, ClassNotFoundException;

    String generateNextPlayerId() throws SQLException, ClassNotFoundException;

    String getTotalPlayers(String type) throws SQLException, ClassNotFoundException;

    boolean deletePlayer(String pId) throws SQLException, ClassNotFoundException;
}
