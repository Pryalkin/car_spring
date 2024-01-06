package com.security.app.enumeration;

public enum MachineDrive {

    REAR_WHEEL_DRIVE("Заднеприводный"),
    FRONT_WHEEL_DRIVE("Переднеприводный"),
    ALL_WHEEL_DRIVE("Полноприводный");

    private String wheel;

    MachineDrive(String wheel) {
        this.wheel = wheel;
    }

    public String getWheel() {
        return wheel;
    }

}
