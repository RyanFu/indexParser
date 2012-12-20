package info.kikifcrab.indexparser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

public class FileTools {

	//��ȡ�ļ�
	public static String readHTML(String fileName){			//������Ҫ������ַ
		System.out.println(fileName);
		String fileData = new String();
		try{
			File file=new File(fileName);
			BufferedReader bis=new BufferedReader(new FileReader(file));
			String tmlLine=null;
			while((tmlLine=bis.readLine())!=null){
				fileData+=tmlLine;
			}
			bis.close();
		}catch(Exception e){
			System.out.print("read error");
		}
		return fileData;
	}
		
	//�����ļ�(����ʽ)
	public static void downloadPic(String addr){
		
//		File file=new File(addr);

//		String filename=file.getName();
				
		//ȡ��׺��
//		String ext=filename.substring(filename.indexOf(".")+1);
		
		//����ʽ�����ļ�
		try {
			URL url=new URL(addr);			
			int byteSum=0;
			int byteRead=0;
			
			//ȡ���ļ�������
			String fileName=addr.substring(addr.lastIndexOf("/")+1);
			//Ĭ���ļ���
			String fileDir="D:\\testDownPic\\";
			File fileDoc=new File(fileDir);
			if(!fileDoc.exists()){
				fileDoc.mkdir();
			}
//			System.out.println("addr:"+fileDir+fileName);
			//�½����ļ�
			/*
			File theFile=new File(fileDoc+fileName);
			if(!theFile.exists()){
				fileDoc.createNewFile();
			}
			*/
			
			URLConnection conn=url.openConnection();
			InputStream inStream=conn.getInputStream();
			FileOutputStream fs=new FileOutputStream(fileDir+fileName);
			
			byte[] buffer =new byte[4096];		//�����ȡ���С
			int length;
			while((byteRead=inStream.read(buffer))!=-1){
				byteSum+=byteRead;
				fs.write(buffer, 0, byteRead);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//д���ļ�(����)
	public static void writeHTML(String fileName,String content){
		try {
			File newIndex=new File(fileName);
			if(!newIndex.exists()){
				//�������ļ�
				newIndex.createNewFile();
			}
			FileOutputStream out=new FileOutputStream(newIndex);
			out.write(content.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
}
