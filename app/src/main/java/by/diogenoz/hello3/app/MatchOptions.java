package by.diogenoz.hello3.app;

/**
 * Created by diogen on 25.06.15.
 */
public class MatchOptions {
    private int maxWinSets;
    private int setLength;
    private int lastSetLength;
    public MatchOptions(int maxWinSets,int setLength,int lastSetLength){
        this.maxWinSets=maxWinSets;
        this.setLength=setLength;
        this.lastSetLength=lastSetLength;
    }

    public int getMaxWinSets() {
        return maxWinSets;
    }


    public int getSetLength() {
        return setLength;
    }

    public int getLastSetLength() {
        return lastSetLength;
    }


    public void onDestroy() {

    }
}
