package github.Jimoou.employeeservice.Repository;

import github.Jimoou.employeeservice.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
