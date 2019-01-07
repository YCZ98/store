package cn.tedu.store.service.excption;

public class InsertException extends ServiceException {
	private static final long serialVersionUID = 3549812733157014544L;

	public InsertException() {
		super();
	}

	public InsertException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public InsertException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public InsertException(String arg0) {
		super(arg0);
	}

	public InsertException(Throwable arg0) {
		super(arg0);
	}
	
}
