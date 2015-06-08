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

import com.vehiclerecycle.bigbest.entities.Transportation;
import com.vehiclerecycle.bigbest.service.TransportationService;

@SessionAttributes(value={"transportation"})
@RequestMapping(value="/transportation")
@Controller
public class TransportationHandler {

	@Autowired
	private TransportationService transportationService;
	@Autowired(required=true)
	private HttpSession session;

	public void setTransportationService(TransportationService transportationService) {
		this.transportationService = transportationService;
	}

	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public String getAll(Map<String, Object> map) {
			List<Transportation> transportations=transportationService.getAll();
			map.put("transportations", transportations);
			return "transportation-all";
	}
	

	@ResponseBody
	@RequestMapping(value="/gettransportations",method=RequestMethod.GET)
	public Map<String, Object> getTransportations(
			@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows
			) {
		Transportation transportation=new Transportation();
		if(id!=null){
			transportation.setId(id);
		}
		if(name!=null){
			transportation.setName(name);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		List<Transportation> transportations=transportationService.getByInfo(transportation);
		map.put("total", transportations.size());
		map.put("rows", transportations.subList((page-1)*rows, (transportations.size()>page*rows)?(page*rows):transportations.size()));			
		return map;		
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addInput(Map<String, Object> map) {
		map.put("transportation",new Transportation());
		return "transportation-input";
	}

	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String editInput(@PathVariable("id") Integer id,Map<String, Object> map) {
		map.put("transportation", transportationService.getOneById(id));
		return "transportation-input";
	}

	@ResponseBody
	@RequestMapping(value="/getedit/{id}",method=RequestMethod.GET)
	public Transportation getEditById(@PathVariable("id") Integer id) {
		return transportationService.getOneById(id);
	}
	

	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(Transportation transportation) {	
		try {
			transportationService.saveOrUpdate(session,transportation);
			return "success";
		} catch (Exception e) {
			return e.toString();
		}		
	}

	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		Transportation transportation=new Transportation();
		transportation.setId(id);
		transportationService.delete(session,transportation);
		return "success";
	}
}
