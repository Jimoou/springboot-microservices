package github.Jimoou.employeeservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIResponseDto {
  private EmployeeDto employee;
  private DepartmentDto departmentDto;
  private OrganizationDto organizationDto;
}
