package service.serviceImpl;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.GidAndCount;
import bean.GoodDiv;
import bean.Goods;
import bean.MesAndColCount;
import bean.Message;
import bean.TypeBig;
import bean.TypeSmall;
import bean.User;
import bean.UserInfo;
import dao.DBHelper;
import service.UserService;

public class UserServiceImpl implements UserService{

	
	
	@Override
	public List<GoodDiv> findGoodsByTypeBig() {
		
		 List<TypeBig>bigList=DBHelper.select("select * from typebig where flag=1",TypeBig.class);
		
		 if(bigList==null||bigList.size()==0){
			 //没有   
			 return null;
		 }
		 List<GoodDiv>list=new ArrayList<GoodDiv>();
		 for(int i=0;i<bigList.size();i++){
			 
			 List<Goods>t=DBHelper.select("select g.* from goods g,type t where g.flag=1 and  g.gid=t.gid and t.bid=? ",Goods.class,bigList.get(i).getBid());
			 if(t==null){
				 continue;
			 }
			 
			 if(bigList.get(i).getBid()!=1){
				 for(int j=12;j<t.size();j++){
					 t.remove(j);
					 j--;
				 }
				 
				 if(t.size()>=4){
					 t.add(t.get(0));
					 t.add(t.get(1));
					 t.add(t.get(2));
					 t.add(t.get(3));
				 }
				 
			 }else {
				 for(int j=7;j<t.size();j++){
					 t.remove(j);
					 j--;
				 }
			 }
			 
			 for (Goods goods : t) {
		        	goods.setMacc(findMesAndColCount(goods.getGid()+""));
				}
			 GoodDiv gdiv=new GoodDiv();
			 gdiv.setList(t);
			 gdiv.setType(bigList.get(i).getType());
			 gdiv.setBid(bigList.get(i).getBid());
			 list.add(gdiv);
		 }
		return list;
	}

	public GoodDiv findGoodsByTypeBig(int bid) {
		
			 List<Goods>t=DBHelper.select
					 ("select g.* from goods g,type t where g.flag=1 and g.gid=t.gid and t.bid=? ",Goods.class,bid);
			 
			 if(t==null ){
				 return null;
			 }
			 for (Goods goods :t) {
		        	goods.setMacc(findMesAndColCount(goods.getGid()+""));
				}
			 
			 GoodDiv gdiv=new GoodDiv();
			 gdiv.setList(t);
			 gdiv.setType("");
			 gdiv.setBid(bid);
		return gdiv;
	}

	
	
	public List<TypeBig> findAllTypeBig() {
		
		List<TypeBig>list=DBHelper.select("select * from typebig", TypeBig.class);
		
		if(list==null || list.size()==0){
			return null;
		}
		return list;
	}

	public List<TypeSmall> findAllTypeSmall() {
		
		List<TypeSmall> list = DBHelper.select("select * from typesmall", TypeSmall.class);
		if(list==null || list.size()==0){
			return null;
		}
		return list;
	}
	public User findUserByAccountPwd(String account,String pwd) {
		return DBHelper.unique("select * from user where account=? and pwd=?", User.class,account,pwd);
		 
	}
	public User findUserByAccount(String account) {
		
		return DBHelper.unique("select * from user where account=?",User.class, account);
	}
	public User findByEmail(String email) {
		return DBHelper.unique("select * from user where email=?", User.class,email);
	}
	
	public List<List<Object>> addUser(String email, String account, String pwd ,String uname){
		return DBHelper.insert("insert into user(email,account,pwd,uname) values (?,?,?,?)",email,account,pwd,uname);
	}
	public List<User> findUser() {
		
		List<User>list=DBHelper.select("select * from user", User.class);
		if(list==null || list.size()==0){
			return null;
		}
		return list;
	}
	/**
	 * 查找爱好，并返回list集合 
	 * ck
	 */
	@Override
	public List<String> findHobby(String uid) {
		
		List<String> hList = new ArrayList<String>();
		List<Map<String, Object>> select = DBHelper.select("select * from user where uid = ?", uid);
		String[] hobbys = ((String)select.get(0).get("hobby")).split("[+]");
		if(hobbys.length > 0){
			for(int i = 0; i < hobbys.length; i++){
				hList.add(hobbys[i]);
			}
		}
		
		
		return hList;
	}
	
	public MesAndColCount findMesAndColCount(String gid) {
		
		MesAndColCount macc=new MesAndColCount();
		
		List<GidAndCount>clist=DBHelper.select("SELECT c.gid, count(c.gid) number from collection c where c.gid=? "
				+ "GROUP BY c.gid ORDER BY COUNT(c.gid) DESC ;", GidAndCount.class,gid);
		if(clist==null || clist.size()==0){
			macc.setCnumber(0);
		}else{
			macc.setCnumber(clist.get(0).getNumber());
		}
		
		List<GidAndCount>mlist=DBHelper.select("SELECT m.gid, count(m.gid) number from message m where m.flag=1 and  m.gid=? "
				+ "GROUP BY m.gid ORDER BY COUNT(m.gid) DESC ;", GidAndCount.class,gid);
		if(mlist==null || mlist.size()==0){
			macc.setMnumber(0);
		}else{
			macc.setMnumber(mlist.get(0).getNumber());
		}
		macc.setSum(macc.getCnumber()+macc.getMnumber());
		return macc;
	}

	@Override
	public User findUserById(String uid) {
		User select = DBHelper.unique("select * from user where uid = ?", User.class, uid);
		return select;
	}
	/**
	 * 修改用户信息
	 */
	@Override
	public int updateInfo(UserInfo userInfo, String hobby, String uid) {
		//对用户信息判断
		int result = DBHelper.update("update user set uname = ?,sex = ?,tel = ?,email = ?,"
				+ "addr = ?,hobby = ?, sign = ?, birthday = ? where uid = ?", 
		userInfo.getUname(),userInfo.getSex(),userInfo.getTel(),userInfo.getEmail()
		,userInfo.getAddr(),hobby,userInfo.getSign(),userInfo.getBirthday(),uid);
		return result;
	}

	@Override
	public int updateHead(String uid, String uploadPath) {
		int result = DBHelper.update("update user set head = ? where uid = ?", uploadPath,uid);
		return result;
	}
	
	public int updatePwd(String pwd,String email,String account){
		return DBHelper.update("update user set pwd=? where email=? and account=?",pwd,email,account);
	}
	
	/**
	 * 判断用户是否被关注
	 * ck
	 */
	@Override
	public String isFollow(String uid, String infouid) {
		List<Map<String, Object>> select = DBHelper.select("select * from follow where uid1 = ? and uid2 = ?", uid,infouid);
		if(select.size() > 0){
			return "true";
		}
		return "false";
	}
	/**
	 * 关注用户
	 * ck
	 */
	@Override
	public long follow(String uid, String infouid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		List<List<Object>> insert = DBHelper.insert("insert into follow(uid1,uid2,fdate) values(?,?,?)", uid,infouid,Timestamp.valueOf(time));
		long fid = Long.parseLong(insert.get(0).get(0).toString());
		if(fid > 0){
			return fid;
		}
		return -1;
	}
	/**
	 * 统计关注数
	 * ck
	 */
	@Override
	public int countFollow(String uid) {
		String sql = "select count(*) num from follow where uid1 = ? and uid2 not in( " +
				"select * from (select uid1 from follow where uid2 = ?) m " +
				"where uid1 in (select uid2 from follow where uid1 = ?))";
		List<Map<String, Object>> select = DBHelper.select(sql, uid,uid,uid);
		return Integer.parseInt(select.get(0).get("num").toString());
	}
	/**
	 * 统计粉丝数
	 * ck
	 */
	@Override
	public int countFans(String uid) {
		String sql = "select count(*) num from follow where uid2 = ? and uid1 not in( " +
				"select * from (select uid1 from follow where uid2 = ?) m " +
				"where uid1 in (select uid2 from follow where uid1 = ?))";
		List<Map<String, Object>> select = DBHelper.select(sql, uid,uid,uid);
		return Integer.parseInt(select.get(0).get("num").toString());
	}
	/**
	 * 统计好友数
	 * ck
	 */
	@Override
	public int countFriend(String uid) {
		List<Map<String, Object>> select = DBHelper.select("select count(*) num from (select uid1 from follow where uid2 = ?) m " +
				"where uid1 in (select uid2 from follow where uid1 = ?)", uid,uid);
		return Integer.parseInt(select.get(0).get("num").toString());
	}
	
	/**
	 * 查找关注用户
	 * ck
	 */
	@Override
	public List<User> queryFollow(String uid) {
		String sql = "select * from user where uid in (select uid2 from follow where uid1 = ? "
				+ "and uid2 not in("
				+"select * from (select uid1 from follow where uid2 = ?) m "
   				+"where uid1 in (select uid2 from follow where uid1 = ?)))";
		List<User> select = DBHelper.select(sql, User.class, uid,uid,uid);
		return select;
	}
	/**
	 * 查找好友用户
	 * ck
	 */
	@Override
	public List<User> queryFriend(String uid) {
		String sql = "select * from user where uid in " +
				"(select * from (select uid1 from follow where uid2 = ?) m " +
				"where uid1 in (select uid2 from follow where uid1 = ?))";
		List<User> select = DBHelper.select(sql, User.class, uid,uid);
		return select;
	}
	/**
	 * 查找粉丝用户
	 * ck
	 */
	@Override
	public List<User> queryFans(String uid) {
		String sql = "select * from user where uid in (select uid1 from follow where uid2 = ? "+
				 "and uid1 not in( " +
				 "select * from (select uid1 from follow where uid2 = ?) m " +
				 "where uid1 in (select uid2 from follow where uid1 = ?)))";
		List<User> select = DBHelper.select(sql, User.class, uid,uid,uid);
		return select;
	}
	/**
	 * 取消关注
	 * ck
	 */
	@Override
	public int cancelFollow(String uid, String infouid) {
		String sql = "delete from follow where uid1 = ? and uid2 = ?";
		int update = DBHelper.update(sql,uid,infouid);
		return update;
	}
	/**
	 * 删除好友
	 * ck
	 */
	@Override
	public int delFriend(String uid, String infouid) {
		String sql = "delete from follow where uid1 = ? and uid2 = ?";
		int update1 = DBHelper.update(sql,uid,infouid);
		int update2 = DBHelper.update(sql,infouid,uid);
		if(update1 > 0 && update2 > 0){
			return 1;
		}
		return 0;
	}

	@Override
	public int query(String uid) {
		/**
		 * 查询 未读消息数
		 */
		int number =0;
		// 我商品下面的 所有留言
		List<Message>list=DBHelper.select("select m.* from goods g,message m where  m.isread=1 and  g.uid=? and  g.gid=m.gid and m.flag=1 and m.uid1!=? and m.uid2=0 ",Message.class ,uid,uid);
		// 任意商品下面的  与我有关的  回复
		List<Message>list1=DBHelper.select("select * from message where flag =1 and isread=1 and  uid1!=? and uid2 =?   ", Message.class,uid ,uid);
		
		if(list==null || list.size()==0){
			
		}else{
			number+=list.size();
		}
		if(list1==null || list1.size()==0){
			
		}else{
			number+=list1.size();
		}
		return number;
	}
}
