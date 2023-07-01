package com.example.samplebot.data;

public enum Country {
    SEBRIA("Сербия"),
    HORVATIA("Хорватия"),
    ITALY("Италия"),
    BRITAN("Великобритания"),
    TURKEY("Турция"),
    ARMENY("Армения"),
    OAE("ОАЭ"),
    FRANCE("Франция"),
    KIPR("Кипр"),
    IZRAIL("Израиль");

    public String description;

    Country(String description) {
        this.description = description;
    }
}
