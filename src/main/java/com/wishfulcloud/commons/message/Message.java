package com.wishfulcloud.commons.message;

import java.util.concurrent.ConcurrentHashMap;

import com.wishfulcloud.commons.model.ResponseResult;

/**   
 * @Title: 
 * @Description: 
 * @author wangxuezheng
 * @date 2016年12月27日 下午2:00:29
 * @version V1.0   
 *
 */
public abstract class Message {
	
	public static ConcurrentHashMap<String, ResponseResult> messageMap = new ConcurrentHashMap<String, ResponseResult>();
	
	static{
		messageMap.put("404", new ResponseResult(true, "404", "未找到对应的访问资源"));
		messageMap.put("500", new ResponseResult(true, "500", "系统异常"));
		messageMap.put("1", new ResponseResult(false, "1", "成功"));
	}
	
	public static ResponseResult buildResponseResult(String code){
		
		ResponseResult result = messageMap.get(code);
		if (result == null)
			return new ResponseResult(false, "9999", "未找到对应的响应编号");
		return result;
	}
	
	public static ResponseResult buildResponseResult(String code, Object data){
		
		ResponseResult result = messageMap.get(code);
		if (result == null)
			return new ResponseResult(false, "9999", "未找到对应的响应编号");
		
		result.setData(data);
		return result;
	}

	public static ResponseResult buildResponseResult(Boolean hasErrors, String code, Object message){
		return new ResponseResult(hasErrors, code, message);
	}
	
	public static ResponseResult buildResponseResult(Boolean hasErrors, String code, Object message, Object data){
		return new ResponseResult(hasErrors, code, message, data);
	}
	
	/**
	 * 操作成功/保存成功/删除成功 等。。。
	 * 
	 * @return
	 */
	public static ResponseResult buildResponseResultSuccess(){
		return new ResponseResult(false, "1", "成功");
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	public static ResponseResult buildResponseResultSuccess(Object data){
		return new ResponseResult(false, "1", "成功", data);
	}	
	
	public static ResponseResult buildResponseResultFail(){
		return new ResponseResult(true, "-1", "失败");
	}
	
	
}
