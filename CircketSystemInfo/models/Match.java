package CircketSystemInfo.models;

import CircketSystemInfo.ENUM.MatchStatus;
import CircketSystemInfo.observer.MatchFormatStrategy;
import CircketSystemInfo.observer.MatchState;

import java.util.List;

public class Match {
    private String id;
    private List<Innings> innings;
    private Team team1;
    private MatchStatus currentStatus;
    private Team team2;
    private String resultMessage;
    private MatchFormatStrategy formatStrategy;
    private MatchState currentState;
    private Team winner;


}
