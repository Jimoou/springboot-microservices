package github.Jimoou.employeeservice.Service;

import github.Jimoou.employeeservice.DTO.DepartmentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "DEPARTMENT-SERVICE")
public interface APIClient {
  // Get 부서
  @GetMapping("api/departments/{department-code}")
  DepartmentDto getDepartment(@PathVariable("department-code") String departmentCode);
}
