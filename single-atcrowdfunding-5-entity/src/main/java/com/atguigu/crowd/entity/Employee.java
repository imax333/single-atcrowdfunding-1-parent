package com.atguigu.crowd.entity;

public class Employee {
    private Integer empId;

    private String empName;

    private Integer empAge;

    private Double salary;
    
    

    public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(Integer empId, String empName, Integer empAge, Double salary) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empAge = empAge;
		this.salary = salary;
	}

	public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public Integer getEmpAge() {
        return empAge;
    }

    public void setEmpAge(Integer empAge) {
        this.empAge = empAge;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empAge=" + empAge + ", salary=" + salary + "]";
	}
    
}