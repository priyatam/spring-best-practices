package github.priyatam.springrest.exception;

public class InvalidTagException extends RuntimeException {

	private static final long serialVersionUID = -1544195970963068724L;

	public InvalidTagException() {
		super();
	}

	public InvalidTagException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidTagException(String message) {
		super(message);
	}

	public InvalidTagException(Throwable cause) {
		super(cause);
	}

}
