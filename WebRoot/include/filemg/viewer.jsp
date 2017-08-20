<%@ page language="java" contentType="text/html; charset=gbk"
 import="java.io.File, java.util.Hashtable, java.util.Locale,java.net.URLEncoder,com.rbt.common.util.PropertiesUtil"
 pageEncoding="GBK"%>
<%!
//�ļ���
private static final String VIEWER = "viewer.jsp";
//·����URL���Զ�ת��,�ܹ����������е��ļ�·��ת��Ϊurl·��
//�����ʹ�ô˹���,������USE_PATHTOURL=false,ϵͳ���ö�ȡ�ķ�ʽ���ʴ��ļ�,������url
private static final boolean USE_PATHTOURL=true;
//private static final boolean USE_PATHTOURL=false;
//url��ַ������ϵͳ�Զ�����,Ҳ���ֶ�����,
//���AUTOSET_PATHTOURL=true,ϵͳ�Զ�����.AUTOSET_PATHTOURL=false,Ҫ�ֶ�����HOME_URL��HOME_PATH��������
private static final boolean AUTOSET_PATHTOURL=true;
//private static final boolean AUTOSET_PATHTOURL=false;
//HOME_URL,HOME_PATH��Ҫ��USE_PATHTOURL=true��AUTOSET_PATHTOURL=false������²���Ч
//�����κ��������Ҫע�͵�HOME_URL,��HOME_PATH
//����
private static final String HOME_URL ="http://www.epff.cc";
//��������ַ,�����ڴ˵�ַ�µ��ļ���������,�����ڴ�·���µ��ļ����ᱻ����
private static final String HOME_PATH =File.separator+"uploads" + File.separator;
//�������Ƿ���tomcat
private static final boolean ISTOMCAT=true;
//private static final boolean ISTOMCAT=false;
//----------------------------------------------------------------------------------
/*
pj_unicodeToChinese(String s),��iso8859-1ת��Ϊgbk
����s:Ҫת�����ַ���(iso8859-1)
����:ת������ַ���(gbk)
����:(1),���sΪ��,����"",(2)���������ʱ,����sԭ��
*/
private static String pj_unicodeToChinese(String s){
try{
if(s==null||s.equals("")) return "";
String newstring=new String(s.getBytes("ISO8859_1"),"GBK");
return newstring;
}catch(Exception e){return s;}
}
/*
pj_pathToUrl2(String filePath,String homeUrl,String homePath),��λ�ڷ���������Ч���ļ�ת��Ϊurl
ԭ����ͨ���Աȷ������ļ��е�λ�����ļ���λ��,���������ļ��е�λ��ȥ��,ʣ�µĲ��־����ļ�λ�ڷ������ļ��е�url����
���Ϸ�������url
����filePath:�ļ�λ��,��:c:\webapps\abc.htm
����homeUrl:������url.����http:/www.xxx.com
����homePath:�������ļ���λ��,��:c:\webapps
����:ת�����url,��:http:/www.xxx.com/abc.htm
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
fName = fName.toLowerCase();//Сдת��
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
response.setHeader("Expires","0");//��ֹ��proxy

Locale locale = request.getLocale();// ��ȡ�ͻ��˵ĵ���

Hashtable supportedEncodings = new Hashtable();// ����һ����֪�ĵ����ͱ���ӳ���
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
//�����tomcatʹ����������
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
response.setContentType(mimeType+encodingString);//�����������
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