package com.ly.test.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;

/**
 * Student
 * 
 */
public class EmployAttend 
{
    /**
     * id   
     */
    private String id;
    
    /**
     * ����
     */
    private XSSFCell buMen;
    
    
    /**
     * ����
     */
    private XSSFCell gongHao;
    
    /**
     * ����
     */
    private XSSFCell riQi; 
    
    /**
     * ����
     */
    private XSSFCell xingMing;
    
    private XSSFCell xingQi;
    
    /**
     * �ϰ�ʱ��
     */
    private XSSFCell shangBanTime;
    
    /**
     * �°�ʱ��
     */
    private XSSFCell xiaBanTime;
    
    /**
     * �ٵ�(����)
     */
    private XSSFCell chiDaoMin;
    /**
     * ����(����)
     */
    private XSSFCell zaoTuiMin;
    /**
     * ���(Сʱ)
     */
    private XSSFCell qingJiaHour;
    
    /**
     * ����(��)
     */
    private XSSFCell chuChai;
    
    /**
     * ���
     */
    private XSSFCell waiChu;
    
    /**
     * ���ʱ��(��)
     */
    private XSSFCell waiChuMin;
    
    /**
     * �򿨲���
     */
    private XSSFCell daKabudeng;
    
    /**
     * ���쳣
     */
    private XSSFCell daKayichang;
    
    /**
     * �Ӱ�(Сʱ)
     */
    private XSSFCell jiaBanHour;
    
    /**
     * ����(Сʱ)
     */
    private XSSFCell kuangGong;

    /**
     * �쳣����
     */
    private XSSFCell yiChangleixing;
    
    /**
     * �쳣����
     */
    private XSSFCell yiChangmiaoshu;

	/**
	 * @return the shangBanTime
	 */
	public XSSFCell getShangBanTime()
	{
		return shangBanTime;
	}

	/**
	 * @param shangBanTime the shangBanTime to set
	 */
	public void setShangBanTime(XSSFCell shangBanTime)
	{
		this.shangBanTime = shangBanTime;
	}

	/**
	 * @return the xiaBanTime
	 */
	public XSSFCell getXiaBanTime()
	{
		return xiaBanTime;
	}

	/**
	 * @param xiaBanTime the xiaBanTime to set
	 */
	public void setXiaBanTime(XSSFCell xiaBanTime)
	{
		this.xiaBanTime = xiaBanTime;
	}

	/**
	 * @return the chiDaoMin
	 */
	public XSSFCell getChiDaoMin()
	{
		return chiDaoMin;
	}

	/**
	 * @param chiDaoMin the chiDaoMin to set
	 */
	public void setChiDaoMin(XSSFCell chiDaoMin)
	{
		this.chiDaoMin = chiDaoMin;
	}

	/**
	 * @return the zaoTuiMin
	 */
	public XSSFCell getZaoTuiMin()
	{
		return zaoTuiMin;
	}

	/**
	 * @param zaoTuiMin the zaoTuiMin to set
	 */
	public void setZaoTuiMin(XSSFCell zaoTuiMin)
	{
		this.zaoTuiMin = zaoTuiMin;
	}

	/**
	 * @return the qingJiaHour
	 */
	public XSSFCell getQingJiaHour()
	{
		return qingJiaHour;
	}

	/**
	 * @param qingJiaHour the qingJiaHour to set
	 */
	public void setQingJiaHour(XSSFCell qingJiaHour)
	{
		this.qingJiaHour = qingJiaHour;
	}

	/**
	 * @return the chuChai
	 */
	public XSSFCell getChuChai()
	{
		return chuChai;
	}

	/**
	 * @param chuChai the chuChai to set
	 */
	public void setChuChai(XSSFCell chuChai)
	{
		this.chuChai = chuChai;
	}


	/**
	 * @return the daKabudeng
	 */
	public XSSFCell getDaKabudeng()
	{
		return daKabudeng;
	}

	/**
	 * @param daKabudeng the daKabudeng to set
	 */
	public void setDaKabudeng(XSSFCell daKabudeng)
	{
		this.daKabudeng = daKabudeng;
	}

	/**
	 * @return the daKayichang
	 */
	public XSSFCell getDaKayichang()
	{
		return daKayichang;
	}

	/**
	 * @param daKayichang the daKayichang to set
	 */
	public void setDaKayichang(XSSFCell daKayichang)
	{
		this.daKayichang = daKayichang;
	}

	/**
	 * @return the kuangGong
	 */
	public XSSFCell getKuangGong()
	{
		return kuangGong;
	}

	/**
	 * @param kuangGong the kuangGong to set
	 */
	public void setKuangGong(XSSFCell kuangGong)
	{
		this.kuangGong = kuangGong;
	}

	/**
	 * @return the yiChangleixing
	 */
	public XSSFCell getYiChangleixing()
	{
		return yiChangleixing;
	}

	/**
	 * @param yiChangleixing the yiChangleixing to set
	 */
	public void setYiChangleixing(XSSFCell yiChangleixing)
	{
		this.yiChangleixing = yiChangleixing;
	}

	/**
	 * @return the yiChangmiaoshu
	 */
	public XSSFCell getYiChangmiaoshu()
	{
		return yiChangmiaoshu;
	}

	/**
	 * @param yiChangmiaoshu the yiChangmiaoshu to set
	 */
	public void setYiChangmiaoshu(XSSFCell yiChangmiaoshu)
	{
		this.yiChangmiaoshu = yiChangmiaoshu;
	}

	/**
	 * @return the id
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id)
	{
		this.id = id;
	}

	/**
	 * @return the gongHao
	 */
	public XSSFCell getGongHao()
	{
		return gongHao;
	}

	/**
	 * @param gongHao the gongHao to set
	 */
	public void setGongHao(XSSFCell gongHao)
	{
		this.gongHao = gongHao;
	}

	/**
	 * @return the waiChu
	 */
	public XSSFCell getWaiChu()
	{
		return waiChu;
	}

	/**
	 * @param waiChu the waiChu to set
	 */
	public void setWaiChu(XSSFCell waiChu)
	{
		this.waiChu = waiChu;
	}

	/**
	 * @return the buMen
	 */
	public XSSFCell getBuMen()
	{
		return buMen;
	}

	/**
	 * @param buMen the buMen to set
	 */
	public void setBuMen(XSSFCell buMen)
	{
		this.buMen = buMen;
	}

	/**
	 * @return the riQi
	 */
	public XSSFCell getRiQi()
	{
		return riQi;
	}

	/**
	 * @param riQi the riQi to set
	 */
	public void setRiQi(XSSFCell riQi)
	{
		this.riQi = riQi;
	}

	/**
	 * @return the xingMing
	 */
	public XSSFCell getXingMing()
	{
		return xingMing;
	}

	/**
	 * @param xingMing the xingMing to set
	 */
	public void setXingMing(XSSFCell xingMing)
	{
		this.xingMing = xingMing;
	}

	/**
	 * @return the xingQi
	 */
	public XSSFCell getXingQi()
	{
		return xingQi;
	}

	/**
	 * @param xingQi the xingQi to set
	 */
	public void setXingQi(XSSFCell xingQi)
	{
		this.xingQi = xingQi;
	}

	/**
	 * @return the waiChuMin
	 */
	public XSSFCell getWaiChuMin()
	{
		return waiChuMin;
	}

	/**
	 * @param waiChuMin the waiChuMin to set
	 */
	public void setWaiChuMin(XSSFCell waiChuMin)
	{
		this.waiChuMin = waiChuMin;
	}

	/**
	 * @return the jiaBanHour
	 */
	public XSSFCell getJiaBanHour()
	{
		return jiaBanHour;
	}

	/**
	 * @param jiaBanHour the jiaBanHour to set
	 */
	public void setJiaBanHour(XSSFCell jiaBanHour)
	{
		this.jiaBanHour = jiaBanHour;
	}
    
}