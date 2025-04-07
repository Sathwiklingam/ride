package com.gdc.ride_management.entity;

import com.gdc.ride_management.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ride_requests", schema = "gdc-db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
public class RideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(
            name = "ride_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_request_ride_id")
    )
    private Ride ride; // The ride for which the request was made

    @ManyToOne
    @JoinColumn(
            name = "sender_id",
            referencedColumnName = "id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_request_sender_id")
    )
    private User sender; // The user who is sending goods

    @Column(name = "goods_weight", nullable = false)
    private Integer goodsWeight; // Weight of goods in kg

    @Column(name = "request_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @Builder.Default
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();
}

