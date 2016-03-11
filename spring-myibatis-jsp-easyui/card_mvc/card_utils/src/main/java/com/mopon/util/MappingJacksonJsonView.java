package com.mopon.util;

import java.util.Map;

public class MappingJacksonJsonView extends
		org.springframework.web.servlet.view.json.MappingJacksonJsonView {
	
	//public static final String DEFAULT_CONTENT_TYPE = "application/json";
	
	@Override
	protected Object filterModel(Map<String, Object> model) {
		this.setContentType(DEFAULT_CONTENT_TYPE);
		Map<?, ?> result = (Map<?, ?>) super.filterModel(model);    
	    if (result.size() == 1) {    
	        return result.values().iterator().next();    
	    } else {    
	        return result;    
	    }    
	}
	
}
