package bean;

import java.util.ArrayList;
import java.util.List;

public class Board {
	BoardMessageUser bmu;
	List<BoardMessageUser> list;

	public Board(){
		list=new ArrayList<BoardMessageUser>();
	}
	public BoardMessageUser getBmu() {
		return bmu;
	}
	public void setBmu(BoardMessageUser bmu) {
		this.bmu = bmu;
	}
	public List<BoardMessageUser> getList() {
		return list;
	}

	public void setList(List<BoardMessageUser> list) {
		this.list = list;
	}

}
