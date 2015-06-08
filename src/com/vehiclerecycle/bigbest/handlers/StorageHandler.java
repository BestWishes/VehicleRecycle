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

@SessionAttributes(value={"storage"})
@RequestMapping(value="/storage")
@Controller
public class StorageHandler {

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
			List<Storage> storages=storageService.getAll();
			map.put("storages", storages);
			return "storage-all";
	
	}
	

	@ResponseBody
	@RequestMapping(value="/getstorages",method=RequestMethod.GET)
	public Map<String, Object> getStorages(@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="storageName",required=false) String storageName,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows) {
		Storage storage=new Storage();
		if(id!=null){
			storage.setId(id);
		}
		if(storageName!=null){
			storage.setStorageName(storageName);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		List<Storage> storages=storageService.getByInfo(storage);
		map.put("total", storages.size());
		map.put("rows", storages.subList((page-1)*rows, (storages.size()>page*rows)?(page*rows):storages.size()));			
		return map;		
	}
	

	@ResponseBody
	@RequestMapping(value="/getallstolocations",method=RequestMethod.GET)
	public List<StoLocation> getAllStoLocations() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("stoLocations", stoLocationService.getAll());
		return stoLocationService.getAll();
	}
	
	

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String input(Map<String, Object> map) {
		List<StoLocation> stoLocations=stoLocationService.getAll();
		map.put("storage", new Storage());
		map.put("stoLocations", stoLocations);
		return "storage-input";
	}

	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id,Map<String, Object> map) {
		List<StoLocation> stoLocations=stoLocationService.getAll();
		Storage storage=new Storage();
		storage.setId(id);
		map.put("storage", storageService.getOneById(id));
		map.put("stoLocations", stoLocations);
		return "storage-input";
	}

	@ResponseBody
	@RequestMapping(value="/getedit/{id}",method=RequestMethod.GET)
	public Storage getEditById(@PathVariable("id") Integer id) {
		Storage storage=new Storage();
		storage.setId(id);
		storage= storageService.getOneById(id);
		return storage;
	}

	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(Storage storage) {	
		try {
			storageService.saveOrUpdate(session,storage);
			return "success";
		} catch (Exception e) {
			return e.toString();
		}		
	}

	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		Storage storage=new Storage();
		storage.setId(id);
		storageService.delete(session,storage);
		return "success";
	}
	
}
