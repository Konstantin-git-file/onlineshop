package ru.effectivemobile.onlineshop.dto.responce;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class UserInfoResponse implements Serializable {
  private Long id;
  private String username;
  private String email;
  private List<String> roles;

  public UserInfoResponse(Long id, String username, String email, List<String> roles) {}
}
