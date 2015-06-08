package com.vehiclerecycle.bigbest.handlers;

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

import com.vehiclerecycle.bigbest.entities.RevokeBill;
import com.vehiclerecycle.bigbest.entities.VehicleBasic;
import com.vehiclerecycle.bigbest.service.RevokeBillService;
import com.vehiclerecycle.bigbest.service.VehicleBasicService;

@SessionAttributes(value="{revokeBill}")
@RequestMapping(value="revokeBill")
@Controller
public class RevokeBillHandler {

	@Autowired
	private RevokeBillService revokeService;
	@Autowired
	private VehicleBasicService vehicleBasicService;
	@Autowired(required=true)
	private HttpSession session;
	
	/**
	 * 获取所有撤销单
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/getall",method=RequestMethod.GET)
	public String getAll(Map<String, Object> map) {
			List<RevokeBill> revokeBills=revokeService.getAll();
			map.put("revokeBills", revokeBills);
			return "revoke-all";
	
	}
	
	/**
	 * 按条件获取撤销单
	 * @param id
	 * @param vehicleBasicID
	 * @param page
	 * @param rows
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getrevokeBills",method=RequestMethod.GET)
	public Map<String, Object> getRevokes(@RequestParam(value="id",required=false) Integer id,
			@RequestParam(value="vehicleBasicID",required=false) Integer vehicleBasicID,
			@RequestParam(value="page",required=false) Integer page,
			@RequestParam(value="rows",required=false) Integer rows) {
		RevokeBill revoke=new RevokeBill();
		if(id!=null){
			revoke.setId(id);
		}
		else if(vehicleBasicID!=null){
			VehicleBasic vehicleBasic=new VehicleBasic();
			vehicleBasic.setId(vehicleBasicID);
			revoke.setVehicleBasic(vehicleBasic);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		List<RevokeBill> revokes=revokeService.getByInfo(revoke);
		map.put("total", revokes.size());
		map.put("rows", revokes.subList((page-1)*rows, (revokes.size()>page*rows)?(page*rows):revokes.size()));			
		return map;		
	}
	
	/**
	 * 获取所有业务单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getallvehicleBasics",method=RequestMethod.GET)
	public List<VehicleBasic> getAllVehicleBasic() {
		//Map<String, Object> map=new HashMap<String, Object>();
		//map.put("vehicleBasics", vehicleBasicService.getAll());
		VehicleBasic vehicleBasic=new VehicleBasic();
		vehicleBasic.setState("进行中");
		
		return vehicleBasicService.getByInfo(vehicleBasic);
	}
	
	
	/**
	 * 新增撤销单
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String input(Map<String, Object> map) {
		List<VehicleBasic> vehicleBasics=vehicleBasicService.getAll();
		map.put("revokeBill", new RevokeBill());
		map.put("vehicleBasics", vehicleBasics);
		return "revoke-input";
	}
	
	/**
	 *  修改撤销单
	 * @param id
	 * @param map
	 * @return
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") Integer id,Map<String, Object> map) {
		List<VehicleBasic> vehicleBasics=vehicleBasicService.getAll();
		RevokeBill revoke=new RevokeBill();
		revoke.setId(id);
		map.put("revoke", revokeService.getOneById(id));
		map.put("vehicleBasics", vehicleBasics);
		return "revoke-input";
	}
	/**
	 * 按编号获取撤销单
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getedit/{id}",method=RequestMethod.GET)
	public RevokeBill getEditById(@PathVariable("id") Integer id) {
		return revokeService.getOneById(id);
	}
	
	/**
	 * 保存修改或新增
	 * @param position
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String saveOrUpdate(RevokeBill revokeBill) {	
		try {
			revokeService.saveOrUpdate(session,revokeBill);
			return "success";
		} catch (Exception e) {
			return e.toString();
		}		
	}
	/**
	 * 删除撤销单
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	public String delete(@PathVariable("id") Integer id) {
		RevokeBill revoke=new RevokeBill();
		revoke.setId(id);
		revokeService.delete(session,revoke);
		return "success";
	}
	
	@InitBinder
	public void  initBinder(WebDataBinder dataBinder) {
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
