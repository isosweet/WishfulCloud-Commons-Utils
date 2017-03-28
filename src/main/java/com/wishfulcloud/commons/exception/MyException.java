package com.wishfulcloud.commons.exception;

import com.wishfulcloud.commons.model.ResponseResult;

/**   
 * @Title: 
 * @Description: 
 * @author wangxuezheng
 * @date 2016年12月23日 下午3:48:12
 * @version V1.0   
 *
 */
public class MyException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ResponseResult responseResult;

	public MyException(ResponseResult responseResult) {
		super();
		this.responseResult = responseResult;
	}

	public ResponseResult getResponseResult() {
		return responseResult;
	}

	public void setResponseResult(ResponseResult responseResult) {
		this.responseResult = responseResult;
	}
	
	
	
}
