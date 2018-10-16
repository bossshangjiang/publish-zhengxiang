
function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

function mychangeFunction()
{
    m=getQueryVariable("id");
    switch (Number(m))
    {
        case 1:
            x=document.getElementById("bingbian1");  // 找到元素
            x.innerHTML="总论";    // 改变内容
            x=document.getElementById("txt1");  // 找到元素
            x.innerHTML="靶征";    // 改变内容
            x=document.getElementById("txt2");  // 找到元素
            x.innerHTML="EPI";    // 改变内容
            x=document.getElementById("txt3");  // 找到元素
            x.innerHTML="MRS";    // 改变内容
            x=document.getElementById("txt4");  // 找到元素
            x.innerHTML="MRI";    // 改变内容
            x=document.getElementById("txt5");  // 找到元素
            x.innerHTML="皮质扣押征";    // 改变内容
            x=document.getElementById("txt6");  // 找到元素
            x.innerHTML="脊髓空洞征";    // 改变内容
            break;
        case 2:
            x=document.getElementById("bingbian1");  // 找到元素
            x.innerHTML="中枢神经系统";    // 改变内容
            x=document.getElementById("txt1");  // 找到元素
            x.innerHTML="中枢神经系统二级目录";    // 改变内容
            x=document.getElementById("txt2");  // 找到元素
            x.innerHTML="中枢神经系统二级目录2";    // 改变内容
            x=document.getElementById("txt3");  // 找到元素
            x.innerHTML="中枢神经系统二级目录3";    // 改变内容
            x=document.getElementById("txt4");  // 找到元素
            x.innerHTML="中枢神经系统二级目录4";    // 改变内容
            x=document.getElementById("txt5");  // 找到元素
            x.innerHTML="中枢神经系统二级目录5";    // 改变内容
            x=document.getElementById("txt6");  // 找到元素
            x.innerHTML="中枢神经系统二级目录6";    // 改变内容
            break;
        case 3:
            x=document.getElementById("bingbian1");  // 找到元素
            x.innerHTML="呼吸系统";    // 改变内容
            x=document.getElementById("txt1");  // 找到元素
            x.innerHTML="呼吸系统二级目录";    // 改变内容
            x=document.getElementById("txt2");  // 找到元素
            x.innerHTML="呼吸系统二级目录2";    // 改变内容
            x=document.getElementById("txt3");  // 找到元素
            x.innerHTML="呼吸系统二级目录3";    // 改变内容
            x=document.getElementById("txt4");  // 找到元素
            x.innerHTML="呼吸系统二级目录4";    // 改变内容
            x=document.getElementById("txt5");  // 找到元素
            x.innerHTML="呼吸系统二级目录5";    // 改变内容
            x=document.getElementById("txt6");  // 找到元素
            x.innerHTML="呼吸系统二级目录6";    // 改变内容
            break;
        case 4:
            x=document.getElementById("bingbian1");  // 找到元素
            x.innerHTML="循环系统";    // 改变内容
            x=document.getElementById("txt1");  // 找到元素
            x.innerHTML="循环系统二级目录";    // 改变内容
            x=document.getElementById("txt2");  // 找到元素
            x.innerHTML="循环系统二级目录2";    // 改变内容
            x=document.getElementById("txt3");  // 找到元素
            x.innerHTML="循环系统二级目录3";    // 改变内容
            x=document.getElementById("txt4");  // 找到元素
            x.innerHTML="循环系统二级目录4";    // 改变内容
            x=document.getElementById("txt5");  // 找到元素
            x.innerHTML="循环系统二级目录5";    // 改变内容
            x=document.getElementById("txt6");  // 找到元素
            x.innerHTML="循环系统二级目录6";    // 改变内容
            break;
        case 5:
            x=document.getElementById("bingbian1");  // 找到元素
            x.innerHTML="乳腺";    // 改变内容
            x=document.getElementById("txt1");  // 找到元素
            x.innerHTML="乳腺二级目录";    // 改变内容
            x=document.getElementById("txt2");  // 找到元素
            x.innerHTML="乳腺二级目录2";    // 改变内容
            x=document.getElementById("txt3");  // 找到元素
            x.innerHTML="乳腺二级目录3";    // 改变内容
            x=document.getElementById("txt4");  // 找到元素
            x.innerHTML="乳腺二级目录4";    // 改变内容
            x=document.getElementById("txt5");  // 找到元素
            x.innerHTML="乳腺二级目录5";    // 改变内容
            x=document.getElementById("txt6");  // 找到元素
            x.innerHTML="乳腺二级目录6";    // 改变内容
            break;
        case 6:
            x=document.getElementById("bingbian1");  // 找到元素
            x.innerHTML="消化系统";    // 改变内容
            x=document.getElementById("txt1");  // 找到元素
            x.innerHTML="消化系统二级目录";    // 改变内容
            x=document.getElementById("txt2");  // 找到元素
            x.innerHTML="消化系统二级目录2";    // 改变内容
            x=document.getElementById("txt3");  // 找到元素
            x.innerHTML="消化系统二级目录3";    // 改变内容
            x=document.getElementById("txt4");  // 找到元素
            x.innerHTML="消化系统二级目录4";    // 改变内容
            x=document.getElementById("txt5");  // 找到元素
            x.innerHTML="消化系统二级目录5";    // 改变内容
            x=document.getElementById("txt6");  // 找到元素
            x.innerHTML="消化系统二级目录6";    // 改变内容
            break;
        case 7:
            x=document.getElementById("bingbian1");  // 找到元素
            x.innerHTML="泌尿生殖系统";    // 改变内容
            x=document.getElementById("txt1");  // 找到元素
            x.innerHTML="泌尿生殖系统二级目录";    // 改变内容
            x=document.getElementById("txt2");  // 找到元素
            x.innerHTML="泌尿生殖系统二级目录2";    // 改变内容
            x=document.getElementById("txt3");  // 找到元素
            x.innerHTML="泌尿生殖系统二级目录3";    // 改变内容
            x=document.getElementById("txt4");  // 找到元素
            x.innerHTML="泌尿生殖系统二级目录4";    // 改变内容
            x=document.getElementById("txt5");  // 找到元素
            x.innerHTML="泌尿生殖系统二级目录5";    // 改变内容
            x=document.getElementById("txt6");  // 找到元素
            x.innerHTML="泌尿生殖系统二级目录6";    // 改变内容
            break;
        case 8:
            x=document.getElementById("bingbian1");  // 找到元素
            x.innerHTML="骨骼肌肉系统";    // 改变内容
            x=document.getElementById("txt1");  // 找到元素
            x.innerHTML="骨骼肌肉系统二级目录";    // 改变内容
            x=document.getElementById("txt2");  // 找到元素
            x.innerHTML="骨骼肌肉系统二级目录2";    // 改变内容
            x=document.getElementById("txt3");  // 找到元素
            x.innerHTML="骨骼肌肉系统二级目录3";    // 改变内容
            x=document.getElementById("txt4");  // 找到元素
            x.innerHTML="骨骼肌肉系统二级目录4";    // 改变内容
            x=document.getElementById("txt5");  // 找到元素
            x.innerHTML="骨骼肌肉系统二级目录5";    // 改变内容
            x=document.getElementById("txt6");  // 找到元素
            x.innerHTML="骨骼肌肉系统二级目录6";    // 改变内容
            break;
        default:
            x=document.getElementById("txt1");  // 找到元素
            x.innerHTML=m;    // 改变内容
    }
}

