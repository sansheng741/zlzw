package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.alibaba.fastjson.JSON;
import com.sun.corba.se.impl.orbutil.closure.Constant;

import bean.Board;
import bean.GoodUserDiv;
import bean.Goods;
import bean.Type;
import bean.TypeBig;
import bean.TypeSmall;
import dao.DBHelper;
import service.GoodsService;
import service.TypeBigService;
import service.TypeService;
import service.TypeSmallService;
import service.myException.MyDBException;
import service.serviceImpl.GoodsServiceImpl;
import service.serviceImpl.TypeBigServiceImpl;
import service.serviceImpl.TypeServiceImpl;
import service.serviceImpl.TypeSmallServiceImpl;
import sun.misc.BASE64Decoder;

/**
 * Servlet implementation class GoodsServlet
 */
@WebServlet("/goods.s")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GoodsService goodsService = new GoodsServiceImpl();
	private TypeService typeService = new TypeServiceImpl();
	private TypeBigService typeBigService = new TypeBigServiceImpl();
	private TypeSmallService typeSmallService = new TypeSmallServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String oper = req.getParameter("oper");
		if (oper == null || "".equals(oper)) {
			resp.sendRedirect("user.s?oper=index");
			return;
		}

		if ("goods".equals(oper)) {
			goods(req, resp);
		} else if ("uploadfile".equals(oper)) {
			// 查询小类型
			findTypeSmall(req, resp);
			// 查询所有大类型
			findTypeBig(req, resp);
			return;
		} else if ("upload".equals(oper)) {
			upload(req, resp);
		} else if ("delFile".equals(oper)) {
			delFile(req, resp);
		} else if ("cancelFav".equals(oper)) {
			cancelFav(req, resp);
		} else if ("download".equals(oper)) {
			download(req, resp);
		} else if("collect".equals(oper)){
			collect(req,resp);
		} else if("isCollect".equals(oper)){
			isCollect(req,resp);
		}
	}
	/**
	 * 判断用户是否收藏
	 * ck
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void isCollect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		String gid = req.getParameter("gid");
		long cid = goodsService.findIsCollection(gid,uid);
		if(cid > 0){
			resp.getWriter().append("已收藏");
		}else{
			resp.getWriter().append("未收藏");
		}
	}
	/**
	 * 收藏
	 * ck
	 * @param req
	 * @param resp
	 */
	private void collect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		String gid = req.getParameter("gid");
		long cid = goodsService.addCollection(gid,uid);
		if(cid > 0){
			resp.getWriter().append("收藏成功");
		}else{
			resp.getWriter().append("收藏失败");
		}
	}

	/**
	 * 下载
	 * ck
	 * @param req
	 * @param resp
	 */
	private void download(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uid = req.getParameter("uid");
		if (uid == null || "".equals(uid)) {
			resp.sendRedirect("user.s?oper=login");
			return;
		}
		String gid = req.getParameter("gid");
		// 从数据库中查出要下载文件
		Goods goods = goodsService.findPath(gid);
		// 下载路径
		String path = goods.getPath();
		if ("uid+gid".equals(path)) {
			req.setAttribute("msg", "该资源已被下架~~~");
			req.getRequestDispatcher("goods.s?oper=goods&gid="+gid+"&uid="+uid).forward(req, resp);
			return;
		} else {
			// 文件名
			String fileName = goods.getName();
			fileName = fileName + path.substring(path.lastIndexOf("."));
			//下载路径
			path = req.getSession().getServletContext().getRealPath(path);
			//设置已下载类型
			resp.setContentType("aplication/x-download");
			//下载路径 
			String fileDownload = path;
			//设置编码格式为UTF-8
			String filedisplay = fileName;  
			filedisplay = URLEncoder.encode(filedisplay,"UTF-8");
			//设置为以附件方式下载
			resp.addHeader("Content-Disposition", "attachment;filename="+filedisplay);
			
			InputStream is = null;
			OutputStream os = null;
			try{
				is = new FileInputStream(fileDownload);
				os = resp.getOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len = is.read(buffer)) != -1){
					os.write(buffer, 0, len);
				}
//				out.clear();
//				out = pageContext.pushBody();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(is != null){
					is.close();
				}
				if(os != null){
					os.close();
				}
				//添加用户下载信息
				long result = goodsService.addBuy(gid,uid);
			}
		}
	}

	/**
	 * 取消收藏
	 * ck
	 * @param req
	 * @param resp
	 */
	private void cancelFav(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String gid = req.getParameter("gid");
		String uid = req.getParameter("uid");
		int result = goodsService.delCollection(gid);
		if (result > 0) {
			resp.getWriter().append("取消收藏成功;" + gid);
		}
	}

	/**
	 * 删除上传文件 ck
	 * 
	 * @param req
	 * @param resp
	 */
	private void delFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String gid = req.getParameter("gid");
		String uid = req.getParameter("uid");
		// 1.删除该商品
		int result1 = goodsService.delFile(gid);
		// 2.删除该商品的评论
		int result2 = goodsService.delComment(gid);
		// 3.删除该商品的收藏
		int result3 = goodsService.delCollection(gid);
		// 4.统计用户当前的上传数
		String num = goodsService.countUploadById(uid);
		if (result1 > 0) {
			resp.getWriter().append("删除成功;" + gid + ";" + num);
		} else {
			resp.getWriter().append("删除失败");
		}
	}

	private void goods(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 点击某个商品
		try {
			int gid = Integer.parseInt(req.getParameter("gid"));
			// 根据 gid 去查询 商品的信息
			findByGid(req, resp, gid);
			String path = goodsService.getFileSize(gid);
			path = req.getSession().getServletContext().getRealPath(path);
			long size = new File(path).length()/1024;
			String fileSize = "0KB";
			if(size < 1024){
				fileSize = size + "KB";
			}else{
				fileSize = (size/1024) + "MB";
			}
			req.setAttribute("fileSize", fileSize);
			try {
				List<Board> board = goodsService.getBoard(gid + "");

				req.setAttribute("board", board);
			} catch (MyDBException e) {
				// 出现错误 直接转发
				resp.sendRedirect("error?oper=five");
				return;
			}

		} catch (NumberFormatException e) {
			resp.sendRedirect("error?oper=err");
			return;
		}
		req.getRequestDispatcher("goods.jsp").forward(req, resp);
		return;
	}

	/**
	 * ck 上传功能
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String uid = request.getParameter("uid");
		
		Goods goods = new Goods();
		Type type = new Type();

		String filetype = null; // 大类型
		String fileclass1 = null; // 小类型1
		String fileclass2 = null; // 小类型2
		String fileclass3 = null; // 小类型3

		// 创建接收文件的工厂类
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 常见文件解析对象
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 设置单个文件的最大值，以字节为单位
		sfu.setFileSizeMax(1024 * 1024 * 220);
		List<FileItem> fList;
		try {
			fList = sfu.parseRequest(request);
			for (FileItem fi : fList) {
				// 获取原始文件名
				String name = fi.getName();
				// 获得文本域的名字
				String fieldName = fi.getFieldName();
				// 是否是普通文本
				boolean formField = fi.isFormField();
				// 手动转换编码
				String content = fi.getString();
				content = new String(content.getBytes("ISO-8859-1"), "UTF-8");
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
						// 写入磁盘
						InputStream bis = new BufferedInputStream(is);
						OutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
						byte[] bs = new byte[1024*8];
						int length = 0;
						while ((length = bis.read(bs, 0, bs.length)) != -1) {
							bos.write(bs, 0, bs.length);
							bos.flush();// 清空缓冲区，迫使缓冲区的数据全部写出
						}
						bis.close();
						bos.close();
						
						String filePath = "http://localhost/" + uuid + suffix;
						if ("file".equals(fieldName)) {
							goods.setPath(filePath);
						} else if ("'pic1'".equals(fieldName)) {
							goods.setGimage1(filePath);
						} else if ("'pic2'".equals(fieldName)) {
							goods.setGimage2(filePath);
						} else if ("'pic3'".equals(fieldName)) {
							goods.setGimage3(filePath);
						} else if ("'pic4'".equals(fieldName)) {
							goods.setGimage4(filePath);
						} else if ("'pic5'".equals(fieldName)) {
							goods.setGimage5(filePath);
						} else if ("'pic6'".equals(fieldName)) {
							goods.setGimage6(filePath);
						}
					}
				} else {
					if ("srcname".equals(fieldName)) {
						goods.setName(content);
					} else if ("desc".equals(fieldName)) {
						goods.setContext(content);
					} else if ("uid".equals(fieldName)) {
						goods.setUid(Long.parseLong(content));
					} else if ("filetype".equals(fieldName)) {
						filetype = content;
					} else if ("fileclass1".equals(fieldName)) {
						fileclass1 = content;
					} else if ("fileclass2".equals(fieldName)) {
						fileclass2 = content;
					} else if ("fileclass3".equals(fieldName)) {
						fileclass3 = content;

					}
				}
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf.format(new Date());
			goods.setGdate(Timestamp.valueOf(date));

			Long result1 = goodsService.addGoods(goods);
			// 根据大类型名查找对应的bid
			long bid = typeBigService.findIdByTypeName(filetype);
			long sid1 = typeSmallService.findIdByTypeName(fileclass1);
			long sid2 = typeSmallService.findIdByTypeName(fileclass2);
			long sid3 = typeSmallService.findIdByTypeName(fileclass3);
			type.setGid(result1);
			type.setBid(bid);
			type.setSid1(sid1);
			type.setSid2(sid2);
			type.setSid3(sid3);
			Long result2 = typeService.add(type);

			if (result1 > 0 && result2 > 0) {
				response.getWriter().append("文件上传成功");
			} else {
				response.getWriter().append("服务繁忙，请稍后重试1");
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof FileSizeLimitExceededException) {
				response.getWriter().append("上传失败，文件过大");
			} else {
				response.getWriter().append("服务繁忙，请稍后重试2");
			}
		}
	}

	/**
	 * 查找小类型 ck
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void findTypeSmall(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TypeSmall> typeSmallList = goodsService.findTypeSmall();
		req.setAttribute("typeSmallList", typeSmallList);
	}

	/**
	 * 查找大类型 ck
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void findTypeBig(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<TypeBig> typeBigList = goodsService.findTypeBig();
		req.setAttribute("typeBigList", typeBigList);
		req.getRequestDispatcher("uploadfile.jsp").forward(req, resp);
	}

	private void findByGid(HttpServletRequest req, HttpServletResponse resp, int gid)
			throws ServletException, IOException {

		GoodUserDiv gUserDiv = null;
		gUserDiv = goodsService.findByGidUid(gid);

		if (gUserDiv == null) {
			resp.sendRedirect("error?oper=five");
			return;
		}
		/* System.out.println(gUserDiv.toString()); */

		req.setAttribute("good", gUserDiv);

	}
}
