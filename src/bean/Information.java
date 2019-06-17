package bean;

import java.util.ArrayList;
import java.util.List;

public class Information {

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	private User user;
	private Goods good;
	private long index;
	private User user1;
	private Message message;

	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public long getIndex() {
		return index;
	}

	public void setIndex(long index) {
		this.index = index;
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


}
