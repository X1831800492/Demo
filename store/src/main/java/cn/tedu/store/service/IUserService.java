package cn.tedu.store.service;

import cn.tedu.store.entity.User;
/**
 * 处理用户业务接口
 * @author JAVA
 *
 */
public interface IUserService {
	/**
	 * 用户注册
	 * @param 新用户的信息
	 */
	void  reg(User user);
	/**
	 * 登录
	 * @param 用户名
	 * @param 用户密码
	 * @return 用户信息
	 */
	User login(String username,String password);
	/**
	 * 修改密码
	 * @param uid 用户id
	 * @param username 用户姓名
	 * @param oldPassword 旧密码
	 * @param newPassword 新密码
	 */
	void changePassword(Integer uid,String username,String oldPassword,String newPassword);
	/**
	 * 修改用户资料
	 * @param uid 当前登录用户id
	 * @param user 用户资料（phone,email,gender）
	 * @param username 当前登录用户姓名
	 */
	void chengeInfo(Integer uid,String username ,User user);
	
	/**
	 * 上传用户头像
	 * @param uid 当前登录用户id
	 * @param username 当前登录用户姓名
	 * @param avatar 新头像
	 */
	void chengeAvatar(Integer uid,String username,String avatar);
	
	
	
	
	
	/**
	 * 查询用户数据
	 * @param 用户id
	 * @return 用户信息
	 */
	User getinfo(Integer uid) ;

}
