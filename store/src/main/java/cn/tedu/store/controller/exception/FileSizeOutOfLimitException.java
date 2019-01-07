package cn.tedu.store.controller.exception;

/**
 * 上传文件超出限制异常
 * @author 屿
 *
 */
public class FileSizeOutOfLimitException extends FileUploadException {
	private static final long serialVersionUID = 1094167663295032048L;

	public FileSizeOutOfLimitException() {
		super();
	}

	public FileSizeOutOfLimitException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public FileSizeOutOfLimitException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FileSizeOutOfLimitException(String arg0) {
		super(arg0);
	}

	public FileSizeOutOfLimitException(Throwable arg0) {
		super(arg0);
	}
	
}
