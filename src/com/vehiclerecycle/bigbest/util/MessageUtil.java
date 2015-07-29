package com.vehiclerecycle.bigbest.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 消息工具类
 * 
 * @author Ljj
 *
 */
public class MessageUtil {
	private static ResourceBundle resourceBundle;
	private static String resourcePath = "com.resouce.messages";
	private static Locale locale = Locale.getDefault();

	public static void setLoacle(Locale newlocale) {
		resourceBundle = ResourceBundle.getBundle(resourcePath, newlocale);
	}

	public static String getMessage(String key) {
		if (resourceBundle == null) {
			resourceBundle = ResourceBundle.getBundle(resourcePath, locale);
		}
		return resourceBundle.getString(key);
	}

}
