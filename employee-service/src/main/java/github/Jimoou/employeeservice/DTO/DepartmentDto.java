package github.Jimoou.employeeservice.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
  private Long id;
  private String departmentName;
  private String departmentDescription;
  private String departmentCode;
}
