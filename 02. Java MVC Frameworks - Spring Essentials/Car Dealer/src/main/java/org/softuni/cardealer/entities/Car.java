package org.softuni.cardealer.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;

    private String model;

    private Long travelledDistance;

    @OneToMany(
            mappedBy = "car",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private Set<Sale> sales = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "parts_cars",
            joinColumns = {@JoinColumn(name = "part_id")},
            inverseJoinColumns = {@JoinColumn(name = "car_id")})
    private Set<Part> parts = new HashSet<>();
}
