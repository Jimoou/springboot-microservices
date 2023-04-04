package github.Jimoou.departmentservice.Service;

import github.Jimoou.departmentservice.DTO.DepartmentDto;
import github.Jimoou.departmentservice.Entity.Department;

public interface DepartmentService {
  DepartmentDto saveDepartment(DepartmentDto departmentDto);

  DepartmentDto getDepartmentByCode(String code);

  DepartmentDto departmentToConvertDto(Department department);
}
