$(document).ready(function() {
    onloadcases();

});
function onloadcases() {
    console.log("onloadcases");
    $.ajax({
        type: "POST",
        url: "/tobedone",
        dataType:"json",
        success: function(data)
        {
            console.log(data);
            console.log();
            var str = "";
            $.each(data, function (i, n) {
                str += "<tr><td>"+i+"</td>";

                var ct = n.CT;
                // $.each(ct,function (j,k) {
                str += "<td><img height='150px' width='100px' src='"+ct[0]["path"]+"'/></td><td>"+ct[0]["type"]+"</td><td><a href='/thecheckcase?caseid="+n._id+"'>查看详情</a></td>";
                // })

            });
            $('#casesbody').append(str);

        }
    });
    return false;

}