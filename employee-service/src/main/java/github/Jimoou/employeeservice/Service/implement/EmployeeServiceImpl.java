package github.Jimoou.employeeservice.Service.implement;

import github.Jimoou.employeeservice.DTO.APIResponseDto;
import github.Jimoou.employeeservice.DTO.DepartmentDto;
import github.Jimoou.employeeservice.DTO.EmployeeDto;
import github.Jimoou.employeeservice.Entity.Employee;
import github.Jimoou.employeeservice.Exception.ResourceNotFoundException;
import github.Jimoou.employeeservice.Repository.EmployeeRepository;
import github.Jimoou.employeeservice.Service.APIClient;
import github.Jimoou.employeeservice.Service.EmployeeService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private EmployeeRepository employeeRepository;
  private ModelMapper modelMapper;
  private APIClient apiClient;

  @Override
  public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
    Employee employee = modelMapper.map(employeeDto, Employee.class);

    Employee savedEmployee = employeeRepository.save(employee);

    EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);

    return savedEmployeeDto;
  }

  @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
  @Override
  public APIResponseDto getEmployeeById(Long employeeId) {
    Employee employee =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

    DepartmentDto departmentDto = apiClient.getDepartment(employee.getDepartmentCode());

    EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
    APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto);

    return apiResponseDto;
  }

  /*fallbackMethod*/
  public APIResponseDto getDefaultDepartment(Long employeeId, Exception e) {
    Employee employee =
     employeeRepository
      .findById(employeeId)
      .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));

    /*default Department return*/
    DepartmentDto departmentDto = new DepartmentDto();
    departmentDto.setDepartmentCode("RD001");
    departmentDto.setDepartmentName("R&D Department");
    departmentDto.setDepartmentDescription("Research and Development Department");

    EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
    APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto);

    return apiResponseDto;
  }
}
