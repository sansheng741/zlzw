package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;

import bean.DynamicDiv;
import bean.GoodDiv;
import bean.Goods;
import bean.PageBean;
import bean.TypeBig;
import bean.TypeSmall;
import bean.User;
import bean.UserInfo;
import dao.DBHelper;
import service.GoodsService;
import service.UserService;
import service.serviceImpl.GoodsServiceImpl;
import service.serviceImpl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user.s")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	private GoodsService goodsService = new GoodsServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String oper = req.getParameter("oper");
		if (oper == null || "".equals(oper)) {
			resp.sendRedirect("login.jsp");
			return;
		}
		if ("login".equals(oper)) {
			login(req, resp);
		} else if ("register".equals(oper)) {
			req.getRequestDispatcher("register.jsp").forward(req, resp);
			return;
		} else if ("index".equals(oper)) {
			index(req, resp);
		} else if ("mySrc".equals(oper)) {
			mySrc(req, resp);
		} else if ("uploadfile".equals(oper)) {
			uploadfile(req, resp);
		} else if ("myDownload".equals(oper)) {
			myDownload(req, resp);
		} else if ("favorite".equals(oper)) {
			favorite(req, resp);
		} else if ("vip".equals(oper)) {
			vip(req, resp);
		}else if ("info".equals(oper)) {
			info(req, resp);
		} else if ("loginservice".equals(oper)) {
			loginservice(req, resp);
		} else if ("regservice".equals(oper)) {
			register(req, resp);
			return ;
		}else if("quit".equals(oper)){
			clearUserCookie(req,resp);
			return ;
		}else if("reg".equals(oper)){
			req.getRequestDispatcher("register").forward(req, resp);
			return ;
		}else if("findpwd".equals(oper)){
			req.getRequestDispatcher("findpwd.jsp").forward(req, resp);
			return ;
		}else if("search".equals(oper)){
			req.getRequestDispatcher("search.jsp").forward(req, resp);
			return ;
		} else if ("quit".equals(oper)) {
			clearUserCookie(req, resp);
			return;
		} else if ("editInfo".equals(oper)) {
			editInfo(req, resp);
			return;
		} else if ("updateInfo".equals(oper)) {
			updateInfo(req, resp);
			return;
		} else if ("updateHead".equals(oper)) {
			updateHead(req, resp);
			return;
		} else if ("isFollow".equals(oper)) {
			isFollow(req, resp);
			return;
		} else if ("follow".equals(oper)) {
			follow(req, resp);
			return;
		} else if ("queryFollow".equals(oper)) {
			queryFollow(req, resp);
			return;
		} else if ("queryFans".equals(oper)) {
			queryFans(req, resp);
			return;
		}else if ("queryFriend".equals(oper)) {
			queryFriend(req, resp);
			return;
		} else if ("cancelFollow".equals(oper)) {
			cancelFollow(req, resp);
			return;
		} else if ("delFriend".equals(oper)) {
			delFriend(req, resp);
			return;
		} else if ("queryfff".equals(oper)) {
			queryfff(req, resp);
			return;
		}else if("query".equals(oper)){
			query(req,resp);
			return ;
		}else {
			resp.sendRedirect("user.s?oper=index");
			return;
		}
	}
	
	
	/**
	 * 查询 消息数目
	 * @param req
	 * @param resp
	 */
	private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid =req.getParameter("uid");
		
		if(uid==null || "".equals(uid)){
			return  ;
		}
		int number=userService.query(uid);
		resp.getWriter().write(number+"");
		return ;
	}



	/**
	 * 查询好友，关注，粉丝数
	 * ck
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void queryfff(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		if (uid == null || "".equals(uid)) {
			return;
		}
		// 查找用户的关注数(follow)，粉丝数(fans)，好友数(friend)  
		int followNum = userService.countFollow(uid);
		int fansNum = userService.countFans(uid);
		int friendNum = userService.countFriend(uid);
		resp.getWriter().append(followNum+";"+fansNum+";"+friendNum);
	}

	/**
	 * 删除好友
	 * @param req
	 * @param resp
	 */
	private void delFriend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String infouid = req.getParameter("Infouid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		int result = userService.delFriend(uid,infouid);
		if(result > 0){
			resp.getWriter().append("操作成功");
		}else{
			resp.getWriter().append("操作失败");
		}
	}
	/**
	 * 取消关注
	 * @param req
	 * @param resp
	 */
	private void cancelFollow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String infouid = req.getParameter("Infouid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		int result = userService.cancelFollow(uid,infouid);
		if(result > 0){
			resp.getWriter().append("操作成功");
		}else{
			resp.getWriter().append("操作失败");
		}
	}
	/**
	 * 查询好友用户
	 * @param req
	 * @param resp
	 */
	private void queryFriend(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		List<User> userList = userService.queryFriend(uid);		
		String userString = JSON.toJSONString(userList);
		resp.getWriter().append(userString);
		
	}
	/**
	 * 查询粉丝用户
	 * @param req
	 * @param resp
	 */
	private void queryFans(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		List<User> userList = userService.queryFans(uid);		
		String userString = JSON.toJSONString(userList);
		resp.getWriter().append(userString);
	}
	/**
	 * 查询关注的用户
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void queryFollow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		List<User> userList = userService.queryFollow(uid);		
		String userString = JSON.toJSONString(userList);
		resp.getWriter().append(userString);
	}

	/**
	 * 关注
	 * @param req
	 * @param resp
	 */
	private void follow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String infouid = req.getParameter("Infouid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		long result = userService.follow(uid, infouid);
		if(result > 0){
			resp.getWriter().append("关注成功");
		}else{
			resp.getWriter().append("服务器繁忙,请稍后再试!!!");
		}
	}
	/**
	 * 判断该用户是否被关注
	 * ck
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void isFollow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		String infouid = req.getParameter("Infouid");
		String ifs = userService.isFollow(uid,infouid);
		resp.getWriter().append(ifs);
	}

	/**
	 * 更新用户头像
	 * @param req
	 * @param resp
	 */
	private void updateHead(HttpServletRequest req, HttpServletResponse resp) {
		String uid = req.getParameter("uid");
		
		// 创建接收文件的工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 常见文件解析对象
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 设置单个文件的最大值，以字节为单位
		sfu.setFileSizeMax(1024*1024*2);
		List<FileItem> fList;
		try {
			fList = sfu.parseRequest(req);
			FileItem fi = fList.get(0);
			// 获取原始文件名
			String name = fi.getName();
			// 获得文本域的名字
			String fieldName = fi.getFieldName();
			// 是否是普通文本
			boolean formField = fi.isFormField();
			if (!formField) {
				if (fieldName != null && !"".equals(fieldName)) {
					// 生成UUID文件名，使文件名不会重复
					String uuid = UUID.randomUUID().toString();
					uuid = uuid.replaceAll("-", "");
					// 获得原始文件名的后缀名
					String suffix = name.substring(name.lastIndexOf("."));
					// 指定要上传的目录
					String uploadPath = "D:/upload";
					File files = new File(uploadPath);
					if(!files.exists()){
						files.mkdirs();
					}
					// 创建文件对象
					File file = new File(uploadPath, uuid + suffix);
					InputStream is = fi.getInputStream();
					//写入磁盘
					InputStream bis = new BufferedInputStream(is);
					OutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
					byte[] bs = new byte[1024];
					int length = 0;
					while((length = bis.read(bs, 0, bs.length)) != -1){
						bos.write(bs, 0, bs.length);
						bos.flush();//清空缓冲区，迫使缓冲区的数据全部写出
					}
					bis.close();
					bos.close();
					int result = userService.updateHead(uid,"http://localhost/" + uuid + suffix);
					if(result > 0){
						resp.getWriter().append("头像上传成功");
						User u=(User) req.getSession().getAttribute("user");
						u.setHead("http://localhost/" + uuid + suffix);
					}else{
						resp.getWriter().append("服务繁忙，请稍后重试");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			try {
				if( e instanceof FileSizeLimitExceededException){
					resp.getWriter().append("上传失败，图片过大");
				}else{
					resp.getWriter().append("服务繁忙，请稍后重试");
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 更新用户信息
	 * 
	 * @param req
	 * @param resp
	 */
	private void updateInfo(HttpServletRequest req, HttpServletResponse resp) {
		/**
		 *   用户修改个人信息
		 */
		String uid = req.getParameter("uid");
		String json = req.getParameter("json");
		UserInfo userInfo = new Gson().fromJson(json, UserInfo.class);
		String hobby = "";
		for (int i = 0; i < userInfo.getHobby().length; i++) {
			hobby += userInfo.getHobby()[i] + "+";
		}
		hobby = hobby.substring(0, hobby.lastIndexOf("+"));
		int result = userService.updateInfo(userInfo, hobby, uid);
		try {
			if (result > 0) {
				resp.getWriter().append("修改成功");
				User u=(User) req.getSession().getAttribute("user");
				u.setUname(userInfo.getUname());
				u.setSex(userInfo.getSex());
				u.setTel(userInfo.getTel());
				u.setEmail(userInfo.getEmail());
				u.setAddr(userInfo.getAddr());
				u.setSign(userInfo.getSign());
				u.setBirthday(userInfo.getBirthday());
			} else {
				resp.getWriter().append("修改失败");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void editInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 *  用户修改 个人信息页面的数据打包
		 */
		String uid = req.getParameter("uid");
		// 查找用户信息
		User user = userService.findUserById(uid);
		List<String> hobbyList = new ArrayList<String>();
		String[] hobbys = user.getHobby().split("[+]");
		for (int i = 0; i < hobbys.length; i++) {
			hobbyList.add(hobbys[i]);
		}
		req.setAttribute("hobbyList", hobbyList);
		req.getRequestDispatcher("editInfo.jsp").forward(req, resp);
	}

	private void clearUserCookie(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * 用户退出登陆功能的 数据清理
		 */
		String account = req.getParameter("account");
		if (account == null || "".equals(account)) {
			resp.sendRedirect("error?oper=err");
			return;
		}
		Cookie cookies[] = req.getCookies();
		if (cookies == null || cookies.length == 0) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		for (Cookie cookie : cookies) {
			
			if("account".equals(cookie.getName())){
				if(account.equals(cookie.getValue())){
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
					break;
				}
			}
		}
		req.getSession().setAttribute("user",null);
		
		resp.sendRedirect("user.s?oper=login");
		return;
	}

	private void index(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * 首页 数据打包
		 */
		selGoodsByTypeBig(req, resp);
		req.getRequestDispatcher("index.jsp").forward(req, resp);
		return;
	}

	private void info(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * 个人中心页面的数据打包
		 */
		String uid = req.getParameter("uid");
		String maxPage = req.getParameter("maxPage");
		String minPage = req.getParameter("minPage");
		
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		// 查找用户名和用户信息
		User user = userService.findUserById(uid);
		req.setAttribute("userInfo", user);
		// 查找用户的关注数(follow)，粉丝数(fans)，好友数(friend)  
		int followNum = userService.countFollow(uid);
		int fansNum = userService.countFans(uid);
		int friendNum = userService.countFriend(uid);
		req.setAttribute("followNum", followNum);
		req.setAttribute("fansNum", fansNum);
		req.setAttribute("friendNum", friendNum);
		// 查找爱好
		List<String> hList = findHobby(uid);
		req.setAttribute("hList", hList);
		// 查找用户的动态(发布的商品)
		// 1、获取当前页码
		String currentPage = req.getParameter("currentPage");
		List<DynamicDiv> DynamicList = goodsService.findDynamic(req, uid, currentPage);
		req.setAttribute("DynamicList", DynamicList);
		req.setAttribute("maxPage", maxPage);
		req.setAttribute("minPage", minPage);
		// 转发
		req.getRequestDispatcher("info.jsp").forward(req, resp);
		return;
	}

	private void vip(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		//查询用户上传资源数和下载资源数
		String uploadNum = goodsService.countUploadById(uid);
		String downloadNum = goodsService.countDownloadById(uid);
		req.setAttribute("uploadNum", uploadNum);
		req.setAttribute("downloadNum", downloadNum);
		
		req.getRequestDispatcher("vip.jsp").forward(req, resp);
		return;
	}
	private void favorite(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		//查询该用户的收藏商品
		List<Goods> goodsList =  goodsService.findCollection(uid);
		req.setAttribute("goodsList", goodsList);
		//查询用户上传资源数和下载资源数
		String uploadNum = goodsService.countUploadById(uid);
		String downloadNum = goodsService.countDownloadById(uid);
		req.setAttribute("uploadNum", uploadNum);
		req.setAttribute("downloadNum", downloadNum);
		req.getRequestDispatcher("favorite.jsp").forward(req, resp);
		return;
	}

	private void mySrc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		if (uid == null || "".equals(uid)) {
			req.setAttribute("msg", "请先登录后再操作~~");
			req.getRequestDispatcher("user.s?oper=index").forward(req, resp);
			return;
		}
		//查询用户上传资源数和下载资源数
		String uploadNum = goodsService.countUploadById(uid);
		String downloadNum = goodsService.countDownloadById(uid);
		req.setAttribute("uploadNum", uploadNum);
		req.setAttribute("downloadNum", downloadNum);
		
		//查询用户上传的资源
		List<Goods> goods = goodsService.findGoodsByUid(uid);
		req.setAttribute("goodsList", goods);
		req.getRequestDispatcher("mySrc.jsp").forward(req, resp);
		return;
	}

	private void myDownload(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 *  查询 用户下载资源记录
		 */
		String uid = req.getParameter("uid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		
		//查询用户上传资源数和下载资源数
		String uploadNum = goodsService.countUploadById(uid);
		String downloadNum = goodsService.countDownloadById(uid);
		req.setAttribute("uploadNum", uploadNum);
		req.setAttribute("downloadNum", downloadNum);
		
		//查询用户下载的资源
		List<Goods> goods = goodsService.findBuyByUid(uid);
		req.setAttribute("goodsList", goods);
		
		req.getRequestDispatcher("myDownload.jsp").forward(req, resp);
		return;
	}

	private void uploadfile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * 用户 上传页面的 动态数据 打包
		 */
		String uid = req.getParameter("uid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		List<TypeBig> big = userService.findAllTypeBig();
		List<TypeSmall> small = userService.findAllTypeSmall();
		req.setAttribute("big", big);
		req.setAttribute("small", small);
		req.getRequestDispatcher("uploadfile.jsp").forward(req, resp);
		return;
	}
	

	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/**
		 * 登陆
		 */
		Cookie[] cookies = req.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {

				// 从 登录页面提交的过来的 cookie信息 查看是否存在自己 以前设计好的 uid
				if ("account".equals(cookies[i].getName())) {
					// 查询到了 cookie 有的 account 信息了
					// uid 保存的是 user account
					User user = loginByCookie(cookies[i], cookies[i].getValue());
					if (user == null) {
						req.getRequestDispatcher("login.jsp").forward(req, resp);
						return;
					} else {
						// 登录成功
						user.setPwd("");
						
						req.getSession().setAttribute("user", user);//
						resp.sendRedirect("user.s?oper=index&uid=" + user.getUid());
						return;
					}

				}
			}
		}
		req.getRequestDispatcher("login.jsp").forward(req, resp);
		return;
	}

	private List<String> findHobby(String uid) {
		/**
		 *  查找爱好
		 */
		List<String> list = userService.findHobby(uid);
		return list;
	}

	private User loginByCookie(Cookie cookies, String account) {
		/**
		 * 用户通过cookie功能登陆 
		 */
		User user = userService.findUserByAccount(account);
		if (user == null) {
			// 登录失败
			cookies.clone();
			return null;
		} else {
			// 登录成功
			return user;

		}
	}

	private void loginservice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/**
		 * 用户登陆功能
		 */
		String account = req.getParameter("account");
		String pwd = req.getParameter("pwd");
		System.out.println(account + "   " + pwd);
		if (account == null || "".equals(account)) {
			// 账号为空
			req.setAttribute("msg", "账号不能为空！");
			req.getRequestDispatcher("user.s?oper=login").forward(req, resp);
			return;
		}
		if (pwd == null || "".equals(pwd)) {
			// 密码为空
			req.setAttribute("msg", "请填写密码！");
			req.getRequestDispatcher("user.s?oper=login").forward(req, resp);
			return;

		}
		User user = userService.findUserByAccountPwd(account, pwd);
		if (user == null) {
			// 登录失败
			req.setAttribute("msg", "账号或密码错误！");
			req.getRequestDispatcher("user.s?oper=login").forward(req, resp);
			return;
		} else {
			// 登录成功
			user.setPwd("");
			if ("on".equals(req.getParameter("check"))) {
				Cookie cookie = new Cookie("account", user.getAccount());
				/*
				 * cookie.setPath("user.s?oper=login"); // url uri
				 * cookie.setPath("user.s?oper=quit");
				 */
				cookie.setMaxAge(60 * 60 * 24 * 7);// 秒为单位
				resp.addCookie(cookie);
			}
			req.getSession().setAttribute("user", user);//
			resp.sendRedirect("user.s?oper=index&uid=" + user.getUid());
			return;
		}
	}

	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/**
		 * 用户的注册功能
		 */
		req.setCharacterEncoding("utf-8");
		String email = req.getParameter("email");
		String account = req.getParameter("username");
		String pwd = req.getParameter("pwd");
		String uname = req.getParameter("uname");
		String code = req.getParameter("code");
		if (email == null || "".equals(email)) {
			// 邮箱为空
			req.setAttribute("msg", "邮箱不能为空！");
			req.getRequestDispatcher("user.s?oper=register").forward(req, resp);
			return;
		}
		if (uname == null || "".equals(uname)) {
			// 名字为空
			req.setAttribute("msg", "名字不能为空！");
			req.getRequestDispatcher("user.s?oper=register").forward(req, resp);
			return;
		}
		if (account == null || "".equals(account)) {
			// 账号为空
			req.setAttribute("msg", "账号不能为空！");
			req.getRequestDispatcher("user.s?oper=register").forward(req, resp);
			return;
		}
		if (pwd == null || "".equals(pwd)) {
			// 密码为空
			req.setAttribute("msg", "密码不能为空！");
			req.getRequestDispatcher("user.s?oper=register").forward(req, resp);
			return;
		}
		String ccode = (String) req.getSession().getAttribute("checkcode");
		if (code.equalsIgnoreCase(ccode)) {
			User u=userService.findUserByAccount(account);
			if(u==null){
				List<List<Object>> user = userService.addUser(email, account, pwd, uname);
				if (user == null) {
					// 注册失败
					req.setAttribute("msg", "服务器繁忙！");
					req.getRequestDispatcher("user.s?oper=register").forward(req, resp);
					return;
				} else {
					// 注册成功
					resp.sendRedirect("user.s?oper=login");
				}
			}else{
				req.setAttribute("msg", "该用户已经存在！");
				req.getRequestDispatcher("user.s?oper=register").forward(req, resp);
				return;
			}
			
		} else {
			// 注册失败
			req.setAttribute("msg", "验证码错误！");
			req.getRequestDispatcher("user.s?oper=register").forward(req, resp);
			return;
		}

		System.out.println(code);
		System.out.println(ccode);

	}

	private void selGoodsByTypeBig(HttpServletRequest req, HttpServletResponse resp, int bid)
			throws ServletException, IOException {

		/**
		 * 查询 商品 通过 单个 bid 类型 
		 */
		GoodDiv gdiv = userService.findGoodsByTypeBig(bid);
		req.setAttribute("gdiv", gdiv);
		req.setAttribute("bid", bid + "");
	}

	private void selGoodsByTypeBig(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		/**
		 * 查询所有typebig类型的 goods
		 */
		
		List<GoodDiv> gooddiv = userService.findGoodsByTypeBig();
		req.setAttribute("gooddiv", gooddiv);
	}

}
