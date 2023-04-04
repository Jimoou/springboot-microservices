package github.Jimoou.departmentservice.Service.implement;

import github.Jimoou.departmentservice.DTO.DepartmentDto;
import github.Jimoou.departmentservice.Entity.Department;
import github.Jimoou.departmentservice.Repository.DepartmentRepository;
import github.Jimoou.departmentservice.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
  private DepartmentRepository departmentRepository;

  @Override
  public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
    // 부서 DTO 개체를 부서 JPA 엔터티로 변환
    Department department =
        new Department(
            departmentDto.getId(),
            departmentDto.getDepartmentName(),
            departmentDto.getDepartmentDescription(),
            departmentDto.getDepartmentCode());

    Department savedDepartment = departmentRepository.save(department);

    DepartmentDto savedDepartmentDto = departmentToConvertDto(savedDepartment);

    return savedDepartmentDto;
  }

  @Override
  public DepartmentDto getDepartmentByCode(String departmentCode) {

    Department department = departmentRepository.findByDepartmentCode(departmentCode);

    DepartmentDto departmentDto = departmentToConvertDto(department);

    return null;
  }

  @Override
  public DepartmentDto departmentToConvertDto(Department department) {
    DepartmentDto departmentDto =
     new DepartmentDto(
      department.getId(),
      department.getDepartmentName(),
      department.getDepartmentDescription(),
      department.getDepartmentCode());

    return departmentDto;
  }
}
