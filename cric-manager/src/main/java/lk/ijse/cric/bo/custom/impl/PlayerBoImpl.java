package lk.ijse.cric.bo.custom.impl;

import lk.ijse.cric.bo.custom.PlayerBo;
import lk.ijse.cric.dao.DAOFactory;
import lk.ijse.cric.dao.custom.Impl.PlayerDaoImpl;
import lk.ijse.cric.dao.custom.PlayerDao;
import lk.ijse.cric.dto.playerDto;
import lk.ijse.cric.entity.Player;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerBoImpl implements PlayerBo {

    PlayerDao playerDao = (PlayerDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.PLAYER);
    @Override
    public List<playerDto> getAllPlayers() throws SQLException, ClassNotFoundException {
        ArrayList<Player> players = playerDao.getAll();

        ArrayList<playerDto> playerDtos = new ArrayList<>();

        for (Player player :players) {

            playerDtos.add(new playerDto(
                    player.getPId(),
                    player.getName(),
                    player.getAge(),
                    player.getType()
            ));
        }
        return playerDtos;
     }

    @Override
    public boolean savePlayer(playerDto dto) throws SQLException, ClassNotFoundException {
        //Player player = new Player(dto.getPId(),dto.getPUId(),dto.getName(),dto.getAge(), dto.getType());
        return playerDao.save(new Player(dto.getPId(),dto.getPUId(),dto.getName(),dto.getAge(), dto.getType()));
    }

    @Override
    public boolean updatePlayer(playerDto playerDto) throws SQLException, ClassNotFoundException {

        return playerDao.update(new Player(playerDto.getPId(),playerDto.getName(),playerDto.getAge(),playerDto.getType()));
    }

    @Override
    public String generateNextPlayerId() throws SQLException, ClassNotFoundException {

        return playerDao.generateNewId();
    }

    @Override
    public String getTotalPlayers(String type) throws SQLException, ClassNotFoundException {

        return playerDao.getTotal(type);
    }

    @Override
    public boolean deletePlayer(String pId) throws SQLException, ClassNotFoundException {

        return playerDao.delete(pId);
    }
}
