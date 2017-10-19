package org.probosque.model.json;

public class PageInfoJson {
    private int totalrows;
    private long totalpages;
    private int currentpage;

    public int getTotalrows() {
        return totalrows;
    }

    public void setTotalrows(int totalrows) {
        this.totalrows = totalrows;
    }

    public long getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(long totalpages) {
        this.totalpages = totalpages;
    }

    public int getCurrentpage() {
        return currentpage;
    }

    public void setCurrentpage(int currentpage) {
        this.currentpage = currentpage;
    }

}
