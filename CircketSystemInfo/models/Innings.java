package CircketSystemInfo.models;

import java.util.List;
import java.util.Map;

public class Innings {
    private List<Ball> balls;
    private Map<Player, PlayerStats> playerStats;
    private Team bowlingTeam;
    private int score;
    private Team battingTeam;
    private int wickets;
}
