package ru.effectivemobile.onlineshop.mapper.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.effectivemobile.onlineshop.dto.UserDto;
import ru.effectivemobile.onlineshop.entity.User;
import ru.effectivemobile.onlineshop.mapper.UserMapper;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final PasswordEncoder encoder;

    @Override
    public User userDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }

        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(encoder.encode(userDto.getPassword()));
        user.setRoles(userDto.getRoles());
        user.setOrders(userDto.getOrders());
        user.setNotifications(userDto.getNotifications());
        return user;
    }

    @Override
    public UserDto userToUserDto(User user) {
        if (user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setUsername((user.getUsername()));
        userDto.setEmail(user.getEmail());
        userDto.setBalance(user.getBalance());
        userDto.setRoles(user.getRoles());
        userDto.setOrders(user.getOrders());
        userDto.setNotifications(user.getNotifications());
        return userDto;

//  public List<UserDto> usersToUserDTO (List < User > users) {
//      return users.stream()
//                    .filter(Objects::nonNull)
//                    .map(this::userToUserDto)
//                    .collect(Collectors.toList());
        }
    }
