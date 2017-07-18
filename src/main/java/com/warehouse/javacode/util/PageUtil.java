package com.warehouse.javacode.util;

import java.util.List;

public class PageUtil {
	private List<Object> list;  //要返回的某一页的记录列表
	private int allRow; //总记录数
	private int totalPage;  //总页数
	private int currentPage;  //当前页
	private int pageSize;  //每页的记录数
	private boolean isFirstPage;  //是否为当前第一页
	private boolean isLastPage;  //是否为最后一页
	private boolean hasPreviousPage;  //是否有前一页
	private boolean hasNextPage;  //是否有下一页
	
	/**
	 * 通过无参构造方法，初始化数据
	 * @param pageNum
	 * @param pageSize
	 * @param allRow
	 */
	public PageUtil(int pageNum,int pageSize,int allRow){
		int totalPage = countTatalPage(pageSize, allRow); //总页数
		int currentPage = countCurrentPage(pageNum); // 当前页
		this.allRow=allRow;
		this.totalPage=totalPage;
		this.currentPage=currentPage;
		this.pageSize=pageSize;
		boolean isFirstPage=isFirstPage(currentPage);
		this.isFirstPage=isFirstPage;
		boolean isLastPage=isLastPage(currentPage, pageSize, allRow);
		this.isLastPage = isLastPage;
		boolean hasPreviousPage=isHasPreviousPage(currentPage);
		this.hasPreviousPage = hasPreviousPage;
		boolean hasNextPage=isHasNextPage(currentPage, pageSize, allRow);
		this.hasNextPage = hasNextPage;
	}
	
	
	/**
	 * 计算总页数  静态方法
	 * @param pageSize  每页的记录数
	 * @param allRow  总记录数
	 * @return 总页数
	 */
	public static int countTatalPage(final int pageSize,final int allRow){
		int toalPage = allRow % pageSize == 0 ? allRow/pageSize : allRow/pageSize + 1;
		return toalPage;
	}
	
	/**
	 * 计算当前页开始的记录
	 * @param pageSize 每页记录数
	 * @param currentPage 当前第几页
	 * @return 当前页开始记录号
	 */
	public static int countOffset(final int pageSize,final int currentPage){
		final int offset = pageSize * (currentPage - 1);
		return offset;
	}
	/**
	 * 计算当前页，若为0或者请求的URL中没有“?page = ”则用1代替
	 * @param page 传入的参数（可能为空，即0  则返回1）
	 * @return
	 */
	public static int countCurrentPage(int page){
		final int curpage = (page == 0 ? 1 : page);
		return curpage;
	}
	
	public List<Object> getList() {
		return list;
	}
	public void setList(List<Object> list) {
		this.list = list;
	}
	public int getAllRow() {
		return allRow;
	}
	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public boolean isFirstPage(int page) {
	    if(countCurrentPage(page)==1){
	    	
	    	isFirstPage=true;
	    }
	    else{
	    	isFirstPage=false;
	    }
		return isFirstPage;
	}
	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
	public boolean isLastPage(int page,int pageSize,int allRow) {
		if(countCurrentPage(page)>=countTatalPage(pageSize, allRow)){
			isLastPage=true;
		}
		else{
			isLastPage=false;
		}
		return isLastPage;
	}
	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}
	public boolean isHasPreviousPage(int page) {
		if(countCurrentPage(page)<=1){
			hasPreviousPage=true;
		}
		else{
			hasPreviousPage=false;
		}
		return hasPreviousPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public boolean isHasNextPage(int page,final int pageSize,final int allRow) {
		if(countCurrentPage(page)<countTatalPage(pageSize, allRow)){
			hasNextPage=true;
		}
		else{
			hasNextPage=false;
		}
		return hasNextPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
}
