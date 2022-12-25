package database.tables;

public class Performance extends TableRow {
    public Integer idPer;
    public String perName;
    public Integer showCount;

    public Performance(Integer idPer, String perName, Integer showCount) {
        this.idPer = idPer;
        this.perName = perName;
        this.showCount = showCount;
    }

    public Performance(int idPer) {
        this.idPer = idPer;
    }

    public Integer getIdPer() {
        return idPer;
    }

    public void setIdPer(Integer idPer) {
        this.idPer = idPer;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public Integer getShowCount() {
        return showCount;
    }
    public void setShowCount(Integer showCount) {
        this.showCount = showCount;
    }
}
