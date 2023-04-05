class Department {
  id: number;
  departmentName: string;
  departmentDescription: string;
  departmentCode: string;
  constructor(
    id: number,
    departmentName: string,
    departmentDescription: string,
    departmentCode: string
  ) {
    this.id = id;
    this.departmentName = departmentName;
    this.departmentDescription = departmentDescription;
    this.departmentCode = departmentCode;
  }
}
export default Department;
