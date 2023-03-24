package ru.effectivemobile.onlineshop.dto.userReg;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class UserRegistrationDtoRs implements Serializable {

  private Long userId;
}
