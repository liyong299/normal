/**
 * 项目名称：ly.test.tomcat
 * 文件包名：com.ly.test.tomcat.web.entities
 * 文件名称：WebUtil.java
 * 版本信息：SCEC_Branches
 * 生成日期：2016年2月24日 下午3:42:17
 * Copyright (c) 2015-2015深圳市泰久信息系统股份有限公司
 * 
 */
package com.ly.test.tomcat.web.entities;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.tomcat.util.buf.ByteChunk;
import org.apache.tomcat.util.buf.UDecoder;
import org.apache.tomcat.util.http.Parameters.FailReason;

/**
 * @author ly
 * 
 */
public class WebUtil
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	static String DEFAULT_CHARSET = "utf-8";
	private UDecoder urlDec = new UDecoder();

	public void processParameters(byte bytes[], int start, int len, Charset charset)
	{
		int decodeFailCount = 0;

		int pos = start;
		int end = start + len;

		while (pos < end)
		{
			int nameStart = pos;
			int nameEnd = -1;
			int valueStart = -1;
			int valueEnd = -1;

			boolean parsingName = true;
			boolean decodeName = false;
			boolean decodeValue = false;
			boolean parameterComplete = false;

			do
			{
				switch (bytes[pos])
				{
				case '=':
					if (parsingName)
					{
						// Name finished. Value starts from next character
						nameEnd = pos;
						parsingName = false;
						valueStart = ++pos;
					}
					else
					{
						// Equals character in value
						pos++;
					}
					break;
				case '&':
					if (parsingName)
					{
						// Name finished. No value.
						nameEnd = pos;
					}
					else
					{
						// Value finished
						valueEnd = pos;
					}
					parameterComplete = true;
					pos++;
					break;
				case '%':
				case '+':
					// Decoding required
					if (parsingName)
					{
						decodeName = true;
					}
					else
					{
						decodeValue = true;
					}
					pos++;
					break;
				default:
					pos++;
					break;
				}
			} while (!parameterComplete && pos < end);

			if (pos == end)
			{
				if (nameEnd == -1)
				{
					nameEnd = pos;
				}
				else if (valueStart > -1 && valueEnd == -1)
				{
					valueEnd = pos;
				}
			}
			if (nameEnd <= nameStart)
			{
				if (valueStart == -1)
				{
					// Do not flag as error
					continue;
				}

				continue;
				// invalid chunk - it's better to ignore
			}

			tmpName.setBytes(bytes, nameStart, nameEnd - nameStart);
			if (valueStart >= 0)
			{
				tmpValue.setBytes(bytes, valueStart, valueEnd - valueStart);
			}
			else
			{
				tmpValue.setBytes(bytes, 0, 0);
			}

			try
			{
				String name;
				String value;

				if (decodeName)
				{
					urlDecode(tmpName);
				}
				tmpName.setCharset(charset);
				name = tmpName.toString();

				if (valueStart >= 0)
				{
					if (decodeValue)
					{
						urlDecode(tmpValue);
					}
					tmpValue.setCharset(charset);
					value = tmpValue.toString();
				}
				else
				{
					value = "";
				}

				try
				{
					addParameter(name, value);
				}
				catch (IllegalStateException ise)
				{
					ise.printStackTrace();
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			tmpName.recycle();
			tmpValue.recycle();
		}
	}

	ByteChunk tmpName = new ByteChunk();
	ByteChunk tmpValue = new ByteChunk();
	private int limit = -1;
	private int parameterCount = 0;
	private final Map<String, ArrayList<String>> paramHashValues = new LinkedHashMap<String, ArrayList<String>>();

	public void addParameter(String key, String value) throws IllegalStateException
	{
		if (key == null)
		{
			return;
		}

		parameterCount++;
		if (limit > -1 && parameterCount > limit)
		{
			throw new IllegalStateException("超过最大数：" + limit);
		}

		ArrayList<String> values = paramHashValues.get(key);
		if (values == null)
		{
			values = new ArrayList<String>(1);
			paramHashValues.put(key, values);
		}
		values.add(value);
	}

	private void urlDecode(ByteChunk bc) throws IOException
	{
		if (urlDec == null)
		{
			urlDec = new UDecoder();
		}
		urlDec.convert(bc, true);
	}
}
