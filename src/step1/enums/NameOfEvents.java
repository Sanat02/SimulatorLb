package step1.enums;

public enum NameOfEvents {
    ORDINARY_DAY("Обычный день"), RAIN("Дождь"), SMOOTH_ROAD("Ровная дорога"), BROKEN_WHEELS("Сломалось колесо"),
    RIVER("Река"), MEETING_PEOPLE("Встретил местного"), BURGLARS("Разбойники большой дороги"), ROADSIDE_INN("Придорожный трактир"),
    SPOILED_PRODUCT("Товар испортился");
    private String value;

    NameOfEvents(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
