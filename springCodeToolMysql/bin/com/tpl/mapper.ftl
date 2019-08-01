<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${basePackage}.mapper.${tableName}Mapper" >
	 
	<!--${tabelComments}通用查询条件 -->
	<sql id="${tableName}_WHERE">
		<where>
			<#list colList  as col>
			<#assign DATA_TYPE='${col.DATA_TYPE}'>
			<#if DATA_TYPE=="DATE">
			<if test="${col.COLUMN_NAME} != null and ${col.COLUMN_NAME} != ''">
				AND T.${col.COLUMN_NAME} = STR_TO_DATE(${'#'}{${col.COLUMN_NAME},'%Y-%m-%d %H:%i:%s')
			</if>
			<if test="${col.COLUMN_NAME}_MIN != null and ${col.COLUMN_NAME}_MIN != ''">
				AND T.${col.COLUMN_NAME} &gt;= STR_TO_DATE(${'#'}{${col.COLUMN_NAME}_MIN},'%Y-%m-%d %H:%i:%s')
			</if>
			<if test="${col.COLUMN_NAME}_MAX != null and ${col.COLUMN_NAME}_MAX != ''">
				AND T.${col.COLUMN_NAME} &lt;= STR_TO_DATE(${'#'}{${col.COLUMN_NAME}_MAX},'%Y-%m-%d %H:%i:%s')
			</if>
			<#else>
			<if test="${col.COLUMN_NAME} != null and ${col.COLUMN_NAME} != ''">
				AND T.${col.COLUMN_NAME} = ${'#'}{${col.COLUMN_NAME}}
			</if>
			<if test="${col.COLUMN_NAME}_LIKE != null and ${col.COLUMN_NAME}_LIKE != ''">
				AND T.${col.COLUMN_NAME} LIKE concat(concat('%',${'#'}{${col.COLUMN_NAME}_LIKE}),'%')
			</if>
			<if test="${col.COLUMN_NAME}_LLIKE != null and ${col.COLUMN_NAME}_LLIKE != ''">
				AND T.${col.COLUMN_NAME} LIKE concat('%',${'#'}{${col.COLUMN_NAME}_LIKE})
			</if>
			<if test="${col.COLUMN_NAME}_RLIKE != null and ${col.COLUMN_NAME}_RLIKE != ''">
				AND T.${col.COLUMN_NAME} LIKE concat(${'#'}{${col.COLUMN_NAME}_RIKE},'%')
			</if>
			</#if>
				
			</#list>
		</where>
	</sql>
	<!-- ${tabelComments}通用查询 -->
    <select id="select${tableName}" parameterType="java.util.Map" resultType="java.util.Map" >
     	 SELECT (@rowNum:=@rowNum+1) AS ROWNO,
        (SELECT COUNT(1) FROM ${tableName} T <include refid="${tableName}_WHERE"/>) AS TOTAL,
        E.* FROM (
        SELECT <#list colList as col><#assign fieldSqlType='${col.DATA_TYPE}'><#if fieldSqlType=="DATE">
        DATE_FORMAT(T.${col.COLUMN_NAME},'%Y-%m-%d %H:%i:%s') ${col.COLUMN_NAME}<#if col_index+1 != colList?size>,</#if><#else>T.${col.COLUMN_NAME}<#if col_index+1 != colList?size>,</#if></#if><#if (col_index+1)%5==0>${'\r\n\t\t\t'}</#if></#list>
         FROM ${tableName} T
        <include refid="${tableName}_WHERE"/>
         <if test="SORTNAME != null and SORTNAME != ''">
            	 ORDER BY  ${r'${SORTNAME}'}
            	 <if test="SORTORDER != null and SORTORDER != ''">
            	 	 ${r'${SORTORDER}'}
            	 </if>
         </if>
         
         <if test="PAGESIZE != null and PAGESIZE != ''">
            	limit ${r'${ROWS}'},${r'${PAGESIZE}'}
         </if> 
        
         )E,${tableName} T
         <if test="PAGESIZE != null and PAGESIZE != ''">
            	,(SELECT(@rowNum :=${r'${ROWS}'}))TEM_TB
         </if>
         <if test="PAGESIZE == null or PAGESIZE == ''">
            	,(SELECT(@rowNum :=0))TEM_TB
         </if>
        WHERE E.ID=T.ID
    </select>
    
    <!-- ${tabelComments}通用查询个数 -->
    <select id="select${tableName}Count" parameterType="java.util.Map" resultType="java.lang.Integer" >
     	 SELECT COUNT(1) FROM ${tableName} T <include refid="${tableName}_WHERE"/>
    </select>
    
    <!-- ${tabelComments}保存 -->
    <insert id="insert${tableName}" parameterType="java.util.Map" >
    	insert into ${tableName} 
    	<trim prefix="(" suffix=")" suffixOverrides="," >
    	<#list colList  as col>
			<#assign DATA_TYPE='${col.DATA_TYPE}'>
			<#if DATA_TYPE=="DATE">
			 ${col.COLUMN_NAME} ,
			<#else>
			<if test="${col.COLUMN_NAME} != null and ${col.COLUMN_NAME} != ''">
				  ${col.COLUMN_NAME} ,
			</if>
			</#if>
		</#list>
		</trim>
		<trim prefix="values (" suffix=")" suffixOverrides="," >
		<#list colList  as col>
			<#assign DATA_TYPE='${col.DATA_TYPE}'>
			<#if DATA_TYPE=="DATE">
				NOW(),
			<#else>
			<if test="${col.COLUMN_NAME} != null and ${col.COLUMN_NAME} != ''">
				 ${'#'}{${col.COLUMN_NAME}},  
			</if>
			</#if>
		</#list>
		</trim>
    </insert>
    
    <!-- ${tabelComments}修改-->
    <update id="update${tableName}" parameterType="java.util.Map" >
    	update   ${tableName} 
    	<set>
    	<#list colList  as col>
			<#assign DATA_TYPE='${col.DATA_TYPE}'>
			<#if DATA_TYPE=="DATE">
			<if test="${col.COLUMN_NAME}_NEW != null and ${col.COLUMN_NAME}_NEW != ''">
				  ${col.COLUMN_NAME}=  STR_TO_DATE(${'#'}{${col.COLUMN_NAME}_NEW},'%Y-%m-%d %H:%i:%s')
			</if>
			 
			<#else>
			<if test="${col.COLUMN_NAME}_NEW != null and ${col.COLUMN_NAME}_NEW != ''">
				  ${col.COLUMN_NAME}=  ${'#'}{${col.COLUMN_NAME}_NEW},
			</if>
			</#if>
		</#list>
		</set>
		<where>
			<#list colList  as col>
			<if test="${col.COLUMN_NAME} != null and ${col.COLUMN_NAME} != ''">
				AND ${col.COLUMN_NAME} = ${'#'}{${col.COLUMN_NAME}}
			</if>
			</#list>
		</where>
    </update>
    
    <!-- ${tabelComments}删除-->
    <delete id="delete${tableName}" parameterType="java.util.Map" >
    	delete  from ${tableName}  
    	<where>
			<#list colList  as col>
			<if test="${col.COLUMN_NAME} != null and ${col.COLUMN_NAME} != ''">
				AND ${col.COLUMN_NAME} = ${'#'}{${col.COLUMN_NAME}}
			</if>
			</#list>
		</where>
    </delete>
    
</mapper>