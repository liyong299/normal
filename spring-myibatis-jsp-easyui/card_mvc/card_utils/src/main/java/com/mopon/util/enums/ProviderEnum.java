package com.mopon.util.enums;

/**
 * 
  * @ClassName: ProviderEnum
  * @Description: 接入商枚举项,值和SCEC保存一致
  * @author Comsys-mopon
  * @date 2015年7月7日 下午4:55:09
  *
 */
public enum ProviderEnum {	
	HLNCINEMA("1"), //火烈鸟 
	DXCINEMA("2"),  //鼎新
	HFHCINEMA("3"), //火凤凰
	MTXCINEMA("4"), //满天星
	
	DADICINEMA("5"), //大地
	MCLCINEMA("6"), //洲立
	JYCINEMA("7"), //金逸
	WYCINEMA("8"); //微影
	
	private String id;	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	ProviderEnum(String id){
		this.setId(id);
	}
	
}
