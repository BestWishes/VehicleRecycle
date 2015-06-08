package com.vehiclerecycle.bigbest.handlers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vehiclerecycle.bigbest.entities.Log;
import com.vehiclerecycle.bigbest.service.LogService;

@RequestMapping("log")
@Controller
public class LogHandler {

	@Autowired
	private LogService logService;
	
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public String getAll(Map<String, Object> map) {
		
			List<Log> logs=logService.getAll();
			map.put("logs", logs);
			return "log-all";		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/getlogs",method=RequestMethod.GET)
	public Map<String, Object> getLogs(@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows) {
		Log log=new Log();
		if(id!=null){
			log.setId(id);
		}
		
		
		Map<String, Object> map=new HashMap<String, Object>();
			List<Log> logs=logService.getAll();
			map.put("total", logs.size());
			map.put("rows", logs.subList((page-1)*rows, (logs.size()>page*rows)?(page*rows):logs.size()));			
			return map;
	}
	
	@InitBinder
	public void  initBinder(WebDataBinder dataBinder) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
