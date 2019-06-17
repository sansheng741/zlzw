package bean;

import java.util.ArrayList;
import java.util.List;

public class GoodDiv {
	private int pageMax;
	 public int getPageMax() {
		return pageMax;
	}
	public void setPageMax(int pageMax) {
		this.pageMax = pageMax;
	}
	// 显示的内容的 数目
    private int pageSize;
    // 当前 页数
    private int pageNumber;
    
    // 总页数
    private long total;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
    
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	@Override
	public String toString() {
		return "GoodDiv [type=" + type + ", bid=" + bid + ", list=" + list + "]";
	}
	private String type;
	private int bid;
	private int sid;
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getBid() {
		return bid;
	}
	public GoodDiv(){
		list=new ArrayList<Goods>();
	}
	
	public void setBid(int bid) {
		this.bid = bid;
	}
	private List<Goods>list;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Goods> getList() {
		return list;
	}
	public void setList(List<Goods> list) {
		this.list = list;
	}

}
