package by.diogenoz.hello3.app;

import java.util.Date;

/**
 * Created by diogen on 20.06.15.
 */
public class Match {
    private int id;
    private String hName,aName;
    private Date date;
    private String venue;
    private int hSet,aSet;
    private MatchOptions matchOptions;
    public Match(String hName,String aName, MatchOptions matchOptions) {
        this.hName = hName;
        this.aName = aName;
        this.matchOptions = matchOptions;
        this.date = new Date();
        this.venue="Home";
        this.hSet = 0;
        this.aSet = 0;
    }

    public long getId() {
        return id;
    }

    public String gethName() {
        return hName;
    }

    public void sethName(String hName) {
        this.hName = hName;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public int gethSet() {
        return hSet;
    }

    public void sethSet(int hSet) {
        this.hSet = hSet;
    }

    public int getaSet() {
        return aSet;
    }

    public void setaSet(int aSet) {
        this.aSet = aSet;
    }

    public MatchOptions getMatchOptions() {
        return matchOptions;
    }

    public void setMatchOptions(MatchOptions matchOptions) {
        this.matchOptions = matchOptions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    @Override
    public String toString() {
        return hName+" "+String.valueOf(this.hSet)+" : "+String.valueOf(this.aSet) +" "+aName;
    }
}
