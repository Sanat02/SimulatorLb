package step2;

import step2.enums.NameOfEvents;

import java.util.Random;

public class Event {
    private final Random random;

    public Event() {
        random = new Random();
    }

    private int getRandomNumber() {
        return random.nextInt(NameOfEvents.values().length);
    }

    public NameOfEvents getElement() {
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
}
