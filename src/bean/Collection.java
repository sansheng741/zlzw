package bean;

import java.sql.Timestamp;

public class Collection {

	@Override
	public String toString() {
		return "Collection [cid=" + cid + ", gid=" + gid + ", uid=" + uid + ", cdate=" + cdate + "]";
	}
	private long cid;
	private long gid;
	private long uid;
	private Timestamp cdate;
	public long getCid() {
		return cid;
	}
	public void setCid(long cid) {
		this.cid = cid;
	}
	public long getGid() {
		return gid;
	}
	public void setGid(long gid) {
		this.gid = gid;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public Timestamp getCdate() {
		return cdate;
	}
	public void setCdate(Timestamp cdate) {
		this.cdate = cdate;
	}
	
}
