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

import com.vehiclerecycle.bigbest.entities.VehicleElement;
import com.vehiclerecycle.bigbest.service.VehicleElementService;

@SessionAttributes(value={"vehicleElement"})
@RequestMapping(value="/vehicleElement")
@Controller
public class VehicleElementHandler {

	@Autowired
	private VehicleElementService vehicleElementService;
	@Autowired(required=true)
	private HttpSession session;

	public void setVehicleElementService(VehicleElementService vehicleElementService) {
		this.vehicleElementService = vehicleElementService;
	}


	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public String getAll(Map<String, Object> map) {
			List<VehicleElement> vehicleElements=vehicleElementService.getAll();
			map.put("vehicleElements", vehicleElements);
			return "vehicleelement-all";
	}
	

	@ResponseBody
	@RequestMapping(value="/getvehicleelements",method=RequestMethod.GET)
	public Map<String, Object> getVehicleElements(
			@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="eleName",required=false) String name,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows
			) {
		VehicleElement vehicleElement=new VehicleElement();
		if(id!=null){
			vehicleElement.setId(id);
		}
		if(name!=null){
			vehicleElement.setEleName(name);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		List<VehicleElement> vehicleElements=vehicleElementService.getByInfo(vehicleElement);
		map.put("total", vehicleElements.size());
		map.put("rows", vehicleElements.subList((page-1)*rows, (vehicleElements.size()>page*rows)?(page*rows):vehicleElements.size()));			
		return map;		
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addInput(Map<String, Object> map) {
		map.put("vehicleElement",new VehicleElement());
		return "vehicleelement-input";
	}

	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String editInput(@PathVariable("id") Integer id,Map<String, Object> map) {
		map.put("vehicleElement", vehicleElementService.getOneById(id));
		return "vehicleelement-input";
	}

	@ResponseBody
	@RequestMapping(value="/getedit/{id}",method=RequestMethod.GET)
	public VehicleElement getEditById(@PathVariable("id") Integer id) {
		return vehicleElementService.getOneById(id);
	}
	

	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(VehicleElement vehicleElement) {	
		try {
			vehicleElementService.saveOrUpdate(session,vehicleElement);
			return "success";
		} catch (Exception e) {
			return e.toString();
		}		
	}

	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		VehicleElement vehicleElement=new VehicleElement();
		vehicleElement.setId(id);
		vehicleElementService.delete(session,vehicleElement);
		return "success";
	}
}
