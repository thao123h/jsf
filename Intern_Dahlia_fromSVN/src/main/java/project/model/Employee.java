package project.model;

import java.util.Date;

public class Employee {
    private String employeeCode;
    private String employeeName;
    private int employeeAge;
    private Date dateOfBirth;
  
	public Employee() {
		super();
	}
	public Employee(String employeeCode, String employeeName, int employeeAge, Date dateOfBirth) {
		super();
		this.employeeCode = employeeCode;
		this.employeeName = employeeName;
		this.employeeAge = employeeAge;
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getEmployeeAge() {
		return employeeAge;
	}
	public void setEmployeeAge(int employeeAge) {
		this.employeeAge = employeeAge;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

    
}
