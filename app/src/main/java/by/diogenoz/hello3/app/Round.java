package by.diogenoz.hello3.app;

/**
 * Created by diogen on 20.06.15.
 */
public class Round {
    private long id;
    private int hScore,aScore,winSide,matchEvent,matchId,currentSet,currentRound;

    public Round(int matchId,int currentSet,int currentRound) {
        this.hScore = 0;
        this.aScore = 0;
        this.matchId = matchId;
        this.currentSet = currentSet;
        this.currentRound = currentRound;
        this.winSide = 0;
        this.matchEvent = 0;

    }

    public Round(int matchId,int currentSet,int currentRound,int winSide,int matchEvent) {
        this.hScore = 0;
        this.aScore = 0;
        this.matchId = matchId;
        this.currentSet = currentSet;
        this.currentRound = currentRound;
        this.winSide = winSide;
        this.matchEvent = matchEvent;

    }


    public long getId() {
        return id;
    }

    public void addHScore(int hscore) {
        this.hScore = hscore;
    }

    public void addAScore(int ascore) {
        this.aScore = ascore;
    }

    public int getHScore() {
        return this.hScore;
    }

    public int getAScore() {
        return this.aScore;
    }

    public int getMatchId(){
        return this.matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public int getCurrentSet() {
        return currentSet;
    }

    public void setCurrentSet(int currentSet) {
        this.currentSet = currentSet;
    }

    public int getCurrentRound() {
        return currentRound;
    }

    public void setCurrentRound(int currentRound) {
        this.currentRound = currentRound;
    }

    public int getMatchEvent() {
        return matchEvent;
    }

    public void setMatchEvent(int matchEvent) {
        this.matchEvent = matchEvent;
    }

    public int getWinSide() {
        return winSide;
    }

    public void setWinSide(int winSide) {
        this.winSide = winSide;
    }

    @Override
    public String toString() {
        return String.valueOf(this.hScore)+" : "+String.valueOf(this.aScore);
    }
}
