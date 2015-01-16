/*
 * PropertyPlaceholder.java, 2012-11-14 下午05:22:34 xyluo
 * 
 * Copyright (c) 2011 Shanghai Qi(dea) Information Technology Co.,Ltd 
 * All rights reserved.
 * 
 * This software is copyrighted and owned by Qi(dea) or the copyright holder
 * specified, unless otherwise noted, and may not be reproduced or distributed
 * in whole or in part in any form or medium without express written permission.
 */
package com.qidea.payment;

import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * 
 * @author xyluo
 * @version 1.0, 2012-11-14 
 * @since 1.0
 */
public class PropertyPlaceholder extends PropertyPlaceholderConfigurer {

	private Map<String, String> contextProperties = new Hashtable<String, String>();
	
	protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException { 
        super.processProperties(beanFactoryToProcess, props);
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            contextProperties.put(keyStr, value);
        }
    }
 
    public String getContextProperty(String name) {
        return contextProperties.get(name);
    }
}