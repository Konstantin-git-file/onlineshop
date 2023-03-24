package ru.effectivemobile.onlineshop.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import ru.effectivemobile.onlineshop.entity.Notification;
import ru.effectivemobile.onlineshop.entity.Order;
import ru.effectivemobile.onlineshop.entity.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@Getter
public class UserDto implements Serializable {

    private Long id;

    @Size(min = 1, max = 50)
    @NotNull(message = "Username can't be null")
    private String username;

    @Size(min = 5, max = 100)
    private String email;

    @JsonIgnore
    @NotNull(message = "Password can't be null")
    @Size(min = 6, max = 200, message = "Password should contain 6 to 12 character only")
    private String password;

    private Set<Role> roles;
    // TODO regex for currency
    private double balance;

    private List<Order> orders;

    private List<Notification> notifications;
}
