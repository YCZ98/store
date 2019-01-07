package cn.tedu.store.service.excption;

public class PasswordNotMatchException extends ServiceException {
	private static final long serialVersionUID = 2156319494559456226L;

	public PasswordNotMatchException() {
		super();
	}

	public PasswordNotMatchException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public PasswordNotMatchException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PasswordNotMatchException(String arg0) {
		super(arg0);
	}

	public PasswordNotMatchException(Throwable arg0) {
		super(arg0);
	}
	
}
