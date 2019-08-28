package com.chuxi.user.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.base.core.tools.BaseTools;
import com.chuxi.config.BaseConfig;
import com.chuxi.sms.service.ISMS_CODEService;
import com.chuxi.user.service.IUSER_INFOService;
import com.chuxi.util.HttpClientUtil;
import com.tcwy.distribute.controller.BaseController;
import com.tcwy.distribute.result.BaseResult;

import me.chanjar.weixin.mp.api.WxMpService;

/**
 * <ol>
 * date:2019-08-23 editor:dingshuangbo
 * <li>创建文档</li>
 * <li>Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="mailto:2449709309@qq.com">dingshuangbo</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Scope("prototype")
@RequestMapping(value="/user")
public class USER_INFOController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(USER_INFOController.class);
	@Autowired
	IUSER_INFOService USER_INFOService;
	@Autowired
	private WxMpService wxMpService;
	@Autowired
	private BaseConfig baseConfig;
	@Autowired
	private ISMS_CODEService SMS_CODEService;


	@RequestMapping(value={"/insertUSER_INFO"}, method={RequestMethod.POST})
	public BaseResult insertUSER_INFO(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			USER_INFOService.insertUSER_INFO(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}

	@RequestMapping(value={"/deleteUSER_INFO"}, method={RequestMethod.POST})
	public BaseResult deleteUSER_INFO(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			USER_INFOService.deleteUSER_INFO(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}


	@RequestMapping(value={"/updateUSER_INFO"}, method={RequestMethod.POST})
	public BaseResult updateUSER_INFO(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			String YZM = MapUtils.getString(map, "YZM","");
			if (StringUtils.isNotEmpty(YZM)) {
				//判断验证码是否正确
				List list = SMS_CODEService.selectSMS_CODE(ArrayUtils.toMap(new String[][]{
					{"CODE",MapUtils.getString(map, "YZM")},
					{"PHONE",MapUtils.getString(map, "PHONE")},
					{"STATE","1"},
					{"FLAG","1"},
				}));
				if (list==null||list.size()==0) {
					code=-1;
					msg="验证码已过期或不正确";
					result.code=code;
					result.msg=msg;
					return result;
				}
			}
			String SERVERID = MapUtils.getString(map, "SERVERID","");
			
			if (StringUtils.isNotBlank(SERVERID)) {
				//用户换头像了 
				File headImg=null;
				File tmpFile = new File(baseConfig.getTmpFilePath());
				if (!tmpFile.exists()) {
					tmpFile.mkdirs();
				}
				File file =  wxMpService.getMaterialService().mediaDownload(SERVERID); //下载新头像
				headImg = new File(baseConfig.getFilePath()+"HEAD"+File.separator+BaseTools.getCurStrDate(2)+File.separator+file.getName());
				FileUtils.moveFile(file, headImg);//移动到头像目录
				map.put("HEADIMGURL_NEW", headImg.getAbsolutePath());
			}
			USER_INFOService.updateUSER_INFO(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}

	@RequestMapping(value={"/selectUSER_INFO"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> selectUSER_INFO(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= USER_INFOService.selectUSER_INFO(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}


	@RequestMapping(value={"/selectUSER_INFOCount"}, method={RequestMethod.POST})
	public	BaseResult<Integer> selectUSER_INFOCount(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= USER_INFOService.selectUSER_INFOCount(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}



	@RequestMapping(value={"/doSave"}, method={RequestMethod.POST})
	public BaseResult doSave(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{

			int count = USER_INFOService.selectUSER_INFOCount(ArrayUtils.toMap(new String[][]{
				{"OPENID",MapUtils.getString(map, "OPENID")},
			}));
			if (count>0) {
				code=-1;
				msg="已经是会员了，请勿重复办理";
				result.code=code;
				result.msg=msg;
				return result;
			}
			//判断验证码是否正确
			List list = SMS_CODEService.selectSMS_CODE(ArrayUtils.toMap(new String[][]{
				{"CODE",MapUtils.getString(map, "YZM")},
				{"PHONE",MapUtils.getString(map, "PHONE")},
				{"STATE","1"},
				{"FLAG","1"},
			}));
			if (list==null||list.size()==0) {
				code=-1;
				msg="验证码已过期或不正确";
				result.code=code;
				result.msg=msg;
				return result;
			}

			String SERVERID=MapUtils.getString(map, "SERVERID");
			File headImg=null;
			if (StringUtils.isNotBlank(SERVERID)) {
				//用户换头像了 
				File tmpFile = new File(baseConfig.getTmpFilePath());
				if (!tmpFile.exists()) {
					tmpFile.mkdirs();
				}
				File file =  wxMpService.getMaterialService().mediaDownload(SERVERID); //下载新头像
				headImg = new File(baseConfig.getFilePath()+"HEAD"+File.separator+BaseTools.getCurStrDate(2)+File.separator+file.getName());
				FileUtils.moveFile(file, headImg);//移动到头像目录
			}else{
				//用户用的微信头像
				//下载用户微信头像
				//有0、46、64、96、132数值可选，0代表640*640正方形头像
				String HEADIMGURL=MapUtils.getString(map, "HEADIMGURL");
				HEADIMGURL=StringUtils.replace(HEADIMGURL, "/46", "/0");
				HEADIMGURL=StringUtils.replace(HEADIMGURL, "/64", "/0");
				HEADIMGURL=StringUtils.replace(HEADIMGURL, "/96", "/0");
				HEADIMGURL=StringUtils.replace(HEADIMGURL, "/132", "/0");

				headImg = new File(baseConfig.getFilePath()+"HEAD"+File.separator+BaseTools.getCurStrDate(2)+File.separator+BaseTools.getNextSeq()+".jpg");
				if (headImg.getParentFile()!= null) {
					headImg.getParentFile().mkdirs();
				}
				HttpClientUtil.downFile(HEADIMGURL, headImg);
			}
			map.put("HEADIMGURL", headImg.getAbsolutePath());
			for (int i = 0; i < list.size(); i++) {
				Map tmap = (Map) list.get(i);
				SMS_CODEService.updateSMS_CODE(ArrayUtils.toMap(new String[][]{
					{"ID",MapUtils.getString(tmap, "ID")},
					{"STATE_NEW","0"},
				}));
			}
			USER_INFOService.insertUSER_INFO(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}


	//文件下载相关代码
	@RequestMapping(value = "/downloadImage",method = RequestMethod.GET)
	public String downloadImage(String fileAbs,HttpServletRequest request, HttpServletResponse response){
		File file = new File(fileAbs);
		if (file.exists()) {
			response.setContentType("application/force-download");// 设置强制下载不打开
			response.addHeader("Content-Disposition",
					"attachment;fileName=" + file.getName());// 设置文件名
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return null;
	}



}