package com.softuni.residentevil.services;

import com.softuni.residentevil.domain.enums.Authority;
import com.softuni.residentevil.domain.etities.Role;
import com.softuni.residentevil.domain.models.binding.RoleBindingModel;
import com.softuni.residentevil.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl extends BaseService implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(final Validator validator,
                           final ModelMapper modelMapper,
                           final RoleRepository roleRepository) {
        super(validator, modelMapper);
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean create(final Object dto) {
        return super.validateAndCreate(dto, Role.class, this.roleRepository);
    }

    @Override
    public void initRoles() {
        final List<String> authorities = getRoles();

        Arrays.stream(Authority.values())
                .map(Enum::name)
                .forEach(role -> {
                    if (!authorities.contains(role)) {
                        this.create(new RoleBindingModel(role));
                    }
                });
    }

    @Override
    public Role getByAuthority(final String authority) {
        return this.roleRepository.findByAuthority(authority);
    }

    private List<String> getRoles() {
        return roleRepository
                .findAll()
                .stream()
                .map(Role::getAuthority)
                .collect(Collectors.toUnmodifiableList());
    }
}
