package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Information;
import service.BoardService;
import service.serviceImpl.BoardServiceImpl;

/**
 * Servlet implementation class AJAXServlet
 */
@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String oper=req.getParameter("oper");
			
			if(oper==null ||"".equals(oper)){
				return ;
			}
			String text=req.getParameter("text");
			String uid1=req.getParameter("uid1");
			String gid=req.getParameter("gid");
			BoardService board=new BoardServiceImpl();
			if("rely".equals(oper)){
				
				String uid2=req.getParameter("uid2");
				String tmid=req.getParameter("tmid");
				
				String flag=board.rely(uid1, uid2, text, tmid,gid);
				resp.getWriter().write(flag);
				
			}else if("board".equals(oper)){
				
				String flag=board.board(uid1, text, gid);
				resp.getWriter().write(flag);
				
			}else if("news".equals(oper)){
				String uid=req.getParameter("uid");
				
				if(uid!=null&&!"".equals(uid) ){
					List<Information>list=board.information(uid);
					req.setAttribute("list", list);
					System.out.println(list.toString());
				}
				
				req.getRequestDispatcher("news.jsp").forward(req, resp);
				return ;
			}else if("delboard".equals(oper)){
				
				String mid =req.getParameter("mid");
				String flag =board.delBoard(mid);
				resp.getWriter().write(flag);
				return ;
			}else if("delreply".equals(oper)){
				String mid =req.getParameter("mid");
				String flag =board.delReply(mid);
				resp.getWriter().write(flag);
				return ;
			}
			
		}

}
