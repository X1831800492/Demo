package cn.tedu.store.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.Address;
import cn.tedu.store.service.IAddressService;
import cn.tedu.store.util.JsonResult;

@RestController
@RequestMapping("address")
public class AddressControllre extends BaseController {

	@Autowired
	private IAddressService addressService;
	
	@RequestMapping("addnew")
	public JsonResult<Void> addnew(Address address,HttpSession session){
		//从Session中获取uid和username
		Integer uid = getuidFromSession(session);
		String username = getUsernameFromSession(session);
		//调用业务方法中调用方法
		addressService.addnew(uid, username, address);
		//响应成功
		return new JsonResult<Void>(OK);
	}
	
	@GetMapping({"","/"})
	public  JsonResult<List<Address>> getByUid(HttpSession session){
		//从session中获取uid
		Integer uid = getuidFromSession(session);
		List<Address> data = addressService.getByUid(uid);
		return  new JsonResult<>(OK,data);
	}
	
	@RequestMapping("{aid}/set_default")
	public JsonResult<Void> setDefault(HttpSession session,@PathVariable("aid")Integer aid ){
		// 从session种获取
		Integer uid = getuidFromSession(session);
		String username=getUsernameFromSession(session);
		//调用业务中的方法
		addressService.setDefault(aid, uid, username); 
		//响应成功
		return new JsonResult<>(OK);
	} 
	
	@RequestMapping("{aid}/delete")
	public JsonResult<Void> delete(HttpSession session,@PathVariable("aid")Integer aid ){
		// 从session种获取
		Integer uid = getuidFromSession(session);
		String username=getUsernameFromSession(session);
		//调用业务中的方法删除
		addressService.delete(aid, uid, username);
		//响应成功
		return new JsonResult<>(OK);
	} 
}
