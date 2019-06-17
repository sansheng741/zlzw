package bean;

public class Type {
	private long tid;
	private long gid;
	private long bid;
	private long sid1;
	private long sid2;
	private long sid3;
	public long getTid() {
		return tid;
	}
	public void setTid(long tid) {
		this.tid = tid;
	}
	public long getGid() {
		return gid;
	}
	public void setGid(long gid) {
		this.gid = gid;
	}
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public long getSid1() {
		return sid1;
	}
	public void setSid1(long sid1) {
		this.sid1 = sid1;
	}
	public long getSid2() {
		return sid2;
	}
	public void setSid2(long sid2) {
		this.sid2 = sid2;
	}
	public long getSid3() {
		return sid3;
	}
	public void setSid3(long sid3) {
		this.sid3 = sid3;
	}
	@Override
	public String toString() {
		return "Type [tid=" + tid + ", gid=" + gid + ", bid=" + bid + ", sid1=" + sid1 + ", sid2=" + sid2 + ", sid3="
				+ sid3 + "]";
	}
	
	
}
