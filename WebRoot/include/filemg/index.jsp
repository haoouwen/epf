<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.text.SimpleDateFormat,java.util.Vector,java.net.URLEncoder,java.util.Arrays;"
%>
<%!
//设置
//FILEINDEXALL, FILEINDEXALL的页面名称
private static final String FILEINDEXALL = "index.jsp";
//EDITOR,文本编辑器的页面名称;
private static final String EDITOR = "editor.jsp";
//VIEWER,预览页面名称
private static final String VIEWER = "viewer.jsp";
/*** 其他扩展插件 ***/
//PUT_FILENAME,上传扩展组件的页面名称
//-----------------不要修改此横线以下的内容-----------------
/*
fl_getDrivers()获得驱动器列表,返回字符串(<a href="...">)
*/
private static String fl_getDrivers(){
StringBuffer drivers=new StringBuffer();
File roots[]=File.listRoots();
for(int i=0;i<roots.length;i++){
drivers.append("<a href='"+FILEINDEXALL+"?path="+URLEncoder.encode(roots[i].toString())+"'>"+roots[i]+"</a>&nbsp;");
}
return drivers.toString();
}
/*
fl_unicodeToChinese(String s),将iso8859-1转换为gbk
参数s:要转换的字符串(iso8859-1)
返回:转换后的字符串(gbk)
错误:(1),如果s为空,返回"",(2)当捕获错误时,返回s原样
*/
private static String fl_unicodeToChinese(String s){
try{
if(s==null||s.equals("")) return "";
String newstring=new String(s.getBytes("ISO8859_1"),"GBK");
return newstring;
}catch(Exception e){return s;}
}
/*
fl_path2link(String path),将路径转换为<a href="...">
参数path:要转换的path字符串
返回:转换后的字符串(<a href="...">)
*/
private static String fl_path2link(String path) {
File f = new File(path);
StringBuffer buf = new StringBuffer();
String encPath=null;
if(!f.canRead()){
return path;
}else{
while (f.getParentFile() != null) {
encPath = URLEncoder.encode(f.getAbsolutePath());
buf.insert(0,"<a href=\""+FILEINDEXALL+"?path="+encPath+"\">"+f.getName()+File.separator+"</a>");
f = f.getParentFile();
}
encPath = URLEncoder.encode(f.getAbsolutePath());
buf.insert(0,"<a href=\""+FILEINDEXALL+"?path="+encPath+"\">"+f.getAbsolutePath()+"</a>");
}
return buf.toString();
}
/*
fl_convertFileSize(long fileSize),将byte为单位的文件大小转换windows的文件大小格式
参数fileSize:要转换的文件大小
返回:转换后的字符串,如10KB
*/
private static String fl_convertFileSize(long fileSize) {
String fileSizeOut=null;//size
if(fileSize<1024){
fileSizeOut=fileSize+"byte";
}else if(fileSize>=(1024*1024*1024)){
fileSize=fileSize/(1024*1024*1024);
fileSizeOut=fileSize +"GB";
}else if(fileSize>=(1024*1024)){
fileSize=fileSize/(1024*1024);
fileSizeOut=fileSize +"MB";
}else if(fileSize>=1024){
fileSize=fileSize/1024;
fileSizeOut=fileSize+"KB";
}
return fileSizeOut;
}
/*
expandFileList(String[] files, boolean inclDirs)
扩展文件列表,以便可以删除不为空的文件夹
没有修改此方法
*/
private static Vector expandFileList(String[] files, boolean inclDirs) {
Vector v = new Vector();
if (files == null) return v;
for (int i = 0; i < files.length; i++)
//v.add(new File(URLDecoder.decode(files[i])));
v.add(new File(files[i]));
for(int i = 0; i < v.size(); i++) {
File f = (File) v.get(i);
if (f.isDirectory()) {
File[] fs = f.listFiles();
for (int n = 0; n < fs.length; n++)
	v.add(fs[n]);
if (!inclDirs) {
	v.remove(i);
	i--;
}
}
}
return v;
}
/*
fl_delFile(String[] files),删除文件
参数String[] files:要删除的文件列表
*files字符串数组是有form提交的字符串列表,所以files[]都是位于同一目录下的文件,
*当获得此数组后,交由expandFileList()扩展至每个文件的最地层位置,即遍历files[]中所有文件夹里面的所有文件
返回:成功与否的提示
异常捕获:无
*/
private String fl_delFile(String[] files){
StringBuffer errorInfo=new StringBuffer();
String resultInfo="";
boolean error=false;//是否出现错误
Vector v = expandFileList(files, true);
int total=0;
for(int i=v.size()-1;i>=0;i--){
File f = (File)v.get(i);
//if (!f.canWrite() || !f.delete()) {
if (!f.delete()) {
errorInfo.append("<div class='error'>无法删除:["+f.getAbsolutePath()+"]</div>");
error=true;
continue;//继续进行循环
}
total++;
}//end for
if (error){
resultInfo=errorInfo.toString()+"<div class='error'>"+total+"个文件已删除,s部分文件无法删除</div>";
}else{
resultInfo="<div class='success'>"+total+"个文件已删除</div>";
}
return (resultInfo);
}
/*
fl_mvFile(String[] files,String newPath),移动文件
参数String[] files:要移动的文件列表
参数String newPath:要移动到的新位置
返回:成功与否的提示
异常捕获:NullPointerException(空指向错误),SecurityException(安全管理器阻止了此操作)
*/
private String fl_mvFile(String[] files,String newPath){
StringBuffer errorInfo=new StringBuffer();
boolean error=false;//是否出现错误
String sysInfo="";
String resultInfo="";
String fileExistsError="";
File f_old=null;
File f_new=null;
try{
for (int i=0;i<files.length;i++){
f_old=new File(files[i]);
if(!newPath.trim().endsWith(File.separator)) newPath=newPath.trim()+File.separator;
f_new=new File(newPath.trim()+f_old.getName());
if(!f_old.renameTo(f_new)){
errorInfo.append("<div class='error'>无法移动:["+files[i]+"]到["+newPath+"]</div>");
error=true;
continue;//继续进行for循环
}
}//end for
}catch(NullPointerException mvFileNPex){
sysInfo="<div class='error'>NullPointerException:"+mvFileNPex.getMessage()+"</div>";
error=true;
}catch(SecurityException mvFileSFex){
sysInfo="<div class='error'>SecurityException:"+mvFileSFex.getMessage()+"</div>";
error=true;
}
//error
if(error){
resultInfo=errorInfo.toString()+"<div class='error'>文件已移动到:["+newPath+"]"
+"<a href='"+FILEINDEXALL+"?path="+URLEncoder.encode(newPath)+"'>(点击进入)</a>部分文件无法移动</div>";
}else{
resultInfo="<div class='success'>所有文件已移动到:["+newPath+"]"
+"<a href='"+FILEINDEXALL+"?path="+URLEncoder.encode(newPath)+"'>(点击进入)</a></div>";
}
return (sysInfo+resultInfo);
}//end function
/*
fl_cpFile(String[] files,String newPath,String path),复制文件
参数String[] files:要移动的文件列表
参数String newPath:要移动到的新位置
参数String path:当前位置
返回:成功与否的提示
异常捕获:
NullPointerException(空指向错误),
SecurityException(安全管理器阻止了此操作),
FileNotFoundException
IOException
*/
private String fl_cpFile(String[] files,String newPath,String path){
StringBuffer errorInfo=new StringBuffer();
boolean error=false;//是否出现错误
String sysInfo="";
String resultInfo="";
String fileExistsError="";
boolean success=false;//是否成功
int total=0;
File f_old=null;
File f_new=null;
FileInputStream fis=null;
FileOutputStream fos=null;
Vector v = expandFileList(files, true);
byte buffer[] = new byte[0xffff];
int b;
try{
for(int i=0;i<v.size();i++){
f_old=(File) v.get(i);
if(!newPath.trim().endsWith(File.separator)) newPath=newPath.trim()+File.separator;
f_new=new File(newPath.trim()+f_old.getAbsolutePath().substring(path.length()));
//如果是文件夹直接创建
if(f_old.isDirectory()){
f_new.mkdirs();
total++;
}else if(f_new.exists()){
errorInfo.append("不能复制["+f_new.getAbsolutePath()+"],文件已存在");
error=true;
continue;//继续进行for循环
}else{
fis=new FileInputStream(f_old);
fos=new FileOutputStream(f_new);
while ((b = fis.read(buffer)) != -1){
fos.write(buffer,0,b);
}
total++;
}//end else
}//end for
if(fis != null) fis.close();
if(fos != null) fos.close();
}catch(NullPointerException cpFileNPex){
sysInfo="<div class='error'>NullPointerException:"+cpFileNPex.getMessage()+"</div>";
error=true;
}catch(FileNotFoundException cpFileFNFex){
sysInfo="<div class='error'>FileNotFoundException:"+cpFileFNFex.getMessage()+"</div>";
error=true;
}catch(SecurityException cpFileSFex){
sysInfo="<div class='error'>SecurityException:"+cpFileSFex.getMessage()+"</div>";
error=true;
}catch(IOException cpFileIOex){
sysInfo="<div class='error'>IOException:"+cpFileIOex.getMessage()+"</div>";
error=true;
}
//error
if(error){
resultInfo=errorInfo.toString()+"<div class='error'>"+total+"个文件已复制到:["+newPath+"]"
+"<a href='"+FILEINDEXALL+"?path="+URLEncoder.encode(newPath)+"'>(点击进入)</a>部分文件无法复制</div>";
}else{
resultInfo="<div class='success'>"+total+"个文件已复制到:["+newPath+"]"
+"<a href='"+FILEINDEXALL+"?path="+URLEncoder.encode(newPath)+"'>(点击进入)</a></div>";
}
return (sysInfo+resultInfo);
}
/*
fl_crFolder(String path,String name),创建文件夹
参数String path:要创建文件夹的位置
参数String name:要创建文件夹的名称
返回:成功与否的提示,或文件已存在提示
异常捕获:
NullPointerException(空指向错误),File crfolderFile=new File(crPath)抛出
SecurityException(安全管理器阻止了此操作),mkdir(),exists()抛出
*/
private String fl_crFolder(String path,String name){
String sysInfo="";
String resultInfo="";
String fileExistsError="";
boolean success=false;//是否成功
if(!path.trim().endsWith(File.separator)) path=path.trim()+File.separator;
String crPath=path.trim()+name;
try{
File crfolderFile=new File(crPath);
if(crfolderFile.exists()){
fileExistsError="文件夹已存在";
}else{
success=crfolderFile.mkdir();//如果创建成功返回true
}
}catch(NullPointerException crFolderNPex){
sysInfo="<div class='error'>NullPointerException:"+crFolderNPex.getMessage()+"</div>";
success=false;
}catch(SecurityException crFolderSFex){
sysInfo="<div class='error'>SecurityException:"+crFolderSFex.getMessage()+"</div>";
success=false;
}
//error
if(success){resultInfo="<div class='success'>1个文件夹已创建</div>";}
else{resultInfo ="<div class='error'>0个文件夹已创建,不能创建</div>";}
return (sysInfo+resultInfo);
}//end function
/*
fl_crFile(String path,String name),创建文件
参数String path:要创建文件的位置
参数String name:要创建文件的名称
返回:成功与否的提示,或文件已存在提示
异常捕获:
NullPointerException(空指向错误),File crfolderFile=new File(crPath)抛出
SecurityException(安全管理器阻止了此操作),createNewFile(),exists()抛出
*/
private String fl_crFile(String path,String name){
String sysInfo="";
String resultInfo="";
String fileExistsError="";
boolean success=false;//是否成功
if(!path.trim().endsWith(File.separator)) path=path+File.separator;
String crPath=path+name;
try{
File crfileFile=new File(crPath);
if(crfileFile.exists()){
fileExistsError="文件已存在";
}else{
success=crfileFile.createNewFile();//如果创建成功返回true
}
}catch(NullPointerException crFileNPex){
sysInfo="<div class='error'>NullPointerException:"+crFileNPex.getMessage()+"</div>";
success=false;
}catch(IOException crFileIOex){
sysInfo="<div class='error'>IOException:"+crFileIOex.getMessage()+"</div>";
success=false;
}catch(SecurityException crFileSFex){
sysInfo="<div class='error'>SecurityException:"+crFileSFex.getMessage()+"</div>";
success=false;
}
//error
if(success){resultInfo="<div class='success'>1个文件已创建成功</div>";}
else{resultInfo ="<div class='error'>0个文件已创建,不能创建:["+crPath+"]."+fileExistsError+"</div>";}
return (sysInfo+resultInfo);
}//end function
/*
fl_reName(String path,String oldFileName,String newFileName),重命名文件
参数String path:要当前位置
参数String oldFileName:旧文件名
参数String newFileName:新文件名
返回:成功与否的提示,或文件已存在提示
异常捕获:
NullPointerException(空指向错误),
File f_old=new File(path+oldFileName);
File f_new=new File(path+newFileName);抛出
SecurityException(安全管理器阻止了此操作),renameTo(),exists()抛出
*/
private String fl_reName(String path,String oldFileName,String newFileName){
String sysInfo="";
String resultInfo="";
String fileExistsError="";
boolean success=false;//是否成功
if(!path.trim().endsWith(File.separator)) path=path+File.separator;
try{
File f_old=new File(path+oldFileName);
File f_new=new File(path+newFileName);
//如果新文件名已有
if(f_new.exists()){
fileExistsError="文件或文件夹已存在";
}else{success=f_old.renameTo(f_new);}
}catch(NullPointerException cnFileNPex){
sysInfo="<div class='error'>NullPointerException:"+cnFileNPex.getMessage()+"</div>";
success=false;
}catch(SecurityException cnFileSFex){
sysInfo="<div class='error'>SecurityException:"+cnFileSFex.getMessage()+"</div>";
success=false;
}
if(success){resultInfo="<div class='success'>["+oldFileName+"]已重命名为:["+newFileName+"]</div>";}
else{resultInfo ="<div class='error'>不能重命名["+oldFileName+"]为["+newFileName+"]."+fileExistsError+"</div>";}
return (sysInfo+resultInfo);
}
/*
getReqValue(String s,String reqName),用于解析上传文件时,获得头部某参数的值
原理是通过先查找两个双引号来获得中间的值
s=字符串
reqName=要查找的文件头字符串,如filename
getReqValue("filename=\"C:\\Documents and Settings\\jun\\桌面\\mainwindowb4\\table_all.txt\"","filename")
*/
private String getReqValue(String s,String reqName){
String reqValue = null; //此请求的值,将返回此值
int reqNameStartIndex = -1; //此请求参数开始部分出现的位置,即filename="的字母f出现的位置
int reqValueStartIndex = -1;//开始"出现的位置
int reqValueEndIndex = -1;//最后"出现的位置
reqName = reqName + "=" + "\"";//reqName=reqName加上等号和双引号即变成filename="
reqNameStartIndex = s.indexOf(reqName);//出现filename="的位置
if(reqNameStartIndex > -1){//如果出现filename="
reqValueStartIndex = reqNameStartIndex + reqName.length();//req值的开始位置=req参数名出现位置+req参数长度
reqValueEndIndex = s.indexOf("\"",reqValueStartIndex);//从参数值开始处即\"开始处查找另外一个\"
  if(reqValueStartIndex > -1 && reqValueEndIndex > -1){ //如果这个参数值有开始有结尾,即有两个双引号都有
    reqValue = s.substring(reqValueStartIndex,reqValueEndIndex);//获得两个双引号之间的内容
  }
}
return reqValue;//返回reqValue
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
<title>文件管理器</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<link type="text/css" rel="StyleSheet" href="/include/filemg/face.css" />
<%@ include file="/include/uploadIncSpace.html"%> 
</head><body>
<input type="hidden" id="file_size" value="2"/>
<input type="hidden" id="flash_size" value="100"/>
<input type="hidden" id="image_size" value="6"/>
<input type="hidden" id="image_type" value="gif/jpg/png/bmp/jpeg"/>
<input type="hidden" id="flash_type" value="flv/mp4/mov/f4v/3gp"/>
<input type="hidden" id="file_type" value="doc/docx/xls/xlsx/ppt/pptx/pdf/rar/txt/csv"/>
<input type="hidden" id="img_cust_id" value="0"/>
<span id="title">文件管理器</span>
<div id="about">
</div>
<hr>
<%
String resultInfo="";
String URLpath=null;
String path=request.getParameter("path");
boolean error=false;
//if path==null
if(path==null||path.length()<1){
path=request.getRealPath("/uploads/");
//path=application.getRealPath(".");
}
//path=URLDecoder.decode();
path=fl_unicodeToChinese(path);
if(request.getParameter("errorPath")!=null){
path=request.getParameter("errorPath");
}
//download
if (request.getParameter("download") != null) {
String filePath = request.getParameter("download");
filePath=fl_unicodeToChinese(filePath);
File file=null;
FileInputStream fis=null;
BufferedOutputStream os=null;
try{
file = new File(filePath);
fis = new FileInputStream(file); 
os = new BufferedOutputStream(response.getOutputStream());
fis = new java.io.FileInputStream(filePath); 
response.setContentType("application/octet-stream");
response.setContentLength((int)file.length()); 
response.setHeader("Content-Disposition","attachment; filename=\""+new String(file.getName().getBytes("GBK"),"iso8859-1")+"\"");
byte[] buff=new byte[1024*10];
int i = 0; 
while( (i = fis.read(buff)) > 0 ){ 
os.write(buff, 0, i); 
}
fis.close(); 
os.flush(); 
os.close(); 
}catch(Exception ex){
out.print("<div class='error'>"+new String(ex.getMessage())+"</div>");
if(fis != null)fis.close(); 
if(os != null)os.close(); 
}
}//end
//delete files
else if((request.getParameter("submit")!= null)&&(request.getParameter("submit").equals("Delete"))){
String[] files=request.getParameterValues("selfile");
path=request.getParameter("path");//如果path以POST发过来就不要过滤了
if(files==null || files.length<1){
out.print("<div class='error'>没有选择任何文件</div>");
}else{
resultInfo=fl_delFile(files);
out.print(resultInfo);
}
}//end
// Move selected file(s)
else if((request.getParameter("submit")!=null)&&(request.getParameter("submit").equals("MoveFile"))){
String mvFilePath=request.getParameter("mvFilePath");
String[] files=request.getParameterValues("selfile");
if(files==null || files.length<1){
out.print("<div class='error'>没有选择任何文件</div>");
}else if(mvFilePath==null || mvFilePath.length()<1){
out.print("<div class='error'>没有获得移动的新路径</div>");
}else{
resultInfo=fl_mvFile(files,mvFilePath);
out.print(resultInfo);
}
}//end
//Copy selected file(s)
else if((request.getParameter("submit")!=null)&&(request.getParameter("submit").equals("CopyFile"))){
String cpFilePath=request.getParameter("cpFilePath");
String[] files=request.getParameterValues("selfile");
if(files==null || files.length<1){
out.print("<div class='error'>没有选择任何文件</div>");
}else if(cpFilePath==null || cpFilePath.length()<1){
out.print("<div class='error'>没有获得复制的新路径</div>");
}else{
resultInfo=fl_cpFile(files,cpFilePath,path);
out.print(resultInfo);
}
}//end
//Create folder
else if((request.getParameter("submit")!=null)&&(request.getParameter("submit").equals("CreateFolder"))){
String crFolderName=request.getParameter("crFolderName");
path=request.getParameter("path");//如果path以POST发过来就不要过滤了
String info=null;
if(path==null || path.length()<1){
out.print("<div class='error'>没有获得当前路径</div>");
}else if(crFolderName==null || crFolderName.length()<1){
out.print("<div class='error'>没有获得创建文件夹的名称</div>");
}else{
resultInfo=fl_crFolder(path,crFolderName);
out.print(resultInfo);
}
}//end
//Create file
else if((request.getParameter("submit")!=null)&&(request.getParameter("submit").equals("CreateFile"))){
String crFileName=request.getParameter("crFileName");
path=request.getParameter("path");//如果path以POST发过来就不要过滤了
String crFilePath=path+File.separator+crFileName;
if(path==null || path.length()<1){
out.print("<div class='error'>没有获得当前路径</div>");
}else if(crFileName==null || crFileName.length()<1){
out.print("<div class='error'>没有获得创建文件的名称</div>");
}else{
resultInfo=fl_crFile(path,crFileName);
out.print(resultInfo);
}
}//end
//rename
else if((request.getParameter("submit")!=null)&&(request.getParameter("submit").equals("Rename"))){
String oldFileName=request.getParameter("oldFileName");
String newFileName=request.getParameter("newFileName");
path=request.getParameter("path");//如果path以POST发过来就不要过滤了
if(path==null || path.length()<1){
out.print("<div class='error'>没有获得当前路径</div>");
}else if(oldFileName ==null || oldFileName .length()<1){
out.print("<div class='error'>请先点击文件右边的\"重命名\"连接</div>");
}else if(newFileName ==null || newFileName .length()<1){
out.print("<div class='error'>没有获得文件的新名称</div>");
}else{
resultInfo=fl_reName(path,oldFileName,newFileName);
out.print(resultInfo);
}
}//end
//upload file
else if((request.getContentType()!= null) && (request.getContentType().toLowerCase().startsWith("multipart"))){
//if(request.getContentLength()>(1024*1024)){
//out.print("<div class='error'>不能上传size大于1MB</div>");
//}else{
File ff=null;
FileOutputStream ffos=null;
int l=0,l2=0;
int startlineLength=0;//边界符长度
long filesize=0;
byte[] buffout=new byte[1024*10];
byte[] buff=new byte[1024*10];
String savepathname=null;
String fileName=null;//文件名
String startline=null;//第一行
String filenameline=null;//文件名行(第二行)
String endline=null;//最后一行
String middleline=null;//中间行
String exInfo="";
boolean issuccess=false;
boolean isEnd=false;
ServletInputStream sis=null;
try{
sis=request.getInputStream();
//读第1行
startlineLength=sis.readLine(buff,0,buff.length);
//如果第一行<3
if(startlineLength<3){
out.print("<div class='error'>读取第一行错误</div>");
}else{
startline=new String(buff,0,startlineLength-2);//得到第一行作为结尾行的依据
endline=startline+"--";//结尾行比第一行多"--"
//读第2行
l=sis.readLine(buff,0,buff.length);
filenameline=new String(buff,0,l);//得到第二行作为文件名的依据
if(filenameline.indexOf("filename=\"")==-1){
out.print("<div class='error'>没有发现http头文部的filename</div>");
}else{
filenameline=getReqValue(filenameline,"filename");
fileName=filenameline.substring(filenameline.lastIndexOf(File.separator)+1);
//fileName=getFileName(filenameline);
if(fileName==null || fileName.length()<1 ){out.print("<div class='error'>没有发现要上传文件的文件名</div>");}
else{
if(!path.endsWith(File.separator)){path=path+File.separator;}
savepathname=path+fileName;
ff = new File(savepathname); 
//如果文件已经存在
if(ff.exists()){
sis.close();
out.print("<div class='error'>不能上传到此路径:["+savepathname+"],文件已存在</div>");
}else{
l=sis.readLine(buff,0,buff.length);//读第3行
l=sis.readLine(buff,0,buff.length);//读第4行(空行)
ffos=new FileOutputStream(ff);
l2=sis.readLine(buffout,0,buffout.length);//读取内容第一行放入输出缓冲
//读其他行
while((l=sis.readLine(buff,0,buff.length))!=-1){
//判断是否最后一行,比较长度比比较字符串使用更小的系统开销
if(l==(startlineLength+2) || l==(startlineLength+2)){
middleline=new String(buff,0,l);
if(middleline.startsWith(endline)){isEnd=true;}
}
if(isEnd){ffos.write(buffout,0,l2-2);break;}
else{
ffos.write(buffout,0,l2);
for(int i=0;i<buff.length;i++){
buffout[i]=buff[i];
}
l2=l;
}
}//while的结尾
filesize=ff.length();
out.print("<div class='success'>1个文件已上传:["+savepathname+"],size:["+fl_convertFileSize(filesize)+"("+filesize+"字节)]</div>");
}//else
}//else
}//else
}//else
}catch(Exception ulex){
out.print("<div class='error'>Exception:"+ulex.getMessage()+"</div>");
}
//关闭流
if(ffos!=null){
ffos.flush();
ffos.close();
}
if(sis!=null) sis.close();
//}//else
}//else

//start view filelist
int objFileError=0;
File objFile=new File(path);
URLpath=URLEncoder.encode(path);
String fileAbsolutePath=null;
String encodeFileAbsolutePath=null;
%>
<script type="text/javascript" src="/include/filemg/hands.js"></script>
<table width="100%" cellpadding="0" cellspacing="0">
<tr >
<td style="padding-bottom:5px;">
<%
if(objFile.getParent()==null|| !objFile.getParent().contains("uploads")){}
else{out.print("<a href='"+FILEINDEXALL+"?path="+URLEncoder.encode(objFile.getParent())+"'>上一级目录</a>");}
SimpleDateFormat formatter = new SimpleDateFormat("yyyy-M-d H:mm");
int a=0,b=0;
%>
<a href="<%=FILEINDEXALL%>?path=<%=URLpath%>">刷新</a> |
<a href="<%=FILEINDEXALL%>">主页</a>
</td><td align="right" style="padding-bottom:5px;">
文件查找:<input name="filt" type="text" class="text" onkeyup="filter(this);">
</td>
</tr>
</table>
<form action="<%=FILEINDEXALL%>" method="POST" name="FileList">
<div style="background-color:#DEDEDE">
<table width="100%" cellpadding="1" cellspacing="1" id="Filetable">
<thead>
<tr bgcolor="#CCCCCC">
<td width="2%" align="center"><input name="selall" type="checkbox" onClick="selectAll(this.form);" class="checkbox"></td>
<td width="49%">文件名</td><td width="9%">大小</td><td width="17%">最后修改时间</td>
<td width="5%">编辑</td><td width="8%">重命名</td><td width="10%">下载</td>
</tr>
</thead>
<tbody>
<%
File fileList[]=objFile.listFiles();
if(fileList!=null) Arrays.sort(fileList);
if(fileList==null){
out.print("<tr><td colspan='7' bgcolor='#FFA2A2'>路径错误或其他错误,导致不能创建文件列表<br>尝试手动输入此路径:");
out.print("<form action="+FILEINDEXALL+" method='post'><input type='text' name='errorPath' size='50' class='text'>&nbsp;");
out.print("<input type='submit' value='submit' class='button2'></form></td></tr></table></div>");
}else{
for(int i=0;i<fileList.length;i++){
if(fileList[i].isDirectory()){ 
a++;
fileAbsolutePath=fileList[i].getAbsolutePath();
encodeFileAbsolutePath=URLEncoder.encode(fileAbsolutePath);
%>
<tr style="background-color:#FFFFFF" onMouseOver="selectRow(this,0);" onMouseOut="selectRow(this,1);" onmouseup="selectRow(this,2);">
<td align="center">
<input type="checkbox" name="selfile" value="<%=fileAbsolutePath%>" onmousedown="dis();" class="checkbox">
</td><td>
<img src='/include/filemg/folder.gif' align="absmiddle" style="margin-left:4px;">
<a href="<%=FILEINDEXALL%>?path=<%=encodeFileAbsolutePath%>" onmousedown="dis();">
<%=fileList[i].getName()%></a></td>
<td>文件夹</td>
<td><%=formatter.format(fileList[i].lastModified())%></td>
<td>-</td>
<td><a href="#tool" onmousedown="dis();" onClick="showDivValue('Div_rename','<%=fileList[i].getName()%>');">点击重命名</a></td>
<td>-</td>
</tr>
<%
}//end if
}//end for
for(int i=0;i<fileList.length;i++){
if(fileList[i].isFile()){ 
b++;
fileAbsolutePath=fileList[i].getAbsolutePath();
encodeFileAbsolutePath=URLEncoder.encode(fileAbsolutePath);
%>
<tr style="background-color:#FFFFFF" onMouseOver="selectRow(this,0);" onMouseOut="selectRow(this,1);" onmouseup="selectRow(this,2);">
<td align="center">
<input type="checkbox" name="selfile" value="<%=fileAbsolutePath%>" onmousedown="dis();" class="checkbox">
</td><td>
<img src='/include/filemg/file.gif' align="absmiddle" style="margin-left:4px;">
<%
 //去掉根目录
 String  showpath="";
 if(encodeFileAbsolutePath!=null&&!"".equals(encodeFileAbsolutePath)){
    if(encodeFileAbsolutePath.contains(".")){
      showpath=encodeFileAbsolutePath.replace("/WebRoot","").replace("%5CWebRoot","");
    }else{
      showpath=encodeFileAbsolutePath;
    }
 }
 %>
<a href="<%=VIEWER%>?path=<%=showpath%>" onmousedown="dis();" target="_blank">
<%=fileList[i].getName()%></a></td>
<td><%=fl_convertFileSize(fileList[i].length())%></td>
<td><%=formatter.format(fileList[i].lastModified())%></td>
<td><a href="<%=EDITOR%>?path=<%=encodeFileAbsolutePath%>" onmousedown="dis();" target="_blank">编辑</a></td>
<td><a href="#tool" onmousedown="dis();" onClick="showDivValue('Div_rename','<%=fileList[i].getName()%>');">重命名</a></td>
<td>
<a href="<%=FILEINDEXALL%>?download=<%=encodeFileAbsolutePath%>" onmousedown="dis();">
点击下载
</a>
</td></tr>
<%
}//end if
}//end for
%>
</tbody></table></div>
<%
}
%>
<div style="background-color:#CCCCCC;">
&nbsp;总共:
<img src='folder.gif' align="absmiddle">:<%=a%>,
<img src='file.gif' align="absmiddle">:<%=b%>
(过滤结果:
<img src='folder.gif' align="absmiddle">:<span id="folderTotal"><%=a%></span>,
<img src='file.gif' align="absmiddle">:<span id="fileTotal"><%=b%></span>)
</div><br>
<a name="tool"></a>
<b style="color:#666666">操作选中的文件:</b><br>
<input type="hidden" name="path" value="<%=path%>">
<span id="Div_movefile_span">
<a href="#tool" onclick="javascript:showDiv('Div_movefile');">移动</a> 
</span>|<span id="Div_copyfile_span">
<a href="#tool" onclick="javascript:showDiv('Div_copyfile');">复制</a> 
</span>|<span id="Div_deletefile_span">
<a href="#tool" onclick="javascript:showDiv('Div_deletefile');">删除</a>
</span>
<!--Move File-->
<div id="Div_movefile" style="display:none;margin-top:3px;LETTER-SPACING:0px">
<b style="color:red;">移动文件(Move File):</b>移动选中的文件到你填写的目标路径下<br>
请输入移动的目标路径(如 "c:\abc\"):<br>
<input type="text" name="mvFilePath" size="90" class="text"><br>
<input type="submit" name="submit" value="MoveFile" class="button2">
<hr></div>
<!---->
<!--Copy File-->
<div id="Div_copyfile" style="display:none;margin-top:3px;LETTER-SPACING:0px">
<b style="color:red;">复制文件(Copy File):</b>复制选中的文件到你填写的目标路径下<br>
请输入复制的目标路径(如 "c:\abc\"):<br>
<input type="text" name="cpFilePath" size="90" class="text"><br>
<input type="submit" name="submit" value="CopyFile" class="button2">
<hr></div>
<!---->
<!--Delete File-->
<div id="Div_deletefile" style="display:none;margin-top:3px;LETTER-SPACING:0px">
<input type="submit" name="submit" class="button2" value="Delete" onclick="return confirm('你确定要删除这些文件?')">
<b style="color:red;">系统提示(Delete)</b>:危险的操作,文件一旦被删除就不能恢复了,确定删除,点击按钮,选中的文件将被彻底删除.
<hr></div>
<!---->
</form>
<div style="margin-top:3px;color:#666666;"><b>操作当前目录:</b></div>
<span id="Div_upload_span">
<a href="#tool" onclick="javascript:showDiv('Div_upload');">上传文件</a>
</span>|<span id="Div_newfolder_span">
<a href="#tool" onclick="javascript:showDiv('Div_newfolder');">创建文件夹</a>
</span>|<span id="Div_newfile_span">
<a href="#tool" onclick="javascript:showDiv('Div_newfile');">创建文件</a>
</span>|<span id="Div_rename_span">
<a href="#tool" onclick="javascript:showDiv('Div_rename');">重命名</a>
</span>
<!--upload-->
<div id="Div_upload" style="display:none;margin-top:3px;LETTER-SPACING:0px">



<b style="color:red;">上传文件(Upload)</b>:你将上传一个文件到:<font color=red><%=path%></font> 上传文件成功之后,点击一下&nbsp;&nbsp;<a href="<%=FILEINDEXALL%>?path=<%=URLpath%>">刷新</a><br>



<table border="0" cellpadding="0" cellspacing="0">
  <tr>
	<td style="padding:0px;">
	<div id="fileQueue"></div>
			<input type="hidden"  name="img_path"    id="uploadresult" cssStyle="width:250px;"/>
			<input type="hidden"  id="idpath" value="<%=path%>" />
	</td>
	<td style="padding-left:3px;">
	    
		<input type="file" name="uploadifyfile" id="uploadifyfile"/>
	</td>
	<td style="padding-left:3px;">
		<img src="/include/components/upload/borwssee.jpg" onclick="showpicture('uploadresult');"/>
			<script>uploadOneImgMulti();</script>
		</td>
	</tr>
</table>     	      


<hr></div>
<!---->
<!--Create Folder-->
<div id="Div_newfolder" style="display:none;margin-top:3px;LETTER-SPACING:0px">
<b style="color:red;">创建一个新文件夹(Create Folder):</b>你将创建一个新文件夹在:<font color=red><%=path%></font><br>
<form method="POST" action="<%=FILEINDEXALL%>">
<input type="hidden" name="path" value="<%=path%>">
新文件夹的名称:(不要含有 \ / : * ? " < > |)<br>
<input type="text" name="crFolderName" size="50" class="text">
<input type="submit" name="submit" value="CreateFolder" class="button2">
</form>
<hr></div>
<!---->
<!--Create File-->
<div id="Div_newfile" style="display:none;margin-top:3px;LETTER-SPACING:0px">
<b style="color:red;">创建一个新文件(Create File):</b>你将创建一个新文件在:<font color=red><%=path%></font><br>
<form method="POST" action="<%=FILEINDEXALL%>">
<input type="hidden" name="path" value="<%=path%>">
新文件的名称:(如"abc.txt",并且不要含有 \ / : * ? " < > |)<br>
<input type="text" name="crFileName" size="50" class="text">
<input type="submit" name="submit" value="CreateFile" class="button2">
</form>
<hr></div>
<!---->
<!--rename-->
<div id="Div_rename" style="display:none;margin-top:3px;LETTER-SPACING:0px">
<b>重命名一个文件夹或文件(Rename):</b>你将重命名一个文件夹或文件在:<font color=red><%=path%></font><br>
<form method="POST" action="<%=FILEINDEXALL%>">
<input type="hidden" name="path" value="<%=path%>">
<input type="hidden" id="Div_rename_hidden" name="oldFileName">
请先点击文件列表右边对应的"重命名"连接,然后在此对话框中更改文件名称:(不要含有 \ / : * ? " < > |)<br>
<input type="text" id="Div_rename_input" name="newFileName" size="50" class="text">
<input type="submit" name="submit" value="Rename" class="button2">
</form>
<hr></div>
<!---->
<!--展示预览图片-->
<div class="wrap" id="displaypicture" style="display:none;">
	    <div  align="right"> <a onclick="closeshow();"  href="###" ><img src="/include/components/upload/close.png" /></a></div>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollLeft" href="javascript:;">&#8249;</a>
		<a onclick="scrollHori.start(this);" class="rollMenu" id="rollRight" href="javascript:;">&#8250;</a>
		<div id="rollBox">
			<ul id="rollList">
			</ul>
		</div>	
</div>
<!--展示预览图片end-->
</body></html>