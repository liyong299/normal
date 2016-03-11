package com.mopon.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 
 * <p>Title: 日期数据序列号</p>
 * <p>Description: </p>
 * <p>Copyright:Copyright(c)2014</p>
 * <p>Company:MOPON</p>
 * @date 2014-10-17
 * @version 1.0
 */
public class DateJsonSerializer extends JsonSerializer<Date> {

	@Override
	public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		try {
			jsonGenerator.writeString(new SimpleDateFormat("yyyyMMddHHmmss").format(date));
		} catch (Exception e) {
			jsonGenerator.writeString(String.valueOf(date.getTime()));
		}
	}

}
