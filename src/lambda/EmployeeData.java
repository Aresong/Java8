package lambda;

import java.util.Arrays;
import java.util.List;

public class EmployeeData {
	
	private List<Employee> employees =
		Arrays.asList(
			new Employee(1L, "Tom", 1),
			new Employee(2L, "ttttt", 1),
			new Employee(3L, "Alice", 0)
		);

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
}
