package github.Jimoou.departmentservice.Service.implement;

import github.Jimoou.departmentservice.DTO.DepartmentDto;
import github.Jimoou.departmentservice.Entity.Department;
import github.Jimoou.departmentservice.Exception.ResourceNotFoundException;
import github.Jimoou.departmentservice.Repository.DepartmentRepository;
import github.Jimoou.departmentservice.Service.DepartmentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
  private DepartmentRepository departmentRepository;
  private ModelMapper modelMapper;

  @Override
  public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
    Department department = modelMapper.map(departmentDto, Department.class);

    Department savedDepartment = departmentRepository.save(department);

    DepartmentDto savedDepartmentDto = modelMapper.map(savedDepartment, DepartmentDto.class);

    return savedDepartmentDto;
  }

  @Override
  public DepartmentDto getDepartmentByCode(String departmentCode) {
    Department department =
        departmentRepository
            .findByDepartmentCode(departmentCode)
            .orElseThrow(
                () ->
                    new ResourceNotFoundException("Department", "Department-Code", departmentCode));
    DepartmentDto departmentDto = modelMapper.map(department, DepartmentDto.class);

    return departmentDto;
  }
}
