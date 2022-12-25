package database.tables;

import java.sql.Date;
import java.sql.Time;

public class Show extends TableRow {
    public String perName;
    public Integer idShow;
    public Integer idPer;
    public String showDate;
    public String showTime;

    public Show(String perName, Date showDate, Time showTime) {
        this.perName = perName;
        this.showDate = showDate.toString();
        this.showTime = showTime.toString();
    }

    public Show(Integer idShow, Integer idPer, Date showDate, Time showTime) {
        this.idShow = idShow;
        this.idPer = idPer;
        this.showDate = showDate.toString();
        this.showTime = showTime.toString();
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Integer getIdShow() {
        return idShow;
    }

    public void setIdShow(Integer idShow) {
        this.idShow = idShow;
    }

    public Integer getIdPer() {
        return idPer;
    }

    public void setIdPer(Integer idPer) {
        this.idPer = idPer;
    }
}
