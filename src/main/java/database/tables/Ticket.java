package database.tables;

public class Ticket extends TableRow {
    public Integer idTicket;
    public Integer idShow;
    public Integer idUser;
    public Integer rowVal;
    public Integer seat;
    public Double price;

    public Ticket(Integer idTicket, Integer idShow, Integer idUser, Integer rowVal, Integer seat, Double price) {
        this.idTicket = idTicket;
        this.idShow = idShow;
        this.idUser = idUser;
        this.rowVal = rowVal;
        this.seat = seat;
        this.price = price;
    }

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    public Integer getIdShow() {
        return idShow;
    }

    public void setIdShow(Integer idShow) {
        this.idShow = idShow;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getRowVal() {
        return rowVal;
    }

    public void setRowVal(Integer rowVal) {
        this.rowVal = rowVal;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
