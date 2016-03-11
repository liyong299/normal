package com.mopon.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;


/**
 * Excel包装类。
 */
public class Excel {
	Logger logger = Logger.getLogger(Excel.class);
	/** Excel模版路径 */
	private static String REPORT_DIR = "";
	private XLSTransformer transformer = new XLSTransformer();
	private Workbook workbook;

	/**
	 * 初始化生成Excel。
	 * 
	 * @param templateFileName
	 *            模版文件名称
	 * @param model
	 *            数据模型
	 */
	public void init(String templateFileName, Map<String, ?> model) {
		try {
			String string = getClass().getResource("/").getPath();
			logger.info("Excel==============string:"+string);
			String[] strs = string.split("/");
			String path = "";
			for (int i = 0; i < strs.length - 2; i++) {
				path += strs[i] + File.separator;
			}
			path = path + templateFileName;
//			path = path.substring(1, path.length());
			logger.info(">>>>>>>>>>模板文件路径为：" + path);
			File file = new File(path);
			
			InputStream in = new FileInputStream(file);

			//InputStream in = getClass().getResourceAsStream(path);
			workbook = transformer.transformXLS(in, model);
			//transformer.t
			in.close();

		} catch (Exception e) {
			throw new BusinessException("生成Excel时发生异常。", e);
		}
	}

	/**
	 * 初始化生成多工作表的Excel。
	 * 
	 * @param data
	 *            Excel数据
	 */
	public void init(ExcelData data) {
		try {
			// 如果没有设置工作表名称列表，则根据指定的工作表名称字段自动生成。
			List<String> sheetNames = data.getSheetNames();
			if (sheetNames.isEmpty()) {
				for (Object sheetModel : data.getSheetModels()) {
					sheetNames.add(BeanUtils.getField(sheetModel,
							data.getSheetNameField()).toString());
				}
			}
			InputStream in = getClass().getResourceAsStream(
					REPORT_DIR + "/" + data.getTemplateFileName());
			workbook = transformer.transformMultipleSheetsList(in,
					data.getSheetModels(), sheetNames,
					data.getSheetModelName(), data.getOtherModel(),
					data.getStartSheetNum());
			in.close();
		} catch (Exception e) {
			throw new BusinessException("生成Excel时发生异常。", e);
		}
	}

	/**
	 * 转换成Excel文件输出流。
	 * 
	 * @return 返回Excel文件输出流。
	 */
	public ByteArrayOutputStream toOutputStream() {
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			workbook.write(out);
			out.flush();
			return out;
		} catch (Exception e) {
			throw new BusinessException("转换Excel文件输出流时发生异常。", e);
		}
	}

	/**
	 * 转换成Excel文件输入流。
	 * 
	 * @return 返回Excel文件输入流。
	 */
	public ByteArrayInputStream toInputStream() {
		try {
			ByteArrayOutputStream out = toOutputStream();
			ByteArrayInputStream in = new ByteArrayInputStream(
					out.toByteArray());
			out.close();
			return in;
		} catch (Exception e) {
			throw new BusinessException("转换Excel文件输入流时发生异常。", e);
		}
	}

	/**
	 * 将Excel文件写入到输出流。
	 * 
	 * @param out
	 *            输出流
	 */
	public void writeTo(OutputStream out) {
		try {
			workbook.write(out);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.info("Excel==============Exception:"+e.getMessage());
			throw new BusinessException("将Excel文件写入到输出流时发生异常。", e);
		}
	}

	public XLSTransformer getTransformer() {
		return transformer;
	}

	public void setTransformer(XLSTransformer transformer) {
		this.transformer = transformer;
	}

	public Workbook getWorkbook() {
		return workbook;
	}

	public void setWorkbook(Workbook workbook) {
		this.workbook = workbook;
	}
	
	
	public InputStream getInputStream(String tpath){
		InputStream in=null;
		try {
			String string = getClass().getResource("/").getPath();
			logger.info("Excel==============string:"+string);
			String[] strs = string.split("/");
			String path = "";
			for (int i = 0; i < strs.length - 2; i++) {
				path += strs[i] + File.separator;
			}
			path = path + tpath;
//			path = path.substring(1, path.length());
			logger.info(">>>>>>>>>>模板文件路径为：" + path);
			File file = new File(path);
			
			in = new FileInputStream(file);
		} catch (Exception e) {
		}
		return in;
	}
}
