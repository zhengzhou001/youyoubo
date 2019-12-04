package com.chuxi.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.core.tools.BaseTools;
import com.tcwy.distribute.controller.BaseController;


@Controller
@RequestMapping("/common")
@Scope("prototype")
public class CommonConreoller  extends BaseController {
	private Logger logger = LoggerFactory.getLogger(CommonConreoller.class);

	@RequestMapping(value="/upload")
	@ResponseBody
	public Map upload(HttpServletRequest request, HttpServletResponse response ) throws Exception{
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
				FileOutputStream outputStream  = new FileOutputStream(path+item.getName());
				int bytesWritten = 0;
				int byteCount = 0;
				byte[] bytes = new byte[1024];
				while ((byteCount = in.read(bytes)) != -1)
				{
					outputStream.write(bytes, bytesWritten, byteCount);
					bytesWritten += byteCount;
				}
				in.close();
				outputStream.close();
				break;
			}
		}
		 
		
		return retMap ;
	}
}	
