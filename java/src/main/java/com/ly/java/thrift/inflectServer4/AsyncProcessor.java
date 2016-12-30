package com.ly.java.thrift.inflectServer4;

import java.util.HashMap;
import java.util.Map;

import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.AsyncFrameBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AsyncProcessor<I extends AsyncIface> extends org.apache.thrift.TBaseAsyncProcessor<I> {
	private static final Logger LOGGER = LoggerFactory.getLogger(AsyncProcessor.class.getName());

	public AsyncProcessor(I iface) {
		super(
				iface,
				getProcessMap(new HashMap<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>>()));
	}

	protected AsyncProcessor(
			I iface,
			Map<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>> processMap) {
		super(iface, getProcessMap(processMap));
	}

	private static <I extends AsyncIface> Map<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>> getProcessMap(
			Map<String, org.apache.thrift.AsyncProcessFunction<I, ? extends org.apache.thrift.TBase, ?>> processMap) {
		processMap.put("invoke", new invoke());
		return processMap;
	}

	public static class invoke<I extends AsyncIface> extends
			org.apache.thrift.AsyncProcessFunction<I, invoke_args, String> {
		public invoke() {
			super("invoke");
		}

		public invoke_args getEmptyArgsInstance() {
			return new invoke_args();
		}

		public AsyncMethodCallback<String> getResultHandler(final AsyncFrameBuffer fb, final int seqid) {
			final org.apache.thrift.AsyncProcessFunction fcall = this;
			return new AsyncMethodCallback<String>() {
				public void onComplete(String o) {
					invoke_result result = new invoke_result();
					result.success = o;
					try {
						fcall.sendResponse(fb, result, org.apache.thrift.protocol.TMessageType.REPLY, seqid);
						return;
					} catch (Exception e) {
						LOGGER.error("Exception writing to internal frame buffer", e);
					}
					fb.close();
				}

				public void onError(Exception e) {
					byte msgType = org.apache.thrift.protocol.TMessageType.REPLY;
					org.apache.thrift.TBase msg;
					invoke_result result = new invoke_result();
					{
						msgType = org.apache.thrift.protocol.TMessageType.EXCEPTION;
						msg = (org.apache.thrift.TBase) new org.apache.thrift.TApplicationException(
								org.apache.thrift.TApplicationException.INTERNAL_ERROR, e.getMessage());
					}
					try {
						fcall.sendResponse(fb, msg, msgType, seqid);
						return;
					} catch (Exception ex) {
						LOGGER.error("Exception writing to internal frame buffer", ex);
					}
					fb.close();
				}
			};
		}

		protected boolean isOneway() {
			return false;
		}

		public void start(I iface, invoke_args args,
				org.apache.thrift.async.AsyncMethodCallback<String> resultHandler) throws TException {
			iface.invoke(args.uri, args.request, resultHandler);
		}
	}

}