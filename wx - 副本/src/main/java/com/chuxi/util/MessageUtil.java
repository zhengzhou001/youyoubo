package com.chuxi.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.chuxi.util.vo.Article;
import com.chuxi.util.vo.NewsMessage;
 

/**  
 * @Title: MessageUtil.java
 * @Description: 解析微信发送的消息请求（XML）
 * @author zhoucan
 * @date 2016年3月28日 下午5:18:16
 * @update
 * @version
 */
public class MessageUtil {

    /**
     * 解析微信发送的消息请求（XML）
     * 
     * @param request
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中
        Map<String, String> map = new HashMap<String, String>();

        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源
        inputStream.close();
        inputStream = null;
        
        return map;
    }

    /**
     * 消息对象转换为xml
     * 
     * @param obj 消息对象 
     * @return xml
     */
    public static String messageToXml(Object obj) {
    	xstream.alias("xml", obj.getClass());
    	return xstream.toXML(obj);
    }
    
    /**
     * 图文消息对象转换为xml
     * 
     * @param obj 图文消息对象 
     * @return xml
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
    	xstream.alias("xml", newsMessage.getClass());
    	xstream.alias("item", new Article().getClass());
    	return xstream.toXML(newsMessage);
    }

    /**
     * 扩展xstream，使其支持CDATA块
     * 
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记
                boolean cdata = true;

                @SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
                    super.startNode(name, clazz);
                }

                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}
