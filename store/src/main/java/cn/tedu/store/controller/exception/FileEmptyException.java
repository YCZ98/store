package cn.tedu.store.controller.exception;

/**
 * 上传文件为空的异常
 * @author 屿
 *
 */
public class FileEmptyException extends FileUploadException {
	private static final long serialVersionUID = 7473259255005119050L;

	public FileEmptyException() {
		super();
	}

	public FileEmptyException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public FileEmptyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FileEmptyException(String arg0) {
		super(arg0);
	}

	public FileEmptyException(Throwable arg0) {
		super(arg0);
	}
	
}
