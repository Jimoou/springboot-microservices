package github.Jimoou.organizationservice.Controller;

import github.Jimoou.organizationservice.DTO.OrganizationDto;
import github.Jimoou.organizationservice.Service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/organizations")
@AllArgsConstructor
@Tag(name = "회사 API")
public class OrganizationController {
  private OrganizationService organizationService;

  @Operation(summary = "Save 회사")
  @PostMapping
  public ResponseEntity<OrganizationDto> saveOrganization(
      @RequestBody OrganizationDto organizationDto) {
    OrganizationDto savedOrganizationDto = organizationService.saveOrganization(organizationDto);
    return new ResponseEntity<>(savedOrganizationDto, HttpStatus.CREATED);
  }

  @Operation(summary = "Get 회사")
  @GetMapping("{organization-code}")
  public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("organization-code") String organizationCode) {
    OrganizationDto organizationDto = organizationService.getOrganizationByCode(organizationCode);
    return new ResponseEntity<>(organizationDto, HttpStatus.OK);
  }
}
