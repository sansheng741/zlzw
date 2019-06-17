package bean;

import java.util.Arrays;

public class UserInfo {
	private String uname;
	private String sex;
	private String tel;
	private String email;
	private String addr;
	private String[] hobby;
	private String sign;
	private String birthday;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "UserInfo [uname=" + uname + ", sex=" + sex + ", tel=" + tel + ", email=" + email + ", addr=" + addr
				+ ", hobby=" + Arrays.toString(hobby) + ", sign=" + sign + ", birthday=" + birthday + "]";
	}
	
	
	
	
}
