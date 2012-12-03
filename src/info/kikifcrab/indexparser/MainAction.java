package info.kikifcrab.indexparser;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;



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
		System.out.println("indexParser start");
		try{
			//读取并解析整个HTML
			Parser parser=new Parser((new URL(TARGET_ADDR)).openConnection());			
			TextExtractingVisitor visitor=new TextExtractingVisitor();
			parser.visitAllNodesWith(visitor);
			String textNodePage=visitor.getExtractedText();
			
			//打印该HTML节点内容
			System.out.println(new String(textNodePage.getBytes("GBK"),System.getProperty("file.encoding")));
		}catch(Exception e){
			
		}
	}

}
