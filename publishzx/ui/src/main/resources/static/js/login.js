
function postData() {
    var data = 'email=' + $('#inputEmail').val() + '&pwd=' + $('#inputPassword').val();
    console.log(data+"AJAX 外");
    $.ajax({
        type : 'POST',
        url : '/postData',
        data : data,
        success : function(r) {
            console.log(data+"ajax内");

            if(r=="success"){
                window.location.href = '/home';
                return false;
            }
            else if(r.equals("fail"))//登录失败，alert
                alert("密码错误");
            else if(r=="notexist")
                window.location.href = '/signup';
            else if(r=="error")
                window.location.href = '/error';
            else
                alert("UI 返回错误数据");
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
}


function usersignup() {
    var data = 'username=' + $('#username').val() + '&userpwd=' + $('#userpwd').val() + '&useremail=' +$('#useremail').val();
    console.log(data);
    $.ajax({
        type : 'POST',
        url : '/usersignup',
        data : data,
        success : function(r) {
            console.log(data);
            console.log(r);
            if(r=="success")//注册成功，跳转login
                window.location.href = '/login';
            else if(r=="error")
                window.location.href = '/error';
            else
                alert("UI 返回错误数据");
        },
        error : function() {
            alert('没有返回数据!')
        }
    });
}

function docsignup() {
    var form = $('#docsignup')[0];
    var data2 = new FormData(form);

    var data = 'docname=' + $('#docname').val() + '&docpwd=' + $('#docpwd').val() + '&docemail=' +$('#docemail').val()+'&dochospital='+$('#dochospital').val();
    $.ajax({
        type : 'POST',
        enctype:'mltipart/form-data',
        url : '/docsignup',
        data : data2,
        success : function(r) {
            console.log(data);
            console.log(r);
            if(r=="success")//注册成功，跳转login
                window.location.href = '/login';
            else if(r=="error")
                window.location.href = '/error';
            else
                alert("UI 返回错误数据");
        },
        error : function() {
            alert('没有返回数据!')
        }
    });
}
function upload() {

    window.location.href = '/uploadcase';
}
//
// $(document).ready(function() {
//     jQuery("#clearCac").click(function() {
//         jQuery.ajax({
//             url: url,
//             type: "post",
//             data: { id: '0' },
//             dataType: "json",
//             success: function(msg) {
//                 alert(msg);
//             },
//             error: function(XMLHttpRequest, textStatus, errorThrown) {
//                 alert(XMLHttpRequest.status);
//                 alert(XMLHttpRequest.readyState);
//                 alert(textStatus);
//             },
//             complete: function(XMLHttpRequest, textStatus) {
//                 this; // 调用本次AJAX请求时传递的options参数
//             }
//         });
//     });
// });