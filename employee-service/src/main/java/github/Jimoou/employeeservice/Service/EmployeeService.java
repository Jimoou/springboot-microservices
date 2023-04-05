package github.Jimoou.employeeservice.Service;

import github.Jimoou.employeeservice.DTO.APIResponseDto;
import github.Jimoou.employeeservice.DTO.EmployeeDto;

public interface EmployeeService {
  EmployeeDto saveEmployee(EmployeeDto employeeDto);

  APIResponseDto getEmployeeById(Long employeeId);
}
