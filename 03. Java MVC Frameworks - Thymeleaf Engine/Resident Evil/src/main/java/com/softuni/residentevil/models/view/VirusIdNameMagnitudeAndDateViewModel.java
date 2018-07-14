package com.softuni.residentevil.models.view;

import com.softuni.residentevil.etities.enums.Magnitude;
import lombok.Data;

import java.time.LocalDate;

@Data
public final class VirusIdNameMagnitudeAndDateViewModel {

    private String id;

    private String name;

    private Magnitude magnitude;

    private LocalDate releasedOn;
}
