package net.biboka.transrad.transrad.model;

/**
 * Created by biboka on 2016.11.12..
 */

public class MyMonthlyModel {
    private String Workplace;
    private String hetNap;
    private String HonapNap;
    private String CoWorker;

    public MyMonthlyModel(String workplace, String hetnap, String honapNap, String coWorker) {
        Workplace = workplace;
        hetNap = hetnap;
        HonapNap = honapNap;
        CoWorker = coWorker;
    }

    public String getWorkplace() {
        return Workplace;
    }

    public String getHetNap() {
        return hetNap;
    }

    public String getHonapNap() {
        return HonapNap;
    }

    public String getCoWorker() {
        return CoWorker;
    }

    public void setWorkplace(String workplace) {
        Workplace = workplace;
    }

    public void setDatum(String hetnap) {
        hetNap = hetnap;
    }

    public void setHonapNap(String honapNap) {
        HonapNap = honapNap;
    }

    public void setCoWorker(String coWorker) {
        CoWorker = coWorker;
    }
}
