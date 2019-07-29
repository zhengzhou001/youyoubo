package com.base.core.tools;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
 

/**
 */
public class BaseTools {
	private static Logger log = Logger.getLogger(BaseTools.class);

	protected BaseTools() {

	}
	private static int seq = 0;
	private static final int MAX_PER_SECOND = 1000;

	/**
	 * 生成15位的数字流水号(16位以后会出现科学计数影响业务操作)
	 * <p>
	 * <I>生成规则为:</I><b>yyMMddHHmmss+1位顺序号</b>
	 * </p>
	 *
	 * @return 15位流水号
	 */
	public static synchronized String getNextSeq() {
		seq++;
		return (new SimpleDateFormat("yyMMddHHmmss").format(new Date()))
				+ String.format("%03d", seq %= MAX_PER_SECOND);
	}


	/**
	 * 获取count个随机数子
	 * @param count 随机数个数
	 * @return count随机数字
	 */
	public static  String getRandomNumber(int count){
		StringBuffer sb = new StringBuffer();
		String str = "0123456789";
		Random r = new Random();
		for(int i=0;i<count;i++){
			int num = r.nextInt(str.length());
			sb.append(str.charAt(num));
			str = str.replace((str.charAt(num)+""), "");
		}
		return sb.toString();
	}
	/**
	 * 将时间字符串转换为指定格式字符串
	 * @param sourceDateStr 2018-01-01 12:00:00
	 * @param sourceDateFormat yyyy-MM-dd HH:mm:ss
	 * @param targetDateFormat yyyyMMdd
	 * @return 20180101
	 */
	public static  String getFormatDateStr(String sourceDateStr,String sourceDateFormat,String targetDateFormat){
		try {
			SimpleDateFormat sourceSimpleDateFormat = new SimpleDateFormat(sourceDateFormat);
			SimpleDateFormat targetSimpleDateFormat = new SimpleDateFormat(targetDateFormat);
			Date date = sourceSimpleDateFormat.parse(sourceDateStr);
			return targetSimpleDateFormat.format(date);
		} catch (ParseException e) {
			return "";
		}
	}
	
	/**
	 * 时间字符串转时间对象
	 * @param sourceDateStr
	 * @param sourceDateFormat
	 * @return Date
	 */
	public static  Date getDateByStr(String sourceDateStr,String sourceDateFormat){
		try {
			SimpleDateFormat sourceSimpleDateFormat = new SimpleDateFormat(sourceDateFormat);
			Date date = sourceSimpleDateFormat.parse(sourceDateStr);
			return date;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 取得当前日期
	 *
	 * @param type 格式类型 0:yyyy-MM-dd 1:yyyy-MM-dd HH:mm:ss 2:yyyyMMdd 3:HHmmss 4:yyyyMMddHHmmss
	 * @return String 返回当前年月日
	 */
	public static String getCurStrDate(int type) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		switch (type) {
		case 0:
			pattern = "yyyy-MM-dd";
			break;
		case 1:
			pattern = "yyyy-MM-dd HH:mm:ss";
			break;
		case 2:
			pattern = "yyyyMMdd";
			break;
		case 3:
			pattern = "HHmmss";
			break;
		case 4:
			pattern = "yyyyMMddHHmmss";
			break;
		case 5:
			pattern = "yyyyMM";
			break;
		}
		return (new SimpleDateFormat(pattern)).format(new Date());
	}
	/**
	 * 从cookie中获取值
	 *
	 * @param key cookie中的键值
	 * @return 返回对应的值，找不到或者不存在时返回""
	 */
	public static String getCookieVal(String key) {
		Cookie[] cookies = getCookies();
		if (cookies == null)
			return "";
		Cookie cookie;
		String val = "";
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			if (cookie.getName().equals(key)) {
				val = cookie.getValue();
				try {
					val = val != null ? URLDecoder.decode(val, "utf-8") : "";
				} catch (UnsupportedEncodingException e) {
					val = "";
				}
			}
		}
		return val;
	}

	public static boolean delCookie(String key) {
		Cookie[] cookies = getCookies();
		if (cookies == null)
			return true;

		Cookie cookie;
		for (int i = 0; i < cookies.length; i++) {
			cookie = cookies[i];
			if (cookie.getName().equals(key)) {
				cookie.setValue(null);
				cookie.setPath("/");
				cookie.setMaxAge(0);
				getResponse().addCookie(cookie);
				return true;
			}
		}
		return false;
	}
	/**
	 * 通过json字符串生成Map对象
	 *
	 * @param strJson json字符串
	 * @return 返回Map对象
	 */
	public static Map getMapByJson(String strJson) {
		return strJson.isEmpty() ? null : JSON.parseObject(strJson, Map.class);
	}
	
	/**
	 * 获取cookie数组
	 *
	 * @return 返回cookie数组, 否则返回null
	 */
	public static Cookie[] getCookies() {
		return getRequest().getCookies();
	}
	public static HttpServletRequest getRequest() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
   		return servletRequestAttributes.getRequest();
	}

	public static HttpServletResponse getResponse() {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
   		return servletRequestAttributes.getResponse();
	}
	
	/**
	 * ajax请求返回
	 *
	 * @param retCode 返回代码
	 * @param retInfo 返回消息
	 */
	public static void ajaxResponseTxt(int retCode, String retInfo) {
		Map map = new HashMap();
		map.put("data", "");
		ajaxResponseTxt(retCode, retInfo, map);
	}
	/**
	 * ajax请求返回
	 *
	 * @param retCode 返回代码
	 * @param retMsg  返回消息
	 * @param map     简单的附加
	 */
	public static void ajaxResponseTxt(int retCode, String retMsg, Map map) {
		List list = new ArrayList();
		list.add(map);
		ajaxResponseTxt(retCode, retMsg, list);
	}
	/**
	 * ajax请求返回
	 *
	 * @param retCode 返回代码
	 * @param retMsg  返回消息
	 * @param listMap 是HashMap类型的集合封装需要转化成JSON字符串的值对
	 */
	public static void ajaxResponseTxt(int retCode, String retMsg, List listMap) {
		HttpServletResponse response = getResponse();

		response.setHeader("Charset", "UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Expires", "0");
		response.setHeader("Pragma", "No-cache");

		//使用csrf-token解决csrf安全问题
		response.setHeader(CsrfToken.getKey(), CsrfToken.setCsrfTokenInSession());
		//返回的是txt文本文件
		response.setContentType("text/json;charset=UTF-8");

		Map tempMap = new HashMap();
		tempMap.put("code", String.valueOf(retCode));
		tempMap.put("msg", retMsg);

		String strJson;
		JSONObject jsonObject = new JSONObject();
		jsonObject.putAll(tempMap);

		for (Object aListMap : listMap) {
			jsonObject.putAll((Map) aListMap);
		}

		//在ResultHashMap类中完成字符集转换
		strJson = jsonObject.toString();

		//添加输出压缩
		String encoding = getRequest().getHeader("Accept-Encoding");
		boolean b = false;
		if (b) {
			GZIPOutputStream gzipOut = null;
			InputStream in = null;
			try {
				gzipOut = new GZIPOutputStream(response.getOutputStream());
				//修正编码问题
				in = new ByteArrayInputStream(strJson.getBytes("UTF-8"));
				byte[] buf = new byte[2048];
				int len;
				while ((len = in.read(buf)) > 0) {
					gzipOut.write(buf, 0, len);
				}
				gzipOut.finish(); 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (in != null)
						in.close();
					if (gzipOut != null)
						gzipOut.close();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			PrintWriter out = null;
			try {
				out = response.getWriter();
				out.write(strJson);
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (out != null)
					out.close();
			}
		}

		 
	}

	public static void main(String[] args) {


	}
}
