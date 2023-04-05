package github.Jimoou.departmentservice.Repository;

import github.Jimoou.departmentservice.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
  Optional<Department> findByDepartmentCode(String departmentCode);
}
