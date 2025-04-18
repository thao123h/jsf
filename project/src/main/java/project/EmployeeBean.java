package project;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.UUID;

@Named
@RequestScoped
public class EmployeeBean {
    private List<Employee> employees;
    private Employee selectedEmployee = new Employee();

  private   EmployeeDao dao = new EmployeeDao();
  private String message;
    public List<Employee> getEmployees() {
      
        employees = dao.getEmployees();
        return employees;
    }
    public String delete() {
    	if(dao.employeeExists(selectedEmployee.getEmployeeCode(), null)) {
    		message = "Employee doesn't exsist!";
    	}
       
       if( dao.delete(selectedEmployee.getEmployeeCode())) {
    	   message = " Delete employee successfully";   
       }
       else message = "Fail to delete employee";
       return "employeeList.xhtml?faces-redirect=true";
    }
    public String add() {
    	if (dao.employeeExists(null, selectedEmployee.getEmployeeName())) {
    		message = "An employee with this name already exists!";
    		 return "employeeList.xhtml";
    	}
    	selectedEmployee.setEmployeeCode(UUID.randomUUID().toString().substring(0, 8));
        if( selectedEmployee != null && dao.add(selectedEmployee)) {
        	message = " Add employee successfully!";
        	employees = dao.getEmployees();  
        }
        else message = " Fail to add employee!";
        return "employeeList.xhtml";
     }
    public String goToUpdatePage() { 	
   	 return "updateEmployee.xhtml";   
    }
//    public String update() {
//    	if
//     }
   




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
