package ${basePackage}.mapper;

import java.util.List;
import java.util.Map;


/**
 * <ol>
 * date:${date} editor:${editor}
 * <li>创建文档</li>
 * <li>${tabelComments}Mapper接口</li>
 * </ol>
 * <ol>
 *
 * @author <a href="${author}">${editor}</a>
 * @version 1.0
 * @since 1.0
 */
public interface ${tableName}Mapper {
	public void insert${tableName}(Map map);
	
	public void delete${tableName}(Map map);
	
	public void update${tableName}(Map map);
	
	public	List<Map> select${tableName}(Map map);
	
	public	int select${tableName}Count(Map map);
}