package com.softuni.residentevil.services;

import com.softuni.residentevil.models.binding.VirusAddEditBindingModel;
import com.softuni.residentevil.models.view.VirusIdNameMagnitudeAndDateViewModel;

import java.util.List;

public interface VirusService extends Creatable {

    VirusAddEditBindingModel getById(final String id);

    void removeById(final String id);

    List<VirusIdNameMagnitudeAndDateViewModel> getSimpleView();

    boolean update(final VirusAddEditBindingModel virusDto);
}
