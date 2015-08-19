package by.diogenoz.hello3.app;

/**
 * Created by diogen on 25.06.15.
 */
public class MatchStat {

    private int hAces;
    private int aAces;

    private int hBlocks;
    private int aBlocks;

    private int hErrSrv;
    private int aErrSrv;

    private int hErrAtt;
    private int aErrAtt;

    private int hAtt1;
    private int aAtt1;

    private int hAtt2;
    private int aAtt2;

    private int hAtt3;
    private int aAtt3;

    private int hAtt4;
    private int aAtt4;

    private int hAtt5;
    private int aAtt5;

    private int hAtt6;
    private int aAtt6;

    public MatchStat(){
        this.hAces=0;
        this.aAces=0;

        this.hBlocks=0;
        this.aBlocks=0;

        this.hErrSrv=0;
        this.aErrSrv=0;

        this.hErrAtt=0;
        this.aErrAtt=0;

        this.hAtt1=0;
        this.aAtt1=0;

        this.hAtt2=0;
        this.aAtt2=0;

        this.hAtt3=0;
        this.aAtt3=0;

        this.hAtt4=0;
        this.aAtt4=0;

        this.hAtt5=0;
        this.aAtt5=0;

        this.hAtt6=0;
        this.aAtt6=0;
    }

    public int gethAces() {
        return hAces;
    }

    public int getaAces() {
        return aAces;
    }

    public int getaAtt1() {
        return aAtt1;
    }

    public int getaAtt2() {
        return aAtt2;
    }

    public int getaAtt3() {
        return aAtt3;
    }

    public int getaAtt4() {
        return aAtt4;
    }

    public int getaAtt5() {
        return aAtt5;
    }

    public int getaAtt6() {
        return aAtt6;
    }

    public int getaBlocks() {
        return aBlocks;
    }

    public int getaErrAtt() {
        return aErrAtt;
    }

    public int getaErrSrv() {
        return aErrSrv;
    }

    public int gethAtt1() {
        return hAtt1;
    }

    public int gethAtt2() {
        return hAtt2;
    }

    public int gethAtt3() {
        return hAtt3;
    }

    public int gethAtt4() {
        return hAtt4;
    }

    public int gethAtt5() {
        return hAtt5;
    }

    public int gethAtt6() {
        return hAtt6;
    }

    public int gethBlocks() {
        return hBlocks;
    }

    public int gethErrAtt() {
        return hErrAtt;
    }

    public int gethErrSrv() {
        return hErrSrv;
    }

    public void addaAces() {
        this.aAces += 1;
    }

    public void addhAces() {
        this.hAces += 1;
    }

    public void addaBlocks() {
        this.aBlocks += 1;
    }
    public void addhBlocks() {
        this.hBlocks += 1;
    }

    public void addhErrSrv() {
        this.hErrSrv += 1;
    }

    public void addaErrSrv() {
        this.aErrSrv += 1;
    }

    public void addhErrAtt() {
        this.hErrAtt += 1;
    }

    public void addaErrAtt() {
        this.aErrAtt += 1;
    }
    public void addhAtt1() {
        this.hAtt1 += 1;
    }

    public void addaAtt1() {
        this.aAtt1 += 1;
    }

    public void addhAtt2() {
        this.hAtt2 += 1;
    }

    public void addaAtt2() {
        this.aAtt2 += 1;
    }

    public void addhAtt3() {
        this.hAtt3 += 1;
    }

    public void addaAtt3() {
        this.aAtt3 += 1;
    }

    public void addhAtt4() {
        this.hAtt4 += 1;
    }

    public void addaAtt4() {
        this.aAtt4 += 1;
    }
    public void addhAtt5() {
        this.hAtt5 += 1;
    }

    public void addaAtt5() {
        this.aAtt5 += 1;
    }
    public void addhAtt6() {
        this.hAtt6 += 1;
    }

    public void addaAtt6() {
        this.aAtt6 += 1;
    }
}
