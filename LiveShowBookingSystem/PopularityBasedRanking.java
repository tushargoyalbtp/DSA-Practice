package LiveShowBookingSystem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PopularityBasedRanking implements RankingStrategy{
    /**
     * @param showSlotList
     * @return
     */
    @Override
    public List<ShowSlot> rank(List<ShowSlot> showSlotList) {
        return showSlotList.stream()
                .sorted(Comparator.comparing((ShowSlot slot) -> slot.getLiveShow()
                                                                .getTotalBookings())
                        .reversed())
                .collect(Collectors.toList());
    }
}
