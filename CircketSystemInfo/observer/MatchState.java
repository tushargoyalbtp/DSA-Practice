package CircketSystemInfo.observer;

import CircketSystemInfo.models.Ball;
import CircketSystemInfo.models.Match;

public interface MatchState {

    void startNextinnings(Match match);

    void processBall(Match match, Ball ball);
}
