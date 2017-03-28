package com.wishfulcloud.commons.valid;

import java.util.Map;

/**   
 * @Title: 
 * @Description: 
 * @author mayi
 * @date 2016年12月15日 下午4:37:11
 * @version V1.0   
 *
 */
public class ValidationResult {

	
	//校验结果是否有错
	private boolean hasErrors;
		
	//校验错误信息
	private Map<String,String> errorMsg;
	
	 
	public boolean isHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public Map<String, String> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(Map<String, String> errorMsg) {
		this.errorMsg = errorMsg;
	}

	@Override
	public String toString() {
		return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg="
				+ errorMsg + "]";
	}

	
}
