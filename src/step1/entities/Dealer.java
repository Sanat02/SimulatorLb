package step1.entities;

import java.util.Random;

public class Dealer {
    private double loadCapacity;
    private  int speed;
    private double money;

    public Dealer(int loadCapacity) {
        Random random = new Random();
        this.loadCapacity = loadCapacity;
        money = random.nextInt(500, 1000);
        speed = random.nextInt(1, 6);
    }

    public void setSpeed(int speed) {
        this.speed = speed;
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
        loadCapacity = (int) (loadCapacity - m);
    }

    public void addMoney(double m) {
        money = money + m;
    }
    public void subSpeed(int m){speed=speed-m;}
    public void addSpeed(int m){speed=speed+m;}

    public void setMoney(double money) {
        this.money = money;
    }
}
