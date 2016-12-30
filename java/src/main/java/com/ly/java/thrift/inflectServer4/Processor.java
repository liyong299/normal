package com.ly.java.thrift.inflectServer4;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Processor<I extends Iface> extends org.apache.thrift.TBaseProcessor<I> implements
		org.apache.thrift.TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(I iface) {
      super(iface, getProcessMap(new HashMap<String, org.apache.thrift.ProcessFunction<I, ? extends org.apache.thrift.TBase>>()));
    }

    protected Processor(I iface, Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      super(iface, getProcessMap(processMap));
    }

    private static <I extends Iface> Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> getProcessMap(Map<String,  org.apache.thrift.ProcessFunction<I, ? extends  org.apache.thrift.TBase>> processMap) {
      processMap.put("invoke", new invoke());
      return processMap;
    }

    public static class invoke<I extends Iface> extends org.apache.thrift.ProcessFunction<I, invoke_args> {
      public invoke() {
        super("invoke");
      }

      public invoke_args getEmptyArgsInstance() {
        return new invoke_args();
      }

      protected boolean isOneway() {
        return false;
      }

      public invoke_result getResult(I iface, invoke_args args) throws org.apache.thrift.TException {
        invoke_result result = new invoke_result();
        result.success = iface.invoke(args.uri, args.request);
        return result;
      }
    }

  }