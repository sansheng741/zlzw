package bean;

public class GidAndCount {

	private Long gid; //记录商品id
	@Override
	public String toString() {
		return "GidAndCount [gid=" + gid + ", number=" + number + "]";
	}
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	private int number; //记录查询的数目
}
