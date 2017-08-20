/*
 
 * Package:com.rbt.action
 * FileName: RegularlyUpdatedAction.java 
 */
package com.rbt.action;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.stereotype.Controller;

import com.rbt.common.util.FileUtil;
import com.rbt.common.util.PropertiesUtil;
import java.io.IOException;

/**
 * @function 功能  定时任务更新信息
 * @author  创建人  HXK
 * @date  创建日期  2014-09-24
 */
@Controller
public class RegularlyUpdatedAction extends AdminBaseAction {

	private static final long serialVersionUID = -2854683104492590176L;
	/*********************字段********************/
	static final String CLASS_PATH = PropertiesUtil.getClassPath();// 获取系统根目录
	private static String file_names = "quartz_job.xml";// 获取标签sql配置文件
	private static String file_path = CLASS_PATH + file_names;
	public String allXmldateString = "";
	public String redXmlDateString = "";
	public String opdate;
	public String upallXmldataString = "";
	private static String xmlnameString = "name";
	private static String xmldescriptionString = "mydescription";
	private static String xmlgroupString = "group";
	private static String xmljob_classString = "job-class";
	private static String xmljob_groupString = "job-group";
	private static String xmlcron_expressionString = "cron-expression";
	private static String xmltriggerString = "trigger";
	private static String xmljob_nameString = "job-name";
	private static String xmljobString = "job";
	private static String xmlcronString = "cron";
	FileUtil fileUtil;

	/**
	 * 方法描述：定时更新数据页面的绑定
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		readXML();
		if (allXmldateString != null && !allXmldateString.equals("")
				&& allXmldateString.length() > 4) {
			allXmldateString = allXmldateString.substring(0, allXmldateString
					.length() - 4);
		}
		if (redXmlDateString != null && !redXmlDateString.equals("")
				&& redXmlDateString.length() > 4) {
			redXmlDateString = redXmlDateString.substring(0, redXmlDateString
					.length() - 4);
		}
		return goUrl(INDEXLIST);
	}

	/**
	 * 方法描述：读取XML文件信息
	 */
	public void readXML() {
		try {
			File file = new File(file_path);// 创建文件对象
			SAXReader reader = new SAXReader();// 创建SAX阅读器
			Document doc = reader.read(file);// 读取内容生成Document对象
			Element root = doc.getRootElement();// 取得根节点
			search(root);// 开始遍历
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 递归遍历
	private void search(Element root) {
		String eNameString = "";
		String evalueString = "";
		Iterator it_element = root.elementIterator();// 将根节点下包含的元素组织成一个迭代器
		// 迭代
		while (it_element.hasNext()) {
			Element element = (Element) it_element.next();
			if (!element.getText().equals("")) {
				if (element.getName().equals(xmlnameString)) {
					eNameString += element.getName() + "=" + element.getText()
							+ "##";
				}
				if (element.getName().equals(xmldescriptionString)) {
					eNameString += element.getName() + "=" + element.getText()
							+ "##";
					evalueString += element.getText() + "##";
				}
				if (element.getName().equals(xmlgroupString)) {
					eNameString += element.getName() + "=" + element.getText()
							+ "##";
				}
				if (element.getName().equals(xmljob_classString)) {
					eNameString += element.getName() + "=" + element.getText()
							+ "##";
				}
				if (element.getName().equals(xmljob_nameString)) {
					eNameString += element.getName() + "=" + element.getText()
							+ "##";
				}
				if (element.getName().equals(xmljob_groupString)) {
					eNameString += element.getName() + "=" + element.getText()
							+ "##";
				}
				if (element.getName().equals(xmlcron_expressionString)) {
					eNameString += element.getName() + "=" + element.getText()
							+ "##";
					evalueString += element.getText() + "##";
				}
			} else {
				System.out.println(element.getName() + "==节点");
			}
			search(element);// 递归调用
		}
		if (!eNameString.equals("")) {
			allXmldateString += eNameString + "&&";
		}
		if (!evalueString.equals("")) {
			redXmlDateString += evalueString + "&&";
		}
	}

	/**
	 * AJAX定时更新
	 * 
	 * @throws Exception
	 */
	public void updatedRegularly() throws Exception {
		HttpServletRequest request = getRequest();
		HttpServletResponse response = getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String outputString = "1";
		try {
			File file = new File(file_path);// 创建文件对象
			SAXReader reader = new SAXReader();// 创建SAX阅读器
			Document doc = reader.read(file);// 读取内容生成Document对象
			/*
			 * 调用ModiXMLFiles()更新XML
			 */
			ModiXMLFiles(doc);
		} catch (Exception e) {
			outputString = "2";
		}
		out.write(outputString);
	}

	/**
	 * 方法描述：修改定时更新的xml文件
	 * 
	 * @return
	 * @throws Exception
	 */
	public void ModiXMLFiles(Document document) throws IOException {
		Element root = document.getRootElement(); // 得到根节点目录
		Iterator iter = root.elementIterator();
		String[] repvalueStrings = opdate.split("@@");
		Integer num = 0;
		while (iter.hasNext()) {
			Element titleElement = (Element) iter.next();
			// 读取xml的job元素
			if (titleElement.getName().equals(xmljobString)) {
				Iterator iter1 = titleElement.elementIterator();
				while (iter1.hasNext()) {
					Element titleElement1 = (Element) iter1.next();
					// 读取xml的trigger元素
					if (titleElement1.getName().equals(xmltriggerString)) {
						Iterator iter2 = titleElement1.elementIterator();
						while (iter2.hasNext()) {
							Element titleElement2 = (Element) iter2.next();
							// 读取xml的cron元素
							if (titleElement2.getName().equals(xmlcronString)) {
								Iterator iter3 = titleElement2.elementIterator();
								while (iter3.hasNext()) {
									Element titleElement3 = (Element) iter3.next();
									// 读取xml的cron_expression元素
									if (titleElement3.getName().equals(xmlcron_expressionString)) {
										if (repvalueStrings[num] != null&& !repvalueStrings[num].toString().equals("")) {
											titleElement3.setText(repvalueStrings[num].toString());
										}
										num = num + 1;
									}
								}
							}
						}
					}
				}
			}
		}
		// 输出全部原始数据，在编译器中显示
		OutputFormat format = OutputFormat.createPrettyPrint();
		/** 指定XML字符集编码 */
		format.setEncoding("gb2312");
		// 输出全部原始数据，并用它生成新的我们需要的XML文件
		XMLWriter writer = new XMLWriter(new FileWriter(new File(file_path)),format);
		writer.write(document); // 输出到文件
		writer.close();
	}
	public String getAllXmldateString() {
		return allXmldateString;
	}

	public void setAllXmldateString(String allXmldateString) {
		this.allXmldateString = allXmldateString;
	}

	public String getRedXmlDateString() {
		return redXmlDateString;
	}

	public void setRedXmlDateString(String redXmlDateString) {
		this.redXmlDateString = redXmlDateString;
	}

	public String getOpdate() {
		return opdate;
	}

	public void setOpdate(String opdate) {
		this.opdate = opdate;
	}

	public String getUpallXmldataString() {
		return upallXmldataString;
	}

	public void setUpallXmldataString(String upallXmldataString) {
		this.upallXmldataString = upallXmldataString;
	}

}
