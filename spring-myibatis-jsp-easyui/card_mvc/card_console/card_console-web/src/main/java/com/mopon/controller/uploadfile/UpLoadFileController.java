package com.mopon.controller.uploadfile;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mopon.util.ResponseWriteUtils;

@Controller
@RequestMapping("/upload")
public class UpLoadFileController {

	@RequestMapping(value="/index")
	public String index(Model model){
		return "upload/index";
	}


	@RequestMapping(value="/uploadify",method=RequestMethod.POST)
    public void uploadify(@RequestParam("Filedata")MultipartFile multiFile,HttpServletRequest request, HttpServletResponse response) throws IOException, FileUploadException{
        System.out.println("上传文件");
        System.out.println("name:"+multiFile.getOriginalFilename());
        System.out.println("inputstream"+multiFile.getInputStream());

        ServletContext sc = request.getSession().getServletContext();
        String dir = sc.getRealPath("/uploadFiles");//附件存放服务器的路径


        System.out.println(dir);
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        String filename = multiFile.getOriginalFilename();
        String realname = filename.substring(0, filename.indexOf("."));
        //防止文件被覆盖，以纳秒生成文件
//        Long _l = System.nanoTime();
//        String _extfilename = filename.substring(filename.indexOf("."));
//        filename = _l+_extfilename;
        String uploadPath = request.getContextPath()+""+dir+"/"+filename;
        String responseStr = "";
        try {
            FileUtils.writeByteArrayToFile(new File(dir, filename), multiFile.getBytes());
            responseStr = "上传成功！";

        } catch (Exception e) {
            e.printStackTrace();
            responseStr = "上传失败！";
            System.out.println("上传失败！");
        }

        ResponseWriteUtils.returnAjax(response, responseStr);
	}

	/*
	public static void main(String[] args){
		//连接本地的 Redis 服务
	      Jedis jedis = new Jedis("192.168.9.148");
	      System.out.println("Connection to server sucessfully");
	      //查看服务是否运行
	      System.out.println("Server is running: "+jedis.ping());
	      //获取缓存内的值
	      System.out.println("Server is running: "+jedis.get("testKey"));

	     String value =  jedis.set("name", "mengxiangrui");

	     Long n =  jedis.incr("CountTicket");
	     System.out.println("Server is running: "+n);
	     n = jedis.expire("CountTicket", 1);
	     System.out.println("Server is running: "+n);
	}*/
}
