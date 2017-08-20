//由于jQuery 1.9.0 以上版本 jquery去掉了对 $.browser 的支持,采用$.support 来判断浏览器类型。导致之前的很多插件报错
//使用jquery的继承机制 对jquery 1.11.1版本 进行扩展 使其支持 $.browser 方法，已达到兼容之前组件的目的
jQuery.extend({  
    browser: function()   
    {  
        var  
        rwebkit = /(webkit)\/([\w.]+)/,  
        ropera = /(opera)(?:.*version)?[ \/]([\w.]+)/,  
        rmsie = /(msie) ([\w.]+)/,  
        rmozilla = /(mozilla)(?:.*? rv:([\w.]+))?/,      
        browser = {},  
        ua = window.navigator.userAgent,  
        browserMatch = uaMatch(ua);  
  
        if (browserMatch.browser) {  
            browser[browserMatch.browser] = true;  
            browser.version = browserMatch.version;  
        }  
        return { browser: browser };  
    },  
});  
  
function uaMatch(ua)   
{  
        ua = ua.toLowerCase();  
  
        var match = rwebkit.exec(ua)  
                    || ropera.exec(ua)  
                    || rmsie.exec(ua)  
                    || ua.indexOf("compatible") < 0 && rmozilla.exec(ua)  
                    || [];  
  
        return {  
            browser : match[1] || "",  
            version : match[2] || "0"  
        };  
}