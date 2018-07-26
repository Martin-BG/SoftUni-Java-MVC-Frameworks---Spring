package com.softuni.residentevil.services;

import com.softuni.residentevil.domain.enums.Authority;
import com.softuni.residentevil.domain.etities.Role;
import com.softuni.residentevil.domain.etities.User;
import com.softuni.residentevil.domain.models.view.UserViewModel;
import com.softuni.residentevil.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl extends BaseService implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(final Validator validator,
                           final ModelMapper modelMapper,
                           final UserRepository userRepository,
                           final RoleService roleService,
                           final BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(validator, modelMapper);
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected <T> T mapDtoToEntity(final Object dto, final Class<T> entityClass) {
        final T user = super.map(dto, entityClass);

        ((User) user).setPassword(this.bCryptPasswordEncoder.encode(((User) user).getPassword()));

        Set<Role> authorities = new HashSet<>();

        if (this.userRepository.findAll().isEmpty()) {
            this.roleService.initRoles();
            authorities.add(this.roleService.getByAuthority(Authority.ROOT.name()));
            authorities.add(this.roleService.getByAuthority(Authority.ADMIN.name()));
            authorities.add(this.roleService.getByAuthority(Authority.MODERATOR.name()));
            authorities.add(this.roleService.getByAuthority(Authority.USER.name()));
        } else {
            authorities.add(this.roleService.getByAuthority(Authority.USER.name()));
        }

        ((User) user).setAuthorities(authorities);

        return user;
    }

    @Override
    public boolean create(final Object dto) {
        return super.validateAndCreate(dto, User.class, this.userRepository);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public List<UserViewModel> getAll() {
        return this.userRepository
                .findAll()
                .stream()
                .map(user -> {
                    UserViewModel model = super.map(user, UserViewModel.class);
                    Authority highestAuthority = user
                            .getAuthorities()
                            .stream()
                            .map(role -> Authority.valueOf(role.getAuthority()))
                            .max(Comparator.reverseOrder())
                            .orElse(null);
                    model.setHighestAuthority(highestAuthority);
                    return model;
                })
                .collect(Collectors.toList());
    }
}
