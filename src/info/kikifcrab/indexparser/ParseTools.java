package info.kikifcrab.indexparser;

import java.io.UnsupportedEncodingException;
import java.util.ListIterator;



import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasChildFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.htmlparser.util.SimpleNodeIterator;

public class ParseTools {

	//����ÿ����
	public void getNodes(Node node){
		printMsg("getText:"+node.getText());				//�ı���Ϣ
		printMsg("getPlainText:"+node.toPlainTextString());	//���ı���Ϣ
		printMsg("toHTML:"+node.toHtml());					//ԭʼHTML	
		printMsg("toHTML(true):"+node.toHtml(true));
		printMsg("toHTML(false):"+node.toHtml(false));
		printMsg("toString:"+node.toString());				//�ַ�����Ϣ
		printMsg("toPage:"+node.getPage());					//ȡ�� node��Ӧ��page����
		printMsg("getStartPosition:"+node.getStartPosition());	//��ʼλ��
		printMsg("getEndPosition:"+node.getEndPosition());		//����λ��
		printMsg("==============================���Ƿָ���=======================");
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

	//�����ڵ�
	public void visitAllNode(NodeList nodes){
		SimpleNodeIterator itor=nodes.elements();
		while(itor.hasMoreNodes()){
			Node node=itor.nextNode();
			NodeList childNodes=node.getChildren();
			if(null==childNodes){

				getNodes(node);
			}else{
				//�ݹ��ȡ
				visitAllNode(childNodes);
			}
		}
	}
	

}
