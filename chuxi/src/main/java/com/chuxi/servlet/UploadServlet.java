package com.chuxi.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSONObject;
import com.base.core.tools.BaseTools;

@WebServlet(name = "upload", urlPatterns = "/upload")  //标记为servlet，以便启动器扫描
public class UploadServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	} 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String basePath ="/tmp/file/";
			File up = new File( basePath );
			if ( !up.exists() )
			{
				up.mkdir();
			}
			String path = basePath+BaseTools.getNextSeq()+"/";
			File targetFile = new File(path);
			if(!targetFile.exists()){
				targetFile.mkdirs();
			}
			request.setCharacterEncoding("utf-8");
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024*1024) ;
			ServletFileUpload upload = new ServletFileUpload(factory);
			Map retMap = new HashMap();
			retMap.put("path", path);

			//可以上传多个文件
			List<FileItem> list = upload.parseRequest(request);
			for(FileItem item : list){
				if(!item.isFormField()){
					retMap.put("name", item.getName());
					InputStream in = item.getInputStream() ;
					getFile(in,path+item.getName());
					break;
				}
			}


			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");
			PrintWriter out = response.getWriter();

			JSONObject jsonObject = new JSONObject();
			jsonObject.putAll(retMap);
			String msg =  jsonObject.toString();
			out.print(msg);
			out.close();

		} catch (Exception e) {
			// TODO: handle exception
		}
	} 
	
	public static void getFile(InputStream is,String fileName) throws IOException{
	    BufferedInputStream in=null;
	    BufferedOutputStream out=null;
	    in=new BufferedInputStream(is);
	    out=new BufferedOutputStream(new FileOutputStream(fileName));
	    int len=-1;
	    byte[] b=new byte[1024];
	    while((len=in.read(b))!=-1){
	        out.write(b,0,len);
	    }
	    in.close();
	    out.close();
	} 
}
