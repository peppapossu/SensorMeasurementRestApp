package ru.kir.sm.sensormeasurementrestapp.service;

import ru.kir.sm.sensormeasurementrestapp.dto.UserDto;
import ru.kir.sm.sensormeasurementrestapp.security.User;

import java.util.Optional;

public interface UserService {

    void createUser(UserDto userDto);

}
