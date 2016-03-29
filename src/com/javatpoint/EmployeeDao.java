package com.javatpoint;  
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;  
import org.springframework.orm.hibernate3.HibernateTemplate;

public class EmployeeDao {  /*
	private JdbcTemplate jdbcTemplate;  

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {  
		this.jdbcTemplate = jdbcTemplate;  

		System.out.println("in setJdbcTemplate "+jdbcTemplate);
	}  

	public int saveEmployee(Employee e){  
		String query="insert into employee values( '"+e.getId()+"','"+e.getName()+"','"+e.getSalary()+"')";  
				return jdbcTemplate.update(query);  
	}  
	public int updateEmployee(Employee e){  
		String query="update employee set name='"+e.getName()+"',salary='"+e.getSalary()+"' where id='"+e.getId()+"' ";  
				return jdbcTemplate.update(query);  
	}  
	public int deleteEmployee(Employee e){  
		String query="delete from employee where id='"+e.getId()+"' ";  
		return jdbcTemplate.update(query);  
	}  
 */


	HibernateTemplate template;  
	public void setTemplate(HibernateTemplate template) {  
		this.template = template;  
	}  
	//method to save employee  
	public void saveEmployee(Employee e){  
		template.save(e);  
	}  
	//method to update employee  
	public void updateEmployee(Employee e){  
		template.update(e);  
	}  
	//method to delete employee  
	public void deleteEmployee(Employee e){  
		template.delete(e);  
	}  
	//method to return one employee of given id  
	public Employee getById(int id){  
		Employee e=(Employee)template.get(Employee.class,id);  
		return e;  
	}  
	//method to return all employees  
	public List<Employee> getEmployees(){  
		List<Employee> list=new ArrayList<Employee>();  
		list=template.loadAll(Employee.class);  
		return list;  
	}  

}  