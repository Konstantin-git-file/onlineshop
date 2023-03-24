package ru.effectivemobile.onlineshop.exception;

import lombok.Getter;

@Getter
public class OnlineShopSystemException extends RuntimeException {

    private ErrorEnum errorEnum;

    public OnlineShopSystemException(ErrorEnum code) {
        super(code.getDescription());
        this.errorEnum = code;
    }
}
