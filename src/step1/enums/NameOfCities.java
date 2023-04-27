package step1.enums;

public enum NameOfCities {
    BERLIN("Берлин"), KIEV("Киев"), NEW_YORK("Нью-Йорк"), ASTANA("Астана"),
    JAKARTA("Джакарта"), PEKIN("Пекин"), OSH("Ош");
    private String value;

    NameOfCities(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
