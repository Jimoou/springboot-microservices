package github.Jimoou.employeeservice.Service.implement;

import github.Jimoou.employeeservice.DTO.EmployeeDto;
import github.Jimoou.employeeservice.Entity.Employee;
import github.Jimoou.employeeservice.Repository.EmployeeRepository;
import github.Jimoou.employeeservice.Service.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
  private EmployeeRepository employeeRepository;
  private ModelMapper modelMapper;

  @Override
  public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
    Employee employee = modelMapper.map(employeeDto, Employee.class);

    Employee savedEmployee = employeeRepository.save(employee);

    EmployeeDto savedEmployeeDto = modelMapper.map(savedEmployee, EmployeeDto.class);

    return savedEmployeeDto;
  }

  @Override
  public EmployeeDto getEmployeeById(Long employeeId) {
    Employee employee = employeeRepository.findById(employeeId).get();

    EmployeeDto employeeDto = modelMapper.map(employee, EmployeeDto.class);

    return employeeDto;
  }
}
