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

	//处理每个点
	public void getNodes(Node node){
//		printMsg("getText:"+node.getText());				//文本信息
//		printMsg("getPlainText:"+node.toPlainTextString());	//纯文本信息
		printMsg("toHTML:"+node.toHtml());					//原始HTML	
//		printMsg("toHTML(true):"+node.toHtml(true));
//		printMsg("toHTML(false):"+node.toHtml(false));
//		printMsg("toString:"+node.toString());				//字符串信息
//		printMsg("toPage"+node.getPage());					//取得 node对应的page对象
//		printMsg("getStartPosition:"+node.getStartPosition());	//起始位置
//		printMsg("getEndPosition:"+node.getEndPosition());		//结束位置
		printMsg("==============================我是分割线=======================");
	}
	//过滤器
	public NodeList getFilterTag(Parser parser){
		try {
			NodeFilter filter=new TagNameFilter("img");		//根据标签名称
			NodeFilter filter1=new HasAttributeFilter("id");	//包含id属性
			//拥有某种子节点的节点
			NodeFilter innerFilter=new TagNameFilter("DIV");
			NodeFilter filter2=new HasChildFilter(innerFilter);
			//逻辑运算filter
			//and运算
			NodeFilter logoFilter=new AndFilter(filter,filter1);
			
			//执行过滤器
			
			NodeList nodes=parser.extractAllNodesThatMatch(logoFilter);
			return nodes;
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//条件过滤器（带过滤条件）		//type:过滤类型	//带标签
	public NodeList OptionalFilter(Parser parser,int type,String optional){
		NodeList nodes=null;
		NodeFilter filter=null;
		try{
			switch(type){
				case 0:					//获取LOGO标签
					NodeFilter filter1=new TagNameFilter("img");		//根据标签名称
					NodeFilter filter2=new HasAttributeFilter("id");	//包含id属性
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
	
	
	//规定编码打印信息(默认GBK)
	public void printMsg(String msg){
		try {
			System.out.println(new String(msg.getBytes("GBK"),System.getProperty("file.encoding")));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}
