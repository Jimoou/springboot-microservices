import axios from "axios";

const BASE_URL = "http://localhost:9191/api";
const API = {
  EmployeeService: `${BASE_URL}/employees`,
  DepartmentService: `${BASE_URL}/departments`,
  OrganizationService: `${BASE_URL}/organizations`,
};
export default API;

export const fetchEmployeeData = async (employeeId: number) => {
  const response = await axios.get(`${API.EmployeeService}/${employeeId}`);
  return response;
};

export const fetchDepartmentData = async (departmentCode: string) => {
  const response = await axios.get(
    `${API.DepartmentService}/${departmentCode}`
  );
  return response;
};

export const fetchOrganizationData = async (organizationCode: string) => {
  const response = await axios.get(
    `${API.OrganizationService}/${organizationCode}`
  );
  return response;
};
