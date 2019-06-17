package service;

import java.util.List;

import bean.Information;

public interface BoardService {

	String rely(String uid1,String uid2,String text,String tmid,String gid);
	String board(String uid1,String text,String gid);
	
	String delBoard(String mid);
	String delReply(String tmid);
	List<Information> information(String uid);
}
