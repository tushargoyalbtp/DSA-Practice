package CircketSystemInfo.models;

import CircketSystemInfo.ENUM.ExtraType;

public class Ball {

    private String commentary;
    private Player bowledBy;
    private Player facedBy;
    private ExtraType extraType;
    private int runScored;
    private int ballNumber;
    private Wicket wicket;

    public boolean isBoundary(){
        return runScored == 4 || runScored == 6;
    }

    public String getCommentary() {
        return commentary;
    }

    public ExtraType getExtraType() {
        return extraType;
    }

}
