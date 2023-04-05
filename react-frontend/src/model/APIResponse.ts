import Department from "./Department";
import Employee from "./Employee";
import Organization from "./Organization";

class APIResponse {
  employee: Employee;
  departmentDto: Department;
  organizationDto: Organization;
  constructor(
    employee: Employee,
    departmentDto: Department,
    organizationDto: Organization
  ) {
    this.employee = employee;
    this.departmentDto = departmentDto;
    this.organizationDto = organizationDto;
  }
}
export default APIResponse;
