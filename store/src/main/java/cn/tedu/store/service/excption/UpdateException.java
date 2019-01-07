package cn.tedu.store.service.excption;

public class UpdateException extends ServiceException {
	private static final long serialVersionUID = 6287436556550280077L;

	public UpdateException() {
		super();
	}

	public UpdateException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public UpdateException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UpdateException(String arg0) {
		super(arg0);
	}

	public UpdateException(Throwable arg0) {
		super(arg0);
	}
	
}
