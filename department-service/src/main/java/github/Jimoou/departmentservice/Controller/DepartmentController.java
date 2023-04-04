package github.Jimoou.departmentservice.Controller;

import github.Jimoou.departmentservice.DTO.DepartmentDto;
import github.Jimoou.departmentservice.Service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/departments")
@AllArgsConstructor
@Tag(name = "부서 API")
public class DepartmentController {

  private DepartmentService departmentService;

  @Operation(summary = "Save 부서")
  @PostMapping
  public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto departmentDto) {
    DepartmentDto savedDepartment = departmentService.saveDepartment(departmentDto);
    return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
  }

  @Operation(summary = "Get 부서")
  @GetMapping("{department-code}")
  public ResponseEntity<DepartmentDto> getDepartment(@PathVariable("department-code") String departmentCode) {
    DepartmentDto departmentDto = departmentService.getDepartmentByCode(departmentCode);
    return new ResponseEntity<>(departmentDto, HttpStatus.OK);
  }
}
