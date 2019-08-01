package ${basePackage}.service;

import java.util.List;
import java.util.Map;
import com.base.service.IBaseService;

/**
 * <ol>
 * date:${date} editor:${editor}
 * <li>创建文档</li>
 * <li>${tabelComments}Service接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="${author}">${editor}</a>
 * @version 1.0
 * @since 1.0
 */
public interface I${tableName}Service extends IBaseService{
	 
	public void insert${tableName}(Map map) throws Exception;
	
	public void delete${tableName}(Map map) throws Exception;
	
	public void update${tableName}(Map map) throws Exception;
	 
	public	List<Map> select${tableName}(Map map) throws Exception;
	
	public	int select${tableName}Count(Map map);
}