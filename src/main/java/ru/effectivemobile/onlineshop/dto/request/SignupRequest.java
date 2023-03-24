package ru.effectivemobile.onlineshop.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.effectivemobile.onlineshop.entity.ERole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(max = 250)
  private String email;

  @JsonFormat(shape = JsonFormat.Shape.OBJECT)
  private List<ERole> role;

  @NotBlank
  @Size(min = 6, max = 100)
  private String password;
}
