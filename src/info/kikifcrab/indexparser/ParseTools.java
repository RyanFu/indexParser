package info.kikifcrab.indexparser;

import java.io.UnsupportedEncodingException;



import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class ParseTools {

	//����ÿ����
	public void getNodes(Node node){
//		printMsg("getText:"+node.getText());				//�ı���Ϣ
//		printMsg("getPlainText:"+node.toPlainTextString());	//���ı���Ϣ
		printMsg("toHTML:"+node.toHtml());					//ԭʼHTML	
//		printMsg("toHTML(true):"+node.toHtml(true));
//		printMsg("toHTML(false):"+node.toHtml(false));
//		printMsg("toString:"+node.toString());				//�ַ�����Ϣ
//		printMsg("toPage"+node.getPage());					//ȡ�� node��Ӧ��page����
//		printMsg("getStartPosition:"+node.getStartPosition());	//��ʼλ��
//		printMsg("getEndPosition:"+node.getEndPosition());		//����λ��
		printMsg("==============================���Ƿָ���=======================");
	}
	//������
	public NodeList getFilterTag(Parser parser){
		try {
			NodeFilter filter=new TagNameFilter("img");		//���ݱ�ǩ����
			NodeFilter filter1=new HasAttributeFilter("id");	//����id����
			//ӵ��ĳ���ӽڵ�Ľڵ�
			NodeFilter innerFilter=new TagNameFilter("DIV");
			NodeFilter filter2=new HasChildFilter(innerFilter);
			//�߼�����filter
			//and����
			NodeFilter logoFilter=new AndFilter(filter,filter1);
			
			//ִ�й�����
			
			NodeList nodes=parser.extractAllNodesThatMatch(logoFilter);
			return nodes;
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//������������������������		//type:��������	//����ǩ
	public NodeList OptionalFilter(Parser parser,int type,String optional){
		NodeList nodes=null;
		NodeFilter filter=null;
		try{
			switch(type){
				case 0:					//��ȡLOGO��ǩ
					NodeFilter filter1=new TagNameFilter("img");		//���ݱ�ǩ����
					NodeFilter filter2=new HasAttributeFilter("id");	//����id����
					filter=new AndFilter(filter1,filter2);
					break;
				case 1:					//
					break;
				case 2:
					break;
				default:break;
			}
			nodes=parser.extractAllNodesThatMatch(filter);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		return nodes;
	}
	
	
	//�涨�����ӡ��Ϣ(Ĭ��GBK)
	public void printMsg(String msg){
		try {
			System.out.println(new String(msg.getBytes("GBK"),System.getProperty("file.encoding")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}
