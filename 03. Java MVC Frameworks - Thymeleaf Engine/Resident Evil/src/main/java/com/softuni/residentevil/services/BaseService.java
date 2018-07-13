package com.softuni.residentevil.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

abstract class BaseService {

    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    protected BaseService(final Validator validator,
                          final ModelMapper modelMapper) {
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    protected <T> boolean isValid(T t) {
        return t != null && this.validator.validate(t).isEmpty();
    }

    protected <T> Set<ConstraintViolation<T>> validate(T t) {
        return this.validator.validate(t);
    }

    protected <T> T map(Object source, Class<T> clazz) {
        return this.modelMapper.map(source, clazz);
    }

    protected <T, D> boolean validateAndCreate(final T dto,
                                               Class<D> entityClass,
                                               JpaRepository repository) {
        if (!this.isValid(dto)) {
            return false; // TODO - proper error handling
        }

        final D entity = this.map(dto, entityClass);

        try {
            repository.saveAndFlush(entity);
        } catch (Throwable e) {
            return false;   // TODO - proper error handling
        }

        return true;
    }
}
