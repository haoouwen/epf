<%@ page language="java" contentType="text/html; charset=gbk"
 import="java.io.File, java.util.Hashtable, java.util.Locale,java.net.URLEncoder,com.rbt.common.util.PropertiesUtil"
 pageEncoding="GBK"%>
<%!
//文件名
private static final String VIEWER = "viewer.jsp";
//路径与URL的自动转换,能够将服务器中的文件路径转化为url路径
//如果不使用此功能,即设置USE_PATHTOURL=false,系统将用读取的方式访问此文件,而不是url
private static final boolean USE_PATHTOURL=true;
//private static final boolean USE_PATHTOURL=false;
//url地址可以由系统自动生成,也可手动配置,
//如果AUTOSET_PATHTOURL=true,系统自动设置.AUTOSET_PATHTOURL=false,要手动输入HOME_URL和HOME_PATH两个参数
private static final boolean AUTOSET_PATHTOURL=true;
//private static final boolean AUTOSET_PATHTOURL=false;
//HOME_URL,HOME_PATH需要在USE_PATHTOURL=true和AUTOSET_PATHTOURL=false的情况下才生效
//无论任何情况都不要注释掉HOME_URL,和HOME_PATH
//域名
private static final String HOME_URL ="http://www.epff.cc";
//服务器地址,所有在此地址下的文件将被解析,而不在此路径下的文件不会被解析
private static final String HOME_PATH =File.separator+"uploads" + File.separator;
//服务器是否是tomcat
private static final boolean ISTOMCAT=true;
//private static final boolean ISTOMCAT=false;
//----------------------------------------------------------------------------------
/*
pj_unicodeToChinese(String s),将iso8859-1转换为gbk
参数s:要转换的字符串(iso8859-1)
返回:转换后的字符串(gbk)
错误:(1),如果s为空,返回"",(2)当捕获错误时,返回s原样
*/
private static String pj_unicodeToChinese(String s){
try{
if(s==null||s.equals("")) return "";
String newstring=new String(s.getBytes("ISO8859_1"),"GBK");
return newstring;
}catch(Exception e){return s;}
}
/*
pj_pathToUrl2(String filePath,String homeUrl,String homePath),将位于服务器中有效的文件转化为url
原理是通过对比服务器文件夹的位置于文件的位置,将服务器文件夹的位置去掉,剩下的部分就是文件位于服务器文件夹的url部分
加上服务器的url
参数filePath:文件位置,如:c:\webapps\abc.htm
参数homeUrl:服务器url.例如http:/www.xxx.com
参数homePath:服务器文件夹位置,如:c:\webapps
返回:转换后的url,如:http:/www.xxx.com/abc.htm
PathToUrl=pj_pathToUrl(path,"http://"+request.getServerName()+":"+request.getServerPort(),uf.getParent());
*/
public String pj_pathToUrl2(String filePath,String homeUrl,String homePath){
String rtnURL=null;
String tempURL=null;
String LowerCaseHomePath=null;
String LowerCaseFilePath=null;
if(homeUrl==null && homePath==null){
homeUrl=HOME_URL;
homePath=HOME_PATH;
LowerCaseHomePath=HOME_PATH.toLowerCase();
}else{
LowerCaseHomePath=homePath.toLowerCase();
}
LowerCaseFilePath=filePath.toLowerCase();
if(!LowerCaseHomePath.endsWith(File.separator)){LowerCaseHomePath=LowerCaseHomePath+File.separator;}
if(LowerCaseFilePath.indexOf(LowerCaseHomePath)==-1){ return rtnURL;}
else{
tempURL=filePath.substring(LowerCaseHomePath.length());
tempURL=tempURL.replace(File.separator,"/");
if(!homeUrl.endsWith("/")){homeUrl=homeUrl+"/";}
tempURL=homeUrl+tempURL;
rtnURL=tempURL;
}
return rtnURL;
}


public String getMimeType(String fName) {
fName = fName.toLowerCase();//小写转换
if (fName.endsWith(".jpg") || fName.endsWith(".jpeg") || fName.endsWith(".jpe")) return "image/jpeg";
else if (fName.endsWith(".gif")) return "image/gif";
else if (fName.endsWith(".pdf")) return "application/pdf";
else if (fName.endsWith(".htm") || fName.endsWith(".html") || fName.endsWith(".shtml")) return "text/html";
else if (fName.endsWith(".avi")) return "video/x-msvideo";
else if (fName.endsWith(".mov") || fName.endsWith(".qt")) return "video/quicktime";
else if (fName.endsWith(".mpg") || fName.endsWith(".mpeg") || fName.endsWith(".mpe")) return "video/mpeg";
else if (fName.endsWith(".zip")) return "application/zip";
else if (fName.endsWith(".tiff") || fName.endsWith(".tif")) return "image/tiff";
else if (fName.endsWith(".rtf")) return "application/rtf";
else if (fName.endsWith(".mid") || fName.endsWith(".midi")) return "audio/x-midi";
else if (fName.endsWith(".xl") || fName.endsWith(".xls") || fName.endsWith(".xlv")
|| fName.endsWith(".xla") || fName.endsWith(".xlb") || fName.endsWith(".xlt")
|| fName.endsWith(".xlm") || fName.endsWith(".xlk")) return "application/excel";
else if (fName.endsWith(".doc") || fName.endsWith(".dot")) return "application/msword";
else if (fName.endsWith(".png")) return "image/png";
else if (fName.endsWith(".xml")) return "text/xml";
else if (fName.endsWith(".svg")) return "image/svg+xml";
else if (fName.endsWith(".mp3")) return "audio/mp3";
else if (fName.endsWith(".ogg")) return "audio/ogg";
else return "text/plain";
}

%>
<% 
request.setCharacterEncoding("gbk");
response.setHeader("Pragma","No-cache");//HTTP 1.1
response.setHeader("Cache-Control","no-cache");//HTTP 1.0
response.setHeader("Expires","0");//防止被proxy

Locale locale = request.getLocale();// 获取客户端的地区

Hashtable supportedEncodings = new Hashtable();// 设置一个已知的地区和编码映射表
supportedEncodings.put(Locale.SIMPLIFIED_CHINESE, "GBK");
supportedEncodings.put(Locale.CHINESE, "GBK");
supportedEncodings.put(Locale.TRADITIONAL_CHINESE, "BIG5");
supportedEncodings.put(Locale.ENGLISH, "ISO8859-1");

String encoding = (String)supportedEncodings.get(locale);
if(encoding == null) {
    encoding = "GBK";
}

String encodingString = ";charset=" + encoding;// ;charset=ISO9959-1 like strings
String path=request.getParameter("path");

if(path==null || path.length()<1){
out.print("path is null");
}else{
path=pj_unicodeToChinese(path);
String PathToUrl=null;
if(USE_PATHTOURL){
if(AUTOSET_PATHTOURL){
File uf=new File(request.getRealPath("/"));
if(ISTOMCAT)
//如果是tomcat使用下面这行
PathToUrl=pj_pathToUrl2(path,"http://"+request.getServerName()+":"+request.getServerPort(),uf.getParent());
else
PathToUrl=pj_pathToUrl2(path,"http://"+request.getServerName()+":"+request.getServerPort(),uf.getAbsolutePath());
}else{
PathToUrl=pj_pathToUrl2(path,null,null);
}
}

if(PathToUrl!=null){
out.print("<script language='javascript'>window.location.href='"+PathToUrl+"';</script>");
//out.print(PathToUrl);
}else{
try{
File f=new File(path);
String mimeType = getMimeType(f.getName());
response.setContentType(mimeType+encodingString);//设置输出类型
if (mimeType.equals("text/plain")) response.setHeader("Content-Disposition", "inline;filename=\"temp.txt\"");
else response.setHeader("Content-Disposition", "inline;filename=\""+f.getName()+"\"");

out.clearBuffer();

if (f.exists() && f.canRead() && f.isFile()){
java.io.BufferedInputStream bis = new java.io.BufferedInputStream(new java.io.FileInputStream(f));
int i;
while ((i=bis.read()) != -1) response.getOutputStream().write(i);
bis.close();
}
out.flush();
return;

}catch(Exception ex){
out.print("Exception:"+ex.getMessage());
}

}
}
%>