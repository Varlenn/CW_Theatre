package database.tables;

public class User extends TableRow {
    public static User authorizedUser;
    TableRow user;

    private int idUser;
    private String userLn;
    private String userFn;
    private int ticketsCount;
    private boolean discount;
    private int userRole;
    private String login;
    private String password;

    public User(int idUser, String userLn, String userFn, int ticketsCount, boolean discount, int userRole, String login, String password) {
        this(userLn, userFn, login, password);

        this.idUser = idUser;
        this.ticketsCount = ticketsCount;
        this.discount = discount;
        this.userRole = userRole;
    }

    public User(String userLn, String userFn, String login, String password) {
        this.userLn = userLn;
        this.userFn = userFn;
        this.login = login;
        this.password = password;
    }

    public User(int ticketsCount) {
        this.ticketsCount = ticketsCount;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserLn() {
        return userLn;
    }

    public void setUserLn(String userLn) { this.userLn = userLn; }

    public String getUserFn() {
        return userFn;
    }

    public void setUserFn(String userFn) {
        this.userFn = userFn;
    }

    public int getTicketsCount() {
        return ticketsCount;
    }

    public void setTicketsCount(int ticketsCount) {
        this.ticketsCount = ticketsCount;
    }

    public boolean getDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String returnUserName() {
        String [] userFn = authorizedUser.getUserFn().split(" ");
        return userFn[0] + " " + authorizedUser.getUserLn();
    }
}
