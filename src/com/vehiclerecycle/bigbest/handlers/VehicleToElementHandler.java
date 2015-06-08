package com.vehiclerecycle.bigbest.handlers;

import static com.vehiclerecycle.bigbest.util.Constant.EMPLOYEE_NAME;
import static com.vehiclerecycle.bigbest.util.Constant.POSITION;
import static com.vehiclerecycle.bigbest.util.Constant.VEHICLESTATE;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.vehiclerecycle.bigbest.entities.VehicleElement;
import com.vehiclerecycle.bigbest.entities.VehicleToElement;
import com.vehiclerecycle.bigbest.service.ClientService;
import com.vehiclerecycle.bigbest.service.DepartmentService;
import com.vehiclerecycle.bigbest.service.EmployeeService;
import com.vehiclerecycle.bigbest.service.PositionService;
import com.vehiclerecycle.bigbest.service.StoLocationService;
import com.vehiclerecycle.bigbest.service.StorageService;
import com.vehiclerecycle.bigbest.service.VehicleBasicService;
import com.vehiclerecycle.bigbest.service.VehicleDetailService;
import com.vehiclerecycle.bigbest.service.VehicleElementService;
import com.vehiclerecycle.bigbest.service.VehicleToElementService;

@SessionAttributes(value="vehicleToElement")
@RequestMapping(value="/vehicleToElement")
@Controller
public class VehicleToElementHandler {

	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private VehicleBasicService vehicleBasicService;
	@Autowired
	private VehicleDetailService vehicleDetailService;
	@Autowired
	private VehicleToElementService vehicleToElementService;
	@Autowired
	private VehicleElementService vehicleElementService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private StoLocationService stoLocationService;
	@Autowired
	private StorageService storageService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired(required=true)
	private HttpSession session;
	
	
	public void setVehicleDetailService(VehicleDetailService vehicleDetailService) {
		this.vehicleDetailService = vehicleDetailService;
	}
	
	public void setVehicleToElementService(
			VehicleToElementService vehicleToElementService) {
		this.vehicleToElementService = vehicleToElementService;
	}

	public void setVehicleElementService(VehicleElementService vehicleElementService) {
		this.vehicleElementService = vehicleElementService;
	}

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	public void setStoLocationService(StoLocationService stoLocationService) {
		this.stoLocationService = stoLocationService;
	}

	public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	
	public void setVehicleBasicService(VehicleBasicService vehicleBasicService) {
		this.vehicleBasicService = vehicleBasicService;
	}

	/**
	 * 获取符合条件的所有业务单
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public String getAll(Map<String, Object> map,
			@RequestParam(value="vehiclestate",required=false) String vehicleparam) {
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
			return "vehicletoelement-all";
	
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
	public Map<String, Object> getPositions(
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
	
	
	@ResponseBody
	@RequestMapping(value="/getvehicletoelements",method=RequestMethod.GET)
	public Map<String, Object> getvehicletoelements(
			@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="eleName",required=false) String eleName,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows
			) {
		VehicleElement vehicleElement=new VehicleElement();
		VehicleToElement vehicleToElement=new VehicleToElement();
		if(id!=null){
			vehicleToElement.setId(id);
		}
		if (eleName!=null) {
			vehicleElement.setEleName(eleName);
			vehicleToElement.setVehicleElement(vehicleElement);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		List<VehicleToElement> vehicleBasics=vehicleToElementService.getByInfo(vehicleToElement);
		map.put("total", vehicleBasics.size());
		map.put("rows", vehicleBasics.subList((page-1)*rows, (vehicleBasics.size()>page*rows)?(page*rows):vehicleBasics.size()));			
		return map;		
	}

	
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
	
	@ResponseBody
	@RequestMapping(value="/getvehicledetailbystate",method=RequestMethod.GET)
	public List<VehicleDetail> getVehicleBasicByState() {
		VehicleBasic vehicleBasic=new VehicleBasic();
		vehicleBasic.setState("进行中");
		VehicleDetail vehicleDetail=new VehicleDetail();
		vehicleDetail.setVehicleBasic(vehicleBasic);
		return vehicleDetailService.getByInfo(vehicleDetail);
	}
	
	@ResponseBody
	@RequestMapping(value="/getallvehicleelement",method=RequestMethod.GET)
	public List<VehicleElement> getallvehicleelement() {
		return vehicleElementService.getAll();
	}
	
	/**
	 * 添加
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String input(
			Map<String, Object> map,
			@RequestParam(value="id",required=false) Integer id
			) {
		List<Employee> employees=employeeService.getAll();
		map.put("vehicleBasic", new VehicleBasic());
		map.put("clerks", employees);
		map.put("servers", employees);
		Employee employee=new Employee();
		employee.setEmployeeName(EMPLOYEE_NAME);
		map.put("server", employeeService.getByInfo(employee));
		return "vehicletoelement-input";
	}
	
	/**
	 *  修改
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id,Map<String, Object> map) {
		List<VehicleToElement> vehicleToElements=vehicleToElementService.getAll();
		VehicleDetail vehicleDetail=new VehicleDetail();
		vehicleDetail.setId(id);
		map.put("vehicleDetail", vehicleDetailService.getOneById(id));
		map.put("vehicleToElements", vehicleToElements);
		return "vehicletoelement-input";
	}

	@ResponseBody
	@RequestMapping(value="/getedit/{id}",method=RequestMethod.GET)
	public VehicleToElement getEditById(@PathVariable("id") Integer id) {
		VehicleToElement vehicleToElement=new VehicleToElement();
		vehicleToElement.setId(id);
		vehicleToElement= vehicleToElementService.getOneById(id);
		return vehicleToElement;
	}
	

	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(
			VehicleToElement vehicleToElement
			) {	
		try {
			vehicleToElementService.saveOrUpdate(session,vehicleToElement);
			return "success";
		} catch (Exception e) {
			return e.toString();
		}		
	}

	/**
	 * 删除指定ID的业务单
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		VehicleToElement vehicleToElement=new VehicleToElement();
		vehicleToElement.setId(id);
		vehicleToElementService.delete(session,vehicleToElement);
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
