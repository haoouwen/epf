//生成32位GUID

function GetGuid(){
     var g = "";
     var i = 32;
     while(i--){
         g += Math.floor(Math.random()*16.0).toString(16);
     }
     return g;
}