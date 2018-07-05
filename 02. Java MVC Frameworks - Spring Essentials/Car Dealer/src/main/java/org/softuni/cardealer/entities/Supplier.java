package org.softuni.cardealer.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Boolean isImporter;

    @OneToMany(
            mappedBy = "supplier",
            fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE
    )
    private Set<Part> parts = new HashSet<>();
}
