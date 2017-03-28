package com.wishfulcloud.commons.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 类型转换
 * 
 * @author wxz
 *
 */
public class ObjectConversionUtils extends org.apache.commons.lang3.StringUtils {
	
	/**
	 * 数据类型转换，String->BigDecimal
	 * 
	 * @param val
	 * @return
	 */
	public static BigDecimal toBigDecimal(String val) {
		if (!MoneyUtils.isNumeric(val)) {
			return null;
		}
		BigDecimal bd = null;
		try {
			bd = new BigDecimal(val);
		} catch (Exception e) {
		}
		return bd;
	}
	
	/**
	 * 数据类型转换，Integer->BigDecimal
	 * 
	 * @param val
	 * @return
	 */
	public static BigDecimal toBigDecimal(Integer val) {
		if (null == val) {
			return null;
		}
		BigDecimal bd = null;
		try {
			bd = new BigDecimal(val);
		} catch (Exception e) {
		}
		return bd;
	}
	
	/**
	 * 转换为Double类型
	 */
	public static Double toDouble(Object val){
		if (val == null){
			return 0D;
		}
		try {
			return Double.valueOf(trim(val.toString()));
		} catch (Exception e) {
			return 0D;
		}
	}
	
	/**
	 * 转换为Float类型
	 */
	public static Float toFloat(Object val){
		return toDouble(val).floatValue();
	}

	/**
	 * 转换为Long类型
	 */
	public static Long toLong(Object val){
		return toDouble(val).longValue();
	}
	
	/**
	 * 转换为Integer类型
	 */
	public static Integer toInteger(Object val){
		return toLong(val).intValue();
	}
	
	/**
	 * 转换为Map类型
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, String> toMap(Object obj) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			BeanInfo info = Introspector.getBeanInfo(obj.getClass());
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				Method reader = pd.getReadMethod();
				// 内容为null的过滤掉
				if (reader == null || reader.invoke(obj) == null) {
					continue;
				}
				// 默认继承Object类的属性，过滤掉
				if (pd.getName().equalsIgnoreCase("class")) {
					continue;
				}
				if (reader.getReturnType().equals(Date.class)) {
					Date date = (Date) reader.invoke(obj);
					map.put(pd.getName(), String.valueOf(date.getTime()));
				} else {
					map.put(pd.getName(), String.valueOf(reader.invoke(obj)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 转换为Class类型
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T toBean(Map<String, String> map, Class<T> clazz) {
		T obj = null;
		try {
			if (!map.isEmpty()) {
				obj = clazz.newInstance();
				for (PropertyDescriptor pd : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
					// 默认继承Object类的属性，过滤掉
					if (pd.getName().equalsIgnoreCase("class")) {
						continue;
					}
					String key = pd.getName();
					if (map.containsKey(key) && map.get(key) != null && map.get(key).length() > 0) {
						Class<?> propertyType = pd.getPropertyType();
						Method method = clazz.getMethod(pd.getWriteMethod().getName(), propertyType);
						if (propertyType.equals(Date.class)) {
							method.invoke(obj, new Date(Long.valueOf(map.get(key))));
						} else if (propertyType.equals(Integer.class)) {
							method.invoke(obj, Integer.valueOf(map.get(key)));
						} else if (propertyType.equals(Double.class)) {
							method.invoke(obj, Double.valueOf(map.get(key)));
						} else if (propertyType.equals(Boolean.class)) {
							method.invoke(obj, Boolean.valueOf(map.get(key)));
						} else {
							method.invoke(obj, map.get(key));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
