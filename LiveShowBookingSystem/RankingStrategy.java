package LiveShowBookingSystem;

import java.util.List;

public interface RankingStrategy {
    List<ShowSlot> rank(List<ShowSlot> showSlotList);

}
