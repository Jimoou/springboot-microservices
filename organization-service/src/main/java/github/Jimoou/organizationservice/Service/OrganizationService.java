package github.Jimoou.organizationservice.Service;

import github.Jimoou.organizationservice.DTO.OrganizationDto;

public interface OrganizationService {
  OrganizationDto saveOrganization(OrganizationDto organizationDto);

  OrganizationDto getOrganizationByCode(String organizationCode);
}
