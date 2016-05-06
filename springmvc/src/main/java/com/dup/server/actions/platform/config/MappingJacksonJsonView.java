package com.dup.server.actions.platform.config;

import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class MappingJacksonJsonView extends
		MappingJackson2JsonView {
	
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
