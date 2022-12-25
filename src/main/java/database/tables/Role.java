package database.tables;

public class Role extends TableRow {
    public Integer idPer;
    public Integer idActor;
    public String role;

    public Role(Integer idRole, Integer idActor, String role) {
        this.idPer = idRole;
        this.idActor = idActor;
        this.role = role;
    }

    public Integer getIdPer() {
        return idPer;
    }

    public void setIdPer(Integer idPer) {
        this.idPer = idPer;
    }

    public Integer getIdActor() {
        return idActor;
    }

    public void setIdActor(Integer idActor) {
        this.idActor = idActor;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
