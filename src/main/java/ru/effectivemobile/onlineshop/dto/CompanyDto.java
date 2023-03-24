package ru.effectivemobile.onlineshop.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
public class CompanyDto implements Serializable {

    @Size(min = 1, max = 50)
    @NotNull(message = "Company's name can't be null")
    private String companyName;

    @Size(min = 1, max = 50)
    @NotNull(message = "Description can't be null")
    private String companyDescription;

    @Size(min = 1, max = 50)
    @NotNull(message = "Company's logo can't be null")
    private String companyLogo;

    private List<ProductDto> products;

    private double commission;

    private boolean isActivated;

    private boolean isFrozen;
}
