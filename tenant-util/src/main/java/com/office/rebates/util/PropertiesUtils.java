package com.office.rebates.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * 属性文件读取
 * 
 * @author xxm
 *
 */
public class PropertiesUtils {

	static Logger logger = Logger.getLogger(PropertiesUtils.class);

	public static Map<String, String> prop = null;

	static {
		Properties properties = new Properties();
		HashMap<String, String> tmpProps = new HashMap<String, String>();
		try {
			properties.load(PropertiesUtils.class.getClassLoader().getResourceAsStream("config/config.properties"));
			Set<Map.Entry<Object, Object>> entries = properties.entrySet();
			Iterator<Map.Entry<Object, Object>> iter = entries.iterator();
			while (iter.hasNext()) {
				Map.Entry<Object, Object> item = iter.next();
				tmpProps.put(item.getKey().toString(), item.getValue().toString());
			}
			prop = Collections.unmodifiableMap(tmpProps);
		} catch (IOException e) {
			logger.error("fail to get properties from config.progerties", e);
		}
	}
	
	public static void main(String[] args)  {
		System.out.println("props is:"+prop);
	}
}
