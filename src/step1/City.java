package step1;

import java.util.Random;

public class City {
    private Random random;
    private NameOfCities destinationCity;
    private int distance;

    public City() {
        random = new Random();
    }

    private int getRandomNumber(int num1,int num2) {
        Random random = new Random();
        return random.nextInt(num1,num2);
    }

    private NameOfCities getElement() {
        int num = getRandomNumber(0,7);
        NameOfCities element1 = NameOfCities.ASTANA; //пока что просто проинициализируем
        switch (num) {
            case 0:
                element1 = NameOfCities.ASTANA;
                break;
            case 1:
                element1 = NameOfCities.BERLIN;
                break;
            case 2:
                element1 = NameOfCities.PEKIN;
                break;
            case 3:
                element1 = NameOfCities.JAKARTA;
                break;
            case 4:
                element1 = NameOfCities.KIEV;
                break;
            case 5:
                element1 = NameOfCities.NEW_YORK;
                break;
            case 6:
                element1 = NameOfCities.OSH;
                break;
            default:
                System.out.println("Some error happened!");
        }
        return element1;
    }

    public NameOfCities getDestinationCity() {
        destinationCity=getElement();
        return destinationCity;
    }

    public int getDistance() {
        distance=getRandomNumber(50,100);
        return distance;
    }
}
