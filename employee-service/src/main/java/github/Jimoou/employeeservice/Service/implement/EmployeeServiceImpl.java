package github.Jimoou.employeeservice.Service.implement;

import github.Jimoou.employeeservice.DTO.APIResponseDto;
import github.Jimoou.employeeservice.DTO.DepartmentDto;
import github.Jimoou.employeeservice.DTO.EmployeeDto;
import github.Jimoou.employeeservice.DTO.OrganizationDto;
import github.Jimoou.employeeservice.Entity.Employee;
import github.Jimoou.employeeservice.Exception.ResourceNotFoundException;
import github.Jimoou.employeeservice.Repository.EmployeeRepository;
import github.Jimoou.employeeservice.Service.DepartmentAPI;
import github.Jimoou.employeeservice.Service.EmployeeService;
import github.Jimoou.employeeservice.Service.OrganizationAPI;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private EmployeeRepository employeeRepository;
  private ModelMapper modelMapper;
  private DepartmentAPI departmentAPI;
  private OrganizationAPI organizationAPI;

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

    DepartmentDto departmentDto = departmentAPI.getDepartment(employee.getDepartmentCode());
    OrganizationDto organizationDto =
        organizationAPI.getOrganization(employee.getOrganizationCode());

    EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
    APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto, organizationDto);

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

    /*default Organization return*/
    OrganizationDto organizationDto = new OrganizationDto();
    organizationDto.setOrganizationCode("NA001");
    organizationDto.setOrganizationName("Naver");
    organizationDto.setOrganizationDescription("IT Company");

    EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);
    APIResponseDto apiResponseDto = new APIResponseDto(employeeDto, departmentDto, organizationDto);

    return apiResponseDto;
  }
}
