package com.gdc.ride_management.entity;

import com.gdc.ride_management.enums.RideStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "rides", schema = "gdc-db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;


   @JoinColumn(
            name = "driver_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_ride_driver_id")
    )
     // The driver who created the ride

    @Column(name = "departure_location", nullable = false)
    private String departureLocation;

    @Column(name = "destination_location", nullable = false)
    private String destinationLocation;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    @Column(name = "available_space", nullable = false)
    private Integer availableSpace;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RideStatus status;

    @OneToMany(mappedBy = "ride", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<RideRequest> requests;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}

