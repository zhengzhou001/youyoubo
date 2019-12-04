package com.chuxi.yabo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcwy.distribute.controller.BaseController;
import com.tcwy.distribute.result.BaseResult;


@RestController
@Scope("prototype")
@RequestMapping(value="/yabo")
public class YaboController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(YaboController.class);


	@RequestMapping(value={"/upload"}, method={RequestMethod.POST})
	public BaseResult upload(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			String filePath = MapUtils.getString(map, "filePath");
			String fileName = MapUtils.getString(map, "fileName");

			File file = new File(filePath+fileName);
			InputStream is= new FileInputStream(file);
			Workbook  wb = WorkbookFactory.create(is);
			Sheet sheet = wb.getSheet("销售分析");
			int rowNos = sheet.getLastRowNum();// 得到excel的总记录条数
			for (int i = 0; i <= rowNos; i++) {// 遍历行
				Row row = sheet.getRow(i);
				if(row != null){
					int columNos = row.getLastCellNum();// 表头总共的列数
					//获取 J列 和K列
					Cell cellJ= row.getCell(9);
					Cell cellK= row.getCell(9);
					String strJ="";
					strJ = cellJ.getStringCellValue();
					try {
						double doubleJ = Double.parseDouble(strJ);
						DecimalFormat df = new DecimalFormat("#.0");
 						if (doubleJ>=0.0) {
							cellK.setCellValue("销售正常，长款"+df.format(doubleJ)+"元");
						}else if (doubleJ>=-10.0) {
							cellK.setCellValue("销售正常，短款"+df.format(-doubleJ)+"元");
						}else{
							cellK.setCellValue("销售正常，短款"+df.format(-doubleJ)+"元");
						}
					} catch (Exception e) {
 					}
					 
 				}
			}


		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}


}