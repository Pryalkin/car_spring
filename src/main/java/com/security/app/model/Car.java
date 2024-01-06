package com.security.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "car")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Car {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @EqualsAndHashCode.Include
        private Long id;
        private String number;
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
        private String status;
        @OneToMany(
                mappedBy = "car",
                cascade = CascadeType.ALL,
                orphanRemoval = true
        )
        private List<Application> applications = new ArrayList<>();

        public void addApplication(Application application){
                this.applications.add(application);
                application.setCar(this);
        }

}
