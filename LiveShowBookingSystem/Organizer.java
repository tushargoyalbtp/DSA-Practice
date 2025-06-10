package LiveShowBookingSystem;

import java.util.ArrayList;
import java.util.List;

public class Organizer extends User {

    private List<LiveShow> organizedShows;

    public Organizer(String userName, String email) {
        super(userName, email);
        this.organizedShows = new ArrayList<>();
    }

    public void addShows(LiveShow liveShow){
        organizedShows.add(liveShow);
    }

    public List<LiveShow> getOrganizedShows(){
        return new ArrayList<>(organizedShows);
    }
}
