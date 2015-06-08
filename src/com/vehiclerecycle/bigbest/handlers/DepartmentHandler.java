package com.vehiclerecycle.bigbest.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vehiclerecycle.bigbest.entities.Department;
import com.vehiclerecycle.bigbest.service.DepartmentService;

@SessionAttributes(value={"department"})
@RequestMapping(value="department")
@Controller
public class DepartmentHandler {

	@Autowired
	private DepartmentService departmentService;
	@Autowired(required=true)
	private HttpSession session;
	
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public String getAll(Map<String, Object> map) {
			List<Department> departments=departmentService.getAll();
			map.put("departments", departments);
			return "dept-all";
	
	}
	

	@ResponseBody
	@RequestMapping(value="/getdepartments",method=RequestMethod.GET)
	public Map<String, Object> getDepartments(
			@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="deptName",required=false) String departmentName,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows
			) {
		Department department=new Department();
		if(id!=null){
			department.setId(id);
		}
		if(departmentName!=null){
			department.setDeptName(departmentName);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		List<Department> departments=departmentService.getByInfo(department);
		map.put("total", departments.size());
		map.put("rows", departments.subList((page-1)*rows, (departments.size()>page*rows)?(page*rows):departments.size()));			
		return map;		
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addInput(Map<String, Object> map) {
		map.put("department",new Department());
		return "dept-input";
	}

	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String editInput(@PathVariable("id") Integer id,Map<String, Object> map) {
		map.put("department", departmentService.getOneById(id));
		return "dept-input";
	}

	@ResponseBody
	@RequestMapping(value="/getedit/{id}",method=RequestMethod.GET)
	public Department getEditById(@PathVariable("id") Integer id) {
		return departmentService.getOneById(id);
	}
	

	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(Department department) {	
		try {
			departmentService.saveOrUpdate(session,department);
			return "success";
		} catch (Exception e) {
			return e.toString();
		}		
	}

	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		Department department=new Department();
		department.setId(id);
		departmentService.delete(session,department);
		return "success";
	}
}
