package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

import bean.User;
import service.UserService;
import service.serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class CheckUserServlet
 */
@WebServlet("/CheckEmailServlet")
public class CheckEmailServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    public CheckEmailServlet() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	String cemail = request.getParameter("em");
        //解码
    	cemail = URLDecoder.decode(cemail, "UTF-8");
    	
        System.out.println(cemail);
        
        User user1=userService.findByEmail(cemail);
        PrintWriter out = response.getWriter();
        if (user1==null) {
            out.print(" ");
        } else {
            out.print("该邮箱已经被注册！");
        }
        //request.getRequestDispatcher("register.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}