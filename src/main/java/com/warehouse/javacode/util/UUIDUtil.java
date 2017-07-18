package com.warehouse.javacode.util;

import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * UUID生成主键规则
 * @author shunjeh@ncsi.com.cn
 * @date   Sep 20, 2016 3:51:41 PM
 */
public class UUIDUtil {

	private UUIDUtil(){
	}
	
	/**
	 * 升成主键id
	 * @return uuid
	 */
	public static String getUUID() {
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmssSSS");
		StringBuilder result = new StringBuilder(format.format(System.currentTimeMillis()));
		Random random = new Random();
		result.append(random.nextInt(10000) + 10000);
		return result.toString();
	}
}