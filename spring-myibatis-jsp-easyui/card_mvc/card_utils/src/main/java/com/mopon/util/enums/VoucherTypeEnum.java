package com.mopon.util.enums;

/**
 * 凭证类型枚举
 * @author mopon
 *
 */
public enum VoucherTypeEnum {

	
	/**
	 * 单家
	 */
	SINGLE(2),
	/**
	 * 多家
	 */
	MULT(3),
	/**
	 * 选座
	 */
	SELECT(1),
	/**
	 * 卡券卡
	 */
	CARD(4);
	private VoucherTypeEnum(int code) {
		this.code = code;
	}

	private final int code;

	public int getCode() {
		return code;
	}
	
	/**
	  * getVoucherType(获取凭证类型)
	  * @Title: getVoucherType
	  * @Description: TODO
	  * @param @param voucherNo
	  * @param @return    设定文件
	  * @return VoucherTypeEnum    返回类型
	  * @throws
	 */
	public static VoucherTypeEnum getVoucherType(String voucherNo)
	{
		VoucherTypeEnum thisType = null;
		int voucherLength = voucherNo.length();
		String voucherBin = voucherNo.substring(0,1);
		//TODO 因为5.0只有卡券通兑票类型，所以这里根据长度判断是通兑票，待线下开发时，这里需要修改根据卡BIN判断类型
		switch(voucherLength)
		{
		case 8:
			thisType = VoucherTypeEnum.MULT;
			break;
		case 9:
			thisType = VoucherTypeEnum.MULT;
			break;
		case 10:
			thisType = VoucherTypeEnum.CARD;
			break;
		case 11:
			thisType = voucherBin.equals("2") ? VoucherTypeEnum.SINGLE:VoucherTypeEnum.SELECT;
			break;
		case 16:
			thisType = VoucherTypeEnum.CARD;
			break;
		}
		return thisType;
	}
}
