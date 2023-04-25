package step1;

import java.util.Random;

public class Dealer {
    private int loadCapacity;
    private int speed;
    private int money;
    private Random random;

    Dealer(int loadCapacity) {
        random = new Random();
        this.loadCapacity=loadCapacity;
    }

    public int getLoadCapacity() {
        return loadCapacity;
    }

    public int getSpeed() {
        speed = random.nextInt(3, 6);
        return speed;
    }

    public int getMoney() {
        money = random.nextInt(500, 1000);
        return money;
    }
}
