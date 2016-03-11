package com.mopon.util.enums;

public enum SysType {
		WIN(1),
		
		UNIX(2);
		
		private SysType(int code) {
			this.code = code;
		}

		private final int code;

		public int getCode() {
			return code;
		}
}
