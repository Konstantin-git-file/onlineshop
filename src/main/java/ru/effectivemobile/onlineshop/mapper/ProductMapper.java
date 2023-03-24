package ru.effectivemobile.onlineshop.mapper;

import org.mapstruct.*;
import ru.effectivemobile.onlineshop.dto.ProductDto;
import ru.effectivemobile.onlineshop.entity.Product;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")

public interface ProductMapper {

    Product productDtoToProduct(ProductDto productDto);

    ProductDto productToProductDto(Product product);

}
