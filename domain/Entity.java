package domain;

public abstract class Entity {
    private int id;

    public Entity() {
        this.id = 0;
    }

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
