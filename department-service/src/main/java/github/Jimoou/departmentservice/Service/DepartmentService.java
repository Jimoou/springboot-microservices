package github.Jimoou.departmentservice.Service;

import github.Jimoou.departmentservice.DTO.DepartmentDto;

public interface DepartmentService {
  DepartmentDto saveDepartment(DepartmentDto departmentDto);

  DepartmentDto getDepartmentByCode(String code);
}
