/**
 *
 */
package com.mopon.util.entity;

import java.io.Serializable;

/**
 * <p>Description: 影院类 </p>
 * @date 2015年4月14日
 * @author Aaron
 * <p>Company:Mopon</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class Cinema implements Serializable{

	public Cinema() {
	}
	/**
	 * 编码
	 */
	private String code;
	/**
	 * 影院名称
	 */
	private String name;
	/**
	 * 应听数量
	 */
	private int hallCount;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 省份名称
	 */
	private String provinceCode;
	/**
	 *
	 */
	private String city;
	/**
	 * 城市编码
	 */
	private String cityCode;
	/**
	 * 辖区
	 */
	private String county;
	/**
	 * 辖区编码
	 */
	private String countyCode;
	/**
	 * d地址
	 */
	private String address;
	/**
	 * logo URL 地址
	 */
	private String logo;
	/**
	 * 影院网站
	 */
	private String url;
	/**
	 * 客服电话
	 */
	private String tel;
	/**
	 * 终端位置说明
	 */
	private String devicePos;
	/**
	 * 终端位置图片
	 */
	private String deviceImg;
	/**
	 * 综合评分
	 */
	private String grade;
	/**
	 * 简介
	 */
	private String intro;
	/**
	 * 公交线路
	 */
	private String busLine;
	/**
	 * 经度
	 */
	private String longitude;
	/**
	 * 纬度
	 */
	private String latitude;
	/**
	 * 特色
	 */
	private String feature;
	/**
	 * 接入商类型（1：火烈鸟 2：鼎新 3：火凤凰 4：满天星）
	 */
	private String provider;
	/**
	 * 短信类型（1：地面取票号；2：平台凭证号；3：混合方式）
	 */
	private String printMode;
	/**
	 * 用于类的入库时检查是否存在
	 */
	private String cinemamd5;
	/**
	 * 影院下厅
	 */
	private String hallmd5;
	/**
	 * 商品类型 0:选座票 1 通兑票(多个以，)
	 */
	private String goodsType;
	/**
	 * 商品兑换类型 0：2D 1:3D 2:IMAX2D 3:IMAX3D 4:中国巨幕2D 5：中国巨幕3D(多个以，隔开)
	 */
	private String goodsShowType;
	/**
	 * 定价类型 0：常规价 1：闲忙时价
	 */
	private String priceType;

	private Integer viewGoodsType;

	private String seatSale;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHallCount() {
		return hallCount;
	}
	public void setHallCount(int hallCount) {
		this.hallCount = hallCount;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getProvinceCode() {
		return provinceCode;
	}
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getCountyCode() {
		return countyCode;
	}
	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDevicePos() {
		return devicePos;
	}
	public void setDevicePos(String devicePos) {
		this.devicePos = devicePos;
	}
	public String getDeviceImg() {
		return deviceImg;
	}
	public void setDeviceImg(String deviceImg) {
		this.deviceImg = deviceImg;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getBusLine() {
		return busLine;
	}
	public void setBusLine(String busLine) {
		this.busLine = busLine;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getFeature() {
		return feature;
	}
	public void setFeature(String feature) {
		this.feature = feature;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getPrintMode() {
		return printMode;
	}
	public void setPrintMode(String printMode) {
		this.printMode = printMode;
	}

	@Override
	public String toString() {
		return "Cinema [code=" + code + ", name=" + name + ", hallCount="
				+ hallCount + ", province=" + province + ", provinceCode="
				+ provinceCode + ", city=" + city + ", cityCode=" + cityCode
				+ ", county=" + county + ", countyCode=" + countyCode
				+ ", address=" + address + ", logo=" + logo + ", url=" + url
				+ ", tel=" + tel + ", devicePos=" + devicePos + ", deviceImg="
				+ deviceImg + ", grade=" + grade + ", intro=" + intro
				+ ", busLine=" + busLine + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", feature=" + feature
				+ ", provider=" + provider + ", printMode=" + printMode
				+ "]";
	}
	public String getCinemamd5() {
		return cinemamd5;
	}
	public void setCinemamd5(String cinemamd5) {
		this.cinemamd5 = cinemamd5;
	}
	public String getHallmd5() {
		return hallmd5;
	}
	public void setHallmd5(String hallmd5) {
		this.hallmd5 = hallmd5;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsShowType() {
		return goodsShowType;
	}
	public void setGoodsShowType(String goodsShowType) {
		this.goodsShowType = goodsShowType;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
	public Integer getViewGoodsType() {
		return viewGoodsType;
	}
	public void setViewGoodsType(Integer viewGoodsType) {
		this.viewGoodsType = viewGoodsType;
	}
	public String getSeatSale() {
		return seatSale;
	}
	public void setSeatSale(String seatSale) {
		this.seatSale = seatSale;
	}



}
