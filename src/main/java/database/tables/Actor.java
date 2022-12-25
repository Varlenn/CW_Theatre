package database.tables;

public class Actor extends TableRow {
    public String actorName;
    public String actorLn;
    public String actorFn;
    public String perName;
    public Integer idActor;

    public Actor(Integer idActor, String actorLn, String actorFn) {
        this.actorLn = actorLn;
        this.actorFn = actorFn;
        this.idActor = idActor;
    }

    public Actor(String actorName, String perName) {
        this.actorName = actorName;
        this.perName = perName;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getActorLn() {
        return actorLn;
    }

    public void setActorLn(String actorLn) {
        this.actorLn = actorLn;
    }

    public String getActorFn() {
        return actorFn;
    }

    public void setActorFn(String actorFn) {
        this.actorFn = actorFn;
    }

    public Integer getIdActor() {
        return idActor;
    }

    public void setIdActor(Integer idActor) {
        this.idActor = idActor;
    }
}
