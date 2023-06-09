package ru.effectivemobile.onlineshop.exception;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ErrorRsDto {
    private int errorCode;
    private String description;
}

