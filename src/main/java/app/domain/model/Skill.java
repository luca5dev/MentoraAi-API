package app.domain.model;

public enum Skill {

    JAVA(1),
    SPRING(2),
    SQL(3),
    GIT(4),
    DOCKER(5),
    AWS(6),
    ANGULAR(7),
    REACT(8),
    POSTGRESQL(9),
    HTML(10),
    CSS(11);

    private final int id;

    Skill(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return name();
    }

    public String toStringComId() {
        return "(" + id + ") " + name();
    }
}
