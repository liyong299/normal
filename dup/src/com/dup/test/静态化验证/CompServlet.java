///**
// * 
// */
//package com.dup.test.静态化验证;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author ly
// *
// */
//public class CompServlet extends HttpServlet
//{
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	/* (non-Javadoc)
//	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//	 */
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
//	{
//		resp.setContentType("text/html;charset=UTF-8");  
//		
//		String channelCode = req.getParameter("channelCode");
//		String cinemaCode = req.getParameter("cinemaCode");
//		
//		String oldURL = req.getParameter("oldURL");
//		String newURL = req.getParameter("newURL");
//		
//		
//		静态化验证入口 comp = new 静态化验证入口(oldURL, newURL);
//		if (channelCode == null && cinemaCode == null)
//		{
//			 resp.getOutputStream().write(comp.comp().toString().getBytes("UTF-8")); 
//			 return;
//		}
//		if (channelCode == null && cinemaCode != null)
//		{
//			 resp.getOutputStream().write(comp.comp(cinemaCode).toString().getBytes("UTF-8")); 
//			 return;
//		}
//		if (channelCode != null && cinemaCode != null)
//		{
//			 resp.getOutputStream().write(comp.comp(channelCode, cinemaCode).toString().getBytes("UTF-8")); 
//			 return;
//		}
//		
//		resp.getOutputStream().write("参数影院编码为空，暂时不能处理。".getBytes("UTF-8")); 
//		return;
//	}
//
//	/* (non-Javadoc)
//	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
//	 */
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
//	{
//		// TODO Auto-generated method stub
//		this.doGet(req, resp);
//	}
//
//}
