package com.office.rebates.model.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lanner
 */
public class ListResult<T extends Serializable> implements Serializable
{
	private static final long serialVersionUID = -3357057038349867138L;
	private List<T> listData;
	private int totalNum;
	private int pageSize;
	private int pageNum;
	
	public ListResult()
	{
		listData = new ArrayList<T>();
	}

	public List<T> getListData()
	{
		return listData;
	}

	//public Integer add(T data)
	//{
	//	listData.add(data);
		//this.totalNum = listData.size();
	//	return this.totalNum;
	//}

	public void setListData(List<T> data)
	{
		this.listData = data;
		//this.totalNum = data.size();
	}

	public int getPageSize()
	{
		return pageSize;
	}

	public void setPageSize(int pageSize)
	{
		this.pageSize = pageSize;
	}

	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	public void setTotalNum(int totalNum)
	{
		this.totalNum = totalNum;
	}

	public int getTotalNum()
	{
		return totalNum;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
