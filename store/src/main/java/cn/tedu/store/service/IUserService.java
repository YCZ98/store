package cn.tedu.store.service;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.excption.DuplicateKeyException;
import cn.tedu.store.service.excption.InsertException;
import cn.tedu.store.service.excption.PasswordNotMatchException;
import cn.tedu.store.service.excption.UpdateException;
import cn.tedu.store.service.excption.UserNotFoundException;

/**
 * 处理
 * @author 屿
 *
 */
public interface IUserService {
	/**
	 * 用户注册
	 * @param user	用户注册的信息
	 * @return	成功注册的用户数据
	 * @throws DuplicateKeyException
	 * @throws InsertException
	 */
	User reg(User user) throws DuplicateKeyException,InsertException;
	User login(String username,String password) throws UserNotFoundException,PasswordNotMatchException;
	/**
	   *   根据用户id修改密码
	 * @param uid
	 * @param oldPassword
	 * @param newPassword
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchException
	 * @throws UpdateException
	 */
	void changePassword(Integer uid,String oldPassword,String newPassword)throws UserNotFoundException,PasswordNotMatchException,UpdateException;
	/**
	 * 修改用户资料
	 * @param user
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeInfo(User user)throws UserNotFoundException,UpdateException;
	/**
	 * 修改头像
	 * @param uid
	 * @param avatar
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	void changeAvatar(Integer uid,String avatar) throws UserNotFoundException,UpdateException;
	/**
	 * 修改用户资料,
	 * @return	用户数据
	 */
	User getById(Integer id);
}
