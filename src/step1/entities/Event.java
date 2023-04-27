package step1.entities;

import step1.state.Quality;
import step1.enums.NameOfEvents;

import java.util.*;

public class Event {
    private final Random random;
    private List<Product> listOfProducts;
    private Dealer dealer;
    private City city;


    public Event(List<Product> listOfProducts, Dealer dealer, City city) {
        random = new Random();
        this.listOfProducts = listOfProducts;
        this.dealer = dealer;
        this.city = city;

    }

    private int getRandomNumber() {
        return random.nextInt(NameOfEvents.values().length);
    }

    private NameOfEvents getElement() {
        int num = getRandomNumber();
        NameOfEvents element1 = NameOfEvents.BROKEN_WHEELS; //пока что просто проинициализируем
        switch (num) {
            case 0:
                element1 = NameOfEvents.BROKEN_WHEELS;
                break;
            case 1:
                element1 = NameOfEvents.BURGLARS;
                break;
            case 2:
                element1 = NameOfEvents.MEETING_PEOPLE;
                break;
            case 3:
                element1 = NameOfEvents.ORDINARY_DAY;
                break;
            case 4:
                element1 = NameOfEvents.RAIN;
                break;
            case 5:
                element1 = NameOfEvents.RIVER;
                break;
            case 6:
                element1 = NameOfEvents.ROADSIDE_INN;
                break;
            case 7:
                element1 = NameOfEvents.SMOOTH_ROAD;
                break;
            case 8:
                element1 = NameOfEvents.SPOILED_PRODUCT;
                break;
            default:
                System.out.println("Some error happened!");
        }
        return element1;
    }

    public void startEvent() {
        NameOfEvents currentEvent = getElement();
        System.out.println(currentEvent.getValue());
        if (currentEvent.equals(NameOfEvents.ORDINARY_DAY)) {
            System.out.println("В этот день ничего не происходит!");
        } else if (currentEvent.equals(NameOfEvents.RAIN)) {
            dealer.subSpeed(2);
            if (dealer.getSpeed() < 1) {
                dealer.setSpeed(1);
            }
            int[] arr={0,0,0,1,1,1,1,1,1,1};
            int index=random.nextInt(10);
            if(arr[index]==0)
            {
                System.out.println("Качество продукта испортилась!");
                spoilProduct();
            }
        } else if (currentEvent.equals(NameOfEvents.SMOOTH_ROAD)) {
            dealer.addSpeed(2);
            if (dealer.getSpeed() > 5) {
                dealer.setSpeed(5);
            }
        } else if (currentEvent.equals(NameOfEvents.BROKEN_WHEELS) || currentEvent.equals(NameOfEvents.RIVER)) {
            System.out.println("День ушел в пустую!");
            dealer.setSpeed(0);
        } else if (currentEvent.equals(NameOfEvents.MEETING_PEOPLE)) {
            Random random = new Random();
            int num = random.nextInt(3, 7);
            city.subDistance(num);
            System.out.printf("Вы срезали путь на %s лиг%n", num);
        } else if (currentEvent.equals(NameOfEvents.BURGLARS)) {
            freeFromBurglar();
        } else if (currentEvent.equals(NameOfEvents.ROADSIDE_INN)) {
            sellOrBuy();
        } else {
            spoilProduct();
        }
    }

    private void changeQuality(int num) {
        switch (listOfProducts.get(num).getQualityOfGood()) {
            case NORMAL -> listOfProducts.get(num).setQualityOfGood(step1.state.Quality.LITTLE_SPOILED);
            case LITTLE_SPOILED -> listOfProducts.get(num).setQualityOfGood(step1.state.Quality.HALF_SPOILED);
            case HALF_SPOILED -> listOfProducts.get(num).setQualityOfGood(step1.state.Quality.ALMOST_SPOILED);
            case ALMOST_SPOILED -> listOfProducts.get(num).setQualityOfGood(step1.state.Quality.WHOLE_SPOILED);
        }
    }


    private void freeFromBurglar() {
        Map<Integer, Quality> mp = new TreeMap<>();
        if (dealer.getMoney() > 0) {
            System.out.println("Откупился ДЕНЬГАМИ!");
            dealer.setMoney(0);
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

            for (int i = 0; i < listOfProducts.size(); i++) {
                if (listOfProducts.get(i).getQualityOfGood().equals(mp.get(0))) {
                    index = i;
                    break;
                }
            }
            System.out.println(listOfProducts.get(index));
            listOfProducts.remove(index);
        }
    }

    private void sellOrBuy() {
        Random random = new Random();
        int num = random.nextInt(0, 2);
        if (num == 0) {
            System.out.println("Идет продажа товара.....");
            int rantProductToSell = random.nextInt(listOfProducts.size());
            dealer.addMoney(listOfProducts.get(rantProductToSell).getFinalPrice() / 2);
            dealer.subWeight(listOfProducts.get(rantProductToSell).getWeight());
            System.out.printf("Товар который был продан:[%s %nОбщее количество денег после оплаты ночлега:%s%n",
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
                System.out.printf("НИЧЕГО НЕ КУПИЛИ!Недостаточно средств либо места  для покупки данного товара:%n[%s]%n", p);
            }
        }
    }
    private void spoilProduct()
    {
        int checkSpoiled = 0;
        Random random = new Random();
        int num = random.nextInt(listOfProducts.size());
        System.out.println("Было:" + listOfProducts.get(num));
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
            System.out.println("Стало:" + listOfProducts.get(num));
        }
    }

}
