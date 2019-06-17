package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.GoodDiv;
import bean.TypeBig;
import bean.TypeSmall;
import service.SearchService;
import service.UserService;
import service.myException.NumberException;
import service.serviceImpl.SearchServiceImpl;
import service.serviceImpl.UserServiceImpl;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	SearchService search=new SearchServiceImpl();
        UserService userService=new UserServiceImpl();
    	String tbid=req.getParameter("bid");
    	String tsid=req.getParameter("sid");
    	String max=req.getParameter("pageMax");
    	int pageMax=6;//默认最大值为6    记录页面出现的最大值  要转发出去 的 
         // 当前 页数
         int pageNumber=1;
         if(max!=null || !"".equals(max)){
     		try {
     			//得到最大 页数
     			int tmax=Integer.parseInt(max);
     			
     			if(tmax>pageMax){
     				pageMax=tmax;
     			}
     		} catch (NumberFormatException e1) {
     			
     		}
     	}
    	//当前页
    	String currentPage=req.getParameter("currentpage");
    	//共有多少页
    	if(currentPage==null || "".equals(currentPage)){
    		
    	}else{
    		try {
				pageNumber=Integer.parseInt(currentPage);
				
				if(pageNumber<=0){
					pageNumber=1;
				}
				//  如果要查询的 页数  大于当前页数时候   改变最大页数的值
				if(pageMax<pageNumber){
					pageMax=pageNumber;
				}
				
			} catch (NumberFormatException e) {
				//出错的话 默认给它 第一页的内容
				pageNumber=1;
			}
    	}
    	
    	// tmax为最大值
    	
    	//多少条记录
    	String totalCount=req.getParameter("totalcount");
    	int sid=0;
    	int bid=0;
    	 // 查找  用户 已经上传的商品的类型
		   // 查询  大类型  和 小类型
		List<TypeBig> big=search.findAllTypeBigFlag();
	    List<TypeSmall>small=search.findAllTypeSmallFlag();
	    req.setAttribute("big",big);
	    req.setAttribute("small",small);
		String oper=req.getParameter("oper");
		
		if(oper==null || "".equals(oper)){
			if(tbid==null ||"".equals(tbid)){
	    		
	    		if(tsid==null || "".equals(tsid)){
	    			// 搜索所有商品
	    			GoodDiv gdiv=search.findGoodAll(pageNumber);
	    			//判断用户传过来的 tmax  是否比总  页数大
	    			
	    			// 不考虑 当前页 大于 总页数的情况
	    			gdiv=changeGoodDiv(gdiv,pageMax,pageNumber);
	    			req.setAttribute("gdiv", gdiv);
	    			req.getRequestDispatcher("course.jsp").forward(req, resp);
	    			return ;
	    		}
	    		try {
					sid=Integer.parseInt(tsid);
				} catch (NumberFormatException e) {
					// 出现解析异常
					resp.sendRedirect("error?oper=err");
					return ;
				}
	    		//根据sid 搜素商品
	    		GoodDiv gdiv=search.findGoodBySid(sid,pageNumber);
	    		gdiv=changeGoodDiv(gdiv,pageMax,pageNumber);
	    		req.setAttribute("gdiv", gdiv);
	    		req.setAttribute("sid", sid);
				req.getRequestDispatcher("course.jsp").forward(req, resp);
	    		return ;
	    		
	    	}else{
	    		try {
					bid=Integer.parseInt(tbid);
				} catch (NumberFormatException e) {
					// 出现解析异常
					resp.sendRedirect("error?oper=err");
					return ;
				}
	    		
	    		if(tsid==null || "".equals(tsid)){
	    			// 搜索bid商品
	    			GoodDiv gdiv=search.findGoodByBid(bid,pageNumber);
	    			
	    			gdiv=changeGoodDiv(gdiv,pageMax,pageNumber);
	    			req.setAttribute("gdiv", gdiv);
	    			req.setAttribute("bid", bid);
	    			req.getRequestDispatcher("course.jsp").forward(req, resp);
	    			return ;
	    		}
	    		
	    		try {
					sid=Integer.parseInt(tsid);
				} catch (NumberFormatException e) {
					//出现解析异常
					resp.sendRedirect("error?oper=err");
					return ;
				}
	    		// 搜素 bid下面  sid 商品
	    		
	    		GoodDiv gdiv=search.findGoodByBidSid(bid, sid,pageNumber);
	    		gdiv=changeGoodDiv(gdiv,pageMax,pageNumber);
	    		
	    		req.setAttribute("gdiv", gdiv);
	    		req.setAttribute("bid", bid);
	    		req.setAttribute("sid", sid);
	    		
				req.getRequestDispatcher("course.jsp").forward(req, resp);
				return ;
	    	}
		}else{
			
			if("search".equals(oper)){
				
				String key=req.getParameter("key");
				
				if(key==null || "".equals(key)){
					//返回所有的商品
					GoodDiv gdiv=search.findGoodAll(pageNumber);
					gdiv=changeGoodDiv(gdiv,pageMax,pageNumber);
	    			req.setAttribute("gdiv", gdiv);
	    			req.getRequestDispatcher("search.jsp").forward(req, resp);
					return ;
				}
				key=new String (key.getBytes("ISO-8859-1"),"utf-8").toString();
				GoodDiv gdiv=search.findGoodsByKey(key,pageNumber);
				gdiv=changeGoodDiv(gdiv,pageMax,pageNumber);
				req.setAttribute("gdiv", gdiv);
				req.setAttribute("key", key);
    			req.getRequestDispatcher("search.jsp").forward(req, resp);
				return ;
			}
			
			GoodDiv gdiv=null;
			 if("comprehensive".equals(oper)){
				   //收藏数和留言数
				try {
					gdiv = search.findGoodByColAndMesCount22(tbid,tsid,pageNumber);
				} catch (NumberException e) {
					//出现 数字解析错误
					resp.sendRedirect("error?oper=err");
					return ;
				}
				if(gdiv!=null)
				   gdiv.setType(oper);
				
				    gdiv=changeGoodDiv(gdiv,pageMax,pageNumber);
					req.setAttribute("bid", tbid);
		    		req.setAttribute("sid", tsid);
				   req.setAttribute("gdiv", gdiv);
	   			   req.getRequestDispatcher("course.jsp").forward(req, resp);
				   return ;
				   
			   }else if("fire".equals(oper)){
				   // 最火的 留言最多的
				   
				try {
					gdiv = search.findGoodByMesCount1(tbid,tsid,pageNumber);
				} catch (NumberException e) {
					//出现 数字解析错误
					resp.sendRedirect("error?oper=err");
					return ;
				}
				   if(gdiv!=null)
				   gdiv.setType(oper);
				   
				   gdiv=changeGoodDiv(gdiv,pageMax,pageNumber);
				   req.setAttribute("bid", tbid);
		    		req.setAttribute("sid", tsid);
				   req.setAttribute("gdiv", gdiv);
	   			   req.getRequestDispatcher("course.jsp").forward(req, resp);
				   return ;
			   }else if("newbest".equals(oper)){
				   
				   //最新的
				   try {
					gdiv=search.findGoodByNewbest(tbid,tsid,pageNumber);
				} catch (NumberException e) {
					//出现 数字解析错误
					resp.sendRedirect("error?oper=err");
					return ;
				}
				   if(gdiv!=null)
				   gdiv.setType(oper);
				   
				   gdiv=changeGoodDiv(gdiv,pageMax,pageNumber);
				   req.setAttribute("bid", tbid);
		    		req.setAttribute("sid", tsid);
				   req.setAttribute("gdiv", gdiv);
	   			   req.getRequestDispatcher("course.jsp").forward(req, resp);
				   return  ;
			   }
		}
    }

	private GoodDiv changeGoodDiv(GoodDiv gdiv, int pageMax,int pageNumber) {
		
		if(gdiv!=null){
					//正常的相差
				if((pageMax-pageNumber)==6){
					pageMax--;// 3 8  2 7
					gdiv.setPageSize(pageMax);
				}else if((pageMax-pageNumber)>5){
					gdiv=null;
				}else{
					if(gdiv.getTotal()<=6){
						
						gdiv.setPageSize((int) gdiv.getTotal());
					}else{
						gdiv.setPageSize(pageMax);
					}
				}
				
					gdiv.setPageMax(pageMax);
			return gdiv;
			
		}else {
			return null;
		}
	}
}
