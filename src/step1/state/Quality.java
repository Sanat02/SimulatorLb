package step1.state;


public enum Quality {
    NORMAL("нормальное"),
    LITTLE_SPOILED("слегка испорчен"),
    HALF_SPOILED("половина испортилась"),
    ALMOST_SPOILED("почти весь пропал "),
    WHOLE_SPOILED("испорчен в хлам");

    private String value;

    Quality(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}