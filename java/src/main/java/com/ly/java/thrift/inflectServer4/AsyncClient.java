package com.ly.java.thrift.inflectServer4;


public class AsyncClient extends org.apache.thrift.async.TAsyncClient implements AsyncIface {
	public static class Factory implements org.apache.thrift.async.TAsyncClientFactory<AsyncClient> {
		private org.apache.thrift.async.TAsyncClientManager clientManager;
		private org.apache.thrift.protocol.TProtocolFactory protocolFactory;

		public Factory(org.apache.thrift.async.TAsyncClientManager clientManager,
				org.apache.thrift.protocol.TProtocolFactory protocolFactory) {
			this.clientManager = clientManager;
			this.protocolFactory = protocolFactory;
		}

		public AsyncClient getAsyncClient(org.apache.thrift.transport.TNonblockingTransport transport) {
			return new AsyncClient(protocolFactory, clientManager, transport);
		}
	}

	public AsyncClient(org.apache.thrift.protocol.TProtocolFactory protocolFactory,
			org.apache.thrift.async.TAsyncClientManager clientManager,
			org.apache.thrift.transport.TNonblockingTransport transport) {
		super(protocolFactory, clientManager, transport);
	}

	public void invoke(String uri, String request, org.apache.thrift.async.AsyncMethodCallback resultHandler)
			throws org.apache.thrift.TException {
		checkReady();
		invoke_call method_call = new invoke_call(uri, request, resultHandler, this, ___protocolFactory,
				___transport);
		this.___currentMethod = method_call;
		___manager.call(method_call);
	}

	public static class invoke_call extends org.apache.thrift.async.TAsyncMethodCall {
		private String uri;
		private String request;

		public invoke_call(String uri, String request,
				org.apache.thrift.async.AsyncMethodCallback resultHandler,
				org.apache.thrift.async.TAsyncClient client,
				org.apache.thrift.protocol.TProtocolFactory protocolFactory,
				org.apache.thrift.transport.TNonblockingTransport transport)
				throws org.apache.thrift.TException {
			super(client, protocolFactory, transport, resultHandler, false);
			this.uri = uri;
			this.request = request;
		}

		public void write_args(org.apache.thrift.protocol.TProtocol prot) throws org.apache.thrift.TException {
			prot.writeMessageBegin(new org.apache.thrift.protocol.TMessage("invoke",
					org.apache.thrift.protocol.TMessageType.CALL, 0));
			invoke_args args = new invoke_args();
			args.setUri(uri);
			args.setRequest(request);
			args.write(prot);
			prot.writeMessageEnd();
		}

		public String getResult() throws org.apache.thrift.TException {
			if (getState() != org.apache.thrift.async.TAsyncMethodCall.State.RESPONSE_READ) {
				throw new IllegalStateException("Method call not finished!");
			}
			org.apache.thrift.transport.TMemoryInputTransport memoryTransport = new org.apache.thrift.transport.TMemoryInputTransport(
					getFrameBuffer().array());
			org.apache.thrift.protocol.TProtocol prot = client.getProtocolFactory().getProtocol(
					memoryTransport);
			return (new Client(prot)).recv_invoke();
		}
	}

}