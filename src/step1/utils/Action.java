package step1.utils;
import step1.entities.City;
import step1.entities.Dealer;
import step1.entities.Event;
import step1.entities.Product;
import java.util.*;

public class Action {
    private List<Product> listOfProducts;
    private Dealer dealer;
    private City city;
    private Event event;
    private double startDistance;
    private int startSpeed;

    Action() {
        listOfProducts = new ArrayList<>();
        city = new City();
        startDistance = city.getDistance();
        dealer = new Dealer(300);
        startSpeed = dealer.getSpeed();
        startSpeed = dealer.getSpeed();
    }

    public void startAction() {
        int day = 1;
        System.out.printf("Город назначения:%s ,Расстояние:%s%n", city.getDestinationCity().getValue(), startDistance);
        System.out.printf("Количество денег:%s ,Вмещаемость:%s ,Начальная скорость:%s%n", dealer.getMoney(), dealer.getLoadCapacity(), dealer.getSpeed());
        double startMoney = dealer.getMoney();
        while (dealer.getMoney() > 0 && dealer.getLoadCapacity() > 0) {
            Product product = new Product();
            dealer.subMoney(product.priceOfProduct());
            dealer.subWeight(product.getWeight());
            if (dealer.getMoney() > 0 && dealer.getLoadCapacity() > 0) {
                listOfProducts.add(product);
            }
        }
        double sum = 0;
        System.out.println("__________________________________________________________________________________");
        for (int i = 0; i < listOfProducts.size(); i++) {
            sum += listOfProducts.get(i).priceOfProduct();
            System.out.println(listOfProducts.get(i));
        }
        System.out.println("__________________________________________________________________________________");
        double remainMoney = startMoney - sum;
        System.out.println("Оставшиеся деньги:" + remainMoney);
        System.out.println(".....................Начинается поездка.....................");
        dealer.setMoney(remainMoney);
        while (city.getDistance() > 0) {
            System.out.printf("---ДЕНЬ %s---%n", day);
            if (listOfProducts.size() == 0) {
                System.out.println("У вас не осталось товара.Поэтому останавливаемся!");
                break;
            }
            dealer.setSpeed(startSpeed);
            event = new Event(listOfProducts, dealer, city);
            event.startEvent();
            city.subDistance(dealer.getSpeed());

            System.out.printf("Скорость:%s ,Расстояние:%s%n", dealer.getSpeed(), city.getDistance());
            System.out.println("Money:" + dealer.getMoney());
            System.out.println();
            day++;
        }
        double totalMoney = 0;
        if (!listOfProducts.isEmpty()) {
            System.out.println("__________________________________________________________________________________");
            for (int i = 0; i < listOfProducts.size(); i++) {
                System.out.printf("тип товара:%s, качество:%s, конечная цена:%s%n", listOfProducts.get(i).getGoodType().getValue(),
                        listOfProducts.get(i).getQualityOfGood().getValue(), listOfProducts.get(i).getFinalPrice());
                totalMoney += listOfProducts.get(i).getFinalPrice();
            }
            System.out.println("__________________________________________________________________________________");

            System.out.printf("Начальная общая цена товаров :%s ,Конечная цена:%s,Дни:%s%n", startMoney - remainMoney, totalMoney, day);
        } else {
            System.out.printf("Потратили:%s ,Заработали:%s,Дни:%s%n", dealer.getMoney(), startMoney, day);
        }

        String s = (totalMoney) - (startMoney - remainMoney) > 0 ? "Торговец получил прибыль!" : "Торговец НЕ получил прибыль!";
        System.out.println(s);
        System.out.println(".....................Поездка закончилась.....................");
    }

}
