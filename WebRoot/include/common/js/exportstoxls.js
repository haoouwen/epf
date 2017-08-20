var idTmr ;
//导出页面表格到本地
function exports(tableExcel) {//整个表格拷贝到EXCEL中 
	alert("导出EXCEL文件只能用含有IE核心的浏览器，例如IE6、IE8、360、傲游、世界之窗、搜狗、TT、QQ等。需按提示操作：工具---Internet选项---安全---Internet---自定义级别---对未标记为可安全执行脚本的ActiveX控件初始化并执行脚本---提示");
    var curTbl = document.getElementById(tableExcel); 
    var oXL = new ActiveXObject("Excel.Application"); 
    //创建AX对象excel 
    var oWB = oXL.Workbooks.Add(); 
    //获取workbook对象 
    var xlsheet = oWB.Worksheets(1);
    //激活当前sheet 
    var sel = document.body.createTextRange(); 
    sel.moveToElementText(curTbl); 
    //把表格中的内容移到TextRange中 
    sel.select(); 
    //全选TextRange中内容 
    sel.execCommand("Copy"); 
    //复制TextRange中内容  
    xlsheet.Paste(); 
    //粘贴到活动的EXCEL中       
    oXL.Visible = true; 
    //设置excel可见属性 
 
    try{
        var fname = oXL.Application.GetSaveAsFilename("save.xls", "Excel Spreadsheets (*.xls), *.xls");
    }catch(e){
        print("Nested catch caught " + e);
    }finally{
        oWB.SaveAs(fname);
 
        oWB.Close(savechanges=false);
         //xls.visible = false;
        oXL.Quit();
        oXL=null;
         //结束excel进程，退出完成
         //window.setInterval("Cleanup();",1);
        idTmr = window.setInterval("Cleanup();",1);
 
    }
} 
function Cleanup() {
    window.clearInterval(idTmr);
    CollectGarbage();
  }
			