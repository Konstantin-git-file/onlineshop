package ru.effectivemobile.onlineshop.mapper.Impl;

import org.springframework.stereotype.Component;
import ru.effectivemobile.onlineshop.dto.CompanyDto;
import ru.effectivemobile.onlineshop.dto.ProductDto;
import ru.effectivemobile.onlineshop.entity.Company;
import ru.effectivemobile.onlineshop.entity.Product;
import ru.effectivemobile.onlineshop.mapper.CompanyMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public Company companyDtoToCompany(CompanyDto companyDto) {
        if (companyDto == null) {
            return null;
        }

        Company company = new Company();
        company.setCompanyName(companyDto.getCompanyName());
        company.setCompanyDescription(companyDto.getCompanyDescription());
        company.setCompanyLogo(companyDto.getCompanyLogo());
        company.setCommission(companyDto.getCommission());
        company.setActivated(companyDto.isActivated());
        company.setFrozen(companyDto.isFrozen());

//        List<ProductDto> products = companyDto.getProducts()
//                .stream()
//                .map(productMapper::productDtoToProduct)
//                .collect(Collectors.toList());
//        company.setProducts(products);
//
        return company;
    }

    @Override
    public CompanyDto companyToCompanyDto(Company company) {
        if (company == null) {
            return null;
        }

        CompanyDto companyDto = new CompanyDto();
        companyDto.setCompanyName(company.getCompanyName());
        companyDto.setCompanyDescription(company.getCompanyDescription());
        companyDto.setCompanyLogo(company.getCompanyLogo());
        companyDto.setCommission(company.getCommission());
        companyDto.setActivated(company.isActivated());
        companyDto.setFrozen(company.isFrozen());

//        List<ProductDto> productDtos = company.getProducts()
//                .stream()
//                .map(productMapper::productDtoToProduct)
//                .collect(Collectors.toList());
//        company.setProducts(products);
        return companyDto;
    }
}