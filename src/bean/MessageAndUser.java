package bean;

public class MessageAndUser {

	private User user;
	private Message message;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "MessageAndUser [user=" + user + ", message=" + message + "]";
	}
	
}
