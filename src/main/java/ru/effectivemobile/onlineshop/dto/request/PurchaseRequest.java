package ru.effectivemobile.onlineshop.dto.request;

import lombok.Data;
import lombok.Getter;
import ru.effectivemobile.onlineshop.dto.ProductDto;
import ru.effectivemobile.onlineshop.dto.UserDto;

import java.io.Serializable;

@Data
@Getter
public class PurchaseRequest implements Serializable {
    private UserDto user;
    private ProductDto product;
    private int quantity;

}
