package info.kikifcrab.indexparser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.htmlparser.Attribute;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.nodes.AbstractNode;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
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
			ParseTools tool=new ParseTools();
			//读取并解析整个HTML
			//通过URL获取在线HTML（用来做爬虫就行）
//			Parser parser=new Parser((new URL(TARGET_ADDR)).openConnection());			
			//通过读取自身文件获取
			Parser parser=new Parser(readHTML("E:\\parserTest\\index.htm"));
			
			
			TextExtractingVisitor visitor=new TextExtractingVisitor();
//			parser.visitAllNodesWith(visitor);
//			String textNodePage=visitor.getExtractedText();
			
			//打印该HTML节点内容
//			tool.printMsg(textNodePage);
/*
			for(NodeIterator i=parser.elements();i.hasMoreNodes();){
				Node node=i.nextNode();
				tool.getNodes(node);
			}
	*/		
			//打印过滤的节点内容
			NodeList filterNodeList=tool.getFilterTag(parser);
			String testATTR="";
			if(filterNodeList!=null){
				for(int i=0;i<filterNodeList.size();i++){
					TagNode tmlNode=(TagNode)filterNodeList.elementAt(i);
					tool.getNodes(tmlNode);			//打印信息					
			
					String tmlPic=tmlNode.getAttribute("src");
//					System.out.println(tmlPic);
					//对符合条件的节点进行处理
					tmlNode.setAttribute("src", "it's been set");
					testATTR=tmlNode.toHtml();
					if(tmlNode.toHtml().equals(testATTR))
						System.out.println("true");
//					parser.reset();
//					System.out.print(testATTR);			//打印信息
				}
			}
 
			//将新内容写入新文件
			String saveFileName="E:\\parserTest\\index_new.htm";
			String newContent="";
			writeHTML(saveFileName,newContent);
			
		}catch(Exception e){
			
		}
	}
	//读取文件
	private static String readHTML(String fileName){			//名称中要包含地址
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
	
	//写入文件
	private static void writeHTML(String fileName,String content){
		File newIndex=new File(fileName);
		
	}

}
