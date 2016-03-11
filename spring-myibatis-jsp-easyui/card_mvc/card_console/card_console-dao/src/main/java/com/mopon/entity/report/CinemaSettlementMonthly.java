package com.mopon.entity.report;

public class CinemaSettlementMonthly {
    private Long id;

    private Long cinemano;

    private String cinemaName;

    private String cityno;

    private Long settlemntAmount;

    private Long checkAmount;

    private Long manualAmount;

    private Long payAmount;

    private Integer settlmentDate;

    private Integer settlementFlag;

    private String modifier;

    private Integer modifiedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCinemano() {
        return cinemano;
    }

    public void setCinemano(Long cinemano) {
        this.cinemano = cinemano;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName == null ? null : cinemaName.trim();
    }

    public String getCityno() {
        return cityno;
    }

    public void setCityno(String cityno) {
        this.cityno = cityno == null ? null : cityno.trim();
    }

    public Long getSettlemntAmount() {
        return settlemntAmount;
    }

    public void setSettlemntAmount(Long settlemntAmount) {
        this.settlemntAmount = settlemntAmount;
    }

    public Long getCheckAmount() {
        return checkAmount;
    }

    public void setCheckAmount(Long checkAmount) {
        this.checkAmount = checkAmount;
    }

    public Long getManualAmount() {
        return manualAmount;
    }

    public void setManualAmount(Long manualAmount) {
        this.manualAmount = manualAmount;
    }

    public Long getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(Long payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getSettlmentDate() {
        return settlmentDate;
    }

    public void setSettlmentDate(Integer settlmentDate) {
        this.settlmentDate = settlmentDate;
    }

    public Integer getSettlementFlag() {
        return settlementFlag;
    }

    public void setSettlementFlag(Integer settlementFlag) {
        this.settlementFlag = settlementFlag;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier == null ? null : modifier.trim();
    }

    public Integer getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Integer modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}