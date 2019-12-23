package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.controller.ex.FileEmptyException;
import cn.tedu.store.controller.ex.FileIOException;
import cn.tedu.store.controller.ex.FileSizeException;
import cn.tedu.store.controller.ex.FileStateException;
import cn.tedu.store.controller.ex.FileTypeException;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.JsonResult;

@RestController
@RequestMapping("users")
public class UserController extends BaseController {
	@Autowired
	private IUserService userService;

	@RequestMapping("reg")
	public JsonResult<Void> reg(User user) {
		//调用userService的reg()方法实现注册
		userService.reg(user);	

		//返回
		return new JsonResult<Void>(OK);
	}

	@RequestMapping("login")
	public JsonResult<User> login(String username,String password ,HttpSession session){
		//调用userService的login()方法实现登录
		User user=userService.login(username, password);
		//将uid,username存入到session中
		session.setAttribute("uid", user.getUid());
		session.setAttribute("username", user.getUsername());
		System.err.println("\tsession.uid:"+user.getUid());
		System.err.println("\tsession.username:"+user.getUsername());
		//返回
		return  new JsonResult<User>(OK,user);
	}
	@RequestMapping("change_password")
	public JsonResult<Void> change_password(String oldPassword,String newPassword,HttpSession session){
		//从Session中获取uid和username
		Integer uid=Integer.valueOf(session.getAttribute("uid").toString());
		String username=session.getAttribute("username").toString();
		//调用userService方法
		userService.changePassword(uid, username, oldPassword, newPassword);
		//返回
		return new JsonResult<Void>(OK);

	}	
	@RequestMapping("change_info")
	public JsonResult<Void> change_info(User user,HttpSession session){
		//从Session中获取uid和username
		Integer uid=Integer.valueOf(session.getAttribute("uid").toString());
		String username=session.getAttribute("username").toString();
		//调用userService方法
		userService.chengeInfo(uid, username, user);
		//返回
		return new JsonResult<Void>(OK);	
	}
	
	//创建控制器类的时候（项目启动时候），设置了上限值
	@Value("${project.upload.avatar.avatarMaxSize}")
	private int avatarMaxSize;
	
	//检查上传的文件类型：image/JPEG
	private static final List<String> avatar_Types=new ArrayList<>();
	//快捷改名 ctrl+2 , R
	static {
		avatar_Types.add("image/jpeg");
		avatar_Types.add("image/png");
	}
	@PostMapping("change_avatar")
	public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file,HttpSession session){
		//长篇代码，倒着写，最小化
		
		//获取原始文件名
		String originalFilename=file.getOriginalFilename();
		//判断是否为空
		boolean isEmpty = file.isEmpty();
		if(isEmpty) {
			throw new FileEmptyException("请选择您要上传的头像文件");
		}

		//检查上传的文件大小
		if(file.getSize()>avatarMaxSize) {
			throw new FileSizeException("不允许上传超过 "+avatarMaxSize/1024 +"kb的文件");
		}
	
		if(!avatar_Types.contains(file.getContentType())) {
			throw new FileTypeException("您上传的格式有误，允许上传的格式有："+avatar_Types);
		}
		
		//将客户端上传的文件保存砸那个文件夹
		String dir = session.getServletContext().getRealPath("upload");
		File dirFile = new  File(dir);
		if(!(dirFile.exists())) {
			dirFile.mkdirs();
		}
		// 保存上传的头像时使用的文件名
		String name = System.currentTimeMillis()+"-"+System.nanoTime();
		String suffix="";
		//保存客户端上传的文件扩展名
		int beginIndex= originalFilename.lastIndexOf(".");
		if(beginIndex>0) {
			suffix=originalFilename.substring(beginIndex);
		}
		String filename=name +suffix;
		
		// 用户头像的路径(响应给客户端的，且存入到数据库的)
		String avatar = "/upload/"+filename;
		System.err.println(avatar);
		//确定客户端上传的文件保存在哪里	
		try {
			//执行保存
			File dest = new File(dir,filename);
			file.transferTo(dest);
		} catch(IllegalStateException e) {
			throw new  FileStateException("文件状态异常，可能原文件以损坏，请重新上传");
		}catch(IOException e) {
			throw new FileIOException("读写文件时出现错误，请重新上传");
		}
		//从session中获取uid和username
		Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
		String username = session.getAttribute("username").toString();
		//更新用户头像
		userService.chengeAvatar(uid, username, avatar);
		//返回 
		return new JsonResult<>(OK,avatar) ;
		
	}
	
	@GetMapping("info")
	public JsonResult<User> ge2tinfo(HttpSession session){
		//从Session中获取uid和username
		Integer uid=Integer.valueOf(session.getAttribute("uid").toString());
		//调用userService方法	
		User user=userService.getinfo(uid);
		return new JsonResult<User>(OK,user);

	}
}
