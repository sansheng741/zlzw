package bean;

import java.util.List;

public class GoodUserDiv {
	
	private Goods good;
	private TypeBig type;
	private User user;
	private String time;
	private List<Goods>list;
	
	public List<Goods> getList() {
		return list;
	}
	public void setList(List<Goods> list) {
		this.list = list;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Goods getGood() {
		return good;
	}
	public void setGood(Goods good) {
		this.good = good;
	}
	public TypeBig getType() {
		return type;
	}
	public void setType(TypeBig type) {
		this.type = type;
	}

}
