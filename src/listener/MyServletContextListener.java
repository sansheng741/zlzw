package listener;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyServletContextListener implements ServletContextListener {

//	@Override
//	public void contextInitialized(ServletContextEvent arg0) {
//		System.out.println("服务器启动");
//		String path1 = "D:/upload";
//		String path2 = arg0.getServletContext().getRealPath("/upload");
//		//将D:/upload中的所有文件拷贝到tomcat服务器下
//		try {
//			int result = copyFile(path1,path2);
//			if(result == 1){
//				System.out.println("文件拷贝成功");
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	

	/**
	 * 将路径1下面的文件拷贝到路径2下
	 * @param sourcePath  资源路径
	 * @param targetPath  目标路径
	 * @return 返回1 表示文件拷贝成功
	 * @throws IOException 
	 */
	private int copyFile(String sourcePath, String targetPath) throws IOException {
		File dfile = new File(sourcePath);
        String[] filePath = dfile.list();
        if(!dfile.exists()){
        	dfile.mkdir();
        }
        if (!(new File(targetPath)).exists()) {
            (new File(targetPath)).mkdir();
        }
        for(int i = 0; i < filePath.length; i++){
        	InputStream bis = new BufferedInputStream(new FileInputStream(new File(sourcePath,filePath[i])));
			OutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(targetPath,filePath[i])));
			byte[] bs = new byte[148576];
			int length = 0;
			while((length = bis.read(bs, 0, bs.length)) != -1){
				bos.write(bs, 0, bs.length);
				bos.flush();//清空缓冲区，迫使缓冲区的数据全部写出
			}
			bis.close();
			bos.close();
        }
		return 1;
	}




	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}




	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
