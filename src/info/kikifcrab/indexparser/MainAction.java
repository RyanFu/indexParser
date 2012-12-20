package info.kikifcrab.indexparser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;

import org.htmlparser.Attribute;
import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.Tag;
import org.htmlparser.nodes.AbstractNode;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeIterator;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;
import org.htmlparser.visitors.TextExtractingVisitor;



//对HTML文件进行解析，下载IMG对象
public class MainAction {
	
//	static final String TARGET_ADDR="http://elog.gdbnet.cn:8001/themes/mobileModule/index_10382.html";
	static final String TARGET_ADDR="http://www.jandan.net/pic";

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
			//通过URL获取在线HTML（用来做爬虫就行）在线资源
			Parser parser=new Parser((new URL(TARGET_ADDR)).openConnection());			
			//通过读取自身文件获取
//			Parser parser=new Parser(FileTools.readHTML("E:\\parserTest\\index.htm"));

			parser.setEncoding("utf-8");
			
			//通过visit容器访问
//			TextExtractingVisitor visitor=new TextExtractingVisitor();
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
			NodeList filterNodeList=ParseTools.getImgTag(parser);
			String testATTR="";

			if(filterNodeList!=null){
				for(int i=0;i<filterNodeList.size();i++){
					TagNode tmlNode=(TagNode)filterNodeList.elementAt(i);
//					tool.getNodes(tmlNode);			//打印信息					
			
					String tmlPic=tmlNode.getAttribute("src");
					System.out.println(tmlPic);
					//对图片地址进行过滤，只要那些有效的
					String fileSuffix=tmlPic.substring(tmlPic.lastIndexOf(".")+1);
					//只下载JPG文件
					if("jpg".equals(fileSuffix)||"JPG".equals(fileSuffix)||"gif".equals(fileSuffix)||"GIF".equals(fileSuffix)){
						FileTools.downloadPic(tmlPic);
					}
					//对符合条件的节点进行处理
//					tmlNode.setAttribute("src", "it's been set");
//					testATTR=tmlNode.toHtml();
//					if(tmlNode.toHtml().equals(testATTR))
//						System.out.println("true");
//					System.out.print(testATTR);			//打印信息
				}
			}
 
			//遍历节点
//			NodeList nodes=parser.parse(null);
//			tool.visitAllNode(nodes);
	
			//将新内容写入新文件
			/*
			String saveFileName="E:\\parserTest\\index_new.htm";
			String newContent="";
			FileTools.writeHTML(saveFileName,newContent);
			*/
		}catch(Exception e){
			
		}
	}
	


}
