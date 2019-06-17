package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class VIPServlet
 */
@WebServlet("/vip")
public class VIPServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String oper=req.getParameter("oper");
    	
    	if(oper==null||"".equals(oper)){
    		resp.sendRedirect("user.s?oper=index");
    		return ;
    	}
    	
    	//µã»÷¿ªÍ¨vip ....
    	
    	if("vip".equals(oper)){
    		String uid=req.getParameter("uid");
 		   if(uid==null ||"".equals(uid)){
 			   resp.sendRedirect("user.s?oper=login");
 			   return;
 		   }
    		req.getRequestDispatcher("vip.jsp").forward(req, resp);
    		return ;
    	}
    	
    	
    	
    }

}
