package github.priyatam.springrest.utils.exception;

/**
 * Sample Business Exception for Policy Validation
 * 
 */
public class PolicyInvalidException extends RuntimeException {

	public static enum ErrorCode {
		TOO_MANY_DRIVERS(41201), TOO_MANY_VEHICLES(41202), NON_NULL_POLICY_NUM(41203), NON_NULL_QUOTE(41204);

		final int code;

		ErrorCode(int code) {
			this.code = code;
		}

		public int value() {
			return this.code;
		}
	}

	private static final long serialVersionUID = -5424196757379520963L;

	ErrorCode errorCode;

	public PolicyInvalidException(ErrorCode errorCode) {
		super(errorCode.toString() + ", code: " + errorCode.value());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
	}

}
