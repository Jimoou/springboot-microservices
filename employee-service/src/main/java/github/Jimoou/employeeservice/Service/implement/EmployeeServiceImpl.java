package github.Jimoou.employeeservice.Service.implement;

import github.Jimoou.employeeservice.DTO.EmployeeDto;
import github.Jimoou.employeeservice.Entity.Employee;
import github.Jimoou.employeeservice.Repository.EmployeeRepository;
import github.Jimoou.employeeservice.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private EmployeeRepository employeeRepository;

  @Override
  public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
    Employee employee =
        new Employee(
            employeeDto.getId(),
            employeeDto.getFirstName(),
            employeeDto.getLastName(),
            employeeDto.getEmail());
    Employee savedEmployee = employeeRepository.save(employee);

    EmployeeDto savedEmployeeDto = employeeToConvertDto(savedEmployee);

    return savedEmployeeDto;
  }

  @Override
  public EmployeeDto getEmployeeById(Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId).get();

    EmployeeDto employeeDto = employeeToConvertDto(employee);

    return employeeDto;
  }

  @Override
  public EmployeeDto employeeToConvertDto(Employee employee) {
    EmployeeDto employeeDto =
        new EmployeeDto(
            employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail());
    return employeeDto;
  }
}
