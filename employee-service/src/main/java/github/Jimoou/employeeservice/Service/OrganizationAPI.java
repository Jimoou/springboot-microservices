package github.Jimoou.employeeservice.Service;

import github.Jimoou.employeeservice.DTO.OrganizationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORGANIZATION-SERVICE")
public interface OrganizationAPI {
  // Get 회사
  @GetMapping("api/organizations/{organization-code}")
  OrganizationDto getOrganization(@PathVariable("organization-code") String organizationCode);
}
