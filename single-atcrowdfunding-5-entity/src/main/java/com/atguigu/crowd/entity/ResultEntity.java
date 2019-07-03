package com.atguigu.crowd.entity;

public class ResultEntity<T> {
	public static final String SUCCESS = "SUCCESS";
	public static final String FAILED="FAILED";
	public static final String NO_MSG="NO_MSG";
	public static final String NO_DATA="NO_DATA"; 
	

	private String result;
	private String message;
	private T data;
	
	public ResultEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResultEntity(String result, String message, T data) {
		super();
		this.result = result;
		this.message = message;
		this.data = data;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResultEntity [result=" + result + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
