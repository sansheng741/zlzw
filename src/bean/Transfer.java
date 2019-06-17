package bean;

import java.sql.Timestamp;

public class Transfer {

	private long rid;
	private long inuid;
	private long outuid;
	private Timestamp tdate;
	private int integral;
	public long getRid() {
		return rid;
	}
	public void setRid(long rid) {
		this.rid = rid;
	}
	public long getInuid() {
		return inuid;
	}
	public void setInuid(long inuid) {
		this.inuid = inuid;
	}
	public long getOutuid() {
		return outuid;
	}
	public void setOutuid(long outuid) {
		this.outuid = outuid;
	}
	public Timestamp getTdate() {
		return tdate;
	}
	public void setTdate(Timestamp tdate) {
		this.tdate = tdate;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	
}
