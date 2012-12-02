package info.kikifcrab.indexparser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;



//移动客户端HTML文件解析与动态生成
public class MainAction {
	
	static final String TARGET_ADDR="http://elog.gdbnet.cn:8001/themes/mobileModule/index_10382.html";
	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws ParserException 
	 */
	public static void main(String[] args) throws ParserException, MalformedURLException, IOException {
		// TODO Auto-generated method stub
		System.out.print("indexParser start");
		try{
			Parser parser=new Parser((HttpURLConnection)(new URL(TARGET_ADDR)).openConnection());			
		}catch(Exception e){
			
		}
	}

}
