package by.diogenoz.hello3.app;

import android.content.Context;

/**
 * Created by diogen on 25.06.15.
 */
public class Game {
    private String hName;
    private String aName;
    private int hScore;
    private int aScore;
    private int hSet;
    private int aSet;
    private int currentRound;
    private int currentSet;
    private MatchOptions matchOptions;
    private MatchStat[] matchStat;
    private RoundAdapter roundAdapter;
    private MatchAdapter matchAdapter;
    private int id;

    public Game(String hName, String aName, MatchOptions matchOptions,Context context) {
        this.hName = hName;
        this.aName = aName;
        this.hScore = 0;
        this.aScore = 0;
        this.hSet = 0;
        this.aSet = 0;
        this.matchOptions = matchOptions;
        this.matchStat=new MatchStat[6];
        this.matchStat[0] = new MatchStat();
        this.currentSet = 0;
        this.currentRound = 0;
        roundAdapter=new RoundAdapter(context);
        matchAdapter=new MatchAdapter(context);
        Match match=new Match(this.hName,this.aName,this.matchOptions);
        this.id = matchAdapter.addItem(match);
    }

    public int gethScore() {
        return roundAdapter.getRound(this.id,this.currentSet,this.getCurrentRound()).getHScore();
        //return hScore;
    }
    public int getaScore() {
        return roundAdapter.getRound(this.id,this.currentSet,this.getCurrentRound()).getAScore();
        //return aScore;
    }

    public int getaSet() {
        return aSet;
    }



    public int gethSet() {
        return hSet;
    }

    public String getaName() {
        return aName;
    }

    public String gethName() {
        return hName;
    }

    public int getCurrentRound(){
        return this.currentRound;
    }

    public void addMatchEvent(int winSide, int matchEvent) {
        this.currentRound += 1;

        if (winSide == 1) {

            switch (matchEvent) {
                case 1:
                    this.matchStat[this.currentSet].addhAces();
                    break;
                case 2:
                    this.matchStat[this.currentSet].addhErrSrv();
                    break;
                case 3:
                    this.matchStat[this.currentSet].addhBlocks();
                    break;
                case 4:
                    this.matchStat[this.currentSet].addhErrAtt();
                    break;
                case 5:
                    this.matchStat[this.currentSet].addhAtt1();
                    break;
                case 6:
                    this.matchStat[this.currentSet].addhAtt2();
                    break;
                case 7:
                    this.matchStat[this.currentSet].addhAtt3();
                    break;
                case 8:
                    this.matchStat[this.currentSet].addhAtt4();
                    break;
                case 9:
                    this.matchStat[this.currentSet].addhAtt5();
                    break;
                case 10:
                    this.matchStat[this.currentSet].addhAtt6();
                    break;
                default:
                    ;
                    break;
            }
            this.hScore += 1;

        }

        if (winSide == 2) {

            switch (matchEvent) {
                case 1:
                    this.matchStat[this.currentSet].addaAces();
                    break;
                case 2:
                    this.matchStat[this.currentSet].addaErrSrv();
                    break;
                case 3:
                    this.matchStat[this.currentSet].addaBlocks();
                    break;
                case 4:
                    this.matchStat[this.currentSet].addaErrAtt();
                    break;
                case 5:
                    this.matchStat[this.currentSet].addaAtt1();
                    break;
                case 6:
                    this.matchStat[this.currentSet].addaAtt2();
                    break;
                case 7:
                    this.matchStat[this.currentSet].addaAtt3();
                    break;
                case 8:
                    this.matchStat[this.currentSet].addaAtt4();
                    break;
                case 9:
                    this.matchStat[this.currentSet].addaAtt5();
                    break;
                case 10:
                    this.matchStat[this.currentSet].addaAtt6();
                    break;
                default:
                    ;
                    break;
            }
            this.aScore += 1;



        }

        Round round=new Round(this.id,this.currentSet,this.currentRound);
        round.addHScore(this.hScore);
        round.addAScore(this.aScore);
        round.setWinSide(winSide);
        round.setMatchEvent(matchEvent);
        roundAdapter.addItem(round);
        this.isSetFinished();
    }

    private void isSetFinished() {
        if (this.hSet>this.matchOptions.getMaxWinSets() || this.aSet>this.matchOptions.getMaxWinSets()) {
            if (this.isMatchFinish()) {
                this.finishMatch();
            }

        } else {
            if (((this.hScore >= this.matchOptions.getSetLength()) || (this.aScore >= this.matchOptions.getSetLength())) && (Math.abs(this.hScore - this.aScore) > 1)) {
                this.finishSet();
            }
        }
    }

    private void finishSet() {
        if (this.hScore == this.matchOptions.getSetLength()) {
            this.hSet += 1;
        }
        if (this.aScore == this.matchOptions.getSetLength()) {
            this.aSet += 1;
        }
        this.hScore = 0;
        this.aScore = 0;
        this.currentSet += 1;
        this.matchStat[this.currentSet]=new MatchStat();

    }


    private boolean isMatchFinish(){
        if((((this.hScore>=this.matchOptions.getLastSetLength())||(this.aScore>=this.matchOptions.getLastSetLength()))&&(Math.abs(this.hScore-this.aScore)>1)))
        {
            return true;
        } else
        {
            return false;
        }
    }
    private void finishMatch(){

    }
    public MatchStat getMatchStat(){
        return this.matchStat[this.currentSet];
    }
    public void onDestroy(){
        roundAdapter.onDestroy();
        matchAdapter.onDestroy();
    }
}


