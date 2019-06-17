package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LX
 * @date 2018/11/17 - 19:47
 */
public class PageBean {
	//当前页
	private Integer currentPage;
	//共有多少页
	private Integer totalPage;
	//多少条记录
	private Integer totalCount;
	//当前页内容
	private List<?> goodsList = new ArrayList<>();
	
	
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public List<?> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<?> goodsList) {
		this.goodsList = goodsList;
	}
	
	
	
	
}
