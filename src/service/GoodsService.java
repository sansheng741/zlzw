package service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import bean.Board;
import bean.DynamicDiv;
import bean.GoodDiv;
import bean.GoodUserDiv;
import bean.Goods;
import bean.PageBean;
import bean.TypeBig;
import bean.TypeSmall;
import service.myException.MyDBException;

public interface GoodsService {
   GoodUserDiv	findByGidUid(int gid);
   List<TypeBig> findTypeBig();
   List<TypeSmall> findTypeSmall();
   Long addGoods(Goods goods);
   List<DynamicDiv> findDynamic(HttpServletRequest req, String uid, String currentPage);
   PageBean getPageBean(Integer currentPage, String uid);
   String countUploadById(String uid);
   String countDownloadById(String uid);
   List<Goods> findGoodsByUid(String uid);
   List<Board> getBoard(String gid) throws MyDBException;
   int delFile(String gid);
   int delComment(String gid);
   int delCollection(String gid);
   List<Goods> findCollection(String uid);
   Goods findPath(String gid);
   long addBuy(String gid, String uid);
   long findIsCollection(String gid, String uid);
   long addCollection(String gid, String uid);
   List<Goods> findBuyByUid(String uid);
   String getFileSize(int gid);
}
