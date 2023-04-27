package step1;

import java.util.Random;

public class Dealer {
    private double loadCapacity;
    private final int speed;
    private double money;

    Dealer(int loadCapacity) {
        Random random = new Random();
        this.loadCapacity = loadCapacity;
        money = random.nextInt(500, 1000);
        speed = random.nextInt(1, 6);
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public int getSpeed() {
        return speed;
    }

    public double getMoney() {
        return money;
    }

    public void subMoney(double m) {
        money = money - m;
    }

    public void subWeight(double m) {
        loadCapacity = loadCapacity - m;
    }

    public void addMoney(double m) {
        money = money + m;
    }
}
