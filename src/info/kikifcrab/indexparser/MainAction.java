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



//��HTML�ļ����н���������IMG����
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
			//��ȡ����������HTML
			//ͨ��URL��ȡ����HTML��������������У�������Դ
			Parser parser=new Parser((new URL(TARGET_ADDR)).openConnection());			
			//ͨ����ȡ�����ļ���ȡ
//			Parser parser=new Parser(FileTools.readHTML("E:\\parserTest\\index.htm"));

			parser.setEncoding("utf-8");
			
			//ͨ��visit��������
//			TextExtractingVisitor visitor=new TextExtractingVisitor();
//			parser.visitAllNodesWith(visitor);
//			String textNodePage=visitor.getExtractedText();
			
			//��ӡ��HTML�ڵ�����
//			tool.printMsg(textNodePage);
/*
			for(NodeIterator i=parser.elements();i.hasMoreNodes();){
				Node node=i.nextNode();
				tool.getNodes(node);
			}
	*/		
			//��ӡ���˵Ľڵ�����
			NodeList filterNodeList=ParseTools.getImgTag(parser);
			String testATTR="";

			if(filterNodeList!=null){
				for(int i=0;i<filterNodeList.size();i++){
					TagNode tmlNode=(TagNode)filterNodeList.elementAt(i);
//					tool.getNodes(tmlNode);			//��ӡ��Ϣ					
			
					String tmlPic=tmlNode.getAttribute("src");
					System.out.println(tmlPic);
					//��ͼƬ��ַ���й��ˣ�ֻҪ��Щ��Ч��
					String fileSuffix=tmlPic.substring(tmlPic.lastIndexOf(".")+1);
					//ֻ����JPG�ļ�
					if("jpg".equals(fileSuffix)||"JPG".equals(fileSuffix)||"gif".equals(fileSuffix)||"GIF".equals(fileSuffix)){
						FileTools.downloadPic(tmlPic);
					}
					//�Է��������Ľڵ���д���
//					tmlNode.setAttribute("src", "it's been set");
//					testATTR=tmlNode.toHtml();
//					if(tmlNode.toHtml().equals(testATTR))
//						System.out.println("true");
//					System.out.print(testATTR);			//��ӡ��Ϣ
				}
			}
 
			//�����ڵ�
//			NodeList nodes=parser.parse(null);
//			tool.visitAllNode(nodes);
	
			//��������д�����ļ�
			/*
			String saveFileName="E:\\parserTest\\index_new.htm";
			String newContent="";
			FileTools.writeHTML(saveFileName,newContent);
			*/
		}catch(Exception e){
			
		}
	}
	


}
