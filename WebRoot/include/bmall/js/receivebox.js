function replysubmit(actionName,id){
     var replycon="<div class='email email2'>";
     replycon+="<p>------------------ 原始邮件 ------------------</p>";
          replycon+="<table width='80%' cellpadding='0' cellspacing='0' >";
             replycon+="<tr valign='top'>";
              replycon+="<th width='100%'>";
               replycon+="<p>主&nbsp;&nbsp;&nbsp;题:"+$("#sendbox_title").val();+"</p>";
                replycon+="<p>发件人:"+$("#sendbox_cust_name").val();+"</p>";
               replycon+="<p>时&nbsp;&nbsp;间："+$("#sendbox_in_date").val();+"</p>";
               replycon+="<p>收件人："+$("#sendbox_recevie_name").val();+"</p>";
               replycon+="</th>";
             replycon+="<tr>";
               replycon+="<td >";
                replycon+=""+$("#newcontent").val()+"";
               replycon+="</td>";
             replycon+="</tr>";
          replycon+="</table>";
        replycon+="</div>";
    $("#sendman_content").val(replycon);
    $("#reply").attr("action",actionName+"?receivebox.receive_id="+id).submit();
}



