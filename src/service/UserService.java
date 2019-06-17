package service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import bean.GoodDiv;
import bean.Goods;
import bean.TypeBig;
import bean.TypeSmall;
import bean.User;
import bean.UserInfo;

public interface UserService {

	List<TypeBig> findAllTypeBig();
	List<TypeSmall> findAllTypeSmall();
	List<GoodDiv> findGoodsByTypeBig();
	GoodDiv findGoodsByTypeBig(int bid);
	User findUserByAccount(String account);
	User findUserByAccountPwd(String account, String pwd);
	List<List<Object>> addUser(String email, String account, String pwd, String uname);
	List<String> findHobby(String uid);
	List<User> findUser();
	User findByEmail(String email);
	User findUserById(String uid);
	int updateInfo(UserInfo userInfo, String hobby, String uid);
	int updateHead(String uid, String uploadPath);
	int updatePwd(String pwd,String email,String account);
	String isFollow(String uid, String infouid);
	long follow(String uid, String infouid);
	int countFollow(String uid);
	int countFans(String uid);
	int countFriend(String uid);
	List<User> queryFollow(String uid);
	List<User> queryFriend(String uid);
	List<User> queryFans(String uid);
	int cancelFollow(String uid, String infouid);
	int delFriend(String uid, String infouid);
	int query(String uid);
}
