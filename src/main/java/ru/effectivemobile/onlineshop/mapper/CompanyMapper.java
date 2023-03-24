package ru.effectivemobile.onlineshop.mapper;

import org.mapstruct.*;
import ru.effectivemobile.onlineshop.dto.CompanyDto;
import ru.effectivemobile.onlineshop.entity.Company;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")

public interface CompanyMapper {
    
    Company companyDtoToCompany(CompanyDto companyDto);

    CompanyDto companyToCompanyDto(Company company);

}
