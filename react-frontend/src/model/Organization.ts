class Organization {
  id: number;
  organizationName: string;
  organizationDescription: string;
  organizationCode: string;
  createdDate: string;
  constructor(
    id: number,
    organizationName: string,
    organizationDescription: string,
    organizationCode: string,
    createdDate: string
  ) {
    this.id = id;
    this.organizationName = organizationName;
    this.organizationCode = organizationCode;
    this.organizationDescription = organizationDescription;
    this.createdDate = createdDate;
  }
}
export default Organization;
