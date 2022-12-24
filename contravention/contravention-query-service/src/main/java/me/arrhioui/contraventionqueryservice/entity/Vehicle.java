package me.arrhioui.contraventionqueryservice.entity;

import lombok.*;
import me.arrhioui.core.enums.VehicleType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Vehicle {
    @Id
    private String registrationNumber;
    private VehicleType type;
    private String brand;
    private String model;
    private  int  fiscalPower;
    @OneToOne
    private Owner owner;
}
