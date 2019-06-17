package bean;

import java.sql.Timestamp;

public class Follow {

	private long fid;
	private long uid1;
	private long uid2;
	private Timestamp fdate;
	public long getFid() {
		return fid;
	}
	public void setFid(long fid) {
		this.fid = fid;
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
	public Timestamp getFdate() {
		return fdate;
	}
	public void setFdate(Timestamp fdate) {
		this.fdate = fdate;
	}
}
