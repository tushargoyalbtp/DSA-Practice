package CircketSystemInfo.repository;

import CircketSystemInfo.models.Player;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class PlayerRepository {

    HashMap<String, Player> players = new HashMap<>();

    void savePlayers(Player player){
        players.putIfAbsent("1",player);
    }

    Optional<Player> findPlayerById(String playerId){
        return null;
    }

}
