package com.gdc.ride_management.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "fares", schema = "gdc-db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fare {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

   @OneToOne
    @JoinColumn(name = "ride_request_id", nullable = false, unique = true,
            foreignKey = @ForeignKey(name = "fk_fare_request"))
    private RideRequest rideRequest;

    @Column(name = "distance_km", nullable = false)
    private Double distanceInKm;

    @Column(name = "weight_kg", nullable = false)
    private Double weightInKg;

    @Column(name = "total_fare", nullable = false)
    private Double totalFare;

    @Column(name = "currency", nullable = false)
    private String currency;
}
