package com.security.app.enumeration;


public enum Category {

    COMFORT_CLASS("Комфорт класс"),
    BUSINESS_CLASS("Бизнес класс"),
    PREMIUM("Премиум"),
    SUV("Внедорожники"),
    CONVERTIBLES("Кабриолеты"),
    SPORT("Спорт"),
    MINIBUSES("Микроавтобусы");

    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
