package org.jelly.model;

import java.util.List;
/**
 * 分页模型
 * @author Lychie Fan
 * @since 1.2.0
 */
public class Pager <E> {

	// 分页结果集
	private List<E> result;
	// 总记录条数
	private int totalCount;
	// 当前的页码
	private int thisPage;
	// 上一页页码
	private int backPage;
	// 下一页页码
	private int nextPage;
	// 每页显示的数据条数
	private int pageSize;
	// 分页的总页数
	private int totalPage;
	// 是否是第一页
	private boolean isFirstPage;
	// 是否是最后一页
	private boolean isLastPage;
	
	/**
	 * @param thisPage 当前页的页码 ( 页码从第一页开始, 错误的页码模型会自动更正 )
	 * @param pageSize 每页显示的记录的条数
	 * @param totalCount 数据源中数据记录的总条数
	 * @param result 当前页的结果集
	 * @since 1.2.0
	 */
	public Pager(int thisPage, int pageSize, int totalCount, List<E> result){
		this.result = result;
		this.pageSize = pageSize;
		this.setTotalCount(totalCount);
		this.setThisPage(thisPage);
	}

	// 数据记录的总条数
	private void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		// 计算分页的总页数
		totalPage = totalCount / pageSize;
		if(totalCount % pageSize != 0){
			totalPage += 1;
		}
	}

	// 当前页的页码
	private void setThisPage(int thisPage) {
		this.thisPage = thisPage;
		// 处理页码小于等于零的情况
		if(thisPage <= 0){
			this.thisPage = 1;
		}
		// 处理页码大于分页总页数的情况
		if(thisPage > totalPage){
			this.thisPage = totalPage;
		}
		reset();
	}

	/**
	 * <des> 获取当前页的结果集 </des>
	 * @since 1.2.0
	 */
	public List<E> getResult() {
		return result;
	}

	/**
	 * <des> 获取数据源中数据记录的总条数 </des>
	 * @since 1.2.0
	 */
	public int getTotalCount() {
		return totalCount;
	}

	/**
	 * <des> 获取当前页的页码, 索引从第一页开始 </des>
	 * @since 1.2.0
	 */
	public int getThisPage() {
		return thisPage;
	}

	/**
	 * <des> 获取上一页的页码 </des>
	 * @since 1.2.0
	 */
	public int getBackPage() {
		return backPage;
	}

	/**
	 * <des> 获取下一页的页码 </des>
	 * @since 1.2.0
	 */
	public int getNextPage() {
		return nextPage;
	}
	
	/**
	 * <des> 获取每页显示的记录的条数 </des>
	 * @since 1.2.0
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * <des> 获取分页的总页数 </des>
	 * @since 1.2.0
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * <des> 当前页是否是第一页 </des>
	 * @since 1.2.0
	 */
	public boolean isIsFirstPage() {
		return isFirstPage;
	}

	/**
	 * <des> 当前页是否是最后一页 </des>
	 * @since 1.2.0
	 */
	public boolean isIsLastPage() {
		return isLastPage;
	}
	
	// reset
	private void reset(){
		// 判定当前页是否是第一页
		this.isFirstPage = this.thisPage == 1 ? true : false;
		// 判定当前页是否是最后一页
		this.isLastPage = this.thisPage >= totalPage ? true : false;
		// 处理上一页的页码数, 若当前页是第一页, 则上一页也为当前页
		this.backPage = isFirstPage ? 1 : this.thisPage - 1;
		// 处理下一页的页码数, 若当前页是最后一页, 则下一页也为当前页
		this.nextPage = isLastPage ? totalPage : this.thisPage + 1;
		// 处理当前页结果集为空的情况, 若为空, 下一页也为当前页
		this.nextPage = isFirstPage && isLastPage ? 1 : this.nextPage;
	}
	
}