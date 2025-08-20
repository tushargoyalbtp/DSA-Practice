package CircketSystemInfo.models;

import CircketSystemInfo.ENUM.PlaverRole;

public class Player {
    private String id;
    private PlaverRole plaverRole;
    private String name;
    private PlayerStats playerStats;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlaverRole getPlaverRole() {
        return plaverRole;
    }

    public void setPlaverRole(PlaverRole plaverRole) {
        this.plaverRole = plaverRole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public void setPlayerStats(PlayerStats playerStats) {
        this.playerStats = playerStats;
    }
}
