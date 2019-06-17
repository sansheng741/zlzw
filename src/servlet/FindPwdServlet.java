package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import service.UserService;
import service.serviceImpl.UserServiceImpl;
import util.Data;

/**
 * Servlet implementation class FindPwdServlet
 */
@WebServlet("/FindPwdServlet")
public class FindPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl(); 
	private static Data d=new Data();
       
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String email=req.getParameter("email");
    	String account=req.getParameter("username");
    	String code=req.getParameter("code");
    	String npwd=req.getParameter("npwd");
    	String tnpwd=req.getParameter("tnpwd");
    	System.out.println(code);
    	System.out.println(d.code);
    	if(code.equals(d.code)){
    		if(npwd.equals(tnpwd)){
    			int result=userService.updatePwd(npwd,email,account);
        		if(result>0){
        			req.setAttribute("msg", "修改成功,为你跳转到登录页面！");
        			d.code="";
        			req.getRequestDispatcher("login.jsp").forward(req, resp);
        		}else{
        			req.setAttribute("msg", "修改失败,请确认用户名是否错误！");
        			req.getRequestDispatcher("findpwd.jsp").forward(req, resp);
        		}
    		}else{
    			req.setAttribute("msg", "两次输入的密码不匹配");
        		req.getRequestDispatcher("findpwd.jsp").forward(req, resp);
    		}
    	}else{
    		req.setAttribute("msg", "验证码错误，请确认是否输入错误");
    		req.getRequestDispatcher("findpwd.jsp").forward(req, resp);
    	} 	
    }
}
