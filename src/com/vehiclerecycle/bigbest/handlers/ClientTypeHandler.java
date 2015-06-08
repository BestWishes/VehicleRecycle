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

import com.vehiclerecycle.bigbest.entities.ClientType;
import com.vehiclerecycle.bigbest.service.ClientTypeService;

@SessionAttributes(value={"clientType"})
@RequestMapping(value="/clientType")
@Controller
public class ClientTypeHandler {
	
	@Autowired
	private ClientTypeService clientTypeService;
	@Autowired(required=true)
	private HttpSession session;
	
	public void setClientTypeService(ClientTypeService clientTypeService) {
		this.clientTypeService = clientTypeService;
	}


	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public String getAll(Map<String, Object> map) {
			List<ClientType> clientTypes=clientTypeService.getAll();
			map.put("clientTypes", clientTypes);
			return "clienttype-all";
	}
	

	@ResponseBody
	@RequestMapping(value="/getclienttypes",method=RequestMethod.GET)
	public Map<String, Object> getClientTypes(
			@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="typeName",required=false) String typeName,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows
			) {
		ClientType clientType=new ClientType();
		if(id!=null){
			clientType.setId(id);
		}
		if(typeName!=null){
			clientType.setTypeName(typeName);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		List<ClientType> clientTypes=clientTypeService.getByInfo(clientType);
		map.put("total", clientTypes.size());
		map.put("rows", clientTypes.subList((page-1)*rows, (clientTypes.size()>page*rows)?(page*rows):clientTypes.size()));			
		return map;		
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addInput(Map<String, Object> map) {
		map.put("clientType",new ClientType());
		return "clienttype-input";
	}

	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String editInput(@PathVariable("id") Integer id,Map<String, Object> map) {
		map.put("clientType", clientTypeService.getOneById(id));
		return "clienttype-input";
	}

	@ResponseBody
	@RequestMapping(value="/getedit/{id}",method=RequestMethod.GET)
	public ClientType getEditById(@PathVariable("id") Integer id) {
		return clientTypeService.getOneById(id);
	}
	

	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(ClientType clientType) {	
		try {
			clientTypeService.saveOrUpdate(session,clientType);
			return "success";
		} catch (Exception e) {
			return e.toString();
		}		
	}

	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		ClientType clientType=new ClientType();
		clientType.setId(id);
		clientTypeService.delete(session,clientType);
		return "success";
	}
}
