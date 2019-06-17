package bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户在上传商品的时候   大分类      为用户提供的选择
 * @author Administrator
 *
 */
public class TypeBig {

	private String type;
	private int bid;
	private int flag;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
