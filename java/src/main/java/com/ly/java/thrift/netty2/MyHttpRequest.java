/**
 * 
 */
package com.ly.java.thrift.netty2;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义的Http请求
 * 
 * @author ly
 * 
 */
public class MyHttpRequest extends DefaultHttpRequest {

	private HttpHeaders trailingHeader;
	private boolean validateHeaders;
	private final Map<String, List<String>> multipartParameters = new HashMap();

	public MyHttpRequest(HttpVersion httpVersion, HttpMethod method, String uri) {
		super(httpVersion, method, uri);
	}

	public void resolveMultipart(String name, List<String> values) {
		this.multipartParameters.put(name, values);
	}

	public void resolveMultipart(Map<String, List<String>> params) {
		for (String key : params.keySet()) {
			this.multipartParameters.put(key, params.get(key));
		}
	}

	public HttpHeaders getTrailingHeader() {
		return trailingHeader;
	}

	public void setTrailingHeader(HttpHeaders trailingHeader) {
		this.trailingHeader = trailingHeader;
	}

	public boolean isValidateHeaders() {
		return validateHeaders;
	}

	public void setValidateHeaders(boolean validateHeaders) {
		this.validateHeaders = validateHeaders;
	}

	public Map<String, List<String>> getMultipartParameters() {
		return multipartParameters;
	}

	public List<String> getParameterValues(String name) {
		List<String> values = (List) this.multipartParameters.get(name);
		if (values != null) {
			return values;
		}
		return null;
	}

}
