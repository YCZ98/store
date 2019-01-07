package cn.tedu.store.mapper;


import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.tedu.store.entity.User;
/**
 * 
 * @author 屿
 *
 */
public interface UserMapper {
	/**
	 * 插入用户数据
	 * @param 用户数据
	 * @return 受影响的行数
	 */
	Integer addnew(User user);
	Integer updatePassword(@Param("uid") Integer uid,@Param("password") String password,@Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);
	/**
	 * 修改用户资料
	 * @param user	用户资料
	 * @return	受影响行数
	 */
	Integer updateInfo(User user);
	/**
	 * 上传修改头像
	 * @param uid	用户id
	 * @param avatar	头像
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer updateAvatar(@Param("uid") Integer uid,@Param("avatar") String avatar,@Param("modifiedUser") String modifiedUser,@Param("modifiedTime") Date modifiedTime);
	/**
	 * 根据用户名查询用户数据
	 * @param username	用户名
	 * @return	匹配的用户数据,如果没有匹配的数据,则返回null
	 */
	User findByUsername(String username);
	/**
	 * 根据用户名查询用户数据
	 * @param id
	 * @return
	 */
	User findById(Integer id);
}
