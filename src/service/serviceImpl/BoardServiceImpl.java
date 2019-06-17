package service.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import bean.GidAndCount;
import bean.Goods;
import bean.Information;
import bean.Message;
import bean.MessageAndUser;
import bean.User;
import dao.DBHelper;
import service.BoardService;

/**
 * 主页面显示与你相关的 信息数
 * 
 * 一个 商品的名字     商品的 第一个上传的图片
 *  别人在所参与留言商品的评论数   
 * 
 *  别人回复你的   和你回复别人的    
 * @author Administrator
 *
 */
public class BoardServiceImpl implements BoardService{

	@Override
	public String rely(String uid1, String uid2, String text,String tmid,String gid) {
		/**
		 * 用户留言功能
		 */
		String flag="";
		Timestamp time=new Timestamp(System.currentTimeMillis());
		List<List<Object>>list=DBHelper.insert("insert into message(gid,uid1,uid2,tmid,msg,mdate) value(?,?,?,?,?,?)",gid,uid1,uid2,tmid,text,time);
		if(list==null || list.size()==0){
			//留言失败
			return "null";
		}
		
		return list.get(0).get(0).toString();
	}

	@Override
	public String board(String uid1, String text, String gid) {
		String flag="";
		Timestamp time=new Timestamp(System.currentTimeMillis());
		List<List<Object>>list=DBHelper.insert("insert into message(gid,uid1,msg,mdate) value(?,?,?,?)",gid,uid1,text,time);
		if(list==null || list.size()==0){
			//留言失败
			return "null";
		}
		
		return list.get(0).get(0).toString();
	}

	@Override
	public List<Information> information(String uid) {
		/**
		 * 
		 * 第一种情况  别人在你商品下面留言     
		 * 
		 * 第二种情况 你在别人商品下面留言  另外的人回复你
		 * 
		 * 
		 * 
		 * 一个消息   一个 gdiv   里面包含  用户信息 和商品信息  还有留言数 信息
		 * 构建这个信息的同时
		 * 
		 * 一个gdiv 构建一个是商品信息和用户 名字
		 * 
		 * 展示这个商品的 所有回复你的信息 按照 时间的大小排序
		 * 
		 * @param uid
		 */
		//第一种情况  别人在你商品下面留言     
		
		// 保存 每个 div的所有信息
		List<Information>boardList=new ArrayList<Information>();
		
		//查询到自己 发布的所有 商品  别人给自己留的言 
		List<Message>list=DBHelper.select("select m.* from goods g  ,message  m "
				+ "where m.flag=1 and  g.uid=? and g.gid=m.gid and m.uid1!=? and m.uid2=0 ", Message.class, uid,uid);
		User my=DBHelper.unique("select * from user where uid =?", User.class, uid);
		
		if(list!=null && list.size()>0){
			
			for (Message message : list) {
				
				//自己发布的商品的  信息
				Goods good=DBHelper.unique("select * from goods where  gid =?", Goods.class, message.getGid());
				if(good==null){
					continue;
				}
				
				//得到   回复给你信息 人的信息
				User user1=DBHelper.unique("select * from user where uid= ?", User.class,message.getUid1());
			    if(user1 ==null ){
			    	continue;
			    }
			    message.setTmid(message.getMid());
			    Information ift=new Information();
			    ift.setIndex(message.getMid());
				ift.setGood(good);
				ift.setMessage(message);
				ift.setUser(my);
				ift.setUser1(user1);
				boardList.add(ift);
				int resule=DBHelper.update("update message set isread =0 where mid= ?", message.getMid());
				/*if(resule>0){
					//搜索成功
					boardList.add(ift);
				}*/
			}
		}
		//第二种情况 你在别人商品下面留言  另外的人回复你
		List<Message>list2= DBHelper.select("select * from message where flag = 1 and  uid2=? ORDER BY mid DESC", 
				Message.class,uid);
		
		
		if(list2!=null && list2.size()>0){
			
			//遍历回复你的人  的留言信息  去 gid  和uid   保存他们的信息  放    Information
			for (Message message : list2) {
				
				Goods good=DBHelper.unique("select * from goods where  gid =?", Goods.class, message.getGid());
				if(good==null){
					continue;
				}
				
				//得到  商品的发布人的信息
				User user=DBHelper.unique("select * from user where uid=? ", User.class,good.getUid());
				
				if(user==null){
					continue;
				}
				
				//得到   回复给你信息 人的信息
				User user1=DBHelper.unique("select * from user where uid= ?", User.class,message.getUid1());
			    if(user1 ==null ){
			    	continue;
			    }
			    
			    Information ift=new Information();
			    
			    ift.setGood(good);
			    
			    ift.setIndex(message.getMid());
			    ift.setMessage(message);
			    ift.setUser(user);
			    ift.setUser1(user1);
			    int resule=DBHelper.update("update message set isread =0 where mid= ?", message.getMid());
			    
			    boardList.add(ift);
			    
			    /*if(resule>0){
					//搜索成功
					boardList.add(ift);
				}*/
			}
		}
		
		// boardList  根据  index  降序排序
		Collections.sort(boardList, new Comparator<Information>(){
            /*
             * int compare(GidAndCount p1, GidAndCount p2) 返回一个基本类型的整型，
             * 返回负数表示：p1 小于p2，
             * 返回0 表示：p1和p2相等，
             * 返回正数表示：p1大于p2
             */
            public int compare(Information p1,Information p2) {
                //按照Person的年龄进行升序排列
                if(p1.getIndex() < p2.getIndex()){
                    return 1;
                }
                if(p1.getIndex() == p2.getIndex()){
                    return 0;
                }
                return -1;
            }
        });
		return boardList;
	}

	@Override
	public String delBoard(String mid) {
		int result =DBHelper.update("update message set flag =0 where mid =?  ", mid);
		int result1=DBHelper.update("update message set flag =0 where tmid =?  ", mid);
		result+=result1;
		if(result>0){
		    return result+"";
		}else{
			return "0";
		}
	}

	@Override
	public String delReply(String mid) {
		int result =DBHelper.update("update message set flag =0 where mid =? ",mid);
		return result+"";
	}

}
