
//验证该类型为decimal
function _isdecimal(str){
    if (/[^d.]/i.test(str)){
            return false;
        }
    return true;
}
function www_zzjs_net()
{
num=parseFloat(document.getElementById("admin_Rate_insert_rate_exchangerate").value);
alert(Math.floor(num * 1000) / 1000);
}
function lookRate(){
			$("#lookRate").popup({p_width:"560", p_height:"215", pop_title:"查询汇率"});
}