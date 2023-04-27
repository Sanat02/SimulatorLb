package step2;

import step2.enums.NameOfEvents;
import step2.enums.Quality;

import java.util.*;

public class Action {
    private final City city;
    private final Event event;
    private final NameOfCities destinationCity;
    private final int distance;
    private final Dealer dealer;
    private final List<Product> listOfProducts;
    private int speed;
    private double remainMoney;

    public Action() {
        listOfProducts = new ArrayList<>();
        city = new City();
        event = new Event();
        destinationCity = city.getDestinationCity();
        distance = city.getDistance();
        dealer = new Dealer(300);
        speed = dealer.getSpeed();
    }

    public void startAction() {
        System.out.printf("Город назначения:%s ,Расстояние:%s%n", destinationCity, distance);
        int startSpeed = speed;
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
        for (int i = 0; i < listOfProducts.size(); i++) {
            sum += listOfProducts.get(i).priceOfProduct();
            System.out.println(listOfProducts.get(i));
        }
        remainMoney = startMoney - sum;
        System.out.println("Оставшиеся деньги:" + remainMoney);
        System.out.println("Начинается поездака.......");
        int day = 1;
        while (city.getDistance() > 0) {
            speed = startSpeed;
            NameOfEvents choiceEvent = event.getElement();
            System.out.println(choiceEvent.getValue());
            eventCause(choiceEvent);
            System.out.println("speed:" + speed);
            city.subDistance(speed);
            System.out.println("distance:" + city.getDistance());
            day++;
        }
        int totalPrice = 0;
        System.out.println("Все доставленные продукты с их окончательной ценой:");
        for (int i = 0; i < listOfProducts.size(); i++) {
            System.out.printf("Product name:%s, quality:%s, final price:%s%n", listOfProducts.get(i).getGoodType().getValue(),
                    listOfProducts.get(i).getQualityOfGood().getValue(), listOfProducts.get(i).getFinalPrice());
            totalPrice += listOfProducts.get(i).getFinalPrice();
        }
        System.out.printf("Final price:%s ,Start price:%s,Days:%s%n", totalPrice, startMoney, day);
        String s = totalPrice - startMoney > 0 ? "Торговец получил прибыль!" : "Торговец НЕ получил прибыль!";
        System.out.print(s);
    }

    private void eventCause(NameOfEvents choiceEvent) {
        if (choiceEvent.equals(NameOfEvents.ORDINARY_DAY)) {
            System.out.println("В этот день ничего не происходит!");
        } else if (choiceEvent.equals(NameOfEvents.RAIN)) {
            speed = speed - 2;
            if (speed < 1) {
                speed = 1;
            }

        } else if (choiceEvent.equals(NameOfEvents.SMOOTH_ROAD)) {
            speed = speed + 2;
            if (speed > 5) {
                speed = 5;
            }
        } else if (choiceEvent.equals(NameOfEvents.BROKEN_WHEELS)) {
            speed = 0;
        } else if (choiceEvent.equals(NameOfEvents.RIVER)) {
            speed = 0;
        } else if (choiceEvent.equals(NameOfEvents.MEETING_PEOPLE)) {
            Random random = new Random();
            city.subDistance(random.nextInt(3, 7));
        } else if (choiceEvent.equals(NameOfEvents.BURGLARS)) {
            freeFromBurglar();
        } else if (choiceEvent.equals(NameOfEvents.ROADSIDE_INN)) {
            sellOrBuy();
        } else {
            int checkSpoiled = 0;
            Random random = new Random();
            int num = random.nextInt(listOfProducts.size());
            System.out.println("random product:" + listOfProducts.get(num));
            for (int i = 0; i < listOfProducts.size(); i++) {
                if (listOfProducts.get(i).getQualityOfGood().equals(Quality.WHOLE_SPOILED)) {
                    checkSpoiled++;
                }
            }
            if (checkSpoiled == listOfProducts.size()) {
                city.subDistance(city.getDistance());

            } else {
                while (listOfProducts.get(num).getQualityOfGood().equals(Quality.WHOLE_SPOILED)) {
                    num = random.nextInt(listOfProducts.size());
                }
                changeQuality(num);
                System.out.println("random product:" + listOfProducts.get(num));
            }
        }
    }

    private void changeQuality(int num) {
        switch (listOfProducts.get(num).getQualityOfGood()) {
            case NORMAL -> listOfProducts.get(num).setQualityOfGood(Quality.LITTLE_SPOILED);
            case LITTLE_SPOILED -> listOfProducts.get(num).setQualityOfGood(Quality.HALF_SPOILED);
            case HALF_SPOILED -> listOfProducts.get(num).setQualityOfGood(Quality.ALMOST_SPOILED);
            case ALMOST_SPOILED -> listOfProducts.get(num).setQualityOfGood(Quality.WHOLE_SPOILED);
        }
    }

    private void freeFromBurglar() {
        Map<Integer, Quality> mp = new TreeMap<>();
        if (remainMoney > 0) {
            System.out.println("Откупился ДЕНЬГАМИ!");
        } else {
            System.out.println("Отдали лучший товар разбойникам!");
            int index = 0;
            for (int i = 0; i < listOfProducts.size(); i++) {
                if (listOfProducts.get(i).getQualityOfGood().equals(Quality.NORMAL)) {
                    mp.put(1, Quality.NORMAL);
                } else if (listOfProducts.get(i).getQualityOfGood().equals(Quality.LITTLE_SPOILED)) {
                    mp.put(2, Quality.LITTLE_SPOILED);
                } else if (listOfProducts.get(i).getQualityOfGood().equals(Quality.HALF_SPOILED)) {
                    mp.put(3, Quality.HALF_SPOILED);
                } else if (listOfProducts.get(i).getQualityOfGood().equals(Quality.ALMOST_SPOILED)) {
                    mp.put(4, Quality.ALMOST_SPOILED);
                } else {
                    mp.put(5, Quality.WHOLE_SPOILED);
                }
            }
            System.out.println(mp);
            for (int i = 0; i < listOfProducts.size(); i++) {
                if (listOfProducts.get(i).getQualityOfGood().equals(mp.get(0))) {
                    index = i;
                    break;
                }
            }
            listOfProducts.remove(index);
        }
    }

    private void sellOrBuy() {
        Random random = new Random();
        int num = random.nextInt(0, 2);
        if (num == 0) {
            System.out.println("Идет продажа товара.....");
            int rantProductToSell = random.nextInt(listOfProducts.size());
            dealer.addMoney(listOfProducts.get(rantProductToSell).priceOfProduct() / 2);
            dealer.subWeight(listOfProducts.get(rantProductToSell).getWeight());
            System.out.printf("Товар который был продан:%s%nОбщее количество денег после оплаты ночега%n",
                    listOfProducts.get(rantProductToSell), dealer.getMoney());
            listOfProducts.remove(rantProductToSell);
        } else {
            System.out.println("Идет покупка товара.....");
            Product p = new Product();
            if (p.priceOfProduct() <= dealer.getMoney() && p.getWeight() <= dealer.getLoadCapacity()) {
                dealer.subMoney(p.priceOfProduct());
                dealer.subWeight(p.getWeight());
                listOfProducts.add(p);
                System.out.printf("Вы успешно купили данный товар:%s%nВаши оставшиеся деньги:%s%n", p, dealer.getMoney());
            } else {
                System.out.printf("Недостаточно средств либо места  для покупки данного товара:%n%s", p);
            }
        }
    }
}
