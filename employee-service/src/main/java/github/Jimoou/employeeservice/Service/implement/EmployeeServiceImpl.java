package github.Jimoou.employeeservice.Service.implement;

import github.Jimoou.employeeservice.DTO.APIResponseDto;
import github.Jimoou.employeeservice.DTO.DepartmentDto;
import github.Jimoou.employeeservice.DTO.EmployeeDto;
import github.Jimoou.employeeservice.Entity.Employee;
import github.Jimoou.employeeservice.Exception.ResourceNotFoundException;
import github.Jimoou.employeeservice.Repository.EmployeeRepository;
import github.Jimoou.employeeservice.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private EmployeeRepository employeeRepository;

  private RestTemplate restTemplate;
  private ModelMapper modelMapper;

  @Override
  public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
    Employee employee = modelMapper.map(employeeDto, Employee.class);

    Employee savedEmployee = employeeRepository.save(employee);

    EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);

    return savedEmployeeDto;
  }

  @Override
  public APIResponseDto getEmployeeById(Long employeeId) {
    Employee employee =
        employeeRepository
            .findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", employeeId));
    ResponseEntity<DepartmentDto> responseEntity =
        restTemplate.getForEntity(
            "http://localhost:8080/api/departments/" + employee.getDepartmentCode(),
            DepartmentDto.class);

    DepartmentDto departmentDto = responseEntity.getBody();

    EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
    APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto);

    return apiResponseDto;
  }
}
