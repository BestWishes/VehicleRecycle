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

import com.vehiclerecycle.bigbest.entities.StoLocation;
import com.vehiclerecycle.bigbest.entities.Storage;
import com.vehiclerecycle.bigbest.service.StoLocationService;
import com.vehiclerecycle.bigbest.service.StorageService;

@SessionAttributes(value={"stoLocation"})
@RequestMapping(value="/stoLocation")
@Controller
public class StoLocationHandler {

	@Autowired
	private StorageService storageService;
	@Autowired
	private StoLocationService stoLocationService;
	@Autowired(required=true)
	private HttpSession session;
	
	public void setStorageService(StorageService storageService) {
		this.storageService = storageService;
	}

	public void setStoLocationService(StoLocationService stoLocationService) {
		this.stoLocationService = stoLocationService;
	}


	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public String getAll(Map<String, Object> map) {
			List<StoLocation> stoLocations=stoLocationService.getAll();
			map.put("stoLocations", stoLocations);
			return "stolocation-all";
	}
	

	@ResponseBody
	@RequestMapping(value="/getstolocations",method=RequestMethod.GET)
	public Map<String, Object> getStoLocations(
			@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="storageName",required=false) String storageName,
			@RequestParam(value="locName",required=false) String locName,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows) {
		StoLocation stoLocation=new StoLocation();
		Storage storage=new Storage();
		if(id!=null){
			stoLocation.setId(id);
		}
		if(storageName!=null){
			storage.setStorageName(storageName);
			stoLocation.setStorage(storage);
		}
		if (locName!=null) {
			stoLocation.setLocName(locName);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		List<StoLocation> stoLocations=stoLocationService.getByInfo(stoLocation);
		map.put("total", stoLocations.size());
		map.put("rows", stoLocations.subList((page-1)*rows, (stoLocations.size()>page*rows)?(page*rows):stoLocations.size()));			
		return map;		
	}
	

	@ResponseBody
	@RequestMapping(value="/getallstorages",method=RequestMethod.GET)
	public List<Storage> getAllStorages() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("Storage", storageService.getAll());
		return storageService.getAll();
	}
	
	

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String input(Map<String, Object> map) {
		List<StoLocation> stoLocations=stoLocationService.getAll();
		map.put("storage", new Storage());
		map.put("stoLocations", stoLocations);
		return "stolocation-input";
	}

	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id,Map<String, Object> map) {
		List<Storage> storages=storageService.getAll();
		StoLocation stoLocation=new StoLocation();
		stoLocation.setId(id);
		map.put("stoLocation", stoLocationService.getOneById(id));
		map.put("storages", storages);
		return "stolocation-input";
	}

	@ResponseBody
	@RequestMapping(value="/getedit/{id}",method=RequestMethod.GET)
	public StoLocation getEditById(@PathVariable("id") Integer id) {
		StoLocation stoLocation=new StoLocation();
		stoLocation.setId(id);
		stoLocation= stoLocationService.getOneById(id);
		return stoLocation;
	}

	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(StoLocation stoLocation) {	
		try {
			stoLocationService.saveOrUpdate(session,stoLocation);
			return "success";
		} catch (Exception e) {
			return e.toString();
		}		
	}

	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		StoLocation stoLocation=new StoLocation();
		stoLocation.setId(id);
		stoLocationService.delete(session,stoLocation);
		return "success";
	}
	
}
