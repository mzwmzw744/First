package cn.com.servlet.http;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;

abstract class LoadConfig {
	private static Map<String, String> map;
	private LoadConfig(){
		
	}
	static{
		map = new HashMap<String, String>();
		load();
	}
	
	public static Map<String, String> getConfig(){
		return map;
	}
	static void load(){
		SAXBuilder buil = new SAXBuilder();
		try {
			Document doc = buil.build(new FileReader(new File("WEB-INF/web.xml")));
			Element root = doc.getRootElement();
			XPath servletsPath = XPath.newInstance("servlet");
			XPath servletMappingsPath = XPath.newInstance("servlet-mapping");
			List<Element> servlets = servletsPath.selectNodes(root);
			List<Element> servletMappings = servletMappingsPath.selectNodes(root);
			for(Element mapping : servletMappings){
				String sn = mapping.getChildText("servlet-name");
				String up = mapping.getChildText("url-pattern");
				for(Element servlet : servlets){
					String ssn = servlet.getChildText("servlet-name");
					String ssc = servlet.getChildText("servlet-class");
					if(sn.equals(ssn)){
						map.put(up,ssc);
						break;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
