<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ page import="java.io.*,java.text.SimpleDateFormat,java.util.Date,java.net.URLEncoder;"%>
<%!
private static final String EDITOR = "editor.jsp";
private static final int EDITFIELD_COLS = 120;//�ı��������
private static final int EDITFIELD_ROWS = 40;//�ı��������
//-----------------��Ҫ�޸Ĵ˺������µ�����-----------------
/*
pj_unicodeToChinese(String s),��iso8859-1ת��Ϊgbk
����s:Ҫת�����ַ���(iso8859-1)
����:ת������ַ���(gbk)
����:(1),���sΪ��,����"",(2)���������ʱ,����sԭ��
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
conv2Html(int i),�ַ�ת��Ϊhtm�ַ�
��Ҫ����ת��>Ϊ&gt;,��Щhtm�޷���ʾ�ķ���
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
response.setHeader("Expires","0");//��ֹ��proxy
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
boolean isSaved=false;//�Ƿ񱣴�ɹ�
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
out.print("<div class='error'>����һ��PJ's jspFileBrowser����չҳ��,�����ܱ�����ʹ��.���ȴ�PJ's jspFileBrowser</div>");
}

//����
else if((request.getParameter("submit")!=null) && (request.getParameter("submit").equals("Save"))){
String text=request.getParameter("text");
path=request.getParameter("path");//���path��POST����������,���������Ľ���
if(charset==null) charset="sys";//���sharsetΪ��,�Զ�ʹ��ϵͳĬ�ϱ���
try{
wf = new File(path);
//��һ������
if(request.getParameter("backup")!=null){
bf= new File(path+".bak");
//����Ѿ�����ɾ��֮
if(bf.exists()) bf.delete();
//����ɹ���
if(wf.renameTo(bf)) out.print("<div class='success'>���ݳɹ�:["+path+".bak].</div>");
else  out.print("<div class='error'>���ݲ��ɹ�:["+path+".bak].</div>");
}
//���������ֱ�Ӵ���һ����
if(!wf.exists()) wf.createNewFile();
//��ʼ������
fos=new FileOutputStream(wf);//�ֽ���
if (charset.equals("sys")) osw=new OutputStreamWriter(fos);
if (charset.equals("utf-8")) osw=new OutputStreamWriter(fos,"UTF-8");
osw.write(text); 
//�ر���
if (osw!=null){
osw.flush(); 
osw.close(); 
}
isSaved=true;//�ɹ����
}catch(Exception edFileex){
isSaved=false;
if (osw!=null){
osw.flush(); 
osw.close();
}
error="<div class='error'>Exception:"+edFileex.getMessage()+"</div>";
}

//��õ�ǰʱ��
java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yy-MM-dd HH:mm:ss");
java.util.Date currentTime = new java.util.Date();
String date = formatter.format(currentTime); 
if(isSaved) out.print("<div class='success'>����ɹ�:["+path+"].�Ա��뷽ʽ:["+charset+"].ʱ��:["+date+"]</div>");
else out.print(error);
}

//��
if(path==null) path="";
if(charset!=null) path=request.getParameter("path");//���path��POST����������,·�����������Ľ���
else charset="sys";
try{
ef = new File(path);
if (ef.length()>(1024*1024)){error="<div class='error'>���ܴ�&gt;1MB���ļ�</div>";}
else{
fis=new FileInputStream(ef);//��ȡ�ֽ���
if (charset.equals("sys")) isr=new InputStreamReader(fis);//���ֽ���ת��Ϊ�ַ���,����ת����뷽ʽ   
if (charset.equals("utf-8")) isr=new InputStreamReader(fis,"UTF-8");//���ֽ���ת��Ϊ�ַ���,����ת����뷽ʽ   
bfReader=new BufferedReader(isr);//������,ȡ����StringBuffer�Ĺ��ܽ��л���*/   
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
<div style="background-color:#CCCCCC;">���ڱ༭:[<%=path%>],�Ա��뷽ʽ:[<%=charset%>]</div>
���´��Ա���:
<form action="<%=EDITOR%>" method="POST">
<input type="hidden" name="path" value="<%=path%>">
<select name="charset">
<option value="sys" <% if(charset==null || charset.equals("sys")) out.print("selected");%>>ϵͳĬ��(sys)</option>
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
<input type="checkbox" name="backup" value="backup">���ݴ��ļ�
<input name="submit" type="submit" class="button2" value="Save">
</form>
</body>
</html>