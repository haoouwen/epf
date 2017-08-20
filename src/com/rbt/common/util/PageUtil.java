/*
 
 * Package:com.rbt.common.util
 * FileName: PropertiesUtil.java
 */
package com.rbt.common.util;

public class PageUtil {

	public int curPage = 0; // 当前页
	private int pageSize_s = 0; // 后台分页每页多少行
	private int webPageSize_s = 40; // 前台分页每页多少行
	private int endSize; // 用于not in(select top endSize id)不在多少行内
	private int totalRow; // 共多少行
	private int totalPage; // 共多少页

	public int getStart() {
		if(curPage > totalPage){
			curPage = 1;
			return 0;
		}else if (curPage > 1){
			return (curPage - 1) * pageSize_s;
		}else{
			return 0;
		}
	}

	public int getEnd() {
		return pageSize_s;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {

		int temp = pageSize_s * (curPage - 1);
		this.setEndSize(temp);
		this.curPage = curPage;
	}

	public int getEndSize() {
		return endSize;
	}

	public void setEndSize(int endSize) {
		this.endSize = endSize;
	}

	public int getPageSize() {
		return pageSize_s;
	}

	public void setPageSize(int pageSize_s) {
		this.pageSize_s = pageSize_s;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {

		totalPage = totalRow / pageSize_s;
		if (totalRow % pageSize_s > 0)
			totalPage = totalPage + 1;

		this.totalRow = totalRow;
	}

	public int getTotalPage() {

		return this.totalPage;
	}

	public String getToolsMenu() {
		StringBuffer str = new StringBuffer("");
		int next, prev;
		prev = curPage - 1;
		next = curPage + 1;
		str.append(" 总共"+ totalRow +"条记录/" + totalPage + "页");
		str.append("  每页<SELECT size=1 name=pagesize onchange='this.form.pages_s.value=1;this.form.pageSize_s.value=this.value;this.form.submit();'>");

		if (pageSize_s == 3) {
			str.append("<OPTION value=3 selected>3</OPTION>");
		} else {
			str.append("<OPTION value=3>3</OPTION>");
		}

		if (pageSize_s == 10) {
			str.append("<OPTION value=10 selected>10</OPTION>");
		} else {
			str.append("<OPTION value=10>10</OPTION>");
		}
		if (pageSize_s == 20) {
			str.append("<OPTION value=20 selected>20</OPTION>");
		} else {
			str.append("<OPTION value=20>20</OPTION>");
		}
		if (pageSize_s == 50) {
			str.append("<OPTION value=50 selected>50</OPTION>");
		} else {
			str.append("<OPTION value=50>50</OPTION>");
		}
		if (pageSize_s == 100) {
			str.append("<OPTION value=100 selected>100</OPTION>");
		} else {
			str.append("<OPTION value=100>100</OPTION>");
		}
		str.append("</SELECT>");
		str.append("条|&nbsp;");
		if (curPage > 1) {
			str.append("<a class=\"sy\" style=\"cursor:pointer;\" onclick=\"pages_util(1);\"  >首页</a>&nbsp;");
		} else {
			str.append("<span  class=\"sy\" style=\"color:#CCCCCC;\" >首页</span>&nbsp;");
		}
		if (curPage > 1) {
			str.append("<a class=\"up\" style=\"cursor:pointer;\"  onclick=\"pages_util("+ prev + ");\"  >上一页</a>&nbsp;");
		} else {
			str.append("<span class=\"up\"  class=\"upno\" style=\"color:#CCCCCC;\" >上一页</span>&nbsp;");
		}
		if (curPage < totalPage) {
			str.append("<a  style=\"cursor:pointer;\" class=\"next\" onclick=\"pages_util("+ next + ");\" >下一页</a>&nbsp;");
		} else {
			str.append("<span  class=\"nextno\" style=\"color:#CCCCCC;\" >下一页</span>&nbsp;");
		}
		if (totalPage > 1 && curPage != totalPage) {
			str.append("<a class=\"sy\" style=\"cursor:pointer;\" onclick=\"pages_util("+ totalPage + ");\" >尾页</a>&nbsp;&nbsp;");
		} else {
			str.append("<span class=\"sy\" style='color:#CCCCCC;' >尾页</span> &nbsp;");
		}
		
		str.append("转到");
		str.append("<SELECT size=1 name=Pagelist onchange='this.form.pages_s.value=this.value;this.form.submit();'>");
		for (int i = 1; i < totalPage + 1; i++) {
			if (i == curPage) {
				str.append("<OPTION value=" + i + " selected>" + i + "</OPTION>");
			} else {
				str.append("<OPTION value=" + i + ">" + i + "</OPTION>");
			}
		}
		str.append("</SELECT>页&nbsp;&nbsp;");
		str.append("<INPUT type=\"hidden\"  value=\"" + curPage + "\" name=\"pages_s\" id=\"pages_s\" /> ");
		str.append("<INPUT type=\"hidden\"  value=\"" + pageSize_s + "\" name=\"pageSize_s\" /> ");
		return str.toString();
	}
	
	/**
	 * 触屏版会员中心 列表页，分页控件
	 * @return
	 */
	public String getWebAppToolsMenu() {
		StringBuffer str = new StringBuffer("");
		int next, prev;
		prev = curPage - 1;
		next = curPage + 1;
		/*str.append(" 总共"+ totalRow +"条记录/" + totalPage + "页");
		str.append("  每页<SELECT size=1 name=pagesize onchange='this.form.pages_s.value=1;this.form.pageSize_s.value=this.value;this.form.submit();'>");

		if (pageSize_s == 3) {
			str.append("<OPTION value=3 selected>3</OPTION>");
		} else {
			str.append("<OPTION value=3>3</OPTION>");
		}

		if (pageSize_s == 10) {
			str.append("<OPTION value=10 selected>10</OPTION>");
		} else {
			str.append("<OPTION value=10>10</OPTION>");
		}
		if (pageSize_s == 20) {
			str.append("<OPTION value=20 selected>20</OPTION>");
		} else {
			str.append("<OPTION value=20>20</OPTION>");
		}
		if (pageSize_s == 50) {
			str.append("<OPTION value=50 selected>50</OPTION>");
		} else {
			str.append("<OPTION value=50>50</OPTION>");
		}
		if (pageSize_s == 100) {
			str.append("<OPTION value=100 selected>100</OPTION>");
		} else {
			str.append("<OPTION value=100>100</OPTION>");
		}
		str.append("</SELECT>");
		str.append("条|&nbsp;");*/
		
		if (curPage > 1) {
			str.append("<a id=\"home\" style=\"cursor:pointer;\" onclick=\"pages_util_app(1);\"  >首页</a>&nbsp;");
		} else {
			str.append("<a  class=\"sy\" style=\"color:#CCCCCC;\" >首页</a>&nbsp;");
		}
		if (curPage > 1) {
			str.append("<a id=\"prev\" class=\"up\" style=\"cursor:pointer;\"  onclick=\"pages_util_app("+ prev + ");\"  >上一页</a>&nbsp;");
		} else {
			str.append("<a class=\"up\"  class=\"upno\" style=\"color:#CCCCCC;\" >上一页</a>&nbsp;");
		}
		if (curPage < totalPage) {
			str.append("<a id=\"next\"  style=\"cursor:pointer;\" class=\"next\" onclick=\"pages_util_app("+ next + ");\" >下一页</a>&nbsp;");
		} else {
			str.append("<a  class=\"nextno\" style=\"color:#CCCCCC;\" >下一页</a>&nbsp;");
		}
		if (totalPage > 1 && curPage != totalPage) {
			str.append("<a id=\"end\" style=\"cursor:pointer;\" onclick=\"pages_util_app("+ totalPage + ");\" >尾页</a>&nbsp;&nbsp;");
		} else {
			str.append("<a class=\"sy\" style='color:#CCCCCC;' >尾页</a> &nbsp;");
		}
		
		/*str.append("转到");
		str.append("<SELECT size=1 name=Pagelist onchange='this.form.pages_s.value=this.value;this.form.submit();'>");
		for (int i = 1; i < totalPage + 1; i++) {
			if (i == curPage) {
				str.append("<OPTION value=" + i + " selected>" + i + "</OPTION>");
			} else {
				str.append("<OPTION value=" + i + ">" + i + "</OPTION>");
			}
		}
		str.append("</SELECT>页&nbsp;&nbsp;");*/
		str.append("<INPUT type=\"hidden\"  value=\"" + curPage + "\" name=\"pages_s\" id=\"pages_s\" /> ");
		str.append("<INPUT type=\"hidden\"  value=\"" + pageSize_s + "\" name=\"pageSize_s\" /> ");
		return str.toString();
	}

	public String getWebToolsMenu() {
		StringBuffer str = new StringBuffer("");
		str.append("<div class='paging'>");
		int maxPage,minPage;
		maxPage=curPage+7;	
		minPage=curPage-7;		
		
		// <a href="#" class="prea">上一页</a><a href="#" class="sela">1</a><a href="#"  class="prea">下一页</a>
	    //   到第<input type="text" class="pagetext">页<input type="button" class="pagebut" value="确定"></div>
		
		
		if(maxPage>totalPage) maxPage=totalPage;
		if(curPage>totalPage) curPage=totalPage;
		if(minPage<1) minPage=1;
		if (curPage > 1) {
			str.append("<a href=\"javascript:void(0);\" class=\"prea\" onclick=\"document.forms[0].pages_s.value=1;document.forms[0].submit();\">首页</a>&nbsp;");
		} 
		for(int i=minPage;i<curPage;i++){
			str.append("<a class=\"numpag\" onclick=\"document.forms[0].pages_s.value="+i+";document.forms[0].submit();\" style=\"cursor:pointer;\">"+i+"</a>");
		}
		str.append("<a  class=\"sela\"  onclick=\"document.forms[0].pages_s.value="+curPage+";document.forms[0].submit();\" style=\"cursor:pointer;\">"+curPage+"</a>");
		for(int i=curPage+1;i<=maxPage;i++){
			str.append("<a  class=\"sela\" onclick=\"document.forms[0].pages_s.value="+i+";document.forms[0].submit();\" style=\"cursor:pointer;\">"+i+"</a>");
		}
		if (totalPage > 1 && curPage != totalPage) {
			str.append("<a href=\"javascript:void(0);\" class=\"prea\" onclick='document.forms[0].pages_s.value=" + totalPage + ";document.forms[0].submit();'>末页</a>&nbsp;&nbsp;");
		}
		str.append(" 共" + totalPage + "页，到第");
		str.append(" <input class=\"pagetext\" type=\"text\"onkeyup=\"checkDigitals(this);\" value=" + curPage + " size=1 name=\"pages_s\">页");
		str.append(" <input class=\"pagebut\"  onclick='jumpTo()' value='确定' type='button'  />");
		str.append("<INPUT type=hidden  value=" + webPageSize_s + " name=\"webPageSize_s\"> ");
		str.append("<INPUT type=hidden  value=" + curPage + " name=\"curPage\"> ");
		str.append("<INPUT type=hidden  value=" + totalPage + " name=\"totalPage\"> ");
		str.append("</div>");
		
		return str.toString();
	}
}
