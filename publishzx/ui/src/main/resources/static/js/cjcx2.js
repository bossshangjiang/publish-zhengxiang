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
    n=getQueryVariable("secondlevel");
    m=Number(m);
    n=Number(n);
    if(m==1&&n==1)
    {
        x=document.getElementById("bingbian1");  // 找到元素
        x.innerHTML="头颈五官";    // 改变内容
        x=document.getElementById("bingbian2");  // 找到元素
        x.innerHTML="二级目录编号1";    // 改变内容
        x=document.getElementById("txt1");  // 找到元素
        x.innerHTML="头颈五官二级目录1下的三级目录1";    // 改变内容
        x=document.getElementById("txt2");  // 找到元素
        x.innerHTML="头颈五官二级目录1下的三级目录2";    // 改变内容
        x=document.getElementById("txt3");  // 找到元素
        x.innerHTML="头颈五官二级目录1下的三级目录3";    // 改变内容
        x=document.getElementById("txt4");  // 找到元素
        x.innerHTML="头颈五官二级目录1下的三级目录4";    // 改变内容
        x=document.getElementById("txt5");  // 找到元素
        x.innerHTML="头颈五官二级目录1下的三级目录5";    // 改变内容
        x=document.getElementById("txt6");  // 找到元素
        x.innerHTML="头颈五官二级目录1下的三级目录6";    // 改变内容
        x=document.getElementById("txt7");  // 找到元素
        x.innerHTML="头颈五官二级目录1下的三级目录7";    // 改变内容
        x=document.getElementById("txt8");  // 找到元素
        x.innerHTML="头颈五官二级目录1下的三级目录8";    // 改变内容
        x=document.getElementById("txt9");  // 找到元素
        x.innerHTML="头颈五官二级目录1下的三级目录9";
    }  // 改变内容
    else
    {
        x=document.getElementById("bingbian1");  // 找到元素
        x.innerHTML="一级目录编号为"+m;    // 改变内容
        x=document.getElementById("bingbian2");  // 找到元素
        x.innerHTML="二级目录编号为"+n;    // 改变内容
        x=document.getElementById("txt1");  // 找到元素
        x.innerHTML="一级目录编号为"+m+"二级目录编号为"+n+"下的三级目录1";    // 改变内容
        x=document.getElementById("txt2");  // 找到元素
        x.innerHTML="一级目录编号为"+m+"二级目录编号为"+n+"下的三级目录2";    // 改变内容
        x=document.getElementById("txt3");  // 找到元素
        x.innerHTML="一级目录编号为"+m+"二级目录编号为"+n+"下的三级目录3";    // 改变内容
        x=document.getElementById("txt4");  // 找到元素
        x.innerHTML="一级目录编号为"+m+"二级目录编号为"+n+"下的三级目录4";    // 改变内容
        x=document.getElementById("txt5");  // 找到元素
        x.innerHTML="一级目录编号为"+m+"二级目录编号为"+n+"下的三级目录5";    // 改变内容
        x=document.getElementById("txt6");  // 找到元素
        x.innerHTML="一级目录编号为"+m+"二级目录编号为"+n+"下的三级目录6";    // 改变内容
        x=document.getElementById("txt7");  // 找到元素
        x.innerHTML="一级目录编号为"+m+"二级目录编号为"+n+"下的三级目录7";    // 改变内容
        x=document.getElementById("txt8");  // 找到元素
        x.innerHTML="一级目录编号为"+m+"二级目录编号为"+n+"下的三级目录8";    // 改变内容
        x=document.getElementById("txt9");  // 找到元素
        x.innerHTML="一级目录编号为"+m+"二级目录编号为"+n+"下的三级目录9";
    }
}