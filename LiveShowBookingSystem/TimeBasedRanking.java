package LiveShowBookingSystem;

import java.util.Comparator;
import java.util.List;

public class TimeBasedRanking implements RankingStrategy{

    /**
     * @param showSlotList
     * @return
     */
    @Override
    public List<ShowSlot> rank(List<ShowSlot> showSlotList) {
        return showSlotList.stream()
                .sorted(Comparator.comparing(showSlot -> showSlot.getTimeSlot().getStartHour()))
                .toList();
    }
}
