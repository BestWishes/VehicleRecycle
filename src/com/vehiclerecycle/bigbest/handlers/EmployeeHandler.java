package com.vehiclerecycle.bigbest.handlers;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;
import static com.vehiclerecycle.bigbest.util.Constant.POSITION;
import static com.vehiclerecycle.bigbest.util.Constant.SESSION_EMPLOYEE;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vehiclerecycle.bigbest.entities.Employee;
import com.vehiclerecycle.bigbest.entities.Position;
import com.vehiclerecycle.bigbest.service.DepartmentService;
import com.vehiclerecycle.bigbest.service.EmployeeService;
import com.vehiclerecycle.bigbest.service.PositionService;

@SessionAttributes(value={"employee"})
@RequestMapping(value="/employee")
@Controller
public class EmployeeHandler {
		
	@Autowired(required=true)
	private EmployeeService employeeService;
	@Autowired(required=true)
	private DepartmentService departmentService;
	@Autowired(required=true)
	private PositionService positionService;
	@Autowired(required=true)
	private HttpSession session;
	@Autowired(required=true)
	private HttpServletRequest  request;
	
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	
	/**
	 * 获取所有的用户
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public String getAll(Map<String, Object> map) {
		if (session.getAttribute(EMPLOYEE_NAME)!=null) {
			List<Employee> employees=employeeService.getAll();
			map.put("employees", employees);
			return "emp-all";
		}else {
			return "${pageContext.request.contextPath}/";
		}
		
	}
	/**
	 * 按条件获取用户
	 * @param id
	 * @param employeeName
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getemployees",method=RequestMethod.GET)
	public Map<String, Object> getEmployees(@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="employeeName",required=false) String employeeName,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows) {
		Employee employee=new Employee();
		if(id!=null){
			employee.setId(id);
		}
		if(employeeName!=null){
			employee.setEmployeeName(employeeName);
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		if (session.getAttribute(EMPLOYEE_NAME)!=null) {
			List<Employee> employees=employeeService.getByInfo(employee);
			map.put("total", employees.size());
			map.put("rows", employees.subList((page-1)*rows, (employees.size()>page*rows)?(page*rows):employees.size()));			
			return map;
		}
		return null;
	}
	/**
	 * 用户登录
	 * @param employee
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/login")
	public Map<String, Object> login( Employee employee,HttpServletRequest request	)
	{
		Map<String, Object> map=new HashMap<String, Object>();
		session=request.getSession();		
		employee=employeeService.login(session, employee);
		map.put("employee", employee);
		if (employee.getId().equals(-1)) {
			session.setAttribute(EMPLOYEE_NAME, null);
			return map ;
		}else {			
			session.setAttribute(EMPLOYEE_NAME, employee.getEmployeeName());
			session.setAttribute(POSITION, employee.getPosition().getPositionName());
			session.setAttribute(SESSION_EMPLOYEE, employee);
			return map;
		}
	}
	/**
	 * 打开欢迎页面	
	 * @return
	 */
	@RequestMapping(value="welcome",method=RequestMethod.GET)
	public String  welcome() {
		if (session.getAttribute(EMPLOYEE_NAME)!=null){
			if(session.getAttribute(POSITION).toString().equals("业务员")){
				return "welcomeclerk";
			}else if (session.getAttribute(POSITION).toString().equals("客服")) {
				return "welcomeserver";
			}
		return "welcome";
		}else {
			return "redirect:  ";		
			}
		
	}
	
	/**
	 * 新增用户
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String input(Map<String, Object> map) {
		List<Position> positions=positionService.getAll();
		map.put("employee", new Employee());
		map.put("positions", positions);	
		return "emp-input";
	}
	/**
	 * 修改用户
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id,Map<String, Object> map) {
		List<Position> positions=positionService.getAll();
		Employee employee=new Employee();
		employee.setId(id);
		map.put("employee", employeeService.getOneById(id));
		map.put("positions", positions);
		return "emp-input";
	}

	/**
	 * 按id获取用户
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getedit/{id}",method=RequestMethod.GET)
	public Employee getEditById(@PathVariable("id") Integer id) {
		Employee employee=new Employee();
		employee.setId(id);
		employee= employeeService.getOneById(id);
		return employee;
	}
	
	/**
	 * 保存修改或新增
	 * @param employee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(Employee employee) {	
		try {
			employeeService.saveOrUpdate(session,employee);
			return "success";
		} catch (Exception e) {
			return e.toString();
		}		
	}
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		Employee employee=new Employee();
		employee.setId(id);
		employeeService.delete(session,employee);
		return "success";
	}
	/**
	 * 获取所有职位信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getallposition",method=RequestMethod.GET)
	public List<Position> getAllPosition() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("positions", positionService.getAll());
		return positionService.getAll();
	}
	
	
	@InitBinder
	public void  initBinder(WebDataBinder dataBinder) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
