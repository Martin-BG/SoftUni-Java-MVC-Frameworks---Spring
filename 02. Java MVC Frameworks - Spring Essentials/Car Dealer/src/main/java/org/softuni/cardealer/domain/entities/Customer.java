package org.softuni.cardealer.domain.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "customers")
@EqualsAndHashCode(exclude = {"sales"})
public class Customer {

    private static final double PRICE_DISCOUNT_MODIFIER_PERCENTAGE_FOR_YOUNG_DRIVERS = 5.0;
    private static final double PRICE_MODIFIER_FOR_YOUNG_DRIVERS =
            (100.0 - PRICE_DISCOUNT_MODIFIER_PERCENTAGE_FOR_YOUNG_DRIVERS) / 100.0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate birthDate;

    @Column(name = "is_young_driver")
    private Boolean youngDriver;

    @OneToMany(
            mappedBy = "customer",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private Set<Sale> sales = new HashSet<>();

    public Double priceModifier() {
        return this.youngDriver ? PRICE_MODIFIER_FOR_YOUNG_DRIVERS : 1.0;
    }
}
