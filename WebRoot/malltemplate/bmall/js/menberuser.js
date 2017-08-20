function testpass(password){
    var score = 0;
    if (password.length < 6 ) { return -6; }
    score += password.length * 4;
    score += ( repeat(1,password).length - password.length ) * 1;
    score += ( repeat(2,password).length - password.length ) * 1;
    score += ( repeat(3,password).length - password.length ) * 1;
    score += ( repeat(4,password).length - password.length ) * 1;
    if (password.match(/(.*[0-9].*[0-9].*[0-9])/)){ score += 5;}
    if (password.match(/(.*[!,@,#,$,%,^,&,*,?,_,~].*[!,@,#,$,%,^,&,*,?,_,~])/)){ score += 5 ;}
    if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)){ score += 10;}
    if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)){ score += 15;}
    if (password.match(/([!,@,#,$,%,^,&,*,?,_,~])/) && password.match(/([0-9])/)){ score += 15;}
    if (password.match(/([!,@,#,$,%,^,&,*,?,_,~])/) && password.match(/([a-zA-Z])/)){score += 15;}
    if (password.match(/^\w+$/) || password.match(/^\d+$/) ){ score -= 10;}
    if ( score < 0 ){score = 0;}
    if ( score > 100 ){ score = 100;}
    return score;
    
    function repeat(len,str){
    var res = "";
    for (var i = 0; i < str.length; i++ ){
        var repeated = true;
        for (var j = 0, max = str.length - i - len; j < len && j < max; j++){
            repeated = repeated && (str.charAt(j + i) == str.charAt(j + i + len));
        }
        if (j < len) repeated = false;
        if (repeated) {
            i += len - 1;
            repeated = false;
        }else{
            res += str.charAt(i);
        }
    }
    return res;
    }
}
function checkpass(passwd){
    var score = testpass(passwd.value);
        var pclass = score < 34 ? 'lowb' : (score < 68 ? 'midb' : 'heightb');
         $("#ps_s").addClass(pclass);
}

function setpswstrong(passwd){
    var score = testpass(passwd.value);
    var pswstrong = score < 34 ? '0' : (score < 68 ? '1' : '2');
    document.getElementById('psw_strong').value=pswstrong;
}