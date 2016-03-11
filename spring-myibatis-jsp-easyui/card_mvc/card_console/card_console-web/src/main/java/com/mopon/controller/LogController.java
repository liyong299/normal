package com.mopon.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mopon.entity.base.ResultList;
import com.mopon.entity.log.AccessApiLog;
import com.mopon.entity.log.BusinessLog;
import com.mopon.entity.log.OperaterLog;
import com.mopon.entity.log.SysExecptionLog;
import com.mopon.service.log.ILogService;
import com.mopon.util.Page;
import com.mopon.util.ResponseWriteUtils;
import com.mopon.util.StringUtil;

/**
 * 日志管理
 *
 */
@Controller
@RequestMapping(value = "/log")
public class LogController  {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private ILogService logService;
	

	@RequestMapping(value = "businessLogInit", method = RequestMethod.GET)
	public String businessLogInit(Locale locale, Model model) {
		return "log/queryBusinessLogList";
	}
	
	@RequestMapping(value = "sysExecptionLogInit", method = RequestMethod.GET)
	public String sysExecptionLogInit(Locale locale, Model model) {
		return "log/querySysExecptionLogList";
	}
	
	@RequestMapping(value = "operaterLogInit", method = RequestMethod.GET)
	public String operaterLogInit(Locale locale, Model model) {
		return "log/queryOperaterLogList";
	}
	
	@RequestMapping(value = "accessApiLogInit", method = RequestMethod.GET)
	public String accessApiLogInit(Locale locale, Model model) {
		return "log/queryAccessApiLogList";
	}
	
	/**
	 * 查看异常日志详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "logDetail", method = RequestMethod.GET)
	public ModelAndView logDetail(HttpServletRequest request, ModelAndView model) {
		String expMsg;
		String expStackTrace;
		try {
			expMsg=new String(request.getParameter("expMsg").getBytes("ISO-8859-1"), "UTF-8");
			expStackTrace = new String(request.getParameter("expStackTrace").getBytes("ISO-8859-1"), "UTF-8");
			model.addObject("expMsg",expMsg);
			model.addObject("expStackTrace",expStackTrace);
			model.setViewName("log/sysExecptionLogDetail");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * 查看业务日志详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "businessLogDetail", method = RequestMethod.GET)
	public ModelAndView businessLogDetail(HttpServletRequest request, ModelAndView model) {
		try {
			String memo=new String(request.getParameter("memo").getBytes("ISO-8859-1"), "UTF-8");
			memo =  URLDecoder.decode(memo);
			model.addObject("memo",memo);
			model.setViewName("log/businessLogDetail");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		return model;
	}

	/**
	 * 查看业务日志详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "operaterLogDetail", method = RequestMethod.GET)
	public ModelAndView operaterLogDetail(HttpServletRequest request, ModelAndView model) {
		try {
			String memo=new String(request.getParameter("memo").getBytes("ISO-8859-1"), "UTF-8");
			memo =  URLDecoder.decode(memo);
			String dataChangeInfos=new String(request.getParameter("dataChangeInfos").getBytes("ISO-8859-1"), "UTF-8");
			dataChangeInfos =  URLDecoder.decode(dataChangeInfos);
			model.addObject("memo",memo);
			model.addObject("dataChangeInfos",dataChangeInfos);
			model.setViewName("log/operaterLogDetail");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		return model;
	}
	
	/**
	 * 查看接入日志详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "accessApiLogDetail", method = RequestMethod.GET)
	public ModelAndView accessApiLogDetail(HttpServletRequest request, ModelAndView model) {
		try {
			String accessProviderNo=new String(request.getParameter("accessProviderNo").getBytes("ISO-8859-1"), "UTF-8");
			accessProviderNo =  URLDecoder.decode(accessProviderNo);
			String apiName=new String(request.getParameter("apiName").getBytes("ISO-8859-1"), "UTF-8");
			apiName =  URLDecoder.decode(apiName);
			String apiUrl=new String(request.getParameter("apiUrl").getBytes("ISO-8859-1"), "UTF-8");
			apiUrl =  URLDecoder.decode(apiUrl);
			String inParam=new String(request.getParameter("inParam").getBytes("ISO-8859-1"), "UTF-8");
			inParam =  URLDecoder.decode(inParam);
			String outParam=new String(request.getParameter("outParam").getBytes("ISO-8859-1"), "UTF-8");
			outParam =  URLDecoder.decode(outParam);
			String memo=new String(request.getParameter("memo").getBytes("ISO-8859-1"), "UTF-8");
			memo =  URLDecoder.decode(memo);
			model.addObject("accessProviderNo",accessProviderNo);
			model.addObject("apiName",apiName);
			model.addObject("apiUrl",apiUrl);
			model.addObject("inParam",inParam);
			model.addObject("outParam",outParam);
			model.addObject("memo",memo);
			model.setViewName("log/accessApiLogDetail");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}  
		return model;
	}
	
	
	@RequestMapping(value = "queryBusinessLog")
	public void queryBusinessLogByPage(BusinessLog log, HttpServletRequest request,HttpServletResponse response, Page<BusinessLog> page) {
		Map<String, Object> params=new HashMap<String, Object>();
		ResultList<BusinessLog> result = new ResultList<BusinessLog>();
		if (!StringUtil.isEmpty(log.getCreateDateBagin())) {
			log.setCreateDateBagin(log.getCreateDateBagin() + " " + "00:00:00");
		}
		if (!StringUtil.isEmpty(log.getCreateDateEnd())){
			log.setCreateDateEnd(log.getCreateDateEnd() + " " + "23:59:59");
		}
		params.put("businessLog", log);
		page.setParams(params);
 		List<BusinessLog> logs = null;
		try {
			logs=logService.queryBusslogs(page);
			result.setRows(logs);
			result.setTotal(page.getTotalRecord());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	
	@RequestMapping(value = "queryOperaterLog")
	public void queryOperaterLogByPage(OperaterLog log, HttpServletRequest request, HttpServletResponse response,Page<OperaterLog> page) {
		Map<String, Object> params=new HashMap<String, Object>();
		ResultList<OperaterLog> result = new ResultList<OperaterLog>();
		if (!StringUtil.isEmpty(log.getCreateDateBagin())) {
			log.setCreateDateBagin(log.getCreateDateBagin() + " " + "00:00:00");
		}
		if (!StringUtil.isEmpty(log.getCreateDateEnd())){
			log.setCreateDateEnd(log.getCreateDateEnd() + " " + "23:59:59");
		}
		params.put("operaterLog", log);
		page.setParams(params);
 		List<OperaterLog> logs = null;
		try {
			logs = logService.queryOprlogs(page);
			result.setRows(logs);
			result.setTotal(page.getTotalRecord());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	@RequestMapping(value = "querySysExecptionLog")
	public void queryBusinessLogByPage(SysExecptionLog log, HttpServletRequest request, HttpServletResponse response,Page<SysExecptionLog> page) {
		Map<String, Object> params=new HashMap<String, Object>();
		ResultList<SysExecptionLog> result = new ResultList<SysExecptionLog>();
		if (!StringUtil.isEmpty(log.getCreateDateBagin())) {
			log.setCreateDateBagin(log.getCreateDateBagin() + " " + "00:00:00");
		}
		if (!StringUtil.isEmpty(log.getCreateDateEnd())){
			log.setCreateDateEnd(log.getCreateDateEnd() + " " + "23:59:59");
		}
		params.put("sysExecptionLog", log);
		page.setParams(params);
 		List<SysExecptionLog> logs = null;
		try {
			logs = logService.querySyserrlogs(page);
			result.setRows(logs);
			result.setTotal(page.getTotalRecord());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	@RequestMapping(value = "queryAccessApiLog")
	public void queryAccessApiLogByPage(AccessApiLog log, HttpServletRequest request, HttpServletResponse response,Page<AccessApiLog> page) {
		Map<String, Object> params=new HashMap<String, Object>();
		ResultList<AccessApiLog> result = new ResultList<AccessApiLog>();
		if (!StringUtil.isEmpty(log.getCreateDateBagin())) {
			log.setCreateDateBagin(log.getCreateDateBagin() + " " + "00:00:00");
		}
		if (!StringUtil.isEmpty(log.getCreateDateEnd())){
			log.setCreateDateEnd(log.getCreateDateEnd() + " " + "23:59:59");
		}
		params.put("accessApiLog", log);
		page.setParams(params);
 		List<AccessApiLog> logs = null;
		try {
			logs = logService.queryAccinlogs(page);
			result.setRows(logs);
			result.setTotal(page.getTotalRecord());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		ResponseWriteUtils.returnAjax(response, JSONSerializer.toJSON(result).toString());
	}
	
	/*@RequestMapping(value = "queryExtApiLog")
	public ModelAndView queryExtApiLog(ModelAndView mav,ExtApiLog log, HttpServletRequest request, Page<ExtApiLog> page) {
		Map<String, Object> params=new HashMap<String, Object>();
		page.setParams(params);
 		List<ExtApiLog> logs = null;
		try {
			logs = logService.queryExtApiLogByPage(page);
			page.setResults(logs);
			mav.addObject(Contants.PAGES, page);
			mav.setViewName("log/querySysExecptionLoglist");
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return mav;
	}
	
	
	@RequestMapping(value = "extApiLogInit", method = RequestMethod.GET)
	public String extApiLogInit(Locale locale, Model model) {
		return "log/queryExtApiLogList";
	}*/
}
