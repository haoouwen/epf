<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>重复提交</title>
    <style>
    	.tabcss{
    		border:1px solid #6699CC;
    		width:100%;
    		height:130px;
    		background:#F0F7FF;
    		padding-bottom:10px;
    		line-height:30px;
    	}
    	body{
    		color:#505050;
    		background:white;
    	}
    	span{
    		font-size:12px;
    	}
    	.fhdiv{
    		width:56px;
    		height:19px;
    		background:url(/include/common/images/bgbt.gif) no-repeat;
    		text-align:center;
    		font-size:12px;
    		color:white;
    		line-height:19px;
    		margin-left:60px;
    		margin-top:20px;
    	}
    	.fhdiv a{
    		text-decoration:none;
    		color:white;
    	}
    </style>
  </head>
  <body>
<div class="postion"></div>
<div class="rtdcont">
	<div class="rttable">
		<table  align="center">
  	<tr><td>
	  	<table align="center" cellpadding="0" cellspacing="0" class="tabcss">
	  		<tr>
	  			<td width="20%">
	  				<img src="/include/common/images/warning.ico">
	  			</td>
	  			<td width="65%" valign="bottom">
	  				<font color="#444444"><b>信息提示：</b></font><br/>
	  				请不要刷新浏览器提交该页面。<br/>
	  				<span>您可以点击返回按钮，返回之前操作的页面。</span><br/>
	  				<div class="fhdiv">
			  			<a href="javascript:history.go(-1);">返回</a>
			  		</div>
	  			</td>
	  			<td valign="bottom">
	  			</td>
	  		</tr>
	  	</table>
	  </td></tr>
	  </table>
	</div>
	<div class="clear"/>
</div> 

  </body>
</html>
