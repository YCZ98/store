package cn.tedu.store.service.excption;

public class DuplicateKeyException extends ServiceException {
	private static final long serialVersionUID = 7857529484002481236L;

	public DuplicateKeyException() {
		super();
	}

	public DuplicateKeyException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public DuplicateKeyException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DuplicateKeyException(String arg0) {
		super(arg0);
	}

	public DuplicateKeyException(Throwable arg0) {
		super(arg0);
	}
	
}
