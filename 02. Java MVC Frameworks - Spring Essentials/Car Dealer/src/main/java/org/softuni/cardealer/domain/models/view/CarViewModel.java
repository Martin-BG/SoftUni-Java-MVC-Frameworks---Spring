package org.softuni.cardealer.domain.models.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarViewModel {

    private String make;

    private String model;

    private Long travelledDistance;
}
