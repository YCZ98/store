package cn.tedu.store.service.impl;

import java.util.Date;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.excption.DuplicateKeyException;
import cn.tedu.store.service.excption.InsertException;
import cn.tedu.store.service.excption.PasswordNotMatchException;
import cn.tedu.store.service.excption.UpdateException;
import cn.tedu.store.service.excption.UserNotFoundException;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User reg(User user) throws DuplicateKeyException, InsertException {
		/*
		 * 根据尝试注册的用户名查询用户数据
		 * 判断数据是否为null
		 * 是:用户名不存在,允许注册,密码加密处理,执行注册
		 * 否:用户名已被占用
		 */
		User data = findByUsername(user.getUsername());
		if(data == null) {
			user.setIsDelete(0);
			Date now = new Date();
			user.setCreatedUser(user.getUsername());
			user.setModifiedUser(user.getUsername());
			user.setCreatedTime(now);
			user.setModifiedTime(now);
			String salt = UUID.randomUUID().toString();
			user.setSalt(salt);
			String srcPassword = user.getPassword();
			String md5Password = getMd5Password(srcPassword, salt);
			user.setPassword(md5Password);
			addnew(user);
			return user;
		}else {
			throw new DuplicateKeyException("注册失败,用户名("+user.getUsername()+")已经被占用");
		}
	}
	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		User user = findByUsername(username);
		if(user == null) {
			throw new UserNotFoundException("用户名不存在");
		}
		String srcPassword = getMd5Password(password, user.getSalt());
		if(user.getPassword().equals(srcPassword)) {
			if(user.getIsDelete() == 1) {
				throw new UserNotFoundException("用户名不存在");
			}
			user.setSalt(null);
			user.setPassword(null);
			return user;
		}else {
			throw new PasswordNotMatchException("密码错误");
		}
	}
	@Override
	public void changePassword(Integer uid, String oldPassword, String newPassword)
			throws UserNotFoundException, PasswordNotMatchException, UpdateException {
		User user = findById(uid);
		if(user == null) {
			throw new UserNotFoundException("密码修改失败,用户不存在");
		}
		if(user.getIsDelete() == 1) {
			throw new UserNotFoundException("密码修改失败,用户删除不存在");
		}
		String srcOldPassword = getMd5Password(oldPassword, user.getSalt());
		if(user.getPassword().equals(srcOldPassword)) {
			//String salt = UUID.randomUUID().toString();
			String newSrcPassword = getMd5Password(newPassword, user.getSalt());
			updatePassword(uid, newSrcPassword, user.getUsername(), new Date());
		}else {
			throw new PasswordNotMatchException("密码错误");
		}
	}
	@Override
	public void changeInfo(User user) throws UserNotFoundException, UpdateException {
		User data = findById(user.getId());
		if(data == null) {
			throw new UserNotFoundException("个人资料修改失败,用户不存在");
		}
		if(data.getIsDelete() == 1) {
			throw new UserNotFoundException("个人资料修改失败,用户删除不存在");
		}
		user.setModifiedUser(data.getUsername());
		user.setModifiedTime(new Date());
		updateInfo(user);
	}
	
	@Override
	public User getById(Integer id) {
		User data = findById(id);
		data.setPassword(null);
		data.setSalt(null);
		data.setIsDelete(null);
		return data;
	}
	@Override
	public void changeAvatar(Integer uid, String avatar) throws UserNotFoundException, UpdateException {
		User data = findById(uid);
		if(data == null) {
			throw new UserNotFoundException("头像修改失败,用户不存在");
		}
		if(data.getIsDelete() == 1) {
			throw new UserNotFoundException("头像修改失败,用户删除不存在");
		}
		updateAvatar(uid, avatar, data.getUsername(), new Date());
	}
	/**
	 * 	密码加密处理
	 * @param srcPassword	用户密码
	 * @param salt	uuid盐值
	 * @return
	 */
	private String getMd5Password(String srcPassword,String salt) {
		//盐值 + 原密码 + 盐值
		//循环10次摘要运算,返回摘要结果
		String src = salt + srcPassword+salt;
		for(int i = 0;i < 10;i++) {
			src = DigestUtils.md5DigestAsHex(src.getBytes());
		}
		return src;
	}
	/**
	 * 插入用户数据
	 * @param user
	 */
	private void addnew(User user) {
		Integer rows = userMapper.addnew(user);
		if(rows != 1) {
			throw new InsertException("增加数据出现未知错误");
		}
	}
	/**
	 * 根据用户名查询用户数据
	 * @param username
	 * @return
	 */
	private User findByUsername(String username) {	
		return userMapper.findByUsername(username);
	}
	/**
	 * 根据用户id查询用户数据
	 * @param uid	用户id
	 * @param password	用户密码
	 * @param modifiedUser	最后修改人
	 * @param modifiedTime	最后修改时间
	 */
	private void updatePassword(Integer uid,String password,String modifiedUser,Date modifiedTime) {
		Integer rows = userMapper.updatePassword(uid, password, modifiedUser, modifiedTime);
		if(rows != 1) {
			throw new UpdateException("修改密码数据出现未知错误");
		}	
	}
	private User findById(Integer id) {
		return userMapper.findById(id);
		
	}
	private void updateInfo(User user) {
		Integer rows = userMapper.updateInfo(user);
		if(rows != 1) {
			throw new UpdateException("修改用户数据出现未知错误");
		}	
	}
	/**
	 * 修改头像
	 * @param uid
	 * @param avatar
	 * @param modifiedUser
	 * @param modifiedTime
	 */
	private void updateAvatar(Integer uid,String avatar,String modifiedUser,Date modifiedTime) {
		Integer rows = userMapper.updateAvatar(uid, avatar, modifiedUser, modifiedTime);
		if(rows != 1) {
			throw new UpdateException("修改用户头像出现未知错误");
		}	
	}
	
}
