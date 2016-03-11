package com.mopon.util;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

/**
 * 
 * <p>Title: 字符串饭序列号化为日期</p>
 * <p>Description: </p>
 * <p>Copyright:Copyright(c)2014</p>
 * <p>Company:MOPON</p>
 * @date 2014-10-17
 * @version 1.0
 */
public class DateJsonDeserializer extends JsonDeserializer<Date>{
	
	@Override  
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {  
        try {  
        	return DateUtils.smartFormat(jp.getText());
        } catch (Exception e) {
            return new Date(jp.getLongValue());  
        }  
    }  

}
