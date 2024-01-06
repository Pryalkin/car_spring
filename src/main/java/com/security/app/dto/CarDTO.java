package com.security.app.dto;

import lombok.Data;

@Data
public class CarDTO {

    private String category;
    private String brand;
    private Integer engineCapacity;
    private Double volumeCapacity;
    private String machineDrive;
    private String color;
    private Double consumption;
    private Integer numberOfSeats;
    private Double price;
    private String image;
}
