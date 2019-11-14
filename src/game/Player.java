package game;

public class Player {
    private int health;
    private int money;

    public Player() {
        this.health = 100;
        this.money = 100;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
