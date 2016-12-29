package com.ly.java.thrift.inflectServer2;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements
		org.apache.thrift.TProcessor {
	private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());

	public Processor(I iface) {
		super(
				iface,
				getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
	}

	protected Processor(I iface,
			Map<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> processMap) {
		super(iface, getProcessMap(processMap));
	}

	private static <I extends Iface> Map<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> getProcessMap(
			Map<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>> processMap) {
		processMap.put("sayHello", new sayHello());
		return processMap;
	}



}