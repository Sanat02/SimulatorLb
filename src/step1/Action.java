package step1;

import java.util.Random;

public class Action {
    private City city;
    private Event event;
    private NameOfCities choiceCity;
    private NameOfEvents choiceEvent;

    public void startAction() {
        for (int i = 0; i < 3; i++) {
            city = new City();
            event=new Event();
            choiceCity = city.getElement();
            choiceEvent=event.getElement();
            System.out.println(choiceCity);
            System.out.println(choiceEvent);
        }
    }

}
