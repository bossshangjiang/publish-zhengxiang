//定义一个avalonjs的控制器
var viewmodel = avalon.define({
    //id必须和页面上定义的ms-controller名字相同，否则无法控制页面
    $id: "viewmodel",
    datalist: {},
    text: "请求数据cc",

    request: function () {
        $.ajax({
            type: "post",
            url: "/data",    //向springboot请求数据的url
            data: {},
            dataType: "json",
            success: function (data) {
                $('button').removeClass("btn-primary").addClass("btn-success").attr('disabled', true);
                var str="";
                    for(var i=0;i<data.length;i++)
                    str+="<tr><td>"+data[i].id+"</td>><td>"+data[i].name+"</td>><td>"+data[i].role+"</td>><td>"+data[i].sex+"</td></tr>";

                $("tbody").append(str);

                viewmodel.text = "数据请求成功，已渲染";
            }
        });
    }
});
var loginmodel = avalon.define({
    $id: "loginmodel",
    text:"登录测试",
    email: "11@qq.com",
    password: "123",

    request: function(){
        $.ajax({
            type: "post",
            url: "/login",
            contentType: "application/jason;charset=utf-8",
            dataType:jason,
            data: JSON.stringify({ 'email': 'foovalue', 'password': 'barvalue' }),
            ssuccess: function (data) {
                alert(data);
            }
        });

    }

});
