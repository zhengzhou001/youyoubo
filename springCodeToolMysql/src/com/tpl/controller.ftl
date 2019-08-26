package ${basePackage}.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.tcwy.distribute.controller.BaseController;
import com.tcwy.distribute.result.BaseResult;
import ${basePackage}.service.I${tableName}Service;

/**
 * <ol>
 * date:${date} editor:${editor}
 * <li>创建文档</li>
 * <li>${tabelComments}Service实现类</li>
 * </ol>
 * <ol>
 *
 * @author <a href="${author}">${editor}</a>
 * @version 1.0
 * @since 1.0
 */
@RestController
@Scope("prototype")
@RequestMapping(value="/${namespace}")
 public class ${tableName}Controller extends BaseController {
 	private Logger logger = LoggerFactory.getLogger(${tableName}Controller.class);
 	@Autowired
	private I${tableName}Service ${tableName}Service;
	
 
	@RequestMapping(value={"/insert${tableName}"}, method={RequestMethod.POST})
	public BaseResult insert${tableName}(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			${tableName}Service.insert${tableName}(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/delete${tableName}"}, method={RequestMethod.POST})
	public BaseResult delete${tableName}(@RequestBody Map map){
		BaseResult result = new BaseResult();
		try{
			${tableName}Service.delete${tableName}(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/update${tableName}"}, method={RequestMethod.POST})
	public BaseResult update${tableName}(@RequestBody Map map){
		BaseResult result = new BaseResult();
 		try{
			${tableName}Service.update${tableName}(map);
		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	@RequestMapping(value={"/select${tableName}"}, method={RequestMethod.POST})
	public	BaseResult<List<Map>> select${tableName}(@RequestBody Map map){
		BaseResult<List<Map>> result = new BaseResult<>();
		try{
			result.data= ${tableName}Service.select${tableName}(map);
 		} catch (Exception e) {
			code=-1;
			msg=e.getMessage();
			logger.error(msg);
		}
		result.code=code;
		result.msg=msg;
		return result;
	}
	
	 
	@RequestMapping(value={"/select${tableName}Count"}, method={RequestMethod.POST})
	public	BaseResult<Integer> select${tableName}Count(@RequestBody Map map){
		BaseResult<Integer> result = new BaseResult<>();
		try{
			result.data= ${tableName}Service.select${tableName}Count(map);
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