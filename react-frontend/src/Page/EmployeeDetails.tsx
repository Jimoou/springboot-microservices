import React, { useEffect, useState } from "react";
import {
  fetchEmployeeData,
  fetchDepartmentData,
  fetchOrganizationData,
} from "../APIConfig";
import {
  Card,
  CardContent,
  Typography,
  CircularProgress,
  Box,
  TextField,
  MenuItem,
  Button,
  InputAdornment,
  Avatar,
  CardHeader,
  Divider,
  Grow,
} from "@mui/material";
import APIResponse from "../model/APIResponse";
import Department from "../model/Department";
import Organization from "../model/Organization";
import { Clear, Domain, Business, Work } from "@mui/icons-material";

export const EmployeeDetails = () => {
  const [apiResponse, setApiResponse] = useState<APIResponse>();
  const [departmentData, setDepartmentData] = useState<Department>();
  const [organizationData, setOrganizationData] = useState<Organization>();
  const [searchCategory, setSearchCategory] = useState("employee");
  const [searchInput, setSearchInput] = useState("");
  const [loading, setLoading] = useState(false);

  const searchCategories = [
    { value: "employee", label: "직원 ID" },
    { value: "department", label: "부서 Code" },
    { value: "organization", label: "회사 Code" },
  ];

  const handleSearch = async () => {
    setLoading(true);
    try {
      if (searchCategory === "employee") {
        const response = await fetchEmployeeData(Number(searchInput));
        setApiResponse(response.data);
      } else if (searchCategory === "department") {
        const response = await fetchDepartmentData(searchInput);
        setDepartmentData(response.data);
      } else if (searchCategory === "organization") {
        const response = await fetchOrganizationData(searchInput);
        setOrganizationData(response.data);
      }
    } catch (error) {
      console.error("Error fetching data:", error);
    }
    setLoading(false);
  };
  const handleReset = () => {
    setSearchInput("");
  };

  if (loading) {
    return (
      <Box
        display="flex"
        justifyContent="center"
        alignItems="center"
        height="100vh"
      >
        <CircularProgress />
      </Box>
    );
  }
  return (
    <Box>
      <Box display="flex" alignItems="center" marginBottom={2}>
        <TextField
          select
          label="항목"
          value={searchCategory}
          onChange={(e) => setSearchCategory(e.target.value)}
          variant="outlined"
          style={{ marginRight: 16 }}
        >
          {searchCategories.map((category) => (
            <MenuItem key={category.value} value={category.value}>
              {category.label}
            </MenuItem>
          ))}
        </TextField>
        <TextField
          label="검색"
          value={searchInput}
          onChange={(e) => setSearchInput(e.target.value)}
          InputProps={{
            endAdornment: (
              <>
                {searchInput && (
                  <InputAdornment position="end">
                    <Clear onClick={handleReset} />
                  </InputAdornment>
                )}
              </>
            ),
          }}
        />
        <Button variant="contained" onClick={handleSearch}>
          검색
        </Button>
      </Box>
      {apiResponse ? (
        <Grow in={true}>
          <Card sx={{ maxWidth: 500, margin: "auto", marginTop: 2 }}>
            <CardHeader
              avatar={
                <Avatar>{apiResponse.employee.firstName.charAt(0)}</Avatar>
              }
              title={`${apiResponse.employee.firstName} ${apiResponse.employee.lastName}`}
              subheader={`이메일: ${apiResponse.employee.email}`}
            />
            <Divider />
            <CardContent>
              <Typography variant="h6" component="div" gutterBottom>
                <Work fontSize="inherit" /> 부서
              </Typography>
              <Typography>
                이름: {apiResponse.departmentDto.departmentName}
              </Typography>
              <Typography>
                코드: {apiResponse.departmentDto.departmentCode}
              </Typography>
              <Typography>
                설명: {apiResponse.departmentDto.departmentDescription}
              </Typography>
            </CardContent>
            <Divider />
            <CardContent>
              <Typography variant="h6" component="div" gutterBottom>
                <Domain fontSize="inherit" /> 조직
              </Typography>
              <Typography>
                이름: {apiResponse.organizationDto.organizationName}
              </Typography>
              <Typography>
                코드: {apiResponse.organizationDto.organizationCode}
              </Typography>
              <Typography>
                설명: {apiResponse.organizationDto.organizationDescription}
              </Typography>
              <Typography>
                창립일: {apiResponse.organizationDto.createdDate}
              </Typography>
            </CardContent>
          </Card>
        </Grow>
      ) : null}
    </Box>
  );
};
