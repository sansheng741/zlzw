package bean;

import java.util.ArrayList;
import java.util.List;

/**
 *  用户在上传商品的时候   大分类中的   小分类       为用户提供的选择
 *  
 * @author Administrator
 */
public class TypeSmall {
	private String type;
	private int flag;
	private int sid;
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
