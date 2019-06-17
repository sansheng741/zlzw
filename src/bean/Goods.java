package bean;

import java.sql.Timestamp;


public class Goods  {
private MesAndColCount macc;
	
	public MesAndColCount getMacc() {
		return macc;
	}
	public void setMacc(MesAndColCount macc) {
		this.macc = macc;
	}
	private long gid;
	private long uid;
	private String name;
	private String path;
	private double price;
	private String gimage1;
	private String gimage2;
	private String gimage3;
	private String gimage4;
	private String gimage5;
	private String gimage6;
	private Timestamp gdate;
	private String context;
	private long share;
	
	private int flag;
	
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public long getShare() {
		return share;
	}
	public void setShare(long share) {
		this.share = share;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getGimage1() {
		return gimage1;
	}
	public void setGimage1(String gimage1) {
		this.gimage1 = gimage1;
	}
	public String getGimage2() {
		return gimage2;
	}
	public void setGimage2(String gimage2) {
		this.gimage2 = gimage2;
	}
	public String getGimage3() {
		return gimage3;
	}
	public void setGimage3(String gimage3) {
		this.gimage3 = gimage3;
	}
	public String getGimage4() {
		return gimage4;
	}
	public void setGimage4(String gimage4) {
		this.gimage4 = gimage4;
	}
	public String getGimage5() {
		return gimage5;
	}
	public void setGimage5(String gimage5) {
		this.gimage5 = gimage5;
	}
	public String getGimage6() {
		return gimage6;
	}
	public void setGimage6(String gimage6) {
		this.gimage6 = gimage6;
	}
	public Timestamp getGdate() {
		return gdate;
	}
	public void setGdate(Timestamp gdate) {
		this.gdate = gdate;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	@Override
	public String toString() {
		return "Goods [gid=" + gid + ", uid=" + uid + ", name=" + name + ", path=" + path + ", price=" + price
				+ ", gimage1=" + gimage1 + ", gimage2=" + gimage2 + ", gimage3=" + gimage3 + ", gimage4=" + gimage4
				+ ", gimage5=" + gimage5 + ", gimage6=" + gimage6 + ", gdate=" + gdate + ", context=" + context
				+ ", share=" + share + "]";
	}
	
	

}
