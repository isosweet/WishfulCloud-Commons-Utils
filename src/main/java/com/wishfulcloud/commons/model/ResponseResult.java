package com.wishfulcloud.commons.model;

import java.io.Serializable;

public class ResponseResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean hasErrors = false;
	
	/** 消息编号 */
	private String code;
	
	/** 消息描述 */
	private Object message;
	
	/** 数据 */
	private Object data;

	public ResponseResult() {
		super();
	}

	public ResponseResult(Boolean hasErrors, String code, Object message) {
		super();
		this.hasErrors = hasErrors;
		this.code = code;
		this.message = message;
	}

	public ResponseResult(Boolean hasErrors, String code, Object message, Object data) {
		super();
		this.hasErrors = hasErrors;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public Object getMessage() {
		return message;
	}

	public void setMessage(Object message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Boolean getHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(Boolean hasErrors) {
		this.hasErrors = hasErrors;
	}	
}
