package bean;

public class User {
	private long uid;
	private String account;
	private String pwd;
	private String sex;
	private String head;
	private String tel;
	private String uname;
	@Override
	public String toString() {
		return "User [uid=" + uid + ", account=" + account + ", pwd=" + pwd + ", sex=" + sex + ", head=" + head
				+ ", tel=" + tel + ", uname=" + uname + ", email=" + email + ", addr=" + addr + ", birthday=" + birthday
				+ ", sign=" + sign + ", hobby=" + hobby + ", integral=" + integral + "]";
	}
	private String email;
	private String addr;
	private String birthday;
	private String sign;
	private String hobby;
	
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	private int integral;//»ý·Ö
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
}
