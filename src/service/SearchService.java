package service;

import java.util.List;

import bean.GoodDiv;
import bean.MesAndColCount;
import bean.TypeBig;
import bean.TypeSmall;
import service.myException.NumberException;

public interface SearchService {

	//根据大类型来查找 商品信息
	GoodDiv findGoodByBid(int bid,int pageNumber);
	List<TypeSmall> findAllTypeSmallFlag();
	List<TypeBig> findAllTypeBigFlag();
	
	//根据key来查找商品
	GoodDiv findGoodsByKey(String key,int pageNumber);
	    //查询全部
		GoodDiv findGoodAll(int pageNumber);
		//根据小类型来查询
		GoodDiv findGoodBySid(int sid,int pageNumber);
		//根据大类型下面的小类型来查找商品
		GoodDiv findGoodByBidSid(int bid,int sid,int pageNumber);
		//根据最新时间来查找商品
		GoodDiv findGoodByNewbest(String tbid,String tsid,int pageNumber) throws NumberException;
		//根据留言最多的来查找商品
		GoodDiv findGoodByMesCount(String tbid,String tsid) throws NumberException;
		GoodDiv findGoodByMesCount1(String tbid,String tsid,int pageNumber) throws NumberException;
		//根据收藏最多的来查找商品
		 GoodDiv findGoodByColAndMesCount22(String tbid,String tsid,int pageNumber) throws NumberException ; 
		
		GoodDiv findGoodByColAndMesCount(String tbid,String tsid) throws NumberException ;
}
