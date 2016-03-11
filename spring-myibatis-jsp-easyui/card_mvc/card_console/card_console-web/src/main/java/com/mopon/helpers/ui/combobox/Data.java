package com.mopon.helpers.ui.combobox;

import java.util.ArrayList;
import java.util.List;

import com.mopon.entity.base.KeyValuePair;
import com.mopon.entity.console.base.BaseRoleExt;
import com.mopon.service.role.RoleService;
import com.mopon.util.SpringContextUtil;

public class Data {


	/*
	 * DemoList
	 * */
	public static List<KeyValuePair> getDemoList(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("全部");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("是");
		keyValuePair2.setValue("1");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("否");
		keyValuePair3.setValue("0");
		results.add(keyValuePair3);

		return results;
	}

	/*
	 * 状态列表(带全部)
	 * */
	public static List<KeyValuePair> getStatusList(){
		return getStatusList(true);
	}
	/*
	 * 状态列表(有个开关)
	 * */
	public static List<KeyValuePair> getStatusList(Boolean isAll){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		if(isAll){
			KeyValuePair keyValuePair1 = new KeyValuePair();
			keyValuePair1.setText("全部");
			keyValuePair1.setValue("");
			results.add(keyValuePair1);
		}

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("启用");
		keyValuePair2.setValue("1");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("禁用");
		keyValuePair3.setValue("0");
		results.add(keyValuePair3);

		return results;
	}

	public static List<KeyValuePair> getRoleList(){
		return getRoleList(true);
	}

	/*
	 * 角色列表(有个开关)
	 * */
	public static List<KeyValuePair> getRoleList(Boolean isAll){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		if(isAll){
			KeyValuePair keyValuePair1 = new KeyValuePair();
			keyValuePair1.setText("全部");
			keyValuePair1.setValue("");
			keyValuePair1.setSelected(true);
			results.add(keyValuePair1);
		}

		RoleService roleService = (RoleService)SpringContextUtil.getBean("roleServiceImpl");

		List<BaseRoleExt> roleList = roleService.queryRoleList(new BaseRoleExt());
		for(BaseRoleExt role: roleList){
			KeyValuePair keyValuePair = new KeyValuePair();
			keyValuePair.setText(role.getName());
			keyValuePair.setValue(role.getId().toString());
			results.add(keyValuePair);
		}

		return results;
	}

	public static List<KeyValuePair> getYesOrNo(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("是");
		keyValuePair1.setValue("1");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("否");
		keyValuePair2.setValue("0");
		results.add(keyValuePair2);

		return results;
	}

	public static List<KeyValuePair> getSeatSaleList(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();

		keyValuePair1.setText("全部");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("启售");
		keyValuePair2.setValue("1");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("停售");
		keyValuePair3.setValue("0");
		results.add(keyValuePair3);

		return results;
	}

	public static List<KeyValuePair> getTicketType(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();

		keyValuePair1.setText("全部");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("平台凭证");
		keyValuePair2.setValue("0");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("接入商取票号");
		keyValuePair3.setValue("1");
		results.add(keyValuePair3);

		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("平台凭证和接入商取票号");
		keyValuePair4.setValue("2");
		results.add(keyValuePair4);

		return results;
	}

	public static List<KeyValuePair> getcommonSaleList(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();

		keyValuePair1.setText("全部");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("启售");
		keyValuePair2.setValue("1");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("停售");
		keyValuePair3.setValue("0");
		results.add(keyValuePair3);

		return results;

	}

	/**
	 * 客户端商品显示
	 * @return
	 */
	public static List<KeyValuePair> getViewGoodsTypeList(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();

		keyValuePair1.setText("选座票和通兑票");
		keyValuePair1.setValue("0");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("选座票");
		keyValuePair2.setValue("1");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("通兑票");
		keyValuePair3.setValue("2");
		results.add(keyValuePair3);

		return results;

	}

	public static List<KeyValuePair> getTepyList(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();

		keyValuePair1.setText("全部");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("分销");
		keyValuePair2.setValue("1");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("自有");
		keyValuePair3.setValue("2");
		results.add(keyValuePair3);

		return results;

	}

	public static List<KeyValuePair> getOpenedList(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();

		keyValuePair1.setText("全部");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("开放");
		keyValuePair2.setValue("true");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("关闭");
		keyValuePair3.setValue("false");
		results.add(keyValuePair3);

		return results;

	}

	public static List<KeyValuePair> getunitPriceList(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("");
		keyValuePair2.setValue("1");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("");
		keyValuePair3.setValue("0");
		results.add(keyValuePair3);

		return results;

	}

	public static List<KeyValuePair> getdividedTypeList(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("--请选择--");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("单价结算");
		keyValuePair2.setValue("1");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("比例结算");
		keyValuePair3.setValue("2");
		results.add(keyValuePair3);

		return results;

	}

	public static List<KeyValuePair> getSalableList(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();

		keyValuePair1.setText("全部");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("开放");
		keyValuePair2.setValue("true");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("关闭");
		keyValuePair3.setValue("false");
		results.add(keyValuePair3);

		return results;

	}

	public static List<KeyValuePair> getSettlTypeList(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();

		keyValuePair1.setText("--请选择--");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("自销");
		keyValuePair2.setValue("0");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("采销");
		keyValuePair3.setValue("1");
		results.add(keyValuePair3);

		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("代销");
		keyValuePair4.setValue("2");
		results.add(keyValuePair4);

		return results;

	}


	public static List<KeyValuePair> getOrderStatus(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair = new KeyValuePair();
		keyValuePair.setText("--请选择--");
		keyValuePair.setValue("");
		results.add(keyValuePair);

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("创建订单");
		keyValuePair1.setValue("1");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("锁座成功");
		keyValuePair2.setValue("2");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("地面出票成功");
		keyValuePair3.setValue("3");
		results.add(keyValuePair3);

		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("凭证成功");
		keyValuePair4.setValue("4");
		results.add(keyValuePair4);

		KeyValuePair keyValuePair5= new KeyValuePair();
		keyValuePair5.setText("锁座失败");
		keyValuePair5.setValue("5");
		results.add(keyValuePair5);

		KeyValuePair keyValuePair6 = new KeyValuePair();
		keyValuePair6.setText("地面出票失败");
		keyValuePair6.setValue("6");
		results.add(keyValuePair6);

		KeyValuePair keyValuePair7 = new KeyValuePair();
		keyValuePair7.setText("凭证失败");
		keyValuePair7.setValue("7");
		results.add(keyValuePair7);

		KeyValuePair keyValuePair8 = new KeyValuePair();
		keyValuePair8.setText("已撤消");
		keyValuePair8.setValue("8");
		results.add(keyValuePair8);

		KeyValuePair keyValuePair9 = new KeyValuePair();
		keyValuePair9.setText("已取消");
		keyValuePair9.setValue("-1");
		results.add(keyValuePair9);

		return results;
	}

	public static List<KeyValuePair> getOrderStatusPay(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("--请选择--");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("未支付");
		keyValuePair2.setValue("1");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("支付成功");
		keyValuePair3.setValue("2");
		results.add(keyValuePair3);

		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("部分支付");
		keyValuePair4.setValue("3");
		results.add(keyValuePair4);

		return results;
	}

	public static List<KeyValuePair> getOrderType(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("--请选择--");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("通兑票");
		keyValuePair2.setValue("1");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("选座票");
		keyValuePair3.setValue("2");
		results.add(keyValuePair3);

		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("兑换劵");
		keyValuePair4.setValue("3");
		results.add(keyValuePair4);


		return results;
	}

	public static List<KeyValuePair> getFeeType(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("按结算金额");
		keyValuePair1.setValue("1");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("按订单金额");
		keyValuePair2.setValue("2");
		results.add(keyValuePair2);

		return results;
	}
	//商品类型
	public static List<KeyValuePair> getGoodsShowtype(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("2D");
		keyValuePair2.setValue("0");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("3D");
		keyValuePair3.setValue("0");
		results.add(keyValuePair3);

		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("IMAX2D");
		keyValuePair4.setValue("0");
		results.add(keyValuePair4);

		KeyValuePair keyValuePair5 = new KeyValuePair();
		keyValuePair5.setText("IMAX3D");
		keyValuePair5.setValue("0");
		results.add(keyValuePair5);

		KeyValuePair keyValuePair6 = new KeyValuePair();
		keyValuePair6.setText("中国巨幕2D");
		keyValuePair6.setValue("0");
		results.add(keyValuePair6);

		KeyValuePair keyValuePair7 = new KeyValuePair();
		keyValuePair7.setText("中国巨幕3D");
		keyValuePair7.setValue("0");
		results.add(keyValuePair7);

		return results;
	}
	//定价类型
	public static List<KeyValuePair> getPriceType(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("常规价");
		keyValuePair1.setValue("0");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("闲忙时价");
		keyValuePair2.setValue("1");
		results.add(keyValuePair2);

		return results;
		}

	public static List<KeyValuePair> getOrgzaination(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("按结算金额");
		keyValuePair1.setValue("1");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("按订单金额");
		keyValuePair2.setValue("2");
		results.add(keyValuePair2);

		return results;
	}

	public static  List<KeyValuePair> getchannleName(){
//		ChannelMapper channelMapper = (ChannelMapper)SpringContextUtil.getBean("channelDao");
//		List<KeyValuePair> results = new ArrayList<KeyValuePair>();
//		try {
//			List<BaseChannel> list=channelMapper.queryListByPage();
//			for(int i=0;i<list.size();i++){
//				KeyValuePair keyValuePair1 = new KeyValuePair();
//				String channelno=list.get(i).getChannelno();
//				String channelName=list.get(i).getChannelname();
//				keyValuePair1.setText(channelName);
//				keyValuePair1.setValue(channelno);
//				results.add(keyValuePair1);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		return null;
	}
	public static List<KeyValuePair> getTemplateStatus(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("全部");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("未关联");
		keyValuePair2.setValue("0");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("已关联");
		keyValuePair3.setValue("1");
		results.add(keyValuePair3);

		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("待信息审核");
		keyValuePair4.setValue("2");
		results.add(keyValuePair4);

		KeyValuePair keyValuePair5= new KeyValuePair();
		keyValuePair5.setText("信息审核拒绝");
		keyValuePair5.setValue("3");
		results.add(keyValuePair5);

		KeyValuePair keyValuePair6 = new KeyValuePair();
		keyValuePair6.setText("待财务审核");
		keyValuePair6.setValue("4");
		results.add(keyValuePair6);

		KeyValuePair keyValuePair7 = new KeyValuePair();
		keyValuePair7.setText("财务审核拒绝");
		keyValuePair7.setValue("5");
		results.add(keyValuePair7);

		return results;
	}
	
	public static List<KeyValuePair> getCardOrderStatus(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("全部");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("未关联");
		keyValuePair2.setValue("-1");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("已关联");
		keyValuePair3.setValue("1");
		results.add(keyValuePair3);

		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("待信息审核");
		keyValuePair4.setValue("2");
		results.add(keyValuePair4);

		KeyValuePair keyValuePair5= new KeyValuePair();
		keyValuePair5.setText("信息审核拒绝");
		keyValuePair5.setValue("-2");
		results.add(keyValuePair5);

		KeyValuePair keyValuePair6 = new KeyValuePair();
		keyValuePair6.setText("待财务审核");
		keyValuePair6.setValue("3");
		results.add(keyValuePair6);

		KeyValuePair keyValuePair7 = new KeyValuePair();
		keyValuePair7.setText("财务审核拒绝");
		keyValuePair7.setValue("-3");
		results.add(keyValuePair7);

		return results;
	}
	public static List<KeyValuePair> getCardOrderChangeType(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("所有");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("2D");
		keyValuePair2.setValue("0");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("3D");
		keyValuePair3.setValue("1");
		results.add(keyValuePair3);

		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("IMAX2D");
		keyValuePair4.setValue("2");
		results.add(keyValuePair4);

		KeyValuePair keyValuePair5= new KeyValuePair();
		keyValuePair5.setText("IMAX3D");
		keyValuePair5.setValue("3");
		results.add(keyValuePair5);

		KeyValuePair keyValuePair6 = new KeyValuePair();
		keyValuePair6.setText("中国巨幕2D");
		keyValuePair6.setValue("4");
		results.add(keyValuePair6);

		KeyValuePair keyValuePair7 = new KeyValuePair();
		keyValuePair7.setText("中国巨幕3D");
		keyValuePair7.setValue("5");
		results.add(keyValuePair7);
		
		return results;
	}
	public static List<KeyValuePair> getCardOrderProductType(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("所有");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("第三方预付卡");
		keyValuePair2.setValue("0");
		results.add(keyValuePair2);
		
		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("现金卡");
		keyValuePair3.setValue("1");
		results.add(keyValuePair3);

		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("点卡");
		keyValuePair4.setValue("2");
		results.add(keyValuePair4);

		KeyValuePair keyValuePair5= new KeyValuePair();
		keyValuePair5.setText("电影兑换卡");
		keyValuePair5.setValue("3");
		results.add(keyValuePair5);
		
		KeyValuePair keyValuePair6 = new KeyValuePair();
		keyValuePair6.setText("通兑票");
		keyValuePair6.setValue("4");
		results.add(keyValuePair6);

		KeyValuePair keyValuePair7 = new KeyValuePair();
		keyValuePair7.setText("现金券");
		keyValuePair7.setValue("5");
		results.add(keyValuePair7);

		KeyValuePair keyValuePair8 = new KeyValuePair();
		keyValuePair8.setText("兑换券");
		keyValuePair8.setValue("6");
		results.add(keyValuePair8);
		KeyValuePair keyValuePair9 = new KeyValuePair();
		keyValuePair9.setText("点券");
		keyValuePair9.setValue("7");
		results.add(keyValuePair9);

		return results;
	}
	
	public static List<KeyValuePair> getProductType(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("所有");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("通兑票");
		keyValuePair2.setValue("0");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("现金卡");
		keyValuePair3.setValue("1");
		results.add(keyValuePair3);

		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("点卡");
		keyValuePair4.setValue("2");
		results.add(keyValuePair4);

		KeyValuePair keyValuePair5= new KeyValuePair();
		keyValuePair5.setText("电影兑换卡");
		keyValuePair5.setValue("3");
		results.add(keyValuePair5);

		KeyValuePair keyValuePair6 = new KeyValuePair();
		keyValuePair6.setText("现金券");
		keyValuePair6.setValue("4");
		results.add(keyValuePair6);

		KeyValuePair keyValuePair7 = new KeyValuePair();
		keyValuePair7.setText("兑换券");
		keyValuePair7.setValue("5");
		results.add(keyValuePair7);
		KeyValuePair keyValuePair8 = new KeyValuePair();
		keyValuePair8.setText("点券");
		keyValuePair8.setValue("8");
		results.add(keyValuePair8);

		return results;
	}
	public static List<KeyValuePair> getProductType1(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("通兑票");
		keyValuePair2.setValue("0");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("现金卡");
		keyValuePair3.setValue("1");
		results.add(keyValuePair3);

		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("点卡");
		keyValuePair4.setValue("2");
		results.add(keyValuePair4);

		KeyValuePair keyValuePair5= new KeyValuePair();
		keyValuePair5.setText("电影兑换卡");
		keyValuePair5.setValue("3");
		results.add(keyValuePair5);

		KeyValuePair keyValuePair6 = new KeyValuePair();
		keyValuePair6.setText("现金券");
		keyValuePair6.setValue("4");
		results.add(keyValuePair6);

		KeyValuePair keyValuePair7 = new KeyValuePair();
		keyValuePair7.setText("兑换券");
		keyValuePair7.setValue("5");
		results.add(keyValuePair7);
		KeyValuePair keyValuePair8 = new KeyValuePair();
		keyValuePair8.setText("点券");
		keyValuePair8.setValue("8");
		results.add(keyValuePair8);

		return results;
	}

	//日志状态
	public static List<KeyValuePair> getLogStatus(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("--请选择--");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("正常");
		keyValuePair2.setValue("0");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("异常");
		keyValuePair3.setValue("1");
		results.add(keyValuePair3);
		return results;
	}
	
	public static List<KeyValuePair> getRelation(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();

		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("全部");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);

		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("未关联");
		keyValuePair2.setValue("0");
		results.add(keyValuePair2);

		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("已关联");
		keyValuePair3.setValue("1");
		results.add(keyValuePair3);
		return results;
	}
	
	public static List<KeyValuePair> getCardticketTemplateStatus(){
		List<KeyValuePair> results = new ArrayList<KeyValuePair>();
		
		KeyValuePair keyValuePair1 = new KeyValuePair();
		keyValuePair1.setText("全部");
		keyValuePair1.setValue("");
		results.add(keyValuePair1);
		
		KeyValuePair keyValuePair2 = new KeyValuePair();
		keyValuePair2.setText("编辑中");
		keyValuePair2.setValue("1");
		results.add(keyValuePair2);
		
		/*KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("删除");
		keyValuePair3.setValue("-1");
		results.add(keyValuePair3);*/
		
		KeyValuePair keyValuePair3 = new KeyValuePair();
		keyValuePair3.setText("待信息审核");
		keyValuePair3.setValue("2");
		results.add(keyValuePair3);
		
		KeyValuePair keyValuePair4 = new KeyValuePair();
		keyValuePair4.setText("信息审核拒绝");
		keyValuePair4.setValue("-2");
		results.add(keyValuePair4);
		
		KeyValuePair keyValuePair5 = new KeyValuePair();
		keyValuePair5.setText("待财务审核");
		keyValuePair5.setValue("3");
		results.add(keyValuePair5);
		
		KeyValuePair keyValuePair6 = new KeyValuePair();
		keyValuePair6.setText("财务审核拒绝");
		keyValuePair6.setValue("-3");
		results.add(keyValuePair6);
		
		KeyValuePair keyValuePair7 = new KeyValuePair();
		keyValuePair7.setText("启用");
		keyValuePair7.setValue("4");
		results.add(keyValuePair7);
		
		return results;
	}
}