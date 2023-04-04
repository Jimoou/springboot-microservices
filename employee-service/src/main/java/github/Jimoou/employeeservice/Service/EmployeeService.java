package github.Jimoou.employeeservice.Service;

import github.Jimoou.employeeservice.DTO.EmployeeDto;
import github.Jimoou.employeeservice.Entity.Employee;

public interface EmployeeService {
  EmployeeDto saveEmployee(EmployeeDto employeeDto);

  EmployeeDto getEmployeeById(Long employeeId);

  EmployeeDto employeeToConvertDto(Employee employee);
}
