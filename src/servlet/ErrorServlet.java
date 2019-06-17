package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ErrorServlet
 */
@WebServlet("/error")
public class ErrorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String oper=req.getParameter("oper");
    	
    	if(oper==null||"".equals(oper)){
    		resp.sendRedirect("user.s?oper=index");
    		return ;
    	}
    	
    	if("err".equals(oper)){
    		req.getRequestDispatcher("404.jsp").forward(req, resp);
    		return ;
    	}else if("five".equals(oper)){
    		req.getRequestDispatcher("500.jsp").forward(req, resp);
    		return ;
    	}
    }
}
