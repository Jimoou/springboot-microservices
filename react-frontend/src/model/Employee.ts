class Employee {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  departmentCode: string;
  organizationCode: string;

  constructor(
    id: number,
    firstName: string,
    lastName: string,
    email: string,
    departmentCode: string,
    organizationCode: string
  ) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.departmentCode = departmentCode;
    this.organizationCode = organizationCode;
  }
}
export default Employee;
