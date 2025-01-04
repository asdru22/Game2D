package game.stat;


import game.entity.Entity;

public abstract class Stat {

    protected int amount;
    protected Entity entity;

    public Stat(int amount) {
        this.amount = amount;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public int getAmount(){
        return amount;
    }

    public void add(int amount){
        this.amount += amount;
    }
}
