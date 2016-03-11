package com.mopon.util;

import java.util.Properties;

import com.mopon.util.enums.SysType;

public class SysUtils {
	
	public static SysType getSysType()
	{
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if(os.startsWith("win")|| os.startsWith("Win"))
		{
			return SysType.WIN;
		}
		else{
			return SysType.UNIX;
		}
	}
}
