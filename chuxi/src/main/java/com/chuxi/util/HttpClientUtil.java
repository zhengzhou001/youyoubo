package com.chuxi.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpClientUtil {
	
	//下载文件
	public static void downFile(String url, File file ) throws Exception{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		HttpResponse response = client.execute(httpget);  
		HttpEntity entity = response.getEntity();  
		InputStream is = entity.getContent();  
		FileOutputStream fileout = new FileOutputStream(file);  
		byte[] buffer=new byte[ 10 * 1024];  
		int ch = 0;  
		while ((ch = is.read(buffer)) != -1) {  
			fileout.write(buffer,0,ch);  
		}  
		is.close();  
		fileout.flush();  
		fileout.close(); 
	}
	
	public static void main(String[] args) {
 
	}
}
