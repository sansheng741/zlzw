package bean;
import java.sql.Timestamp;

public class Test {
	private int id;
	private String name;
	private Timestamp tdate;
	public String toString() {
		return "Test [id=" + id + ", name=" + name + ", tdate=" + tdate + "]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getTdate() {
		return tdate;
	}
	public void setTdate(Timestamp tdate) {
		this.tdate = tdate;
	}
	public Test(int id, String name, Timestamp tdate) {
		super();
		this.id = id;
		this.name = name;
		this.tdate = tdate;
	}
	public Test() {
		super();
	}
	

}
