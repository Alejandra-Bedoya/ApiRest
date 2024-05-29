package com.api.rest.apiRest.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TouristPackage")
public class TouristPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageId;

    private String destinationPlace;
    private String category;
    private String description;
    private Float price;
    private Integer durationDays;
    private Integer availableSpots;




}
