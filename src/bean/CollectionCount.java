package bean;

public class CollectionCount {
	private long gid;
	private String count;
	public long getGid() {
		return gid;
	}
	public void setGid(long gid) {
		this.gid = gid;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "CollectionCount [gid=" + gid + ", count=" + count + "]";
	}
	
	
}
