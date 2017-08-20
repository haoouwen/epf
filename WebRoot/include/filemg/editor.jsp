<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ page import="java.io.*,java.text.SimpleDateFormat,java.util.Date,java.net.URLEncoder;"%>
<%!
private static final String EDITOR = "editor.jsp";
private static final int EDITFIELD_COLS = 120;//文本框的行数
private static final int EDITFIELD_ROWS = 40;//文本框的列数
//-----------------不要修改此横线以下的内容-----------------
/*
pj_unicodeToChinese(String s),将iso8859-1转换为gbk
参数s:要转换的字符串(iso8859-1)
返回:转换后的字符串(gbk)
错误:(1),如果s为空,返回"",(2)当捕获错误时,返回s原样
PJ 2008.2
*/
private static String pj_unicodeToChinese(String s){
try{
if(s==null||s.equals("")) return "";
String newstring=new String(s.getBytes("ISO8859_1"),"GBK");
return newstring;
}catch(Exception e){return s;}
}
/*
conv2Html(int i),字符转换为htm字符
主要用于转换>为&gt;,这些htm无法显示的符号
*/
public static String conv2Html(int i) {
if (i == '&') return "&amp;";
else if (i == '<') return "&lt;";
else if (i == '>') return "&gt;";
else if (i == '"') return "&quot;";
else return "" + (char) i;
}
%>
<% 
request.setCharacterEncoding("gbk");
response.setHeader("Pragma","No-cache");//HTTP 1.1
response.setHeader("Cache-Control","no-cache");//HTTP 1.0
response.setHeader("Expires","0");//防止被proxy
%>
<html>
<head>
<title>editor</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<link type="text/css" rel="StyleSheet" href="face.css" />
</head><body>
<span id="title">Lucy Editor</span><hr>
<%
String path=request.getParameter("path");
String charset=request.getParameter("charset");
String error="";
boolean isSaved=false;//是否保存成功
path=pj_unicodeToChinese(path);
File wf=null;
File bf=null;
FileOutputStream fos=null;
OutputStreamWriter osw=null;
File ef=null;
BufferedReader bfReader=null;
FileInputStream fis=null;
InputStreamReader isr=null;
//check
if(path==null || path.length()<1){
out.print("<div class='error'>这是一个PJ's jspFileBrowser的扩展页面,它不能被单独使用.请先打开PJ's jspFileBrowser</div>");
}

//保存
else if((request.getParameter("submit")!=null) && (request.getParameter("submit").equals("Save"))){
String text=request.getParameter("text");
path=request.getParameter("path");//如果path以POST方法发过来,不进行中文解码
if(charset==null) charset="sys";//如果sharset为空,自动使用系统默认编码
try{
wf = new File(path);
//备一个份罗
if(request.getParameter("backup")!=null){
bf= new File(path+".bak");
//如果已经存在删除之
if(bf.exists()) bf.delete();
//如果成功了
if(wf.renameTo(bf)) out.print("<div class='success'>备份成功:["+path+".bak].</div>");
else  out.print("<div class='error'>备份不成功:["+path+".bak].</div>");
}
//如果不存在直接创建一个罗
if(!wf.exists()) wf.createNewFile();
//开始保存了
fos=new FileOutputStream(wf);//字节流
if (charset.equals("sys")) osw=new OutputStreamWriter(fos);
if (charset.equals("utf-8")) osw=new OutputStreamWriter(fos,"UTF-8");
osw.write(text); 
//关闭流
if (osw!=null){
osw.flush(); 
osw.close(); 
}
isSaved=true;//成功标记
}catch(Exception edFileex){
isSaved=false;
if (osw!=null){
osw.flush(); 
osw.close();
}
error="<div class='error'>Exception:"+edFileex.getMessage()+"</div>";
}

//获得当前时间
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yy-MM-dd HH:mm:ss");
java.util.Date currentTime = new java.util.Date();
String date = formatter.format(currentTime); 
if(isSaved) out.print("<div class='success'>保存成功:["+path+"].以编码方式:["+charset+"].时间:["+date+"]</div>");
else out.print(error);
}

//打开
if(path==null) path="";
if(charset!=null) path=request.getParameter("path");//如果path以POST方法发过来,路径不进行中文解码
else charset="sys";
try{
ef = new File(path);
if (ef.length()>(1024*1024)){error="<div class='error'>不能打开&gt;1MB的文件</div>";}
else{
fis=new FileInputStream(ef);//读取字节流
if (charset.equals("sys")) isr=new InputStreamReader(fis);//将字节流转换为字符流,并且转变编码方式   
if (charset.equals("utf-8")) isr=new InputStreamReader(fis,"UTF-8");//将字节流转换为字符流,并且转变编码方式   
bfReader=new BufferedReader(isr);//缓冲流,取代了StringBuffer的功能进行缓存*/   
//bfReader=new BufferedReader(new FileReader(ef));
}
}catch(FileNotFoundException edFileFNFex){
error="<div class='error'>FileNotFoundException:"+edFileFNFex.getMessage()+"</div>";
}catch(NullPointerException edFileNPex){
error="<div class='error'>NullPointerException:"+edFileNPex.getMessage()+"</div>";
}catch(IOException edFileIOex){
error="<div class='error'>IOFoundException:"+edFileIOex.getMessage()+"</div>";
}
out.print(error);
int i;
//boolean dos = false;
//boolean cr = false;
//String server_url;
//if(SERVER_URL!=null) server_url=SERVER_URL;
//else server_url="http://"+request.getServerName()+":"+request.getServerPort();
%>
<div style="background-color:#CCCCCC;">正在编辑:[<%=path%>],以编码方式:[<%=charset%>]</div>
重新打开以编码:
<form action="<%=EDITOR%>" method="POST">
<input type="hidden" name="path" value="<%=path%>">
<select name="charset">
<option value="sys" <% if(charset==null || charset.equals("sys")) out.print("selected");%>>系统默认(sys)</option>
<option value="utf-8" <% if(charset!=null && charset.equals("utf-8")) out.print("selected");%>>UTF-8</option>
</select>
<input type="submit" class="button2" value="reopen">
</form>
&nbsp;&nbsp;&nbsp;
<b><%if(ef!=null) out.print(ef.getName());%></b>
<br>
<form action="<%=EDITOR%>" method="POST">
<textarea name="text" cols="<%=EDITFIELD_COLS%>" rows="<%=EDITFIELD_ROWS%>"><%
if(bfReader!=null){
while ((i= bfReader.read())>= 0){
out.print(conv2Html(i));
//if (i == '\r') cr = true;
//else if (cr && (i == '\n')) dos = true;
//else cr = false;
}
if(bfReader!=null) bfReader.close();
if(isr!=null) isr.close();
if(fis!=null) fis.close();
}
%></textarea>
<br>
<input type="hidden" name="path" value="<%=path%>">
<input type="hidden" name="charset" value="<%=charset%>">
<!--input type="radio" name="lineformat" value="dos" <%//= dos?"checked":""%>--><!--Ms-Dos/Windows-->
<!--<input type="radio" name="lineformat" value="unix" <%//= dos?"":"checked"%>--><!--Unix-->
<input type="checkbox" name="backup" value="backup">备份此文件
<input name="submit" type="submit" class="button2" value="Save">
</form>
</body>
</html>