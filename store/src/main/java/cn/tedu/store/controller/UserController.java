package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.tedu.store.controller.exception.FileEmptyException;
import cn.tedu.store.controller.exception.FileSizeOutOfLimitException;
import cn.tedu.store.controller.exception.FileTypeNotSupportException;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	private static final String UPLOAD_DIR_NAME = "upload";
	/**
	 * 上传文件大小
	 */
	private static final long FILE_MAX_SIZE = 5*1024*1024;
	/**
	 * 允许上传的文件类型
	 */
	private static final List<String> FINLE_CONTENT = new ArrayList<String>(); 
	/**
	 * 初始化允许上传的文件类型的集合
	 */
	static {
		FINLE_CONTENT.add("image/jpeg");
		FINLE_CONTENT.add("image/png");
		FINLE_CONTENT.add("image/jpg");
	}
	@Autowired
	private IUserService userService;
	
	@PostMapping("/reg.do")
	public ResponseResult<Void> showReg(User user,HttpSession session){
		System.out.println("0.0");
		User data = userService.reg(user);
		session.setAttribute("message", data.getId());
		return new ResponseResult<Void>(SUCCESS);
	}
	@PostMapping("/login.do")
	public ResponseResult<User> showLogin(@RequestParam("username") String username,@RequestParam("password") String password,HttpSession session){
		System.out.println("0.0");
		User user = userService.login(username,password);
		session.setAttribute("uid", user.getId());
		session.setAttribute("username", user.getUsername());
		System.out.println(user);
		return new ResponseResult<User>(SUCCESS,user);
	}
	@PostMapping("/password.do")
	public ResponseResult<Void> changePassword(@RequestParam("old_password") String oldPassword,@RequestParam("new_password") String newPassword,HttpSession session){
		System.out.println("0.0");
		Integer id = getUidFromSession(session);
		userService.changePassword(id, oldPassword, newPassword);
		return new ResponseResult<>(SUCCESS);
	}
	@PostMapping("/info.do")
	public ResponseResult<User> showInfo(HttpSession session){
		System.out.println("!!!!!!!!!");
		User user = userService.getById(getUidFromSession(session));
		System.out.println(user);
		return new ResponseResult<User>(SUCCESS,user);
	}
	@PostMapping("/change_info.do")
	public ResponseResult<Void> changeInfo(User user,HttpSession session){
		user.setId(getUidFromSession(session));
		userService.changeInfo(user);
		return new ResponseResult<>(SUCCESS);
	}
	@PostMapping("/upload.do")
	public ResponseResult<String> handleUpload(HttpSession session,@RequestParam("file") MultipartFile file){
		System.err.println("来了");
		//TODO 检查是否存在上传文件
		if(file.isEmpty()) {
			throw new FileEmptyException("上传文件为空的异常");
		}
		//TODO 检查文件大小
		if(file.getSize()>FILE_MAX_SIZE) {
			throw new FileSizeOutOfLimitException("上传文件超出大小的异常");
		}
		//TODO 检查文件类型
		if(!FINLE_CONTENT.contains(file.getContentType())) {
			throw new FileTypeNotSupportException("上传文件不为指定类型的异常");
		}
		//确定文件上传路径
		String parentPath = session.getServletContext().getRealPath(UPLOAD_DIR_NAME);
		File parent = new File(parentPath);
		if(!parent.exists()) {
			parent.mkdirs();
		}
		//确定文件名
		String originalFileName = file.getOriginalFilename();
		String suffix = originalFileName.substring(originalFileName.lastIndexOf("."));
		String fileName = System.currentTimeMillis()+""+new Random().nextInt(900000)+100000+suffix;
		//确定文件
		File dest = new File(parent,fileName);
		//执行保存文件
		try {
			file.transferTo(dest);
			System.err.println("上传完成");
		} catch (IllegalStateException e) {
			//抛出异常:上传失败
			
		} catch (IOException e) {
			//抛出异常:上传失败
			
		}
		//获取用户id
		Integer id = getUidFromSession(session);
		//
		String avatar = "/"+UPLOAD_DIR_NAME+"/"+fileName;
		userService.changeAvatar(id, avatar);
		//返回
		ResponseResult<String> rr = new ResponseResult<String>();
		rr.setState(SUCCESS);
		rr.setData(avatar);
		return rr;
		
	}
}
