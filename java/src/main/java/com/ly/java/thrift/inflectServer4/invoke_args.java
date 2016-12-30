package com.ly.java.thrift.inflectServer4;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.scheme.TupleScheme;

public class invoke_args implements org.apache.thrift.TBase<invoke_args, invoke_args._Fields>,
		java.io.Serializable, Cloneable, Comparable<invoke_args> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1149718009321663873L;

	private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct(
			"invoke_args");

	private static final org.apache.thrift.protocol.TField URI_FIELD_DESC = new org.apache.thrift.protocol.TField(
			"uri", org.apache.thrift.protocol.TType.STRING, (short) 1);
	private static final org.apache.thrift.protocol.TField REQUEST_FIELD_DESC = new org.apache.thrift.protocol.TField(
			"request", org.apache.thrift.protocol.TType.STRING, (short) 2);

	private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
	static {
		schemes.put(StandardScheme.class, new invoke_argsStandardSchemeFactory());
		schemes.put(TupleScheme.class, new invoke_argsTupleSchemeFactory());
	}

	public String uri; // required
	public String request; // required

	/** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
	public enum _Fields implements org.apache.thrift.TFieldIdEnum {
		URI((short) 1, "uri"), REQUEST((short) 2, "request");

		private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

		static {
			for (_Fields field : EnumSet.allOf(_Fields.class)) {
				byName.put(field.getFieldName(), field);
			}
		}

		/**
		 * Find the _Fields constant that matches fieldId, or null if its not found.
		 */
		public static _Fields findByThriftId(int fieldId) {
			switch (fieldId) {
			case 1: // URI
				return URI;
			case 2: // REQUEST
				return REQUEST;
			default:
				return null;
			}
		}

		/**
		 * Find the _Fields constant that matches fieldId, throwing an exception
		 * if it is not found.
		 */
		public static _Fields findByThriftIdOrThrow(int fieldId) {
			_Fields fields = findByThriftId(fieldId);
			if (fields == null)
				throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
			return fields;
		}

		/**
		 * Find the _Fields constant that matches name, or null if its not found.
		 */
		public static _Fields findByName(String name) {
			return byName.get(name);
		}

		private final short _thriftId;
		private final String _fieldName;

		_Fields(short thriftId, String fieldName) {
			_thriftId = thriftId;
			_fieldName = fieldName;
		}

		public short getThriftFieldId() {
			return _thriftId;
		}

		public String getFieldName() {
			return _fieldName;
		}
	}

	// isset id assignments
	public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
	static {
		Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(
				_Fields.class);
		tmpMap.put(_Fields.URI, new org.apache.thrift.meta_data.FieldMetaData("uri",
				org.apache.thrift.TFieldRequirementType.DEFAULT,
				new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
		tmpMap.put(_Fields.REQUEST, new org.apache.thrift.meta_data.FieldMetaData("request",
				org.apache.thrift.TFieldRequirementType.DEFAULT,
				new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
		metaDataMap = Collections.unmodifiableMap(tmpMap);
		org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(invoke_args.class, metaDataMap);
	}

	public invoke_args() {}

	public invoke_args(String uri, String request) {
		this();
		this.uri = uri;
		this.request = request;
	}

	/**
	 * Performs a deep copy on <i>other</i>.
	 */
	public invoke_args(invoke_args other) {
		if (other.isSetUri()) {
			this.uri = other.uri;
		}
		if (other.isSetRequest()) {
			this.request = other.request;
		}
	}

	public invoke_args deepCopy() {
		return new invoke_args(this);
	}

	@Override
	public void clear() {
		this.uri = null;
		this.request = null;
	}

	public String getUri() {
		return this.uri;
	}

	public invoke_args setUri(String uri) {
		this.uri = uri;
		return this;
	}

	public void unsetUri() {
		this.uri = null;
	}

	/** Returns true if field uri is set (has been assigned a value) and false otherwise */
	public boolean isSetUri() {
		return this.uri != null;
	}

	public void setUriIsSet(boolean value) {
		if (!value) {
			this.uri = null;
		}
	}

	public String getRequest() {
		return this.request;
	}

	public invoke_args setRequest(String request) {
		this.request = request;
		return this;
	}

	public void unsetRequest() {
		this.request = null;
	}

	/** Returns true if field request is set (has been assigned a value) and false otherwise */
	public boolean isSetRequest() {
		return this.request != null;
	}

	public void setRequestIsSet(boolean value) {
		if (!value) {
			this.request = null;
		}
	}

	public void setFieldValue(_Fields field, Object value) {
		switch (field) {
		case URI:
			if (value == null) {
				unsetUri();
			} else {
				setUri((String) value);
			}
			break;

		case REQUEST:
			if (value == null) {
				unsetRequest();
			} else {
				setRequest((String) value);
			}
			break;

		}
	}

	public Object getFieldValue(_Fields field) {
		switch (field) {
		case URI:
			return getUri();

		case REQUEST:
			return getRequest();

		}
		throw new IllegalStateException();
	}

	/** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
	public boolean isSet(_Fields field) {
		if (field == null) {
			throw new IllegalArgumentException();
		}

		switch (field) {
		case URI:
			return isSetUri();
		case REQUEST:
			return isSetRequest();
		}
		throw new IllegalStateException();
	}

	@Override
	public boolean equals(Object that) {
		if (that == null)
			return false;
		if (that instanceof invoke_args)
			return this.equals((invoke_args) that);
		return false;
	}

	public boolean equals(invoke_args that) {
		if (that == null)
			return false;

		boolean this_present_uri = true && this.isSetUri();
		boolean that_present_uri = true && that.isSetUri();
		if (this_present_uri || that_present_uri) {
			if (!(this_present_uri && that_present_uri))
				return false;
			if (!this.uri.equals(that.uri))
				return false;
		}

		boolean this_present_request = true && this.isSetRequest();
		boolean that_present_request = true && that.isSetRequest();
		if (this_present_request || that_present_request) {
			if (!(this_present_request && that_present_request))
				return false;
			if (!this.request.equals(that.request))
				return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		List<Object> list = new ArrayList<Object>();

		boolean present_uri = true && (isSetUri());
		list.add(present_uri);
		if (present_uri)
			list.add(uri);

		boolean present_request = true && (isSetRequest());
		list.add(present_request);
		if (present_request)
			list.add(request);

		return list.hashCode();
	}

	@Override
	public int compareTo(invoke_args other) {
		if (!getClass().equals(other.getClass())) {
			return getClass().getName().compareTo(other.getClass().getName());
		}

		int lastComparison = 0;

		lastComparison = Boolean.valueOf(isSetUri()).compareTo(other.isSetUri());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetUri()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uri, other.uri);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		lastComparison = Boolean.valueOf(isSetRequest()).compareTo(other.isSetRequest());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetRequest()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.request, other.request);
			if (lastComparison != 0) {
				return lastComparison;
			}
		}
		return 0;
	}

	public _Fields fieldForId(int fieldId) {
		return _Fields.findByThriftId(fieldId);
	}

	public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
		schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
	}

	public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
		schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("invoke_args(");
		boolean first = true;

		sb.append("uri:");
		if (this.uri == null) {
			sb.append("null");
		} else {
			sb.append(this.uri);
		}
		first = false;
		if (!first)
			sb.append(", ");
		sb.append("request:");
		if (this.request == null) {
			sb.append("null");
		} else {
			sb.append(this.request);
		}
		first = false;
		sb.append(")");
		return sb.toString();
	}

	public void validate() throws org.apache.thrift.TException {
		// check for required fields
		// check for sub-struct validity
	}

	private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
		try {
			write(new org.apache.thrift.protocol.TCompactProtocol(
					new org.apache.thrift.transport.TIOStreamTransport(out)));
		} catch (org.apache.thrift.TException te) {
			throw new java.io.IOException(te);
		}
	}

	private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
		try {
			read(new org.apache.thrift.protocol.TCompactProtocol(
					new org.apache.thrift.transport.TIOStreamTransport(in)));
		} catch (org.apache.thrift.TException te) {
			throw new java.io.IOException(te);
		}
	}

	private static class invoke_argsStandardSchemeFactory implements SchemeFactory {
		public invoke_argsStandardScheme getScheme() {
			return new invoke_argsStandardScheme();
		}
	}

	private static class invoke_argsStandardScheme extends StandardScheme<invoke_args> {

		public void read(org.apache.thrift.protocol.TProtocol iprot, invoke_args struct)
				throws org.apache.thrift.TException {
			org.apache.thrift.protocol.TField schemeField;
			iprot.readStructBegin();
			while (true) {
				schemeField = iprot.readFieldBegin();
				if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
					break;
				}
				switch (schemeField.id) {
				case 1: // URI
					if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
						struct.uri = iprot.readString();
						struct.setUriIsSet(true);
					} else {
						org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
					}
					break;
				case 2: // REQUEST
					if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
						struct.request = iprot.readString();
						struct.setRequestIsSet(true);
					} else {
						org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
					}
					break;
				default:
					org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
				}
				iprot.readFieldEnd();
			}
			iprot.readStructEnd();

			// check for required fields of primitive type, which can't be checked in the validate method
			struct.validate();
		}

		public void write(org.apache.thrift.protocol.TProtocol oprot, invoke_args struct)
				throws org.apache.thrift.TException {
			struct.validate();

			oprot.writeStructBegin(STRUCT_DESC);
			if (struct.uri != null) {
				oprot.writeFieldBegin(URI_FIELD_DESC);
				oprot.writeString(struct.uri);
				oprot.writeFieldEnd();
			}
			if (struct.request != null) {
				oprot.writeFieldBegin(REQUEST_FIELD_DESC);
				oprot.writeString(struct.request);
				oprot.writeFieldEnd();
			}
			oprot.writeFieldStop();
			oprot.writeStructEnd();
		}

	}

	private static class invoke_argsTupleSchemeFactory implements SchemeFactory {
		public invoke_argsTupleScheme getScheme() {
			return new invoke_argsTupleScheme();
		}
	}

	private static class invoke_argsTupleScheme extends TupleScheme<invoke_args> {

		@Override
		public void write(org.apache.thrift.protocol.TProtocol prot, invoke_args struct)
				throws org.apache.thrift.TException {
			TTupleProtocol oprot = (TTupleProtocol) prot;
			BitSet optionals = new BitSet();
			if (struct.isSetUri()) {
				optionals.set(0);
			}
			if (struct.isSetRequest()) {
				optionals.set(1);
			}
			oprot.writeBitSet(optionals, 2);
			if (struct.isSetUri()) {
				oprot.writeString(struct.uri);
			}
			if (struct.isSetRequest()) {
				oprot.writeString(struct.request);
			}
		}

		@Override
		public void read(org.apache.thrift.protocol.TProtocol prot, invoke_args struct)
				throws org.apache.thrift.TException {
			TTupleProtocol iprot = (TTupleProtocol) prot;
			BitSet incoming = iprot.readBitSet(2);
			if (incoming.get(0)) {
				struct.uri = iprot.readString();
				struct.setUriIsSet(true);
			}
			if (incoming.get(1)) {
				struct.request = iprot.readString();
				struct.setRequestIsSet(true);
			}
		}
	}

}