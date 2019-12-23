package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.InsertException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.UpdateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameDuplicateException;
/**
 * 处理用户相关的实现类
 * @author JAVA
 *
 */
@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public void reg(User user) {
		//根据参数user对象中的username属性查询用户数据
		User result=userMapper.findByUsername(user.getUsername());
		//判断查询结果是否为null
		if(result !=null) {
			//是：根据用户名没有查询到数据，表示用户名已经被注册，则抛出UsernameDuplicateException对象
			throw new UsernameDuplicateException("尝试注册的用户名已被占用");
		}
		//根据用户名没有查询到数据，表示用户名没有被注册，则允许注册
		// TODO 补全数据：加密后的密码，盐值\
		String salt=UUID.randomUUID().toString().toUpperCase();
		String password = user.getPassword(); //获取原始密码
		String md5Password = getMd5Password(password,salt);//执行加密
		user.setPassword(md5Password);
		user.setSalt(salt);
		//补全数据：isDelete
		user.setIsDelete(0);
		//补全数据：4个日志属性
		Date now =new Date();
		user.setCreatedTime(now);
		user.setCreatedUser(user.getUsername());
		user.setModifiedTime(now);
		user.setModifiedUser(user.getUsername());
		//--调用userMapper的insert（）方法执行注册，获取返回的受影响行数
		Integer rows = userMapper.insert(user);
		//--判断受影响的行数是否为1

		//--是：表示注册成功
		if(rows!=1) {
			//--否：表示注册失败，则抛出InsertException对象
			throw new InsertException("插入用户数据时出现错误，请联系管理员");
		}

	}

	@Override
	public User login(String username, String password) {
		//调用userMapper的findByUsername(),根据参数username查询数据
		User result= userMapper.findByUsername(username);
		//判断查询结果是否为null
		if(result==null  ) {
			//是：登录失败，用户名没有对应的数据，抛出UserNotFoundException
			throw new UserNotFoundException("用户名错误或不存在");
			//判断查询结果中的is_delete是否为1
		}else if (result.getIsDelete()==1){
			//是：表示用户数据标记为已删除，判处UsernameNotFoundException
			throw new UserNotFoundException("用户名数据不存在");
		}
		//从查询中取出salt值
		String salt= result.getSalt();
		//调用getMd5Password(String password,String salt)方法，将参数passWord加密，得到md5Password
		String md5Password = getMd5Password(password,salt);

		//判断查询结果中的password与以上加密得到的md5Password是否不同
		if(!(md5Password.equals(result.getPassword()))) {
			//是：密码错误，抛出PasswordNotMatchException
			throw new PasswordNotMatchException("密码错误");
		}
		//【1】将查询结果中的除了uid,username,avatar以外的属性设置为null
		//【2】设置新的User对象，将查询结果中的uid，username,avatar封装到新的对象中
		User user = new User();
		user.setUid(result.getUid());
		user.setUsername(result.getUsername());
		user.setAvatar(result.getAvatar());
		//返回查询结果
		return user;
	}


	@Override
	public void changePassword(Integer uid, String username, String oldPassword, String newPassword) {
		//根据参数uid查询用户数据
		User result = userMapper.findByUid(uid);
		//判断查询结果是否为null
		if(result==null) {
			//是：UserNotFoundException
			throw new UserNotFoundException("用户不存在");
		}
		if(result.getIsDelete()==1) {
			//判断查询结果中的isDelete是否为1
			//是：UserNotFountException
			throw new UserNotFoundException("用户数据不存在");
		}
		//从查询结果中获取salt值
		String salt=result.getSalt();
		//将参数oldPassword结合salt加密，得到oldMd5Password
		String oldMd5Password=getMd5Password(oldPassword, salt);
		//将oldMd5Password与查询结果中的密码是否不同
		if(!(result.getPassword().equals(oldMd5Password))) {
			//是：抛出PasswordNotMatchException
			throw new PasswordNotMatchException("原密码错误");
		}
		//将参数newPasswrod结合salt几码，得到newPassword
		String newMd5Password = getMd5Password(newPassword, salt);
		//创建当前时间对象
		Date modifiedTime = new Date();
		//执行修改密码，并获取受影响的行数
		Integer row = userMapper.updatePasswordByUid(uid, newMd5Password, username, modifiedTime);
		//判断受影响的行数是否为1
		if(row!=1) {    
			//是：抛出UpdateException
			throw new UpdateException("修改失败出现未知错误");
		}
	}
	
	@Override
	public void chengeAvatar(Integer uid, String username, String avatar) {
		//根据参数uid查询用户数据
		User result = userMapper.findByUid(uid);
		if(result==null) {
			//是：UserNotFoundException
			throw new UserNotFoundException("用户不存在");
		}
		if(result.getIsDelete()==1) {
			//判断查询结果中的isDelete是否为1
			//是：UserNotFountException
			throw new UserNotFoundException("用户数据不存在");
		}
		//执行更新头像，并获取返回的受影响的行数
		Integer row = userMapper.updateAvatarByUid(uid, avatar,username,new Date());
		//判断受影响的行数是否为1
				if(row!=1) {    
					//是：抛出UpdateException
					throw new UpdateException("更新头像出现未知错误，请联系管理员");
				}
	}



	@Override
	public User getinfo(Integer uid) {
		//根据参数uid查询用户数据
		User result = userMapper.findByUid(uid);
		//判断查询结果是否为null
		if(result==null) {
			//是：UserNotFoundException
			throw new UserNotFoundException("用户不存在");
		}
		//判断查询结果中的isDelete是否为1
		if(result.getIsDelete()==1) {

			//是：UserNotFoundException
			throw new UserNotFoundException("用户数据不存在");
		}
		//创建新的User对象
		//向新User对象中封装username,phone,email,gender
		User user = new User();
		user.setUsername(result.getUsername());
		user.setPhone(result.getPhone());
		user.setEmail(result.getEmail());
		user.setGender(result.getGender());
		//返回新的user对象
		return user;
	}
	@Override
	public void chengeInfo(Integer uid, String username, User user) {
		//根据参数uid查询用户数据
		User result = userMapper.findByUid(uid);
		//判断查询结果是否为null
		if(result==null) {
			//是：UserNotFoundException
			throw new UserNotFoundException("用户不存在");
		}
		//判断查询结果中的isDelete是否为1
		if(result.getIsDelete()==1) {

			//是：UserNotFoundException
			throw new UserNotFoundException("用户数据不存在");
		}

		//将参数uid封装到user的uid属性中
		user.setUid(uid);
		//将参数username封装到参数user的modifiedUser属性中
		user.setModifiedUser(username);
		user.setModifiedTime(new Date());
		Integer row = userMapper.updateInfoByUid(user);
		//判断受影响的行数是否为1
		if(row!=1) {    
			//是：抛出UpdateException
			throw new UpdateException("修改失败出现未知错误");
		}

	}






	private String getMd5Password(String password,String salt) {
		System.err.println("执行加密");
		System.err.println("\t密码："+password);
		System.err.println("\t盐值："+salt);
		//加密规则：
		//1：将加盐凭借在密码前面1次，后2次
		//2加密10次
		for (int i = 0; i < 5; i++) {
			password = DigestUtils.md5DigestAsHex((salt+password+salt+salt).getBytes()).toUpperCase();
		}
		System.err.println("\t结果："+password);
		return  password;
	}





}

