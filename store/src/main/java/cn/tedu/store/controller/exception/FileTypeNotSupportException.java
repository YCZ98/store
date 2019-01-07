package cn.tedu.store.controller.exception;

/**
 * 上传文件类型不支持的异常
 * @author 屿
 *
 */
public class FileTypeNotSupportException extends FileUploadException {
	private static final long serialVersionUID = -1919934638257089254L;

	public FileTypeNotSupportException() {
		super();
	}

	public FileTypeNotSupportException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public FileTypeNotSupportException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public FileTypeNotSupportException(String arg0) {
		super(arg0);
	}

	public FileTypeNotSupportException(Throwable arg0) {
		super(arg0);
	}
	
}
