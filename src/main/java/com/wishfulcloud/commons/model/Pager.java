package com.wishfulcloud.commons.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页类
 * 
 * @author wxz
 *
 * @param <T>
 */
public class Pager<T> {
	
	private int pageNo = 1; // 当前页码
	
	private int pageSize; // 页面大小，设置为“-1”表示不进行分页（分页无效）
	
	private long totalCount; // 总条数
	
	private List<T> dataList = new ArrayList<T>(); // 数据

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	
}
