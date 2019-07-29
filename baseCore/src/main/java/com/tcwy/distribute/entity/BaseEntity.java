package com.tcwy.distribute.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "实体类通用属性")
public class BaseEntity {
	@ApiModelProperty(value="总数")
	public int total;
	@ApiModelProperty(value="当前数")
	public int rowno;
	@ApiModelProperty(value="排序字段")
	public String sortName;
	@ApiModelProperty(value="排序方向")
	public String sortOrder="DESC";
	@ApiModelProperty(value="当前页")
	public int curPage=1;
	@ApiModelProperty(value="分页大小")
	public int pageSize=100;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getRowno() {
		return rowno;
	}
	public void setRowno(int rowno) {
		this.rowno = rowno;
	}
	public String getSortName() {
		return sortName;
	}
	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	 

}
