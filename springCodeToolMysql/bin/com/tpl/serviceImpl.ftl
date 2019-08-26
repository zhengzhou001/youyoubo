package ${basePackage}.service.impl;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import com.base.core.tools.BaseTools;
import com.base.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import ${basePackage}.service.I${tableName}Service;
import ${basePackage}.mapper.${tableName}Mapper;

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
@Service
public class ${tableName}ServiceImpl extends BaseServiceImpl implements I${tableName}Service {
	@Autowired
	private ${tableName}Mapper ${tableName}Mapper;
	
	public void insert${tableName}(Map map) throws Exception{
		map.put("ID",BaseTools.getNextSeq());
		${tableName}Mapper.insert${tableName}(map);
	}
	
	public void delete${tableName}(Map map) throws Exception{
		${tableName}Mapper.delete${tableName}(map);
	}
	
	public void update${tableName}(Map map) throws Exception{
		${tableName}Mapper.update${tableName}(map);
	}
	
	public	List<Map> select${tableName}(Map map) throws Exception{
		return ${tableName}Mapper.select${tableName}(map);
	}
	
	public	int select${tableName}Count(Map map){
		return ${tableName}Mapper.select${tableName}Count(map);
	}
}