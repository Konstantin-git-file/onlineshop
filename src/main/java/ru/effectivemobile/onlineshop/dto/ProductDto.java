package ru.effectivemobile.onlineshop.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.effectivemobile.onlineshop.entity.Review;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Getter
@Setter
public class ProductDto implements Serializable {

    @Size(min = 1, max = 50)
    @NotNull(message = "Product's name can't be null")

    private String name;

    private double productPrice;

    private List<Review> reviews;

    private double rating;

    private int instock;
}
