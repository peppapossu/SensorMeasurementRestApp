package ru.kir.sm.sensormeasurementrestapp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kir.sm.sensormeasurementrestapp.dto.UserDto;
import ru.kir.sm.sensormeasurementrestapp.repositorie.UserRepository;
import ru.kir.sm.sensormeasurementrestapp.security.User;
import ru.kir.sm.sensormeasurementrestapp.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.name());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setRole(userDto.role());

        userRepository.save(user);
    }
}
