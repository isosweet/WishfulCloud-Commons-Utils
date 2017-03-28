package com.wishfulcloud.commons.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 金额工具类
 * 
 * 
 * @author wxz
 *
 */
public class MoneyUtils {
	
	/** 金额格式设定 000,000.00 */
	public static String setMoneyFormatD2(String money) {

		BigDecimal dbMoney = null;
		DecimalFormat dfMoney = new DecimalFormat("###,##0.00");

		if (money != null && !"".equals(money)) {
			dbMoney = new BigDecimal(money);
			money = dfMoney.format(dbMoney);
		} else {
			money = dfMoney.format(0);
		}
		return money;
	}
	
	/** 金额格式设定 000000.00 */
	public static String setMoneyFormatR2(String money) {

		BigDecimal dbMoney = null;
		DecimalFormat dfMoney = new DecimalFormat("#####0.00");

		if (money != null && !"".equals(money)) {
			dbMoney = new BigDecimal(money);
			money = dfMoney.format(dbMoney);
		} else {
			money = dfMoney.format(0);
		}
		return money;
	}
	
	/**
	 * 金额格式设定,去掉多余的.与0
	 * 
	 * @param money
	 * @return
	 */
	public static String setMoneyFormatR3(String money) {
		if (StringUtils.isBlank(money)) {
			return money;
		}
		if (money.indexOf(".") > 0) {
			money = money.replaceAll("0+?$", "");// 去掉多余的0
			money = money.replaceAll("[.]$", "");// 如最后一位是.则去掉
		}
		return money;
	}
	
	/** 金额格式设定 000000 */
	public static String setMoneyFormatR(String money) {

		BigDecimal dbMoney = null;
		DecimalFormat dfMoney = new DecimalFormat("######");

		if (money != null && !"".equals(money)) {
			dbMoney = new BigDecimal(money);
			dfMoney.setRoundingMode(RoundingMode.DOWN);
			money = dfMoney.format(dbMoney);
		} else {
			money = dfMoney.format(0);
		}
		return money;
	}
	
	/** 金额格式设定：x.x万 */
	public static String setMoneyFormatW1(String money) {
		BigDecimal dbMoney = null;
		DecimalFormat dfMoney = new DecimalFormat("#####0.0");

		if (money != null && !"".equals(money)) {
			dbMoney = new BigDecimal(money);
			dbMoney = dbMoney.divide(new BigDecimal(10000));
			dfMoney.setRoundingMode(RoundingMode.DOWN);
			money = dfMoney.format(dbMoney);
		} else {
			money = dfMoney.format(0);
		}
		return money;

	}
	
	/** 金额格式设定：x万 */
	public static String setMoneyFormatW(String money) {
		BigDecimal dbMoney = null;
		DecimalFormat dfMoney = new DecimalFormat("######");

		if (money != null && !"".equals(money)) {
			dbMoney = new BigDecimal(money);
			dbMoney = dbMoney.divide(new BigDecimal(10000));
			dfMoney.setRoundingMode(RoundingMode.DOWN);
			money = dfMoney.format(dbMoney);
		} else {
			money = dfMoney.format(0);
		}
		return money;

	}
	
	/** 金额格式设定：x.xx万 */
	public static String setMoneyFormatW2(String money) {
		BigDecimal dbMoney = null;
		DecimalFormat dfMoney = new DecimalFormat("#####0.00");

		if (money != null && !"".equals(money)) {
			dbMoney = new BigDecimal(money);
			dbMoney = dbMoney.divide(new BigDecimal(10000));
			dfMoney.setRoundingMode(RoundingMode.DOWN);
			money = dfMoney.format(dbMoney);
		} else {
			money = dfMoney.format(0);
		}
		return money;

	}
	
	/**
	 * 判断字符串是否为数字
	 * 
	 * @param str
	 * @return true:是 | false:不是
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("^(-?[1-9]\\d*\\.?\\d*)|(-?0\\.\\d*[1-9])|(-?[0])|(-?[0]\\.\\d*)$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 验证金额是否合法
	 * 验证规则：金额整数位必须大于0 ，如果有小数且点后保留两位
	 * 
	 * 可行的: 999999999.99  999999999. 99999999.00  999999999.9
	 * 		   xxxxxxxxx.xx xxxxxxxxx. xxxxxxxxx.xx  xxxxxxxxx.x
	 * 
	 * @return true 正确 false 错误
	 */
	public static Boolean rexMoney(String money) {
		
		money = money.trim();
		if (money == null) {
			return false;
		}
		if ("".equals(money.replace(" ", "").trim())) {
			return false;
		}
		
		String strMoney = new BigDecimal(money).toString();

		String reg = "^[+]?(([1-9]\\d*[.]?))(\\d{0,2})?$";
		boolean flag = Pattern.compile(reg).matcher(strMoney).find();
		return flag;
	}
	
	/**
	 * 是否含有小数点，且小数点后最多两位
	 * 
	 * @param money
	 * @return 没有小数点的情况返回true ,
	 * 		      满足含有小数点，且小数点后最多两位则返回 true，否则返回false
	 */
	public static Boolean includePoint2Len2(String money){
		
		if (!money.contains(".")){ // 没有小数位
			return true;
		}
		
		// 含有小数位则执行如下代码
    	int index = money.indexOf(".");
    	money = money.substring(index + 1, money.length());
		
    	if (money.length() > 2){
    		return false;
    	}
    	
    	if (money.length() == 0){
    		return false;
    	}
		return true;
	}
	
	
	
}
