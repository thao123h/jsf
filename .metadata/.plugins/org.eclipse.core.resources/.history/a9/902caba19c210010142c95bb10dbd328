package project;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Named
@RequestScoped
public class EmployeeBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Employee> employees;
	private Employee selectedEmployee = new Employee();
	private EmployeeDao dao = new EmployeeDao();
	private String message;

	public List<Employee> getEmployees() {
		if (employees == null) {
			employees = dao.getEmployees();
		}
		return employees;
	}

	public String delete() {
		if (dao.findEmployee(selectedEmployee.getEmployeeCode(), null) == null) {
			message = "Employee doesn't exsist!";
		} else if (dao.delete(selectedEmployee.getEmployeeCode())) {
			message = " Delete employee successfully";
		} else
			message = "Fail to delete employee";
		return "employeeList.xhtml?faces-redirect=true";
	}

	public String add() {
		if (dao.findEmployee(null, selectedEmployee.getEmployeeName()) != null) {
			message = "An employee with this name already exists!";
			return "employeeList.xhtml";
		}
		selectedEmployee.setEmployeeCode(UUID.randomUUID().toString().substring(0, 8));
		if (selectedEmployee != null && dao.add(selectedEmployee)) {
			message = " Add employee successfully!";
			employees = dao.getEmployees();
		} else
			message = " Fail to add employee!";
		return "employeeList.xhtml";
	}

	public String goToUpdatePage() {
		return "updateEmployee.xhtml";
	}

	public String update() {
		Employee existing = dao.findEmployee(null, selectedEmployee.getEmployeeName());

		if (existing != null && !selectedEmployee.getEmployeeCode().equalsIgnoreCase(existing.getEmployeeCode())) {
			message = "An employee with this name already exists!";
		} else if (dao.update(selectedEmployee)) {
			return "employeeList.xhtml?faces-redirect=true";
		} else {
			message = "Fail to update employee";
		}

		return "updateEmployee.xhtml";
	}

	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}

	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
