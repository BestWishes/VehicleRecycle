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

import com.vehiclerecycle.bigbest.entities.Client;
import com.vehiclerecycle.bigbest.entities.ClientType;
import com.vehiclerecycle.bigbest.entities.VehicleBasic;
import com.vehiclerecycle.bigbest.service.ClientService;
import com.vehiclerecycle.bigbest.service.ClientTypeService;
import com.vehiclerecycle.bigbest.service.VehicleBasicService;

@SessionAttributes(value={"client"})
@RequestMapping(value="/client")
@Controller
public class ClientHandler {
	
	@Autowired(required=true)
	private ClientService clientService;
	@Autowired(required=true)
	private VehicleBasicService vehicleBasicService ;
	@Autowired(required=true)
	private HttpSession session;
	@Autowired(required=true)
	private ClientTypeService clientTypeService;
	
	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}
	
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public String getAll(Map<String, Object> map) {
			List<Client> clients=clientService.getAll();
			map.put("clients", clients);
			return "client-all";
	
	}
	

	@ResponseBody
	@RequestMapping(value="/getclients",method=RequestMethod.GET)
	public Map<String, Object> getClients(
			@RequestParam(value="name",required=false) String name,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows
			) {
		Client client=new Client();
		if(name!=null){
			client.setName(name);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		List<Client> clients=clientService.getByInfo(client);
		map.put("total", clients.size());
		map.put("rows", clients.subList((page-1)*rows, (clients.size()>page*rows)?(page*rows):clients.size()));			
		return map;		
	}

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String addInput(Map<String, Object> map) {
		map.put("client",new Client());
		return "client-input";
	}

	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String editInput(@PathVariable("id") Integer id,Map<String, Object> map) {
		map.put("client", clientService.getOneById(id));
		return "client-input";
	}

	@ResponseBody
	@RequestMapping(value="/getedit/{id}",method=RequestMethod.GET)
	public Client getEditById(@PathVariable("id") Integer id) {
		return clientService.getOneById(id);
	}
	

	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(Client client) {	
		try {
			clientService.saveOrUpdate(session,client);
			return "success";
		} catch (Exception e) {
			return e.toString();
		}		
	}

	@ResponseBody
	@RequestMapping(value="/getallclienttype",method=RequestMethod.GET)
	public List<ClientType> getAllClientType() {

		return clientTypeService.getAll();
	}
	
	@ResponseBody
	@RequestMapping(value="/getallvehicles/{id}",method=RequestMethod.GET)
	public Map<String, Object> getAllVehicles(
			@PathVariable("id") Integer id,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows) {
		Map<String, Object> map=new HashMap();
		List<VehicleBasic> vehicleBasics;
		vehicleBasics=vehicleBasicService.getByClient(id);
		map.put("total", vehicleBasics.size());
		map.put("rows", vehicleBasics);			
		return map;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		Client client=new Client();
		client.setId(id);
		clientService.delete(session,client);
		return "success";
	}
}
