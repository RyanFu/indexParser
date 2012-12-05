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
	
	static final String TARGET_ADDR="http://elog.gdbnet.cn:8001/themes/mobileModule/index_10382.html";
//	static final String TARGET_ADDR="http://www.jandan.net/pic";

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
			//ͨ��URL��ȡ����HTML��������������У�
//			Parser parser=new Parser((new URL(TARGET_ADDR)).openConnection());			
			//ͨ����ȡ�����ļ���ȡ
			Parser parser=new Parser(readHTML("E:\\parserTest\\index.htm"));
			parser.setEncoding("utf-8");
			
			//ͨ��visit��������
			TextExtractingVisitor visitor=new TextExtractingVisitor();
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
/*			
			NodeList filterNodeList=tool.getFilterTag(parser);
			String testATTR="";
			if(filterNodeList!=null){
				for(int i=0;i<filterNodeList.size();i++){
					TagNode tmlNode=(TagNode)filterNodeList.elementAt(i);
					tool.getNodes(tmlNode);			//��ӡ��Ϣ					
			
					String tmlPic=tmlNode.getAttribute("src");
//					System.out.println(tmlPic);
					//�Է��������Ľڵ���д���
					tmlNode.setAttribute("src", "it's been set");
					testATTR=tmlNode.toHtml();
					if(tmlNode.toHtml().equals(testATTR))
						System.out.println("true");
//					System.out.print(testATTR);			//��ӡ��Ϣ
				}
			}
 */
			//�����ڵ�
			NodeList nodes=parser.parse(null);
			tool.visitAllNode(nodes);
	
			//��������д�����ļ�
			String saveFileName="E:\\parserTest\\index_new.htm";
			String newContent="";
			writeHTML(saveFileName,newContent);
			
		}catch(Exception e){
			
		}
	}
	//��ȡ�ļ�
	private static String readHTML(String fileName){			//������Ҫ������ַ
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
	
	//д���ļ�
	private static void writeHTML(String fileName,String content){
		File newIndex=new File(fileName);
		
	}
	
	//���ض�Ӧ��IMG����
	private static void downloadPic(String addr){
		
	}

}
