package it.es.gestionale.dto;

public class LoginOutputDto {
	
	public static final int LOGIN_FAILED = 0xff;
	public static final int LOGIN_SUCCESS = 0x01;
	
	private int code;
	private String message;
    
	public LoginOutputDto() {
		code = LOGIN_FAILED;
		message = "invalid username or password";
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
