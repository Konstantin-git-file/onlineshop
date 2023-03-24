package ru.effectivemobile.onlineshop.dto.responce;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@RequiredArgsConstructor
public class MessageResponse implements Serializable {

  private String message;

  public MessageResponse(String s) {
    this.message = s;
  }
}
