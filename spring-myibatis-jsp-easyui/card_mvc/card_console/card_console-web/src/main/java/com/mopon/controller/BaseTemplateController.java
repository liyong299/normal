package com.mopon.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.apache.commons.httpclient.util.DateUtil;
import org.apache.ibatis.annotations.Delete;
import org.logicalcobwebs.cglib.transform.impl.AddDelegateTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import repackage.EditBuildScript;

import com.mopon.controller.common.BaseController;
import com.mopon.entity.BaseBaseTempKeyWord;
import com.mopon.entity.BaseBaseTemplAttribute;
import com.mopon.entity.BaseBasetemplate;
import com.mopon.entity.base.KeyValuePair;
import com.mopon.entity.base.Result;
import com.mopon.entity.base.ResultList;
import com.mopon.service.base.IBaseBaseTempAttributorService;
import com.mopon.service.base.IBaseBaseTemplateService;
import com.mopon.util.DateUtils;
import com.mopon.util.LogUtils;
import com.mopon.util.Page;
import com.mopon.util.ResponseWriteUtils;
import com.mopon.util.enums.LogOperationEnum;
import com.mopon.util.enums.TemplateKey;

@Controller
@RequestMapping("/basetemplate")
public class BaseTemplateController extends BaseController<BaseBasetemplate> {

	public LogUtils Log = new LogUtils(BaseController.class);
	
	@Autowired
	 IBaseBaseTemplateService baseTmplService;
	@Autowired
	IBaseBaseTempAttributorService baseTmplAttrService;
	
	/**
	 * 初始化页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="index")
	public String index(Model model) {
		return "/baseTemplate/index";
	}
	
	/**
	 * 查询模板列表
	 * @param request
	 * @param response
	 * @param baseBasetemplate
	 * @param page
	 */
	@RequestMapping(value="queryList")
	public void queryList(HttpServletRequest request,HttpServletResponse response,BaseBasetemplate baseBasetemplate
			,Page<BaseBasetemplate> page){
		ResultList<BaseBasetemplate> results = new ResultList<BaseBasetemplate>();
		this.setParams(request, baseBasetemplate, page);
		List<BaseBasetemplate> list = baseTmplService.queryListByPage(page);
		results.setRows(list);
		results.setTotal(page.getTotalRecord());
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(results).toString());
	}
	/**
	 * 初始化添加页面
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value="add",method=RequestMethod.GET)
	public String add(Locale locale,Model model){
		List<BaseBaseTemplAttribute> typelist = baseTmplAttrService.queryTemplateTypeList(new BaseBaseTemplAttribute());
		List<BaseBaseTemplAttribute> classlist = baseTmplAttrService.queryTemplateClassList(new BaseBaseTemplAttribute());
		
		model.addAttribute("typelist", getTypeList(typelist,""));
		model.addAttribute("classlist", getClassList(classlist,""));
		model.addAttribute("definelist", getTempDefine(""));
		
		/*//测试获取短信
		BaseBaseTempKeyWord record = new BaseBaseTempKeyWord();
		record.setCinemaname("金逸中心城店");
		record.setFilmname("复仇者联盟2");
		record.setSeatno("1号停5排5，6号");
		record.setShowdate("2015/05/17 10:30");
		record.setDeviceposition("影院购票大厅Pos");
		record.setGoodsnum("2");
		try {
			BaseBasetemplate smsTemp = baseTmplService.getSendVoucherSMS(TemplateKey.SendSeatVoucher.getVal(), record);
			
		} catch (Exception e) {
			// TODO: handle exception
		}*/
		
		
		return "/baseTemplate/add";
	}
	/**
	 * 添加模板
	 * @param request
	 * @param response
	 * @param baseBasetemplate
	 */
	@RequestMapping(value="add",method = RequestMethod.POST)
	public void add(HttpServletRequest request,HttpServletResponse response,BaseBasetemplate baseBasetemplate){
		Result result = new Result();
		try {
			if(baseBasetemplate.getTemplatename() == null && baseBasetemplate.getTemplatename() == ""){
				result.setMessage("模板名称不能为空！");
				result.setIsSuccess(false);
			}if(baseBasetemplate.getTemplatekey() == null && baseBasetemplate.getTemplatekey() == ""){
				result.setMessage("模板名称不能为空！");
				result.setIsSuccess(false);
			}else if(baseBasetemplate.getTemplatedefine() == null && baseBasetemplate.getTemplatedefine().toString() =="") {
				result.setMessage("定义模板不能为空！");
				result.setIsSuccess(false);
			}else if(baseBasetemplate.getTemplatetype() == null && baseBasetemplate.getTemplatetype().toString() =="") {
				result.setMessage("模板类型不能为空！");
				result.setIsSuccess(false);
			}else if(baseBasetemplate.getTemplateclass() == null && baseBasetemplate.getTemplateclass().toString() =="") {
				result.setMessage("动作不能为空！");
				result.setIsSuccess(false);
			}else if(baseBasetemplate.getTemplatecontent() == null && baseBasetemplate.getTemplatecontent().toString() =="") {
				result.setMessage("短信内容不能为空！");
				result.setIsSuccess(false);
			}else if(baseBasetemplate.getTemplatecontent1() == null && baseBasetemplate.getTemplatecontent1().toString() =="") {
				result.setMessage("彩信内容不能为空！");
				result.setIsSuccess(false);
			}else {
				
				BaseBasetemplate baseBasetemplate2 = baseTmplService.selectByPrimaryKey(baseBasetemplate);
				if(baseBasetemplate2 !=null){
					result.setMessage("模板标识已存在！");
					result.setIsSuccess(false);
				}else {
					baseBasetemplate.setCreatetime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
					baseBasetemplate.setStatus(1);
					int tmpResult = baseTmplService.insertSelective(baseBasetemplate);
					if(tmpResult>0){
						result.setIsSuccess(true);
						result.setMessage("保存成功！");
						Log.logInfoOperation(this.getUserID().toString(), this.getFullName(), LogOperationEnum.ADD.getName(), "添加模板成功！");
					}else {
						result.setIsSuccess(false);
						result.setMessage("保存失败！");
						Log.logInfoOperation(this.getUserID().toString(), this.getFullName(), LogOperationEnum.ADD.getName(), "添加模板失败！");
					}	
				}
			}
		} catch (Exception e) {
			Log.logErrorOperation(LogOperationEnum.ADD.getName(), "基础信息->模板管理->添加模板", JSONSerializer.toJSON(baseBasetemplate).toString(), e);
		}
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());		
	}
	/**
	 * 初始化编辑页面
	 * @param templateID
	 * @return
	 */
	@RequestMapping(value="edit",method = RequestMethod.GET)
	public ModelAndView edit(Integer templateid){
		ModelAndView mv = new ModelAndView();
		List<BaseBaseTemplAttribute> typelist = baseTmplAttrService.queryTemplateTypeList(new BaseBaseTemplAttribute());
		List<BaseBaseTemplAttribute> classlist = baseTmplAttrService.queryTemplateClassList(new BaseBaseTemplAttribute());
		
		BaseBasetemplate baseBasetemplate = new BaseBasetemplate();
		baseBasetemplate.setTemplateid(templateid);
		
		BaseBasetemplate baseBasetemplate2 = baseTmplService.selectByPrimaryKey(baseBasetemplate);
		
		mv.addObject("typelist", getTypeList(typelist,baseBasetemplate2.getTemplatetype().toString()));
		mv.addObject("classlist", getClassList(classlist,baseBasetemplate2.getTemplateclass().toString()));
		mv.addObject("definelist", getTempDefine(baseBasetemplate2.getTemplatedefine().toString()));
		mv.addObject("baseBaseTemplate", baseBasetemplate2);
		mv.setViewName("/baseTemplate/edit");
		return mv;
	}
	/**
	 * 编辑模板
	 * @param request
	 * @param response
	 * @param baseBasetemplate
	 */
	@RequestMapping(value="edit",method = RequestMethod.POST)
	public void edit(HttpServletRequest request,HttpServletResponse response,BaseBasetemplate baseBasetemplate){
		Result result = new Result();
		try {
			if(baseBasetemplate.getTemplatename() == null && baseBasetemplate.getTemplatename() == ""){
				result.setMessage("模板名称不能为空！");
				result.setIsSuccess(false);
			}if(baseBasetemplate.getTemplatekey() == null && baseBasetemplate.getTemplatekey() == ""){
				result.setMessage("模板名称不能为空！");
				result.setIsSuccess(false);
			}else if(baseBasetemplate.getTemplatedefine() == null && baseBasetemplate.getTemplatedefine().toString() =="") {
				result.setMessage("定义模板不能为空！");
				result.setIsSuccess(false);
			}else if(baseBasetemplate.getTemplatetype() == null && baseBasetemplate.getTemplatetype().toString() =="") {
				result.setMessage("模板类型不能为空！");
				result.setIsSuccess(false);
			}else if(baseBasetemplate.getTemplateclass() == null && baseBasetemplate.getTemplateclass().toString() =="") {
				result.setMessage("动作不能为空！");
				result.setIsSuccess(false);
			}else if(baseBasetemplate.getTemplatecontent() == null && baseBasetemplate.getTemplatecontent().toString() =="") {
				result.setMessage("短信内容不能为空！");
				result.setIsSuccess(false);
			}else if(baseBasetemplate.getTemplatecontent1() == null && baseBasetemplate.getTemplatecontent1().toString() =="") {
				result.setMessage("彩信内容不能为空！");
				result.setIsSuccess(false);
			}else {
				
				baseBasetemplate.setCreatetime(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
				baseBasetemplate.setStatus(1);
				int tmpResult = baseTmplService.updateByPrimaryKeySelective(baseBasetemplate);
				if(tmpResult>0){
					result.setIsSuccess(true);
					result.setMessage("保存成功！");
					Log.logInfoOperation(this.getUserID().toString(), this.getFullName(), LogOperationEnum.EDIT.getName(), "编辑模板成功！");
				}else {
					result.setIsSuccess(false);
					result.setMessage("保存失败！");
					Log.logInfoOperation(this.getUserID().toString(), this.getFullName(), LogOperationEnum.EDIT.getName(), "编辑模板失败！");
				}
			}
		} catch (Exception e) {
			Log.logErrorOperation(LogOperationEnum.EDIT.getName(), "基础信息->模板管理->编辑模板", JSONSerializer.toJSON(baseBasetemplate).toString(), e);
		}
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());	
	}

	/**
	 * 删除模板
	 * @param request
	 * @param response
	 * @param templateid
	 */
	@RequestMapping(value="delete",method = RequestMethod.POST)
	public void delete(HttpServletRequest request,HttpServletResponse response,Integer templateid){
		Result result = new Result();
		int tmpResult = baseTmplService.deleteByPrimaryKey(templateid);
		if(tmpResult>0){
			result.setIsSuccess(true);
			result.setMessage("删除成功！");
			Log.logInfoOperation(this.getUserID().toString(), this.getFullName(), LogOperationEnum.DELETE.getName(), "删除模板成功！");
		}else {
			result.setIsSuccess(false);
			result.setMessage("删除失败！");
			Log.logInfoOperation(this.getUserID().toString(), this.getFullName(), LogOperationEnum.DELETE.getName(), "删除模板失败！");
		}
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());	
	}
	
	
	@RequestMapping(value="queryTmplAttrList")
	public void queryTmplAttrList(HttpServletRequest request,HttpServletResponse response,BaseBaseTemplAttribute baseBaseTemplAttribute){
		BaseBaseTemplAttribute baseTemplAttr = baseTmplAttrService.queryTemplAttrList(baseBaseTemplAttribute);
		String selectAttrs = baseTemplAttr.getTempselectattrs();
		String selectValues = baseTemplAttr.getTempseletedvalues();
		String[] attrs = selectAttrs.split("\\|");
		String[] vals = selectValues.split("\\|");
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < attrs.length; i++) {
			map.put(vals[i].toString(), attrs[i].toString());
		}
		
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(map).toString());
	}
	
	/**
	 * 将字典列表转化为combobox列表数据
	 * @param list
	 * @return
	 */
	private List<KeyValuePair> getTypeList(List<BaseBaseTemplAttribute> list,String value){
		List<KeyValuePair> result = new ArrayList<KeyValuePair>();
		KeyValuePair keyPair = null;

		for (int i = 0; i < list.size(); i++) {
			
			keyPair = new KeyValuePair();
			keyPair.setValue(list.get(i).getTemplatetype().toString());
			keyPair.setText(list.get(i).getTemplatetypename());
			if(value.equals(list.get(i).getTemplatetype().toString())){
				keyPair.setSelected(true);
			}
			result.add(keyPair);
		}
		return result;
	}
	
	private List<KeyValuePair> getClassList(List<BaseBaseTemplAttribute> list,String value){
		List<KeyValuePair> result = new ArrayList<KeyValuePair>();
		KeyValuePair keyPair = null;

		for (int i = 0; i < list.size(); i++) {
			keyPair = new KeyValuePair();
			keyPair.setValue(list.get(i).getTemplateclass().toString());
			keyPair.setText(list.get(i).getTemplateclassname());
			if(value.equals(list.get(i).getTemplateclass().toString())){
				keyPair.setSelected(true);
			}
			result.add(keyPair);
		}
		return result;
	}
	
	private List<KeyValuePair> getTempDefine(String value){
		List<KeyValuePair> result = new ArrayList<KeyValuePair>();
		KeyValuePair keyPair = null;
		keyPair = new KeyValuePair();
		keyPair.setValue("0");
		keyPair.setText("默认");
		if(value.equals("0")){
			keyPair.setSelected(true);
		}
		result.add(keyPair);
		keyPair = new KeyValuePair();
		keyPair.setValue("1");
		if(value.equals("1")){
			keyPair.setSelected(true);
		}
		keyPair.setText("自定义");
		result.add(keyPair);
		
		return result;
	}
}
