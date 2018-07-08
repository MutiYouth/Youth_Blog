var iframeId = "mainFrame";
//控制Frame跳转
function jump(url,type) {
    if (url != null && url != '') {
        var webApp,frame;
        if(type == 0){//ifrmae所在页面调用
            webApp = document.getElementById("webApp");
            frame = document.getElementById(iframeId);
            frame.src = url;
        }
        if(type== 1){//iframe子页面调用
            webApp = parent.document.getElementById("webApp");
            frame = parent.document.getElementById(iframeId);
            frame.src = url;
        }

    }
}

//iframe所在页面调用此方法：
// 根据iframe子页面高度度控制iframe高度自适应
//function setIframeHeight(){
//    try{
//        var iframe = document.getElementById(iframeId);
//        if(iframe.attachEvent){
//            iframe.height =  iframe.contentWindow.document.documentElement.height - 15;
//            return;
//        }else{
//            iframe.height = iframe.contentDocument.body.height - 15;
//            return;
//        }
//    }catch(e){
//        throw new Error('setIframeHeight Error');
//    }
//}
//iframe子页面调用此方法：根据子页面高度设置iframe高度
function setParentIframeHeight(){
    try{
        var parentIframe = parent.document.getElementById(iframeId);
        if(window.attachEvent){
            parentIframe.height = this.document.documentElement.scrollHeight -15;
            return;
        }else{
            parentIframe.height = this.document.body.scrollHeight -15;
            return;
        }
    }catch(e){
        throw new Error('setParentIframeHeight Error');
    }
}

// 日期时间的过滤
//传入时间戳,转为特定格式的字符串
//默认格式  "yyyy-MM-dd hh:mm:ss"
function formatDateTime (gmt) { //author: meizz
    var date = new Date(gmt);
    var fmt = "yyyy-MM-dd hh:mm:ss";
    var o = {
        "M+": date.getMonth() + 1, //月份
        "d+": date.getDate(), //日
        "h+": date.getHours(), //小时
        "m+": date.getMinutes(), //分
        "s+": date.getSeconds(), //秒
        "q+": Math.floor((date.getMonth() + 3) / 3), //季度
        "S": date.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;

}