package ru.kir.sm.sensormeasurementrestapp.mapper;

import org.mapstruct.Mapper;
import ru.kir.sm.sensormeasurementrestapp.dto.UserDto;
import ru.kir.sm.sensormeasurementrestapp.security.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
