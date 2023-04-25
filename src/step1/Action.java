package step1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Action {
    private City city;
    private Event event;
    private NameOfCities destinationCity;
    private NameOfEvents choiceEvent;
    private int distance;
    private Dealer dealer;
    private List<Product> listOfProducts;

    public void startAction() {
        listOfProducts = new ArrayList<>();
        city = new City();
        event = new Event();
        destinationCity = city.getDestinationCity();
        distance = city.getDistance();
        System.out.printf("Город назначения:%s ,Расстояние:%s%n", destinationCity, distance);
        dealer = new Dealer(200);
        System.out.printf("Количество денег:%s ,Скорость:%s%n", dealer.getMoney(), dealer.getLoadCapacity());

       if(dealer.getMoney()>0&&dealer.getLoadCapacity()>0)
       {
//            Product product=new Product();
//            listOfProducts.add(product);
//            System.out.println(product);
           System.out.println("jj");
        }
        System.out.println(listOfProducts.size());


    }

}
