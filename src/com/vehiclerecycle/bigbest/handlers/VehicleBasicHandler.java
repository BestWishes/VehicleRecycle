package com.vehiclerecycle.bigbest.handlers;

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

import com.vehiclerecycle.bigbest.entities.Client;
import com.vehiclerecycle.bigbest.entities.Employee;
import com.vehiclerecycle.bigbest.entities.Position;
import com.vehiclerecycle.bigbest.entities.VehicleBasic;
import com.vehiclerecycle.bigbest.entities.VehicleDetail;
import com.vehiclerecycle.bigbest.service.ClientService;
import com.vehiclerecycle.bigbest.service.EmployeeService;
import com.vehiclerecycle.bigbest.service.PositionService;
import com.vehiclerecycle.bigbest.service.VehicleBasicService;
import com.vehiclerecycle.bigbest.service.VehicleDetailService;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;
import static com.vehiclerecycle.bigbest.util.Constant.VEHICLESTATE;
import static com.vehiclerecycle.bigbest.util.Constant.POSITION;;

@SessionAttributes(value={"vehicleBasic","vehicleDetail"})
@RequestMapping(value="/vehicleBasic")
@Controller
public class VehicleBasicHandler {

	@Autowired
	private VehicleDetailService vehicleDetailService;
	@Autowired
	private VehicleBasicService vehicleBasicService;
	@Autowired
	private EmployeeService	employeeService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private ClientService clientService;
	@Autowired(required=true)
	private HttpSession session;
	@Autowired(required=true)
	private HttpServletRequest  request;
		
	public void setVehicleBasicService(VehicleBasicService vehicleBasicService) {
		this.vehicleBasicService = vehicleBasicService;
	}

	/**
	 * 获取符合条件的所有业务单
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public String getAll(
			Map<String, Object> map,
			@RequestParam(value="vehiclestate",required=false) String vehicleparam
			) {
				if(vehicleparam!=null){
					session.setAttribute(VEHICLESTATE, vehicleparam);
					if(vehicleparam.equals("31")){
						return "clerkvehicle1-all";
					}else if (vehicleparam.equals("32")) {
						return "clerkvehicle2-all";
					}else if (vehicleparam.equals("33")) {
						return "clerkvehicle3-all";
					}else if (vehicleparam.equals("41")) {
						return "servervehicle1-all";
					}else if (vehicleparam.equals("42")) {
						return "servervehicle2-all";
					}else if (vehicleparam.equals("43")) {
						return "servervehicle3-all";
					}else if (vehicleparam.equals("44")) {
						return "servervehicle4-all";
					}
				}
				List<VehicleBasic> vehicleBasics=vehicleBasicService.getAll();
				map.put("vehicleBasics", vehicleBasics);
				return "vehicle-all";
	}
	
	
	@RequestMapping(value="/gettabs",method=RequestMethod.GET)
	public String getAll(Map<String, Object> map) {
			return "vehicle-tabs";
	}
	
	
	/**
	 * 获取所有业务单信息
	 * @param id
	 * @param clerkName
	 * @param serverName
	 * @param vehiclestate
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getvehicleBasics",method=RequestMethod.GET)
	public Map<String, Object> getvehicledetailsbyclient(
			
			@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="clerkName",required=false) String clerkName,
			@RequestParam(value="serverName",required=false) String serverName,
			@RequestParam(value="vehiclestate",required=false) String vehiclestate,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows
			) {
		VehicleBasic vehicleBasic=new VehicleBasic();
		if(!((String)session.getAttribute(VEHICLESTATE)).equals("21")){
			String vehicleparam=(String) session.getAttribute(VEHICLESTATE);
			Employee employee=new Employee();
			/**
			 * 读取用户姓名
			 */
			if(session.getAttribute(EMPLOYEE_NAME)!=null){
				employee.setEmployeeName((String) session.getAttribute(EMPLOYEE_NAME));
			}
			
			if(session.getAttribute(POSITION).toString().equals("客服")){
				vehicleBasic.setServer(employee);
			}
			else if (session.getAttribute(POSITION).toString().equals("业务员")) {
				
				vehicleBasic.setClerk(employee);
			}
			
			if(vehicleparam.equals("31")){
				vehicleBasic.setState("进行中");
			}
			if(vehicleparam.equals("32")){
				vehicleBasic.setState("已完成");
			}
			if(vehicleparam.equals("33")){
				vehicleBasic.setState("已撤单");
			}
			
			if(vehicleparam.equals("41")){
				vehicleBasic.setState("已预约");
			}
			if(vehicleparam.equals("42")){
				vehicleBasic.setState("进行中");
			}
			if(vehicleparam.equals("43")){
				vehicleBasic.setState("已完成");
			}
			if(vehicleparam.equals("44")){
				vehicleBasic.setState("已撤单");
			}
			
		}
		if(id!=null){
			vehicleBasic.setId(id);
		}
		if (clerkName!=null) {
			Employee employee=new Employee();
			employee.setEmployeeName(clerkName);
			vehicleBasic.setClerk(employee);
		}
		if (serverName!=null) {
			Employee employee=new Employee();
			employee.setEmployeeName(serverName);
			vehicleBasic.setServer(employee);
		}
		
		if (vehiclestate!=null) {
			vehicleBasic.setState(vehiclestate);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		List<VehicleBasic> vehicleBasics=vehicleBasicService.getByInfo(vehicleBasic);
		map.put("total", vehicleBasics.size());
		map.put("rows", vehicleBasics.subList((page-1)*rows, (vehicleBasics.size()>page*rows)?(page*rows):vehicleBasics.size()));			
		return map;		
	}
	
	//
	@ResponseBody
	@RequestMapping(value="/getvehicledetailsbyclient/{vbId}",method=RequestMethod.GET)
	public Map<String, Object> getvehicledetailsbyclient(
			@PathVariable("vbId") Integer vbId,
			@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="clerkName",required=false) String clerkName,
			@RequestParam(value="vehicleBasicID",required=false) Integer vehicleBasicID,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows
			) {
		VehicleBasic vehicleBasic=new VehicleBasic();
		VehicleDetail vehicleDetail=new VehicleDetail();
		if(id!=null){
			vehicleDetail.setId(id);
		}
		if (vbId!=null) {
			vehicleBasic.setId(vbId);
			vehicleDetail.setVehicleBasic(vehicleBasic);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		List<VehicleDetail> vehicleDetails=vehicleDetailService.getByInfo(vehicleDetail);
		map.put("total", vehicleDetails.size());
		map.put("rows", vehicleDetails.subList((page-1)*rows, (vehicleDetails.size()>page*rows)?(page*rows):vehicleDetails.size()));			
		return map;		
	}
	//
	/**
	 * 获取所有的业务员
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getallclerk",method=RequestMethod.GET)
	public List<Employee> getAllClerk() {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("clerks", employeeService.getAll());
		Employee employee=new Employee();
		Position position=new Position();
		position.setPositionName("业务员");
		position=positionService.getByInfo(position).get(0);
		employee.setPosition(position);
			
		return employeeService.getByInfo(employee);
	}
	
	@ResponseBody
	@RequestMapping(value="/getallclient",method=RequestMethod.GET)
	public List<Client> getAllClient() {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("clerks", employeeService.getAll());
		Client client=new Client();		
		Map<String, Object> map=new HashMap<String, Object>();
		return clientService.getAll();
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getallserver",method=RequestMethod.GET)
	public List<Employee> getAllServer() {
//		Map<String, Object> map=new HashMap<String, Object>();
//		map.put("clerks", employeeService.getAll());
		Employee employee=new Employee();
		Position position=new Position();
		position.setPositionName("客服");
		position=positionService.getByInfo(position).get(0);
		employee.setPosition(position);
		
		return employeeService.getByInfo(employee);
	}
	
	@ResponseBody
	@RequestMapping(value="/getserver",method=RequestMethod.GET)
	public List<Employee> getServer() {
		Employee employee=new Employee();
		employee.setEmployeeName(session.getAttribute(EMPLOYEE_NAME).toString());
		
		return employeeService.getByInfo(employee);
	}
	
	/**
	 * 添加
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String input(Map<String, Object> map) {
		List<Employee> employees=employeeService.getAll();
		map.put("vehicleBasic", new VehicleBasic());
		map.put("clerks", employees);
		map.put("servers", employees);
		Employee employee=new Employee();
		employee.setEmployeeName(EMPLOYEE_NAME);
		map.put("server", employeeService.getByInfo(employee));
		return "vehicle-input";
	}
	
	/**
	 *  修改
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id,Map<String, Object> map) {
		List<Employee> employees=employeeService.getAll();
		VehicleBasic vehicleBasic=new VehicleBasic();
		vehicleBasic.setId(id);
		map.put("vehicleBasic", vehicleBasicService.getOneById(id));
		map.put("employees", employees);
		return "vehicle-input";
	}

	@ResponseBody
	@RequestMapping(value="/getedit/{id}",method=RequestMethod.GET)
	public VehicleBasic getEditById(@PathVariable("id") Integer id) {
		VehicleBasic vehicleBasic=new VehicleBasic();
		vehicleBasic.setId(id);
		vehicleBasic= vehicleBasicService.getOneById(id);
		return vehicleBasic;
	}
	

	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(
			VehicleBasic vehicleBasic
			) {	
		try {
			vehicleBasicService.saveOrUpdate(session,vehicleBasic);
			return "success";
		} catch (Exception e) {
			return e.toString();
		}		
	}
	@ResponseBody
	@RequestMapping(value="/complete/{id}",method=RequestMethod.POST)
	public String complete(@PathVariable("id") Integer id) {	
		VehicleBasic vehicleBasic=new VehicleBasic();
		vehicleBasic.setId(id);
		vehicleBasic=vehicleBasicService.getOneById(id);
		vehicleBasic.setState("已完成");
		vehicleBasicService.saveOrUpdate(session,vehicleBasic);
		return "success";	
	}
	@ResponseBody
	@RequestMapping(value="/revoke/{id}",method=RequestMethod.POST)
	public String revoke(@PathVariable("id") Integer id) {	
		VehicleBasic vehicleBasic=new VehicleBasic();
		vehicleBasic.setId(id);
		vehicleBasic=vehicleBasicService.getOneById(id);
		vehicleBasic.setState("已撤单");
		vehicleBasicService.saveOrUpdate(session,vehicleBasic);
		return "success";		
	}
	/**
	 * 删除指定ID的业务单
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		VehicleBasic vehicleBasic=new VehicleBasic();
		vehicleBasic.setId(id);
		vehicleBasicService.delete(session,vehicleBasic);
		return "success";
	}
	
	@InitBinder
	public void  initBinder(WebDataBinder dataBinder) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, 
				new CustomDateEditor(dateFormat, false));
	}
}
