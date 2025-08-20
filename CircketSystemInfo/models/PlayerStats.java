package CircketSystemInfo.models;

public class PlayerStats {
    private int wickets;
    private int runs;
    private int ballsPlayed;

    public int getWicket() {
        return wickets;
    }

    public void setWicket(int wicket) {
        this.wickets = wicket;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public void setBallsPlayed(int ballsPlayed) {
        this.ballsPlayed = ballsPlayed;
    }

    public void updateRuns(int runs) {
        this.runs = runs;
    }

    public void incrementBallsPlayed() {
        ballsPlayed++;
    }

    public void incrementWickets() {
        wickets++;
    }
}
