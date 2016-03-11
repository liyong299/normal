package com.mopon.entity.report;

public class BaseCinema {
    private Integer cinemano;

    private String cinemaname;

    private Short hallcount;

    private String provinceno;

    private String province;

    private String cityno;

    private String city;

    private String countyno;

    private String county;

    private String address;

    private String logo;

    private String url;

    private String tel;

    private String devicepos;

    private String deviceimg;

    private Short grade;

    private String intro;

    private String busline;

    private String longitude;

    private String latitude;

    private String feature;

    private Byte provider;

    private Byte printmode;

    private String cinemamd5;

    private String hallmd5;

    private Integer status;

    private Integer seatSale;

    private Integer commonSale;
    
    private Integer cinemaLineId;

    public Integer getCinemaLineId() {
		return cinemaLineId;
	}

	public void setCinemaLineId(Integer cinemaLineId) {
		this.cinemaLineId = cinemaLineId;
	}

	public Integer getCinemano() {
        return cinemano;
    }

    public void setCinemano(Integer cinemano) {
        this.cinemano = cinemano;
    }

    public String getCinemaname() {
        return cinemaname;
    }

    public void setCinemaname(String cinemaname) {
        this.cinemaname = cinemaname == null ? null : cinemaname.trim();
    }

    public Short getHallcount() {
        return hallcount;
    }

    public void setHallcount(Short hallcount) {
        this.hallcount = hallcount;
    }

    public String getProvinceno() {
        return provinceno;
    }

    public void setProvinceno(String provinceno) {
        this.provinceno = provinceno == null ? null : provinceno.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCityno() {
        return cityno;
    }

    public void setCityno(String cityno) {
        this.cityno = cityno == null ? null : cityno.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountyno() {
        return countyno;
    }

    public void setCountyno(String countyno) {
        this.countyno = countyno == null ? null : countyno.trim();
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county == null ? null : county.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getDevicepos() {
        return devicepos;
    }

    public void setDevicepos(String devicepos) {
        this.devicepos = devicepos == null ? null : devicepos.trim();
    }

    public String getDeviceimg() {
        return deviceimg;
    }

    public void setDeviceimg(String deviceimg) {
        this.deviceimg = deviceimg == null ? null : deviceimg.trim();
    }

    public Short getGrade() {
        return grade;
    }

    public void setGrade(Short grade) {
        this.grade = grade;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getBusline() {
        return busline;
    }

    public void setBusline(String busline) {
        this.busline = busline == null ? null : busline.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature == null ? null : feature.trim();
    }

    public Byte getProvider() {
        return provider;
    }

    public void setProvider(Byte provider) {
        this.provider = provider;
    }

    public Byte getPrintmode() {
        return printmode;
    }

    public void setPrintmode(Byte printmode) {
        this.printmode = printmode;
    }

    public String getCinemamd5() {
        return cinemamd5;
    }

    public void setCinemamd5(String cinemamd5) {
        this.cinemamd5 = cinemamd5 == null ? null : cinemamd5.trim();
    }

    public String getHallmd5() {
        return hallmd5;
    }

    public void setHallmd5(String hallmd5) {
        this.hallmd5 = hallmd5 == null ? null : hallmd5.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSeatSale() {
        return seatSale;
    }

    public void setSeatSale(Integer seatSale) {
        this.seatSale = seatSale;
    }

    public Integer getCommonSale() {
        return commonSale;
    }

    public void setCommonSale(Integer commonSale) {
        this.commonSale = commonSale;
    }
}