package step1.enums;

public enum ProductType {
    MEAT("мясо"), DRIED_FOOD("сухофрукты"), GRAIN("зерно"),
    FLOUR("мука"), TEXTILE("ткани"), DYE("текстиль");
    private String value;

    ProductType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
