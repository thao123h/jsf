package project.bean;

import javax.inject.Named;

import project.dao.EmployeeDao;
import project.model.Employee;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.validator.ValidatorException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import java.util.List;
import java.util.UUID;

@Named
@SessionScoped
public class EmployeeBean implements Serializable {

	private boolean showForm = false;
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

	public void toggleForm() {
		this.showForm = !this.showForm;

	}

	public void delete() {
		if (dao.findEmployee(selectedEmployee.getEmployeeCode(), null) == null) {
			message = "Employee doesn't exsist!";
		} else if (dao.delete(selectedEmployee.getEmployeeCode())) {
			employees = dao.getEmployees();
			message = " Delete employee successfully";

		} else
			message = "Fail to delete employee";

	}

	public void add() {
		selectedEmployee.setEmployeeCode(UUID.randomUUID().toString().substring(0, 8));
		 if (selectedEmployee.getDateOfBirth() != null) {
			   LocalDate today = LocalDate.now();
		        LocalDate birthDate = selectedEmployee.getDateOfBirth();  
		        Period period = Period.between(birthDate, today);
		        int age = period.getYears();  
		        selectedEmployee.setEmployeeAge(age);	 
		 }
		       
		if (selectedEmployee != null && dao.add(selectedEmployee)) {
			employees = dao.getEmployees();
			toggleForm();
			selectedEmployee = new Employee();
		}
	}

	public void update() {
		Employee existing = dao.findEmployee(null, selectedEmployee.getEmployeeName());

		if (existing != null && !selectedEmployee.getEmployeeCode().equalsIgnoreCase(existing.getEmployeeCode())) {
			message = "An employee with this name already exists!";
		} else if (dao.update(selectedEmployee)) {
			employees = dao.getEmployees();
			toggleForm();
		} else {
			message = "Fail to update employee";
		}

	}

	// Validate
	public void validateName(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name cannot be empty!", null));
		}
		String valueString = value.toString();
		if (valueString.length() < 2 || valueString.length() > 50) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"The name must be between 2 and 50 characters!", null));
		}
		if (!valueString.matches("^[\\p{L}\\s'.-]+$")) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"The name cannot contain number or special characters other than apostrophes, periods, and hyphens.",
					null));
		}

	}

	public void validateDate(FacesContext context, UIComponent component, LocalDate value) throws ValidatorException {
	    if (value == null) {
	        throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date cannot be empty!", null));
	    }

	    LocalDate today = LocalDate.now();
	    
	    if (!value.isBefore(today)) {
	        throw new ValidatorException(
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Date must be before today.", null));
	    }

	    int age = Period.between(value, today).getYears();
	 
	    if (age < 18) {
	        throw new ValidatorException(
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Age must be at least 18.", null));
	    }
	    if (age > 100) {
	        throw new ValidatorException(
	                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Age must be less than 100.", null));
	    }
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

	public boolean isShowForm() {
		return showForm;
	}

	public void setShowForm(boolean showForm) {
		this.showForm = showForm;
	}

}
