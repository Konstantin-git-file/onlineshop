package ru.effectivemobile.onlineshop.dto.userReg;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserRegistrationDtoRq implements Serializable {

  @Size(min = 1, max = 50)
  @NotNull(message = "Username can't be null")
  private String username;

  @Size(min = 5, max = 100)
  private String email;

  @JsonIgnore
  @NotNull(message = "Password can't be null")
  @Size(min = 6, max = 200, message = "Password should contain 6 to 12 character only")
  private String password;
}
