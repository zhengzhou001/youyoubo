package com.youyoubo.wx.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
 import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

 
/**
 * @Desc:   http请求util
 * @author  zhoucan
 * @date    2016年3月29日 下午1:51:11
 * @version V1.0
 */
public class HttpRequestUtil {

	private static final Log log = LogFactory.getLog(HttpRequestUtil.class);
	
	public static String httpPost(String url) {
		return httpPost(url, null);
	}

	public static String httpPost(String url, HttpEntity se) {
		HttpPost http = new HttpPost(url);
		http.setEntity(se);
		return httpRequest(http);
	}

	public static String httpGet(String url) {
		HttpGet http = new HttpGet(url);
		return httpRequest(http);
	}

	public static String httpGet(String url, String encoding) {
		HttpGet http = new HttpGet(url);
		return httpRequest(http, encoding);
	}

	public static String httpRequest(HttpRequestBase http) {
		return httpRequest(http, null);
	}

	public static String httpRequest(HttpRequestBase http, String encoding) {
		String result = null;
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(http);
			HttpEntity entity = response.getEntity();
			if (StringUtils.isEmpty(encoding)) {
				result = EntityUtils.toString(entity);
			} else {
				result = EntityUtils.toString(entity, encoding);
			}
		} catch (IOException e) {
			log.error(e);
		} finally {
			try {
				if (response != null) {
					response.close();
				}
			} catch (IOException e) {
				log.error(e);
			}
			try {
				httpclient.close();
			} catch (IOException e) {
				log.error(e);
			}
		}
		
		return result;
	}
	
	/**
	 * @Desc:  将对象转换为StringEntity
	 * @param  @param obj
	 * @param  @return
	 * @param  @throws UnsupportedEncodingException
	 * @return StringEntity
	 * @throws
	 * @author zhoucan
	 * @date   2016年3月29日 下午2:50:51
	 */
	public static StringEntity convertToStringEntity(Object obj) throws UnsupportedEncodingException {
		String msg = JSONObject.toJSONString(obj);
		StringEntity se = new StringEntity(msg, "utf-8");
		return se;
	}
	
}
