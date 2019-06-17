package bean;

public class MesAndColCount {

	private long gid;
	private int  mnumber;
	private int cnumber;
	private int sum;
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public long getGid() {
		return gid;
	}
	public void setGid(long gid) {
		this.gid = gid;
	}
	@Override
	public String toString() {
		return "MesAndColCount [gid=" + gid + ", mnumber=" + mnumber + ", cnumber=" + cnumber + "]";
	}
	public int getMnumber() {
		return mnumber;
	}
	public void setMnumber(int mnumber) {
		this.mnumber = mnumber;
	}
	public int getCnumber() {
		return cnumber;
	}
	public void setCnumber(int cnumber) {
		this.cnumber = cnumber;
	}
}
