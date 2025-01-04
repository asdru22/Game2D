package game.stat;

public abstract class CappedStat extends Stat {
    protected int max;

    public CappedStat(int amount) {
        super(amount);
        max = amount;
    }

    public int getMax(){
        return max;
    }
}
