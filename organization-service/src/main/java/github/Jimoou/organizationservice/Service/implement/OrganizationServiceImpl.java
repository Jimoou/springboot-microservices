package github.Jimoou.organizationservice.Service.implement;

import github.Jimoou.organizationservice.DTO.OrganizationDto;
import github.Jimoou.organizationservice.Entity.Organization;
import github.Jimoou.organizationservice.Exception.ResourceNotFoundException;
import github.Jimoou.organizationservice.Repository.OrganizationRepository;
import github.Jimoou.organizationservice.Service.OrganizationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

  private OrganizationRepository organizationRepository;
  private ModelMapper modelMapper;

  @Override
  public OrganizationDto saveOrganization(OrganizationDto organizationDto) {
    Organization organization = modelMapper.map(organizationDto, Organization.class);

    Organization savedOrganization = organizationRepository.save(organization);

    OrganizationDto savedOrganizationDto =
        modelMapper.map(savedOrganization, OrganizationDto.class);

    return savedOrganizationDto;
  }

  @Override
  public OrganizationDto getOrganizationByCode(String organizationCode) {
    Organization organization =
        organizationRepository
            .findByOrganizationCode(organizationCode)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException(
                        "Organization", "Organization-Code", organizationCode));
    OrganizationDto organizationDto = modelMapper.map(organization, OrganizationDto.class);
    return organizationDto;
  }
}
