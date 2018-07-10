package com.softuni.residentevil.etities.converters;

import com.softuni.residentevil.etities.enums.Magnitude;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class MagnitudeAttributeConverter implements AttributeConverter<Magnitude, String> {

    @Override
    public String convertToDatabaseColumn(final Magnitude magnitude) {
        return (magnitude == null ? null : magnitude.getNormalizedName());
    }

    @Override
    public Magnitude convertToEntityAttribute(final String dbData) {
        return (dbData == null ? null : Magnitude.fromNormalizedName(dbData));
    }
}
