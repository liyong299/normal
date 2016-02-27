/**
 * 
 */
package com.dup.core.xls;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Before;

/**
 * @author hugoyang
 * 
 */
public class XLSExport {

	private XLSTransformer transformer = new XLSTransformer();
	private Workbook workbook;
	
	@Before
	public void before()
	{
		
	}
	
	public ExcelData getData()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dailys", getUserList());
		map.put("startDate", "2015-09-23");
		map.put("endDate", "2015-09-24");
		map.put("now", Calendar.getInstance().getTime());
		map.put("list", getUserList());
		
		
		ExcelData data = new ExcelData();
		String fileName = "cardStat" + new Date() + ".xls";
		data.setTemplateFileName("cardStat.xls");
		data.setSheetModels(getUserList());
		
		List<String> list2= new ArrayList<String>();
		list2.add("wangbadan");
		
		data.setSheetNames(list2);
		data.setOtherModel(map);
		data.setSheetModelName("test");
		data.setStartSheetNum(1);
		return data;
	}
	
	public List<UserModel> getUserList()
	{
		List<UserModel> list = new ArrayList<UserModel>();
		for (int i = 0; i < 3; i++)
		{
			UserModel userModel = new UserModel();
			userModel.setName("name_" + i);
			list.add(userModel);
		}
		return list;
	}
	
	public void exportXLXS2() {
		try {
			ExcelData data = getData();
			List<String> sheetNames = data.getSheetNames();
			if (sheetNames.isEmpty()) {
				int i = 0;
				for (Object sheetModel : data.getSheetModels()) {
					sheetNames.add("wangbadan_" + i++);
				}
			}
			InputStream in = getClass().getResourceAsStream("巡检商户满意度报表模板.xlsx");
			workbook = transformer.transformMultipleSheetsList(in,
					data.getSheetModels(), sheetNames,
					data.getSheetModelName(), data.getOtherModel(),
					data.getStartSheetNum());
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
