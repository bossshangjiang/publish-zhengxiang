$(document).ready(function() {
    onloadcases();

});
function onloadcases() {
    console.log("onloadcases");
    $.ajax({
        type: "POST",
        url: "/mychecked",
        dataType:"json",
        success: function(data)
        {
            console.log(data);
            console.log();
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