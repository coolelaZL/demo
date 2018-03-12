package cn.tedu.cloudnote.exception;

public class NotebookNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -5143848368268368282L;

	public NotebookNotFoundException() {
		super();
	}

	public NotebookNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NotebookNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotebookNotFoundException(String message) {
		super(message);
	}

	public NotebookNotFoundException(Throwable cause) {
		super(cause);
	}
	
}
