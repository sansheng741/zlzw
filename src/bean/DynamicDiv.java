package bean;

import java.sql.Timestamp;
import java.util.List;

public class DynamicDiv {
	private long gid;
	private String uid;
	private String gimage1;
	private String gimage2;
	private String gimage3;
	private String gimage4;
	private String gimage5;
	private String gimage6;
	private List<String> imageDiv;
	private String year;
	private String month;
	private String day;
	private String context;
	private String share;
	private String comment;
	private String collection;
	
	
	
	public List<String> getImageDiv() {
		return imageDiv;
	}
	public void setImageDiv(List<String> imageDiv) {
		this.imageDiv = imageDiv;
	}
	public long getGid() {
		return gid;
	}
	public void setGid(long gid) {
		this.gid = gid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getShare() {
		return share;
	}
	public void setShare(String share) {
		this.share = share;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getCollection() {
		return collection;
	}
	public void setCollection(String collection) {
		this.collection = collection;
	}
	@Override
	public String toString() {
		return "DynamicDiv [gid=" + gid + ", uid=" + uid + ", gimage1=" + gimage1 + ", gimage2=" + gimage2
				+ ", gimage3=" + gimage3 + ", gimage4=" + gimage4 + ", gimage5=" + gimage5 + ", gimage6=" + gimage6
				+ ", imageDiv=" + imageDiv + ", year=" + year + ", month=" + month + ", day=" + day + ", context="
				+ context + ", share=" + share + ", comment=" + comment + ", collection=" + collection + "]";
	}
}
