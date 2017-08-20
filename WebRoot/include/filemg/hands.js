var browser=0;
var check = false;
var Div_Array = Array(
Array('Div_deletefile'),
Array('Div_upload'),
Array('Div_newfolder')
);
function dis(){check = true;}
function checkBrowser(){
if(browser==0){
if(window.opera) browser = 1;//Opera
if(document.getElementById) browser = 2;//Moz or Netscape
if(document.all && browser!=1) browser = 3;
}
}
//0=mouseover;1=mouseout;2=mouseup;
function selectRow(element,action){
var erst;
checkBrowser();
if((browser==1)||(browser==3)) erst = element.firstChild.firstChild;//op,ie
else if(browser==2) erst = element.firstChild.nextSibling.firstChild;//ns
//action
if (action==0){
if (erst.checked == true) element.style.backgroundColor="#BCC8CB";
else element.style.backgroundColor="#EBEDED";
}
else if (action==1){
if (erst.checked == true) element.style.backgroundColor="#DDE4E6";
else element.style.backgroundColor="#FFFFFF";
}
//mouseup
else if ((action==2)&&(!check)){
if (erst.checked==true) element.style.backgroundColor="#DDE4E6";
else element.style.backgroundColor="#BCC8CB";
erst.click();
}
else check=false;
}
function selectAll(){
for(var x=0;x<document.FileList.elements.length;x++){
var y = document.FileList.elements[x];
var ytr = y.parentNode.parentNode;
var check = document.FileList.selall.checked;
if(y.name =="selfile"){
if (y.disabled != true){
y.checked=check;
if (y.checked == true) ytr.style.backgroundColor="#DDE4E6";
else ytr.style.backgroundColor="#FFFFFF";
}
}
}
}
function filter(begriff){
var suche = begriff.value.toLowerCase();
var table = document.getElementById("Filetable");
var ele,type;
var folderTotal=0,fileTotal=0;
for (var r = 1; r < table.rows.length; r++){
ele = table.rows[r].cells[1].innerHTML.replace(/<[^>]+>/g,"");
type = table.rows[r].cells[2].innerHTML.replace(/<[^>]+>/g,"");
if (ele.toLowerCase().indexOf(suche)>=0 ){
table.rows[r].style.display = '';
if(type=="folder"){folderTotal++;}
else{fileTotal++;}
}else{table.rows[r].style.display = 'none';}
}
document.getElementById("folderTotal").innerHTML=folderTotal;
document.getElementById("fileTotal").innerHTML=fileTotal;
}
function disableAll(){
var span_id;
for (i = 0; i < Div_Array.length; i++){
span_id=Div_Array[i]+"_span";
document.getElementById(span_id).className = "unchoosed";
document.getElementById(Div_Array[i]).style.display = 'none';
}
}
function showDiv(cmd){
var _div=document.getElementById(cmd);
var _span=document.getElementById(cmd+"_span");
if(_div.style.display == "none"){
disableAll();
_div.style.display = "block";
_span.className = "choosed";
}else{
_div.style.display = "none";
_span.className = "unchoosed";
}
}
function showDivValue(cmd,value){
disableAll();
document.getElementById(cmd).style.display='block';
document.getElementById(cmd+"_span").className = "choosed";
document.getElementById(cmd+"_input").value=value;
document.getElementById(cmd+"_hidden").value=value;
}
//about
function about(){
var about="";
window.alert(about);
}