package CircketSystemInfo.observer;

import CircketSystemInfo.models.Ball;
import CircketSystemInfo.models.Match;

public interface MatchObserver {
    void update(Match match, Ball ball);
}
