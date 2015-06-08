package com.vehiclerecycle.bigbest.handlers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.vehiclerecycle.bigbest.entities.Position;
import com.vehiclerecycle.bigbest.service.DepartmentService;
import com.vehiclerecycle.bigbest.service.PositionService;

@SessionAttributes(value={"position"})
@RequestMapping(value="/position")
@Controller
public class PositionHandler {

	@Autowired(required=true)
	private PositionService positionService;
	@Autowired(required=true)
	private DepartmentService departmentService;
	@Autowired(required=true)
	private HttpSession session;
	@Autowired(required=true)
	private HttpServletRequest  request;
	
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public String getAll(Map<String, Object> map) {
			List<Position> positions=positionService.getAll();
			map.put("positions", positions);
			return "position-all";
	
	}
	

	@ResponseBody
	@RequestMapping(value="/getpositions",method=RequestMethod.GET)
	public Map<String, Object> getPositions(@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="positionName",required=false) String positionName,
			@RequestParam(value="departmentName",required=false) String departmentName,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows) {
		Position position=new Position();
		if(id!=null){
			position.setId(id);
		}
		if(positionName!=null){
			position.setPositionName(positionName);
		}
		if(departmentName!=null)
		{
			Department department=new Department();
			department.setDeptName(departmentName);
			position.setDepartment(department);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		List<Position> positions=positionService.getByInfo(position);
		map.put("total", positions.size());
		map.put("rows", positions.subList((page-1)*rows, (positions.size()>page*rows)?(page*rows):positions.size()));			
		return map;		
	}
	

	@ResponseBody
	@RequestMapping(value="/getalldepartment",method=RequestMethod.GET)
	public List<Department> getAllDepartment() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("departments", departmentService.getAll());
		return departmentService.getAll();
	}
	
	

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String input(Map<String, Object> map) {
		List<Department> departments=departmentService.getAll();
		map.put("position", new Position());
		map.put("departments", departments);
		return "position-input";
	}

	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id,Map<String, Object> map) {
		List<Department> departments=departmentService.getAll();
		Position position=new Position();
		position.setId(id);
		map.put("position", positionService.getOneById(id));
		map.put("departments", departments);
		return "position-input";
	}

	@ResponseBody
	@RequestMapping(value="/getedit/{id}",method=RequestMethod.GET)
	public Position getEditById(@PathVariable("id") Integer id) {
		Position position=new Position();
		position.setId(id);
		position= positionService.getOneById(id);
		return position;
	}

	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(Position position) {	
		try {
			positionService.saveOrUpdate(session,position);
			return "success";
		} catch (Exception e) {
			return e.toString();
		}		
	}

	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		Position position=new Position();
		position.setId(id);
		positionService.delete(session,position);
		return "success";
	}
}
