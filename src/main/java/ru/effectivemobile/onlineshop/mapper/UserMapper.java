package ru.effectivemobile.onlineshop.mapper;

import org.mapstruct.*;
import ru.effectivemobile.onlineshop.dto.UserDto;
import ru.effectivemobile.onlineshop.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);


}
