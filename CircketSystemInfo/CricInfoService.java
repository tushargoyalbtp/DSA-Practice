package CircketSystemInfo;

import CircketSystemInfo.observer.MatchObserver;
import CircketSystemInfo.repository.MatchRepository;
import CircketSystemInfo.repository.PlayerRepository;

public class CricInfoService {
    private PlayerRepository playerRepository;
    private MatchRepository matchRepository;
    private static CricInfoService instance;

    public static synchronized CricInfoService getInstance(){
        if(instance == null){
            instance = new CricInfoService();
        }
        return instance;
    }

    public void subscribeToMatch(String matchId, MatchObserver matchObserver){

    }
}
