package com.ly.java.thrift.inflectServer2;

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

public class sayHello_result implements
		org.apache.thrift.TBase<sayHello_result, sayHello_result._Fields>, java.io.Serializable, Cloneable,
		Comparable<sayHello_result> {
	private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct(
			"sayHello_result");

	private static final org.apache.thrift.protocol.TField SUCCESS_FIELD_DESC = new org.apache.thrift.protocol.TField(
			"success", org.apache.thrift.protocol.TType.STRING, (short) 0);

	private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
	static {
		schemes.put(StandardScheme.class, new sayHello_resultStandardSchemeFactory());
		schemes.put(TupleScheme.class, new sayHello_resultTupleSchemeFactory());
	}

	public String success; // required

	/**
	* The set of fields this struct contains, along with convenience
	* methods for finding and manipulating them.
	*/
	public enum _Fields implements org.apache.thrift.TFieldIdEnum {
		SUCCESS((short) 0, "success");

		private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

		static {
			for (_Fields field : EnumSet.allOf(_Fields.class)) {
				byName.put(field.getFieldName(), field);
			}
		}

		/**
		 * Find the _Fields constant that matches fieldId, or null if its
		 * not found.
		 */
		public static _Fields findByThriftId(int fieldId) {
			switch (fieldId) {
			case 0: // SUCCESS
				return SUCCESS;
			default:
				return null;
			}
		}

		/**
		 * Find the _Fields constant that matches fieldId, throwing an
		 * exception if it is not found.
		 */
		public static _Fields findByThriftIdOrThrow(int fieldId) {
			_Fields fields = findByThriftId(fieldId);
			if (fields == null)
				throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
			return fields;
		}

		/**
		 * Find the _Fields constant that matches name, or null if its not
		 * found.
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
		tmpMap.put(_Fields.SUCCESS, new org.apache.thrift.meta_data.FieldMetaData("success",
				org.apache.thrift.TFieldRequirementType.DEFAULT,
				new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
		metaDataMap = Collections.unmodifiableMap(tmpMap);
		org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(sayHello_result.class, metaDataMap);
	}

	public sayHello_result() {}

	public sayHello_result(String success) {
		this();
		this.success = success;
	}

	/**
	* Performs a deep copy on <i>other</i>.
	*/
	public sayHello_result(sayHello_result other) {
		if (other.isSetSuccess()) {
			this.success = other.success;
		}
	}

	public sayHello_result deepCopy() {
		return new sayHello_result(this);
	}

	@Override
	public void clear() {
		this.success = null;
	}

	public String getSuccess() {
		return this.success;
	}

	public sayHello_result setSuccess(String success) {
		this.success = success;
		return this;
	}

	public void unsetSuccess() {
		this.success = null;
	}

	/**
	* Returns true if field success is set (has been assigned a value) and
	* false otherwise
	*/
	public boolean isSetSuccess() {
		return this.success != null;
	}

	public void setSuccessIsSet(boolean value) {
		if (!value) {
			this.success = null;
		}
	}

	public void setFieldValue(_Fields field, Object value) {
		switch (field) {
		case SUCCESS:
			if (value == null) {
				unsetSuccess();
			} else {
				setSuccess((String) value);
			}
			break;

		}
	}

	public Object getFieldValue(_Fields field) {
		switch (field) {
		case SUCCESS:
			return getSuccess();

		}
		throw new IllegalStateException();
	}

	/**
	* Returns true if field corresponding to fieldID is set (has been
	* assigned a value) and false otherwise
	*/
	public boolean isSet(_Fields field) {
		if (field == null) {
			throw new IllegalArgumentException();
		}

		switch (field) {
		case SUCCESS:
			return isSetSuccess();
		}
		throw new IllegalStateException();
	}

	@Override
	public boolean equals(Object that) {
		if (that == null)
			return false;
		if (that instanceof sayHello_result)
			return this.equals((sayHello_result) that);
		return false;
	}

	public boolean equals(sayHello_result that) {
		if (that == null)
			return false;

		boolean this_present_success = true && this.isSetSuccess();
		boolean that_present_success = true && that.isSetSuccess();
		if (this_present_success || that_present_success) {
			if (!(this_present_success && that_present_success))
				return false;
			if (!this.success.equals(that.success))
				return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		List<Object> list = new ArrayList<Object>();

		boolean present_success = true && (isSetSuccess());
		list.add(present_success);
		if (present_success)
			list.add(success);

		return list.hashCode();
	}

	@Override
	public int compareTo(sayHello_result other) {
		if (!getClass().equals(other.getClass())) {
			return getClass().getName().compareTo(other.getClass().getName());
		}

		int lastComparison = 0;

		lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(other.isSetSuccess());
		if (lastComparison != 0) {
			return lastComparison;
		}
		if (isSetSuccess()) {
			lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.success, other.success);
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
		StringBuilder sb = new StringBuilder("sayHello_result(");
		boolean first = true;

		sb.append("success:");
		if (this.success == null) {
			sb.append("null");
		} else {
			sb.append(this.success);
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

	private static class sayHello_resultStandardSchemeFactory implements SchemeFactory {
		public sayHello_resultStandardScheme getScheme() {
			return new sayHello_resultStandardScheme();
		}
	}

	private static class sayHello_resultStandardScheme extends StandardScheme<sayHello_result> {

		public void read(org.apache.thrift.protocol.TProtocol iprot, sayHello_result struct)
				throws org.apache.thrift.TException {
			org.apache.thrift.protocol.TField schemeField;
			iprot.readStructBegin();
			while (true) {
				schemeField = iprot.readFieldBegin();
				if (schemeField.type == org.apache.thrift.protocol.TType.STOP) {
					break;
				}
				switch (schemeField.id) {
				case 0: // SUCCESS
					if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
						struct.success = iprot.readString();
						struct.setSuccessIsSet(true);
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

			// check for required fields of primitive type, which can't be
			// checked in the validate method
			struct.validate();
		}

		public void write(org.apache.thrift.protocol.TProtocol oprot, sayHello_result struct)
				throws org.apache.thrift.TException {
			struct.validate();

			oprot.writeStructBegin(STRUCT_DESC);
			if (struct.success != null) {
				oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
				oprot.writeString(struct.success);
				oprot.writeFieldEnd();
			}
			oprot.writeFieldStop();
			oprot.writeStructEnd();
		}

	}

	private static class sayHello_resultTupleSchemeFactory implements SchemeFactory {
		public sayHello_resultTupleScheme getScheme() {
			return new sayHello_resultTupleScheme();
		}
	}

	private static class sayHello_resultTupleScheme extends TupleScheme<sayHello_result> {

		@Override
		public void write(org.apache.thrift.protocol.TProtocol prot, sayHello_result struct)
				throws org.apache.thrift.TException {
			TTupleProtocol oprot = (TTupleProtocol) prot;
			BitSet optionals = new BitSet();
			if (struct.isSetSuccess()) {
				optionals.set(0);
			}
			oprot.writeBitSet(optionals, 1);
			if (struct.isSetSuccess()) {
				oprot.writeString(struct.success);
			}
		}

		@Override
		public void read(org.apache.thrift.protocol.TProtocol prot, sayHello_result struct)
				throws org.apache.thrift.TException {
			TTupleProtocol iprot = (TTupleProtocol) prot;
			BitSet incoming = iprot.readBitSet(1);
			if (incoming.get(0)) {
				struct.success = iprot.readString();
				struct.setSuccessIsSet(true);
			}
}
	}

}