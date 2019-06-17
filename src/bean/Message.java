package bean;

import java.sql.Timestamp;

public class Message {

	private long mid;
	private long gid;
	private long uid1;
	private long uid2;
	private int isread;
	public int getIsread() {
		return isread;
	}
	public void setIsread(int isread) {
		this.isread = isread;
	}
	private String msg;
	@Override
	public String toString() {
		return "Message [mid=" + mid + ", gid=" + gid + ", uid1=" + uid1 + ", uid2=" + uid2 + ", msg=" + msg + ", tmid="
				+ tmid + ", flag=" + flag + ", mdate=" + mdate + "]";
	}
	private long tmid;
	private int flag;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public long getTmid() {
		return tmid;
	}
	public void setTmid(long tmid) {
		this.tmid = tmid;
	}
	private Timestamp mdate;
	public long getMid() {
		return mid;
	}
	public void setMid(long mid) {
		this.mid = mid;
	}
	public long getGid() {
		return gid;
	}
	public void setGid(long gid) {
		this.gid = gid;
	}
	public long getUid1() {
		return uid1;
	}
	public void setUid1(long uid1) {
		this.uid1 = uid1;
	}
	public long getUid2() {
		return uid2;
	}
	public void setUid2(long uid2) {
		this.uid2 = uid2;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Timestamp getMdate() {
		return mdate;
	}
	public void setMdate(Timestamp mdate) {
		this.mdate = mdate;
	}
}
