package ru.effectivemobile.onlineshop.mapper.Impl;

import org.springframework.stereotype.Component;
import ru.effectivemobile.onlineshop.dto.ProductDto;
import ru.effectivemobile.onlineshop.entity.Product;
import ru.effectivemobile.onlineshop.mapper.ProductMapper;

@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product productDtoToProduct(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        Product product = new Product();
        product.setProductName(productDto.getName());
        product.setProductPrice(productDto.getProductPrice());
        product.setReviews(productDto.getReviews());
        product.setRating(productDto.getRating());
        product.setInstock(productDto.getInstock());

        return product;
    }

    @Override
    public ProductDto productToProductDto(Product product) {
        if (product == null) {
            return null;
        }

        ProductDto productDto = new ProductDto();
        productDto.setName(product.getProductName());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setReviews(product.getReviews());
        productDto.setRating(product.getRating());
        productDto.setInstock(product.getInstock());

        return productDto;
    }

}