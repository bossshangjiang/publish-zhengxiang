$(document).ready(function() {
    onloadcases();

});
function onloadcases() {
    var r = 'key='+$('#key').text();
    $.ajax({
        type: "POST",
        url: "/search",
        data:r,
        dataType:"json",
        success: function(data)
        {
            console.log(data);
            console.log();
            var str = "";
            $.each(data.hits.hits, function (i, n) {

                str += "<tr><td>"+i+"</td>";
                var ct = n._source.CT;
                //alert(ct[0]["path"]);
                // $.each(ct,function (j,k) {
                str += "<td><img height='150px' width='130px' src='"+ct[0]["path"]+"'/></td><td>"+ct[0]["discription"]+"</td>";
                // })
                // str += "<td>"+n.collectionnumber+"</td><td><a href='/thecase?caseid="+n._id+"'>详情</a></td>";

                var fields = [n._source.disease,
                    n._source.extra_exam,
                    n._source.present_history,
                    n._source.MRI_symptom,
                    n._source.CT_symptom,
                    n._source.position,
                    n._source.chief,
                    n._source.sex,
                    n._source.CT_symptom,
                    n._source.lab_exam];
                $.each(fields, function(i,item)
                {
                    detail = keystrsub(item, $('#key').text());
                    if(detail!=-1) {
                        str += "<td>" + n._source.collectionnumber + "</td><td style='width:20%'><a href='/thecase?caseid=" + n._id + "'>" + detail + "</a></td>";
                        return false;
                    }
                    })
            });
            $('#casesbody').append(str);

        }
    });
    return false;

}

function insert_flg(str,flg,sn){
    var newstr=str.substring(0,sn)+flg+str.substring(sn,str.length);
    return newstr;
}
function keystrsub(str, keyword){
    pos = str.toLowerCase().indexOf(keyword.toLowerCase());
    console.log(str);
    console.log(keyword);
    console.log(pos);
    if(pos == -1)
    {
        return -1;
    }
    var length = 40;
    if (pos < length)
    {
        start = 0;
    }
    else{
        start = pos - length;
    }
    if ((pos + length) > str.length)
    {
        end = str.length;
    }
    else{
        end = pos + length;
    }
    str = insert_flg(str,"<em>",pos);
    str = insert_flg(str,"</em>",pos+keyword.length+4);
    result = str.substring(start, end);
    console.log(start,end);
    console.log(result);
    return result;
}