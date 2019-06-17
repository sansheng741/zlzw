package service.serviceImpl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import bean.Board;
import bean.BoardMessageUser;
import bean.Collection;
import bean.CollectionCount;
import bean.DynamicDiv;
import bean.GidAndCount;
import bean.GoodDiv;
import bean.GoodUserDiv;
import bean.Goods;
import bean.ImageDiv;
import bean.MesAndColCount;
import bean.Message;
import bean.MessageCount;
import bean.PageBean;
import bean.Share;
import bean.Type;
import bean.TypeBig;
import bean.TypeSmall;
import bean.User;
import dao.DBHelper;
import service.GoodsService;
import service.myException.MyDBException;

public class GoodsServiceImpl implements GoodsService{

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
	public GoodUserDiv findByGidUid(int gid) {
		
		Goods good=DBHelper.unique("select * from goods where  gid=?",Goods.class,gid);
		GoodUserDiv gdiv=new GoodUserDiv();
		if(good==null  ){
			return null;
		}
		good.setMacc(findMesAndColCount(good.getGid()+""));
		List<Goods> list=new ArrayList<>();
		Type type=DBHelper.unique("select * from type where gid=?", Type.class, good.getGid());
		if(type!=null){
			if(type.getSid1()!=-1){
				
				List<Map<String,Object>>t= DBHelper.select("select gid from type where gid !=? and (sid1=? or sid2=? or sid3=?) " ,type.getGid(),type.getSid1(),type.getSid1(),type.getSid1());
			
				if(t!=null || t.size()>0){
					int len=t.size();
					for(int  i=0;i<len;i++){
						Goods g=DBHelper.unique("select * from goods where flag=1 and  gid =?",Goods.class, t.get(i).get("gid"));
						if(g==null){
							continue;
						}
						list.add(g);
					}
				}
			
			}
			
			if(type.getSid2()!=-1){
				List<Map<String,Object>>t=DBHelper.select("select * from type where gid !=? and (sid1=? or sid2=? or sid3=?) " ,type.getGid(),type.getSid2(),type.getSid2(),type.getSid2());
				if(t!=null || t.size()>0){
					int len=t.size();
					for(int  i=0;i<len;i++){
						Goods g=DBHelper.unique("select * from goods where flag =1 and  gid =?",Goods.class, t.get(i).get("gid"));
						if(g==null){
							continue;
						}
						boolean flag=true;
						for(Goods gg:list){
							if(gg.getGid()==g.getGid()){
								flag=false;
								break;
							}
						}
						if(flag)
						list.add(g);
					}
				}
			
			}
			
			if(type.getSid3()!=-1){
				List<Map<String,Object>>t=DBHelper.select("select * from type where gid !=? and (sid1=? or sid2=? or sid3=?) ",type.getGid(),type.getSid3(),type.getSid3(),type.getSid3());
				
				if(t!=null || t.size()>0){
					int len=t.size();
					for(int  i=0;i<len;i++){
						Goods g=DBHelper.unique("select * from goods where flag=1 and  gid =?",Goods.class, t.get(i).get("gid"));
						if(g==null){
							continue;
						}
						//遍历list集合 是否存在 相同的gid
						boolean flag=true;
						for(Goods gg:list){
							if(gg.getGid()==g.getGid()){
								flag=false;
								break;
							}
						}
						if(flag)
						list.add(g);
					}
				}
			
			}
			
			//random   list.size >6  就循环
			
			if(list!=null &&list.size()>6){
				List<Goods> tlist=new ArrayList<>();
				
				for(int i=0;i<list.size();i++){
					int lenth=list.size();
					Random r=new Random();
					int t=r.nextInt(lenth);
					tlist.add(list.get(t));
					list.remove(t);
				}
				gdiv.setList(tlist);
			}
			gdiv.setList(list);
		}
		
		
		long time=good.getGdate().getTime();
		long now=System.currentTimeMillis();
		time=now-time;
		Timestamp ti=new Timestamp(System.currentTimeMillis()); 
		
		//System.out.println(good.getGdate().getTime()+" "+ti.getTime());
		
	    long t1=time/1000;//秒
	    long t2=t1/60;//分钟
	    long t3=t2/60;//小时
	    long t4=t3/24;//天
	   
	    if(t4>0){
	    	if(t4>2){
	    		
	    		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    		String tt=sdf.format(good.getGdate());
	    		gdiv.setTime(tt);
	    	}else{
	    		gdiv.setTime(t4+"天前");
	    	}
	    }else if(t3>0){
	    	gdiv.setTime(t3+"小时前");
	    }else if(t2>0){
	    	gdiv.setTime(t2+"分钟前");
	    }else if(t1>0){
	    	gdiv.setTime("刚刚");
	    }else{
	    	gdiv.setTime("刚刚");
	    }
		gdiv.setGood(good);
		
		List<TypeBig>bigList=DBHelper.select("select b.* from typeBig b ,type t where t.gid=? and t.bid=b.bid",TypeBig.class,gid);
		
		if(bigList==null){
			return null;
		}
		
		if(bigList.size()>0)
		gdiv.setType(bigList.get(0));
		
		User user=DBHelper.unique("select * from user where uid=? ",User.class,good.getUid());
		
		if(user==null){
			return null;
		}
			gdiv.setUser(user);
		return gdiv;
	}
	/**
	 * ck
	 * 查找大类型
	 */
	@Override
	public List<TypeBig> findTypeBig() {
		List<TypeBig> select = DBHelper.select("select * from typebig", TypeBig.class);
		return select;
	}
	/**
	 * ck
	 * 查找小类型
	 */
	
	@Override
	public List<TypeSmall> findTypeSmall() {
		
		List<TypeSmall> select = DBHelper.select("select * from typesmall", TypeSmall.class);
		return select;
	}

	
	/**
	 * ck
	 * 添加商品
	 */
	@Override
	public Long addGoods(Goods goods) {
		String sql = "insert into goods (uid,gimage1,gimage2,gimage3,gimage4,gimage5,gimage6,gdate,context,name,path) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?)";
		
		/*goods.getGimage1(),goods.getGimage2(),goods.getGimage3(),goods.getGimage4()
		,goods.getGimage5(),goods.getGimage6(),goods.getPath()*/
		
//		if(goods.getGimage1()!=null){
//			goods.setGimage1(goods.getGimage1().substring(goods.getGimage1().indexOf("upload")));
//		}
//		if(goods.getGimage2()!=null){
//			goods.setGimage2(goods.getGimage2().substring(goods.getGimage2().indexOf("upload")));
//		}
//		if(goods.getGimage3()!=null){
//			goods.setGimage3(goods.getGimage3().substring(goods.getGimage3().indexOf("upload")));
//		}
//		if(goods.getGimage4()!=null){
//			goods.setGimage5(goods.getGimage5().substring(goods.getGimage5().indexOf("upload")));
//		}
//		if(goods.getGimage6()!=null){
//			goods.setGimage1(goods.getGimage6().substring(goods.getGimage6().indexOf("upload")));
//		}
//		if(goods.getPath()!=null){
//			goods.setPath(goods.getPath().substring(goods.getPath().indexOf("upload")));
//		}
		
		
		List<List<Object>> insert = DBHelper.insert(sql,goods.getUid(),goods.getGimage1(),goods.getGimage2(),goods.getGimage3(),goods.getGimage4()
				,goods.getGimage5(),goods.getGimage6(),goods.getGdate(),goods.getContext()
				,goods.getName(),goods.getPath());
		//返回gid
		return (Long) insert.get(0).get(0);
	}
	
	
	
	@Override
	public PageBean getPageBean(Integer currentPage, String uid) {
		PageBean pageBean = new PageBean();
		//设置当前页
		pageBean.setCurrentPage(currentPage);
		//获取有多少条记录
		//从数据库中查询
		List<Map<String, Object>> num = DBHelper.select("select count(*) num from goods where uid = ?", uid);
		Long count = (Long) num.get(0).get("num");
		pageBean.setTotalCount(count.intValue());
		//一页展示多少条记录
		Integer pageCount = 5;
		//总页数
		double totalPage = Math.ceil(1.0 * pageBean.getTotalCount() / pageCount);
		pageBean.setTotalPage((int)totalPage);
		//当前页查询的角标
		Integer index = (pageBean.getCurrentPage() - 1) * pageCount;
		//分页查询数据库
		String sql = "select * from goods where flag=1 and  uid = ? order by gid desc limit ?,?";
		List<Goods> goodList = DBHelper.select(sql, Goods.class, uid,index,pageCount);
		pageBean.setGoodsList(goodList);
		return pageBean;
	}
	
	/**
	 * 查找动态
	 */
	@Override
	public List<DynamicDiv> findDynamic(HttpServletRequest req, String uid, String currentPage) {
		List<DynamicDiv> dynamicList = new ArrayList<DynamicDiv>();
		//2、把页码给业务层，根据页码给我一个pageBean
		
		int pageNumber =1;
		
		try {
			
			int t=Integer.parseInt(currentPage);
			
			if(t>0){
				pageNumber=t;
			}
		} catch (NumberFormatException e) {
			
			
		}
		
		PageBean pageBean = getPageBean(pageNumber,uid);
		//3、把pageBean写到域中
//		pageBean.setTotalPage(13);
		req.setAttribute("pageBean", pageBean);
		List<Goods> goods = (List<Goods>) pageBean.getGoodsList();
		//查找商品表
		//List<Goods> goods = DBHelper.select("select * from goods where uid = ?", Goods.class, uid);
		if(goods.size() > 0){
			for(int i = 0; i < goods.size(); i++){
				
				DynamicDiv dd = new DynamicDiv();
				List<String> idList = new ArrayList<String>();
				for(int j = 1; j <= 5; j++){
					ImageDiv id = new ImageDiv();
					if(j == 1 && goods.get(i).getGimage1() != null){
						id.setGimage(goods.get(i).getGimage1());
						idList.add(id.getGimage());
					}else if(j == 2 && goods.get(i).getGimage2() != null){
						id.setGimage(goods.get(i).getGimage2());
						idList.add(id.getGimage());
					}else if(j == 3 && goods.get(i).getGimage3() != null){
						id.setGimage(goods.get(i).getGimage2());
						idList.add(id.getGimage());
					}else if(j == 4 && goods.get(i).getGimage4() != null){
						id.setGimage(goods.get(i).getGimage2());
						idList.add(id.getGimage());
					}else if(j == 5 && goods.get(i).getGimage5() != null){
						id.setGimage(goods.get(i).getGimage2());
						idList.add(id.getGimage());
					}else if(j == 6 && goods.get(i).getGimage6() != null){
						id.setGimage(goods.get(i).getGimage2());
						idList.add(id.getGimage());
					}
				}
				dd.setImageDiv(idList);
				//将时间分割
				String time = goods.get(i).getGdate().toString();
				String[] times = time.split("-");
				
				dd.setYear(times[0]);
				dd.setMonth(times[1]);
				dd.setDay(times[2].substring(0, times[2].indexOf(" ")));
				dd.setContext(goods.get(i).getContext());
				dd.setShare(goods.get(i).getShare()+"");
				dd.setUid(uid);
				dd.setGid(goods.get(i).getGid());
				dynamicList.add(dd);
			}
		}
		//查找收藏表
		List<CollectionCount> collection = DBHelper.select("select gid,count(*) count from collection c where gid in"
				+ "(select gid from goods g where g.flag=1 and g.uid = ?) GROUP BY gid order by gid desc limit ?,?", CollectionCount.class, uid,(Integer.parseInt(currentPage)-1)*5,5);
		if(collection.size() > 0 && goods.size() > 0){
			for(int i = 0; i < goods.size(); i++){
				for(int j = 0; j < collection.size(); j++){
					if(collection.get(j).getGid() == dynamicList.get(i).getGid()){
						dynamicList.get(i).setCollection(collection.get(j).getCount());
					}
				}
			}
		}
		//查找留言表
		List<MessageCount> message = DBHelper.select("select gid,count(*) count from message m where gid in" +
				"(select gid from goods g where g.flag=1 and g.uid = ?) GROUP BY gid order by gid desc limit ?,?", MessageCount.class, uid,(Integer.parseInt(currentPage)-1)*5,5);
		if(message.size() > 0 && goods.size() > 0){
			for(int i = 0; i < goods.size(); i++){
				for(int j = 0; j < message.size(); j++){
					if(message.get(j).getGid() == dynamicList.get(i).getGid()){
						dynamicList.get(i).setComment(message.get(j).getCount());
					}
				}
			}
		}
		return dynamicList;
	}
	/**
	 * 统计用户上传资源数
	 * ck
	 */
	@Override
	public String countUploadById(String uid) {
		List<Map<String, Object>> select = DBHelper.select("select count(*) num from goods where flag =1 and  uid = ?", uid);
		return select.get(0).get("num").toString();
	}
	/**
	 * 统计用户下载数
	 * ck
	 */
	@Override
	public String countDownloadById(String uid) {
		List<Map<String, Object>> select = DBHelper.select("select count(*) num from (select count(*) from buy where uid = ? group by gid) m", uid);
		return select.get(0).get("num").toString();
	}
	/**
	 * 查找用户的上传资源
	 * ck
	 */
	@Override
	public List<Goods> findGoodsByUid(String uid) {
		List<Goods> unique = DBHelper.select("select * from goods where flag =1 and  uid = ? order by gdate desc", Goods.class, uid);
		return unique;
	}
	
	public List<Board> getBoard(String gid) throws MyDBException {
		// 构造留言板
		List<Message> list=DBHelper.select("select * from message where flag=1 and  gid=? and uid2=0 ORDER BY mdate desc ", Message.class, gid);
		
		if(list==null ||list.size()==0){
			return null;
		}
		
		List<Board>board=new ArrayList<Board>();
		 
		for (Message message : list) {
			
			Board tboard=new Board();
			
			BoardMessageUser bmu=new BoardMessageUser();
			User user=DBHelper.unique("select * from user where uid=?", User.class, message.getUid1());
			if(user ==null){
				throw new MyDBException("系统繁忙");
			}
			user.setPwd("");
			bmu.setMessage(message);
			bmu.setUser1(user);
			tboard.setBmu(bmu);
			
			List<Message>t = DBHelper.select("select * from message where flag=1 and gid=? and tmid=? ORDER BY mdate asc", Message.class,message.getGid(),message.getMid() );
			
			if(list==null || list.size()==0){
				continue;
			}
			
			for (Message message2 : t) {
				BoardMessageUser tbmu=new BoardMessageUser();
				
				User user1=DBHelper.unique("select * from user where uid=?", User.class, message2.getUid1());
				if(user1 ==null){
					throw new MyDBException("系统繁忙");
				}
				User user2=DBHelper.unique("select * from user where uid=?", User.class, message2.getUid2());
				if(user2 ==null){
					throw new MyDBException("系统繁忙");
				}
				user1.setPwd("");
				user2.setPwd("");
				tbmu.setMessage(message2);
				tbmu.setUser1(user1);
				tbmu.setUser2(user2);
				tboard.getList().add(tbmu);
			}
			board.add(tboard);
			
		}
		return board;
	}
	/**
	 * 删除商品
	 * ck
	 */
	@Override
	public int delFile(String gid) {
		int update = DBHelper.update("update goods set flag=0 where gid =? ", gid);
		return update;
	}
	/**
	 * 删除评论
	 * ck
	 */
	@Override
	public int delComment(String gid) {
		int update = DBHelper.update("delete from message where gid = ?", gid);
		return update;
	}
	/**
	 * 删除收藏
	 * ck
	 */
	@Override
	public int delCollection(String gid) {
		int update = DBHelper.update("delete from collection where gid = ?", gid);
		return update;
	}
	
	/**
	 * 查询用户收藏
	 * ck
	 */
	@Override
	public List<Goods> findCollection(String uid) {
		List<Goods> select = DBHelper.select("select * from goods where  gid in (select gid from collection where uid = ? order by cdate desc)", Goods.class ,uid);
		return select;
	}
	/**
	 * 查询商品路径
	 */
	@Override
	public Goods findPath(String gid) {
		List<Goods> select = DBHelper.select("select * from goods where gid = ?",Goods.class,gid);
		return select.get(0);
	}
	/**
	 * 添加用户购买
	 * ck
	 */
	@Override
	public long addBuy(String gid, String uid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		List<List<Object>> insert = DBHelper.insert("insert into buy(gid,uid,mdate) values(?,?,?)", gid,uid,Timestamp.valueOf(time));
		if(insert.size() > 0){
			return (long) insert.get(0).get(0);
		}
		return 0;
	}
	/**
	 * 判断用户是否收藏
	 * ck
	 */
	@Override
	public long findIsCollection(String gid, String uid) {
		List<Map<String, Object>> select = DBHelper.select("select * from collection where gid = ? and uid = ?", gid,uid);
		if(select.size() > 0){
			return Long.parseLong(select.get(0).get("gid").toString());
		}
		return 0;
	}
	/**
	 * 添加收藏
	 * ck
	 */
	@Override
	public long addCollection(String gid, String uid) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = sdf.format(new Date());
		List<List<Object>> insert = DBHelper.insert("insert into collection(gid,uid,cdate) values(?,?,?)", gid,uid,Timestamp.valueOf(time));
		if(insert.size() > 0){
			return (long) insert.get(0).get(0);
		}
		return 0;
	}
	/**
	 * 查找用户下载资源
	 */
	@Override
	public List<Goods> findBuyByUid(String uid) {
		List<Goods> select = DBHelper.select("select * from goods where gid in (select gid from buy where uid = ? order by mdate desc)", Goods.class, uid);
		return select;
	}

	@Override
	public String getFileSize(int gid) {
		List<Map<String, Object>> select = DBHelper.select("select * from goods where gid = ?", gid);
		String filepath = select.get(0).get("path").toString();
		return filepath;
	}

}
