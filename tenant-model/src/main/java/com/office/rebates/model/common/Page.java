package com.office.rebates.model.common;

/**
 * 主要功能是在视图层和控制层之间传输数据， 里面封装了需要显示的分页信息和分页数据�??
 */
public class Page {

	private int number = 0; // 当前页数
	private int totalPages; // 总页
	private int totalElements; // 总记录数
	private int size = 20; // 每页显示行数

	public Page() {
	}

	public Page(int number, int totalElements) {
		this.setNumber(number);
		this.totalElements = totalElements;
	}

	public int getNumber() {
		if (number > getTotalPages()) {
			number = getTotalPages();
		} else if (number < 0) {
			number = 0;
		}
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getTotalPages() {

		totalPages = totalElements / size;
		if (totalElements % size != 0)
			totalPages++;
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
