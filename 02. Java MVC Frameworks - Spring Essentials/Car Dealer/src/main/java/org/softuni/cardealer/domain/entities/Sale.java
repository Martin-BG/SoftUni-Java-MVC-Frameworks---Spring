package org.softuni.cardealer.domain.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double discount;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    public Double evaluatePriceModifier() {
        return 1.0 - this.discount;
    }
}
