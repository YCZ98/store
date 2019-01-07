package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.util.ResponseResult;
import cn.tedu.store.controller.exception.FileEmptyException;
import cn.tedu.store.controller.exception.FileSizeOutOfLimitException;
import cn.tedu.store.controller.exception.FileTypeNotSupportException;
import cn.tedu.store.controller.exception.FileUploadException;
import cn.tedu.store.controller.exception.RequestException;
import cn.tedu.store.service.excption.DuplicateKeyException;
import cn.tedu.store.service.excption.InsertException;
import cn.tedu.store.service.excption.PasswordNotMatchException;
import cn.tedu.store.service.excption.ServiceException;
import cn.tedu.store.service.excption.UpdateException;
import cn.tedu.store.service.excption.UserNotFoundException;
/**
 * 当前项目中所有控制器的基类
 * @author 屿
 *
 */
public abstract class BaseController {
	public static final Integer SUCCESS = 200;
	@ExceptionHandler({ServiceException.class,RequestException.class})
	@ResponseBody
	public ResponseResult<Void> handleExceotion(Exception e){
		Integer state = null;
		if(e instanceof DuplicateKeyException) {
			//400-违反了Unique约束的异常
			state = 400;
		}else if(e instanceof InsertException) {
			//500-插入数据的异常
			state = 500;
		}else if(e instanceof UserNotFoundException) {
			//401-用户名不存在的异常
			state = 401;
		}else if(e instanceof PasswordNotMatchException) {
			//402-密码错误的异常
			state = 402;
		}else if(e instanceof UpdateException) {
			//600-修改数据的异常
			state = 403;
		}else if(e instanceof FileEmptyException) {
			//601-上传文件为空的异常
			state = 600;
		}else if(e instanceof FileSizeOutOfLimitException) {
			//602-上传文件超出限制的异常
			state = 601;
		}else if(e instanceof FileTypeNotSupportException) {
			//610-上传文件格式不支持的异常
			state = 602;
		}else if(e instanceof FileUploadException) {
			//610-请求数据的异常
			state = 610;
		}
		return new ResponseResult<>(state,e);
	}
	protected Integer getUidFromSession(HttpSession session) {
		return Integer.valueOf(session.getAttribute("uid").toString());
	}
}
