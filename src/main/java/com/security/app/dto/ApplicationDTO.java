package com.security.app.dto;

import lombok.Data;

@Data
public class ApplicationDTO{

    private Long carId;
    private String category;
    private String name;
    private String surname;
    private String patronymic;
    private String passport;
    private Integer mobile;
    private Integer amountOfDays;

}
