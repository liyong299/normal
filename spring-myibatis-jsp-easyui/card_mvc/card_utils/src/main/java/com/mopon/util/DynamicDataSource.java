package com.mopon.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource{
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>(); 
	
	public static void setSlave() {  
        contextHolder.set("slave");  
    } 
	public static void setMaster() {  
        contextHolder.remove();  
    } 
	@Override
	protected Object determineCurrentLookupKey() { 
		return ((String)contextHolder.get());
	}
	
}
