package step1;

import java.util.Random;

public class City {
    private Random random;

    public City() {
        random = new Random();
    }

    private int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(NameOfCities.values().length);
    }

    public NameOfCities getElement() {
        int num = getRandomNumber();
        NameOfCities element1 = NameOfCities.ASTANA; //пока что просто проинициализируем
        switch (num) {
            case 0:
                element1 = NameOfCities.ASTANA;
                break;
            case 1:
                element1 = NameOfCities.BERLIN;
                break;
            case 2:
                element1 = NameOfCities.BRAZILIZA;
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
            case 7:
                element1 = NameOfCities.PEKIN;
                break;
            case 8:
                element1 = NameOfCities.TOKIO;
                break;
            case 9:
                element1 = NameOfCities.TOKMOK;
                break;
            default:
                System.out.println("Some error happened!");
        }
        return element1;
    }
}
