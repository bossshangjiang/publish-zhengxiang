$(document).ready(function() {
    onloadcases();
    // cellStyle(value, row, index, field);

});
function onloadcases() {
    var disease = 'disease='+$('#disease').text();
    console.log("onloadcases");
    $.ajax({
        type: "POST",
        url: "/cases",
        data:disease,
        dataType:"json",
        success: function(data)
        {
            console.log("data");
            console.log(data);
            var str = "";
            $.each(data, function (i, n) {
                str += "<tr><td>"+i+"</td>";
                 var ct = n.CT;
                //alert(ct[0]["path"]);
                 // $.each(ct,function (j,k) {
                     str += "<td><img height='150px' width='130px' src='"+ct[0]["path"]+"'/></td><td>"+ct[0]["discription"]+"</td>";
                 // })
                str += "<td>"+n.collectionnumber+"</td><td><a href='/thecase?caseid="+n._id+"'>详情</a></td>";

            });
            $('#casesbody').append(str);

        }
    });
    return false;

}
function cellStyle(value, row, index, field) {
    return {
        classes: 'text-nowrap another-class',
        css: {}
    }
}



var $table = $('#table');

// your custom ajax request here
function ajaxRequest(params) {
    //console.log(params.data);
    var offset = params.data.offset;
    var pagesize = params.data.limit;
    var data = "offset=" +offset+ "&pagesize=" + pagesize+ "&disease=" + $('#disease').text();
    $.ajax({
        //要用post方式
        type: "Post",
        //方法所在页面和方法名
        url: "/cases",
        data:data,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function(data) {
            var test = eval(data);
            params.success({

                total: 100,
                rows: test,
            });
        },
        error: function(err) {
            alert("查询失败");
        }
    });

}